/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;


/**
 * <p>创建送货上门的零售单</p>
 *


 *
 
 * @version 2012-3-6
 */

public class CreateRetailDeliveryOrderTask extends CreateRetailOrderTask{
	
	/**
	 * 收货人
	 */
	private String consignee;
	
	/**
	 * 收获地址
	 */
	private String deliveryAddress;
	
	/**
	 * 电话号码
	 */
	private String mobileNumber;
	
	/**
	 * 交货时间
	 */
	private long deliveryTime;

	
	public String getConsignee(){
    	return consignee;
    }

	public void setConsignee(String consignee){
    	this.consignee = consignee;
    }

	public String getDeliveryAddress(){
    	return deliveryAddress;
    }

	public void setDeliveryAddress(String deliveryAddress){
    	this.deliveryAddress = deliveryAddress;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }

	public void setMobileNo(String mobileNumber){
    	this.mobileNumber = mobileNumber;
    }

	public long getDeliveryTime(){
    	return deliveryTime;
    }

	public void setDeliveryTime(long deliveryTime){
    	this.deliveryTime = deliveryTime;
    }
	
	
	
}
