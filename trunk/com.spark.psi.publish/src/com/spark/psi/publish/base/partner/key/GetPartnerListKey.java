/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.customer.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       ������        
 * ============================================================*/

package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryScope;

/**
 * <p>�ͻ���Ӧ��ά���б��ѯ����</p> 
 * @version 2011-11-11
 */

public abstract class GetPartnerListKey extends LimitKey{
	
	public GetPartnerListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }

	private String name;//�ͻ�����

    private String linkman;//��ϵ��
    
    private String salesman;//������
	
	private Boolean isOverCreditAmount;//Ӧ���������ö��
	
	private Boolean isAccountPeriod;//������ 
	
	private Boolean isNearAccountPeriod;//�ӽ����� 
	
	private Integer nearAccountPeriodDay;//�ӽ���������
	
	private Double salesTotalAmountBegin;//��ʼ�����ܶ�
	
	private Double salesTotalAmountEnd;//��ֹ�����ܶ�
	
	private Integer salesCountBegin;//��ʼ���۴���
	
	private Integer salesCountEnd;//��ֹ���۴���
	
	private Double balanceAmountBegin;//��ʼӦ�����
	
	private Double balanceAmountEnd;//��ֹӦ�����
	
	private Double creditAmountBegin;//��ʼ���ö��
	
	private Double creditAmountEnd;//��ֹ���ö��
	
	

	/**
	 * ��ѯ��Χ 
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
