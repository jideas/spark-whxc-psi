package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.type.GUID;

public final class SqlUtil{
	
	public static int DeleteById(Context context,String tab,GUID id){
		StringBuffer sql =
		        new StringBuffer(
		                "define delete deleteById(@id guid)");
		sql.append(" begin");
		sql.append(" delete from ");
		sql.append(tab);
		sql.append(" as a ");
		sql.append(" where a.RECID=@id ");
		sql.append(" end");
		DBCommand dbc = context.prepareStatement(sql);
		dbc.setArgumentValue(0, id);
		return dbc.executeUpdate(); // 删除商品条目
	}
	
}

