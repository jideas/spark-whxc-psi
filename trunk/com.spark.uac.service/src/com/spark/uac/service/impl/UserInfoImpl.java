package com.spark.uac.service.impl;

import java.net.URL;

import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.UserStatus;
import com.spark.uac.publish.entity.HostInfo;
import com.spark.uac.publish.entity.UserInfo;

public class UserInfoImpl implements UserInfo {

	/**
	 * 用户ID
	 */
	private GUID userId;

	/**
	 * 租户ID
	 */
	private GUID tenantId;
	
	/**
	 * 租户所在服务器的地址
	 * 
	 */
	private URL url;
	
	private HostInfo[] productSerialsHosts;

	/**
	 * 手机号码
	 */
	private String mobileNumber;


	/**
	 * 密码
	 */
	private GUID password;

	/**
	 * 状态
	 */
	private boolean enabled;
	
	private String status;
	
	private String tenantCode;

	private String tenantName;
	
	/**
	 * @return the userId
	 */
	public GUID getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(GUID userId) {
		this.userId = userId;
	}

	/**
	 * @return the tenantId
	 */
	public GUID getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setTenantId(GUID tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNo() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNo(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserStatus getStatus(){
    	return UserStatus.getUserStatusByCode(status);
    }

	public void setStatus(UserStatus status){
    	this.status = status.getCode();
    }

	public String getTenantCode(){
    	return tenantCode;
    }

	public void setTenantCode(String tenantCode){
    	this.tenantCode = tenantCode;
    }

	
	
	public GUID getPassword() {
		return password;
	}

	public void setPassword(GUID password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getTenantName(){
    	return tenantName;
    }

	public void setTenantName(String tenantName){
    	this.tenantName = tenantName;
    }
//
//	public boolean checkPwd(String pwd){
//		if(pwd==null)return false;
//		return pwd.equals(this.getPassword());
//	}

	public URL getUrl(){
		if(productSerialsHosts.length==0)return null;
    	return productSerialsHosts[0].getURL();
    }

	public void setUrl(URL url){
    	this.url = url;
    }

	public HostInfo[] getProductSerialsHosts(){
    	return productSerialsHosts;
    }

	public void setProductSerialsHosts(HostInfo[] productSerialsHosts){
    	this.productSerialsHosts = productSerialsHosts;
    }

}
