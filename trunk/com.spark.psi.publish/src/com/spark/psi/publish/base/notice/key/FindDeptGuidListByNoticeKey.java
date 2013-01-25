package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>根据ID查询部门ID列表</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-21
 */

public class FindDeptGuidListByNoticeKey{

	/**公告GUID*/
	private GUID noticeGuid;
	
	/** 
     *构造方法
     */
    public FindDeptGuidListByNoticeKey(){
	    super();
    }
    
	/** 
     *构造方法
     *@param noticeGuid 公告ID
     */
    public FindDeptGuidListByNoticeKey(GUID noticeGuid){
	    super();
	    this.noticeGuid = noticeGuid;
    }



	public GUID getNoticeGuid(){
		return noticeGuid;
	}

	public void setNoticeGuid(GUID noticeGuid){
		this.noticeGuid = noticeGuid;
	}

}
