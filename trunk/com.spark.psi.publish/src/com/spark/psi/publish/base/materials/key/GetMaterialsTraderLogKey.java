package com.spark.psi.publish.base.materials.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>查询材料交易记录</p>
 *
 */
public class GetMaterialsTraderLogKey extends LimitKey{

	public GetMaterialsTraderLogKey(GUID goodsId){
	    super(0, 20, false);
	    this.goodsId = goodsId;
    }
	
	private GUID goodsId;

	public GUID getGoodsId(){
    	return goodsId;
    }	
	
}
