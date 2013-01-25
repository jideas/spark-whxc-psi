package com.spark.psi.publish.phonemessage.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class UpdatePhoneMsgConfigTask extends SimpleTask {
	private GUID id;
	private boolean activing;

	private String balanceUrl;

	private String submitUrl;
	private String compAccountKey;
	private String userNameKey;

	private String passwordKey;

	private String phoneNumberKey;

	private String msgContentKey;
	private String compAccount;
	private String userName;

	private String password;

	private String secretkey;

	public boolean isActiving() {
		return activing;
	}

	public String getCompAccountKey() {
		return compAccountKey;
	}

	public void setCompAccountKey(String compAccountKey) {
		this.compAccountKey = compAccountKey;
	}

	public String getCompAccount() {
		return compAccount;
	}

	public void setCompAccount(String compAccount) {
		this.compAccount = compAccount;
	}

	public void setActiving(boolean activing) {
		this.activing = activing;
	}

	public String getBalanceUrl() {
		return balanceUrl;
	}

	public void setBalanceUrl(String balanceUrl) {
		this.balanceUrl = balanceUrl;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public String getUserNameKey() {
		return userNameKey;
	}

	public void setUserNameKey(String userNameKey) {
		this.userNameKey = userNameKey;
	}

	public String getPasswordKey() {
		return passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

	public String getPhoneNumberKey() {
		return phoneNumberKey;
	}

	public void setPhoneNumberKey(String phoneNumberKey) {
		this.phoneNumberKey = phoneNumberKey;
	}

	public String getMsgContentKey() {
		return msgContentKey;
	}

	public void setMsgContentKey(String msgContentKey) {
		this.msgContentKey = msgContentKey;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
}
