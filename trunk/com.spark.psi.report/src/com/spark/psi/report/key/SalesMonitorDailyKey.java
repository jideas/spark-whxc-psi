/**
 * 
 */
package com.spark.psi.report.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.utils.Condition;

/**
 *
 */
public class SalesMonitorDailyKey{

	private GUID userId, deptId;

	public void setConditions(Condition... cons){
		if(null == cons){
			return;
		}
		for(Condition con : cons){
			if(con.getConditionColumn().equals("DepartmentId")){
				this.deptId = (GUID)con.getValue();
			}
		}
	}

	/**
	 * @return the userId
	 */
	public GUID getUserId(){
		return userId;
	}

	/**
	 * @return the deptId
	 */
	public GUID getDeptId(){
		return deptId;
	}
}
