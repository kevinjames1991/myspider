package com.crawl.xiyanghui.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.bcel.generic.IFNE;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawl.xiyanghui.XiYangHuiHttpClient;
import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.xiyanghui.entity.ProductPhoto;
import com.crawl.zhihu.ZhiHuHttpClient;
import com.crawl.zhihu.entity.Page;

/**
 * 产品详情页解析类
 * @author chenzhangwei
 * @time 2017年5月16日下午4:13:44
 */
public class DetailPageParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(DetailPageParser.class);
    private volatile static DetailPageParser instance;
    public static final String baseUrl = "http://www.xiyanghui.com/product/";
    public static DetailPageParser getInstance(){
        if (instance == null){
            synchronized (XiYangHuiHttpClient.class){
                if (instance == null){
                    instance = new DetailPageParser();
                }
            }
        }
        return instance;
    }
    
    private DetailPageParser(){

    }
    
    public List<ProductInfo> parse(Page page) {
    	List<ProductInfo> infoList = new ArrayList<>();
        Document doc = Jsoup.parse(page.getHtml());
        Elements productElements = doc.select(".product-item").select(".col-fl-2").select(".col-lg-3").select(".col-md-4").select(".col-xs-6");
        for (Element element : productElements) {
        	String id = element.attr("id");
        	String productUrl = baseUrl + id;
        	ProductInfo productInfo = new ProductInfo();
        	getProductInfo(productInfo,productUrl);
        	XiYangHuiHttpClient.parseProductCount.incrementAndGet();
        	infoList.add(productInfo);
        	LOGGER.info(productUrl);
		}
        return infoList;
    }
    
    /**
     * 获取产品详细信息
     * @param info
     * @param productUrl
     */
    private void getProductInfo(ProductInfo info, String productUrl){
    	try {
    		Page page = XiYangHuiHttpClient.getInstance().getWebPage(productUrl);
    		String subjectId = productUrl.replace(baseUrl, "");
    		info.setSubjectId(subjectId);
    		String pageHtml = page.getHtml();
    		Document doc = Jsoup.parse(pageHtml);
    		//获取产品图片
    		Elements photos = doc.select(".prd-images").select(".clearfix").select(".col-md-7").select(".product-show").first().select("[data-origin]");
    		List<ProductPhoto> photoList = new ArrayList<>();
    		for (Element photo : photos) {
    			ProductPhoto productPhoto = new ProductPhoto();
    			productPhoto.setSkuID(subjectId);
    			productPhoto.setUrl(photo.attr("data-origin"));
    			photoList.add(productPhoto);
			}
    		info.setPhotoList(photoList);
    		//获取产品详细信息
    		Element details = doc.select(".prd-desc").select(".col-md-5").first();
    		String brandname = details.select(".brand").first().select("a").first().html();
    		info.setBrandCn(brandname);
    		String productNo = details.select(".product-id").first().text();
    		info.setProductno(productNo);
    		String longname = details.select(".title").first().html();
    		info.setLongname(longname);
    		String origin = details.select(".col-sm-7").first().select("a").first().attr("title");
    		info.setOrigin(origin);
    		String originPriceNow = details.select(".cur").first().html();
    		info.setOriginPriceNow(originPriceNow);
    		String originPriceWas = details.select(".old").first().html();
    		info.setOriginPriceWas(originPriceWas);
    		String discount = details.select(".discount").first().html();
    		info.setDiscount(discount);
    		Elements sizes = doc.select(".size-guide").select("button");
    		StringBuffer sizeBuf = new StringBuffer("");
    		for (Element element : sizes) {
				sizeBuf.append(element.attr("data-key")+"  ");
			}
    		String size = sizeBuf.toString();
    		info.setSizeName(size);
    		String desc = details.select(".panel-body").select(".desc").first().html();
    		info.setProductDesc(desc);
    		String brandDesc = details.select(".panel-body").select(".brand_desc").first().text().replaceAll("查看此品牌全部商品", "");
    		info.setBrandDesc(brandDesc);
    		String shopDesc = details.select(".panel-body").select(".site_desc").first().text().replaceAll("查看此商家全部商品", "");
    		info.setShopDesc(shopDesc);
//    		LOGGER.info(info.toString());
		} catch (Exception e) {
//			LOGGER.error("parse error",e);
		}
    }

    /**
     * 获取产品详情页URL
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
        throw new RuntimeException("not parse ProductId");
    }
}