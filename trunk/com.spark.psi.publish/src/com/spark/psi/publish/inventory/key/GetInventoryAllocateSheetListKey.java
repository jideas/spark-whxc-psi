package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * ��ѯ���������б�
 */
public class GetInventoryAllocateSheetListKey extends LimitKey {

	
	/**
	 * ״̬
	 */
	private InventoryAllocateStatus[] status;

	/**
	 * ��ѯʱ��
	 */
	private QueryTerm queryTerm;
	
	/**
	 * �����ֶ�
	 */
	private SortField sortField;
	//����״̬����
	/**
	 * ����
	 */
	public static InventoryAllocateStatus[] Submittingstatus = new InventoryAllocateStatus[]{InventoryAllocateStatus.Submitting,InventoryAllocateStatus.Rejected};
	/**
	 * ���
	 */
	public static InventoryAllocateStatus[] Approvalingstatus = new InventoryAllocateStatus[]{InventoryAllocateStatus.Submitted};
	/**
	 * ����
	 */
	public static InventoryAllocateStatus[] Processingstatus = new InventoryAllocateStatus[]{InventoryAllocateStatus.Submitted,InventoryAllocateStatus.Allocating,InventoryAllocateStatus.AllocateOut};
	/**
	 * ��¼
	 */
	public static InventoryAllocateStatus[] Processedstatus = new InventoryAllocateStatus[]{InventoryAllocateStatus.AllocateIn};
	

	/**
	 * ���캯��
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetInventoryAllocateSheetListKey(int offset, int count,
			boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	/**
	 * @param queryTerm
	 *            the queryTerm to set
	 */
	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	} 

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortField getSortField() {
		return sortField;
	}

	public InventoryAllocateStatus[] getStatus() {
		return status;
	}

	public void setStatus(InventoryAllocateStatus[] status) {
		this.status = status;
	}

	public static InventoryAllocateStatus[] getSubmittingstatus() {
		return Submittingstatus;
	}

	public static void setSubmittingstatus(
			InventoryAllocateStatus[] submittingstatus) {
		Submittingstatus = submittingstatus;
	}

	public static InventoryAllocateStatus[] getApprovalingstatus() {
		return Approvalingstatus;
	}

	public static void setApprovalingstatus(
			InventoryAllocateStatus[] approvalingstatus) {
		Approvalingstatus = approvalingstatus;
	}

	public static InventoryAllocateStatus[] getProcessingstatus() {
		return Processingstatus;
	}

	public static void setProcessingstatus(
			InventoryAllocateStatus[] processingstatus) {
		Processingstatus = processingstatus;
	}

	public static InventoryAllocateStatus[] getProcessedstatus() {
		return Processedstatus;
	}

	public static void setProcessedstatus(InventoryAllocateStatus[] processedstatus) {
		Processedstatus = processedstatus;
	}

	/**
	 * �����ֶ�
	 */
	public static enum SortField {
		None(""), //
		SheetNumber("allocSheetNo"), //
		CreateDate("createDate"), //
		CreatorName("creator"),
		SourceStoreName("allocateOutStoreName"), //
		DestinationStoreName("allocateInStoreName"), //
		AllocateOutDate("allocateOutDate"), //
		AllocateInDate("allocateInDate"), //
		status("status");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
