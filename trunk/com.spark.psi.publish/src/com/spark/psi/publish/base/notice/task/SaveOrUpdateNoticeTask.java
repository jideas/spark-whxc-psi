package com.spark.psi.publish.base.notice.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>新增或编辑公告</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class SaveOrUpdateNoticeTask extends Task<SaveOrUpdateNoticeTask.Method>{

	public enum Method{
		ADD,
		UPDATE
	}

	/**GUID*/
	private GUID RECID;

	/**公告标题*/
	private String noticeTitle;

	/**发布范围*/
	private String deptNameStr;

	/**发布时间*/
	private long publishTime;

	/**撤消时间*/
	private long cancelTime;

	/**是否置顶*/
	private boolean isTop;

	/**公告内容*/
	private String noticeContent;

	/**部门列表*/
	private List<GUID> deptGuidList;

	/**创建时间*/
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
