package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����������ѯ������Ϣ(ҵ�������)</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public class GetOrderListBusKey {
	private GUID partnerGuid;
	//��Чʱ��
	private Long beginTime;
	private Long endTime;
	/**
	 * �ͻ���Ӧ��id
	 * @return GUID
	 */
	public GUID getPartnerGuid() {
		return partnerGuid;
	}
	public void setPartnerGuid(GUID partnerGuid) {
		this.partnerGuid = partnerGuid;
	}
	/**
	 * ��ʼʱ��
	 * @return Long
	 */
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * ����ʱ��
	 * @return Long
	 */
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
}
