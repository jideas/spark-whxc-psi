package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.InventoryWarningType;

/**
 * ������ϸ��Ϣ���ݣ���������������Ϣ����������ϸ��Ŀ����Ϣ�� <BR>
 * ��ѯ˵����<br>
 * (1)���ݲ���ID��ѯ������MaterialsInfo<br>
 * (2)����GetMaterialsInfoListKey��ѯ������MaterialsInfo�б�
 */
public interface MaterialsInfo {

	/**
	 * ��ȡ��ƷID
	 * 
	 * @return
	 */
	public GUID getId();
	
	
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
	public MaterialsStatus getStatus();

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
	public MaterialsCategoryInfo getCategory();

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @return
	 */
	public MaterialsItemData[] getItems();
	
	/**
	 * ��Ʒ���Ԥ������
	 *  �����Ԥ��
	 *  ������Ԥ��
	 * @return MaterialsWarnningType
	 */
	public InventoryWarningType getMaterialsWarnningType();
	
	public GUID getSupplierId();
	public String getSupplier();
	public double getPercentage();
	public boolean isJointVenture();

}
