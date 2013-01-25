package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.SortType;


/***
 * 
 * <p>��ѯ��Ӧ���б�</p>
 * 


 *
 
 
 */
public class GetSupplierListKey extends GetPartnerListKey{

	private boolean queryAll;
	
	public GetSupplierListKey(){
		super(0,20,false);
	    this.sortType = SortType.Desc;
	}
	
	public GetSupplierListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }
	
	

	
	private SortField sortField = SortField.None;
	
	

	public SortField getSortField(){
    	return sortField;
    }



	public boolean isQueryAll(){
    	return queryAll;
    }

	public void setQueryAll(boolean queryAll){
    	this.queryAll = queryAll;
    }

	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }



	/**
	 * �����ֶ�
	 */
	public static enum SortField {  
		/**
		 * ������
		 */
		None("createDate"), //
		Number("supplierNo"),
		SupplierType("supplierType"),
		TaxRate("taxRate"),
		SupplierNo("supplierNo"),
		/** �ͻ����� */
		Name("namePY"), //
		/** �����ܽ�� */
		TotalTradeAmount("totalPurchaseAmount"), //
		/** �����ܴ��� */
		TotalTradeCount("totalPurchaseTimes"), //
		/** ����������� */
		RecentTradeDate("lastPurchaseDate"), //
		/** Ӧ��Ӧ����� */
		BalanceAmount("balanceAmount"), //
		/** ��ϵ������*/
		ContactPerson("linkmanName"); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

	
}
