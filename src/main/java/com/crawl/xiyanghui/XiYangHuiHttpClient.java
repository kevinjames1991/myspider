package com.crawl.xiyanghui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.core.dao.ConnectionManager;
import com.crawl.core.httpclient.AbstractHttpClient;
import com.crawl.core.util.Config;
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
//			new XiyanghuiPageTask(pageUrl, Config.isProxy).run();
			executorService.submit(new XiyanghuiPageTask(pageUrl, Config.isProxy));
		}
		executorService.shutdown();//关闭线程池
	}

    public ThreadPoolExecutor getDetailPageThreadPool() {
        return detailPageThreadPool;
    }

    public ThreadPoolExecutor getListPageThreadPool() {
        return listPageThreadPool;
    }
}
