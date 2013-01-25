/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.base.index.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.index.entity.Note;

/**
 *
 */
public class NoteTask extends Task<NoteTask.Method> {

	/**
	 *
	 */
	public enum Method {
		ADD, MODIFY
	}
	
	private GUID recid;
	private String text;
	
	public NoteTask(GUID recid, String text) {
		super();
		this.recid = recid;
		this.text = text;
	}
	
	public GUID getRecid() {
		return recid;
	}
	public String getText() {
		return text;
	}
	
	
}
