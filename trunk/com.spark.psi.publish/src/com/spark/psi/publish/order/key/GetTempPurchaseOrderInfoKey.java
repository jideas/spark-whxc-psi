package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;


/**
 * ͨ�����ɹ���Ʒ�嵥���ɲɹ��嵥��ϸ
 
 *
 */
public class GetTempPurchaseOrderInfoKey {
	
	
	private PurchaseGoods[] purchaseGoods;
	
	
	
	public PurchaseGoods[] getPurchaseGoods(){
    	return purchaseGoods;
    }



	public void setPurchaseGoods(PurchaseGoods[] purchaseGoods){
    	this.purchaseGoods = purchaseGoods;
    }



	public static final class PurchaseGoods {
		
		private GUID goodsItemId;//	��Ʒ��Ŀid
		
		private String goodsName;  //��Ʒ����
		
		private double price = -1;//	����	NUM(17,2)
		
		private double count;//	����	NUM(10,2)
		
		private GUID supplierId; //��Ӧ��id
		
		private String supplierName; //��Ӧ������
		
		private double recentPrice; //�ϴβɹ�����
		
		private GUID storeId; //�ֿ�ID

		private String purchaseCause;  //�ɹ�ԭ��

		public GUID getGoodsItemId(){
        	return goodsItemId;
        }

		public void setGoodsItemId(GUID goodsItemId){
        	this.goodsItemId = goodsItemId;
        }

		public String getGoodsName(){
        	return goodsName;
        }

		public void setGoodsName(String goodsName){
        	this.goodsName = goodsName;
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

		public GUID getSupplierId(){
        	return supplierId;
        }

		public void setSupplierId(GUID supplierId){
        	this.supplierId = supplierId;
        }

		public String getSupplierName(){
        	return supplierName;
        }

		public void setSupplierName(String supplierName){
        	this.supplierName = supplierName;
        }

		public double getRecentPrice(){
        	return recentPrice;
        }

		public void setRecentPrice(double recentPrice){
        	this.recentPrice = recentPrice;
        }

		public GUID getStoreId(){
        	return storeId;
        }

		public void setStoreId(GUID storeId){
        	this.storeId = storeId;
        }

		public String getPurchaseCause(){
        	return purchaseCause;
        }

		public void setPurchaseCause(String purchaseCause){
        	this.purchaseCause = purchaseCause;
        }

		
		

	}

}
