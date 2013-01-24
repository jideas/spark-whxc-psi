package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>公告阅读事件</p>
 *

 
 */
public class NoticeReadEvent extends Event{

	private GUID id;

	public NoticeReadEvent(GUID id){
		this.id = id;
	}

	public GUID getId(){
		return id;
	}

}
