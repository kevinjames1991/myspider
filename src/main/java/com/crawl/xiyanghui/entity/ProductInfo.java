package com.crawl.xiyanghui.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInfo {
    private String skuId;//产品id（西洋汇SKU_ID）

    private String name;//产品名称

    private String longname;//产品长名称（西洋汇TITLE）

    private String originno;//原厂货号（西洋汇NO）

    private String ismicrodiscount;//是否参与微折扣:0否 1是（西洋汇DISCOUNT）

    private String microdiscount;//微折扣值（西洋汇DISCOUNT）

    private String origincolor;//原色号

    private String productno;//货号

    private String origin;//产地（西洋汇COUNTRY）

    private String collection;//款号  （西洋汇SPU_ID）

    private String model;//型号

    private String years;//年份
    
    private String discount;//折扣

    private String season;//季度
    
    private String shopDesc;//商家介绍

    private String style;//风格描述

    private String material;//材质描述

    private String waveband;//波段描述 

    private String productgroup;//群体 男士 女士 等

    private String exestandard;//执行标准

    private String salftype;//安全技术类别  如：A类 B类 C类

    private String rank;//产品等级     如：一等品 二等品

    private String component1;//成分

    private String componentdesc;//成分说明

    private Integer unit;//单位   件、套 等

    private String isbondedproduct;//0=保税产品 1=完税产品 2=海外直邮 等

    private String colornumber;//色号

    private String registnumber;//产品海关备案号

    private String ciqgoodsno;//国检商品备案号

    private String hscode;//海关国检编码

    private String newproduct;//是否新品 0否 1是（西洋汇IS_NEW）

    private String productDesc;//产品描述（西洋汇PRODUCT_DESC）

    private Long onhand;//库存

    private String onhandlabel;//库存提示信息

    private String originPriceWas;//商品货源站原价

    private String originPriceNow;//商品货源站现价

    private String priceWas;//原价

    private String priceNow;//现价

    private String sellerName;//货源名称

    private String sellerUrl;//货源官网

    private String sellerRebateUrl;//货源购买地址

    private String sellerCountry;//货源国家

    private String sellerType;//货源类型

    private String sellerLanguage;//货源语言

    private String sellerDesc;//货源描述

    private String sellerPayment;//货源支付方式

    private String sellerFreights;//运费

    private String sellerSource;//采购方式

    private String sellerReturnDay;//退货说明

    private String sellerService;//其它说明

    private String brandEn;//品牌英文名

    private String brandCn;//品牌中文名

    private String brandCountry;//品牌国家

    private String brandDesc;//品牌描述

    private String brandLogo;//品牌logo

    private String canBuy;//是否可以购买

    private String freight;//运费

    private String freightNote;//运费显示文字

    private String incomingFreight;//跨境运费

    private String weight;//重量

    private String siteFreight;//商家运费

    private String colorId;//颜色id

    private String colorName;//颜色名称

    private String colorSample;//颜色样本

    private String sizeId;//尺寸id

    private String sizeName;//尺寸名称

    private String shopId;//区分商家id，1：西洋汇  
    
    private String subjectId;//主题ID

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
    
    private String taobaoFreight;//运费
    
    private String modernCalcPrice;//售价
    
    //add by chend 如下这个字段私用
    private String productSku;//产品sku
    
    private String url;//图片url
    
    private String shopCode;//商家编码
    
    private String status;//状态
    
    private String lotNo;//批次号
    
    private List<ProductPhoto> photoList;//产品图片列表

	@Override
	public String toString() {
		return "ProductInfo [skuId=" + skuId + ", name=" + name + ", longname=" + longname + ", originno=" + originno
				+ ", ismicrodiscount=" + ismicrodiscount + ", microdiscount=" + microdiscount + ", origincolor="
				+ origincolor + ", productno=" + productno + ", origin=" + origin + ", collection=" + collection
				+ ", model=" + model + ", years=" + years + ", discount=" + discount + ", season=" + season
				+ ", shopDesc=" + shopDesc + ", style=" + style + ", material=" + material + ", waveband=" + waveband
				+ ", productgroup=" + productgroup + ", exestandard=" + exestandard + ", salftype=" + salftype
				+ ", rank=" + rank + ", component1=" + component1 + ", componentdesc=" + componentdesc + ", unit="
				+ unit + ", isbondedproduct=" + isbondedproduct + ", colornumber=" + colornumber + ", registnumber="
				+ registnumber + ", ciqgoodsno=" + ciqgoodsno + ", hscode=" + hscode + ", newproduct=" + newproduct
				+ ", productDesc=" + productDesc + ", onhand=" + onhand + ", onhandlabel=" + onhandlabel
				+ ", originPriceWas=" + originPriceWas + ", originPriceNow=" + originPriceNow + ", priceWas=" + priceWas
				+ ", priceNow=" + priceNow + ", sellerName=" + sellerName + ", sellerUrl=" + sellerUrl
				+ ", sellerRebateUrl=" + sellerRebateUrl + ", sellerCountry=" + sellerCountry + ", sellerType="
				+ sellerType + ", sellerLanguage=" + sellerLanguage + ", sellerDesc=" + sellerDesc + ", sellerPayment="
				+ sellerPayment + ", sellerFreights=" + sellerFreights + ", sellerSource=" + sellerSource
				+ ", sellerReturnDay=" + sellerReturnDay + ", sellerService=" + sellerService + ", brandEn=" + brandEn
				+ ", brandCn=" + brandCn + ", brandCountry=" + brandCountry + ", brandDesc=" + brandDesc
				+ ", brandLogo=" + brandLogo + ", canBuy=" + canBuy + ", freight=" + freight + ", freightNote="
				+ freightNote + ", incomingFreight=" + incomingFreight + ", weight=" + weight + ", siteFreight="
				+ siteFreight + ", colorId=" + colorId + ", colorName=" + colorName + ", colorSample=" + colorSample
				+ ", sizeId=" + sizeId + ", sizeName=" + sizeName + ", shopId=" + shopId + ", subjectId=" + subjectId
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateTime="
				+ updateTime + ", taobaoFreight=" + taobaoFreight + ", modernCalcPrice=" + modernCalcPrice
				+ ", productSku=" + productSku + ", url=" + url + ", shopCode=" + shopCode + ", status=" + status
				+ ", lotNo=" + lotNo + ", photoList=" + photoList + "]";
	}
}