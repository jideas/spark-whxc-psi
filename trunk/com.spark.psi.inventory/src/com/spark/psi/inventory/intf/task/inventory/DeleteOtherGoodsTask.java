package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>ɾ��һ���ֿ��µ����г�ʼ����Ʒ��Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-4-16
 */
public class DeleteOtherGoodsTask extends SimpleTask {

	private GUID storeId;
	
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * ɾ��һ���ֿ��µ����г�ʼ����Ʒ��Ϣ
	 * @param storeId
	 */
	public DeleteOtherGoodsTask(GUID storeId)
	{
		this.storeId = storeId;
	}
}
