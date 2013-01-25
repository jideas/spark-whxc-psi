package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>客户查询是否有关联的销售订单</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-2
 */
public class SelectSaleOrderByCuspTask extends SimpleTask implements ITaskResult{
	private int lenght;
	private final GUID cusproGuid;
	private GUID tenantsGuid;
	
	public SelectSaleOrderByCuspTask(GUID cusproGuid) {
		this.cusproGuid = cusproGuid;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	
	public GUID getCusproGuid() {
		return cusproGuid;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	
	public boolean isHave(){
		return lenght > 0;
	}
	
	public boolean isSucceed() {
		return lenght > 0;
	}

	public int lenght() {
		return lenght;
	}

}
