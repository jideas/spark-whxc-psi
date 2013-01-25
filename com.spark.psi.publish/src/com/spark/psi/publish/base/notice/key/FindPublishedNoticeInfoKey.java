package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>查询已发布公告详情</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-6
 */

public class FindPublishedNoticeInfoKey{

	/**GUID*/
	private GUID RECID;
	
	/** 
     *构造方法
     */
    public FindPublishedNoticeInfoKey(){
	    super();
    }

	/** 
     *构造方法
     *@param rECID
     */
    public FindPublishedNoticeInfoKey(GUID rECID){
	    super();
	    RECID = rECID;
    }

	public GUID getRECID(){
		return RECID;
	}

	public void setRECID(GUID rECID){
		RECID = rECID;
	}
}
