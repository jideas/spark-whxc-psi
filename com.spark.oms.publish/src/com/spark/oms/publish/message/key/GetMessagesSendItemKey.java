package com.spark.oms.publish.message.key;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��������Χ�ӿ�
 * 
 * ��ȡ���ŷ�����������ͳɹ�������ʧ�����������ͽ��
 * 
 * �˷�����Ҫ���ã�ʱ���ӳ�
 */
public class GetMessagesSendItemKey {
	/**
	 * ��������
	 */
	private Date sendDate;
	
	/**
	 * �⻧Id
	 */
	private GUID tenantsId;

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	
	
}
