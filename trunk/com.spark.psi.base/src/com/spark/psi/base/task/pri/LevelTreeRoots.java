package com.spark.psi.base.task.pri;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>ʹ�ü�����ʱ������ڵ�ֵ��ע�����ظ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-24
 */
public enum LevelTreeRoots{
	
	/** ��Ʒ���͸��ڵ�ֵ */
	GOODSTYPE(GUID.valueOf("10000000000000000000000000000001"),
			GUID.valueOf("20000000000000000000000000000002")),;
	
	public GUID getRECID(){
    	return RECID;
    }
	public GUID getNoTypeRECID(){
		return NOTYPERECID;
	}

	private GUID RECID;
	private GUID NOTYPERECID;
	
	private LevelTreeRoots(GUID RECID,GUID NOTYPERECID){
		this.RECID = RECID;
		this.NOTYPERECID=NOTYPERECID;
    }
	
	
}
