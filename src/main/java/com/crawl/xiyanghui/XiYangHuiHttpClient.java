package com.crawl.xiyanghui;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.crawl.core.dao.ConnectionManager;
import com.crawl.core.httpclient.AbstractHttpClient;
import com.crawl.core.httpclient.IHttpClient;
import com.crawl.core.util.Config;
import com.crawl.core.util.SimpleLogger;
import com.crawl.core.util.ThreadPoolMonitor;
import com.crawl.proxy.ProxyHttpClient;
import com.crawl.xiyanghui.task.XiyanghuiPageTask;
import com.crawl.zhihu.dao.ZhiHuDAO;

public class XiYangHuiHttpClient extends AbstractHttpClient implements IHttpClient{
    private static Logger logger = SimpleLogger.getSimpleLogger(XiYangHuiHttpClient.class);
    private volatile static XiYangHuiHttpClient instance;
    /**
     * 统计用户数量
     */
    public static AtomicInteger parseUserCount = new AtomicInteger(0);
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
    private XiYangHuiHttpClient() {
        initHttpClient();
        intiThreadPool();
        //开启监控线程,观察爬取线程的状态
        new Thread(new ThreadPoolMonitor(detailPageThreadPool, "DetailPageDownloadThreadPool")).start();
        new Thread(new ThreadPoolMonitor(listPageThreadPool, "ListPageDownloadThreadPool")).start();
    }
    /**
     * 初始化HttpClient
     */
    @Override
    public void initHttpClient() {
        if(Config.dbEnable){
            ZhiHuDAO.DBTablesInit(ConnectionManager.getConnection());
        }
    }

    /**
     * 初始化线程池
     */
    private void intiThreadPool(){
        detailPageThreadPool = new ThreadPoolExecutor(Config.downloadThreadSize,
                Config.downloadThreadSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        listPageThreadPool = new ThreadPoolExecutor(50, 50,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
    
    public void startCrawl(String url){
        detailPageThreadPool.execute(new XiyanghuiPageTask(url, Config.isProxy));
        manageHttpClient();
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
                logger.info("获取用户数:" + parseUserCount.get());
                break;
            }
            double costTime = (System.currentTimeMillis() - startTime) / 1000.0;//单位s
            logger.debug("抓取速率：" + parseUserCount.get() / costTime + "个/s");
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
