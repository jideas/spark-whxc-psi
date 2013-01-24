package com.spark.oms.publish.product.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.product.entity.ProductAddFunction;
import com.spark.oms.publish.product.entity.ProductGracePeriod;
import com.spark.oms.publish.product.entity.ProductItemInfo;

/**
 * 新建编辑产品详情
 *
 */
public class CreateProductTask  extends SimpleTask{
	//产品规格信息
	private List<ProductItemInfo> productItems;
	
	//被删除的产品规格列表
	private List<GUID>  deletedProductItems;
	
	//产品宽限期
	private ProductGracePeriod productGracePeriod;
	
	//产品附件功能
	private List<ProductAddFunction> productAddFunctions;
	
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
	public List<GUID> getDeletedProductItems() {
		return deletedProductItems;
	}
	public void setDeletedProductItems(List<GUID> deletedProductItems) {
		this.deletedProductItems = deletedProductItems;
	}
	public List<ProductAddFunction> getProductAddFunctions() {
		return productAddFunctions;
	}
	public void setProductAddFunctions(List<ProductAddFunction> productAddFunctions) {
		this.productAddFunctions = productAddFunctions;
	}

}
