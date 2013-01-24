/**
 * 
 */
package com.spark.psi.inventory.intf.key.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * 查询出库单抵减记录
 *
 */
public class GetCheckOutDeductionLogKey {

	private GUID id;

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getId() {
		return id;
	}
}
