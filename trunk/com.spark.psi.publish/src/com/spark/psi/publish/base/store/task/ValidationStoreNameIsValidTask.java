/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.store.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-10    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.store.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-10    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��֤�ֿ������Ƿ���Ч</p>
 *


 *
 
 * @version 2012-5-10
 */

public class ValidationStoreNameIsValidTask extends SimpleTask{
	
	private String name;
	
	private GUID id;
	
	private boolean valid = true;
	
	private String msg;
	
	public ValidationStoreNameIsValidTask(GUID id,String name){
	    this.id = id;
	    this.name = name;
    }

	public boolean isValid(){
    	return valid;
    }

	public void setValid(boolean valid){
    	this.valid = valid;
    }

	public String getMsg(){
    	return msg;
    }

	public void setMsg(String msg){
    	this.msg = msg;
    }

	public String getName(){
    	return name;
    }

	public GUID getId(){
    	return id;
    }
	
	
	
}
