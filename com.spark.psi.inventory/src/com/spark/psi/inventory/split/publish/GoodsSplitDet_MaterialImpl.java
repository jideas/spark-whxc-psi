package com.spark.psi.inventory.split.publish;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;

public class GoodsSplitDet_MaterialImpl implements GoodsSplitDet_Material {
	private GUID RECID;
	private GUID billId;
	private GUID materialId;
	private double mcount;
	private String mname;
	private String munit;
	private String mspec;
	private String mcode;
	private String mnumber;

	public String getMcode() {
		return mcode;
	}

	public String getMnumber() {
		return mnumber;
	}

	public GUID getRECID() {
		return RECID;
	}

	public GUID getBillId() {
		return billId;
	}

	public GUID getMaterialId() {
		return materialId;
	}

	public double getMcount() {
		return mcount;
	}

	public String getMname() {
		return mname;
	}

	public String getMunit() {
		return munit;
	}

	public String getMspec() {
		return mspec;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public void setBillId(GUID billId) {
		this.billId = billId;
	}

	public void setMaterialId(GUID materialId) {
		this.materialId = materialId;
	}

	public void setMcount(double mcount) {
		this.mcount = mcount;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public void setMunit(String munit) {
		this.munit = munit;
	}

	public void setMspec(String mspec) {
		this.mspec = mspec;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}

}
