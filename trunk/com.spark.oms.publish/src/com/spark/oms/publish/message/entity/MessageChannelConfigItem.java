package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ����ͨ��������Ϣ
 * @author Administrator
 *
 */
public interface MessageChannelConfigItem {

	/**
	 * ��ȡRECID
	 */
	public GUID getId();
	
	/**
	 * ��ȡ�˺�
	 * @return
	 */
	public String getAccount();
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getPassword();
	
	/**
	 * ��ȡ�ֻ����������������
	 * @return
	 */
	public int getMobilePhoneMax();
	
	/**
	 * ��ȡС���ܵ��������������
	 * @return
	 */
	public int getPHSMax();
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public String getConnectType();
	
	/**
	 * ��ȡ������
	 * @return
	 */
	public String getServiceType();
	
	/**
	 * ��ȡip
	 * @return
	 */
	public String getIp();
	
	/**
	 * ��ȡ��ͨ�˿�
	 * @return
	 */
	public String getPort();
	
	/**
	 * ��ȡ�����Ŷ˿�
	 * @return
	 */
	public String getLongPort();
	
	/**
	 * ��ȡȺ���������
	 * @return
	 */
	public int getMassSMSMax();
	
	/**
	 * ��ȡ�෢�������
	 * @return
	 */
	public int getMultipleSMSMax();
	
	/**
	 * ��ȡ���ȼ�˳��
	 * @return
	 */
	public int getPriority();
	
	/**
	 * ��ȡ����ͨ��Id
	 * @return
	 */
	public GUID getMsgchannel();
	
}