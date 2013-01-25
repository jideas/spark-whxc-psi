package com.spark.b2c.publish.base.member.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ա������Ϣ
 */
public interface MemberInfo {
	public GUID getId();

	public GUID getUserId();

	public String getRealName();

	public String getUsername();

	public String getMobile();

	public String getEmail();

	/**
	 * �ƹ���
	 * @return
	 */
	public String getExtendCode();

	public String getLevel();

	public boolean isSex();

	public long getBirthday();

	/**
	 * �Ƿ����ʼ�
	 * @return
	 */
	public boolean isSubscribeFlag();

	public long getCreateDate();
}
