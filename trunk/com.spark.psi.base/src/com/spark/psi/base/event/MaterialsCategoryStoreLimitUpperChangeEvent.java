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
public class MaterialsCategoryStoreLimitUpperChangeEvent extends Event{

	private GUID categoryId;
	
	public MaterialsCategoryStoreLimitUpperChangeEvent(GUID categoryId){
		this.categoryId = categoryId;
    }

	public GUID getCategoryId(){
    	return categoryId;
    }

	public void setCategoryId(GUID categoryId){
    	this.categoryId = categoryId;
    }
	
}
