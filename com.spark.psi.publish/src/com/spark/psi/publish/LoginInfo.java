package com.spark.psi.publish;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * �û���¼��Ϣ
 * 
 */
public interface LoginInfo {
	
	public GUID getTenantId();

	public EmployeeInfo getEmployeeInfo();

	public boolean hasAuth(Auth... auths);

}
