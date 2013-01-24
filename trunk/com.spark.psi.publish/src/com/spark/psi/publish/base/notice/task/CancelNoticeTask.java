package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>撤消公告(手动撤消公告为撤消状态，自动撤消公告为过期状态)</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class CancelNoticeTask extends SimpleTask{

	/**GUID*/
	private GUID RECID;

	/**公告状态(撤消或过期)*/
	private String noticestatus;

	public GUID getRECID(){
		return RECID;
	}

	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	public String getNoticeStatus(){
		return noticestatus;
	}

	public void setNoticeStatus(String noticestatus){
		this.noticestatus = noticestatus;
	}

}
