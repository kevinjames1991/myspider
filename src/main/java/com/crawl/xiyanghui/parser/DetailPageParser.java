package com.crawl.xiyanghui.parser;

import java.util.List;

import com.crawl.xiyanghui.entity.ProductInfo;
import com.crawl.zhihu.entity.Page;

public interface DetailPageParser {
	List<ProductInfo> parse(Page page);
}
