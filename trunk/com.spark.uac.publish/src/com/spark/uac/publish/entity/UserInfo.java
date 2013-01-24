package com.spark.uac.publish.entity;

import java.net.URL;

import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.UserStatus;

/**
 * 
 * <p>登录用户接口</p>
 *


 *
 
 * @version 2012-4-10
 */
public interface UserInfo {
	
	/**
	 * 用户id
	 * 
	 * @return GUID
	 */
	public GUID getUserId();

	/**
	 * 租户id
	 * 
	 * @return GUID
	 */
	public GUID getTenantId();
	
	/**
	 * 租户所在服务器的地址
	 * 
	 * @return URL
	 */
	public URL getUrl();
	
	/**
	 * 获得用户可以登录的产品系列
	 * 
	 * @return HostInfo[]
	 */
	public HostInfo[] getProductSerialsHosts();
	
	/**
	 * 租户名称
	 * （运营系统一致）
	 * @return String
	 */
	public String getTenantName();
	/**
	 * 鱼儿多号
	 * 
	 * @return String
	 */
	public String getTenantCode();

	/**
	 * 手机号码
	 * 
	 * @return String
	 */
	public String getMobileNo();
	
	/**
	 * 密码
	 * 
	 * @return String
	 */
	public GUID getPassword();
	
	/**
	 * 是否有效
	 * 
	 * @return boolean
	 */
	public boolean isEnabled();
	
	/**
	 * 用户状态
	 * 
	 * @return UserStatus
	 */
	public UserStatus getStatus();
	
}
