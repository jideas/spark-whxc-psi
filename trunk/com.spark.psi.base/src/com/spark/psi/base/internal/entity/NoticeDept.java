package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>公告部门中间表，存储公告发布范围</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class NoticeDept{
	/**GUID*/
	private GUID RECID;

	/**租户GUID*/
	private GUID tenantsGuid;

	/**公告GUID*/
	private GUID noticeGuid;

	/**部门GUID*/
	private GUID deptGuid;

	/**部门名称*/
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
