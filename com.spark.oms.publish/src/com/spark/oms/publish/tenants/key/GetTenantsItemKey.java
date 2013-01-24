package com.spark.oms.publish.tenants.key;

import com.spark.oms.publish.CycleType;
import com.spark.oms.publish.SortType;
import com.spark.oms.publish.TenantsType;


/**
 * �����ʽ�ͻ�����ʧ�ͻ��б�
 * GetTenantsItemKey & TenantsListIntf
 */
public class GetTenantsItemKey {
	
	public static enum SortField {
		
		TenantsNameColumn("Name"),//�⻧����
		firstSignDate("FirstSignOrderDate"),//�״�ǩԼʱ��
		serverTime("ServeMonth"),//����ʱ��(��)
		come("ReceiptAmount"),//�ۼ��տ���
		receipt("InvoiceAmount"),//�ۼƿ�Ʊ���
		RelatorColumn("BossName"),//��ϵ��
		SaleManager("SalerManager"),//�ͻ�����
		leaseAmount("LeaseAccountBalance"),//����˻����
		pieceAmount("PieceAccountBalance"),//"�����˻����"
		allTotal("LeaseAccountBalance");//"���˽��"

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
	
	/**
	 * ʱ���
	 */
	private CycleType cycleType;
	
	/**
	 * ���ڷ�Χ*
	 */
	private long date;
	
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * �ͻ�����:������Ա����:��ϵ������*
	 */
	private String key;
	
	/**
	 * �ͻ����*
	 */
	private TenantsType tenantsType;

	public CycleType getCycleType() {
		return cycleType;
	}

	public void setCycleType(CycleType cycleType) {
		this.cycleType = cycleType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TenantsType getTenantsType() {
		return tenantsType;
	}

	public void setTenantsType(TenantsType tenantsType) {
		this.tenantsType = tenantsType;
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