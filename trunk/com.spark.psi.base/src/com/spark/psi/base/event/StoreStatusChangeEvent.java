package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * �ֿ�״̬�仯�¼�
 * @author Administrator
 */
public class StoreStatusChangeEvent extends Event {
	
	/**
	 * �ֿ�ID
	 * 
	 */
	public final GUID storeId;
	
	public StoreStatusChangeEvent(GUID storeId){
		this.storeId = storeId;
	}
	
}
