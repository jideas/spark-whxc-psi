package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;
/**
 * ������������<br>
 * ��ѯ������ͨ�����ⵥID��ѯCheckingOutInfo����
 */
public class CheckingOutInfoImpl implements CheckingOutInfo{

	/**
	 * ���ⵥid
	 */
	private GUID sheetId; 

	/**
	 * ��������
	 */
	private long createDate;

	/**
	 * �ƻ�����ʱ��
	 */
	private long planCheckoutDate;

	/**
	 * �ϴγ���ʱ��
	 */
	private long lastCheckoutDate;

	/**
	 * ��ص��ݱ��
	 */
	private String relaBillsNo;

	/**
	 * ����ֿ�id
	 */
	private GUID storeId;

	/**
	 * ����ֿ�����
	 */
	private String storeName;

	/**
	 * ���ⵥ״̬
	 */
	private CheckingOutStatus status;

	/**
	 * ��������
	 */
	private CheckingOutType type;

	/**
	 * �ͻ����߹�Ӧ��ID
	 */
	private GUID partnerId;

	/**
	 * �ͻ����߹�Ӧ������
	 */
	private String partnerName;

	/**
	 * ��ע��Ϣ
	 */
	private String remark;

	/**
	 * ��ֹԭ����ֹ״̬��
	 */
	private String stopReason;
	private boolean stoped;
	
	/**
	 * ��Ʒ��Դ
	 */
	public String goodsFrom;
	
	/**
	 * ��Ʒ��;
	 */
	public String goodsUse;
 
	/**
	 * ������Ʒ�б����۳�����߲ɹ��˻����⣩
	 */
	private CheckingGoodsItemImpl[] checkingGoodsItems;

	/**
	 * ������Ʒ�б�������Ʒ���⣩
	 */
	private CheckKitItemImpl[] CheckKitItems;

	/**
	 * ȷ�ϴ����¼�б�
	 */
	private CheckedOutItemImpl[] checkedOutItems;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	} 

	public long getCreateDate() {
		return createDate;
	}

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getPlanCheckoutDate() {
		return planCheckoutDate;
	}

	public void setPlanCheckoutDate(long planCheckoutDate) {
		this.planCheckoutDate = planCheckoutDate;
	}

	public long getLastCheckoutDate() {
		return lastCheckoutDate;
	}

	public void setLastCheckoutDate(long lastCheckoutDate) {
		this.lastCheckoutDate = lastCheckoutDate;
	} 

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public CheckingOutStatus getStatus() {
		return status;
	}

	public void setStatus(CheckingOutStatus status) {
		this.status = status;
	}

	public CheckingOutType getType() {
		return type;
	}

	public void setType(CheckingOutType type) {
		this.type = type;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	} 

	public String getRelaBillsNo() {
		return relaBillsNo;
	}

	public void setRelaBillsNo(String relaBillsNo) {
		this.relaBillsNo = relaBillsNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}

	public String getGoodsUse() {
		return goodsUse;
	}

	public void setGoodsUse(String goodsUse) {
		this.goodsUse = goodsUse;
	}

	public CheckingGoodsItemImpl[] getCheckingGoodsItems() {
		return checkingGoodsItems;
	}

	public void setCheckingGoodsItems(CheckingGoodsItemImpl[] checkingGoodsItems) {
		this.checkingGoodsItems = checkingGoodsItems;
	}

	public CheckKitItemImpl[] getCheckKitItems() {
		return CheckKitItems;
	}

	public void setCheckKitItems(CheckKitItemImpl[] CheckKitItems) {
		this.CheckKitItems = CheckKitItems;
	}

	public CheckedOutItemImpl[] getCheckedOutItems() {
		return checkedOutItems;
	}

	public void setCheckedOutItems(CheckedOutItemImpl[] checkedOutItems) {
		this.checkedOutItems = checkedOutItems;
	}

	/**
	 * ȷ�ϳ����¼
	 */
}
