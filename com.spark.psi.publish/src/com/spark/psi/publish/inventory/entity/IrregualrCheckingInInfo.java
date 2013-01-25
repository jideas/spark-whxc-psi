package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.order.entity.OrderGoodsItem;

/**
 * �����ⵥ
 * ok
 *
 */
public interface IrregualrCheckingInInfo {
	
	/**
	 * �����ⵥID
	 * @return
	 */
	public GUID getId();
	
	/**
	 * �����ⵥ���
	 * @return
	 */
	public String getSheetNo(); 
	
	/**
	 * ��ȡ�������
	 */
	public CheckingInType getType();
	
	/**
	 * ��ȡ���ֿ�����
	 */
	public String getStoreName();
	
	/**
	 * ��ȡ��Ӧ������
	 */
	public String getSupplierName();
	
	/**
	 * ��ȡ�ɹ�������
	 * @return
	 */
	public String getBuyPerson();
	
	/**
	 * ��ȡ�ɹ�����
	 * @return
	 */
	public long getBuyDate();
	
	/**
	 * ��ñ�ע
	 * @return
	 */
	public String getRemark();
	
	/**
	 * ��������
	 * @return
	 */
	public double getCheckingInAmount();
	
	/**
	 * ��ȡȷ�����������
	 * @return
	 */
	public String getCheckInPersonName();
	
	/**
	 * ���ȷ���������
	 * @return
	 */
	public long getCheckingInDate();
	
	/**
	 * ��ȡ��ⵥ״̬
	 */
	public CheckingInStatus getStatus();
	
	/**
	 * ��������Ʒ��ϸ
	 * @return
	 */
	public IrregualrGoodsItem[] getGoodsItems();
	
	public interface IrregualrGoodsItem extends OrderGoodsItem {
		
	}
}
