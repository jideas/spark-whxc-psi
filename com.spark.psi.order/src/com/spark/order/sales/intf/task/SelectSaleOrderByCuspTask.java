package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>�ͻ���ѯ�Ƿ��й��������۶���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
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
