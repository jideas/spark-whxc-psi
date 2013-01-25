package com.spark.psi.publish.base.bom.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;

/**
 * Bom详细数据信息 <BR>
 * 查询说明：<br>
 * (1)根据BomID查询，返回BomInfo<br>
 */
public interface BomInfo {

	public GUID getId();
	public String getRemark();
	public String getBomNo();// bom单号 nvarchar 30

	public GUID getGoodsItemId();// 商品条目id guid

	public long getCreateDate();// 定制日期 date 

	public GUID getCreatorId();// 创建人Guid guid

	public String getCreator();// 创建人 nvarchar 30

	public GUID getApprovePerson();// 审核人 guid

	public String getApprovePersonName();// 审核人姓名 nvarchar 30

	public long getApproveDate();// 审核日期 date

	public BOM_STATUS getStatus();// 状态 char 2
	
	public List<BomInfoItem> getBomInfoItems();

}
