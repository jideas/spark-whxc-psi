/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.leveltree.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-5-17       Zhoulj        
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.leveltree.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-5-17       Zhoulj        
 * ============================================================*/

package com.spark.psi.base.task.pri;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸ĸ����ڵ�</p>
 * ������������ڵ�ļ��ι���
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-17
 */

public class UpdateLevelTreePathTask extends SimpleTask{
	
	public GUID RECID;
	
	public GUID tenantId;
	
	public GUID parentRecid;
	
	public UpdateLevelTreePathTask(GUID RECID,GUID parentRecid,GUID tenantId){
		this.parentRecid = parentRecid;
		this.RECID = RECID;
		this.tenantId = tenantId;
	}
	
}
