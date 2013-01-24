/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.config
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.config
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.config;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>减少销售经理已设置的客户信用额度</p>
 *


 *
 
 * @version 2012-5-17
 */

public class RemoveSalesManCreidtUesdTask extends SimpleTask{

	/**
	 * 销售经理员工id
	 */
	private GUID id;
	
	private double amount;
	
	
	public RemoveSalesManCreidtUesdTask(GUID id,double amount){
		this.id = id;
		this.amount = amount;
    }


	public GUID getId(){
    	return id;
    }


	public void setId(GUID id){
    	this.id = id;
    }


	public double getAmount(){
    	return amount;
    }


	public void setAmount(double amount){
    	this.amount = amount;
    }

	
	
}
