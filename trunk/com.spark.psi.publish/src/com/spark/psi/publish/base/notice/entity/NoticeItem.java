package com.spark.psi.publish.base.notice.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�����б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */
public interface NoticeItem{

	/**
	 * ���ID
	 */
	public GUID getRECID();
	
	/**
	 * ������GUID
	 */
	public GUID getCreateGuid();
	
	/**
	 * ��ô�����
	 */
	public String getCreatePerson();

	/**
	 * ��ù������
	 */
	public String getNoticeTitle();

	/**
	 * ��÷���ʱ��
	 */
	public long getPublishTime();

	/**
	 * ��ó���ʱ��
	 */
	public long getCancelTime();

	/**
	 * ��÷�����Χ
	 */
	public String getDeptNameStr();

	/**
	 * ����Ƿ��ö�
	 */
	public boolean getIsTop();

}
