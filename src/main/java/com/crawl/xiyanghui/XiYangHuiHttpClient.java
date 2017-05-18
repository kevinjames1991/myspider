package com.crawl.xiyanghui;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.core.dao.ConnectionManager;
import com.crawl.core.httpclient.AbstractHttpClient;
import com.crawl.core.httpclient.IHttpClient;
import com.crawl.core.util.Config;
import com.crawl.core.util.ThreadPoolMonitor;
import com.crawl.proxy.ProxyHttpClient;
import com.crawl.xiyanghui.task.XiyanghuiPageTask;
import com.crawl.zhihu.dao.ZhiHuDAO;

public class XiYangHuiHttpClient extends AbstractHttpClient{
    private static Logger logger = LoggerFactory.getLogger(XiYangHuiHttpClient.class);
    private volatile static XiYangHuiHttpClient instance;
    private static String authorization;
    
    /**
     * 统计用户数量
     */
    public static AtomicInteger parseProductCount = new AtomicInteger(0);
    private static long startTime = System.currentTimeMillis();
    public static volatile boolean isStop = false;

    public static XiYangHuiHttpClient getInstance(){
        if (instance == null){
            synchronized (XiYangHuiHttpClient.class){
                if (instance == null){
                    instance = new XiYangHuiHttpClient();
                }
            }
        }
        return instance;
    }
    /**
     * 详情页下载网页线程池
     */
    private ThreadPoolExecutor detailPageThreadPool;
    /**
     * 列表页下载网页线程池
     */
    private ThreadPoolExecutor listPageThreadPool;
    private ExecutorService executorService;
    private XiYangHuiHttpClient() {
        initHttpClient();
        intiThreadPool();//初始化线程池
        //开启监控线程,观察爬取线程的状态
//        new Thread(new ThreadPoolMonitor(detailPageThreadPool, "DetailPageDownloadThreadPool")).start();
//        new Thread(new ThreadPoolMonitor(listPageThreadPool, "ListPageDownloadThreadPool")).start();
    }
    /**
     * 初始化
     */
    public void initHttpClient() {
    	authorization = initAuthorization();//授权
    	//初始化数据库配置
        if(Config.dbEnable){
            ZhiHuDAO.DBTablesInit(ConnectionManager.getConnection());
        }
    }
    
    /**
     * 初始化线程池
     */
    private void intiThreadPool(){
    	executorService = Executors.newFixedThreadPool(10);
    }
    
    /**
     * 初始化authorization
     * @return
     */
    private String initAuthorization(){
    	return null;
    }
    
    public static String getAuthorization(){
        return authorization;
    }
    
	public void startCrawl(String url, int count) {
		for (int i = 1; i < count; i++) {
			String pageUrl = url + "/" + i;
			executorService.submit(new XiyanghuiPageTask(pageUrl, Config.isProxy));
		}
		executorService.shutdown();//关闭线程池
		// manageHttpClient();
	}
    /**
     * 关闭整个爬虫
     */
    public void manageHttpClient(){
        while (true) {
            /**
             * 下载网页数
             */
            long downloadPageCount = detailPageThreadPool.getTaskCount();
            if (downloadPageCount >= Config.downloadPageCount &&
                    ! XiYangHuiHttpClient.getInstance().getDetailPageThreadPool().isShutdown()) {
                isStop = true;
                /**
                 * shutdown 下载网页线程池
                 */
                XiYangHuiHttpClient.getInstance().getDetailPageThreadPool().shutdown();
            }
            if(XiYangHuiHttpClient.getInstance().getDetailPageThreadPool().isTerminated() &&
                    !XiYangHuiHttpClient.getInstance().getListPageThreadPool().isShutdown()){
                /**
                 * 下载网页线程池关闭后，再关闭解析网页线程池
                 */
                XiYangHuiHttpClient.getInstance().getListPageThreadPool().shutdown();
            }
            if(XiYangHuiHttpClient.getInstance().getListPageThreadPool().isTerminated()){
                /**
                 * 关闭线程池Monitor
                 */
                ThreadPoolMonitor.isStopMonitor = true;
                /**
                 * 关闭ProxyHttpClient客户端
                 */
                ProxyHttpClient.getInstance().getProxyTestThreadExecutor().shutdownNow();
                logger.info("--------------爬取结果--------------");
                logger.info("获取用户数:" + parseProductCount.get());
                break;
            }
            double costTime = (System.currentTimeMillis() - startTime) / 1000.0;//单位s
            logger.debug("抓取速率：" + parseProductCount.get() / costTime + "个/s");
//            logger.info("downloadFailureProxyPageSet size:" + ProxyHttpClient.downloadFailureProxyPageSet.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public ThreadPoolExecutor getDetailPageThreadPool() {
        return detailPageThreadPool;
    }

    public ThreadPoolExecutor getListPageThreadPool() {
        return listPageThreadPool;
    }
}
