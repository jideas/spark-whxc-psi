package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ʒ����
 * 
 */
public class CreateGoodsCategoryTask extends SimpleTask {

	/**
	 * ����ID������ʱ���գ�������Ϻ�д�أ�
	 */
	private GUID id;

	/**
	 * ��������
	 */
	private String name;

	/**
	 * ������
	 */
	private String categoryNo;
	
	/**
	 * ������ID
	 */
	private GUID parentId;

	/**
	 * ���캯��
	 * 
	 * @param id
	 *            ����id
	 * @param name
	 *            ��������
	 * @param parentId
	 *            ������ID
	 */
	public CreateGoodsCategoryTask(String name, String categoryNo, GUID parentId) {
		this.name = name;
		this.parentId = parentId;
		this.categoryNo = categoryNo;
	}

	/**
	 * ����һ��������
	 * 
	 * @param id
	 * @param name
	 */
	public CreateGoodsCategoryTask(String name, String categoryNo) {
		this.name = name;
		this.categoryNo = categoryNo;
		this.parentId = null;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	public String getCategoryNo() {
		return this.categoryNo;
	}
	
	

}
