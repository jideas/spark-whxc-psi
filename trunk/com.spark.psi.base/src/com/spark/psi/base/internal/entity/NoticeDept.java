package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>���沿���м���洢���淢����Χ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class NoticeDept{
	/**GUID*/
	private GUID RECID;

	/**�⻧GUID*/
	private GUID tenantsGuid;

	/**����GUID*/
	private GUID noticeGuid;

	/**����GUID*/
	private GUID deptGuid;

	/**��������*/
	private String deptName;

	public GUID getRECID(){
		return RECID;
	}

	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	public GUID getTenantsGuid(){
		return tenantsGuid;
	}

	public void setTenantsGuid(GUID tenantsGuid){
		this.tenantsGuid = tenantsGuid;
	}

	public GUID getNoticeGuid(){
		return noticeGuid;
	}

	public void setNoticeGuid(GUID noticeGuid){
		this.noticeGuid = noticeGuid;
	}

	public GUID getDeptGuid(){
		return deptGuid;
	}

	public void setDeptGuid(GUID deptGuid){
		this.deptGuid = deptGuid;
	}

	public String getDeptName(){
		return deptName;
	}

	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

}
