package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����ID��ѯ����ID�б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-21
 */

public class FindDeptGuidListByNoticeKey{

	/**����GUID*/
	private GUID noticeGuid;
	
	/** 
     *���췽��
     */
    public FindDeptGuidListByNoticeKey(){
	    super();
    }
    
	/** 
     *���췽��
     *@param noticeGuid ����ID
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
