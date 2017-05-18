package com.crawl.xiyanghui.task;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.core.util.Config;
import com.crawl.core.util.SimpleInvocationHandler;
import com.crawl.xiyanghui.XYHMain;
import com.crawl.xiyanghui.XiYangHuiHttpClient;
import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.xiyanghui.parser.DetailPageParser;
import com.crawl.zhihu.ZhiHuHttpClient;
import com.crawl.zhihu.entity.Page;


public class XiyanghuiPageTask extends AbstractPageTask {
    private static Logger logger = LoggerFactory.getLogger(XiyanghuiPageTask.class); 
    private static DetailPageParser proxyDetailPageParser;
    private static XiYangHuiHttpClient xiYangHuiHttpClient = XiYangHuiHttpClient.getInstance();
    static {
//        proxyDetailPageParser = getProxyDetailParser();
    }

    public XiyanghuiPageTask(String url, boolean proxyFlag) {
        super(xiYangHuiHttpClient, url, proxyFlag);
    }

    @Override
    void retry() {
    	xiYangHuiHttpClient.getDetailPageThreadPool().execute(new XiyanghuiPageTask(url, Config.isProxy));
    }

    @Override
    void handle(Page page) {
        DetailPageParser parser = DetailPageParser.getInstance();
        List<ProductInfo> infoList = parser.parse(page);
        if (infoList != null) {
        	logger.info("解析页面成功:" + infoList.size());
        	XYHMain.latch.countDown();
		}
        if(Config.dbEnable){
        	//插入数据库操作
        }
    }
    
    private void handleUrl(String url){
        HttpGet request = new HttpGet(url);
        request.setHeader("authorization", "oauth " + ZhiHuHttpClient.getAuthorization());
//        if(!Config.dbEnable){
//            zhiHuHttpClient.getListPageThreadPool().execute(new ListPageTask(request, Config.isProxy));
//            return ;
//        }
//        if(zhiHuHttpClient.getListPageThreadPool().getQueue().size() < 100){
//            zhiHuHttpClient.getListPageThreadPool().execute(new ListPageTask(request, Config.isProxy));
//        }
    }

    /**
     * 代理类
     * @return
     */
    private static DetailPageParser getProxyDetailParser(){
        DetailPageParser detailPageParser = DetailPageParser.getInstance();
        InvocationHandler invocationHandler = new SimpleInvocationHandler(detailPageParser);
        DetailPageParser proxyDetailPageParser = (DetailPageParser) Proxy.newProxyInstance(detailPageParser.getClass().getClassLoader(),
                detailPageParser.getClass().getInterfaces(), invocationHandler);
        return proxyDetailPageParser;
    }
}
