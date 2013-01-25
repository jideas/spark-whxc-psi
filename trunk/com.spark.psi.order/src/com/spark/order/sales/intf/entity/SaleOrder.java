package com.spark.order.sales.intf.entity;

import java.util.List;

/**
 * <p>���۶���+��ϸ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-19
 */
public class SaleOrder{
	private SaleOrderInfo info;
	private List<SaleOrderItem> dets;
	
	public SaleOrder() {
		//�޲ι���
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
