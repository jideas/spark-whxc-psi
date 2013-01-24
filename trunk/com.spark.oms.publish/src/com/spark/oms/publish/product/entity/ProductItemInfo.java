package com.spark.oms.publish.product.entity;

import java.io.Serializable;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductStatus;

public class ProductItemInfo implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;

	//产品条目标识
	private GUID productItemId;
	
	//产品标识
	private GUID productId;
	
	//月计价
	private double monthCharge;
	
	//季计价
	private double quarterCharge;
	
	//半年计价
	private double halfYearCharge;
	
	//年计价
	private double yearCharge;
	
	
	//月宽限期
	private int monthGracePeriod;
	
	//季宽限期
	private int quarterGracePeriod;
	
	//半宽限期
	private int halfYearGracePeriod;
	
	//年宽限期
	private int yearGracePeriod;
	
	//月记录标识
	private GUID monthId;
	
	//季记录标识
	private GUID quarterId;
	
	//半记录标识
	private GUID halfYearId;
	
	//年记录标识
	private GUID yearId;
	
	
	//条目名称
	private String  itemName;
	
	//编码
	private String code;
	
	//版本人数上限
	private int upLineNum;
	
	//可上浮人数
	private int upFloatNum;
	
	//上浮人数单单价
	private double perUserCharge;
	
	//短信预存上限
	private double smsDepositeLimited;
	
	//短信单价
	private double perSMSCharge;
	
	//在售状态
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
