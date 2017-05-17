package com.crawl.xiyanghui.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.xiyanghui.XiYangHuiHttpClient;
import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.zhihu.ZhiHuHttpClient;
import com.crawl.zhihu.entity.Page;

/**
 * 
 * @author chenzhangwei
 * @time 2017年5月16日下午4:13:44
 */
public class XiyanghuiDetailPageParser implements DetailPageParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(XiyanghuiDetailPageParser.class);
    private volatile static XiyanghuiDetailPageParser instance;
    public static XiyanghuiDetailPageParser getInstance(){
        if (instance == null){
            synchronized (XiYangHuiHttpClient.class){
                if (instance == null){
                    instance = new XiyanghuiDetailPageParser();
                }
            }
        }
        return instance;
    }
    private XiyanghuiDetailPageParser(){

    }
    @Override
    public List<ProductInfo> parse(Page page) {
    	List<ProductInfo> infoList = new ArrayList<>();
        Document doc = Jsoup.parse(page.getHtml());
        Elements productElements = doc.getElementsByClass("product-item");
        for (Element element : productElements) {
        	Elements hrefElements = element.getElementsByAttribute("href");
        	int i = 1;
        	for (Element element2 : hrefElements) {
				if (i%2 == 0) {
					String productUrl = getProductId(element2.toString());
					ProductInfo productInfo = new ProductInfo();
					getProductInfo(productInfo,productUrl);
					infoList.add(productInfo);
				}else {
					i++;
					continue;
				}
			}
		}
        return infoList;
    }
    private void getProductInfo(ProductInfo info, String productUrl){
    	try {
    		Page page = ZhiHuHttpClient.getInstance().getWebPage(productUrl);
    		String pageHtml = page.getHtml();
    		Document doc = Jsoup.parse(pageHtml);
    		//获取产品图片
    		Elements photos = doc.select(".prd-images").select(".clearfix").select(".col-md-7").select(".product-show").first().select("[data-origin]");
    		for (Element photo : photos) {
				System.out.println(photo.attr("data-origin"));
			}
    		//获取产品详细信息
    		Element details = doc.select(".prd-desc").select(".col-md-5").first();
    		String subjectId = productUrl.replace("http://www.xiyanghui.com/product/", "");
    		String brandname = details.select(".brand").first().select("a").first().html();
    		String productNo = details.select(".product-id").first().text();
    		String longname = details.select(".title").first().html();
    		String origin = details.select(".col-sm-7").first().select("a").first().attr("title");
    		String originPriceNow = details.select(".cur").first().html();
    		String originPriceWas = details.select(".old").first().html();
    		String discount = details.select(".discount").first().html();
    		Elements sizes = doc.select(".size-guide").select("button");
    		StringBuffer sizeBuf = new StringBuffer("");
    		for (Element element : sizes) {
				sizeBuf.append(element.attr("data-key")+"  ");
			}
    		String desc = details.select(".panel-body").select(".desc").first().html();
    		String brandDesc = details.select(".panel-body").select(".brand_desc").first().text().replaceAll("查看此品牌全部商品", "");
    		String shopDesc = details.select(".panel-body").select(".site_desc").first().text().replaceAll("查看此商家全部商品", "");
    		String size = sizeBuf.toString();
    		System.out.println(subjectId);
    		System.out.println(productNo+brandname+longname+origin+originPriceNow+originPriceWas+discount+size);
    		System.out.println(desc);
    		System.out.println(brandDesc);
    		System.out.println(shopDesc);
		} catch (Exception e) {
			LOGGER.error("parse error",e);
		}
        

    }

    /**
     * 
     * @param url
     * @return
     */
    private String getProductId(String url){
        Pattern pattern = Pattern.compile("http://www.xiyanghui.com/product/[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(url);
        String id = null;
        if(matcher.find()){
        	id = matcher.group(0);
            return id;
        }
        throw new RuntimeException("not parse userId");
    }
}