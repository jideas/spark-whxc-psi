package com.spark.oms.publish.product.entity;

import com.jiuqi.dna.core.type.GUID;

public class ProductGracePeriod {
	//��Ʒ��ʶ
	private GUID productId;
	
	//�¼Ƽ�
	private int monthGracePeriod;
	
	//���Ƽ�
	private int quarterGracePeriod;
	
	//����Ƽ�
	private int halfYearGracePeriod;
	
	//��Ƽ�
	private int yearGracePeriod;

	public GUID getProductId() {
		return productId;
	}

	public void setProductId(GUID productId) {
		this.productId = productId;
	}

	public int getMonthGracePeriod() {
		return monthGracePeriod;
	}

	public void setMonthGracePeriod(int monthGracePeriod) {
		this.monthGracePeriod = monthGracePeriod;
	}

	public int getQuarterGracePeriod() {
		return quarterGracePeriod;
	}

	public void setQuarterGracePeriod(int quarterGracePeriod) {
		this.quarterGracePeriod = quarterGracePeriod;
	}

	public int getHalfYearGracePeriod() {
		return halfYearGracePeriod;
	}

	public void setHalfYearGracePeriod(int halfYearGracePeriod) {
		this.halfYearGracePeriod = halfYearGracePeriod;
	}

	public int getYearGracePeriod() {
		return yearGracePeriod;
	}

	public void setYearGracePeriod(int yearGracePeriod) {
		this.yearGracePeriod = yearGracePeriod;
	}
	
}
