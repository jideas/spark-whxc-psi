/**
 * 
 */
/**
 * 
 */
package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ���������ļ���
 
 *
 */
public class PwdEncryptionTask extends SimpleTask {
	
	private String pwd;
	
	private GUID ciphertext;
	
	public PwdEncryptionTask(String pwd){
		this.pwd = pwd;
	}

	public String getPwd() {
		return pwd;
	}

	public GUID getCiphertext() {
		return ciphertext;
	}

	public void setCiphertext(GUID ciphertext) {
		this.ciphertext = ciphertext;
	}
	
	
}
