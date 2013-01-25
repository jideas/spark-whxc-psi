package com.spark.psi.publish.deliver.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;


public class UpdateDeliverStatausTask extends Task<UpdateDeliverStatausTask.Method> {

	public enum Method
	{
		/**
		 * ����
		 */
		Deliver,
		/**
		 * ����
		 */
		Arrive,
		/**
		 * �쳣
		 */
		Exception,
		/**
		 * ����
		 */
		Handle;
	}
	private GUID id;
	public GUID getId() {
		return id;
	}
	/**
	 * �쳣ʱ��У�飺description(�쳣���)���룬����ʱ��У�飺formula(������)����
	 * @param id ���͵�id
	 */
	public UpdateDeliverStatausTask(GUID id) {
		super();
		this.id = id;
	}
	private int packageCount;//	��������
	private int receivedPackageCount;//	�ջ�����
	private String description;//	�쳣���
	private String formula;//	������
	public int getReceivedPackageCount() {
		return receivedPackageCount;
	}
	public void setReceivedPackageCount(int receivedPackageCount) {
		this.receivedPackageCount = receivedPackageCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setPackageCount(int packageCount) {
		this.packageCount = packageCount;
	}
	public int getPackageCount() {
		return packageCount;
	}
}
