/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.intf.partner.task.supplier
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.intf.partner.task.supplier
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * 编辑供应商Task
 * </p>
 * 新增供应商 快速新增供应商 编辑供应商
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
