/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.common.goods.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-9       ���ɹ�        
 * ============================================================*/

package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.utils.GoodsConstant.PropertyInputType;

/**
 * <p>������ʵ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ���ɹ�
 * @version 2011-11-9
 */

public class SubGoodsProperty {
	private String subPropertyName; // ����������
	private String subPropertyValue; // ������ֵ
	private PropertyInputType propertyInputType; // ������¼������
	private GUID goodsTypePropertyGuid; // ��Ʒ��������GUID
	/**
     * @return ��Ʒ��������GUID
     */
    public GUID getGoodsTypePropertyGuid(){
    	return goodsTypePropertyGuid;
    }
	/**
     * @param goodsTypePropertyGuid ��Ʒ��������GUID
     */
    public void setGoodsTypePropertyGuid(GUID goodsTypePropertyGuid){
    	this.goodsTypePropertyGuid = goodsTypePropertyGuid;
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
