package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ�ѷ�����������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-6
 */

public class FindPublishedNoticeInfoKey{

	/**GUID*/
	private GUID RECID;
	
	/** 
     *���췽��
     */
    public FindPublishedNoticeInfoKey(){
	    super();
    }

	/** 
     *���췽��
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
