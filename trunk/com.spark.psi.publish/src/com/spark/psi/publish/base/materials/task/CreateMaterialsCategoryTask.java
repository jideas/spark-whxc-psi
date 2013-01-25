package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 创建材料分类
 * 
 */
public class CreateMaterialsCategoryTask extends SimpleTask {

	/**
	 * 分类ID（创建时传空，创建完毕后写回）
	 */
	private GUID id;

	/**
	 * 分类名称
	 */
	private String name;

	/**
	 * 父分类ID
	 */
	private GUID parentId;
	
	/**
	 * 分类编号
	 */
	private String categoryNo;

	/**
	 * 构造函数
	 * 
	 * @param id
	 *            分类id
	 * @param name
	 *            分类名称
	 * @param parentId
	 *            父分类ID
	 */
	public CreateMaterialsCategoryTask(String name,String categoryNo, GUID parentId) {
		this.name = name;
		this.parentId = parentId;
		this.categoryNo = categoryNo;
	}

	/**
	 * 创建一个根类型
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
