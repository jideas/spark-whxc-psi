package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��Ʒ�����������޸ı��¼���ֻ�����������仯�Żᴥ���¼���</p>
 *


 *
 
 * @version 2011-6-7
 */
public class GoodsCategoryStoreLimitUpperChangeEvent extends Event{

	private GUID categoryId;
	
	public GoodsCategoryStoreLimitUpperChangeEvent(GUID categoryId){
		this.categoryId = categoryId;
    }

	public GUID getCategoryId(){
    	return categoryId;
    }

	public void setCategoryId(GUID categoryId){
    	this.categoryId = categoryId;
    }
	
}
