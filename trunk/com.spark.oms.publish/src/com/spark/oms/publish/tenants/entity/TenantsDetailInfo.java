package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.TenantsRefundStatusEnum;

public class TenantsDetailInfo {
	//�˻���Ϣ
	private AccountInfo[] accountInfos;
	//��ϵ����Ϣ
	private RelatorInfo[] contactInfos;
	//�⻧��ַ
	private  DeliveryAddressInfo[] deliveryAddressInfos;
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
	
	private String refundstatus = TenantsRefundStatusEnum.No.getCode();//Ĭ�����˿�
	
	/**
	 * ��ʧ�ͻ���ʼʱ��
	 */
	private long outDate;
	
	
	
	
	
	public String getRefundstatus() {
		return refundstatus;
	}
	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	public long getOutDate() {
		return outDate;
	}
	public void setOutDate(long outDate) {
		this.outDate = outDate;
	}
	public String getTenantsType() {
		return tenantsType;
	}
	public void setTenantsType(String tenantsType) {
		this.tenantsType = tenantsType;
	}
	public String getTenantstatus() {
		return tenantstatus;
	}
	public void setTenantstatus(String tenantstatus) {
		this.tenantstatus = tenantstatus;
	}
	public AccountInfo[] getAccountInfos() {
		return accountInfos;
	}
	public RelatorInfo[] getContactInfos() {
		return contactInfos;
	}
	public DeliveryAddressInfo[] getDeliveryAddressInfos() {
		return deliveryAddressInfos;
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
	public GUID getSalManagerId() {
		return salManagerId;
	}
	public String getRelation() {
		return relation;
	}
	public String getSource() {
		return source;
	}
	public String getBroadbandAcess() {
		return broadbandAcess;
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
	public void setAccountInfos(AccountInfo[] accountInfos) {
		this.accountInfos = accountInfos;
	}
	public void setContactInfos(RelatorInfo[] contactInfos) {
		this.contactInfos = contactInfos;
	}
	public void setDeliveryAddressInfos(DeliveryAddressInfo[] deliveryAddressInfos) {
		this.deliveryAddressInfos = deliveryAddressInfos;
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
	public void setSalManagerId(GUID salManagerId) {
		this.salManagerId = salManagerId;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setBroadbandAcess(String broadbandAcess) {
		this.broadbandAcess = broadbandAcess;
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
