package com.spark.oms.publish.tenants.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.TenantsRefundStatusEnum;
import com.spark.oms.publish.tenants.entity.AccountInfo;
import com.spark.oms.publish.tenants.entity.DeliveryAddressInfo;
import com.spark.oms.publish.tenants.entity.RelatorInfo;

/**
 * 保存、修改租户信息
 */
public class CreateTenantsTask extends SimpleTask {
	//账户信息
	private List<AccountInfo> accountInfos;
	//联系人信息
	private List<RelatorInfo> contactInfos;
	//租户地址
	private  List<DeliveryAddressInfo> deliveryAddressInfos;
	
	//删除的账户信息
	private List<GUID> deletedAccountInfos;
	//删除的联系人信息
	private List<GUID> deletedContactInfos;
	//删除的租户地址
	private  List<GUID> deletedDeliveryAddressInfos;
	
	//标识
	private GUID id;
	//名称缩写
	private String shortName;
	//名称
	private String name;
	//法人
	private String legalPerson;
	//租户地址市县 详细地址 及邮政编码
	private String province;
	//市
	private String city;
	//县
	private String district;
	//详细地址
	private String address;
	//邮政编码
	private String zipcode;
	//总经理姓名
	private String bossName;
	//总经理性别
	private String bossSex;
	//总经理email
	private String bossEmail;
	//总经理手机
	private String bossMobile;
	//总经理电话
	private String bossTel;
	//客户经理
	private String saleManager;
	//客户经理标识
	private GUID saleManagerId;
	//关系
	private String relation;
	//来源
	private String source;
	//宽带接入
	private String broadbanDAcess;
	//无线接入
	private String wirelessAcess;
	//网址
	private String website;
	//所属行业
	private String industry;
	//企业规模
	private String scale;
	//总机
	private String tel;
	//传真
	private String fax;
	//纳税人识别号
	private String taxNo;
	//经营模式零售或批发
	private String opSaleModel;
	//店铺模式
	private String opShopModel;
	//单店或连锁
	private String opChainModel;
	//连锁家数
	private int StoresNum;
	
	//租赁账户金额
	private double leaseAccountBalance;
	//计量账户金额
	private double pieceAccountBalance;
	//累计收款金额
	private double receiptAmount;
	//累计开票金额
	private double invoiceAmount;
	//服务期限
	private int ServeMonth;
	//首次订单
	private long firstSignOrderDate;
	//描述
	private String remark;
	//创建人
	private GUID creatorId;
	private String creator;
	//创建时间
	private long recordDate;
	
	//创建网上访问
	private String broadbandAcess;
	//客户类型
	private String tenantsType;
	
	//状态
	private String tenantstatus;
	
	
	private String refundstatus = TenantsRefundStatusEnum.No.getCode();//默认无退款
	
	private long outDate;
	
	
	public long getOutDate() {
		return outDate;
	}
	public void setOutDate(long outDate) {
		this.outDate = outDate;
	}

	public String getRefundstatus() {
		return refundstatus;
	}
	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	public String getTenantstatus() {
		return tenantstatus;
	}
	public void setTenantstatus(String tenantstatus) {
		this.tenantstatus = tenantstatus;
	}
	public String getBroadbandAcess() {
		return broadbandAcess;
	}
	public void setBroadbandAcess(String broadbandAcess) {
		this.broadbandAcess = broadbandAcess;
	}
	public String getTenantsType() {
		return tenantsType;
	}
	public void setTenantsType(String tenantsType) {
		this.tenantsType = tenantsType;
	}
	public GUID getId() {
		return id;
	}
	public String getShortName() {
		return shortName;
	}
	public String getName() {
		return name;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getDistrict() {
		return district;
	}
	public String getAddress() {
		return address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public String getBossName() {
		return bossName;
	}
	public String getBossSex() {
		return bossSex;
	}
	public String getBossEmail() {
		return bossEmail;
	}
	public String getBossMobile() {
		return bossMobile;
	}
	public String getBossTel() {
		return bossTel;
	}
	public String getSaleManager() {
		return saleManager;
	}
	public GUID getSaleManagerId() {
		return saleManagerId;
	}
	public String getRelation() {
		return relation;
	}
	public String getSource() {
		return source;
	}
	public String getBroadbanDAcess() {
		return broadbanDAcess;
	}
	public String getWirelessAcess() {
		return wirelessAcess;
	}
	public String getWebsite() {
		return website;
	}
	public String getIndustry() {
		return industry;
	}
	public String getScale() {
		return scale;
	}
	public String getTel() {
		return tel;
	}
	public String getFax() {
		return fax;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public String getOpSaleModel() {
		return opSaleModel;
	}
	public String getOpShopModel() {
		return opShopModel;
	}
	public String getOpChainModel() {
		return opChainModel;
	}
	public int getStoresNum() {
		return StoresNum;
	}
	public double getLeaseAccountBalance() {
		return leaseAccountBalance;
	}
	public double getPieceAccountBalance() {
		return pieceAccountBalance;
	}
	public double getReceiptAmount() {
		return receiptAmount;
	}
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public int getServeMonth() {
		return ServeMonth;
	}
	public long getFirstSignOrderDate() {
		return firstSignOrderDate;
	}
	public String getRemark() {
		return remark;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public long getRecordDate() {
		return recordDate;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public void setBossSex(String bossSex) {
		this.bossSex = bossSex;
	}
	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}
	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}
	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}
	public void setSaleManager(String saleManager) {
		this.saleManager = saleManager;
	}
	public void setSaleManagerId(GUID saleManagerId) {
		this.saleManagerId = saleManagerId;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setBroadbanDAcess(String broadbanDAcess) {
		this.broadbanDAcess = broadbanDAcess;
	}
	public void setWirelessAcess(String wirelessAcess) {
		this.wirelessAcess = wirelessAcess;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public void setOpSaleModel(String opSaleModel) {
		this.opSaleModel = opSaleModel;
	}
	public void setOpShopModel(String opShopModel) {
		this.opShopModel = opShopModel;
	}
	public void setOpChainModel(String opChainModel) {
		this.opChainModel = opChainModel;
	}
	public void setStoresNum(int storesNum) {
		StoresNum = storesNum;
	}
	public void setLeaseAccountBalance(double leaseAccountBalance) {
		this.leaseAccountBalance = leaseAccountBalance;
	}
	public void setPieceAccountBalance(double pieceAccountBalance) {
		this.pieceAccountBalance = pieceAccountBalance;
	}
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public void setServeMonth(int serveMonth) {
		ServeMonth = serveMonth;
	}
	public void setFirstSignOrderDate(long firstSignOrderDate) {
		this.firstSignOrderDate = firstSignOrderDate;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setRecordDate(long recordDate) {
		this.recordDate = recordDate;
	}
	public List<AccountInfo> getAccountInfos() {
		return accountInfos;
	}
	public void setAccountInfos(List<AccountInfo> accountInfos) {
		this.accountInfos = accountInfos;
	}
	public List<RelatorInfo> getContactInfos() {
		return contactInfos;
	}
	public void setContactInfos(List<RelatorInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}
	public List<DeliveryAddressInfo> getDeliveryAddressInfos() {
		return deliveryAddressInfos;
	}
	public void setDeliveryAddressInfos(
			List<DeliveryAddressInfo> deliveryAddressInfos) {
		this.deliveryAddressInfos = deliveryAddressInfos;
	}
	public List<GUID> getDeletedAccountInfos() {
		return deletedAccountInfos;
	}
	public void setDeletedAccountInfos(List<GUID> deletedAccountInfos) {
		this.deletedAccountInfos = deletedAccountInfos;
	}
	public List<GUID> getDeletedContactInfos() {
		return deletedContactInfos;
	}
	public void setDeletedContactInfos(List<GUID> deletedContactInfos) {
		this.deletedContactInfos = deletedContactInfos;
	}
	public List<GUID> getDeletedDeliveryAddressInfos() {
		return deletedDeliveryAddressInfos;
	}
	public void setDeletedDeliveryAddressInfos(
			List<GUID> deletedDeliveryAddressInfos) {
		this.deletedDeliveryAddressInfos = deletedDeliveryAddressInfos;
	}
	
}
