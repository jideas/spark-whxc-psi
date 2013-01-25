package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * 
 * <p>��ѯ�ͻ������۽��׼�¼</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2012-2-22
 */
public class GetSalesOrderByCustomerKey extends LimitKey{
	
	private final GUID id;
	
	/**
	 * ��ѯָ���ͻ���δ��ɵĽ���
	 * @param id �ͻ�id
	 */
	public GetSalesOrderByCustomerKey(final GUID id){
		super(0,100,false);
		this.id = id;
	}
	
	/**
	 * ��ѯָ���ͻ�������ɵĽ��׼�¼
	 * @param id �ͻ�id
	 * @param isCompleted �Ƿ�ֻ��ѯ����ɵĽ��� Ĭ��Ϊfalse
	 */
	public GetSalesOrderByCustomerKey(final GUID id,boolean isCompleted){
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
	 * @return the sortField
	 */
	public SortField getSortField() {
		return sortField;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
}
