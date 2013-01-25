package com.spark.psi.publish.base.index.entity;

import com.jiuqi.dna.core.type.GUID;

public interface IndexTool {
	public GUID getRecid();
	public String getName();
	public int getX();
	public int getY();
	public GUID getUserid();
}
