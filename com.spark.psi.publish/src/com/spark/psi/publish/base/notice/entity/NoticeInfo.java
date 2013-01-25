package com.spark.psi.publish.base.notice.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */
public interface NoticeInfo {
	
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
	
	/**
	 * ����GUID�б�
	 */
	public List<GUID> getDeptGuidList();
}
