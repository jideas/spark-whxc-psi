/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �ɹ�����Task
 * �޸Ĳɹ�����
 
 *
 */
public class UpdatePurchaseGoodsTask extends SimpleTask {
	
	
	private PurchaseGoods[] purchaseGoods;

	public UpdatePurchaseGoodsTask(){
	}	
	
	public PurchaseGoods[] getPurchaseGoods() {
		return purchaseGoods;
	}

	public void setPurchaseGoods(PurchaseGoods... purchaseGoods) {
		this.purchaseGoods = purchaseGoods;
	}



	/**
	 * �ɹ���Ʒ�嵥
	 
	 *
	 */
	public static final class PurchaseGoods {
		
		private GUID id;
		
//		private GUID goodsItemId;//	��Ʒ��Ŀid
				
		private double price = -1;//	����	NUM(17,2)
		
//		private double count;//	����	NUM(10,2)
		
		private GUID supplierId; //��Ӧ��id
		private GUID contactId;//��ϵ��Id
		
//		private double recentPrice; //�ϴβɹ�����
		
//		private GUID storeId; //�ֿ�ID

		private String purchaseCause;
		private boolean isDirect;//�Ƿ�ֱ������
		
		
		/**
		 * @return the isDirect
		 */
		public boolean isDirect() {
			return isDirect;
		}

		/**
		 * @param isDirect the isDirect to set
		 */
		public void setDirect(boolean isDirect) {
			this.isDirect = isDirect;
		}

		public GUID getContactId() {
			return contactId;
		}

		public void setContactId(GUID contactId) {
			this.contactId = contactId;
		}

		public GUID getId(){
        	return id;
        }

		public void setId(GUID id){
        	this.id = id;
        }

		public double getPrice(){
        	return price;
        }

		public void setPrice(double price){
        	this.price = price;
        }

		public GUID getSupplierId(){
        	return supplierId;
        }

		public void setSupplierId(GUID supplierId){
        	this.supplierId = supplierId;
        }

		public String getPurchaseCause(){
        	return purchaseCause;
        }

		public void setPurchaseCause(String purchaseCause){
        	this.purchaseCause = purchaseCause;
        }
	}
}
