package com.spark.order.promotion.service;

import com.jiuqi.dna.core.Context;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.util.dnaSql.DnaSql_update;

/**
 * <p>������״̬�仯��ʱ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
		//�����ڴ��������ĳɴ�����
		Promotioning p = new Promotioning(context);
		p.executeUpdate();
		//�����ڴ������ĳɹ���״̬
		Out_of_date sql = new Out_of_date(context);
		sql.executeUpdate();
	}
	
	/**
	 * <p>�ѹ���</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
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
	 * <p>������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
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
