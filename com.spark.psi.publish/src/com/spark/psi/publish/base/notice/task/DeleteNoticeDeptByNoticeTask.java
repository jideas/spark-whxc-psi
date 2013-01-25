package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>根据公告删除部门与公告关系</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class DeleteNoticeDeptByNoticeTask extends SimpleTask{

	/**公告GUID*/
	private GUID noticeGUID;

	public GUID getNoticeGUID(){
		return noticeGUID;
	}

	public void setNoticeGUID(GUID noticeGUID){
		this.noticeGUID = noticeGUID;
	}
}
