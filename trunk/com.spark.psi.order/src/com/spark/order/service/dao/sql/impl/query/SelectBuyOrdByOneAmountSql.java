package com.spark.order.service.dao.sql.impl.query;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.purchase.intf.key.SelectPurchaseOrdByOneKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

/**
 * <p>线上订单总金额</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-12
 */
public class SelectBuyOrdByOneAmountSql extends QuerySql{

	public SelectBuyOrdByOneAmountSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenantsGuid guid, @startDate date, @endDate date";
	}

	public String setSql(SelectKey key) {
		return setSql((SelectPurchaseOrdByOneKey)key);
	}
	
	public double getTotalAmount(RecordSet rs){
		if(rs.next()){
			return rs.getFields().get(0).getDouble();
		}
		return 0d;
	}
	
	public String setSql(SelectPurchaseOrdByOneKey key){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" sum(t.totalAmount) as totalAmount ");
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
		sql.append(" group by t.tenantsGuid ");
		sql.append(" order by t.createDate desc ");
		return sql.toString();
	}

}
