package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * ��¼��֤
 * 
 *
 */
public class LoginUserTask extends SimpleTask{

	/**
	 * �ֻ�����
	 */
	private String mobile;
	
	/**
	 * ����
	 */
	private String pwd;
		
	/**
	 * ������Ϣ
	 */
	private String msg;
	
	/**
	 * �Ƿ�ɹ�
	 */
	private boolean succeed;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	
	public LoginUserTask(String mobile,String pwd){
		this.mobile = mobile;
		this.pwd = pwd;
	}
}
