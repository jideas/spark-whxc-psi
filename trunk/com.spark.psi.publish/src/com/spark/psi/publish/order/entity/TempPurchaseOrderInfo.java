package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.store.entity.StoreInfo;

/**
 * 
 * <p>待生成的临时采购订单</p>
 * 
 * 生成采购订单界面 List<TempPurchaseOrderInfo> + GetTempPurchaseOrderInfoKey
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 周利均
 * @version 2012-2-22
 */
public interface TempPurchaseOrderInfo{

//	private SupplierInfo supplierInfo; //供应商
//
//	private StoreInfo storeInfo; //仓库
//
//	private TempPurchaseGoodsItem[] purchaseGoodsItems;

	public SupplierInfo getSupplierInfo();

	public StoreInfo getStoreInfo();

	public TempPurchaseGoodsItem[] getPurchaseGoodsItems();

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
