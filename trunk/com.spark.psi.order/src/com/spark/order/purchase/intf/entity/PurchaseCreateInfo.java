package com.spark.order.purchase.intf.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TypeEnum;


/**
 * <p>采购清单实体</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-11
 */
@Deprecated
public class PurchaseCreateInfo{
	private double provPrice;//上次采购单价
	public GUID recid;
	private TypeEnum type;//直供商品标志。直供：TypeEnum.DIRECT
	private PurchaseOrderItem det;
	private PurchaseOrderInfo info;
//	public CusProviderEntity cusp;
	public double oldPrice = -1;
	/**
	 * 采购清单（非直供）
	 */
//	public BuyGoodsInfo dataInfo = null; //数据库存储的info信息
	/**
	 * 库存数据
	 */
//	public FStorageEntity storage; // 库存信息
//	/**
//	 * 采购清单(直供)
//	 */
//	public DirectStorageEntity directInfo = null; //直供商品数据库存储的info信息
	
	/**
	 * 直供商品标志。直供：TypeEnum.DIRECT
	 * @param type void
	 */
	public void setType(TypeEnum type) {
		this.type = type;
	}
	/**
	 * 直供商品标志。直供：TypeEnum.DIRECT
	 * @return TypeEnum
	 */
	public TypeEnum getType() {
		return type;
	}
	public double getProvPrice() {
		return provPrice;
	}
	public void setProvPrice(double provPrice) {
		this.provPrice = provPrice;
	}
	/**
	 * 商品或采购订单明细
	 * @return BuyOrdDet
	 */
	public PurchaseOrderItem getDet() {
		return det;
	}
	/**
	 * 商品或采购订单明细
	 * @param det void
	 */
	public void setDet(PurchaseOrderItem det) {
		this.det = det;
	}
	
	/**
	 * 采购订单信息
	 * @return BuyOrdInfo
	 */
	public PurchaseOrderInfo getInfo() {
		return info;
	}
	/**
	 * 采购订单信息
	 * @param info void
	 */
	public void setInfo(PurchaseOrderInfo info) {
		this.info = info;
	}
}
