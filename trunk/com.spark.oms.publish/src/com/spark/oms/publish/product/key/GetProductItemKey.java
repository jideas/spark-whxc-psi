package com.spark.oms.publish.product.key;
/**
 * ��ȡ��Ʒ�б�
 * GetProductItemKey & ProductListIntf
 */
public class GetProductItemKey {
	/**
	 * ��Ʒ���
	 */
	private String category;
		
	/**
	 * ��Ʒ����:����,ϵ��
	 */
	private String key;
	
	/**
	 * ��Ʒ״̬
	 */
	private String status;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
