package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ͨ��
 * @author Administrator
 *
 */
public class MessageChannel {
	//id
	private GUID id;
	//����
	private String name;
	//����
	private String code;
	//�Ƽ۷�ʽ
	private String chargeType;
	//�۸�
	private Double price;
	//֧����������
	private String isBatchSend;
	//��Ѷ�������
	private int freeSMSNumber;
	//�������ּƼ۷�ʽ
	private Double overtakePrice;
	//����
	private String remark;
	//֧������
	private String supportNetWork;
	
	public String getSupportNetWork() {
		return supportNetWork;
	}
	public void setSupportNetWork(String supportNetWork) {
		this.supportNetWork = supportNetWork;
	}
	public GUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getChargeType() {
		return chargeType;
	}
	public Double getPrice() {
		return price;
	}
	public String getIsBatchSend() {
		return isBatchSend;
	}
	public int getFreeSMSNumber() {
		return freeSMSNumber;
	}
	public Double getOvertakePrice() {
		return overtakePrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setIsBatchSend(String isBatchSend) {
		this.isBatchSend = isBatchSend;
	}
	public void setFreeSMSNumber(int freeSMSNumber) {
		this.freeSMSNumber = freeSMSNumber;
	}
	public void setOvertakePrice(Double overtakePrice) {
		this.overtakePrice = overtakePrice;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
