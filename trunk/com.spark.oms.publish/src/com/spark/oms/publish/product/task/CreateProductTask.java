package com.spark.oms.publish.product.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.product.entity.ProductAddFunction;
import com.spark.oms.publish.product.entity.ProductGracePeriod;
import com.spark.oms.publish.product.entity.ProductItemInfo;

/**
 * �½��༭��Ʒ����
 *
 */
public class CreateProductTask  extends SimpleTask{
	//��Ʒ�����Ϣ
	private List<ProductItemInfo> productItems;
	
	//��ɾ���Ĳ�Ʒ����б�
	private List<GUID>  deletedProductItems;
	
	//��Ʒ������
	private ProductGracePeriod productGracePeriod;
	
	//��Ʒ��������
	private List<ProductAddFunction> productAddFunctions;
	
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
