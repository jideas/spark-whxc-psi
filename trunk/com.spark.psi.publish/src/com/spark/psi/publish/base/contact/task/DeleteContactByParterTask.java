package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.PartnerType;

/**
 * <p>���ݿͻ�/��Ӧ��ɾ����ϵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-16
 */

public class DeleteContactByParterTask extends SimpleTask{

	/**�ͻ�/��Ӧ������*/
	private PartnerType partnerType;

	/** 
	 *���췽��
	 *@param partnerType
	 */
	public DeleteContactByParterTask(PartnerType partnerType){
		super();
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType(){
		return partnerType;
	}

	public void setPartnerType(PartnerType partnerType){
		this.partnerType = partnerType;
	}

}
