package com.spark.psi.publish.base.start.key;

import com.spark.psi.publish.SysParamKey;

/**
 * <p>根据key查询系统参数值</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-3
 */

public class FindSysParamValueKey{

	/**key*/
	private SysParamKey key;

	/** 
	 *构造方法
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
