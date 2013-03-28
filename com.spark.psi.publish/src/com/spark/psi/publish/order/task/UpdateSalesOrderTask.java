/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PsiMethodTask;

/**
 * 新增和修改销售订单
 * @author zhoulijun
 *
 */
public final class UpdateSalesOrderTask extends PsiMethodTask<UpdateSalesOrderTask.Method, UpdateSalesOrderTask.Error> {
	
	public enum Error{
			/**
			 * 商品已停售
			 */
			GoodsStoped("商品已停售"),
			/**
			 * 商品数量必须大于0！
			 */
			GoodsCount_Zero("商品数量必须大于0！"),
			/**
			 * 促销商品超过促销数量
			 */
			promotion("已超促销数量！");
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
	
	@StructField
	private GUID id;// GUID
	@StructField
	private GUID partnerId;// 客户/供应商GUID
	@StructField
	private long deliveryDate;// 交货日期 
	@StructField
	private double amount;	//总金额	
	@StructField
	private String consignee;//	收货人V(10)
	@StructField
	private String consigneePhoneNumber; //收货人电话
	@StructField
	private GUID consigneeId;
	@StructField
	private String address;//	收货地址	V(200)	
	@StructField
	private GUID contactPersonGuid;// 联系人GUID
	@StructField
	private String memo;// 备注
	
	@StructField
	private double discountAmount;//	整单折扣	N
	
	@StructField
	private SalesOrderGoodsItem[] salesOrderGoodsItems;
	
	public GUID getConsigneeId() {
		return consigneeId;
	}



	public void setConsigneeId(GUID consigneeId) {
		this.consigneeId = consigneeId;
	}



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



	public long getDeliveryDate() {
		return deliveryDate;
	}



	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getConsignee() {
		return consignee;
	}



	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}



	public String getConsigneePhoneNumber() {
		return consigneePhoneNumber;
	}



	public void setConsigneePhoneNumber(String consigneePhoneNumber) {
		this.consigneePhoneNumber = consigneePhoneNumber;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
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



	public double getDiscountAmount() {
		return discountAmount;
	}



	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}



	public SalesOrderGoodsItem[] getSalesOrderGoodsItems() {
		return salesOrderGoodsItems;
	}



	public void setSalesOrderGoodsItems(SalesOrderGoodsItem[] salesOrderGoodsItems) {
		this.salesOrderGoodsItems = salesOrderGoodsItems;
	}



	public static final class SalesOrderGoodsItem {
		
		
		private GUID id;
		
		private GUID goodsItemId;//	商品条目id
		
		private double count;//	数量	NUM(10,2)
		
		private double price;//单价
		private double buyAvgPrice;
		
		private double amount;//	金额	NUM(17,2
		
		private double discountCount;//	折扣率	Num(5,4)
		
		private double discountAmount;//	折扣额	NUM(17,2)
		
		private GUID promotionId;//选择的促销商品Id；
		public GUID getId(){
        	return id;
        }
		public void setId(GUID id){
        	this.id = id;
        } 
		public double getBuyAvgPrice() {
			return buyAvgPrice;
		}
		public void setBuyAvgPrice(double buyAvgPrice) {
			this.buyAvgPrice = buyAvgPrice;
		}
		public GUID getGoodsItemId(){
        	return goodsItemId;
        }
		public void setGoodsItemId(GUID goodsItemId){
        	this.goodsItemId = goodsItemId;
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
		public double getDiscountCount(){
        	return discountCount;
        }
		public void setDiscountCount(double discountCount){
        	this.discountCount = discountCount;
        }
		public double getDiscountAmount(){
        	return discountAmount;
        }
		public void setDiscountAmount(double discountAmount){
        	this.discountAmount = discountAmount;
        }
		/**
		 * @return the price
		 */
		public double getPrice() {
			return price;
		}
		/**
		 * @param price the price to set
		 */
		public void setPrice(double price) {
			this.price = price;
		}
		/**
		 * @return the promotionId
		 */
		public GUID getPromotionId() {
			return promotionId;
		}
		/**
		 * @param promotionId the promotionId to set
		 */
		public void setPromotionId(GUID promotionId) {
			this.promotionId = promotionId;
		}
	}
	
}
