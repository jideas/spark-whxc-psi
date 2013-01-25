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
 * 采购需求Task
 * 修改采购需求
 
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
	 * 采购商品清单
	 
	 *
	 */
	public static final class PurchaseGoods {
		
		private GUID id;
		
//		private GUID goodsItemId;//	商品条目id
				
		private double price = -1;//	单价	NUM(17,2)
		
//		private double count;//	数量	NUM(10,2)
		
		private GUID supplierId; //供应商id
		private GUID contactId;//联系人Id
		
//		private double recentPrice; //上次采购单价
		
//		private GUID storeId; //仓库ID

		private String purchaseCause;
		private boolean isDirect;//是否直供需求
		
		
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
