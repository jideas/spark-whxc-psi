package com.spark.order.intf.entity;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;

/**
 * <p>订单抽象父类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-26
 */
public class OrderFather extends EntityFather{
	@StructField
	protected String orderNumber;//	销售订单编号	nvarchar	20
	@StructField
	protected GUID partnerId;//	客户GUID	guid	
	@StructField
	protected String partnerShortName;//	客户名称	nvarchar	20
	@StructField
	protected String partnerName;//	客户全称	nvarchar	100
	@StructField
	protected String partnerNamePY;//	客户拼音	nvarchar	50
	@StructField
	protected String partnerFax;//	客户传真	nvarchar	20
	@StructField
	protected GUID contactId;//	联系人GUID	guid	
	@StructField
	protected String contactName;//	联系人	nvarchar	40
	@StructField
	protected String contactTel;//	联系人电话	nvarchar	20
	@StructField
	protected String contactPhone;//	联系人手机	nvarchar	20
	@StructField
	protected String returnCause;//	退回/中止原因	nvarchar	200
	@StructField
	protected String remark;//	备注	nvarchar	1000
	@StructField
	protected double totalAmount;//	总金额	numeric	17
	@StructField
	protected String type;//	类型	char	2
	@StructField
	protected String status;//	处理情况	char	2
	@StructField
	protected GUID deptId;//	所属部门部门GUID	guid	
	@StructField
	protected boolean isStoped;//	是否中止状态	boolean	
	@StructField
	protected String examin;//	审核人及日期	nvarchar	1000
	@StructField
	protected long effectiveDate;//	订单生效时间	date
	
	@StructField
	protected String defOne;//	用户自定义字段1	nvarchar	100
	@StructField
	protected String defTwo;//	用户自定义字段2	nvarchar	100
	@StructField
	protected String defThree;//	用户自定义字段3	nvarchar	100
	@StructField
	protected double defFour;//	用户自定义字段4	numeric	17
	@StructField
	protected double defFive;//	用户自定义字段5	numeric	17
	@StructField
	protected double defSix	;//用户自定义字段6	numeric	17
	@StructField
	protected long defSeven;//	用户自定义字段7	date	
	@StructField
	protected long defEight;//	用户自定义字段8	date	
	@StructField
	protected String defNine;//	用户自定义字段9	char	2
	@StructField
	protected String defTen	;//用户自定义字段10	char	2
	@StructField
	protected long deliveryDate;//	交货日期	date

	public Long getDeliveryDate() {
		return deliveryDate==0?null:deliveryDate;
	}

	/**
	 * 设置交货日期（采购退货无交货日期）
	 * @param deliveryDate void
	 */
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
//	protected abstract void setDeliveryDate(long deliveryDate);
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerShortName() {
		return partnerShortName;
	}
	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
		this.partnerNamePY = PinyinHelper.getLetter(partnerName);
	}
	public String getPartnerNamePY() {
		return partnerNamePY;
	}
//	private void setPartnerNamePY(String partnerNamePY) {
//		this.partnerNamePY = partnerNamePY;
//	}
	public String getPartnerFax() {
		return partnerFax;
	}
	public void setPartnerFax(String partnerFax) {
		this.partnerFax = partnerFax;
	}
	public GUID getContactId() {
		return contactId;
	}
	public void setContactId(GUID contactId) {
		this.contactId = contactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getReturnCause() {
		return returnCause;
	}
	public void setReturnCause(String returnCause) {
		this.returnCause = returnCause;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public GUID getDeptId() {
		return deptId;
	}
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}
	public boolean isStoped() {
		return isStoped;
	}
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}
	public String getExamin() {
		return examin;
	}
	public void setExamin(String examin) {
		this.examin = examin;
	}
	public long getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(long effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getDefOne() {
		return defOne;
	}
	public void setDefOne(String defOne) {
		this.defOne = defOne;
	}
	public String getDefTwo() {
		return defTwo;
	}
	public void setDefTwo(String defTwo) {
		this.defTwo = defTwo;
	}
	public String getDefThree() {
		return defThree;
	}
	public void setDefThree(String defThree) {
		this.defThree = defThree;
	}
	public double getDefFour() {
		return defFour;
	}
	public void setDefFour(double defFour) {
		this.defFour = defFour;
	}
	public double getDefFive() {
		return defFive;
	}
	public void setDefFive(double defFive) {
		this.defFive = defFive;
	}
	public double getDefSix() {
		return defSix;
	}
	public void setDefSix(double defSix) {
		this.defSix = defSix;
	}
	public long getDefSeven() {
		return defSeven;
	}
	public void setDefSeven(long defSeven) {
		this.defSeven = defSeven;
	}
	public long getDefEight() {
		return defEight;
	}
	public void setDefEight(long defEight) {
		this.defEight = defEight;
	}
	public String getDefNine() {
		return defNine;
	}
	public void setDefNine(String defNine) {
		this.defNine = defNine;
	}
	public String getDefTen() {
		return defTen;
	}
	public void setDefTen(String defTen) {
		this.defTen = defTen;
	}
}
