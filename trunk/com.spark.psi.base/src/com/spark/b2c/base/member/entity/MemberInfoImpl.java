package com.spark.b2c.base.member.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.b2c.publish.base.member.entity.MemberInfo;

public class MemberInfoImpl implements MemberInfo {
	private GUID id	;//标识	guid
	private GUID userId	;//用户id	guid
	private String realName	;//真实姓名	nvarchar
	private String username	;//用户名	nvarchar
	private String mobile	;//手机号码	nvarchar
	private String email	;//邮箱地址	nvarchar
	private String extendCode	;//推广码	nvarchar
	private String level	;//会员等级	char
	private boolean sex	;//性别	boolean
	private long birthday	;//生日	date
	private boolean subscribeFlag	;//是否订阅邮件	boolean
	private long createDate	;//创建时间	date
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getUserId() {
		return userId;
	}
	public void setUserId(GUID userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExtendCode() {
		return extendCode;
	}
	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public boolean isSubscribeFlag() {
		return subscribeFlag;
	}
	public void setSubscribeFlag(boolean subscribeFlag) {
		this.subscribeFlag = subscribeFlag;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
}
