package com.spark.psi.base.task.pri;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>使用级次树时定义根节点值，注意勿重复</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-24
 */
public enum LevelTreeRoots{
	
	/** 商品类型根节点值 */
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
