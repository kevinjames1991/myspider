package com.crawl.xiyanghui;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.core.util.Config;

public class XYHMain {
    private static Logger logger = LoggerFactory.getLogger(XYHMain.class);
    private static final int count = 2;
    public static CountDownLatch latch = new CountDownLatch(count-1);
    public static void main(String args []){
        String startURL = Config.startURLXiYangHui;
//        ProxyHttpClient.getInstance().startCrawl();
        logger.info(startURL);
        XiYangHuiHttpClient.getInstance().startCrawl(startURL, count);
        try {
			latch.await();
			logger.info("all finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
