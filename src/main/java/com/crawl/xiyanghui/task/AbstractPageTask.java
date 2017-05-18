package com.crawl.xiyanghui.task;

import static com.crawl.core.util.Constants.TIME_INTERVAL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.core.httpclient.AbstractHttpClient;
import com.crawl.core.util.HttpClientUtil;
import com.crawl.proxy.ProxyPool;
import com.crawl.proxy.entity.Direct;
import com.crawl.proxy.entity.Proxy;
import com.crawl.xiyanghui.XYHMain;
import com.crawl.xiyanghui.XiYangHuiHttpClient;
import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.zhihu.ZhiHuHttpClient;
import com.crawl.zhihu.entity.Page;

/**
 * 下载网页任务， 下载成功的Page放到解析线程池
 * 若使用代理，从ProxyPool中取
 * @see ProxyPool
 */
public abstract class AbstractPageTask implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(AbstractPageTask.class);
	protected String url;
	protected HttpRequestBase request;
	private boolean proxyFlag;//是否通过代理下载
	private Proxy currentProxy;//当前线程使用的代理
	private AbstractHttpClient httpClient;
	
	public AbstractPageTask(){

	}
	//详情页
	public AbstractPageTask(AbstractHttpClient httpClient,String url, boolean proxyFlag){
		this.httpClient = httpClient;
		this.url = url;
		this.proxyFlag = proxyFlag;
	}
	
	//列表页
	public AbstractPageTask(HttpRequestBase request, boolean proxyFlag){
		this.request = request;
		this.proxyFlag = proxyFlag;
	}

	public void run(){
		HttpGet tempReqeust = null;
		try {
			Page page = null;
			if(url != null){
				if (proxyFlag){
					tempReqeust = new HttpGet(url);
					currentProxy = ProxyPool.proxyQueue.take();
					if(!(currentProxy instanceof Direct)){
						HttpHost proxy = new HttpHost(currentProxy.getIp(), currentProxy.getPort());
						tempReqeust.setConfig(HttpClientUtil.getRequestConfigBuilder().setProxy(proxy).build());
					}
					page = httpClient.getWebPage(tempReqeust);
				}else {
					page = httpClient.getWebPage(url);
				}
			}
			if(request != null){
				if (proxyFlag){
					currentProxy = ProxyPool.proxyQueue.take();
					if(!(currentProxy instanceof Direct)) {
						HttpHost proxy = new HttpHost(currentProxy.getIp(), currentProxy.getPort());
						request.setConfig(HttpClientUtil.getRequestConfigBuilder().setProxy(proxy).build());
					}
					page = httpClient.getWebPage(request);
				}else {
					page = httpClient.getWebPage(request);
				}
			}
			page.setProxy(currentProxy);
			int status = page.getStatusCode();
			if(status == HttpStatus.SC_OK){
				if (page.getHtml().contains("xiyanghui")){
					if (currentProxy != null) {
						logger.info(Thread.currentThread().getName() + " " + getProxyStr(currentProxy)  + " statusCode:" + status + "  executing request " + page.getUrl());
						currentProxy.setSuccessfulTimes(currentProxy.getSuccessfulTimes() + 1);
					}
					handle(page);
				}else {
					/**
					 * 代理异常，没有正确返回目标url
					 */
					logger.warn("proxy exception:" + currentProxy.toString());
				}

			} else if(status == 404 || status == 401 || status == 410){
					/**
					 * 401--不能通过验证
					 */
				logger.warn(Thread.currentThread().getName() + " " + getProxyStr(currentProxy)  + " statusCode:" + status + "  executing request " + page.getUrl());
			} else {
				logger.error(Thread.currentThread().getName() + " " + getProxyStr(currentProxy)  + " statusCode:" + status + "  executing request " + page.getUrl());
				Thread.sleep(100);
				retry();
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (IOException e) {
            if(currentProxy != null){
                /**
                 * 该代理可用，将该代理继续添加到proxyQueue
                 */
                currentProxy.setFailureTimes(currentProxy.getFailureTimes() + 1);
            }
//            if(!httpClient.getDetailPageThreadPool().isShutdown()){
//				retry();
//			}
		} finally {
//			if (request != null){
//				request.releaseConnection();
//			}
//			if (tempReqeust != null){
//				tempReqeust.releaseConnection();
//			}
//			setProxyUseStrategy();
//			logger.info("finished");
		}
	}

	/**
	 * retry
	 */
	abstract void retry();

    /**
     * 是否继续使用代理
	 * 失败次数大于３，且失败率超过60%，则丢弃
     */
	private void setProxyUseStrategy(){
        if (currentProxy != null){
            int succTimes = currentProxy.getSuccessfulTimes();
            int failTimes = currentProxy.getFailureTimes();
            if(failTimes >= 3){
                double failRate = (failTimes + 0.0) / (succTimes + failTimes);
                if (failRate > 0.6){
                    return;
                }
            }
            currentProxy.setTimeInterval(TIME_INTERVAL);
            ProxyPool.proxyQueue.add(currentProxy);//将当前代理放入代理池中
        }
    }

	abstract void handle(Page page);

	private String getProxyStr(Proxy proxy){
		if (proxy == null){
			return "";
		}
		return proxy.getIp() + ":" + proxy.getPort();
	}
}
