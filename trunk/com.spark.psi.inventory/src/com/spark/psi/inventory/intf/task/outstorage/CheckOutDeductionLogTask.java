/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.task.outstorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.pub.CheckOutDeductionLog;

/**
 * <p>���ⵥ�ּ���¼</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-27
 */

public class CheckOutDeductionLogTask extends SimpleTask {

	private CheckOutDeductionLog log;

	public void setLog(CheckOutDeductionLog log) {
		this.log = log;
	}

	public CheckOutDeductionLog getLog() {
		return log;
	}
}
