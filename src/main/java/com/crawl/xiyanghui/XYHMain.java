package com.crawl.xiyanghui;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.core.util.Config;
import com.crawl.proxy.ProxyHttpClient;

public class XYHMain {
    private static Logger logger = LoggerFactory.getLogger(XYHMain.class);
    private static final int count = 5;//要爬取的页数
    public static CountDownLatch latch = new CountDownLatch(count-1);
    public static void main(String args []){
    	long begin = System.currentTimeMillis();
        String startURL = Config.startURLXiYangHui;
        ProxyHttpClient.getInstance().startCrawl();
        XiYangHuiHttpClient.getInstance().startCrawl(startURL, count);
        try {
			latch.await();
			logger.info("all finished"+(System.currentTimeMillis() - begin));
		} catch (Exception e) {
			logger.error("ERROR:",e);
		}
    }
}
