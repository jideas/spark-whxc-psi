package com.spark.psi.publish.base.notice.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>公告列表</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */
public interface NoticeItem{

	/**
	 * 获得ID
	 */
	public GUID getRECID();
	
	/**
	 * 创建人GUID
	 */
	public GUID getCreateGuid();
	
	/**
	 * 获得创建人
	 */
	public String getCreatePerson();

	/**
	 * 获得公告标题
	 */
	public String getNoticeTitle();

	/**
	 * 获得发布时间
	 */
	public long getPublishTime();

	/**
	 * 获得撤消时间
	 */
	public long getCancelTime();

	/**
	 * 获得发布范围
	 */
	public String getDeptNameStr();

	/**
	 * 获得是否置顶
	 */
	public boolean getIsTop();

}
