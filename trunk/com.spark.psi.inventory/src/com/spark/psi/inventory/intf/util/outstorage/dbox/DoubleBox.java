/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage.dbox;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
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
