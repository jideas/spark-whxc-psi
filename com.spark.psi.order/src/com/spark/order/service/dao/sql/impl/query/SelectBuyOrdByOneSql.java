package com.spark.order.service.dao.sql.impl.query;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.key.SelectPurchaseOrdByOneKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

public class SelectBuyOrdByOneSql extends QuerySql{

	public SelectBuyOrdByOneSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenantsGuid guid, @startDate date, @endDate date";
	}

	public String setSql(SelectKey key) {
		return setSql((SelectPurchaseOrdByOneKey)key);
	}
	
	public List<PurchaseOrderInfo> getList(RecordSet rs){
		List<PurchaseOrderInfo> list = new ArrayList<PurchaseOrderInfo>();
		PurchaseOrderInfo info;
		while(rs.next()){
			info = new PurchaseOrderInfo();
			int i = 0;
			info.setStatus(rs.getFields().get(i++).getString());
//			info.setType(rs.getFields().get(i++).getString());
//			info.setCreateDate(rs.getFields().get(i++).getDate());
//			info.setCreateGuid(rs.getFields().get(i++).getGUID());
//			info.setCreatePerson(rs.getFields().get(i++).getString());
//			info.setCuspFax(rs.getFields().get(i++).getString());
//			info.setCuspFullName(rs.getFields().get(i++).getString());
//			info.setCuspFullNamePY(rs.getFields().get(i++).getString());
//			info.setCuspGuid(rs.getFields().get(i++).getGUID());
//			info.setCuspName(rs.getFields().get(i++).getString());
//			info.setDeptGuid(rs.getFields().get(i++).getGUID());
//			info.setRecid(rs.getFields().get(i++).getGUID());
//			info.setTenantsGuid(rs.getFields().get(i++).getGUID());
//			info.setSaleGuidB(rs.getFields().get(i++).getGUID());
//			info.setTenantsGuidB(rs.getFields().get(i++).getGUID());
			info.setTotalAmount(rs.getFields().get(i++).getDouble());
			list.add(info);
		}
		return list;
	}
	
	public String setSql(SelectPurchaseOrdByOneKey key){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" t.status as status, ");
		sql.append(" t.type as Type, ");
		sql.append(" t.createDate as createDate, ");
		sql.append(" t.createGuid as createGuid, ");
		sql.append(" t.createPerson as createPerson, ");
		sql.append(" t.cuspFax as cuspFax, ");
		sql.append(" t.cuspFullName as cuspFullName, ");
		sql.append(" t.cuspFullNamePY as cuspFullNamePY, ");
		sql.append(" t.cuspGuid as cuspGuid, ");
		sql.append(" t.cuspName as cuspName, ");
		sql.append(" t.deptGuid as deptGuid, ");
		sql.append(" t.RECID as recid, ");
		sql.append(" t.tenantsGuid as tenantsGuid, ");
		sql.append(" t.saleGuidB as saleGuidB, ");
		sql.append(" t.tenantsGuidB as tenantsGuidB, ");
		sql.append(" t.totalAmount as totalAmount ");
		sql.append(" from PSI_Purchase_Order as t ");
		sql.append(" where t.tenantsGuid = @tenantsGuid ");
		/**
		 * 日期区间
		 */
		if(key.getStartDate()!=0)
		{
			sql.append(" and t.createDate>= @startDate");
			sql.append(" \n");
		}
		if(null != key.getEndDate())
		{
			sql.append(" and t.createDate<= @endDate");
			sql.append(" \n");
		}
		sql.append("  ");
		sql.append(" order by t.createDate desc ");
		return sql.toString();
	}

}
