package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class FindNoticeInfoKey{

	/**GUID*/
	private GUID RECID;
	
	/** 
     *���췽��
     */
    public FindNoticeInfoKey(){
	    super();
    }

	/** 
     *���췽��
     *@param rECID
     */
    public FindNoticeInfoKey(GUID rECID){
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
