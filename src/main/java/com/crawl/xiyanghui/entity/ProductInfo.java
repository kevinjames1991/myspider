package com.crawl.xiyanghui.entity;

import java.util.Date;
import java.util.List;

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

    public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname == null ? null : longname.trim();
    }

    public String getOriginno() {
        return originno;
    }

    public void setOriginno(String originno) {
        this.originno = originno == null ? null : originno.trim();
    }

    public String getOrigincolor() {
        return origincolor;
    }

    public void setOrigincolor(String origincolor) {
        this.origincolor = origincolor == null ? null : origincolor.trim();
    }

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno == null ? null : productno.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection == null ? null : collection.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getWaveband() {
        return waveband;
    }

    public void setWaveband(String waveband) {
        this.waveband = waveband == null ? null : waveband.trim();
    }

    public String getProductgroup() {
        return productgroup;
    }

    public void setProductgroup(String productgroup) {
        this.productgroup = productgroup == null ? null : productgroup.trim();
    }

    public String getExestandard() {
        return exestandard;
    }

    public void setExestandard(String exestandard) {
        this.exestandard = exestandard == null ? null : exestandard.trim();
    }

    public String getSalftype() {
        return salftype;
    }

    public void setSalftype(String salftype) {
        this.salftype = salftype == null ? null : salftype.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public String getComponent1() {
        return component1;
    }

    public void setComponent1(String component1) {
        this.component1 = component1 == null ? null : component1.trim();
    }

    public String getComponentdesc() {
        return componentdesc;
    }

    public void setComponentdesc(String componentdesc) {
        this.componentdesc = componentdesc == null ? null : componentdesc.trim();
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getColornumber() {
        return colornumber;
    }

    public void setColornumber(String colornumber) {
        this.colornumber = colornumber == null ? null : colornumber.trim();
    }

    public String getRegistnumber() {
        return registnumber;
    }

    public void setRegistnumber(String registnumber) {
        this.registnumber = registnumber == null ? null : registnumber.trim();
    }

    public String getCiqgoodsno() {
        return ciqgoodsno;
    }

    public void setCiqgoodsno(String ciqgoodsno) {
        this.ciqgoodsno = ciqgoodsno == null ? null : ciqgoodsno.trim();
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode == null ? null : hscode.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public Long getOnhand() {
        return onhand;
    }

    public void setOnhand(Long onhand) {
        this.onhand = onhand;
    }

    public String getOnhandlabel() {
        return onhandlabel;
    }

    public void setOnhandlabel(String onhandlabel) {
        this.onhandlabel = onhandlabel == null ? null : onhandlabel.trim();
    }

    public String getOriginPriceWas() {
        return originPriceWas;
    }

    public void setOriginPriceWas(String originPriceWas) {
        this.originPriceWas = originPriceWas == null ? null : originPriceWas.trim();
    }

    public String getOriginPriceNow() {
        return originPriceNow;
    }

    public void setOriginPriceNow(String originPriceNow) {
        this.originPriceNow = originPriceNow == null ? null : originPriceNow.trim();
    }

    public String getPriceWas() {
        return priceWas;
    }

    public void setPriceWas(String priceWas) {
        this.priceWas = priceWas == null ? null : priceWas.trim();
    }

    public String getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(String priceNow) {
        this.priceNow = priceNow == null ? null : priceNow.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getSellerUrl() {
        return sellerUrl;
    }

    public void setSellerUrl(String sellerUrl) {
        this.sellerUrl = sellerUrl == null ? null : sellerUrl.trim();
    }

    public String getSellerRebateUrl() {
        return sellerRebateUrl;
    }

    public void setSellerRebateUrl(String sellerRebateUrl) {
        this.sellerRebateUrl = sellerRebateUrl == null ? null : sellerRebateUrl.trim();
    }

    public String getSellerCountry() {
        return sellerCountry;
    }

    public void setSellerCountry(String sellerCountry) {
        this.sellerCountry = sellerCountry == null ? null : sellerCountry.trim();
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType == null ? null : sellerType.trim();
    }

    public String getSellerLanguage() {
        return sellerLanguage;
    }

    public void setSellerLanguage(String sellerLanguage) {
        this.sellerLanguage = sellerLanguage == null ? null : sellerLanguage.trim();
    }

    public String getSellerDesc() {
        return sellerDesc;
    }

    public void setSellerDesc(String sellerDesc) {
        this.sellerDesc = sellerDesc == null ? null : sellerDesc.trim();
    }

    public String getSellerPayment() {
        return sellerPayment;
    }

    public void setSellerPayment(String sellerPayment) {
        this.sellerPayment = sellerPayment == null ? null : sellerPayment.trim();
    }

    public String getSellerFreights() {
        return sellerFreights;
    }

    public void setSellerFreights(String sellerFreights) {
        this.sellerFreights = sellerFreights == null ? null : sellerFreights.trim();
    }

    public String getSellerSource() {
        return sellerSource;
    }

    public void setSellerSource(String sellerSource) {
        this.sellerSource = sellerSource == null ? null : sellerSource.trim();
    }

    public String getSellerReturnDay() {
        return sellerReturnDay;
    }

    public void setSellerReturnDay(String sellerReturnDay) {
        this.sellerReturnDay = sellerReturnDay == null ? null : sellerReturnDay.trim();
    }

    public String getSellerService() {
        return sellerService;
    }

    public void setSellerService(String sellerService) {
        this.sellerService = sellerService == null ? null : sellerService.trim();
    }

    public String getBrandEn() {
        return brandEn;
    }

    public void setBrandEn(String brandEn) {
        this.brandEn = brandEn == null ? null : brandEn.trim();
    }

    public String getBrandCn() {
        return brandCn;
    }

    public void setBrandCn(String brandCn) {
        this.brandCn = brandCn == null ? null : brandCn.trim();
    }

    public String getBrandCountry() {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry == null ? null : brandCountry.trim();
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc == null ? null : brandDesc.trim();
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo == null ? null : brandLogo.trim();
    }

    public String getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(String canBuy) {
        this.canBuy = canBuy == null ? null : canBuy.trim();
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }

    public String getFreightNote() {
        return freightNote;
    }

    public void setFreightNote(String freightNote) {
        this.freightNote = freightNote == null ? null : freightNote.trim();
    }

    public String getIncomingFreight() {
        return incomingFreight;
    }

    public void setIncomingFreight(String incomingFreight) {
        this.incomingFreight = incomingFreight == null ? null : incomingFreight.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getSiteFreight() {
        return siteFreight;
    }

    public void setSiteFreight(String siteFreight) {
        this.siteFreight = siteFreight == null ? null : siteFreight.trim();
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId == null ? null : colorId.trim();
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public String getColorSample() {
        return colorSample;
    }

    public void setColorSample(String colorSample) {
        this.colorSample = colorSample == null ? null : colorSample.trim();
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId == null ? null : sizeId.trim();
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName == null ? null : sizeName.trim();
    }

	public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getIsmicrodiscount() {
		return ismicrodiscount;
	}

	public void setIsmicrodiscount(String ismicrodiscount) {
		this.ismicrodiscount = ismicrodiscount;
	}

	public String getMicrodiscount() {
		return microdiscount;
	}

	public void setMicrodiscount(String microdiscount) {
		this.microdiscount = microdiscount;
	}

	public String getIsbondedproduct() {
		return isbondedproduct;
	}

	public void setIsbondedproduct(String isbondedproduct) {
		this.isbondedproduct = isbondedproduct;
	}

	public String getNewproduct() {
		return newproduct;
	}

	public void setNewproduct(String newproduct) {
		this.newproduct = newproduct;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getTaobaoFreight() {
		return taobaoFreight;
	}

	public void setTaobaoFreight(String taobaoFreight) {
		this.taobaoFreight = taobaoFreight;
	}

	public String getModernCalcPrice() {
		return modernCalcPrice;
	}

	public void setModernCalcPrice(String modernCalcPrice) {
		this.modernCalcPrice = modernCalcPrice;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	
	public List<ProductPhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<ProductPhoto> photoList) {
		this.photoList = photoList;
	}

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