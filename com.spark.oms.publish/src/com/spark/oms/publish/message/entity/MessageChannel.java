package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 通道
 * @author Administrator
 *
 */
public class MessageChannel {
	//id
	private GUID id;
	//名称
	private String name;
	//编码
	private String code;
	//计价方式
	private String chargeType;
	//价格
	private Double price;
	//支持批量发送
	private String isBatchSend;
	//免费短信条数
	private int freeSMSNumber;
	//超出部分计价方式
	private Double overtakePrice;
	//描述
	private String remark;
	//支持网络
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
