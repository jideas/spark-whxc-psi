package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.task.ModifyGoodsOkCountTask;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>更新确认出入库数量</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-17
 */
public class ModifyGoodsOkCountSql extends ModifySql{

	public ModifyGoodsOkCountSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@billsGuid guid, @status guid";
	}

	public String setSql(SimpleTask task) {
		return setSql((ModifyGoodsOkCountTask)task);
	}
	
	public String setSql(ModifyGoodsOkCountTask task){
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(task.getBillsEnum().getDb_table());
		sql.append(" as t ");
		sql.append(" set ");
		if(null != task.getBillsstatus()){
			sql.append(" status = @status, ");
		}
		if(null != task.getChangeAmount()){
			sql.append(" okAmount = ");
			sql.append(" t.okAmount+ ");
			sql.append(task.getChangeAmount());
			sql.append(" , ");
		}
		sql.append(" goodsOkCount = ");
		sql.append(" t.goodsOkCount+");
		sql.append(task.getChangeCount());
		sql.append(" where ");
		sql.append(" t.RECID= ");
		sql.append(" @billsGuid ");
		if(0 != task.getChangeCount()){
			sql.append(" and ");
			sql.append(" t.goodsOkCount=");
			sql.append(task.getOldCount());
		}else if(StatusEnum.Finished == task.getBillsstatus()){
			sql.append(" and ");
			sql.append(" t.goodsOkCount= t.goodsTotalCount");
		}
		return sql.toString();
	}
	
}
