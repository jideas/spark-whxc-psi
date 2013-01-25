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
 * �ɹ�����Task
 * @author zhoulijun
 *
 */
@StructClass
public class UpdatePurchaseOrderTask extends PsiMethodTask<UpdatePurchaseOrderTask.Method,UpdatePurchaseOrderTask.Error> {
	
	public enum Error {
		/**
		 * ��Ʒ�����������0��
		 */
		GoodsCount_Zero("��Ʒ�����������0��"),
		/**
		 * ֱ����Ʒ�Ѳɹ�
		 */
		GoodsDirected("ֱ����Ʒ�Ѳɹ�");
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
	
	public static final class PurchaseOrderGoodsItem {
		
		
		@StructField
		private GUID purchaseGoodsItemId; //��زɹ��嵥id
		@StructField
		private String purchaseCause; //�ɹ�ԭ��		
		@StructField
		private GUID goodsItemId;//	��Ʒ��Ŀid
		@StructField
		private Double price;//	����	NUM(17,2)
		@StructField
		private double count;//	����	NUM(10,2)
		@StructField
		private double amount;//	���	NUM(17,2
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
	private GUID partnerId;// �ͻ�/��Ӧ��GUID
//	/**
//	 * 
//	 */
//	private ContactPerson contactPerson;//��ϵ��
//	/**
//	 * 
//	 */
//	private ContactPerson address;//�ջ���ַ
	@StructField
	private long deliveryDate;// �������� 
	@StructField
	private String memo;// ��ע	
	@StructField
	private GUID RelatedId; //���id	
	private String relateName;//�������
	@StructField
	private PurchaseOrderGoodsItem[] purchaseOrderGoodsItems;
	
	@StructField
	private boolean directSupply = false;    //�Ƿ���ֱ��
	
	
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
