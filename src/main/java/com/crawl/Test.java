package com.crawl;


import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static final ExecutorService SERVICE = Executors.newFixedThreadPool(5);
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) throws Exception {

		Document doc1 = Jsoup.connect("http://www.tv777.net/Aspx/f_filmShows.aspx?id=1").get();
		for (int i = 1; i < 10; i++) {
			String baseUrl = "http://www.xiyanghui.com/women/" + i;
			SERVICE.submit(() -> {
				Document doc;
				try {
					doc = Jsoup.connect(baseUrl).get();
					Elements titles = doc.select("div.title");
					for (Iterator<Element> iterator = titles.iterator(); iterator.hasNext();) {
						Element title = iterator.next();
						Element sElement = title.select("a").first();
						String productUrl = sElement.attr("href");
						Document docProduct = Jsoup.connect(productUrl).get();
						System.out.println(docProduct.select("h5").first().text());
					}
				} catch (Exception e) {
					logger.error(baseUrl + "=====error" + e);
				}
			});
		}
		SERVICE.shutdown();
		System.out.println("finish");
	}

}
