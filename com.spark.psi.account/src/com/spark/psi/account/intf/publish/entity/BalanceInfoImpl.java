package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.BalanceInfo;

public class BalanceInfoImpl implements BalanceInfo {

	private GUID id;//	��ʶ
	private GUID partnerId;//	
	private String partnerName;//	�ͻ���Ӧ��ȫ��
	private String namePY;//	����ƴ��
	private String partnerShortName;//	�ͻ���Ӧ�̼��
	private String type;//	���ͣ��ͻ�/��Ӧ�̣�
	private double amount;//	Ӧ��/Ӧ��
	private double initAmount;//	��ʼ��Ӧ��/Ӧ��
	private BalanceInfoItemImpl[] items;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getPartnerShortName() {
		return partnerShortName;
	}
	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getInitAmount() {
		return initAmount;
	}
	public void setInitAmount(double initAmount) {
		this.initAmount = initAmount;
	}
	public void setItems(BalanceInfoItemImpl[] items) {
		this.items = items;
	}
	public BalanceInfoItemImpl[] getItems() {
		return items;
	}
	
	
}
