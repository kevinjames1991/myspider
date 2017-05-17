package com.crawl.xiyanghui.entity;

import java.io.Serializable;

public class ProductPhoto implements Serializable {

	private static final long serialVersionUID = -8945159108095578798L;
	private long id;	
	private String skuID;		//skuID
	private String url;			//西洋汇url
	private String colorId;		//颜色id
	private String sizeId;		//尺码id
	private String localUrl;	//本地图片url
	
	public String getLocalUrl() {
		return localUrl;
	}
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSkuID() {
		return skuID;
	}
	public void setSkuID(String skuID) {
		this.skuID = skuID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	@Override
	public String toString() {
		return "DProductPhotoShop [id=" + id + ", skuID=" + skuID + ", url=" + url + ", colorId=" + colorId
				+ ", sizeId=" + sizeId + ", localUrl=" + localUrl + "]";
	}
	
}
