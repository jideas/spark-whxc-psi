package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.PromotionStatus;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;

/**
 * 
 * <p>���Ա��δȷ�ϴ����б�</p>
 *


 *
 
 * @version 2012-3-6
 */
public class GetPromotionListKey extends LimitKey{
	
	private PromotionStatus[] promotionstatuss; //����״̬

	private QueryTerm queryTerm;  
	
	private QueryScope queryScope;
	
	private SortField sortField;
	
	
	public QueryTerm getQueryTerm(){
    	return queryTerm;
    }

	public void setQueryTerm(QueryTerm queryTerm){
    	this.queryTerm = queryTerm;
    }

	public QueryScope getQueryScope(){
    	return queryScope;
    }

	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    }

	public SortField getSortField(){
    	return sortField;
    }

	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }

	public void setPromotionStatus(PromotionStatus... promotionstatus){
    	this.promotionstatuss = promotionstatus;
    }

	public GetPromotionListKey(int offset, int count,
            PromotionStatus... promotionstatus)
    {
	    super(offset, count, true);
	    this.promotionstatuss = promotionstatus;
    }

	public GetPromotionListKey(int offset, int count)
    {
	    super(offset, count, true);
    }

	public PromotionStatus[] getPromotionStatus(){
    	return promotionstatuss;
    }


	public static enum SortField{
//		//��Ʒ��������
//		sortColumn = "1";
//		//��Ʒ��������
//		sortColumn = "2";
//		//��ʼ��������
//		sortColumn = "3";
//		//������������
//		sortColumn = "4";
//		//״̬/����������
//		sortColumn = "5";
//		//��������
//		sortColumn = "6";
//		//��������
//		sortColumn = "7";
//		//ԭ��
//		sortColumn = "8";
//		//��������
//		sortColumn = "9";
		/** ��Ʒ���� */
		goodsName("t.goodsName"),
		/** ��Ʒ���� */
		goodsProperty("t.goodsProperties"),
		/** ������Ʒ���� */
		promotionCount("t.promotionCount"),
		/** ԭʼ���� */
		originalPrice("t.price_goods"),
		/** �������� */
		promotionPrice("t.price_promotion"),
		/** ��ʼ���� */
		startDate("t.beginDate"),
		/** �������� */
		endDate("t.endDate"),
		/** �������� */
		saleCount("t.saledCount"),
		/** ״̬*/
		PromotionStatus("t.status"),
		/** ������*/
		creator("t.creator"),		
		/** ������ */
		None("t.goodsName");

		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }

	}
	
}
