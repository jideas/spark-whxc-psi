package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ״̬�仯�¼�
 * @author Administrator
 */
public class GoodsStatusChangeEvent extends Event {
	
	public final GUID goodsItemId;//��Ʒ����ID
	
	public GoodsStatusChangeEvent(GUID goodsItemId){
		this.goodsItemId = goodsItemId;
	}
	
}
