package com.crawl.xiyanghui;

import org.apache.log4j.Logger;

import com.crawl.Main;
import com.crawl.core.util.Config;
import com.crawl.core.util.SimpleLogger;

public class XYHMain {
    private static Logger logger = SimpleLogger.getLogger(Main.class);
    public static void main(String args []){
        String startURL = Config.startURLXiYangHui;
//        ProxyHttpClient.getInstance().startCrawl();
        System.out.println(startURL);
        XiYangHuiHttpClient.getInstance().startCrawl(startURL);
    }
}
