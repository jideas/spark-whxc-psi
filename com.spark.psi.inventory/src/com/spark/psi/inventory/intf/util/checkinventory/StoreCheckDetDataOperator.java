package com.spark.psi.inventory.intf.util.checkinventory;

import com.jiuqi.dna.core.da.RecordSet;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.intf.key.checkinventory.CheckInventoryItemKey;

public class StoreCheckDetDataOperator {
	
	public static String getSql(CheckInventoryItemKey checkDetKey) {
		StringBuffer buffer = new StringBuffer("define query querycheckdet(");
		buffer.append("@checkOrdGuid guid,@goodsTypeGuid guid,@yzFlag boolean,@tenantsGuid guid");
		buffer.append(") ");
		defaultSql(buffer);
		buffer.append(" join (");
		//TODO 
//		buffer.append(GoodsConstant.QUERY_TONGYONG);
//		buffer.append(GoodsConstant.QUERY_CHILIDREN_AND_SELF);
		buffer.append(") as type on t.goodsTypeGuid=type.recid");
		buffer.append(" and type.yzFlag=@yzFlag");
		buffer.append(" where 1=1  ");
		buffer.append("and ");
		buffer.append("t.checkOrdGuid = @checkOrdGuid ");
		if(checkDetKey.isHistory()) {
			//是不是历史订单，如果是，那么，只查询盘盈盘亏物品
			buffer.append(" and t.realCount = t.carryCount ");
		}
		//buffer.append(" order by " + checkDetKey.getOrderName() + " " + checkDetKey.getOrder() + " ");
		buffer.append(" end");
		return buffer.toString();
	}
	
	public static String getbossSql(CheckInventoryItemKey checkDetKey) {
		StringBuffer buffer = new StringBuffer("define query querycheckdet(");
		buffer.append("@checkOrdGuid guid ");
		buffer.append(") ");
		defaultSql(buffer);
		buffer.append(" where 1=1  ");
		buffer.append("and ");
		buffer.append("t.checkOrdGuid = @checkOrdGuid ");
		if(checkDetKey.isHistory()) {
			//是不是历史订单，如果是，那么，只查询盘盈盘亏物品
			buffer.append(" and t.realCount <> t.carryCount ");
		}
		//buffer.append(" order by " + checkDetKey.getOrderName() + " " + checkDetKey.getOrder() + " ");
		buffer.append(" end");
		return buffer.toString();
	}
	
	/**
	 * 盘点单的默认的字段
	 * @param buffer
	 */
	public static void defaultSql(StringBuffer buffer) {
		buffer.append("begin ");
		buffer.append("select ");
		buffer.append("t.carryCount as carryCount," +
				"t.checkOrdGuid as checkOrdGuid," +
				"t.goodsTypeGuid as goodsTypeGuid," +
				"t.createDate as createDate," +
				"t.createPerson as createPerson," +
				"t.goodsAttr as goodsAttr," +
				"t.goodsGuid as goodsGuid," +
				"t.goodsName as goodsName," +
				"t.goodsItemCode as goodsItemCode," +
				"t.realCount as realCount," +
				"t.RECID as recid," +
				"t.remark as remark," +
				"t.tenantsGuid as tenantsGuid," +
				"t.unit as unit," +
				"t.newGoods as newGoods," +
				"t.goodsScale as goodsScale ,t.goodsItemNo as goodsNo ");
		buffer.append("from SA_STORE_CHECK_DET as t ");
		
	}
	
	public static CheckInventoryItem getStoreCheckDet(RecordSet rs) {
		CheckInventoryItem checkDet = new CheckInventoryItem();
		checkDet.setCarryCount(rs.getFields().get(0).getDouble());
		checkDet.setCheckOrdGuid(rs.getFields().get(1).getGUID());
		checkDet.setGoodsTypeGuid(rs.getFields().get(2).getGUID());
		checkDet.setCreateDate(rs.getFields().get(3).getDate());
		checkDet.setCreatePerson(rs.getFields().get(4).getString());
		checkDet.setGoodsAttr(rs.getFields().get(5).getString());
		checkDet.setGoodsGuid(rs.getFields().get(6).getGUID());
		checkDet.setGoodsName(rs.getFields().get(7).getString());
		checkDet.setGoodsItemCode(rs.getFields().get(8).getString());
		checkDet.setRealCount(rs.getFields().get(9).getDouble());
		checkDet.setRecid(rs.getFields().get(10).getGUID());
		checkDet.setRemark(rs.getFields().get(11).getString());
		checkDet.setTenantsGuid(rs.getFields().get(12).getGUID());
		checkDet.setUnit(rs.getFields().get(13).getString());
		checkDet.setNewGoods(rs.getFields().get(14).getString());
		checkDet.setGoodsScale(rs.getFields().get(15).getInt());
		checkDet.setGoodsItemNo(rs.getFields().get(16).getString());
		return checkDet;
	}
}
