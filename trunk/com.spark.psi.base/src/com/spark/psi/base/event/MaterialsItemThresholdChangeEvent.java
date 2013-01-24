package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��Ʒ��Ŀ����������޸��¼�����һ��������ʵ���Ա仯��</p>
 *


 *
 
 * @version 2011-6-7
 */
public class MaterialsItemThresholdChangeEvent extends Event{

	private GUID goodsItemId;
	
	public MaterialsItemThresholdChangeEvent(GUID goodsItemId){
		this.goodsItemId = goodsItemId;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }
	
}
