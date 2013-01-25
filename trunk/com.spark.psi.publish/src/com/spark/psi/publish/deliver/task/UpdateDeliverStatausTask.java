package com.spark.psi.publish.deliver.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;


public class UpdateDeliverStatausTask extends Task<UpdateDeliverStatausTask.Method> {

	public enum Method
	{
		/**
		 * 配送
		 */
		Deliver,
		/**
		 * 到货
		 */
		Arrive,
		/**
		 * 异常
		 */
		Exception,
		/**
		 * 处理
		 */
		Handle;
	}
	private GUID id;
	public GUID getId() {
		return id;
	}
	/**
	 * 异常时需校验：description(异常情况)输入，处理时需校验：formula(处理方案)输入
	 * @param id 配送单id
	 */
	public UpdateDeliverStatausTask(GUID id) {
		super();
		this.id = id;
	}
	private int packageCount;//	发货箱数
	private int receivedPackageCount;//	收货箱数
	private String description;//	异常情况
	private String formula;//	处理方案
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
