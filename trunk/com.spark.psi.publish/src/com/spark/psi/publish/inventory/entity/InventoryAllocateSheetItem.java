package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;

/**
 * 库存调拨单列表项目<br>
 * 查询方法：ListEntity<InventoryAllocateSheetItem>+GetInventoryAllocateSheetListKey
 */
public interface InventoryAllocateSheetItem {

	/**
	 * 获取调拨单ID
	 * 
	 * @return
	 */
	public GUID getSheetId();

	/**
	 * 获取调拨单号码
	 * 
	 * @return
	 */
	public String getSheetNumber();
	
	/**
	 * 审批人
	 * 
	 * @return
	 */
	public String getApprovePerson();

	/**
	 * 获取调拨单状态
	 * 
	 * @return
	 */
	public InventoryAllocateStatus getStatus();

	/**
	 * 获取源仓库ID
	 * 
	 * @return
	 */
	public GUID getSourceStoreId();

	/**
	 * 获取源仓库名称
	 * 
	 * @return
	 */
	public String getSourceStoreName();

	/**
	 * 获取目的仓库ID
	 * 
	 * @return
	 */
	public GUID getDestinationStoreId();

	/**
	 * 获取目的仓库名称
	 * 
	 * @return
	 */
	public String getDestinationStoreName();

	/**
	 * 获取调入时间
	 * 
	 * @return
	 */
	public long getAllocateInDate();

	/**
	 * 获取调出时间
	 * 
	 * @return
	 */
	public long getAllocateOutDate();

	/**
	 * 获取创建时间
	 * 
	 * @return
	 */
	public long getCreateDate();

	/**
	 * 获取制单人ID
	 * 
	 * @return
	 */
	public GUID getCreatorId();

	/**
	 * 获取制单人名称
	 * 
	 * @return
	 */
	public String getCreatorName();
	/**
	 * 获取调入审批人名称
	 * 
	 * @return
	 */
	public String getInExamName();
	/**
	 * 获取调出审批人名称
	 * 
	 * @return
	 */
	public String getOutExamName();

}
