package com.spark.psi.publish.inventory.key;

/**
 * 得到预警商品及其库存列表
 */
public class GetWarningGoodsItemListKey {
	private Boolean jointVenture;

	public GetWarningGoodsItemListKey(Boolean jointVenture) {
		this.jointVenture = jointVenture;
	}

	public Boolean getJointVenture() {
		return jointVenture;
	}
}
