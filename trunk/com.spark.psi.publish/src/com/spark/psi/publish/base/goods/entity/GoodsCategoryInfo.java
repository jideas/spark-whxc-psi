package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ������ϸ���ݣ�����������Ϣ <br>
 * ��ѯ˵����<br>
 * (1)���ݷ���ID��ѯGoodsCategoryInfo����
 */
public interface GoodsCategoryInfo {


	/**
	 * ��ȡ����ID
	 * 
	 * @return
	 */
	public GUID getId();

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * ��ȡ������
	 * @return
	 */
	public String getCategoryNo();

	/**
	 * ��ȡ����С��λ
	 * 
	 * @return
	 */
	public int getScale(); 

	/**
	 * ��ȡ���Զ����б�
	 * 
	 * @return
	 */
	public PropertyDefine[] getPropertyDefines();

}
