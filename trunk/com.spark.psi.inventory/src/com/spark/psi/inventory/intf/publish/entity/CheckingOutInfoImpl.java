package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;
/**
 * 出库需求详情<br>
 * 查询方法：通过出库单ID查询CheckingOutInfo对象
 */
public class CheckingOutInfoImpl implements CheckingOutInfo{

	/**
	 * 出库单id
	 */
	private GUID sheetId; 

	/**
	 * 创建日期
	 */
	private long createDate;

	/**
	 * 计划出库时间
	 */
	private long planCheckoutDate;

	/**
	 * 上次出库时间
	 */
	private long lastCheckoutDate;

	/**
	 * 相关单据编号
	 */
	private String relaBillsNo;

	/**
	 * 出库仓库id
	 */
	private GUID storeId;

	/**
	 * 出库仓库名称
	 */
	private String storeName;

	/**
	 * 出库单状态
	 */
	private CheckingOutStatus status;

	/**
	 * 出库类型
	 */
	private CheckingOutType type;

	/**
	 * 客户或者供应商ID
	 */
	private GUID partnerId;

	/**
	 * 客户或者供应商名称
	 */
	private String partnerName;

	/**
	 * 备注信息
	 */
	private String remark;

	/**
	 * 中止原因（中止状态）
	 */
	private String stopReason;
	private boolean stoped;
	
	/**
	 * 物品来源
	 */
	public String goodsFrom;
	
	/**
	 * 物品用途
	 */
	public String goodsUse;
 
	/**
	 * 出库商品列表（销售出库或者采购退货出库）
	 */
	private CheckingGoodsItemImpl[] checkingGoodsItems;

	/**
	 * 出库物品列表（其他物品出库）
	 */
	private CheckKitItemImpl[] CheckKitItems;

	/**
	 * 确认处理记录列表
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
	 * 确认出库记录
	 */
}
