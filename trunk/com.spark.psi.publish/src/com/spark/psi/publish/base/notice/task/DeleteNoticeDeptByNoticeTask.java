package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>���ݹ���ɾ�������빫���ϵ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class DeleteNoticeDeptByNoticeTask extends SimpleTask{

	/**����GUID*/
	private GUID noticeGUID;

	public GUID getNoticeGUID(){
		return noticeGUID;
	}

	public void setNoticeGUID(GUID noticeGUID){
		this.noticeGUID = noticeGUID;
	}
}
