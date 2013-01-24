package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsPropertyDefine;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * ��Ʒ������ϸ���ݣ�����������Ϣ <br>
 * ��ѯ˵����<br>
 * (1)���ݷ���ID��ѯGoodsCategoryInfo����
 */
public class MaterialsCategoryInfoImpl implements MaterialsCategoryInfo {

	/**
	 * ����ID
	 */
	protected GUID id;

	/**
	 * ��������
	 */
	protected String name;
	
	/**
	 * ������
	 */
	protected String categoryNo;

	/**
	 * ����С��λ
	 */
	protected int scale; 

	/**
	 * ��Ʒ�������Զ����б�
	 */
	protected MaterialsPropertyDefine[] propertyDefines;

	/**
	 * ��ȡ����ID
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}  

	/**
	 * ��ȡ���Զ����б�
	 * 
	 * @return
	 */
	public MaterialsPropertyDefine[] getPropertyDefines() {
		return propertyDefines;
	}

	public void setId(GUID id){
    	this.id = id;
    }

	public void setName(String name){
    	this.name = name;
    } 

	public int getScale() {
		return GoodsScale.PSI_SCALE;
	}

	public void setScale(int scale) {
		this.scale = scale;
	} 

	public void setPropertyDefines(MaterialsPropertyDefine[] propertyDefines){
    	this.propertyDefines = propertyDefines;
    }

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
}
