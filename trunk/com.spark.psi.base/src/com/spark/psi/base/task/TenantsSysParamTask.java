/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.tenants.intf.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       周利均        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.tenants.intf.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       周利均        
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.SysParamKey;

/**
 * <p>更新租户系统参数</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 周利均
 * @version 2011-11-17
 */

public class TenantsSysParamTask extends SimpleTask{
	
	private SysParamKey key;

	private Boolean yes;
	
	/**
	 * 修改指定参数  默认值 为 false
	 * @param key 参数枚举
	 */
	public TenantsSysParamTask(SysParamKey key){
		this.key = key;
	}
	
	/**
	 * 修改指定参数
	 * @param key 参数枚举
	 * @param value 值 true or false
	 */
	public TenantsSysParamTask(SysParamKey key,boolean value){
		this.key = key;
		this.yes = value;
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
