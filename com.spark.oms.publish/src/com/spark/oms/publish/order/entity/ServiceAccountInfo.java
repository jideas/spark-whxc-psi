package com.spark.oms.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ServiceAccountInfo {
	
	/**
	 * ��ʶ
	 */
	public GUID getRECID();
	
	/**
	 * �⻧ID
	 */
	public GUID getTenantsRECID() ;
	
	/**
	 * ��Ʒϵͳ��ʶ
	 */
	public String getProductSerials() ;
	
	/**
	 * �����˻����
	 */
	public double getLeaseAccountBalance() ;
	
	/**
	 * ��֧����˻����
	 */
	public double getLeaseAccountCanUseBalance();
	
	/**
	 * Ӧ�ɽ��
	 */
	public double getPayAmount();

}
