package com.spark.oms.publish.message.key;

/**
 * ����Ԥ�����ȡ�⻧���ܵ� ��Ʒ��Ϣ
 * 
 *GetSMSProductKey &ProductInfo
 */
public class GetSMSProductKey {
	String productCode;
	double smsDesposit;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public double getSmsDesposit() {
		return smsDesposit;
	}
	public void setSmsDesposit(double smsDesposit) {
		this.smsDesposit = smsDesposit;
	}
	

}
