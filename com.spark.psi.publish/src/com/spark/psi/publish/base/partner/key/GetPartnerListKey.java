/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.customer.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       闫永红        
 * ============================================================*/

package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryScope;

/**
 * <p>客户供应商维护列表查询对象</p> 
 * @version 2011-11-11
 */

public abstract class GetPartnerListKey extends LimitKey{
	
	public GetPartnerListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }

	private String name;//客户名称

    private String linkman;//联系人
    
    private String salesman;//销售人
	
	private Boolean isOverCreditAmount;//应收余额超过信用额度
	
	private Boolean isAccountPeriod;//超账期 
	
	private Boolean isNearAccountPeriod;//接近账期 
	
	private Integer nearAccountPeriodDay;//接近账期天数
	
	private Double salesTotalAmountBegin;//起始销售总额
	
	private Double salesTotalAmountEnd;//截止销售总额
	
	private Integer salesCountBegin;//起始销售次数
	
	private Integer salesCountEnd;//截止销售次数
	
	private Double balanceAmountBegin;//起始应收余额
	
	private Double balanceAmountEnd;//截止应收余额
	
	private Double creditAmountBegin;//起始信用额度
	
	private Double creditAmountEnd;//截止信用额度
	
	

	/**
	 * 查询范围 
	 */
	private QueryScope queryScope; 

	public String getName(){
    	return name;
    }

	public void setName(String name){
    	this.name = name;
    } 

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getSalesman(){
    	return salesman;
    }

	public void setSalesman(String salesman){
    	this.salesman = salesman;
    }

	public Boolean getIsOverCreditAmount(){
    	return isOverCreditAmount;
    }

	public void setIsOverCreditAmount(Boolean isOverCreditAmount){
    	this.isOverCreditAmount = isOverCreditAmount;
    }

	public Boolean getIsAccountPeriod(){
    	return isAccountPeriod;
    }

	public void setIsAccountPeriod(Boolean isAccountPeriod){
    	this.isAccountPeriod = isAccountPeriod;
    }

	public Boolean getIsNearAccountPeriod(){
    	return isNearAccountPeriod;
    }

	public void setIsNearAccountPeriod(Boolean isNearAccountPeriod){
    	this.isNearAccountPeriod = isNearAccountPeriod;
    }

	public Integer getNearAccountPeriodDay(){
    	return nearAccountPeriodDay;
    }

	public void setNearAccountPeriodDay(Integer nearAccountPeriodDay){
    	this.nearAccountPeriodDay = nearAccountPeriodDay;
    }

	public Double getSalesTotalAmountBegin(){
    	return salesTotalAmountBegin;
    }

	public void setSalesTotalAmountBegin(Double salesTotalAmountBegin){
    	this.salesTotalAmountBegin = salesTotalAmountBegin;
    }

	public Double getSalesTotalAmountEnd(){
    	return salesTotalAmountEnd;
    }

	public void setSalesTotalAmountEnd(Double salesTotalAmountEnd){
    	this.salesTotalAmountEnd = salesTotalAmountEnd;
    }

	public Integer getSalesCountBegin(){
    	return salesCountBegin;
    }

	public void setSalesCountBegin(Integer salesCountBegin){
    	this.salesCountBegin = salesCountBegin;
    }

	public Integer getSalesCountEnd(){
    	return salesCountEnd;
    }

	public void setSalesCountEnd(Integer salesCountEnd){
    	this.salesCountEnd = salesCountEnd;
    }

	public Double getBalanceAmountBegin(){
    	return balanceAmountBegin;
    }

	public void setBalanceAmountBegin(Double balanceAmountBegin){
    	this.balanceAmountBegin = balanceAmountBegin;
    }

	public Double getBalanceAmountEnd(){
    	return balanceAmountEnd;
    }

	public void setBalanceAmountEnd(Double balanceAmountEnd){
    	this.balanceAmountEnd = balanceAmountEnd;
    }

	public Double getCreditAmountBegin(){
    	return creditAmountBegin;
    }

	public void setCreditAmountBegin(Double creditAmountBegin){
    	this.creditAmountBegin = creditAmountBegin;
    }

	public Double getCreditAmountEnd(){
    	return creditAmountEnd;
    }

	public void setCreditAmountEnd(Double creditAmountEnd){
    	this.creditAmountEnd = creditAmountEnd;
    }

	public QueryScope getQueryScope(){
    	return queryScope;
    }

	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    } 
}
