package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>���������벿���й�ϵ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class SaveNoticeDeptTask extends SimpleTask{

	/**�⻧GUID*/
	private GUID tenantsGUID;

	/**����GUID*/
	private GUID noticeGUID;

	/**����GUID*/
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
