package com.spark.psi.publish.account.key;

import com.spark.psi.publish.PartnerType;

/**
 * 
 * <p>��ѯ�����ó�ʼ���������Ŀͻ�or��Ӧ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 * ��ѯ��ʽ��context.find(Integer.class, GetInitedPartnerSummaryKey);
 * @version 2012-7-16
 */
public class GetInitedPartnerSummaryKey {
	
	private PartnerType partnerType;

	/**
	 * 
	 * @param partnerType ��Ӧ��or�ͻ�
	 */
	public GetInitedPartnerSummaryKey(PartnerType partnerType) {
		super();
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType() {
		return partnerType;
	}
	
	
}
