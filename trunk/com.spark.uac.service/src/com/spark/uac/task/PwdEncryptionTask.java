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
 * 将密码明文加密
 
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
