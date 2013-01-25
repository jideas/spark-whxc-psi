package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 验证材料名称在指定分类下是否存在
 * 查询方法 context.find(Boolean.class,ValidationGoodsNameIsExist)
 */
public class ValidationMaterialsIsExistTask extends SimpleTask{
	
	public enum ErrType {
		Name,
		Code,
		SPECANDNUMBER,
		All
	}
	
	private GUID id;
	
	private boolean exist;
	
	private ErrType errType;
	
	private GUID categoryId;
	
	private String name;
	
	private String code;
	
	private GUID materialItemId;
	
	// 商品规格
	private String spec;
	
	public ValidationMaterialsIsExistTask(GUID id,GUID categoryId,String name,String code){
	    this.categoryId = categoryId;
	    this.name = name;
	    this.code = code;
	    this.id = id;
    }

	public GUID getCategoryId(){
    	return categoryId;
    }

	public String getName(){
    	return name;
    }

	public String getCode(){
    	return code;
    }

	public boolean isExist(){
    	return exist;
    }

	public void setExist(boolean exist){
    	this.exist = exist;
    }

	public ErrType getErrType(){
    	return errType;
    }

	public void setErrType(ErrType errType){
    	this.errType = errType;
    }

	public GUID getId(){
    	return id;
    }

	public GUID getMaterialItemId() {
		return materialItemId;
	}

	public void setMaterialItemId(GUID materialItemId) {
		this.materialItemId = materialItemId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
}
