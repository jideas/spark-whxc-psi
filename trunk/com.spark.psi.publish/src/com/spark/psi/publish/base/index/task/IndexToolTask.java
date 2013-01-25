package com.spark.psi.publish.base.index.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class IndexToolTask extends Task<IndexToolTask.Method>{
	public enum Method {
		ADD, DEL
	}
	
	private GUID recid;
	private String name;
	private int X;
	private int Y;
	private GUID userid;
	public IndexToolTask(GUID recid, String name, GUID userid) {
		super();
		this.recid = recid;
		this.name = name;
		this.userid = userid;
	}
	public GUID getRecid() {
		return recid;
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public GUID getUserid() {
		return userid;
	}
	
	
	
}
