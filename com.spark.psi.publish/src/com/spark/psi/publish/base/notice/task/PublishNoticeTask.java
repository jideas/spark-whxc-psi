package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>发布公告</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-9
 */

public class PublishNoticeTask extends SimpleTask{

	/**GUID*/
	private GUID RECID;
	
	/** 
     *构造方法
     *@param rECID
     */
    public PublishNoticeTask(GUID rECID){
	    RECID = rECID;
    }

	public GUID getRECID(){
    	return RECID;
    }

	public void setRECID(GUID rECID){
    	RECID = rECID;
    }
}
