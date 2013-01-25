package com.spark.psi.publish.base.notice.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>������༭����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class SaveOrUpdateNoticeTask extends Task<SaveOrUpdateNoticeTask.Method>{

	public enum Method{
		ADD,
		UPDATE
	}

	/**GUID*/
	private GUID RECID;

	/**�������*/
	private String noticeTitle;

	/**������Χ*/
	private String deptNameStr;

	/**����ʱ��*/
	private long publishTime;

	/**����ʱ��*/
	private long cancelTime;

	/**�Ƿ��ö�*/
	private boolean isTop;

	/**��������*/
	private String noticeContent;

	/**�����б�*/
	private List<GUID> deptGuidList;

	/**����ʱ��*/
	private long createDate;

	public GUID getRECID(){
		return RECID;
	}

	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	public String getNoticeTitle(){
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle){
		this.noticeTitle = noticeTitle;
	}

	public String getDeptNameStr(){
		return deptNameStr;
	}

	public void setDeptNameStr(String deptNameStr){
		this.deptNameStr = deptNameStr;
	}

	public long getPublishTime(){
		return publishTime;
	}

	public void setPublishTime(long publishTime){
		this.publishTime = publishTime;
	}

	public long getCancelTime(){
		return cancelTime;
	}

	public void setCancelTime(long cancelTime){
		this.cancelTime = cancelTime;
	}

	public boolean getIsTop(){
		return isTop;
	}

	public void setIsTop(boolean isTop){
		this.isTop = isTop;
	}

	public String getNoticeContent(){
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent){
		this.noticeContent = noticeContent;
	}

	public List<GUID> getDeptGuidList(){
		return deptGuidList;
	}

	public void setDeptGuidList(List<GUID> deptGuidList){
		this.deptGuidList = deptGuidList;
	}

	public long getCreateDate(){
		return createDate;
	}

	public void setCreateDate(long createDate){
		this.createDate = createDate;
	}

}
