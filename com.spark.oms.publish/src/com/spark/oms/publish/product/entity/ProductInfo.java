package com.spark.oms.publish.product.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public class ProductInfo {
	//��ƷGUID
	private GUID RECID ;
	//��Ʒ���
	private String category;
	//��Ʒϵ��
	private String serial;
	//��Ʒ����
	private String name;
	//��Ʒ����
	private String code;
	//��ƷԤ������
	private double remindLine;
	//����
	private String remark;
	//��ƷͼƬ
	private byte []  picture;
	
	//��Ʒ�����Ϣ
	private List<ProductItemInfo>  productItems;
	//��Ʒ������
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
