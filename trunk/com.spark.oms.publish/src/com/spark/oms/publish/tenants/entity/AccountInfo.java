package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

public class AccountInfo {
	//id
	private GUID id;
	
	//开户行
	private String bankName;
	
	//开户账号
	private String accountNo;
	
	//开户人姓名
	private String AccountHolder;
	
	//开会名
	private String accountName;
	
	//创建时间
	private long createTime;
	
	//创建人
	private String creator;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return AccountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		AccountHolder = accountHolder;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	

	
}
