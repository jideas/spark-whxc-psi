package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;


/**
 * 通过待采购商品清单生成采购清单明细
 
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
		
		private GUID goodsItemId;//	商品条目id
		
		private String goodsName;  //商品名称
		
		private double price = -1;//	单价	NUM(17,2)
		
		private double count;//	数量	NUM(10,2)
		
		private GUID supplierId; //供应商id
		
		private String supplierName; //供应商名称
		
		private double recentPrice; //上次采购单价
		
		private GUID storeId; //仓库ID

		private String purchaseCause;  //采购原因

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
