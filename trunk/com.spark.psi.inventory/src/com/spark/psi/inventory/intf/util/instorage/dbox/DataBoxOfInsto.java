/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.util
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.instorage.dbox;

import com.spark.psi.inventory.intf.constant.instorage.InstoStatus;
import com.spark.psi.inventory.intf.constant.instorage.InstoTypes;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;


/**
 * <p>TODO ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-11
 */

public class DataBoxOfInsto{

//	private DetailType detType;
	private Instorage entity;
	/**
	 * ����ת�������ֵ���ʾ����
	 */
	private InstoStatus statuss;
	/**
	 * ����ת�������ֵ���ʾ����
	 */
	private InstoTypes types;
	
	private boolean isCloseAll = false;
	
	
	/**
     * @return the isCloseAll
     */
    public boolean isCloseAll(){
    	return isCloseAll;
    }

	/**
     * @param isCloseAll the isCloseAll to set
     */
    public void setCloseAll(boolean isCloseAll){
    	this.isCloseAll = isCloseAll;
    }

	public DataBoxOfInsto(){
	}

	/**
	 * �½�ҳ�棬��������
	 */
//	public DataBoxOfInsto(DetailType detType){
//		this.detType = detType;
//	}
//
//	/**
//	 * �鿴ҳ�棬��������
//	 */
//	public DataBoxOfInsto(Instorage entity, DetailType detType){
//		this.entity = entity;
//		this.detType = detType;
//	}
//	/**
//	 * �鿴ҳ�棬��������
//	 */
//	public DataBoxOfInsto(Instorage entity, DetailType detType,instoState statuss,InstoTypes types){
//		this.entity = entity;
//		this.detType = detType;
//		this.statuss = statuss;
//		this.types = types;
//	}
//	/**
//	 * �鿴ҳ�棬��������
//	 */
//	public DataBoxOfInsto(Instorage entity, DetailType detType,instoState statuss,InstoTypes types,boolean isCloseAll){
//		this.entity = entity;
//		this.detType = detType;
//		this.statuss = statuss;
//		this.types = types;
//		this.isCloseAll = isCloseAll;
//	}
	/**
     * @return the statuss
     */
    public InstoStatus getStatus(){
    	return statuss;
    }

	/**
     * @return the types
     */
    public InstoTypes getTypes(){
    	return types;
    }

	/**
     * @param types the types to set
     */
    public void setTypes(InstoTypes types){
    	this.types = types;
    }

	/**
     * @param statuss the statuss to set
     */
    public void setStatuss(InstoStatus statuss){
    	this.statuss = statuss;
    }

	/**
	 * @return the detType
	 */
//	public DetailType getDetType(){
//		return detType;
//	}
//
//	/**
//	 * @param detType the detType to set
//	 */
//	public void setDetType(DetailType detType){
//		this.detType = detType;
//	}

	/**
	 * @return the entity
	 */
	public Instorage getEntity(){
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Instorage entity){
		this.entity = entity;
	}

}
