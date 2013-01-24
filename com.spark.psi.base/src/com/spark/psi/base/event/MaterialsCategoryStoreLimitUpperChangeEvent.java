package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>商品分类库存金额上限改变事件（只有真正发生变化才会触发事件）</p>
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
