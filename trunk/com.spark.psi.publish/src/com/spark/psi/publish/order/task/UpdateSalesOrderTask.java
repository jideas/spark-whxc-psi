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
 * �������޸����۶���
 * @author zhoulijun
 *
 */
public final class UpdateSalesOrderTask extends PsiMethodTask<UpdateSalesOrderTask.Method, UpdateSalesOrderTask.Error> {
	
	public enum Error{
			/**
			 * ��Ʒ��ͣ��
			 */
			GoodsStoped("��Ʒ��ͣ��"),
			/**
			 * ��Ʒ�����������0��
			 */
			GoodsCount_Zero("��Ʒ�����������0��"),
			/**
			 * ������Ʒ������������
			 */
			promotion("�ѳ�����������");
			String msg;
			
			/**
			 * ����쳣��ʾ
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
	private GUID partnerId;// �ͻ�/��Ӧ��GUID
	@StructField
	private long deliveryDate;// �������� 
	@StructField
	private double amount;	//�ܽ��	
	@StructField
	private String consignee;//	�ջ���V(10)
	@StructField
	private String consigneePhoneNumber; //�ջ��˵绰
	@StructField
	private GUID consigneeId;
	@StructField
	private String address;//	�ջ���ַ	V(200)	
	@StructField
	private GUID contactPersonGuid;// ��ϵ��GUID
	@StructField
	private String memo;// ��ע
	
	@StructField
	private double discountAmount;//	�����ۿ�	N
	
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
		
		private GUID goodsItemId;//	��Ʒ��Ŀid
		
		private double count;//	����	NUM(10,2)
		
		private double price;//����
		private double buyAvgPrice;
		
		private double amount;//	���	NUM(17,2
		
		private double discountCount;//	�ۿ���	Num(5,4)
		
		private double discountAmount;//	�ۿ۶�	NUM(17,2)
		
		private GUID promotionId;//ѡ��Ĵ�����ƷId��
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
