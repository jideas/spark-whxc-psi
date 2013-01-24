package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.type.GUID;

public class MaterialsSelectParameters {

	private GUID storeId;
	private boolean onsaleOnly, isSingleLimit, enableAdd;
	private Boolean jointVenture;

	public MaterialsSelectParameters(GUID storeId, boolean onsaleOnly, boolean enableAdd, boolean isSingleLimit, Boolean jointVenture) {
		this.storeId = storeId;
		this.onsaleOnly = onsaleOnly;
		this.isSingleLimit = isSingleLimit;
		this.jointVenture = jointVenture;
		this.enableAdd = enableAdd;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public boolean isOnsaleOnly() {
		return onsaleOnly;
	}

	public boolean isSingleLimit() {
		return isSingleLimit;
	}

	public boolean isEnableAdd() {
		return enableAdd;
	}

	public Boolean getJointVenture() {
		return jointVenture;
	}
}
