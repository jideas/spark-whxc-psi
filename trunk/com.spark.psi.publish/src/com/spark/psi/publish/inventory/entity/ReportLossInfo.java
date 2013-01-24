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
	 * ���ݱ��
	 * @return
	 */
	public String getSheetNo();
	/**
	 * �ֿ�ID
	 * @return
	 */
	public GUID getStoreId();
	/**
	 * �ֿ�����
	 * @return
	 */
	public String getStoreName();
	/**
	 * ��������
	 * @return
	 */
	public long getApplyDate();
	/**
	 * ��������
	 * @return
	 */
	public long getCreateDate();
	/**
	 * �����ˣ����ƣ�
	 * @return
	 */
	public String getCreateor();
	
	/**
	 * ������ID
	 * @return
	 */
	public GUID getCreatorId();
	/**
	 * ������ID
	 * @return
	 */
	public GUID getApprovalPersonId();
	/**
	 * ����������
	 * @return
	 */
	public String getApprovalPersonName();
	/**
	 * ��������
	 * @return
	 */
	public long getApprovalDate();
	
	/**
	 * ������Ʒ��Ŀ��Ϣ
	 * @return
	 */
	public ReportLossInfoItem[] getItems();
	/**
	 * ��ע
	 * @return
	 */
	public String getRemark();
	
	/**
	 * ״̬
	 * @return
	 */
	public ReportLossStatus getStatus(); 
	
	/**
	 * �˻�ԭ��
	 * @return
	 */
	public String getRejectReason();
}
