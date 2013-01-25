package com.spark.psi.publish.base.bom.key;

import com.jiuqi.dna.core.type.GUID;

public class GetNewBomNoKey {

	private GUID goodsItemId;
	private String goodsNo;

	public GetNewBomNoKey(GUID id,String goodsNo) {
		this.goodsItemId = id; 
		this.goodsNo = goodsNo;
	}

	public GUID getGoodsItemId() {
		return this.goodsItemId;
	}
	
	public String getGoodsNo(){
		return goodsNo;
	}
}
