package com.spark.oms.publish.product.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public class ProductInfo {
	//产品GUID
	private GUID RECID ;
	//产品类别
	private String category;
	//产品系列
	private String serial;
	//产品名称
	private String name;
	//产品编码
	private String code;
	//产品预计下线
	private double remindLine;
	//描述
	private String remark;
	//产品图片
	private byte []  picture;
	
	//产品规格信息
	private List<ProductItemInfo>  productItems;
	//产品宽限期
	private ProductGracePeriod productGracePeriod;
	
	
	public GUID getRECID() {
		return RECID;
	}
	public String getCategory() {
		return category;
	}
	public String getSerial() {
		return serial;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public double getRemindLine() {
		return remindLine;
	}
	public String getRemark() {
		return remark;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setRemindLine(double remindLine) {
		this.remindLine = remindLine;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public ProductGracePeriod getProductGracePeriod() {
		return productGracePeriod;
	}
	public void setProductGracePeriod(ProductGracePeriod productGracePeriod) {
		this.productGracePeriod = productGracePeriod;
	}
	public List<ProductItemInfo> getProductItems() {
		return productItems;
	}
	public void setProductItems(List<ProductItemInfo> productItems) {
		this.productItems = productItems;
	}
	public void addProductItem(ProductItemInfo item){
		if(productItems==null){
			 productItems = new ArrayList<ProductItemInfo>();
		}
		productItems.add(item);
		
	}
	
}
