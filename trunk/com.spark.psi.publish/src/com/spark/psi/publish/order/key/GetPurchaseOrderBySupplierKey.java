package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * ��ù�Ӧ�̵Ľ��׼�¼
 * @author zhoulijun
 *
 */
public class GetPurchaseOrderBySupplierKey extends LimitKey {
	
	private final GUID id;
	
	/**
	 * ��ѯָ����Ӧ�̵�δ��ɵĽ���
	 * @param id ��Ӧ��id
	 */
	public GetPurchaseOrderBySupplierKey(final GUID id){
		super(0,100,false);
		this.id = id;
	}
	
	/**
	 * ��ѯָ����Ӧ�̵�����ɵĽ��׼�¼
	 * @param id ��Ӧ��id
	 * @param isCompleted �Ƿ�ֻ��ѯ����ɵĽ��� Ĭ��Ϊfalse
	 */
	public GetPurchaseOrderBySupplierKey(final GUID id,boolean isCompleted){
		super(0,100,false);
		this.id = id;
		this.isCompleted = isCompleted;
	}

	public GUID getId(){
    	return id;
    }
	
	private QueryTerm queryTerm;
	
	private SortField sortField;

	private boolean isCompleted = false;
	
	public static enum SortField{
		/** ������� */
		OrderNumber("t.billsNo"),
		/** �������� */
		OrderType("t.type"),
		/** ������� */
		Amount("t.totalAmount"),
		/** �Ƶ��� */
		CreatePerson("t.createPerson"),
		/** �Ƶ����� */
		CreateDate("t.createDate"),
		/** ����״̬ */
		OrderStatus("t.status"),
		/** ������ */
		None("t.payDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	/**
	 * @param queryTerm the queryTerm to set
	 */
	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

	/**
	 * @return the sortField
	 */
	public SortField getSortField() {
		return sortField;
	}

	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	
}
