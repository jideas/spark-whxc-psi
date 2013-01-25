package com.spark.order.sales.intf.entity;

import java.util.List;

/**
 * <p>销售订单+明细集合</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-19
 */
public class SaleOrder{
	private SaleOrderInfo info;
	private List<SaleOrderItem> dets;
	
	public SaleOrder() {
		//无参构造
	}
	/**
	 * @param info
	 * @param dets
	 */
	public SaleOrder(SaleOrderInfo info, List<SaleOrderItem> dets) {
		this.info = info;
		this.dets = dets;
	}
	/**
	 * @return the info
	 */
	public SaleOrderInfo getInfo() {
		return info;
	}
	/**
	 * @return the dets
	 */
	public List<SaleOrderItem> getDets() {
		return dets;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(SaleOrderInfo info) {
		this.info = info;
	}
	/**
	 * @param dets the dets to set
	 */
	public void setDets(List<SaleOrderItem> dets) {
		this.dets = dets;
	}
	
}
