/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.config
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.config
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.config;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�������۾��������õĿͻ����ö��</p>
 *


 *
 
 * @version 2012-5-17
 */

public class RemoveSalesManCreidtUesdTask extends SimpleTask{

	/**
	 * ���۾���Ա��id
	 */
	private GUID id;
	
	private double amount;
	
	
	public RemoveSalesManCreidtUesdTask(GUID id,double amount){
		this.id = id;
		this.amount = amount;
    }


	public GUID getId(){
    	return id;
    }


	public void setId(GUID id){
    	this.id = id;
    }


	public double getAmount(){
    	return amount;
    }


	public void setAmount(double amount){
    	this.amount = amount;
    }

	
	
}
