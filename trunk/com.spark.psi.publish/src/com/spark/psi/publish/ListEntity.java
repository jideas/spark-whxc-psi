package com.spark.psi.publish;

import java.util.List;

/**
 * Listʵ�壬�����洢����List�����������͵�����
 * 
 * @version 2012-2-21
 */
public class ListEntity<Item> {

	/**
	 * ����б�
	 */
	private List<Item> itemList;

	/**
	 * ���������Ϊ����ѯ�������򷵻�0��
	 */
	private long totalCount;

	/**
	 * ���캯��
	 * 
	 * @param dataList
	 * @param totalCount
	 */
	public ListEntity(List<Item> dataList, long totalCount) {
		this.itemList = dataList;
		this.totalCount = totalCount;
	}

	/**
	 * ��ȡ�б�
	 * 
	 * @return
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

}
