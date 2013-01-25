/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.report.intf.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-13       汤成国        
 * ============================================================*/

package com.spark.psi.report.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.constant.OldReportEnums.ScreenType;

/**
 * <p>
 * 滚动屏实体
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 汤成国
 * @version 2011-12-13
 */

public class ReportScreen {
	private GUID recid; // 系统规格编号GUID
	private GUID tenantId; // 租户编号
	private ScreenType orderType; // 类型
	private double orderAmount; // 订单金额
	private GUID orderPerson; // 订单创建人
	private GUID deptGuid; // 订单所属部门GUID
	private long orderDate; // 订单日期
	private int viewOrder; // 数据在一天之内显示的顺序号

	/**
	 * @return the 数据在一天之内显示的顺序号
	 */
	public int getViewOrder() {
		return viewOrder;
	}

	/**
	 * @param viewOrderl
	 *            数据在一天之内显示的顺序号
	 */
	public void setViewOrder(int viewOrder) {
		this.viewOrder = viewOrder;
	}

	/**
	 * @return 系统规格编号GUID
	 */
	public GUID getRecid() {
		return recid;
	}

	/**
	 * @param recid
	 *            系统规格编号GUID
	 */
	public void setRecid(GUID recid) {
		this.recid = recid;
	}

	/**
	 * @return 租户编号
	 */
	public GUID getTenantsGuid() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            租户编号
	 */
	public void setTenantsGuid(GUID tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return 类型
	 */
	public ScreenType getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType
	 *            类型
	 */
	public void setOrderType(ScreenType orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return 订单金额
	 */
	public double getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount
	 *            订单金额
	 */
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return 订单创建人
	 */
	public GUID getOrderPerson() {
		return orderPerson;
	}

	/**
	 * @param orderPerson
	 *            订单创建人
	 */
	public void setOrderPerson(GUID orderPerson) {
		this.orderPerson = orderPerson;
	}

	/**
	 * @return 订单所属部门GUID
	 */
	public GUID getDeptGuid() {
		return deptGuid;
	}

	/**
	 * @param deptGuid
	 *            订单所属部门GUID
	 */
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}

	/**
	 * @return 订单日期
	 */
	public long getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            订单日期
	 */
	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}
}
