package com.spark.order.intf.entity;

import com.spark.order.intf.type.StatusEnum;

/**
 * <p>处理状态搜索处理</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2012-2-7
 */
public class SearchStatusEntity {
	private final String type;//类型
	private StatusEnum status;//状态
	private boolean isStop;//是否已中止
	
	private final String name;//名称
	/**
	 * @param type
	 * @param status
	 */
	public SearchStatusEntity(String type, StatusEnum status, String name) {
		this.type = type;
		this.status = status;
		this.name = name;
	}
	
	
	/**
	 * @param type
	 * @param isStop
	 */
	public SearchStatusEntity(String type, String name, boolean isStop) {
		this.type = type;
		this.isStop = isStop;
		this.name = name;
	}
	/**
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the isStop
	 */
	public boolean isStop() {
		return isStop;
	}
	/**
	 * @param isStop the isStop to set
	 */
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the status
	 */
	public StatusEnum getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
