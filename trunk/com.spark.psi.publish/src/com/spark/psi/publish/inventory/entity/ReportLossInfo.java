package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ReportLossStatus;

public interface ReportLossInfo {
	/**
	 * 
	 * @return
	 */
	public GUID getId();
	/**
	 * 单据编号
	 * @return
	 */
	public String getSheetNo();
	/**
	 * 仓库ID
	 * @return
	 */
	public GUID getStoreId();
	/**
	 * 仓库名称
	 * @return
	 */
	public String getStoreName();
	/**
	 * 申请日期
	 * @return
	 */
	public long getApplyDate();
	/**
	 * 创建日期
	 * @return
	 */
	public long getCreateDate();
	/**
	 * 创建人（名称）
	 * @return
	 */
	public String getCreateor();
	
	/**
	 * 创建人ID
	 * @return
	 */
	public GUID getCreatorId();
	/**
	 * 审批人ID
	 * @return
	 */
	public GUID getApprovalPersonId();
	/**
	 * 审批人名称
	 * @return
	 */
	public String getApprovalPersonName();
	/**
	 * 审批日期
	 * @return
	 */
	public long getApprovalDate();
	
	/**
	 * 报损商品条目信息
	 * @return
	 */
	public ReportLossInfoItem[] getItems();
	/**
	 * 备注
	 * @return
	 */
	public String getRemark();
	
	/**
	 * 状态
	 * @return
	 */
	public ReportLossStatus getStatus(); 
	
	/**
	 * 退回原因
	 * @return
	 */
	public String getRejectReason();
}
