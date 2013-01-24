/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.instorage.dbox;

import com.spark.psi.inventory.intf.constant.instorage.InstoStatus;
import com.spark.psi.inventory.intf.constant.instorage.InstoTypes;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;


/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-11
 */

public class DataBoxOfInsto{

//	private DetailType detType;
	private Instorage entity;
	/**
	 * 用于转换数据字典显示标题
	 */
	private InstoStatus statuss;
	/**
	 * 用于转换数据字典显示标题
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
	 * 新建页面，传入类型
	 */
//	public DataBoxOfInsto(DetailType detType){
//		this.detType = detType;
//	}
//
//	/**
//	 * 查看页面，传入类型
//	 */
//	public DataBoxOfInsto(Instorage entity, DetailType detType){
//		this.entity = entity;
//		this.detType = detType;
//	}
//	/**
//	 * 查看页面，传入类型
//	 */
//	public DataBoxOfInsto(Instorage entity, DetailType detType,instoState statuss,InstoTypes types){
//		this.entity = entity;
//		this.detType = detType;
//		this.statuss = statuss;
//		this.types = types;
//	}
//	/**
//	 * 查看页面，传入类型
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
