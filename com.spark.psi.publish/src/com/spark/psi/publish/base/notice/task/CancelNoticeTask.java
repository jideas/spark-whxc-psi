package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��������(�ֶ���������Ϊ����״̬���Զ���������Ϊ����״̬)</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class CancelNoticeTask extends SimpleTask{

	/**GUID*/
	private GUID RECID;

	/**����״̬(���������)*/
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
