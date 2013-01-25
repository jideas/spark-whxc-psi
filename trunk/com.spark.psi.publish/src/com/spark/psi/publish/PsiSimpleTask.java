/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-22    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-22    jiuqi
 * ============================================================*/

package com.spark.psi.publish;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.ValidationReturn.ErrorLevel;

/**
 * <p>��װ�˷�����֤�����SimpleTask</p>
 *


 *
 
 * @version 2012-3-22
 */

public class PsiSimpleTask<E> extends SimpleTask{
	
	
	@StructField
	private boolean ignoredWarning = false;  //�Ƿ���Ծ����쳣	
	
	@StructField
	private boolean success = true; //�������ؽ�� �ɹ� or ʧ��

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
	private ValidationReturn<E> validationReturn = new ValidationReturn<E>(); //��������
	
	/**
	 * �����֤���
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
