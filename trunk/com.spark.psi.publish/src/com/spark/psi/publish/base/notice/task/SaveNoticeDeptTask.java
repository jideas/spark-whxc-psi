package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>新增公告与部门中关系</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class SaveNoticeDeptTask extends SimpleTask{

	/**租户GUID*/
	private GUID tenantsGUID;

	/**公告GUID*/
	private GUID noticeGUID;

	/**部门GUID*/
	private GUID deptGUID;

	public GUID getTenantsGUID(){
		return tenantsGUID;
	}

	public void setTenantsGUID(GUID tenantsGUID){
		this.tenantsGUID = tenantsGUID;
	}

	public GUID getNoticeGUID(){
		return noticeGUID;
	}

	public void setNoticeGUID(GUID noticeGUID){
		this.noticeGUID = noticeGUID;
	}

	public GUID getDeptGUID(){
		return deptGUID;
	}

	public void setDeptGUID(GUID deptGUID){
		this.deptGUID = deptGUID;
	}

}
