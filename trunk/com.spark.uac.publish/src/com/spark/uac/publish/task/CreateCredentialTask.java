/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.publish.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-29    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.publish.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-29    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * <p>����һ����֤����Կ</p>
 *


 *
 
 * @version 2012-5-29
 */

public class CreateCredentialTask extends SimpleTask{
	
	private String name;
	
	private String credential;
	
	public CreateCredentialTask(String name){
	    this.name = name;
    }

	public String getName(){
    	return name;
    }

	public String getCredential(){
    	return credential;
    }

	public void setCredential(String credential){
    	this.credential = credential;
    }
	
	
	
	
}
