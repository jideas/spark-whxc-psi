package com.spark.order.purchase.intf.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TypeEnum;


/**
 * <p>�ɹ��嵥ʵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-11
 */
@Deprecated
public class PurchaseCreateInfo{
	private double provPrice;//�ϴβɹ�����
	public GUID recid;
	private TypeEnum type;//ֱ����Ʒ��־��ֱ����TypeEnum.DIRECT
	private PurchaseOrderItem det;
	private PurchaseOrderInfo info;
//	public CusProviderEntity cusp;
	public double oldPrice = -1;
	/**
	 * �ɹ��嵥����ֱ����
	 */
//	public BuyGoodsInfo dataInfo = null; //���ݿ�洢��info��Ϣ
	/**
	 * �������
	 */
//	public FStorageEntity storage; // �����Ϣ
//	/**
//	 * �ɹ��嵥(ֱ��)
//	 */
//	public DirectStorageEntity directInfo = null; //ֱ����Ʒ���ݿ�洢��info��Ϣ
	
	/**
	 * ֱ����Ʒ��־��ֱ����TypeEnum.DIRECT
	 * @param type void
	 */
	public void setType(TypeEnum type) {
		this.type = type;
	}
	/**
	 * ֱ����Ʒ��־��ֱ����TypeEnum.DIRECT
	 * @return TypeEnum
	 */
	public TypeEnum getType() {
		return type;
	}
	public double getProvPrice() {
		return provPrice;
	}
	public void setProvPrice(double provPrice) {
		this.provPrice = provPrice;
	}
	/**
	 * ��Ʒ��ɹ�������ϸ
	 * @return BuyOrdDet
	 */
	public PurchaseOrderItem getDet() {
		return det;
	}
	/**
	 * ��Ʒ��ɹ�������ϸ
	 * @param det void
	 */
	public void setDet(PurchaseOrderItem det) {
		this.det = det;
	}
	
	/**
	 * �ɹ�������Ϣ
	 * @return BuyOrdInfo
	 */
	public PurchaseOrderInfo getInfo() {
		return info;
	}
	/**
	 * �ɹ�������Ϣ
	 * @param info void
	 */
	public void setInfo(PurchaseOrderInfo info) {
		this.info = info;
	}
}
