package com.spark.psi.publish.base.statecontrol.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>“已使用过”事件</p>
 * 当使用过某个对象（比如使用了商品，选择了客户等），需要触发这个事件


 *
 
 * @version 2012-4-20
 */
public class UsedEvent extends Event{
	
	/**
	 * 
	 * <p>类型</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-20
	 */
	public enum Clazs{
		/**客户供应商*/
		Partner,
		/**商品*/
		Goods
	}
	
	private GUID id;
	
	private Clazs clazs;
	
	public UsedEvent(Clazs clazs,GUID id){
	    this.clazs = clazs;
	    this.id = id;
    }

	public Clazs getClazs(){
    	return clazs;
    }

	public GUID getId(){
    	return id;
    }
	
	
	
}
