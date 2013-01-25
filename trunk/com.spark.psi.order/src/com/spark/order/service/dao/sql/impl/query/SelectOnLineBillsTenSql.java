package com.spark.order.service.dao.sql.impl.query;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.key.SelectTenAfterOneBillsKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

public class SelectOnLineBillsTenSql extends QuerySql{

	public SelectOnLineBillsTenSql(Context context) {
		super(context);
	}
	
	public String setParameter() {
		return "@tenantsGuid guid";
	}
	
	public String setSql(SelectKey key) {
		return setSql((SelectTenAfterOneBillsKey)key);
	}

	public List<PurchaseOrderInfo> getList(RecordSet rs){
		List<PurchaseOrderInfo> list = new ArrayList<PurchaseOrderInfo>();
		PurchaseOrderInfo info;
		while(rs.next()){
			info = new PurchaseOrderInfo();
			int i = 0;
			info.setConsignee(rs.getFields().get(i++).getString());
//			info.setConsigneeTel(rs.getFields().get(i++).getString());
			info.setAddress(rs.getFields().get(i++).getString());
			list.add(info);
		}
		return list;
	}
	
	public String setSql(SelectTenAfterOneBillsKey key){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
//		private String consignee;//	收货人	V(10)
//		private String consigneeTel;//	收货人电话	V(20)
//		private String address;//	收货地址	V(200)
		sql.append(" t.consignee as consignee, ");
		sql.append(" t.consigneeTel as consigneeTel, ");
		sql.append(" t.address as address ");
				sql.append(" from PSI_Purchase_Order as t ");
		sql.append(" where t.tenantsGuid = @tenantsGuid ");
		sql.append(" order by t.createDate desc ");
		return sql.toString();
	}
}
