/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.promotion.intf
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-30     modi 
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.promotion.intf
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-30     modi 
 * ============================================================*/

package com.spark.order.promotion.service;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����ɾ����ʷ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */

class PromotionDelTask2 extends Task<PromotionDelTask2.Method> {

	public com.spark.order.promotion.intf.Promotion2 entity;
	public GUID recid;

	/**
	 * <p>����ɾ����ʷ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */

	public enum Method {
		ADD,
//		MODIFY, DELETE

	}

}
