package com.spark.oms.publish.receipt.key;

/**
 * 高级搜索——收支历史
 * @author gonghaitao
 *
 */
public class GetReceiptItemAdvancedSearchKey {

	private long start;
	private long end;
	private String tenant;
	private String record;
	private String pay;
	private String confirmor;

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getConfirmor() {
		return confirmor;
	}

	public void setConfirmor(String confirmor) {
		this.confirmor = confirmor;
	}
}