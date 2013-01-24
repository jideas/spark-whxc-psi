package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;

/**
 * �ֿ���Ϣ<br>
 * ��ѯ������ <br>
 * (1)ֱ�Ӳ�ѯStore�б�<br>
 * (2)���ݲֿ�ID��ѯStore����<br>
 * (3)��ѯStore�б�<br>
 * (4)....
 */
public interface Store {
	static final GUID GoodsStoreId = GUID.valueOf("00000100000000000000000000000000");
	static final String GoodsStoreName = "��Ʒ��";
	public int getRecver();

	/**
	 * �ֿ�id
	 * @return the id
	 */
	public GUID getId();

	/**
	 * �ֿ�����
	 * @return the name
	 */
	public String getName();

	/**
	 * �ֿ�״̬
	 * @return the status
	 */
	public StoreStatus getStatus();

	/**
	 * �ֿ�����ʡ��
	 * @return the province
	 */
	
	
	public EnumEntity getProvince();

	/**
	 * �ֿ����ڳ���
	 * @return the city
	 */
	public EnumEntity getCity();

	/**
	 * �ֿ���������
	 * @return the district
	 */
	public EnumEntity getTown();

	/**
	 * �ֿ���ϸ��ַ
	 * @return the address
	 */
	public String getAddress();

	/**
	 * �ֿ��ʱ�
	 * @return the postCode
	 */
	public String getPostcode();

	/**
	 * �ֿ��ջ���
	 * @return the consignee
	 */
	public String getConsignee();

	/**
	 * �ֻ�
	 * @return the mobileNumber
	 */
	public String getMobileNo(); 

	/**
	 * ��Ӧ�Ŀ����Ա
	 * @return the keeperIds
	 */
	public GUID[] getKeeperIds(); 
	public GUID getCreatorId();
	
	public String getStoreNo();

	public int getShelfCount();

	public int getDefaultTiersCount();

	public String getStoreType();

}
