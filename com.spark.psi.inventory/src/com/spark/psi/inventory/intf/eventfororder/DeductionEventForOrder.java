/**
 * 
 */
package com.spark.psi.inventory.intf.eventfororder;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * ����ר�������仯+�ּ��¼������ڶ���ά�����ö��ֵ
 *
 */
public class DeductionEventForOrder extends Event {

	/**
	 * ������Ŀid
	 */
	private GUID dealingItemId;
	
	/**
	 * ��Ӧ�ͻ���Ӧ��id
	 */
	private PartnerType partnerType;
	
	/**
	 * �Ƿ�����ݼ�
	 */
	private boolean deductioned;
	/**
	 * �ּ����ⵥ���
	 */
	private double outAmount;
	/**
	 * �ּ���ⵥ���
	 */
	private double inAmount;
	

	/**
	 * 
	 * @param dealingItemId ������ϸID
	 * @param partnerType �ͻ�OR��Ӧ��
	 * @param deductioned �Ƿ�ּ�
	 * @param outAmount �ּ����ⵥ���
	 * @param inAmount �ּ���ⵥ���
	 */
	public DeductionEventForOrder(GUID dealingItemId, PartnerType partnerType,
			boolean deductioned, double outAmount,double inAmount) {
		super();
		this.dealingItemId = dealingItemId;
		this.partnerType = partnerType;
		this.deductioned = deductioned;
		this.outAmount = outAmount;
		this.inAmount = inAmount;
	}
	
	public GUID getDealingItemId() {
		return dealingItemId;
	}
	
	public PartnerType getPartnerType() {
		return partnerType;
	}
	
	public boolean isDeductioned() {
		return deductioned;
	}
	public double getOutAmount() {
		return outAmount;
	}

	public double getInAmount() {
		return inAmount;
	}
	
}
