package com.spark.psi.publish;

/**
 * 报损单状态
 * @author Administrator
 *
 */
public enum ReportLossStatus {
	Submitting("01", "待提交"),
	Approvling("02", "待审批"),
	AccountApprovling("03", "待财务审批"),
	Finished("04", "已完成"),
	Deied("05", "已驳回");
	
	private String code;
	private String title;
	private ReportLossStatus(String code, String title) {
		this.code = code;
		this.title = title;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public static ReportLossStatus getStatusByCode(String code) {
		if (Submitting.getCode().equals(code)) {
			return Submitting;
		} else if (Approvling.getCode().equals(code)) {
			return Approvling;
		} else if (AccountApprovling.getCode().equals(code)) {
			return AccountApprovling;
		} else if (Finished.getCode().equals(code)) {
			return Finished;
		} else if (Deied.getCode().equals(code)) {
			return Deied;
		}
		return null;
	}
}
