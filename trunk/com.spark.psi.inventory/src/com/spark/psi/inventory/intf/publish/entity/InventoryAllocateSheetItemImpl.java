package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;

/**
 * ���������б���Ŀ<br>
 * ��ѯ������ListEntity<InventoryAllocateSheetItem>+GetInventoryAllocateSheetListKey
 */
public class InventoryAllocateSheetItemImpl implements InventoryAllocateSheetItem{

	/**
	 * ������ID
	 * 
	 * @return
	 */
	private GUID sheetId;

	/**
	 * ����������
	 * 
	 * @return
	 */
	private String sheetNumber;
	
	private String approvePerson;

	/**
	 * ������״̬
	 * 
	 * @return
	 */
	private InventoryAllocateStatus status;

	/**
	 * Դ�ֿ�ID
	 * 
	 * @return
	 */
	private GUID sourceStoreId;

	/**
	 * Դ�ֿ�����
	 * 
	 * @return
	 */
	private String sourceStoreName;

	/**
	 * Ŀ�Ĳֿ�ID
	 * 
	 * @return
	 */
	private GUID destinationStoreId;

	/**
	 * Ŀ�Ĳֿ�����
	 * 
	 * @return
	 */
	private String destinationStoreName;

	/**
	 * ����ʱ��
	 * 
	 * @return
	 */
	private long allocateInDate;

	/**
	 * ����ʱ��
	 * 
	 * @return
	 */
	private long allocateOutDate;

	/**
	 * ����ʱ��
	 * 
	 * @return
	 */
	private long createDate;

	/**
	 * �Ƶ���ID
	 * 
	 * @return
	 */
	private GUID creatorId;

	/**
	 * �Ƶ�������
	 * 
	 * @return
	 */
	private String creatorName;
	
	/**
	 * ��������������
	 * 
	 * @return
	 */
	private String inExamName;
	/**
	 * ��������������
	 * 
	 * @return
	 */
	private String outExamName;

	public String getInExamName() {
		return inExamName;
	}

	public void setInExamName(String inExamName) {
		this.inExamName = inExamName;
	}

	public String getOutExamName() {
		return outExamName;
	}

	public void setOutExamName(String outExamName) {
		this.outExamName = outExamName;
	}

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public InventoryAllocateStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryAllocateStatus status) {
		this.status = status;
	}

	public GUID getSourceStoreId() {
		return sourceStoreId;
	}

	public void setSourceStoreId(GUID sourceStoreId) {
		this.sourceStoreId = sourceStoreId;
	}

	public String getSourceStoreName() {
		return sourceStoreName;
	}

	public void setSourceStoreName(String sourceStoreName) {
		this.sourceStoreName = sourceStoreName;
	}

	public GUID getDestinationStoreId() {
		return destinationStoreId;
	}

	public void setDestinationStoreId(GUID destinationStoreId) {
		this.destinationStoreId = destinationStoreId;
	}

	public String getDestinationStoreName() {
		return destinationStoreName;
	}

	public void setDestinationStoreName(String destinationStoreName) {
		this.destinationStoreName = destinationStoreName;
	}

	public long getAllocateInDate() {
		return allocateInDate;
	}

	public void setAllocateInDate(long allocateInDate) {
		this.allocateInDate = allocateInDate;
	}

	public long getAllocateOutDate() {
		return allocateOutDate;
	}

	public void setAllocateOutDate(long allocateOutDate) {
		this.allocateOutDate = allocateOutDate;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public GUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getApprovePerson() {
		return approvePerson;
	}

	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}
	

}
