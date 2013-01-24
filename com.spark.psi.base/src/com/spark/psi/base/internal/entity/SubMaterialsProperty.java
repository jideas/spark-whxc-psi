package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.utils.MaterialsConstant.PropertyInputType;

/**
 * <p>������ʵ����</p>
 *
 */

public class SubMaterialsProperty {
	private String subPropertyName; // ����������
	private String subPropertyValue; // ������ֵ
	private PropertyInputType propertyInputType; // ������¼������
	private GUID materialsTypePropertyGuid; // ��Ʒ��������GUID
	/**
     * @return ��Ʒ��������GUID
     */
    public GUID getMaterialsTypePropertyGuid(){
    	return materialsTypePropertyGuid;
    }
	/**
     * @param materialsTypePropertyGuid ��Ʒ��������GUID
     */
    public void setMaterialsTypePropertyGuid(GUID materialsTypePropertyGuid){
    	this.materialsTypePropertyGuid = materialsTypePropertyGuid;
    }
	/**
     * @return ����������
     */
    public String getSubPropertyName(){
    	return subPropertyName;
    }
	/**
     * @param subPropertyName ����������
     */
    public void setSubPropertyName(String subPropertyName){
    	this.subPropertyName = subPropertyName;
    }
	/**
     * @return ������ֵ
     */
    public String getSubPropertyValue(){
    	return subPropertyValue;
    }
	/**
     * @param subPropertyValue ������ֵ
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
