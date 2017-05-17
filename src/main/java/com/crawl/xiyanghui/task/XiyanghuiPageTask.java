package com.crawl.xiyanghui.task;


import static com.crawl.zhihu.ZhiHuHttpClient.parseUserCount;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

import com.crawl.core.util.Config;
import com.crawl.core.util.SimpleInvocationHandler;
import com.crawl.core.util.SimpleLogger;
import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.xiyanghui.parser.DetailPageParser;
import com.crawl.xiyanghui.parser.XiyanghuiDetailPageParser;
import com.crawl.zhihu.ZhiHuHttpClient;
import com.crawl.zhihu.entity.Page;


public class XiyanghuiPageTask extends AbstractPageTask {
    private static Logger logger = SimpleLogger.getSimpleLogger(XiyanghuiPageTask.class);
    private static DetailPageParser proxyDetailPageParser;
    static {
        proxyDetailPageParser = getProxyDetailParser();
    }

    public XiyanghuiPageTask(String url, boolean proxyFlag) {
        super(url, proxyFlag);
    }

    @Override
    void retry() {
    	xiYangHuiHttpClient.getDetailPageThreadPool().execute(new XiyanghuiPageTask(url, Config.isProxy));
    }

    @Override
    void handle(Page page) {
        DetailPageParser parser = null;
        parser = XiyanghuiDetailPageParser.getInstance();
        List<ProductInfo> infoList = parser.parse(page);
        if (infoList != null) {
        	System.out.println("解析页面成功:" + infoList.size());
		}
        if(Config.dbEnable){
        	//插入数据库
        }
        parseUserCount.incrementAndGet();
        
    }
    public String formatUserFolloweesUrl(String userToken, int offset){
        String url = "https://www.zhihu.com/api/v4/members/" + userToken + "/followees?include=data%5B*%5D.answer_count%2Carticles_count%2Cfollower_count%2C" +
                "is_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=" + offset + "&limit=20";
        return url;
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
        DetailPageParser detailPageParser = XiyanghuiDetailPageParser.getInstance();
        InvocationHandler invocationHandler = new SimpleInvocationHandler(detailPageParser);
        DetailPageParser proxyDetailPageParser = (DetailPageParser) Proxy.newProxyInstance(detailPageParser.getClass().getClassLoader(),
                detailPageParser.getClass().getInterfaces(), invocationHandler);
        return proxyDetailPageParser;
    }
}
