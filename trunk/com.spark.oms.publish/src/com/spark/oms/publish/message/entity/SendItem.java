package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 发送短信　明细接口
 * 
 *
 */
public interface SendItem {
	
	/**
	 * 发送短信记录流水表Id
	 * @return
	 */
	public GUID getRECID();

	/**
	 * 服务套餐Id
	 * 如果能保证服务套餐在一个收件箱记录中是唯一的，不存在一个发件跨两个服务套餐，可移到短信发送主接口中去
	 */
	public GUID getServiceRECID();
	
	/**
	 * 服务套餐名称
	 */
	public String getServiceName();

	/**
	 * 发送号码
	 */
	public String getMobile();
	
	/**
	 * 发送内容
	 */
	public String getContent();
	
	/**
	 * 金额
	 */
	public double getPrice();
	
	/**
	 * 状态：0失败、１成功
	 */
	public String getStatus();
	
	/**
	 * 免费：0免费、1收费
	 * @return
	 */
	public String IsFree();
}
