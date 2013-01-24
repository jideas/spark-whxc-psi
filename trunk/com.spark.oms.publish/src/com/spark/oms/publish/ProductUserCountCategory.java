package com.spark.oms.publish;

/**
 * ��Ʒ�û��������
 * @author gonght
 */
public enum ProductUserCountCategory {

	one("1"), five("5"), ten("10"), twenty("20"), fifty("50"), fiftyMore("50+"), nothing("��");
	
	private String code;

	private ProductUserCountCategory(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static ProductUserCountCategory getProductUserCountCategoryByCode(String code) {
		for (ProductUserCountCategory item : ProductUserCountCategory.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		throw new IllegalArgumentException(code + "����һ����ȷ�Ĳ�Ʒ�û��������ö��");
	}
}