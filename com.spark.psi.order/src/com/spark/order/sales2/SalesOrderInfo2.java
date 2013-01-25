package com.spark.order.sales2;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;

/**
 * <p>销售订单信息</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public class SalesOrderInfo2 extends OrderFather{
	@StructField
	protected GUID consigneeId;//	收货人Guid	guid	
	@StructField
	protected String consignee;//	收货人	nvarchar	40
	@StructField
	protected String consigneeTel;//	收货人电话	nvarchar	30
	@StructField
	protected String consigneePhone;//	收货人手机	nvarchar	30
	@StructField
	protected String address;//	收货地址	nvarchar	200
	@StructField
	protected double disAmount;//	整单折扣	numeric	17
	@StructField
	protected GUID examDeptId;//	审核部门	guid	
	@StructField
	protected boolean isAllot;//	是否分配	boolean	
	@StructField	
	protected long planOutDate;//	预计出库日期	date	
	
	public GUID getConsigneeId() {
		return consigneeId;
	}
	public void setConsigneeId(GUID consigneeId) {
		this.consigneeId = consigneeId;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}
	public GUID getExamDeptId() {
		return examDeptId;
	}
	public void setExamDeptId(GUID examDeptId) {
		this.examDeptId = examDeptId;
	}
	public boolean isAllot() {
		return isAllot;
	}
	public void setAllot(boolean isAllot) {
		this.isAllot = isAllot;
	}
	public long getPlanOutDate() {
		return planOutDate;
	}
	public void setPlanOutDate(long planOutDate) {
		this.planOutDate = planOutDate;
	}
	@Override
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
