/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.task.instorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.CheckOutLog;

/**
 * <p>ȷ�ϳ����¼Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckOutLogTask extends SimpleTask {

	private CheckOutLog checkOutLog;
	public void setCheckOutLog(CheckOutLog checkOutLog) {
		this.checkOutLog = checkOutLog;
	}
	public CheckOutLog getCheckOutLog() {
		return checkOutLog;
	}
}
