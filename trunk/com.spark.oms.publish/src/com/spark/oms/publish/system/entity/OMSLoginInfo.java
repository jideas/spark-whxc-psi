package com.spark.oms.publish.system.entity;

import com.spark.oms.publish.Auth;

/**
 * 用户登录信息
 * 
 */
public interface OMSLoginInfo {

	public OMSUserInfo getUserInfo();

	public boolean hasAuth(Auth... auths);

}
