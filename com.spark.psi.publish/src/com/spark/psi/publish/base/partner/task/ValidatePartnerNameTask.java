package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class ValidatePartnerNameTask extends
		Task<ValidatePartnerNameTask.Method> {

	/**
	 * 操作方法
	 */
	public enum Method {
		CustomerShortName, CustomerName, SupplierShortName, SupplierName
	}

	/**
	 * 名称
	 */
	private String name;
	
	//id
	private GUID id;

	/**
	 * 是否存在
	 */
	private boolean exist;


	/**
	 * 构造函数
	 * 
	 * @param name
	 *            待校验的名称
	 */
	public ValidatePartnerNameTask(GUID id,String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the exist
	 */
	public boolean isExist() {
		return exist;
	}

	/**
	 * @param exist
	 *            the exist to set
	 */
	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public GUID getId(){
    	return id;
    }

}
