/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.allocate.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
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
 * <p>�޸ĵ���״̬</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
	 * �����������
	 * @param examineDate
	 */
	public void setExamineDate(long examineDate) {
		this.examineDate = examineDate;
	}
	
	
	/**
	 * ȡ�ø��µĽ�������µļ�¼������
	 * @return
	 */
	public int getUpdateResult() {
		return updateResult;
	}
	/**
	 * ���ø��µĽ��
	 * @param updateResult
	 */
	public void setUpdateResult(int updateResult) {
		this.updateResult = updateResult;
	}
	/**
	 * �����˻�ԭ��
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
	
	// ��λ��Ϣ
	private AllocateItemDet[] items;
	public AllocateItemDet[] getItems() {
		return items;
	}
	public void setItems(AllocateItemDet[] items) {
		this.items = items;
	}
}
