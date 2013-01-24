package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���Ͷ��� �����߼�ʱ�֣�ʵ�� Send + SendItem = oms_messageSentLog(���Ͷ�����ˮ);
 * 
 *
 */
public interface Send {

	/**
	 * ����id(�ռ����м�¼id)	
	 * @return
	 */
	public GUID getSMSRECID();
	
	/**
	 * ����ʱ�䣬ͬһ�����ύ�����ʱ������ͬ��
	 * @return
	 */
	public long getSentTime();
	
	/**
	 * �⻧Id
	 * @return
	 */
	public GUID getTenantsRECID();
	
	/**
	 * �⻧����
	 * @return
	 */
	public String getTenantsName();
	
	/**
	 * ����ͨ������
	 * @return
	 */
	public String getChannelRECID();
	
	/**
	 * ����ͨ��
	 * @return
	 */
	public String getChannelName();
	
	/**
	 * ���ŷ�����ϸ
	 * @return
	 */
	public SendItem[] getSendItems();
}
