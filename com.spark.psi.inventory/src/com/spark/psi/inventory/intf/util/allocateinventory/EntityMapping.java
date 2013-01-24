/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.allocate.service.common
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-25       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.util.allocateinventory;

import com.jiuqi.dna.core.da.RecordSet;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.internal.entity.AllocateInventoryItem;

/**
 * @author zhongxin
 *
 */
public class EntityMapping {
	public static AllocateInventory mappingAllocateInfo(RecordSet rs) {
		AllocateInventory allocateInfo = new AllocateInventory();
		allocateInfo.setAllocSheetNo(rs.getFields().get(0).getString());
		allocateInfo.setAllocateInDate(rs.getFields().get(1).getDate());
		allocateInfo.setAllocateInPerson(rs.getFields().get(2).getGUID());
		allocateInfo.setAllocateInPersonName(rs.getFields().get(3).getString());
		allocateInfo.setAllocateInStoreId(rs.getFields().get(4).getGUID());
		allocateInfo.setAllocateInStoreName(rs.getFields().get(5).getString());
		allocateInfo.setAllocateOutDate(rs.getFields().get(6).getDate());
		allocateInfo.setAllocateOutPerson(rs.getFields().get(7).getGUID());
		allocateInfo.setAllocateOutPersonName(rs.getFields().get(8).getString());
		allocateInfo.setAllocateOutStoreId(rs.getFields().get(9).getGUID());
		allocateInfo.setAllocateOutStoreName(rs.getFields().get(10).getString());
		allocateInfo.setAllocateReason(rs.getFields().get(11).getString());
		allocateInfo.setApplyDate(rs.getFields().get(12).getDate());
		allocateInfo.setApproveDate(rs.getFields().get(13).getDate());
		allocateInfo.setApprovePerson((rs.getFields().get(14).getString()));
		allocateInfo.setApprovePersonId((rs.getFields().get(15).getGUID()));
		allocateInfo.setCreateDate(rs.getFields().get(16).getDate());
		allocateInfo.setCreator(rs.getFields().get(17).getString());
		allocateInfo.setCreatorId(rs.getFields().get(18).getGUID());
		allocateInfo.setCreatorPY(rs.getFields().get(19).getString());
		allocateInfo.setDeptId(rs.getFields().get(20).getGUID());
		allocateInfo.setRejectReason(rs.getFields().get(21).getString());
		allocateInfo.setId(rs.getFields().get(22).getGUID());
		allocateInfo.setStatus(rs.getFields().get(23).getString());
		
		return allocateInfo;
	}
	
	public static AllocateInventoryItem mappingAllocateDetail(RecordSet rs) {
		AllocateInventoryItem allocateDetail = new AllocateInventoryItem();
		allocateDetail.setAbleCount(rs.getFields().get(0).getDouble());
		allocateDetail.setAllocateCount(rs.getFields().get(1).getDouble());
		allocateDetail.setAllocateId(rs.getFields().get(2).getGUID());
		allocateDetail.setCreateDate(rs.getFields().get(3).getDate());
		allocateDetail.setCreatorId(rs.getFields().get(4).getGUID());
		allocateDetail.setStockCode(rs.getFields().get(5).getString());
		allocateDetail.setStockId(rs.getFields().get(6).getGUID());
		allocateDetail.setStockName(rs.getFields().get(7).getString());
		allocateDetail.setStockNo(rs.getFields().get(8).getString());
		allocateDetail.setStockSpec(rs.getFields().get(9).getString());
		allocateDetail.setStockScale(rs.getFields().get(10).getInt());
		allocateDetail.setId(rs.getFields().get(11).getGUID());
		allocateDetail.setUnit(rs.getFields().get(12).getString());
		return allocateDetail;
	}
}
