package com.spark.order.util.purchase;

import java.util.List;

import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;

/**
 * <p>生成采购订单接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-23
 */
public interface ICreatePurchase {
	/**
	 * 生成采购订单（成功返回true）
	 * @param info
	 * @param dets
	 * @return boolean
	 */
	boolean create(PurchaseOrderInfo info, List<PurchaseOrderItem> dets) throws Throwable;
	/**
	 * 获得直供时抛出的本次未生成的直供商品(非直供或者无时返回null)
	 * @return List<PurchaseOrderItem>
	 */
	List<PurchaseOrderItem> getDirected();
	/**
	 * 获得已经直供商品序列
	 * @return List<Integer>
	 */
	List<Integer> getDirectedIndexs();
}
