package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.base.materials.entity.MaterialsPropertyDefine;

/**
 * 分类属性定义对象
 * 
 */
public class MaterialsPropertyDefineImpl implements MaterialsPropertyDefine {

	/**
	 * 属性ID
	 */
	protected GUID id;

	/**
	 * 属性名称
	 */
	protected String name;

	/**
	 * 属性值的输入模式
	 */
	protected PropertyInputType valueInputMode;

	/**
	 * 对于下拉选择的属性，其可选值范围列表
	 */
	protected String[] options;

	/**
	 * 构造函数
	 * 
	 * @param id
	 *            属性ID
	 * @param name
	 *            属性名称
	 * @param valueInputMode
	 *            属性值的输入模式
	 * @param valueOptions
	 *            其可选值范围列表
	 */
	public MaterialsPropertyDefineImpl(GUID id, String name,
			PropertyInputType valueInputMode, String[] options) {
		this.id = id;
		this.name = name;
		this.valueInputMode = valueInputMode;
		this.options = options;
	}

	public MaterialsPropertyDefineImpl(){
	    // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public PropertyInputType getValueInputMode() {
		return valueInputMode;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getOptions() {
		return options;
	}

	public void setId(GUID id){
    	this.id = id;
    }

	public void setName(String name){
    	this.name = name;
    }

	public void setValueInputMode(PropertyInputType valueInputMode){
    	this.valueInputMode = valueInputMode;
    }

	public void setOptions(String[] options){
    	this.options = options;
    }
	
	

}
