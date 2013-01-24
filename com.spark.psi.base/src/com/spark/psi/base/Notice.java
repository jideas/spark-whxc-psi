package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�������ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-26
 */

public interface Notice{

	/**
	 * GUID
	 */
	public GUID getRECID();

	/**
	 * �⻧GUID
	 */
	public GUID getTenantsGuid();

	/**
	 * ����GUID
	 */
	public GUID getDeptGuid();

	/**
	 * ������Χ
	 */
	public String getDeptNameStr();

	/**
	 * ������GUID
	 */
	public GUID getCreateGuid();

	/**
	 * ������
	 */
	public String getCreatePerson();

	/**
	 * �������
	 */
	public String getNoticeTitle();

	/**
	 * �������ƴ��
	 */
	public String getNoticeTitlePY();

	/**
	 * ����ʱ��
	 */
	public long getCreateTime();

	/**
	 * ����ʱ��
	 */
	public long getPublishTime();

	/**
	 * ����ʱ��
	 */
	public long getCancelTime();

	/**
	 * �Ƿ��ö�
	 */
	public boolean getIsTop();

	/**
	 * ����״̬
	 */
	public String getNoticeStatus();

	/**
	 * ��������
	 */
	public String getNoticeContent();
}
