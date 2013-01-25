package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class ValidatePartnerNameTask extends
		Task<ValidatePartnerNameTask.Method> {

	/**
	 * ��������
	 */
	public enum Method {
		CustomerShortName, CustomerName, SupplierShortName, SupplierName
	}

	/**
	 * ����
	 */
	private String name;
	
	//id
	private GUID id;

	/**
	 * �Ƿ����
	 */
	private boolean exist;


	/**
	 * ���캯��
	 * 
	 * @param name
	 *            ��У�������
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
