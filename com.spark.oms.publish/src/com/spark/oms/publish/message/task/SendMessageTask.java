package com.spark.oms.publish.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ���ŷ��ͽӿڣ�������Χ���͵Ķ����б�
 */
public class SendMessageTask extends SimpleTask{
	/**
	 * ����Id�� 
	 * ����Id���ɷ��Ͷ��ŷ��Զ�����,�����û����Ͷ��ŵĻ�ִID,Ŀǰ��δʵ��
	 * SMSRECID
	 */
	private GUID id;
	/**
	 * �⻧id
	 */
	private GUID tenantsId;
	/**
	 * �⻧����
	 * ���Բ���ֵ
	 */
	private String tenantsName;
	/**
	 * �ֻ������ַ�������ʽΪ �ƶ��绰���绰����;�绰���룬����֮����";"Ϊ���ӷ�
	 */
	private String mobile;
	/**
	 * ��������
	 */
	private String content;
	
	public SendMessageTask(String mobile,String content){
	    this.mobile = mobile;
	    this.content = content;
    }
	
	public SendMessageTask(String mobile,String content,GUID tenantId){
	    this.mobile = mobile;
	    this.content = content;
	    this.tenantsId = tenantId;
    }
	
	public SendMessageTask(){
    }
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public String getTenantsName() {
		return tenantsName;
	}
	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
