package com.spark.psi.publish.base.bom.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;

/**
 * Bom��ϸ������Ϣ <BR>
 * ��ѯ˵����<br>
 * (1)����BomID��ѯ������BomInfo<br>
 */
public interface BomInfo {

	public GUID getId();
	public String getRemark();
	public String getBomNo();// bom���� nvarchar 30

	public GUID getGoodsItemId();// ��Ʒ��Ŀid guid

	public long getCreateDate();// �������� date 

	public GUID getCreatorId();// ������Guid guid

	public String getCreator();// ������ nvarchar 30

	public GUID getApprovePerson();// ����� guid

	public String getApprovePersonName();// ��������� nvarchar 30

	public long getApproveDate();// ������� date

	public BOM_STATUS getStatus();// ״̬ char 2
	
	public List<BomInfoItem> getBomInfoItems();

}
