package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 登录验证
 * 
 *
 */
public class LoginUserTask extends SimpleTask{

	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 密码
	 */
	private String pwd;
		
	/**
	 * 错误消息
	 */
	private String msg;
	
	/**
	 * 是否成功
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
