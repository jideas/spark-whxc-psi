package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 短信通道配置信息
 * @author Administrator
 *
 */
public interface MessageChannelConfigItem {

	/**
	 * 获取RECID
	 */
	public GUID getId();
	
	/**
	 * 获取账号
	 * @return
	 */
	public String getAccount();
	
	/**
	 * 获取密码
	 * @return
	 */
	public String getPassword();
	
	/**
	 * 获取手机单条短信最大字数
	 * @return
	 */
	public int getMobilePhoneMax();
	
	/**
	 * 获取小灵能单条短信最大字数
	 * @return
	 */
	public int getPHSMax();
	
	/**
	 * 获取连接类型
	 * @return
	 */
	public String getConnectType();
	
	/**
	 * 获取服务商
	 * @return
	 */
	public String getServiceType();
	
	/**
	 * 获取ip
	 * @return
	 */
	public String getIp();
	
	/**
	 * 获取普通端口
	 * @return
	 */
	public String getPort();
	
	/**
	 * 获取长短信端口
	 * @return
	 */
	public String getLongPort();
	
	/**
	 * 获取群发最大数量
	 * @return
	 */
	public int getMassSMSMax();
	
	/**
	 * 获取多发最大数量
	 * @return
	 */
	public int getMultipleSMSMax();
	
	/**
	 * 获取优先级顺序
	 * @return
	 */
	public int getPriority();
	
	/**
	 * 获取所属通道Id
	 * @return
	 */
	public GUID getMsgchannel();
	
}