package com.spark.psi.base.utils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.base.publicimpl.BankAccountItemImpl;
import com.spark.psi.base.publicimpl.CustomerInfoImpl;
import com.spark.psi.base.publicimpl.StationInfoImpl;
import com.spark.psi.base.publicimpl.SupplierInfoImpl;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 数据库查询服务小工具
 */
public abstract class QueryTableColumnUtil {

	/**
	 * 设置供应商详情查询列
	 */
	public static void setSupplierInfoColumn(QuerySqlBuilder qb) {
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.supplierName", "supplierName");
		qb.addColumn("t.status", "status");
		qb.addColumn("t.shortName", "shortName");
		qb.addColumn("t.supplierNo", "supplierNo");
		qb.addColumn("t.supplierType", "supplierType");
		qb.addColumn("t.vatNo", "vatNo");
		qb.addColumn("t.province", "province");
		qb.addColumn("t.taxRate", "taxRate");
		qb.addColumn("t.city", "city");
		qb.addColumn("t.town", "town");
		qb.addColumn("t.address", "address");
		qb.addColumn("t.remark", "remark");
		qb.addColumn("t.postcode", "postcode");
		qb.addColumn("t.noticeAddress", "noticeAddress");
		qb.addColumn("t.noticePostcode", "noticePostcode");
		qb.addColumn("t.workTel", "workTel");
		qb.addColumn("t.fax", "fax");
		qb.addColumn("t.accountPeriod", "accountPeriod");
		qb.addColumn("t.accountRemind", "accountRemind");
		qb.addColumn("t.linkmanName", "linkmanName");
		qb.addColumn("t.linkmanSuffix", "linkmanSuffix");
		qb.addColumn("t.linkmanTel", "linkmanTel");
		qb.addColumn("t.linkmanMobile", "linkmanMobile");
		qb.addColumn("t.linkmanEmail", "linkmanEmail");
		qb.addColumn("t.canDelete", "canDelete");
		qb.addColumn("t.creditAmount", "creditAmount");
		qb.addColumn("t.createDate", "createDate");
		qb.addColumn("t.creatorId", "creatorId");
		qb.addColumn("t.creator", "creator");
		qb.addColumn("t.cooperation", "cooperation");
	}

	/**
	 * 得到查询后的供应商详情
	 */
	public static SupplierInfoImpl getSupplierInfo(Context context, RecordSet rs) {
		int i = 0;
		SupplierInfoImpl info = new SupplierInfoImpl();
		info.setId(rs.getFields().get(i++).getGUID());
		info.setName(rs.getFields().get(i++).getString());
		info.setStatus(PartnerStatus.PartnerStatusByCode(rs.getFields().get(i++).getString()));
		info.setShortName(rs.getFields().get(i++).getString());
		info.setNumber(rs.getFields().get(i++).getString());
		info.setSupplierType(rs.getFields().get(i++).getString());
		info.setVatNo(rs.getFields().get(i++).getString());
		info.setProvince(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString()));
		info.setTaxRate(rs.getFields().get(i++).getDouble());
		info.setCity(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString()));
		info.setTown(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString()));
		info.setAddress(rs.getFields().get(i++).getString());
		info.setRemark(rs.getFields().get(i++).getString());
		info.setPostcode(rs.getFields().get(i++).getString());
		info.setNoticeAddress(rs.getFields().get(i++).getString());
		info.setNoticePostcode(rs.getFields().get(i++).getString());
		info.setWorkTel(rs.getFields().get(i++).getString());
		info.setFax(rs.getFields().get(i++).getString());
		info.setAccountPeriod(rs.getFields().get(i++).getInt());
		info.setAccountRemind(rs.getFields().get(i++).getInt());
		info.setLinkmanName(rs.getFields().get(i++).getString());
		info.setLinkmanSuffix(rs.getFields().get(i++).getString());
		info.setLinkmanTel(rs.getFields().get(i++).getString());
		info.setLinkmanMobile(rs.getFields().get(i++).getString());
		info.setLinkmanEmail(rs.getFields().get(i++).getString());
		info.setUsed(!rs.getFields().get(i++).getBoolean());
		info.setCreditAmount(rs.getFields().get(i++).getDouble());
		info.setCreateDate(rs.getFields().get(i++).getDate());
		info.setCreatorId(rs.getFields().get(i++).getGUID());
		info.setCreator(rs.getFields().get(i++).getString());
		info.setSupplierCooperation(rs.getFields().get(i++).getString());
		return info;
	}

	/**
	 * 设置银行账户查询字段
	 */
	public static void setBankAccountColumn(QuerySqlBuilder qb) {
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.bankName", "bankName");
		qb.addColumn("t.accountHolder", "accountHolder");
		qb.addColumn("t.account", "account");
		qb.addColumn("t.remark", "remark");
	}

	/**
	 * 得到查询后的银行账户
	 */
	public static BankAccountItemImpl getBankAccountItem(RecordSet rs, GUID supplierId) {
		BankAccountItemImpl item = new BankAccountItemImpl();
		int i = 0;
		item.setId(rs.getFields().get(i++).getGUID());
		item.setSupplierId(supplierId);
		item.setBankName(rs.getFields().get(i++).getString());
		item.setAccountHolder(rs.getFields().get(i++).getString());
		item.setAccount(rs.getFields().get(i++).getString());
		item.setRemark(rs.getFields().get(i++).getString());
		return item;
	}

	/**
	 * 设置客户详情查询列
	 */
	public static void setCustomerInfoColumn(QuerySqlBuilder qb) {
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.customerName", "customerName");
		qb.addColumn("t.shortName", "shortName");
		qb.addColumn("t.customerNo", "customerNo");
		qb.addColumn("t.status", "status");
		qb.addColumn("t.businessPerson", "businessPerson");
		qb.addColumn("t.province", "province");
		qb.addColumn("t.pricePolicy", "pricePolicy");
		qb.addColumn("t.city", "city");
		qb.addColumn("t.town", "town");
		qb.addColumn("t.address", "address");
		qb.addColumn("t.remark", "remark");
		qb.addColumn("t.postcode", "postcode");
		qb.addColumn("t.workTel", "workTel");
		qb.addColumn("t.fax", "fax");
		qb.addColumn("t.accountPeriod", "accountPeriod");
		qb.addColumn("t.accountRemind", "accountRemind");
		qb.addColumn("t.linkmanName", "linkmanName");
		qb.addColumn("t.linkmanSuffix", "linkmanSuffix");
		qb.addColumn("t.linkmanTel", "linkmanTel");
		qb.addColumn("t.linkmanMobile", "linkmanMobile");
		qb.addColumn("t.linkmanEmail", "linkmanEmail");
		qb.addColumn("t.canDelete", "canDelete");
		qb.addColumn("t.creditAmount", "creditAmount");
		qb.addColumn("t.createDate", "createDate");
		qb.addColumn("t.creatorId", "creatorId");
		qb.addColumn("t.creator", "creator");
		qb.addColumn("t.taxNumber", "taxNumber");
		qb.addColumn("t.customerType", "customerType");
	}

	/**
	 *得到查询后的客户详情
	 */
	public static CustomerInfoImpl getCustomerInfo(Context context, RecordSet rs) {
		CustomerInfoImpl info = new CustomerInfoImpl();
		int i = 0;
		info.setId(rs.getFields().get(i++).getGUID());
		info.setName(rs.getFields().get(i++).getString());
		info.setShortName(rs.getFields().get(i++).getString());
		info.setNumber(rs.getFields().get(i++).getString());
		info.setStatus(PartnerStatus.PartnerStatusByCode(rs.getFields().get(i++).getString()));
		GUID busPerson = rs.getFields().get(i++).getGUID();
		if (null != busPerson) {
			info.setBusinessPerson(context.find(EmployeeInfo.class, busPerson));
		}
		info.setProvince(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString()));
		info.setPricePolicy(rs.getFields().get(i++).getString());
		info.setCity(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString()));
		info.setTown(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString()));
		info.setAddress(rs.getFields().get(i++).getString());
		info.setRemark(rs.getFields().get(i++).getString());
		info.setPostcode(rs.getFields().get(i++).getString());
		info.setWorkTel(rs.getFields().get(i++).getString());
		info.setFax(rs.getFields().get(i++).getString());
		info.setAccountPeriod(rs.getFields().get(i++).getInt());
		info.setAccountRemind(rs.getFields().get(i++).getInt());
		info.setLinkmanName(rs.getFields().get(i++).getString());
		info.setLinkmanSuffix(rs.getFields().get(i++).getString());
		info.setLinkmanTel(rs.getFields().get(i++).getString());
		info.setLinkmanMobile(rs.getFields().get(i++).getString());
		info.setLinkmanEmail(rs.getFields().get(i++).getString());
		info.setUsed(!rs.getFields().get(i++).getBoolean());
		info.setCreditAmount(rs.getFields().get(i++).getDouble());
		info.setCreateDate(rs.getFields().get(i++).getDate());
		info.setCreatorId(rs.getFields().get(i++).getGUID());
		info.setCreator(rs.getFields().get(i++).getString());
		info.setTaxNumber(rs.getFields().get(i++).getString());
		info.setCustomerType(rs.getFields().get(i++).getString());
		return info;
	}

	/**
	 * 设置站点详情查询咧
	 */
	public static void setStationInfoColumn(QuerySqlBuilder qb) {
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.stationNo", "stationNo");
		qb.addColumn("t.stationName", "stationName");
		qb.addColumn("t.shortName", "shortName");
		qb.addColumn("t.telephone", "telephone");
		qb.addColumn("t.fax", "fax");
		qb.addColumn("t.remark", "remark");
		qb.addColumn("t.town", "town");
		qb.addColumn("t.province", "province");
		qb.addColumn("t.city", "city");
		qb.addColumn("t.address", "address");
		qb.addColumn("t.managerId", "managerId");
		qb.addColumn("t.manager", "manager");
		qb.addColumn("t.managerPersonId", "managerPersonId");
		qb.addColumn("t.managerPhone", "managerPhone");
		qb.addColumn("t.managerEmail", "managerEmail");
		qb.addColumn("t.createDate", "createDate");
		qb.addColumn("t.creatorId", "creatorId");
		qb.addColumn("t.creator", "creator");
	}

	/**
	 * 获得查询后的站点对象
	 */
	public static StationInfoImpl getStationInfo(Context context, RecordSet rs) {
		StationInfoImpl impl = new StationInfoImpl();
		int i = 0;
		impl.setId(rs.getFields().get(i++).getGUID());
		String stationNo = rs.getFields().get(i++).getString();
		String str1 = stationNo.substring(0, 4);
		String str2 = stationNo.substring(4, 6);
		String str3 = stationNo.substring(6);
		if (str2.indexOf("4") >= 0) {
			str2 = str2.replace("4", "A");
			stationNo = str1 + str2 + str3;
		}
		impl.setStationNo(stationNo);
		impl.setStationName(rs.getFields().get(i++).getString());
		impl.setShortName(rs.getFields().get(i++).getString());
		impl.setTelephone(rs.getFields().get(i++).getString());
		impl.setFax(rs.getFields().get(i++).getString());
		impl.setRemark(rs.getFields().get(i++).getString());
		impl.setTown(rs.getFields().get(i++).getString());
		impl.setProvince(rs.getFields().get(i++).getString());
		impl.setCity(rs.getFields().get(i++).getString());
		impl.setAddress(rs.getFields().get(i++).getString());
		impl.setManagerId(rs.getFields().get(i++).getGUID());
		impl.setManager(rs.getFields().get(i++).getString());
		impl.setManagerPersonId(rs.getFields().get(i++).getString());
		impl.setManagerPhone(rs.getFields().get(i++).getString());
		impl.setManagerEmail(rs.getFields().get(i++).getString());
		impl.setCreateDate(rs.getFields().get(i++).getDate());
		impl.setCreatorId(rs.getFields().get(i++).getGUID());
		impl.setCreator(rs.getFields().get(i++).getString());
		return impl;
	}

}
