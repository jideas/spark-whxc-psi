package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.notice.entity.NoticeItem;

/**
 * <p>�����б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class NoticeItemImpl implements NoticeItem{

	/**GUID*/
	private GUID RECID;

	/**������Χ*/
	private String deptNameStr;

	/**������GUID*/
	private GUID createGuid;

	/**������*/
	private String createPerson;

	/**�������*/
	private String noticeTitle;

	/**����ʱ��*/
	private long publishTime;

	/**����ʱ��*/
	private long cancelTime;

	/**�Ƿ��ö�*/
	private boolean isTop;

	public GUID getRECID(){
		return RECID;
	}

	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	public String getDeptNameStr(){
		return deptNameStr;
	}

	public void setDeptNameStr(String deptNameStr){
		this.deptNameStr = deptNameStr;
	}

	public GUID getCreateGuid(){
		return createGuid;
	}

	public void setCreateGuid(GUID createGuid){
		this.createGuid = createGuid;
	}

	public String getCreatePerson(){
		return createPerson;
	}

	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}

	public String getNoticeTitle(){
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle){
		this.noticeTitle = noticeTitle;
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

}
