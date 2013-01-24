package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 短信通道与配置详情<br>
 * 查询方法：通过短信通道ID查询MessageChannelInfo对象
 * 
 */
public interface MessageChannelInfo {

	/**
	 * 通道RECID
	 * @return
	 */
	public GUID getRECID();

	/**
	 * 通道名称
	 * @return
	 */
	public String getName();

	/**
	 * 通道编号
	 * @return
	 */
	public String getCode();

	/**
	 * 计价类型
	 * @return
	 */
	public String getChargeType();

	/**
	 * 单价
	 * @return
	 */
	public double getPrice();

	/**
	 * 是否支持批量发送
	 * @return
	 */
	public String getIsBatchSend();

	/**
	 * 免费短信条数
	 * @return
	 */
	public int getFreeSMSNumber();

	/**
	 * 超出部分计价方式
	 * @return
	 */
	public double getOvertakePrice();

	/**
	 * 描述
	 * @return
	 */
	public String getRemark();

	/**
	 * 支持网络
	 * @return
	 */
	public String getSupportNetWork();
	
	/**
	 * 获取通道参数配置列表
	 */
	public MessageChannelConfigItem[] getMessageChannelConfigItems();

}