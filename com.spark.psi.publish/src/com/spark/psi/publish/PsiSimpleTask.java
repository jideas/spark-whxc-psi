/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-22    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-22    jiuqi
 * ============================================================*/

package com.spark.psi.publish;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.ValidationReturn.ErrorLevel;

/**
 * <p>封装了返回验证结果的SimpleTask</p>
 *


 *
 
 * @version 2012-3-22
 */

public class PsiSimpleTask<E> extends SimpleTask{
	
	
	@StructField
	private boolean ignoredWarning = false;  //是否忽略警告异常	
	
	@StructField
	private boolean success = true; //操作返回结果 成功 or 失败

	public boolean isIgnoredWarning(){
    	return ignoredWarning;
    }

	public void setIgnoredWarning(boolean ignoredWarning){
    	this.ignoredWarning = ignoredWarning;
    }

	public boolean isSuccess(){
    	return success;
    }

	public void setSuccess(boolean success){
    	this.success = success;
    }
		
	@StructField
	private ValidationReturn<E> validationReturn = new ValidationReturn<E>(); //错误类型
	
	/**
	 * 获得验证结果
	 */
	public ValidationReturn<E> getValidationReturn(){
    	return validationReturn;
    }
	
	public void addError(E error){
		validationReturn.addError(error);
	}
	
	public void addError(E e,ErrorLevel level){
		validationReturn.addError(e, level);
	}
	
	public void addItemErrors(int index,E itemError){
		validationReturn.addItemError(index, itemError);
	}
	
	public void addItemErrors(int index,E itemError,ErrorLevel level){
		validationReturn.addItemError(index, itemError,level);
	}
	
	
}
