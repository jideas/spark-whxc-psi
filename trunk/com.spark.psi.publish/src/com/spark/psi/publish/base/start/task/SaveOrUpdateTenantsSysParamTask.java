package com.spark.psi.publish.base.start.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.SysParamKey;

/**
 * <p>����������⻧ϵͳ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-3
 */

public class SaveOrUpdateTenantsSysParamTask extends SimpleTask{

	/**ϵͳ����key*/
	private SysParamKey key;

	/**ϵͳ����ֵ*/
	private Boolean yes;

	/** 
	 *���췽��
	 *@param key
	 *@param yes
	 */
	public SaveOrUpdateTenantsSysParamTask(SysParamKey key, Boolean yes){
		super();
		this.key = key;
		this.yes = yes;
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
