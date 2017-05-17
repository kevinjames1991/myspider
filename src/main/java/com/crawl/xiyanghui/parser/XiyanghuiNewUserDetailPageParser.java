package com.crawl.xiyanghui.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawl.xiyanghui.XiYangHuiHttpClient;
import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.zhihu.ZhiHuHttpClient;
import com.crawl.zhihu.entity.Page;
import com.crawl.zhihu.entity.User;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

/**
 * 
 * @author chenzhangwei
 * @time 2017年5月16日下午4:13:44
 */
public class XiyanghuiNewUserDetailPageParser implements DetailPageParser {
    private volatile static XiyanghuiNewUserDetailPageParser instance;
    public static XiyanghuiNewUserDetailPageParser getInstance(){
        if (instance == null){
            synchronized (XiYangHuiHttpClient.class){
                if (instance == null){
                    instance = new XiyanghuiNewUserDetailPageParser();
                }
            }
        }
        return instance;
    }
    private XiyanghuiNewUserDetailPageParser(){

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
    		System.out.println(page.getHtml());
    		System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
        

    }

    /**
     * jsonPath获取值，并通过反射直接注入到user中
     * @param user
     * @param fieldName
     * @param json
     * @param jsonPath
     */
    private void setUserInfoByJsonPth(User user, String fieldName, String json, String jsonPath){
        try {
            Object o = JsonPath.parse(json).read(jsonPath);
            Field field = user.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(user, o);
        } catch (PathNotFoundException e1) {
            //no results
        } catch (Exception e){
            e.printStackTrace();
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