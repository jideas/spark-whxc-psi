package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * ����ͨ������������<br>
 * ��ѯ������ͨ������ͨ��ID��ѯMessageChannelInfo����
 * 
 */
public interface MessageChannelInfo {

	/**
	 * ͨ��RECID
	 * @return
	 */
	public GUID getRECID();

	/**
	 * ͨ������
	 * @return
	 */
	public String getName();

	/**
	 * ͨ�����
	 * @return
	 */
	public String getCode();

	/**
	 * �Ƽ�����
	 * @return
	 */
	public String getChargeType();

	/**
	 * ����
	 * @return
	 */
	public double getPrice();

	/**
	 * �Ƿ�֧����������
	 * @return
	 */
	public String getIsBatchSend();

	/**
	 * ��Ѷ�������
	 * @return
	 */
	public int getFreeSMSNumber();

	/**
	 * �������ּƼ۷�ʽ
	 * @return
	 */
	public double getOvertakePrice();

	/**
	 * ����
	 * @return
	 */
	public String getRemark();

	/**
	 * ֧������
	 * @return
	 */
	public String getSupportNetWork();
	
	/**
	 * ��ȡͨ�����������б�
	 */
	public MessageChannelConfigItem[] getMessageChannelConfigItems();

}