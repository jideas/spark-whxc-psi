/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.common.goods.intf.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-9       汤成国        
 * ============================================================*/

package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.utils.GoodsConstant.PropertyInputType;

/**
 * <p>子属性实体类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 汤成国
 * @version 2011-11-9
 */

public class SubGoodsProperty {
	private String subPropertyName; // 子属性名称
	private String subPropertyValue; // 子属性值
	private PropertyInputType propertyInputType; // 子属性录入类型
	private GUID goodsTypePropertyGuid; // 商品分类属性GUID
	/**
     * @return 商品分类属性GUID
     */
    public GUID getGoodsTypePropertyGuid(){
    	return goodsTypePropertyGuid;
    }
	/**
     * @param goodsTypePropertyGuid 商品分类属性GUID
     */
    public void setGoodsTypePropertyGuid(GUID goodsTypePropertyGuid){
    	this.goodsTypePropertyGuid = goodsTypePropertyGuid;
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
