package com.spark.oms.publish.system.entity;

import com.spark.oms.publish.Auth;

/**
 * �û���¼��Ϣ
 * 
 */
public interface OMSLoginInfo {

	public OMSUserInfo getUserInfo();

	public boolean hasAuth(Auth... auths);

}
