package com.spark.oms.publish.product.entity;

import java.io.Serializable;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductStatus;

public class ProductItemInfo implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;

	//��Ʒ��Ŀ��ʶ
	private GUID productItemId;
	
	//��Ʒ��ʶ
	private GUID productId;
	
	//�¼Ƽ�
	private double monthCharge;
	
	//���Ƽ�
	private double quarterCharge;
	
	//����Ƽ�
	private double halfYearCharge;
	
	//��Ƽ�
	private double yearCharge;
	
	
	//�¿�����
	private int monthGracePeriod;
	
	//��������
	private int quarterGracePeriod;
	
	//�������
	private int halfYearGracePeriod;
	
	//�������
	private int yearGracePeriod;
	
	//�¼�¼��ʶ
	private GUID monthId;
	
	//����¼��ʶ
	private GUID quarterId;
	
	//���¼��ʶ
	private GUID halfYearId;
	
	//���¼��ʶ
	private GUID yearId;
	
	
	//��Ŀ����
	private String  itemName;
	
	//����
	private String code;
	
	//�汾��������
	private int upLineNum;
	
	//���ϸ�����
	private int upFloatNum;
	
	//�ϸ�����������
	private double perUserCharge;
	
	//����Ԥ������
	private double smsDepositeLimited;
	
	//���ŵ���
	private double perSMSCharge;
	
	//����״̬
	private ProductStatus status;

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public GUID getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(GUID productItemId) {
		this.productItemId = productItemId;
	}

	public GUID getProductId() {
		return productId;
	}

	public void setProductId(GUID productId) {
		this.productId = productId;
	}

	public double getMonthCharge() {
		return monthCharge;
	}

	public void setMonthCharge(double monthCharge) {
		this.monthCharge = monthCharge;
	}

	public double getQuarterCharge() {
		return quarterCharge;
	}

	public void setQuarterCharge(double quarterCharge) {
		this.quarterCharge = quarterCharge;
	}

	public double getHalfYearCharge() {
		return halfYearCharge;
	}

	public void setHalfYearCharge(double halfYearCharge) {
		this.halfYearCharge = halfYearCharge;
	}

	public double getYearCharge() {
		return yearCharge;
	}

	public void setYearCharge(double yearCharge) {
		this.yearCharge = yearCharge;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getUpLineNum() {
		return upLineNum;
	}

	public void setUpLineNum(int upLineNum) {
		this.upLineNum = upLineNum;
	}

	public int getUpFloatNum() {
		return upFloatNum;
	}

	public void setUpFloatNum(int upFloatNum) {
		this.upFloatNum = upFloatNum;
	}

	public double getPerUserCharge() {
		return perUserCharge;
	}

	public void setPerUserCharge(double perUserCharge) {
		this.perUserCharge = perUserCharge;
	}

	public double getSmsDepositeLimited() {
		return smsDepositeLimited;
	}

	public void setSmsDepositeLimited(double smsDepositeLimited) {
		this.smsDepositeLimited = smsDepositeLimited;
	}

	public double getPerSMSCharge() {
		return perSMSCharge;
	}

	public void setPerSMSCharge(double perSMSCharge) {
		this.perSMSCharge = perSMSCharge;
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

	public GUID getMonthId() {
		return monthId;
	}

	public void setMonthId(GUID monthId) {
		this.monthId = monthId;
	}

	public GUID getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(GUID quarterId) {
		this.quarterId = quarterId;
	}

	public GUID getHalfYearId() {
		return halfYearId;
	}

	public void setHalfYearId(GUID halfYearId) {
		this.halfYearId = halfYearId;
	}

	public GUID getYearId() {
		return yearId;
	}

	public void setYearId(GUID yearId) {
		this.yearId = yearId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
