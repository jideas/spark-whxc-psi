package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.base.materials.entity.MaterialsPropertyDefine;

/**
 * �������Զ������
 * 
 */
public class MaterialsPropertyDefineImpl implements MaterialsPropertyDefine {

	/**
	 * ����ID
	 */
	protected GUID id;

	/**
	 * ��������
	 */
	protected String name;

	/**
	 * ����ֵ������ģʽ
	 */
	protected PropertyInputType valueInputMode;

	/**
	 * ��������ѡ������ԣ����ѡֵ��Χ�б�
	 */
	protected String[] options;

	/**
	 * ���캯��
	 * 
	 * @param id
	 *            ����ID
	 * @param name
	 *            ��������
	 * @param valueInputMode
	 *            ����ֵ������ģʽ
	 * @param valueOptions
	 *            ���ѡֵ��Χ�б�
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
