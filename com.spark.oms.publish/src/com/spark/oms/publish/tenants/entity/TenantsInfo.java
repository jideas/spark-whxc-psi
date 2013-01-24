package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * 获取租户基础信息（不包括账户，收获地址，联系人）
 * @author mengyongfeng
 *
 */

public class TenantsInfo {
	//标识
	private GUID id;
	//名称缩写
	private String shortName;
	//名称
	private String name;
	
	private String yuduo;
	
	private String code;
	
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
	private GUID salManagerId;
	//关系
	private String relation;
	//来源
	private String source;
	//宽带接入
	private String broadbandAcess;
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
	
	//客户类别
	private String tenantsType;
	
	//状态
	private String tenantstatus;
	
	private String isSystem;
	
	
	public String getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
	public String getTenantstatus() {
		return tenantstatus;
	}
	public void setTenantstatus(String tenantstatus) {
		this.tenantstatus = tenantstatus;
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
	public void setId(GUID id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public String getBossSex() {
		return bossSex;
	}
	public void setBossSex(String bossSex) {
		this.bossSex = bossSex;
	}
	public String getBossEmail() {
		return bossEmail;
	}
	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}
	public String getBossMobile() {
		return bossMobile;
	}
	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}
	public String getBossTel() {
		return bossTel;
	}
	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}
	public String getSaleManager() {
		return saleManager;
	}
	public void setSaleManager(String saleManager) {
		this.saleManager = saleManager;
	}
	public GUID getSalManagerId() {
		return salManagerId;
	}
	public void setSalManagerId(GUID salManagerId) {
		this.salManagerId = salManagerId;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBroadbandAcess() {
		return broadbandAcess;
	}
	public void setBroadbandAcess(String broadbandAcess) {
		this.broadbandAcess = broadbandAcess;
	}
	public String getWirelessAcess() {
		return wirelessAcess;
	}
	public void setWirelessAcess(String wirelessAcess) {
		this.wirelessAcess = wirelessAcess;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getOpSaleModel() {
		return opSaleModel;
	}
	public void setOpSaleModel(String opSaleModel) {
		this.opSaleModel = opSaleModel;
	}
	public String getOpShopModel() {
		return opShopModel;
	}
	public void setOpShopModel(String opShopModel) {
		this.opShopModel = opShopModel;
	}
	public String getOpChainModel() {
		return opChainModel;
	}
	public void setOpChainModel(String opChainModel) {
		this.opChainModel = opChainModel;
	}
	public int getStoresNum() {
		return StoresNum;
	}
	public void setStoresNum(int storesNum) {
		StoresNum = storesNum;
	}
	public double getLeaseAccountBalance() {
		return leaseAccountBalance;
	}
	public void setLeaseAccountBalance(double leaseAccountBalance) {
		this.leaseAccountBalance = leaseAccountBalance;
	}
	public double getPieceAccountBalance() {
		return pieceAccountBalance;
	}
	public void setPieceAccountBalance(double pieceAccountBalance) {
		this.pieceAccountBalance = pieceAccountBalance;
	}
	public double getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public int getServeMonth() {
		return ServeMonth;
	}
	public void setServeMonth(int serveMonth) {
		ServeMonth = serveMonth;
	}
	public long getFirstSignOrderDate() {
		return firstSignOrderDate;
	}
	public void setFirstSignOrderDate(long firstSignOrderDate) {
		this.firstSignOrderDate = firstSignOrderDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(long recordDate) {
		this.recordDate = recordDate;
	}
	public String getYuduo() {
		return yuduo;
	}
	public void setYuduo(String yuduo) {
		this.yuduo = yuduo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
