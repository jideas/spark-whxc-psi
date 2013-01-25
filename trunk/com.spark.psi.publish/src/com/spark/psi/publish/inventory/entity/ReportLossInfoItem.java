package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ReportLossInfoItem {
	/**
	 * 
	 * @return
	 */
	public GUID getId();
	/**
	 * �����ı���ID
	 * @return
	 */
	public GUID getReportLossSheetId();
	
	/**
	 * ��ƷID
	 * @return
	 */
	public GUID getGoodsId();
	
	/**
	 * ��Ʒ���
	 * @return
	 */
	public String getGoodsCode();
	
	/**
	 * ����
	 * @return
	 */
	public String getGoodsNo();
	
	/**
	 * ��Ʒ����
	 * @return
	 */
	public String getGoodsName();
	
	/**
	 * ��Ʒ��λ
	 * @return
	 */
	public String getGoodsUnit();
	
	/**
	 * ��Ʒ���
	 * @return
	 */
	public String getGoodsSpec();
	
	/**
	 * ��������
	 * @return
	 */
	public double getReportCount();
	
	public int getScale();
	
	// Ŀǰֻ�в��ϱ���
//	public ReportLossType getReportLossType();
	
	public String getReportReason();
	
	public ReportLossInfoItemDet[] getItemDets();
}
