package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ״̬�仯�¼�
 * @author Administrator
 */
public class MaterialsStatusChangeEvent extends Event {
	
	public final GUID materialItemId;//��������ID
	
	public MaterialsStatusChangeEvent(GUID materialItemId){
		this.materialItemId = materialItemId;
	}
	
}
