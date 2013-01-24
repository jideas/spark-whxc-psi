/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.publish.entity
 * �޸ļ�¼��
 * ����                ����           ����
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
 * <p>��ⵥ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class CheckingInInfoImpl implements CheckingInInfo{

	/**
	 * ��ⵥid
	 */
	private GUID sheetId; 

	/**
	 * ��������
	 */
	private long createDate;

	/**
	 * �ƻ����ʱ��
	 */
	private long planCheckinDate;

	/**
	 * �ϴ����ʱ��
	 */
	private long lastCheckinDate;

	/**
	 * ��ص��ݱ��
	 */
	private String relaBillsNo;

	/**
	 * ���ֿ�id
	 */
	private GUID storeId;

	/**
	 * ���ֿ�����
	 */
	private String storeName;

	/**
	 * ��ⵥ״̬
	 */
	private CheckingInStatus status;

	/**
	 * �������
	 */
	private CheckingInType type;

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
	 * �����Ʒ�б��ɹ������������˻���⣩
	 */
	private CheckingGoodsItem[] checkingGoodsItems;

	/**
	 * �����Ʒ�б�������Ʒ����⣩
	 */
	private CheckKitItem[] CheckKitItems;

	/**
	 * ȷ����ⵥ�б�
	 */
	private CheckedInItemImpl[] checkedInItems; 
	
	/**
	 * ��Ʒ��Դ
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
