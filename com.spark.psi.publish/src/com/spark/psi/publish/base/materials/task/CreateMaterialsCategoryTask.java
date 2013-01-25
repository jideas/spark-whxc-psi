package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �������Ϸ���
 * 
 */
public class CreateMaterialsCategoryTask extends SimpleTask {

	/**
	 * ����ID������ʱ���գ�������Ϻ�д�أ�
	 */
	private GUID id;

	/**
	 * ��������
	 */
	private String name;

	/**
	 * ������ID
	 */
	private GUID parentId;
	
	/**
	 * ������
	 */
	private String categoryNo;

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
	public CreateMaterialsCategoryTask(String name,String categoryNo, GUID parentId) {
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
	public CreateMaterialsCategoryTask(String name,String categoryNo) {
		this.name = name;
		this.parentId = null;
		this.categoryNo = categoryNo;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getParentId() {
		return parentId;
	}

	public String getCategoryNo() {
		return categoryNo;
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

}
