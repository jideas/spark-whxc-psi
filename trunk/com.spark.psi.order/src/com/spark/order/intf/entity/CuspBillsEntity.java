package com.spark.order.intf.entity;

/**
 * <p>�ͻ�/��Ӧ�̣����굥��ҳǩ�����׼�¼����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-12-7
 */
public class CuspBillsEntity {
	private double orderTotalAmount;//����/�ɹ��ܶ�
	private double orderReceipt;//����/�ɹ��տ�
	private double cancelAmount;//����/�ɹ��˿�
	private int orderTime;//����/�ɹ�����
	private int cancelTime;//����/�ɹ��˻�����
	/**
	 * @return the orderTotalAmount
	 */
	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	/**
	 * @return the orderReceipt
	 */
	public double getOrderReceipt() {
		return orderReceipt;
	}
	/**
	 * @return the cancelAmount
	 */
	public double getCancelAmount() {
		return cancelAmount;
	}
	/**
	 * @return the orderTime
	 */
	public int getOrderTime() {
		return orderTime;
	}
	/**
	 * @return the cancelTime
	 */
	public int getCancelTime() {
		return cancelTime;
	}
	/**
	 * @param orderTotalAmount the orderTotalAmount to set
	 */
	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	/**
	 * @param orderReceipt the orderReceipt to set
	 */
	public void setOrderReceipt(double orderReceipt) {
		this.orderReceipt = orderReceipt;
	}
	/**
	 * @param cancelAmount the cancelAmount to set
	 */
	public void setCancelAmount(double cancelAmount) {
		this.cancelAmount = cancelAmount;
	}
	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(int orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * @param cancelTime the cancelTime to set
	 */
	public void setCancelTime(int cancelTime) {
		this.cancelTime = cancelTime;
	}
}
