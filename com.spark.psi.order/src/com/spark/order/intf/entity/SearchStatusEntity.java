package com.spark.order.intf.entity;

import com.spark.order.intf.type.StatusEnum;

/**
 * <p>����״̬��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2012-2-7
 */
public class SearchStatusEntity {
	private final String type;//����
	private StatusEnum status;//״̬
	private boolean isStop;//�Ƿ�����ֹ
	
	private final String name;//����
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
