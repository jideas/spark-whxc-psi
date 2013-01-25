package com.spark.psi.publish.base.notice.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>公告详情</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */
public interface NoticeInfo {
	
	/**
	 * GUID
	 */
	public GUID getRECID();

	/**
	 * 租户GUID
	 */
	public GUID getTenantsGuid();

	/**
	 * 部门GUID
	 */
	public GUID getDeptGuid();

	/**
	 * 发布范围
	 */
	public String getDeptNameStr();

	/**
	 * 创建人GUID
	 */
	public GUID getCreateGuid();

	/**
	 * 创建人
	 */
	public String getCreatePerson();

	/**
	 * 公告标题
	 */
	public String getNoticeTitle();

	/**
	 * 公告标题拼音
	 */
	public String getNoticeTitlePY();

	/**
	 * 创建时间
	 */
	public long getCreateTime();

	/**
	 * 发布时间
	 */
	public long getPublishTime();

	/**
	 * 撤消时间
	 */
	public long getCancelTime();

	/**
	 * 是否置顶
	 */
	public boolean getIsTop();

	/**
	 * 公告状态
	 */
	public String getNoticeStatus();

	/**
	 * 公告内容
	 */
	public String getNoticeContent();
	
	/**
	 * 部门GUID列表
	 */
	public List<GUID> getDeptGuidList();
}
