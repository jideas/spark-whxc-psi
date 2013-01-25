package com.spark.order.sales2;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;

/**
 * <p>���۶�����Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public class SalesOrderInfo2 extends OrderFather{
	@StructField
	protected GUID consigneeId;//	�ջ���Guid	guid	
	@StructField
	protected String consignee;//	�ջ���	nvarchar	40
	@StructField
	protected String consigneeTel;//	�ջ��˵绰	nvarchar	30
	@StructField
	protected String consigneePhone;//	�ջ����ֻ�	nvarchar	30
	@StructField
	protected String address;//	�ջ���ַ	nvarchar	200
	@StructField
	protected double disAmount;//	�����ۿ�	numeric	17
	@StructField
	protected GUID examDeptId;//	��˲���	guid	
	@StructField
	protected boolean isAllot;//	�Ƿ����	boolean	
	@StructField	
	protected long planOutDate;//	Ԥ�Ƴ�������	date	
	
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
