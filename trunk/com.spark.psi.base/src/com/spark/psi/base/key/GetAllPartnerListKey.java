package com.spark.psi.base.key;

import com.spark.psi.publish.PartnerType;

/**
 * 
 * <p>������еĿͻ���Ӧ���б�</p>
 * ����ֱ�Ӵ� PartnerType ö��


 *
 
 * @version 2012-3-12
 */
@Deprecated
public class GetAllPartnerListKey{
	
	/**
	 * �ͻ� or ��Ӧ��
	 */
	private PartnerType partnerType;

	/**
	 *  ��ѯ���пͻ���Ӧ���б�
	 * @param partnerType �ͻ� or ��Ӧ��
	 */
	public GetAllPartnerListKey(PartnerType partnerType){
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType(){
    	return partnerType;
    }
	
	
}
