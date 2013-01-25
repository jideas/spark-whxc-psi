package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>��ѯ��Ʒ���׼�¼</p>
 *


 *
 
 * @version 2012-4-27
 */
public class GetGoodsTraderLogKey extends LimitKey{

	public GetGoodsTraderLogKey(GUID goodsId){
	    super(0, 20, false);
	    this.goodsId = goodsId;
    }
	
	private GUID goodsId;

	public GUID getGoodsId(){
    	return goodsId;
    }	
	
}
