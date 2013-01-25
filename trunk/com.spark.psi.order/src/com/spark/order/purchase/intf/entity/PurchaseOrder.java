package com.spark.order.purchase.intf.entity;

import java.util.List;

/**
 * <p>采购订单信息+明细</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-14
 */
public class PurchaseOrder{
	private PurchaseOrderInfo info;
	private List<? extends PurchaseOrderItem> dets;
	
	public PurchaseOrder() {
		//无参构造
	}
	public PurchaseOrder(PurchaseOrderInfo info, List<? extends PurchaseOrderItem> dets) {
		this.info = info;
		this.dets = dets;
	}
	/**
	 * @return the info
	 */
	public PurchaseOrderInfo getInfo() {
		return info;
	}
	/**
	 * @return the dets
	 */
	@SuppressWarnings("unchecked")
	public List<PurchaseOrderItem> getDets() {
		return (List<PurchaseOrderItem>) dets;
	}
//	
//	/**
//	 * @return the dets
//	 */
//	@SuppressWarnings("unchecked")
//	public List<PurchaseOrderItem> getDetss(){
//		return (List<PurchaseOrderItem>) dets;
//	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(PurchaseOrderInfo info) {
		this.info = info;
	}
	/**
	 * @param dets the dets to set
	 */
	public void setDets(List<PurchaseOrderItem> dets) {
		this.dets = dets;
	}
	
}
