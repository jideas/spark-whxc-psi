package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.store.entity.StoreInfo;

/**
 * 
 * <p>存储临时采购订单的结构</p>
 *  按供应商和仓库分组
 *


 *
 
 * @version 2012-3-12
 */
public interface TempPurchaseOrderInfoListEntity{
	
	/**
	 * 获得当前操作中所有的供应商列表
	 * 
	 * @return SupplierInfo[]
	 */
	public SupplierInfo[] getSupplierInfos();
	/**
	 * 获得当前操作中指定供应商采购的商品对应的仓库列表
	 * 
	 * @param supplierInfo
	 * @return StoreInfo[]
	 */
	public StoreInfo[] geStoreInfos(GUID supplierInfo);
	
	/**
	 * 通过供应商和仓库对象定位到具体的订单列表
	 * 
	 * @param supplierInfo
	 * @param storeInfo
	 * @return TempPurchaseOrderInfo[]
	 */
	public TempPurchaseOrderInfo[] getTempPurchaseOrderInfos(GUID supplierInfo,GUID storeInfo);

	public interface TempPurchaseOrderInfo{

		public SupplierInfo getSupplierInfo();

		public StoreInfo getStoreInfo();

		public TempPurchaseGoodsItem[] getPurchaseGoodsItems();
	 }

	public static final class TempPurchaseGoodsItem{

			private GUID goodsId; //商品条目id

			private String goodsName; //商品名称

			private double price = -1; //单价	NUM(17,2)

			private double num;//	数量	NUM(10,2)

			public GUID getGoodsId(){
				return goodsId;
			}

			public void setGoodsId(GUID goodsId){
				this.goodsId = goodsId;
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

			public double getNum(){
				return num;
			}

			public void setNum(double num){
				this.num = num;
			}

	}

	
}
