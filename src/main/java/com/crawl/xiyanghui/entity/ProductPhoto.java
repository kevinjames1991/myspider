package com.crawl.xiyanghui.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductPhoto implements Serializable {

	private static final long serialVersionUID = -8945159108095578798L;
	private long id;	
	private String skuID;		//skuID
	private String url;			//西洋汇url
	private String colorId;		//颜色id
	private String sizeId;		//尺码id
	private String localUrl;	//本地图片url
	
	@Override
	public String toString() {
		return "ProductPhoto [id=" + id + ", skuID=" + skuID + ", url=" + url + ", colorId=" + colorId + ", sizeId="
				+ sizeId + ", localUrl=" + localUrl + "]";
	}
	
}
