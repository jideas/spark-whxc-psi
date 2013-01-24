package com.spark.b2c.base.supplier.joint.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.b2c.publish.JointVenture.entity.JointVentureLogItem;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordItem;
import com.spark.b2c.publish.JointVenture.key.GetJointVentureLogListKey;
import com.spark.b2c.publish.JointVenture.key.GetJointVentureRecordListKey;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.base.publicimpl.JointVentureLogItemImpl;
import com.spark.psi.base.publicimpl.JointVentureRecordItemImpl;

public final class JointSeviceUtil {

	final static String jointVentureLogTable = ERPTableNames.Joint.Materials_Joint_Log.getTableName();
	public static List<JointVentureLogItem> getJointVentureLogItems(Context context, GetJointVentureLogListKey key) {
		List<JointVentureLogItem> list = new ArrayList<JointVentureLogItem>();
		StringBuffer sql = new StringBuffer();
		sql.append("define query getOrderItemList(@supplierId guid)\n");
		sql.append("begin\n");
		sql.append("select \n");
		sql.append(getColumns());
		sql.append(" from \n");
		sql.append(jointVentureLogTable);
		sql.append(" as t\n");
		sql.append(" where t.supplierId=@supplierId \n");
//		sql.append(getStatusSql(key));
//		sql.append(getSearchSql(key));
//		sql.append(getOrderSql(key));
		sql.append("end");
		
		DBCommand db = context.prepareStatement(sql);
		db.setArgumentValues(key.getSupplierId());
		RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
		while(rs.next())
		{
			list.add(fillJointVentureLog(context,rs));
		}
		return list;
	}

	private static JointVentureLogItem fillJointVentureLog(Context context, RecordSet rs) {
		JointVentureLogItemImpl impl = new JointVentureLogItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());
		impl.setSupplierId(rs.getFields().get(index++).getGUID());
		impl.setMaterialId(rs.getFields().get(index++).getGUID());
		impl.setMaterialName(rs.getFields().get(index++).getString());
		impl.setMaterialCode(rs.getFields().get(index++).getString());
		impl.setMaterialNo(rs.getFields().get(index++).getString());
		impl.setMaterialUnit(rs.getFields().get(index++).getString());
		impl.setBeginDate(rs.getFields().get(index++).getDate());
		impl.setEndDate(rs.getFields().get(index++).getDate());
		impl.setPercentage(rs.getFields().get(index++).getDouble());
		return impl;
	}

	private static Object getColumns() {
		StringBuffer sql = new StringBuffer();
		
		sql.append("t.RECID as RECID,");
		sql.append("t.supplierId as supplierId,");
		sql.append("t.materialId as materialId,");
		sql.append("t.materialName as materialName,");
		sql.append("t.materialCode as materialCode,");
		sql.append("t.materialNo as materialNo,");
		sql.append("t.materialUnit as materialUnit,");
		sql.append("t.beginDate as beginDate,");
		sql.append("t.endDate as endDate,");
		sql.append("t.percentage as percentage");

		return sql;
	}

	public static List<JointVentureRecordItem> getJointVentureRecordItems(Context context,
			GetJointVentureRecordListKey key) {
		List<JointVentureRecordItem> list = new ArrayList<JointVentureRecordItem>();
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable(ERPTableNames.Joint.Joint_Record.getTableName(), "t");
		qb.addColumn("t.RECID", "RECID");
		qb.addColumn("t.goodsId", "goodsId");
		qb.addColumn("t.sheetId", "sheetId");
		qb.addColumn("t.supplierId", "supplierId");
		qb.addColumn("t.supplierName", "supplierName");
		qb.addColumn("t.shortName", "shortName");
		qb.addColumn("t.supplierNamePY", "supplierNamePY");
		qb.addColumn("t.sheetNo", "sheetNo");
		qb.addColumn("t.goodsCode", "goodsCode");
		qb.addColumn("t.goodsNo", "goodsNo");
		qb.addColumn("t.goodsSpec", "goodsSpec");
		qb.addColumn("t.goodsUnit", "goodsUnit");
		qb.addColumn("t.goodsName", "goodsName");
		qb.addColumn("t.goodsPrice", "goodsPrice");
		qb.addColumn("t.\"salesCount\"", "\"salesCount\"");
		qb.addColumn("t.amount", "amount");
		qb.addColumn("t.percentage", "percentage");
		qb.addColumn("t.createDate", "createDate");
		qb.addColumn("t.alreadySettlement", "alreadySettlement");

		qb.addArgs("pid", qb.guid, key.getPartnerId());
		qb.addEquals("t.supplierId", "@pid");

		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			qb.addArgs("text", qb.STRING, key.getSearchText());
			StringBuilder ss = new StringBuilder("( ");
			ss.append(" t.supplierName like '%'+@text+'%' ");
			ss.append(" or t.sheetNo like '%'+@text+'%' ");
			ss.append(" or t.goodsName like '%'+@text+'%' ");
			ss.append(" or t.shortName like '%'+@text+'%' ");
			ss.append(" or t.goodsCode like '%'+@text+'%' ");
			ss.append(" or t.goodsSpec like '%'+@text+'%' ");
			ss.append(" or t.goodsUnit like '%'+@text+'%' ");
			ss.append(" or t.goodsNo like '%'+@text+'%' ");
			ss.append(")");
			qb.addCondition(ss.toString());
		}
		
		if (key.isNeverSettlement()) {
			qb.addArgs("already", qb.BOOLEAN, false);
			qb.addEquals("t.alreadySettlement", "@already");
		}

		qb.addOrderBy(" t.createDate desc");
		RecordSet rs = null;
		if (key.getCount() > 0) {
			rs = qb.getRecordLimit(key.getOffset(), key.getCount());
		} else {
			rs = qb.getRecord();
		}
		while (rs.next()) {
			int index = 0;
			JointVentureRecordItemImpl item = new JointVentureRecordItemImpl();
			item.setRECID(rs.getFields().get(index++).getGUID());
			item.setGoodsId(rs.getFields().get(index++).getGUID());
			item.setSheetId(rs.getFields().get(index++).getGUID());
			item.setSupplierId(rs.getFields().get(index++).getGUID());
			item.setSupplierName(rs.getFields().get(index++).getString());
			item.setShortName(rs.getFields().get(index++).getString());
			item.setSupplierNamePY(rs.getFields().get(index++).getString());
			item.setSheetNo(rs.getFields().get(index++).getString());
			item.setGoodsCode(rs.getFields().get(index++).getString());
			item.setGoodsNo(rs.getFields().get(index++).getString());
			item.setGoodsSpec(rs.getFields().get(index++).getString());
			item.setGoodsUnit(rs.getFields().get(index++).getString());
			item.setGoodsName(rs.getFields().get(index++).getString());
			item.setGoodsPrice(rs.getFields().get(index++).getDouble());
			item.setCount(rs.getFields().get(index++).getDouble());
			item.setAmount(rs.getFields().get(index++).getDouble());
			item.setPercentage(rs.getFields().get(index++).getDouble());
			item.setCreateDate(rs.getFields().get(index++).getDate());
			item.setAlreadySettlement(rs.getFields().get(index++).getBoolean());
			list.add(item);
		}
		return list;
	}
}
