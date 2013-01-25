package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryWarningType;

/**
 * ��Ʒ��ϸ��Ϣ���ݣ���������������Ϣ����������ϸ��Ŀ����Ϣ�� <BR>
 * ��ѯ˵����<br>
 * (1)������ƷID��ѯ������GoodsInfo<br>
 * (2)����GetGoodsInfoListKey��ѯ������GoodsInfo�б�
 */
public interface GoodsInfo {

	/**
	 * ��ȡ��ƷID
	 * 
	 * @return
	 */
	public GUID getId();

	/**
	 * @return �Ƿ���Ӫ��Ʒ
	 */
//	public boolean isJointVenture();

	/**
	 * @return ��Ӫ��Ӧ��
	 */
//	public GUID getSupplierId();
	
	/**
	 * 
	 * @return ��Ӫ��Ӧ������
	 */
//	public String getSupplier();

	/**
	 * @return ������
	 */
	public int getShelfLife();

	/**
	 * @return ������Ԥ������
	 */
	public int getWarningDay();

	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public String getCode();

	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * ��ȡͳһ���ۼ۸�
	 * 
	 * @return
	 */
	public double getSalePrice();

	/**
	 * ��ȡ��Ʒ״̬
	 * 
	 * @return
	 */
	public GoodsStatus getStatus();

	/**
	 * ��ȡ��ע��Ϣ
	 * 
	 * @return
	 */
	public String getRemark();

	/**
	 * �Ƿ��Ѿ������õı�־
	 * 
	 * @return
	 */
	public boolean isRefFlag();

	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public GoodsCategoryInfo getCategory();

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @return
	 */
	public GoodsItemData[] getItems();

	/**
	 * ��Ʒ���Ԥ������ �����Ԥ�� ������Ԥ��
	 * 
	 * @return GoodsWarnningType
	 */
	public InventoryWarningType getGoodsWarnningType();

}
