package com.spark.oms.publish.order.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public interface OrderInfo {
	/**
	 * ����Id
	 * @return
	 */
	public GUID getRECID() ;
	
	/**
	 * �⻧RECID
	 * @return
	 */
	public GUID getTenantsRECID() ;
	
	/**
	 * �⻧����
	 * @return
	 */
	public String getTenantsName() ;
	
	/**
	 * ����RECID
	 * @return
	 */
	public GUID getSaleRECID() ;
	
	/**
	 * ��������
	 * @return
	 */
	public String getSaleName() ;
	
	/**
	 * �������
	 * @return
	 */
	public double getOrderAmount() ;
	
	/**
	 * Ӧ�ս��
	 * @return
	 */
	public double getDueAmount() ;
	
	/**
	 * ʵ�ս��
	 * @return
	 */
	public double getDoneAmount();
	
	/**
	 * ����ʱ��
	 * @return
	 */
	public long getCreateTime() ;
	
	/**
	 * �������
	 * @return
	 */
	public String getOrderNo() ;
	
	/**
	 * �������
	 * @return
	 */
	public String getOrderType() ;
	
	/**
	 * �޸���RECID
	 * @return
	 */
	public GUID getChangerRECID();
	
	/**
	 * �޸�������
	 * @return
	 */
	public String getChanger();
	
	/**
	 * �޸�ʱ��
	 * @return
	 */
	public long getChangeTime();
	
	/**
	 * �����Ƿ�������޸�
	 * @return
	 */
	public String getIsChange();

	/**
	 * ��������
	 */
	public List<OrderServiceReceiptInfo> getOrderServiceReceiptInfoList() ;
}
