package com.spark.oms.publish.receipt.key;

import com.spark.oms.publish.DrawBillStatus;
import com.spark.oms.publish.SortType;


/**
 * ��Ʊ
 * GetTenantsDrawFeeItemKey & DrawBillItem
 *
 */
public class GetTenantsDrawFeeItemKey {
	
	public static enum SortField {
		
		DrawDate("DrawDate"),//��Ʊ����
		TenantsName("TenantsName"),//�⻧����
		DrawAmount("DrawAmount"),//��Ʊ���
		DrawName("DrawName");//��Ʊ��

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}

	private SortField sortField;

	private SortType sortType;
	
	private DrawBillStatus drawBillstatus;

	/**
	 * ��ѯ����
	 */
	private String serachkey;

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}
	

	public DrawBillStatus getDrawBillStatus() {
		return drawBillstatus;
	}

	public void setDrawBillStatus(DrawBillStatus drawBillstatus) {
		this.drawBillstatus = drawBillstatus;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
}
