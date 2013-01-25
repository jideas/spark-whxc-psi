package com.spark.psi.publish.base.contact.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ����ָ����ϵ�˵�����ϵ�˵�����Key</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-8
 */

public class FindMainPersonCountKey{

	/**�ͻ���Ӧ��Guid*/
	private GUID cusproGuid;

	/**������ϵ��Guid*/
	private GUID contactPersonGuid;

	/** 
	 *���췽��
	 */
	public FindMainPersonCountKey(){
		super();
	}

	/** 
	 *���췽��
	 *@param contactPersonGuid
	 */
	public FindMainPersonCountKey(GUID cusproGuid, GUID contactPersonGuid){
		super();
		this.cusproGuid = cusproGuid;
		this.contactPersonGuid = contactPersonGuid;
	}

	public GUID getCusproGuid(){
		return cusproGuid;
	}

	public void setCusproGuid(GUID cusproGuid){
		this.cusproGuid = cusproGuid;
	}

	public GUID getContactPersonGuid(){
		return contactPersonGuid;
	}

	public void setContactPersonGuid(GUID contactPersonGuid){
		this.contactPersonGuid = contactPersonGuid;
	}

}
