package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 开启当前服务
 */
public class OpenOrderServiceTask  extends Task<OpenOrderServiceTask.Method>{
	public enum Method{
		
	};
	
	//服务Id
	private GUID serviceId;
	
	//服务名称
	private String sysName;
	
	//服务器地址
	private String hostAddress;
	
	//总经理手机号码
	private String bossMobile;

	public GUID getServiceId() {
		return serviceId;
	}

	public void setServiceId(GUID serviceId) {
		this.serviceId = serviceId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}
	
}
