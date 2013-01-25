/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PsiMethodTask;

/**
 * 采购订单Task
 * @author zhoulijun
 *
 */
@StructClass
public class UpdatePurchaseOrderTask extends PsiMethodTask<UpdatePurchaseOrderTask.Method,UpdatePurchaseOrderTask.Error> {
	
	public enum Error {
		/**
		 * 商品数量必须大于0！
		 */
		GoodsCount_Zero("商品数量必须大于0！"),
		/**
		 * 直供商品已采购
		 */
		GoodsDirected("直供商品已采购");
		String msg;
		
		/**
		 * 获得异常提示
		 * 
		 * @return String
		 */
		public String getMsg(){
			return msg;
		}
		
		Error(String msg){
			this.msg = msg;
		}
	}
		
	public enum Method{
		Create,
		Update
	}
	
	public static final class PurchaseOrderGoodsItem {
		
		
		@StructField
		private GUID purchaseGoodsItemId; //相关采购清单id
		@StructField
		private String purchaseCause; //采购原因		
		@StructField
		private GUID goodsItemId;//	商品条目id
		@StructField
		private Double price;//	单价	NUM(17,2)
		@StructField
		private double count;//	数量	NUM(10,2)
		@StructField
		private double amount;//	金额	NUM(17,2
		public String getPurchaseCause(){
			return purchaseCause;
		}
		public void setPurchaseCause(String purchaseCause){
			this.purchaseCause = purchaseCause;
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
		public GUID getPurchaseGoodsItemId(){
			return purchaseGoodsItemId;
		}
		public void setPurchaseGoodsItemId(GUID purchaseGoodsItemId){
			this.purchaseGoodsItemId = purchaseGoodsItemId;
		}
		public void setPrice(Double price){
			this.price = price;
		}
		
		
		
	}
	@StructField
	private GUID id;
	@StructField
	private GUID partnerId;// 客户/供应商GUID
//	/**
//	 * 
//	 */
//	private ContactPerson contactPerson;//联系人
//	/**
//	 * 
//	 */
//	private ContactPerson address;//收货地址
	@StructField
	private long deliveryDate;// 交货日期 
	@StructField
	private String memo;// 备注	
	@StructField
	private GUID RelatedId; //相关id	
	private String relateName;//相关名称
	@StructField
	private PurchaseOrderGoodsItem[] purchaseOrderGoodsItems;
	
	@StructField
	private boolean directSupply = false;    //是否是直供
	
	
	public String getRelateName() {
		return relateName;
	}
	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public long getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public PurchaseOrderGoodsItem[] getPurchaseOrderGoodsItems() {
		return purchaseOrderGoodsItems;
	}

	public void setPurchaseOrderGoodsItems(
			PurchaseOrderGoodsItem[] purchaseOrderGoodsItems) {
		this.purchaseOrderGoodsItems = purchaseOrderGoodsItems;
	}

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID getRelatedId(){
    	return RelatedId;
    }

	public void setRelatedId(GUID relatedId){
    	RelatedId = relatedId;
    }

	public boolean isDirectSupply(){
    	return directSupply;
    }

	public void setDirectSupply(boolean directSupply){
    	this.directSupply = directSupply;
    }
}
