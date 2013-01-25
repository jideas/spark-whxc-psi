/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.finance.invoice.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;


/**
 * ����Ӧ�����¼�б��ѯKEY
 */
public class GetRetailSubmitingListKey extends LimitKey {

	/**
	 * �����ֶ�
	 */
	private SortField sortField;

	/**
	 * ���캯��
	 */
	public GetRetailSubmitingListKey(int offset,int count,boolean queryTotal) {
		super(offset, count, queryTotal); // ����ҳ
	}

	/**
	 * @return the sortField
	 */
	public SortField getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set
	 */
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * �����ֶ�
	 */
	public static enum SortField {

		None(""), //
		SalesName("saleEmpName"), //
		BeginDate("receiptDate"), //
		Amount("shouldMoney"),
		CardRecordCount("shouldCardCount"),
		CardRecordAmount("shouldCardMoney"),
		; //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
