/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.publish.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-13       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.CheckingInInfo;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedInItem;

/**
 * <p>入库单详情</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class CheckingInInfoImpl implements CheckingInInfo{

	/**
	 * 入库单id
	 */
	private GUID sheetId; 

	/**
	 * 创建日期
	 */
	private long createDate;

	/**
	 * 计划入库时间
	 */
	private long planCheckinDate;

	/**
	 * 上次入库时间
	 */
	private long lastCheckinDate;

	/**
	 * 相关单据编号
	 */
	private String relaBillsNo;

	/**
	 * 入库仓库id
	 */
	private GUID storeId;

	/**
	 * 入库仓库名称
	 */
	private String storeName;

	/**
	 * 入库单状态
	 */
	private CheckingInStatus status;

	/**
	 * 入库类型
	 */
	private CheckingInType type;

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
	 * 入库商品列表（采购入库或者销售退货入库）
	 */
	private CheckingGoodsItem[] checkingGoodsItems;

	/**
	 * 入库物品列表（其他物品如入库）
	 */
	private CheckKitItem[] CheckKitItems;

	/**
	 * 确认入库单列表
	 */
	private CheckedInItemImpl[] checkedInItems; 
	
	/**
	 * 物品来源
	 */
	private String goodsFrom;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	} 

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getPlanCheckinDate() {
		return planCheckinDate;
	}

	public void setPlanCheckinDate(long planCheckinDate) {
		this.planCheckinDate = planCheckinDate;
	}

	public long getLastCheckinDate() {
		return lastCheckinDate;
	}

	public void setLastCheckinDate(long lastCheckinDate) {
		this.lastCheckinDate = lastCheckinDate;
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

	public CheckingInStatus getStatus() {
		return status;
	}

	public void setStatus(CheckingInStatus status) {
		this.status = status;
	}

	public CheckingInType getType() {
		return type;
	}

	public void setType(CheckingInType type) {
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

	public CheckingGoodsItem[] getCheckingGoodsItems() {
		return checkingGoodsItems;
	}

	public void setCheckingGoodsItems(CheckingGoodsItem[] checkingGoodsItems) {
		this.checkingGoodsItems = checkingGoodsItems;
	}

	public CheckKitItem[] getCheckKitItems() {
		return CheckKitItems;
	}

	public void setCheckKitItems(CheckKitItem[] CheckKitItems) {
		this.CheckKitItems = CheckKitItems;
	}

	public CheckedInItem[] getCheckedInItems() {
		return checkedInItems;
	}

	public void setCheckedInItems(CheckedInItemImpl[] checkedInItems) {
		this.checkedInItems = checkedInItems;
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

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}

	public String getGoodsFrom() {
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	} 

	public EnumEntity getDealingsWay() { 
		return null;
	} 
	
}
