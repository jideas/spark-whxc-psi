package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���Ͷ��š���ϸ�ӿ�
 * 
 *
 */
public interface SendItem {
	
	/**
	 * ���Ͷ��ż�¼��ˮ��Id
	 * @return
	 */
	public GUID getRECID();

	/**
	 * �����ײ�Id
	 * ����ܱ�֤�����ײ���һ���ռ����¼����Ψһ�ģ�������һ�����������������ײͣ����Ƶ����ŷ������ӿ���ȥ
	 */
	public GUID getServiceRECID();
	
	/**
	 * �����ײ�����
	 */
	public String getServiceName();

	/**
	 * ���ͺ���
	 */
	public String getMobile();
	
	/**
	 * ��������
	 */
	public String getContent();
	
	/**
	 * ���
	 */
	public double getPrice();
	
	/**
	 * ״̬��0ʧ�ܡ����ɹ�
	 */
	public String getStatus();
	
	/**
	 * ��ѣ�0��ѡ�1�շ�
	 * @return
	 */
	public String IsFree();
}
