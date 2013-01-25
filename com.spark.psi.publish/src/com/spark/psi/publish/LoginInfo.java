package com.spark.psi.publish;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 用户登录信息
 * 
 */
public interface LoginInfo {
	
	public GUID getTenantId();

	public EmployeeInfo getEmployeeInfo();

	public boolean hasAuth(Auth... auths);

}
