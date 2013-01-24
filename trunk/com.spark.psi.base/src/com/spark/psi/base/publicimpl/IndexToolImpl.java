package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.index.entity.IndexTool;

public class IndexToolImpl implements IndexTool {
	private String name;
	private int x;
	private int y;
	private GUID recid;
	private GUID userid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setUserid(GUID userid) {
		this.userid = userid;
	}
	public GUID getUserid() {
		return userid;
	}
}
