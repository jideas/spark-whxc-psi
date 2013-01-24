package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.index.entity.Note;

public class NoteImpl implements Note {
	private GUID recid;
	private String noteText;
	public GUID getRecid() {
		return recid;
	}
	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	
}
