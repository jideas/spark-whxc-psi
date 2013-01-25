package com.spark.psi.publish.base.statecontrol.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����ʹ�ù����¼�</p>
 * ��ʹ�ù�ĳ�����󣨱���ʹ������Ʒ��ѡ���˿ͻ��ȣ�����Ҫ��������¼�


 *
 
 * @version 2012-4-20
 */
public class UsedEvent extends Event{
	
	/**
	 * 
	 * <p>����</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-20
	 */
	public enum Clazs{
		/**�ͻ���Ӧ��*/
		Partner,
		/**��Ʒ*/
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
