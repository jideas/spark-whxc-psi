package com.spark.oms.publish.product.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ɾ����Ʒ���
 */
public class DeleteProductTask extends SimpleTask{
	
	private GUID []id;
	
	/**
	 * ��ƷId��ʶ
	 * @return
	 */
	public GUID[] getId() {
		return id;
	}
	
	/**
	 * ���ò�Ʒ��ʶ
	 * @param id
	 */
	public void setId(GUID ...id) {
		this.id = id;
	}
	
}
