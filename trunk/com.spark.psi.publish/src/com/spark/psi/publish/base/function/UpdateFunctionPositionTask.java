/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.function
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.function
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.function;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改用户功能模块定位</p>
 *


 *
 
 * @version 2012-4-18
 */

public class UpdateFunctionPositionTask extends SimpleTask{

	private GUID userId;

	private String functionId;

	private int xindex, yindex;

	private boolean putMain;

	public UpdateFunctionPositionTask(GUID userId, String functionId){
		this.userId = userId;
		this.functionId = functionId;
	}

	public GUID getUserId(){
		return userId;
	}

	public void setUserId(GUID userId){
		this.userId = userId;
	}

	public String getFunctionId(){
		return functionId;
	}

	public void setFunctionId(String functionId){
		this.functionId = functionId;
	}

	public int getXindex(){
		return xindex;
	}

	public void setXindex(int xindex){
		this.xindex = xindex;
	}

	public int getYindex(){
		return yindex;
	}

	public void setYindex(int yindex){
		this.yindex = yindex;
	}

	public boolean isPutMain(){
		return putMain;
	}

	public void setPutMain(boolean putMain){
		this.putMain = putMain;
	}

}
