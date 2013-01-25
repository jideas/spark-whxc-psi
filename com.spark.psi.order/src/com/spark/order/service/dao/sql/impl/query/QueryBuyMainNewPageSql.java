package com.spark.order.service.dao.sql.impl.query;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.key.SelectPurchaseSubRebutKey;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.service.dao.sql.impl.QuerySql;

/**
 * <p>
 * 查询采购订单主列表sql
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author 莫迪
 * @version 2011-11-23
 */
public class QueryBuyMainNewPageSql extends QuerySql {

	public QueryBuyMainNewPageSql(Context context) {
		super(context);
	}

	/**
	 * 获得查询结果集
	 * 
	 * @return List<SaleOrdInfo>
	 */
	public List<PurchaseOrderInfo> getList(RecordSet rs) {
		List<PurchaseOrderInfo> list = new ArrayList<PurchaseOrderInfo>();
		while (rs.next()) {
			PurchaseOrderInfo billsInfo = new PurchaseOrderInfo();
			int index = 0;
			billsInfo.setRECID(rs.getFields().get(index++).getGUID());
			billsInfo.setBillsNo(rs.getFields().get(index++).getString());
			billsInfo.setPartnerId(rs.getFields().get(index++).getGUID());
			billsInfo.setPartnerName(rs.getFields().get(index++).getString());
			billsInfo.setPartnerNamePY(rs.getFields().get(index++).getString());
			billsInfo.setPartnerShortName(rs.getFields().get(index++).getString());
			billsInfo.setBillType(rs.getFields().get(index++).getString());
			billsInfo.setLinkman(rs.getFields().get(index++).getString());
			billsInfo.setAddress(rs.getFields().get(index++).getString());
			// index++;
			billsInfo.setRejectReason(rs.getFields().get(index++).getString());
			// dnaSql.append(" t.stopReason      as       stopReason     ,\n");
			billsInfo.setStopReason(rs.getFields().get(index++).getString());
			// dnaSql.append(" t.remark          as       remark         ,\n");
			billsInfo.setRemark(rs.getFields().get(index++).getString());
			// dnaSql.append(" t.totalAmount     as       totalAmount    ,\n");
			billsInfo.setTotalAmount(rs.getFields().get(index++).getDouble());
			// dnaSql.append(" t.creatorId       as       creatorId      ,\n");
			billsInfo.setCreatorId(rs.getFields().get(index++).getGUID());
			// dnaSql.append(" t.creator         as       creator        ,\n");
			billsInfo.setCreator(rs.getFields().get(index++).getString());
			// dnaSql.append(" t.createDate      as       createDate     ,\n");
			billsInfo.setCreateDate(rs.getFields().get(index++).getDate());
			// dnaSql.append(" t.status          as       status         ,\n");
			billsInfo.setStatus(rs.getFields().get(index++).getString());
			// dnaSql.append(" t.deptId          as       deptId         ,\n");
			billsInfo.setDeptId(rs.getFields().get(index++).getGUID());
			// dnaSql.append(" t.isStoped        as       isStoped       ,\n");
			billsInfo.setStoped(rs.getFields().get(index++).getBoolean());

			// dnaSql.append(" t.approveStr      as       approveStr     ,\n");
			billsInfo.setApproveStr(rs.getFields().get(index++).getString());
			// dnaSql.append(" t.storeId         as       storeId        ,\n");
			index++;
			// dnaSql.append(" t.storeName       as       storeName      ,\n");
			index++;
			// dnaSql.append(" t.finishedDate    as       finishedDate \n");
			billsInfo.setFinishedDate(rs.getFields().get(index++).getDate());

			list.add(billsInfo);
		}
		return list;
	}

	/**
	 * 获得查询结果集
	 * 
	 * @return List<SaleOrdInfo>
	 */
	public List<SaleOrderInfo> getSaleList(RecordSet rs) {
		List<SaleOrderInfo> list = new ArrayList<SaleOrderInfo>();
		while (rs.next()) {
			SaleOrderInfo billsInfo = new SaleOrderInfo();
			int index = 0;
			billsInfo.setRECID(rs.getFields().get(index++).getGUID());
			billsInfo.setBillsNo(rs.getFields().get(index++).getString());
			billsInfo.setPartnerId(rs.getFields().get(index++).getGUID());
			billsInfo.setPartnerName(rs.getFields().get(index++).getString());
			billsInfo.setPartnerNamePY(rs.getFields().get(index++).getString());
			billsInfo.setPartnerShortName(rs.getFields().get(index++).getString());
			// sql.append(" t.billType        as       billType       ,\n");
			billsInfo.setBillType(rs.getFields().get(index++).getString());
			// sql.append(" t.linkman         as       linkman        ,\n");
			billsInfo.setLinkman(rs.getFields().get(index++).getString());
			// sql.append(" t.address         as       address        ,\n");
			billsInfo.setAddress(rs.getFields().get(index++).getString());
			// sql.append(" t.rejectReason    as       rejectReason   ,\n");
			billsInfo.setRejectReason(rs.getFields().get(index++).getString());
			// sql.append(" t.stopReason      as       stopReason     ,\n");
			billsInfo.setStopReason(rs.getFields().get(index++).getString());
			// sql.append(" t.remark          as       remark         ,\n");
			billsInfo.setRemark(rs.getFields().get(index++).getString());
			// sql.append(" t.totalAmount     as       totalAmount    ,\n");
			billsInfo.setTotalAmount(rs.getFields().get(index++).getDouble());
			// sql.append(" t.creatorId       as       creatorId      ,\n");
			billsInfo.setCreatorId(rs.getFields().get(index++).getGUID());
			// sql.append(" t.creator         as       creator        ,\n");
			billsInfo.setCreator(rs.getFields().get(index++).getString());
			// sql.append(" t.createDate      as       createDate     ,\n");
			billsInfo.setCreateDate(rs.getFields().get(index++).getDate());
			// sql.append(" t.status          as       status         ,\n");
			billsInfo.setStatus(rs.getFields().get(index++).getString());
			// sql.append(" t.deptId          as       deptId         ,\n");
			billsInfo.setDeptId(rs.getFields().get(index++).getGUID());
			// sql.append(" t.isStoped        as       isStoped       ,\n");
			billsInfo.setStoped(rs.getFields().get(index++).getBoolean());
			// sql.append(" t.approveStr      as       approveStr     ,\n");
			billsInfo.setApproveStr(rs.getFields().get(index++).getString());
			// sql.append(" t.storeId         as       storeId        ,\n");
			index++;
			// sql.append(" t.storeName       as       storeName      ,\n");
			index++;
			// sql.append(" t.finishedDate    as       finishedDate \n");
			billsInfo.setFinishedDate(rs.getFields().get(index++).getDate());
			list.add(billsInfo);
		}
		return list;
	}

	public String setParameter() {
		// where t.tenantsGuid = @tenants and t.createGuid = @userid and
		// (t.status = @status1 or t.status = @status2)
		return "@tenants guid, @userid guid, @status1 string, @status2 string";
	}

	public String setSql(SelectKey key) {
		return setSql((SelectPurchaseSubRebutKey) key);
	}

	public String setSql(SelectPurchaseSubRebutKey key) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select \n");
		sql.append(" t.RECID as recid, \n");
		sql.append(" t.billsNo as billsNo, \n");
		sql.append(" t.partnerId      as       supplierId     ,\n");
		sql.append(" t.partnerName    as       supplierName   ,\n");
		sql.append(" t.partnerNamePY  as       supplierNamePY ,\n");
		sql.append(" t.shortName       as       shortName      ,\n");
		sql.append(" t.billType        as       billType       ,\n");
		sql.append(" t.linkman         as       linkman        ,\n");
		sql.append(" t.address         as       address        ,\n");
		sql.append(" t.rejectReason    as       rejectReason   ,\n");
		sql.append(" t.stopReason      as       stopReason     ,\n");
		sql.append(" t.remark          as       remark         ,\n");
		sql.append(" t.totalAmount     as       totalAmount    ,\n");
		sql.append(" t.creatorId       as       creatorId      ,\n");
		sql.append(" t.creator         as       creator        ,\n");
		sql.append(" t.createDate      as       createDate     ,\n");
		sql.append(" t.status          as       status         ,\n");
		sql.append(" t.deptId          as       deptId         ,\n");
		sql.append(" t.isStoped        as       isStoped       ,\n");
		sql.append(" t.approveStr      as       approveStr     ,\n");
		sql.append(" t.storeId         as       storeId        ,\n");
		sql.append(" t.storeName       as       storeName      ,\n");
		sql.append(" t.finishedDate    as       finishedDate \n");

		sql.append(" from ");
		sql.append(key.getMainKey().getBillsEnum().getDb_table());
		sql.append(" as t ");
		sql.append(" where ");
		sql.append("t.creatorId = @userid ");
		sql.append(" and ");
		sql.append(" (t.status = @status1 or t.status = @status2) ");
		sql.append(" and t.isStoped = false ");
		/**
		 * 搜索
		 */
		if (null != key.getMainKey().getSearch()) {
			String searchText = key.getMainKey().getSearch();
			// List<String> typeList = OrderUtil.getBillsType(searchText);
			sql.append(" and (t.billsNo like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.partnerName like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.shortName like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.creator like '%");
			sql.append(searchText);
			sql.append("%' ");

			// for(String type:typeList)
			// {
			// sql.append(" or t.type='");
			// sql.append(type);
			// sql.append("' ");
			// }
			sql.append(") ");
		}
		// 排序
		sql.append(" order by ");
		sql.append(key.getMainKey().getSortField());
		sql.append("  ");
		sql.append(key.getMainKey().getSortType());
		return sql.toString();
	}

}
