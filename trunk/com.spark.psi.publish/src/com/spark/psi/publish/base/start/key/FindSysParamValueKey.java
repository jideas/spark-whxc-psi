package com.spark.psi.publish.base.start.key;

import com.spark.psi.publish.SysParamKey;

/**
 * <p>����key��ѯϵͳ����ֵ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-3
 */

public class FindSysParamValueKey{

	/**key*/
	private SysParamKey key;

	/** 
	 *���췽��
	 *@param key
	 */
	public FindSysParamValueKey(SysParamKey key){
		super();
		this.key = key;
	}

	public SysParamKey getKey(){
		return key;
	}

	public void setKey(SysParamKey key){
		this.key = key;
	}

}
