package com.spark.order.promotion.service;

import com.jiuqi.dna.core.Context;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.util.dnaSql.DnaSql_update;

/**
 * <p>促销单状态变化定时器</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
class PromotionStatus2Timer {
	private final Context context;

	/**
	 * @param context
	 */
	public PromotionStatus2Timer(Context context) {
		super();
		this.context = context;
	}
	public void action(){
		//将到期待发布单改成促销中
		Promotioning p = new Promotioning(context);
		p.executeUpdate();
		//将到期促销单改成过期状态
		Out_of_date sql = new Out_of_date(context);
		sql.executeUpdate();
	}
	
	/**
	 * <p>已过期</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	class Out_of_date extends DnaSql_update{

		public Out_of_date(Context context) {
			super(context);
		}

		@Override
		protected String getSql() {
			StringBuilder sql = new StringBuilder();
			sql.append(" update ");
			sql.append(SelectPromotionSql.promotion_Table);
			sql.append(" as t ");
			sql.append(" set ");
			sql.append(" state = @newstatus ");
			this.addParam("@newstatus string", PromotionStatus2.Out_of_date.getCode());
			sql.append(" where ");
			sql.append(" t.state = @promotioning ");
			this.addParam("@promotioning string", PromotionStatus2.Promotioning.getCode());
			sql.append(" and ");
			sql.append(" t.endDate < @thisDate ");
			this.addParam("@thisDate date", System.currentTimeMillis());
			return sql.toString();
		}
	}
	
	/**
	 * <p>促销中</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	class Promotioning extends DnaSql_update{

		public Promotioning(Context context) {
			super(context);
		}

		@Override
		protected String getSql() {
			StringBuilder sql = new StringBuilder();
			sql.append(" update ");
			sql.append(SelectPromotionSql.promotion_Table);
			sql.append(" as t ");
			sql.append(" set ");
			sql.append(" state = @newstatus ");
			this.addParam("@newstatus string", PromotionStatus2.Promotioning.getCode());
			sql.append(" where ");
			sql.append(" t.state = @issuestatus ");
			this.addParam("@issuestatus string", PromotionStatus2.Issue.getCode());
			sql.append(" and ");
			sql.append(" t.beginDate <= @thisDate ");
			this.addParam("@thisDate date", System.currentTimeMillis());
			return sql.toString();
		}
	}
}
