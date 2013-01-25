package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.purchase.intf.task.PurchaseGoodsUpdateTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>���²ɹ���Ʒ�嵥�۸�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-18
 */
public class ModifyBuyGoodsPrice extends ModifySql{

	public ModifyBuyGoodsPrice(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@recid guid, @price double, @cause string";
	}

	public String setSql(SimpleTask task) {
		return setSql((PurchaseGoodsUpdateTask)task);
	}
	
	public String setSql(PurchaseGoodsUpdateTask task){
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		if(task.isDirect()){
			sql.append(" SA_STORE_DIRECT as t ");
		}
		else{
			sql.append(" PSI_Purchase_Order_CREATE as t ");
		}
		sql.append(" set ");
		if(!task.isDirect()){
			sql.append(" buyCause = @cause, ");
		}
		sql.append(" price = @price ");
		sql.append(" where ");
		sql.append(" t.RECID = @recid ");
		return sql.toString();
	}
}
