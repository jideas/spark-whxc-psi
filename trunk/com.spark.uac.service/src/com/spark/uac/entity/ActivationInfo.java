package com.spark.uac.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ActivationInfo {

	public GUID getId();

	public GUID getUserId(); // 仅对用户激活有效

	public GUID getTargetId();

	public GUID getSourceId();

	public String getMobileNo();

	public String getTitle();

	public String getActiveCode();

	public long getActiveCodeCreateTime();
}
