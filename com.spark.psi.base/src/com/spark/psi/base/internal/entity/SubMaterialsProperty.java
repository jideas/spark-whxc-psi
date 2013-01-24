package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.utils.MaterialsConstant.PropertyInputType;

/**
 * <p>子属性实体类</p>
 *
 */

public class SubMaterialsProperty {
	private String subPropertyName; // 子属性名称
	private String subPropertyValue; // 子属性值
	private PropertyInputType propertyInputType; // 子属性录入类型
	private GUID materialsTypePropertyGuid; // 商品分类属性GUID
	/**
     * @return 商品分类属性GUID
     */
    public GUID getMaterialsTypePropertyGuid(){
    	return materialsTypePropertyGuid;
    }
	/**
     * @param materialsTypePropertyGuid 商品分类属性GUID
     */
    public void setMaterialsTypePropertyGuid(GUID materialsTypePropertyGuid){
    	this.materialsTypePropertyGuid = materialsTypePropertyGuid;
    }
	/**
     * @return 子属性名称
     */
    public String getSubPropertyName(){
    	return subPropertyName;
    }
	/**
     * @param subPropertyName 子属性名称
     */
    public void setSubPropertyName(String subPropertyName){
    	this.subPropertyName = subPropertyName;
    }
	/**
     * @return 子属性值
     */
    public String getSubPropertyValue(){
    	return subPropertyValue;
    }
	/**
     * @param subPropertyValue 子属性值
     */
    public void setSubPropertyValue(String subPropertyValue){
    	this.subPropertyValue = subPropertyValue;
    }
	public PropertyInputType getPropertyInputType(){
    	return propertyInputType;
    }
	public void setPropertyInputType(PropertyInputType propertyInputType){
    	this.propertyInputType = propertyInputType;
    }
    
    

}
