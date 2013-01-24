package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * ��ȡ�⻧������Ϣ���������˻����ջ��ַ����ϵ�ˣ�
 * @author mengyongfeng
 *
 */

public class TenantsInfo {
	//��ʶ
	private GUID id;
	//������д
	private String shortName;
	//����
	private String name;
	
	private String yuduo;
	
	private String code;
	
	//����
	private String legalPerson;
	//�⻧��ַ���� ��ϸ��ַ ����������
	private String province;
	//��
	private String city;
	//��
	private String district;
	//��ϸ��ַ
	private String address;
	//��������
	private String zipcode;
	//�ܾ�������
	private String bossName;
	//�ܾ����Ա�
	private String bossSex;
	//�ܾ���email
	private String bossEmail;
	//�ܾ����ֻ�
	private String bossMobile;
	//�ܾ���绰
	private String bossTel;
	//�ͻ�����
	private String saleManager;
	//�ͻ������ʶ
	private GUID salManagerId;
	//��ϵ
	private String relation;
	//��Դ
	private String source;
	//�������
	private String broadbandAcess;
	//���߽���
	private String wirelessAcess;
	//��ַ
	private String website;
	//������ҵ
	private String industry;
	//��ҵ��ģ
	private String scale;
	//�ܻ�
	private String tel;
	//����
	private String fax;
	//��˰��ʶ���
	private String taxNo;
	//��Ӫģʽ���ۻ�����
	private String opSaleModel;
	//����ģʽ
	private String opShopModel;
	//���������
	private String opChainModel;
	//��������
	private int StoresNum;
	
	//�����˻����
	private double leaseAccountBalance;
	//�����˻����
	private double pieceAccountBalance;
	//�ۼ��տ���
	private double receiptAmount;
	//�ۼƿ�Ʊ���
	private double invoiceAmount;
	//��������
	private int ServeMonth;
	//�״ζ���
	private long firstSignOrderDate;
	//����
	private String remark;
	//������
	private GUID creatorId;
	private String creator;
	//����ʱ��
	private long recordDate;
	
	//�ͻ����
	private String tenantsType;
	
	//״̬
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
