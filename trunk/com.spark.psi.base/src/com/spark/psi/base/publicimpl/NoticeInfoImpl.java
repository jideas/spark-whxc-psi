package com.spark.psi.base.publicimpl;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.notice.entity.NoticeInfo;

/**
 * <p>公告详情</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class NoticeInfoImpl implements NoticeInfo{

	/**GUID*/
	private GUID RECID;

	/**租户GUID*/
	private GUID tenantsGuid;

	/**部门GUID*/
	private GUID deptGuid;

	/**发布范围*/
	private String deptNameStr;

	/**创建人GUID*/
	private GUID createGuid;

	/**创建人*/
	private String createPerson;

	/**公告标题*/
	private String noticeTitle;

	/**公告标题拼音*/
	private String noticeTitlePY;

	/**创建时间*/
	private long createTime;

	/**发布时间*/
	private long publishTime;

	/**撤消时间*/
	private long cancelTime;

	/**是否置顶*/
	private boolean isTop;

	/**公告状态*/
	private String noticestatus;

	/**公告内容*/
	private String noticeContent;

	/**部门GUID列表*/
	private List<GUID> deptGuidList;

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

	public GUID getDeptGuid(){
		return deptGuid;
	}

	public void setDeptGuid(GUID deptGuid){
		this.deptGuid = deptGuid;
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

	public String getNoticeTitlePY(){
		return noticeTitlePY;
	}

	public void setNoticeTitlePY(String noticeTitlePY){
		this.noticeTitlePY = noticeTitlePY;
	}

	public long getCreateTime(){
		return createTime;
	}

	public void setCreateTime(long createTime){
		this.createTime = createTime;
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

	public String getNoticeStatus(){
		return noticestatus;
	}

	public void setNoticeStatus(String noticestatus){
		this.noticestatus = noticestatus;
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

}
