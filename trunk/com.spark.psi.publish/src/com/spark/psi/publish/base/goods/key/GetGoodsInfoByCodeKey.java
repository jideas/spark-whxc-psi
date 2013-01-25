/**
 * 
 */
package com.spark.psi.publish.base.goods.key;

/**
 * 
 *
 */
public class GetGoodsInfoByCodeKey {
	private String goodsCode;
	
	public GetGoodsInfoByCodeKey(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	public String getGoodsCode() {
		return this.goodsCode;
	}
}
