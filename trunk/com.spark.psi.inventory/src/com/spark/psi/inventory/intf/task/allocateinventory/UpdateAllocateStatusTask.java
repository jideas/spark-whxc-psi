/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.allocate.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-29       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.allocateinventory;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.AllocateItemDet;
import com.spark.psi.publish.InventoryAllocateStatus;
/**
 * 
 * <p>修改调拨状态</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class UpdateAllocateStatusTask extends Task<UpdateAllocateStatusTask.Method>{
	private GUID allocateGuid;
	private long examineDate;
	private InventoryAllocateStatus prestatus;
	private InventoryAllocateStatus upTostatus;
	
	private String rejectReason;
	
	private int updateResult = 0;
	
	public enum Method {
		SUBMIT,
		EXMAINE,
		CONFIRM,
		REJECT,
	}
	public UpdateAllocateStatusTask( GUID allocateGuid) {
		this.allocateGuid = allocateGuid;
	}
	
	public InventoryAllocateStatus getPrestatus() {
		return prestatus;
	}

	public void setPrestatus(InventoryAllocateStatus prestatus) {
		this.prestatus = prestatus;
	}

	public InventoryAllocateStatus getUpTostatus() {
		return upTostatus;
	}

	public void setUpTostatus(InventoryAllocateStatus upTostatus) {
		this.upTostatus = upTostatus;
	}

	/**
	 * 设置审核日期
	 * @param examineDate
	 */
	public void setExamineDate(long examineDate) {
		this.examineDate = examineDate;
	}
	
	
	/**
	 * 取得更新的结果（更新的记录条数）
	 * @return
	 */
	public int getUpdateResult() {
		return updateResult;
	}
	/**
	 * 设置更新的结果
	 * @param updateResult
	 */
	public void setUpdateResult(int updateResult) {
		this.updateResult = updateResult;
	}
	/**
	 * 设置退回原因
	 * @param rejectReason
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	public GUID getAllocateGuid() {
		return allocateGuid;
	}
	public long getExamineDate() {
		return examineDate;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}
	
	// 货位信息
	private AllocateItemDet[] items;
	public AllocateItemDet[] getItems() {
		return items;
	}
	public void setItems(AllocateItemDet[] items) {
		this.items = items;
	}
}
