/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.util
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-17       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage.dbox;

/**
 * <p>TODO ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-17
 */

public class DoubleBox{
	public DoubleBox(){
	}

	public DoubleBox(double planAmount, double payAmount){
		this.payAmount = payAmount;
		this.planAmount = planAmount;
	}

	private double planAmount;

	private double payAmount;
	
	private boolean hasCount = false;
	/**
	 * @return the planAmount
	 */
	public double getPlanAmount(){
		return planAmount;
	}

	/**
     * @return the hasCount
     */
    public boolean isHasCount(){
    	return hasCount;
    }

	/**
     * @param hasCount the hasCount to set
     */
    public void setHasCount(boolean hasCount){
    	this.hasCount = hasCount;
    }

	/**
	 * @param planAmount the planAmount to set
	 */
	public void setPlanAmount(double planAmount){
		this.planAmount = planAmount;
	}

	/**
	 * @return the payAmount
	 */
	public double getPayAmount(){
		return payAmount;
	}

	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(double payAmount){
		this.payAmount = payAmount;
	}
}
