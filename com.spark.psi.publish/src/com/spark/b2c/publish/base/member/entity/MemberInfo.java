package com.spark.b2c.publish.base.member.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 会员基础信息
 */
public interface MemberInfo {
	public GUID getId();

	public GUID getUserId();

	public String getRealName();

	public String getUsername();

	public String getMobile();

	public String getEmail();

	/**
	 * 推广码
	 * @return
	 */
	public String getExtendCode();

	public String getLevel();

	public boolean isSex();

	public long getBirthday();

	/**
	 * 是否订阅邮件
	 * @return
	 */
	public boolean isSubscribeFlag();

	public long getCreateDate();
}
