/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.goods.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.goods.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO ������</p>
 *


 *
 
 * @version 2012-3-31
 */

public class DeleteGoodsCategoryTask extends SimpleTask{

	/**
	 * ����id
	 */
	private GUID id;

	/**
	 * ���캯��
	 * 
	 * @param id
	 *            ����id
	 */
	public DeleteGoodsCategoryTask(GUID id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

}
