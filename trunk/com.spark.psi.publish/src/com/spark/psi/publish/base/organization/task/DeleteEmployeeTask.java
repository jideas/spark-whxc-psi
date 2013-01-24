/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.organization.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-7-13    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.organization.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-7-13    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>删除员工</p>
 *


 *
 
 * @version 2012-7-13
 */

public class DeleteEmployeeTask extends SimpleTask{
	
	private GUID[] empId;
	
	public DeleteEmployeeTask(GUID... empId){
		this.empId = empId;
    }

	public GUID[] getEmpId(){
    	return empId;
    }
	
}
