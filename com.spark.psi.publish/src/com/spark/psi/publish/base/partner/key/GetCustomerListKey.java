package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.SortType;

/**
 * 
 * <p>��ѯ�ͻ��б�</p>
 *


 *
 
 
 */
public final class GetCustomerListKey extends GetPartnerListKey{

	public GetCustomerListKey(PartnerStatus PartnerStatus){
	    super(0, 0, false);
	    this.PartnerStatus = PartnerStatus;
	    this.sortType = SortType.Desc;
    }
	
	/**
	 * ��ѯ���пͻ�����ͨ��Ȩ�޹���
	 * @param PartnerStatus
	 * @param isShowAll
	 */
	public GetCustomerListKey(PartnerStatus PartnerStatus,boolean isShowAll){
	    this(PartnerStatus);
	    this.isShowAll = isShowAll;
    }
	
	private boolean isShowAll = false;
	
	/**
	 * �ͻ�״̬
	 */
	private PartnerStatus PartnerStatus;
	
	
	private SortField sortField = SortField.None;
	
	
	public PartnerStatus getPartnerStatus(){
    	return PartnerStatus;
    }

	public void setPartnerStatus(PartnerStatus PartnerStatus){
    	this.PartnerStatus = PartnerStatus;
    }
	

	public SortField getSortField(){
    	return sortField;
    }



	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }



	public boolean isShowAll(){
    	return isShowAll;
    }



	public void setShowAll(boolean isShowAll){
    	this.isShowAll = isShowAll;
    }



	/**
	 * �����ֶ�
	 */
	public static enum SortField {
		
		/**
		 * ������
		 */
		None("createDate"), //
		/** �ͻ����� */
		Name("namePY"), //
		/**
		 * �ͻ����
		 */
		CustomerNo("customerNo"),
		/** �����ܽ�� */
		TotalTradeAmount("totalSalesAmount"), //
		/** �����ܴ��� */
		TotalTradeCount("totalSalesTimes"), //
		/** ����������� */
		RecentTradeDate("lastSalesDate"), //
		/** Ӧ��Ӧ����� */
		BalanceAmount(""), //
		/** ���ö��*/
		CreditAmount("creditAmount"),
		/** �������� */
		CreditDay("accountPeriod"),
		/** ��ϵ������*/
		ContactPerson("linkmanNamePy"),
		/** ����Ա������*/
		SalesName("businessPerson"),
		Province("province"), City("city"), Address("collate_gbk(t.address)"); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}


	public static void main(String[] args){
	    System.out.println(SortField.valueOf("None").getFieldName());
    }
	
}
