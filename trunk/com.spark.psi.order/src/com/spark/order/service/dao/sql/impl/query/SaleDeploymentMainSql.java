package com.spark.order.service.dao.sql.impl.query;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.key.SaleDeploymentMainKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

/**
 * <p>
 * 出库分配，查询销售订单
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author 莫迪
 * @version 2011-11-20
 */
public class SaleDeploymentMainSql extends QuerySql {

	public SaleDeploymentMainSql(Context context) {
		super(context);
	}

	/**
	 * 获得查询结果集
	 * 
	 * @return List<SaleOrdInfo>
	 */
	public List<SaleOrderInfo> getList(RecordSet rs) {
		List<SaleOrderInfo> list = new ArrayList<SaleOrderInfo>();
		while (rs.next()) {
			SaleOrderInfo info = new SaleOrderInfo();
			int index = 0;
			info.setRECID(rs.getFields().get(index++).getGUID());
			info.setBillsNo(rs.getFields().get(index++).getString());
			info.setPartnerId(rs.getFields().get(index++).getGUID());
			info.setPartnerShortName(rs.getFields().get(index++).getString());
			info.setPartnerName(rs.getFields().get(index++).getString());
			info.setPartnerNamePY(rs.getFields().get(index++).getString());
			info.setFax(rs.getFields().get(index++).getString());
			info.setConsignee(rs.getFields().get(index++).getString());
			info.setLinkman(rs.getFields().get(index++).getString());
			info.setAddress(rs.getFields().get(index++).getString());
			info.setDeliveryDate(rs.getFields().get(index++).getDate());
			info.setRejectReason(rs.getFields().get(index++).getString());
			info.setStopReason(rs.getFields().get(index++).getString());
			info.setRemark(rs.getFields().get(index++).getString());
			info.setDisAmount(rs.getFields().get(index++).getDouble());
			info.setTotalAmount(rs.getFields().get(index++).getDouble());
			info.setBillType(rs.getFields().get(index++).getString());
			info.setCreatorId(rs.getFields().get(index++).getGUID());
			info.setCreator(rs.getFields().get(index++).getString());
			info.setCreateDate(rs.getFields().get(index++).getDate());
			info.setFinishedDate(rs.getFields().get(index++).getDate());
			info.setStatus(rs.getFields().get(index++).getString());
			info.setStoped(rs.getFields().get(index++).getBoolean());
			info.setDeptId(rs.getFields().get(index++).getGUID());
			info.setApproveStr(rs.getFields().get(index++).getString());
			info.setStoreId(rs.getFields().get(index++).getGUID());
			info.setStoreName(rs.getFields().get(index++).getString());
			list.add(info);
		}
		return list;
	}

	public String setParameter() {
		return "@status string, @isAllot boolean";
	}

	public String setSql(SelectKey key) {
		SaleDeploymentMainKey k = (SaleDeploymentMainKey) key;
		StringBuilder sql = new StringBuilder();
		sql.append(" select \n");
		sql.append(" t.RECID             as RECID         , \n");
		sql.append(" t.billsNo           as billsNo       , \n");
		sql.append(" t.partnerId         as partnerId     , \n");
		sql.append(" t.shortName         as shortName     , \n");
		sql.append(" t.partnerName       as partnerName   , \n");
		sql.append(" t.partnerNamePY     as partnerNamePY , \n");
		sql.append(" t.fax               as fax           , \n");
		sql.append(" t.consignee         as consignee     , \n");
		sql.append(" t.linkman           as linkman       , \n");
		sql.append(" t.address           as address       , \n");
		sql.append(" t.deliveryDate      as deliveryDate  , \n");
		sql.append(" t.rejectReason      as rejectReason  , \n");
		sql.append(" t.stopReason        as stopReason    , \n");
		sql.append(" t.remark            as remark        , \n");
		sql.append(" t.disAmount         as disAmount     , \n");
		sql.append(" t.totalAmount       as totalAmount   , \n");
		sql.append(" t.billType          as billType      , \n");
		sql.append(" t.creatorId         as creatorId     , \n");
		sql.append(" t.creator           as creator       , \n");
		sql.append(" t.createDate        as createDate    , \n");
		sql.append(" t.finishedDate      as finishedDate  , \n");
		sql.append(" t.status            as status        , \n");
		sql.append(" t.isStoped          as isStoped      , \n");
		sql.append(" t.deptId            as deptId        , \n");
		sql.append(" t.approveStr        as approveStr    , \n");
		sql.append(" t.storeId           as storeId       , \n");
		sql.append(" t.storeName         as storeName      \n");
		sql.append(" from ");
		sql.append(BillsEnum.SALE.getDb_table());
		sql.append(" as t ");
		// 条件
		sql.append(" where ");
		sql.append(" t.status = @status ");
		sql.append(" and t.isAllot = @isAllot ");
		sql.append(" and t.isStoped = false ");
		// "t.billsNo","collate_gbk(t.cuspName)","collate_gbk(t.address)"
		if (null != k.getSearch()) {
			sql.append(" and ( ");

			sql.append(" t.billsNo like '%");
			sql.append(k.getSearch());
			sql.append("%' ");

			sql.append(" or t.PartnerName like '%");
			sql.append(k.getSearch());
			sql.append("%' ");

			sql.append(" or t.shortName like '%");
			sql.append(k.getSearch());
			sql.append("%' ");

			sql.append(" or t.address like '%");
			sql.append(k.getSearch());
			sql.append("%' "); 

			sql.append(" ) ");
		}
		sql.append(" order by ");
		sql.append(k.getSortColumnName());
		sql.append("  ");
		sql.append(k.getSortType());
		return sql.toString();
	}

}
