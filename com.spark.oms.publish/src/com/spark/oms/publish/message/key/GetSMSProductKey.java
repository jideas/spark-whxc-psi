package com.spark.oms.publish.message.key;

/**
 * 根据预存金额获取租户享受到 产品信息
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
