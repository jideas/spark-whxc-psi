/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.report.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-13       ���ɹ�        
 * ============================================================*/

package com.spark.psi.report.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.constant.OldReportEnums.ScreenType;

/**
 * <p>
 * ������ʵ��
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author ���ɹ�
 * @version 2011-12-13
 */

public class ReportScreen {
	private GUID recid; // ϵͳ�����GUID
	private GUID tenantId; // �⻧���
	private ScreenType orderType; // ����
	private double orderAmount; // �������
	private GUID orderPerson; // ����������
	private GUID deptGuid; // ������������GUID
	private long orderDate; // ��������
	private int viewOrder; // ������һ��֮����ʾ��˳���

	/**
	 * @return the ������һ��֮����ʾ��˳���
	 */
	public int getViewOrder() {
		return viewOrder;
	}

	/**
	 * @param viewOrderl
	 *            ������һ��֮����ʾ��˳���
	 */
	public void setViewOrder(int viewOrder) {
		this.viewOrder = viewOrder;
	}

	/**
	 * @return ϵͳ�����GUID
	 */
	public GUID getRecid() {
		return recid;
	}

	/**
	 * @param recid
	 *            ϵͳ�����GUID
	 */
	public void setRecid(GUID recid) {
		this.recid = recid;
	}

	/**
	 * @return �⻧���
	 */
	public GUID getTenantsGuid() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            �⻧���
	 */
	public void setTenantsGuid(GUID tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return ����
	 */
	public ScreenType getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType
	 *            ����
	 */
	public void setOrderType(ScreenType orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return �������
	 */
	public double getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount
	 *            �������
	 */
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return ����������
	 */
	public GUID getOrderPerson() {
		return orderPerson;
	}

	/**
	 * @param orderPerson
	 *            ����������
	 */
	public void setOrderPerson(GUID orderPerson) {
		this.orderPerson = orderPerson;
	}

	/**
	 * @return ������������GUID
	 */
	public GUID getDeptGuid() {
		return deptGuid;
	}

	/**
	 * @param deptGuid
	 *            ������������GUID
	 */
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}

	/**
	 * @return ��������
	 */
	public long getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            ��������
	 */
	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}
}
