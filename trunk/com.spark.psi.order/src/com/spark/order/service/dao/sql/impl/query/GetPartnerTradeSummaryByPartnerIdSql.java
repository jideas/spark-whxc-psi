package com.spark.order.service.dao.sql.impl.query;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.service.dao.sql.impl.QuerySql;
import com.spark.psi.base.key.GetPartnerTradeSummaryByPartnerIdKey;

/**
 * <p>订单对客户供应商提供查询数据</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public final class GetPartnerTradeSummaryByPartnerIdSql extends QuerySql{
	private GetPartnerTradeSummaryByPartnerIdKey key;
	
	public GetPartnerTradeSummaryByPartnerIdSql(Context context,GetPartnerTradeSummaryByPartnerIdKey key) {
		super(context);
		this.key = key;
	}
	
//	public PartnerTradeSummary getPartnerTradeSummary(RecordSet rs){
//		PartnerTradeSummary info = null;
//		if(rs.next()){
//			info = new PartnerTradeSummary();
//			int index = 0;
//			info.setAmount(rs.getFields().get(index++).getDouble());
//			info.setCount(rs.getFields().get(index++).getInt());
//			info.setRecentTradeDate(rs.getFields().get(index++).getDate());
//		}
//		return info;
//	}

	public String setParameter() {
		//待提交、待审核、已退回状态
		return "@tenants guid, @status1 string, @status2 string, @status3 string, @cuspGuid guid";
	}

//	交易总额，交易总次数<Br>
//	 *  已审批、已中止、已完成的
	public String setSql(SelectKey selectKey) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" sum(t.totalAmount) as totalAmount, ");
		sql.append(" count(*) as totalCount, ");
		sql.append(" max(t.createDate) as recentTradeDate ");
		sql.append(" from ");
		switch (key.getType()) {
		case Customer:
			sql.append(BillsEnum.SALE.getDb_table());
			break;
		case Supplier:
			sql.append(BillsEnum.PURCHASE.getDb_table());
			break;
		default:
			break;
		}
		sql.append(" as t ");
		sql.append(" where ");
		//不是待提交、待审核、已退回状态
		sql.append(" t.tenantsGuid = @tenants ");
		sql.append(" and ");
		sql.append(" t.status <> @status1 ");
		sql.append(" and ");
		sql.append(" t.status <> @status2 ");
		sql.append(" and ");
		sql.append(" t.status <> @status3 ");
		sql.append(" and ");
		sql.append(" t.cuspGuid = @cuspGuid ");
		return sql.toString();
	}

}
