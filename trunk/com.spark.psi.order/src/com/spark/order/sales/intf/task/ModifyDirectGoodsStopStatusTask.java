package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>销售订单中止或重新执行修改直供商品中止状态</p> 
 */
public class ModifyDirectGoodsStopStatusTask extends SimpleTask implements ITaskResult{
	private GUID tenantsGuid;
	private final GUID saleRecid;
	private boolean isStoped;
	public ModifyDirectGoodsStopStatusTask(GUID saleRecid, boolean isStoped) {
		super();
		this.saleRecid = saleRecid;
		this.isStoped = isStoped;
	}
	public boolean isStoped() {
		return isStoped;
	}
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}
	public GUID getSaleRecid() {
		return saleRecid;
	}
	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	/**
	 * @param tenantsGuid the tenantsGuid to set
	 */
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	
	public int lenght;
	
	public boolean isSucceed() {
		return 1 == lenght;
	}
	public int lenght() {
		return lenght;
	}
	
	
}
