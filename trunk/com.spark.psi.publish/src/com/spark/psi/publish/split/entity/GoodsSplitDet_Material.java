package com.spark.psi.publish.split.entity;

import com.jiuqi.dna.core.type.GUID;

public interface GoodsSplitDet_Material {

	public String getMcode();

	public String getMnumber();

	public GUID getRECID();

	public GUID getBillId();

	public GUID getMaterialId();

	public double getMcount();

	public String getMname();

	public String getMunit();

	public String getMspec();
}
