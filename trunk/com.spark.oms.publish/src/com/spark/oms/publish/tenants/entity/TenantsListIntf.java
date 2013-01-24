package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * �ͻ��б�չ��
 */
public interface TenantsListIntf {
	//��ʶ
	public GUID getId() ;
	
	//���
	public String getShortName();
	
	//����
	public String getName();
	
	//�ܾ�������
	public String getBossName() ;
	
	//�ܾ����Ա�
	public String getBossSex() ;
	
	//�ܾ�������
	public String getBossEmail() ;
	
	//�ƶ�
	public String getBossMobile() ;
	
	//�̻�
	public String getBossTel();
	
	//���۾���
	public String getSaleManager() ;
	
	//�����˻����
	public double getLeaseAccountBalance();
	
	//�����˻����
	public double getPieceAccountBalance() ;
	
	//�տ���
	public double getReceiptAmount();
	
	//��Ʊ���
	public double getInvoiceAmount() ;
	
	//����ʱ��
	public int getServeMonth() ;
	
	//�״ζ���ʱ��
	public long getFirstSignOrderDate();
	
	//��ȡ�⻧�����ʽ�⻧����ʧ���˿�
	public String getStatus();
	//��ȡ�˿�״̬
	public String getRefundstatus();
}
