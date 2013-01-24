
package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>库存调拨</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class AllocateInventory{
	private GUID id;
	private String allocSheetNo;
	private long applyDate;
	private String status;

	private GUID allocateOutStoreId;
	private String allocateOutStoreName;
	private GUID allocateInStoreId;
	private String allocateInStoreName;
	
	private GUID approvePersonId;
	private String approvePerson;
	private long approveDate;
	
	private GUID allocateOutPerson;
	private String allocateOutPersonName;
	private long allocateOutDate;
	private GUID allocateInPerson;
	private String allocateInPersonName;
	private long allocateInDate;

	private String allocateReason;
	private String rejectReason;
	
	private GUID creatorId;
	private GUID deptId;
	private String creator;
	private String creatorPY;
	private long createDate;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getAllocSheetNo() {
		return allocSheetNo;
	}
	public void setAllocSheetNo(String allocSheetNo) {
		this.allocSheetNo = allocSheetNo;
	}
	public long getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(long applyDate) {
		this.applyDate = applyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public GUID getAllocateOutStoreId() {
		return allocateOutStoreId;
	}
	public void setAllocateOutStoreId(GUID allocateOutStoreId) {
		this.allocateOutStoreId = allocateOutStoreId;
	}
	public String getAllocateOutStoreName() {
		return allocateOutStoreName;
	}
	public void setAllocateOutStoreName(String allocateOutStoreName) {
		this.allocateOutStoreName = allocateOutStoreName;
	}
	public GUID getAllocateInStoreId() {
		return allocateInStoreId;
	}
	public void setAllocateInStoreId(GUID allocateInStoreId) {
		this.allocateInStoreId = allocateInStoreId;
	}
	public String getAllocateInStoreName() {
		return allocateInStoreName;
	}
	public void setAllocateInStoreName(String allocateInStoreName) {
		this.allocateInStoreName = allocateInStoreName;
	}
	public GUID getApprovePersonId() {
		return approvePersonId;
	}
	public void setApprovePersonId(GUID approvePersonId) {
		this.approvePersonId = approvePersonId;
	}
	public String getApprovePerson() {
		return approvePerson;
	}
	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}
	public long getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}
	public GUID getAllocateOutPerson() {
		return allocateOutPerson;
	}
	public void setAllocateOutPerson(GUID allocateOutPerson) {
		this.allocateOutPerson = allocateOutPerson;
	}
	public String getAllocateOutPersonName() {
		return allocateOutPersonName;
	}
	public void setAllocateOutPersonName(String allocateOutPersonName) {
		this.allocateOutPersonName = allocateOutPersonName;
	}
	public long getAllocateOutDate() {
		return allocateOutDate;
	}
	public void setAllocateOutDate(long allocateOutDate) {
		this.allocateOutDate = allocateOutDate;
	}
	public GUID getAllocateInPerson() {
		return allocateInPerson;
	}
	public void setAllocateInPerson(GUID allocateInPerson) {
		this.allocateInPerson = allocateInPerson;
	}
	public String getAllocateInPersonName() {
		return allocateInPersonName;
	}
	public void setAllocateInPersonName(String allocateInPersonName) {
		this.allocateInPersonName = allocateInPersonName;
	}
	public long getAllocateInDate() {
		return allocateInDate;
	}
	public void setAllocateInDate(long allocateInDate) {
		this.allocateInDate = allocateInDate;
	}
	public String getAllocateReason() {
		return allocateReason;
	}
	public void setAllocateReason(String allocateReason) {
		this.allocateReason = allocateReason;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public GUID getDeptId() {
		return deptId;
	}
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorPY() {
		return creatorPY;
	}
	public void setCreatorPY(String creatorPY) {
		this.creatorPY = creatorPY;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	
}
