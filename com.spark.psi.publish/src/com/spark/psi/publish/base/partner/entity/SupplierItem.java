package com.spark.psi.publish.base.partner.entity;


/**
 * 
 * <p>��Ӧ���б�</p>
 * 
 * ��Ӧ��ά�����б��ѯ��Ӧ���б� ListEntity<SupplierItem>+com.spark.psi.publish.partner.key.GetSupplierListKey;
 * ��Ʒ�ɹ�ѡ��Ӧ�̽����ѯ���ù�Ӧ���б� ListEntity<SupplierItem>+com.spark.psi.publish.partner.key.GetSupplierListKey;
 *


 *
 
 * @version 2012-3-2
 */
public interface SupplierItem extends PartnerItem{

	public String getSupplierType();
	
	public double getTaxRate();
}
