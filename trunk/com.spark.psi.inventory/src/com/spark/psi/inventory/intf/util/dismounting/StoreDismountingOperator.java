package com.spark.psi.inventory.intf.util.dismounting;

import com.jiuqi.dna.core.da.RecordSet;
import com.spark.psi.inventory.internal.entity.Dismounting;

public class StoreDismountingOperator {
	
	public static void defaultSql(StringBuilder stringBuffer) {
		stringBuffer.append("select  ");
		stringBuffer.append("t.RECID as RECID,"+
		"t.createPerson as createPerson,"+
		"t.deptGuid as deptGuid,"+
		"t.dismOrdNo as dismOrdNo,"+
		"t.markName as markName,"+
		"t.markPerson as markPerson,"+
		"t.storeGuid as storeGuid,"+
		"t.storeName as storeName,"+
		"t.storePY as storePY," +
		"t.dismDate as dismDate," +
		"t.createDate as createDate,"+
		"t.tenantsGuid as tenantsGuid ");
		stringBuffer.append(" from SA_STORE_DISMOUNT as t ");
	}
	
	public static Dismounting setStoreDismounting(RecordSet rs) {
		Dismounting storeDismounting = new Dismounting();
		storeDismounting.setRECID(rs.getFields().get(0).getGUID());
		storeDismounting.setCreatePerson(rs.getFields().get(1).getString());
		storeDismounting.setDeptGuid(rs.getFields().get(2).getGUID());
		storeDismounting.setDismOrdNo(rs.getFields().get(3).getString());
		storeDismounting.setMarkName(rs.getFields().get(4).getString());
		storeDismounting.setMarkPerson(rs.getFields().get(5).getGUID());
		storeDismounting.setStoreGuid(rs.getFields().get(6).getGUID());
		storeDismounting.setStoreName(rs.getFields().get(7).getString());
		storeDismounting.setStorePY(rs.getFields().get(8).getString());
		storeDismounting.setDismDate(rs.getFields().get(9).getDate());
		storeDismounting.setCreateDate(rs.getFields().get(10).getDate());
		storeDismounting.setTenantsGuid(rs.getFields().get(11).getGUID());
		return storeDismounting;
	}
}
