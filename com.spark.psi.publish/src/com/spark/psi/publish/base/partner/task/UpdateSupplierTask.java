/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.intf.partner.task.supplier
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.intf.partner.task.supplier
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * �༭��Ӧ��Task
 * </p>
 * ������Ӧ�� ����������Ӧ�� �༭��Ӧ��
 * 
 * 
 * 
 * 
 * 
 * @version 2012-3-2
 */

public final class UpdateSupplierTask extends
		UpdatePartnerTask<UpdatePartnerTask.Method> {

	private String type;

	private String noticeAddress;

	private String noticePostcode;

	private String vatNo;

	private ItemOfSupplier[] items;

	public ItemOfSupplier[] getItems() {
		return items;
	}

	public void setItems(ItemOfSupplier[] items) {
		this.items = items;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNoticeAddress() {
		return noticeAddress;
	}

	public void setNoticeAddress(String noticeAddress) {
		this.noticeAddress = noticeAddress;
	}

	public String getNoticePostcode() {
		return noticePostcode;
	}

	public void setNoticePostcode(String noticePostcode) {
		this.noticePostcode = noticePostcode;
	}
}
