/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 新增和修改销售退货单
 
 *
 */
public class UpdateSalesReturnTask extends Task<UpdateSalesReturnTask.Method> {
	
	public enum Method{
		Create,
		Update
	}
	
	
	@StructField
	private GUID id;// GUID
	@StructField
	private GUID partnerId;// 客户/供应商GUID
	@StructField
	private double amount;	//总金额	
	@StructField
	private GUID contactPersonGuid;// 联系人GUID
	@StructField
	private String memo;// 备注
	
	@StructField
	private SalesReturnGoodsItem[] salesReturnGoodsItems;
	
	

	public GUID getId() {
		return id;
	}



	public void setId(GUID id) {
		this.id = id;
	}



	public GUID getPartnerId() {
		return partnerId;
	}



	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public GUID getContactPersonGuid() {
		return contactPersonGuid;
	}



	public void setContactPersonGuid(GUID contactPersonGuid) {
		this.contactPersonGuid = contactPersonGuid;
	}



	public String getRemark() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
	}



	public SalesReturnGoodsItem[] getSalesReturnGoodsItems() {
		return salesReturnGoodsItems;
	}



	public void setSalesReturnGoodsItems(
			SalesReturnGoodsItem[] salesReturnGoodsItems) {
		this.salesReturnGoodsItems = salesReturnGoodsItems;
	}



	public static final class SalesReturnGoodsItem {
		
		private GUID storeId; //退货仓库id
		
		@StructField
		private GUID goodsItemId;//	商品条目id
		@StructField
		private double price = -1;//	单价	NUM(17,2)
		@StructField
		private double count;//	数量	NUM(10,2)
		@StructField
		private double amount;//	金额	NUM(17,2
		public GUID getStoreId(){
        	return storeId;
        }
		public void setStoreId(GUID storeId){
        	this.storeId = storeId;
        }
		public GUID getGoodsItemId(){
        	return goodsItemId;
        }
		public void setGoodsItemId(GUID goodsItemId){
        	this.goodsItemId = goodsItemId;
        }
		public double getPrice(){
        	return price;
        }
		public void setPrice(double price){
        	this.price = price;
        }
		public double getCount(){
        	return count;
        }
		public void setCount(double count){
        	this.count = count;
        }
		public double getAmount(){
        	return amount;
        }
		public void setAmount(double amount){
        	this.amount = amount;
        }
		
		
	}

}
