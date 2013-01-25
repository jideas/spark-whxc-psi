/**
 * 
 */
package com.spark.psi.publish;

/**
 * 
 *
 */
public enum InvoiceType {
	ValueAddesTax("01", "��ֵ˰��Ʊ"),
	Common("02", "��ͨ��Ʊ");
	
	private String code;
	private String name;
	
	private InvoiceType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static InvoiceType getInvoiceTypeByCode(String code) {
		if (code == null) return null;
		if (code.equals("01")) {
			return ValueAddesTax;
		} else if (code.equals("02")) {
			return Common;
		} else {
			return null;
		}
	}
}
