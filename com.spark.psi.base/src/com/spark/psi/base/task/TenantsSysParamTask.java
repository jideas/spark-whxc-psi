/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.tenants.intf.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-17       ������        
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.tenants.intf.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-17       ������        
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.SysParamKey;

/**
 * <p>�����⻧ϵͳ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2011-11-17
 */

public class TenantsSysParamTask extends SimpleTask{
	
	private SysParamKey key;

	private Boolean yes;
	
	/**
	 * �޸�ָ������  Ĭ��ֵ Ϊ false
	 * @param key ����ö��
	 */
	public TenantsSysParamTask(SysParamKey key){
		this.key = key;
	}
	
	/**
	 * �޸�ָ������
	 * @param key ����ö��
	 * @param value ֵ true or false
	 */
	public TenantsSysParamTask(SysParamKey key,boolean value){
		this.key = key;
		this.yes = value;
	}

	public SysParamKey getKey(){
    	return key;
    }

	public void setKey(SysParamKey key){
    	this.key = key;
    }
		

	public Boolean getYes(){
    	return yes;
    }

	public void setYes(Boolean yes){
    	this.yes = yes;
    }
	
	
	
}
