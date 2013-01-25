package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 创建商品分类
 * 
 */
public class CreateGoodsCategoryTask extends SimpleTask {

	/**
	 * 分类ID（创建时传空，创建完毕后写回）
	 */
	private GUID id;

	/**
	 * 分类名称
	 */
	private String name;

	/**
	 * 分类编号
	 */
	private String categoryNo;
	
	/**
	 * 父分类ID
	 */
	private GUID parentId;

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
	public CreateGoodsCategoryTask(String name, String categoryNo, GUID parentId) {
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
