package com.spark.order.purchase.intf.entity;

//import com.spark.bus.store.storage.intf.facade.FStorageEntity;

/**
 * <p>
 * �ɹ�Ԥ����Ʒ
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-11-22
 */
public class PurchaseWarningGoodsInfo {
	private double buying;// �ɹ�������
	private double buyAdvise;// �ɹ�����BUY_ADVISE
	private double floor;// �������
	private double upper;// �������
	private Double buyNum;// ���βɹ�����

	/**
	 * 
	 * @return Double
	 */
	public Double getBuyNum() {
		return buyNum;
	}

	/**
	 * 
	 * @param buyNum void
	 */
	public void setBuyNum(Double buyNum) {
		this.buyNum = buyNum;
	}

	/**
	 * @return the floor
	 */
	public double getFloor() {
		return floor;
	}

	/**
	 * @return the upper
	 */
	public double getUpper() {
		return upper;
	}

	/**
	 * @param floor
	 *            the floor to set
	 */
	public void setFloor(double floor) {
		this.floor = floor;
	}

	/**
	 * @param upper
	 *            the upper to set
	 */
	public void setUpper(double upper) {
		this.upper = upper;
	}

	/**
	 * @return the buying
	 */
	public double getBuying() {
		return buying;
	}

	/**
	 * @param buying
	 *            the buying to set
	 */
	public void setBuying(double buying) {
		this.buying = buying;
	}

	/**
	 * @return the buyAdvise
	 */
	public double getBuyAdvise() {
		return buyAdvise;
	}

	/**
	 * @param buyAdvise
	 *            the buyAdvise to set
	 */
	public void setBuyAdvise(double buyAdvise) {
		this.buyAdvise = buyAdvise;
	}
}
