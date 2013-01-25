package com.spark.psi.publish.base.start.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.SysParamKey;

/**
 * <p>新增或更新租户系统参数</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-3
 */

public class SaveOrUpdateTenantsSysParamTask extends SimpleTask{

	/**系统参数key*/
	private SysParamKey key;

	/**系统参数值*/
	private Boolean yes;

	/** 
	 *构造方法
	 *@param key
	 *@param yes
	 */
	public SaveOrUpdateTenantsSysParamTask(SysParamKey key, Boolean yes){
		super();
		this.key = key;
		this.yes = yes;
	}

	public SysParamKey getKey(){
		return key;
	}

	public void setKey(SysParamKey key){
		this.key = key;
	}

	public Boolean getYes(){
		return yes;
	}

	public void setYes(Boolean yes){
		this.yes = yes;
	}

}
