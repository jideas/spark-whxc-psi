package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.bom.entity.BomHistoryItem;

public class BomHistoryItemImpl implements BomHistoryItem {
	private GUID id;
	private String bomNo, creator, approvor, ineffectDate, outeffectDate,ineffector,outeffector;

	public String getIneffector() {
		return ineffector;
	}

	public void setIneffector(String ineffector) {
		this.ineffector = ineffector;
	}

	public String getOuteffector() {
		return outeffector;
	}

	public void setOuteffector(String outeffector) {
		this.outeffector = outeffector;
	}

	public String getBomNo() {
		return bomNo;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getApprovor() {
		return approvor;
	}

	public void setApprovor(String approvor) {
		this.approvor = approvor;
	}

	public String getIneffectDate() {
		return ineffectDate;
	}

	public void setIneffectDate(String ineffectDate) {
		this.ineffectDate = ineffectDate;
	}

	public String getOuteffectDate() {
		return outeffectDate;
	}

	public void setOuteffectDate(String outeffectDate) {
		this.outeffectDate = outeffectDate;
	} 
 
}
