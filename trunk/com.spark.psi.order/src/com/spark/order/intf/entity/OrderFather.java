package com.spark.order.intf.entity;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;

/**
 * <p>����������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-26
 */
public class OrderFather extends EntityFather{
	@StructField
	protected String orderNumber;//	���۶������	nvarchar	20
	@StructField
	protected GUID partnerId;//	�ͻ�GUID	guid	
	@StructField
	protected String partnerShortName;//	�ͻ�����	nvarchar	20
	@StructField
	protected String partnerName;//	�ͻ�ȫ��	nvarchar	100
	@StructField
	protected String partnerNamePY;//	�ͻ�ƴ��	nvarchar	50
	@StructField
	protected String partnerFax;//	�ͻ�����	nvarchar	20
	@StructField
	protected GUID contactId;//	��ϵ��GUID	guid	
	@StructField
	protected String contactName;//	��ϵ��	nvarchar	40
	@StructField
	protected String contactTel;//	��ϵ�˵绰	nvarchar	20
	@StructField
	protected String contactPhone;//	��ϵ���ֻ�	nvarchar	20
	@StructField
	protected String returnCause;//	�˻�/��ֹԭ��	nvarchar	200
	@StructField
	protected String remark;//	��ע	nvarchar	1000
	@StructField
	protected double totalAmount;//	�ܽ��	numeric	17
	@StructField
	protected String type;//	����	char	2
	@StructField
	protected String status;//	�������	char	2
	@StructField
	protected GUID deptId;//	�������Ų���GUID	guid	
	@StructField
	protected boolean isStoped;//	�Ƿ���ֹ״̬	boolean	
	@StructField
	protected String examin;//	����˼�����	nvarchar	1000
	@StructField
	protected long effectiveDate;//	������Чʱ��	date
	
	@StructField
	protected String defOne;//	�û��Զ����ֶ�1	nvarchar	100
	@StructField
	protected String defTwo;//	�û��Զ����ֶ�2	nvarchar	100
	@StructField
	protected String defThree;//	�û��Զ����ֶ�3	nvarchar	100
	@StructField
	protected double defFour;//	�û��Զ����ֶ�4	numeric	17
	@StructField
	protected double defFive;//	�û��Զ����ֶ�5	numeric	17
	@StructField
	protected double defSix	;//�û��Զ����ֶ�6	numeric	17
	@StructField
	protected long defSeven;//	�û��Զ����ֶ�7	date	
	@StructField
	protected long defEight;//	�û��Զ����ֶ�8	date	
	@StructField
	protected String defNine;//	�û��Զ����ֶ�9	char	2
	@StructField
	protected String defTen	;//�û��Զ����ֶ�10	char	2
	@StructField
	protected long deliveryDate;//	��������	date

	public Long getDeliveryDate() {
		return deliveryDate==0?null:deliveryDate;
	}

	/**
	 * ���ý������ڣ��ɹ��˻��޽������ڣ�
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
