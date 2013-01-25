package com.spark.order.purchase.intf.entity;

import java.util.List;

/**
 * <p>�ɹ�������Ϣ+��ϸ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-14
 */
public class PurchaseOrder{
	private PurchaseOrderInfo info;
	private List<? extends PurchaseOrderItem> dets;
	
	public PurchaseOrder() {
		//�޲ι���
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
