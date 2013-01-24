package com.spark.psi.base.key.organization;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * ��ѯָ�����ŵ���������ڵ�
 
 *
 */
public class GetDescendantDepartmentListKey {
	
	private GUID deptId;
	
	private Auth[] auths;
	
	public GetDescendantDepartmentListKey(GUID deptId,Auth... auths){
		this.deptId = deptId;
		this.auths = auths;
	}

	public GUID getDeptId() {
		return deptId;
	}

	public Auth[] getAuths() {
		return auths;
	}

	
}
