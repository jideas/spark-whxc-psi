package com.spark.psi.query.intenal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.query.intf.intenal.entity.ProduceItemImpl;

public final class QueryServiceUtil {

	public static List<ProduceItemImpl.LogImpl> getProduceLogs(Context context, ProduceItemImpl impl) {
		List<ProduceItemImpl.LogImpl> list = new ArrayList<ProduceItemImpl.LogImpl>();
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable(ERPTableNames.Sales.Produce_FinishedLog.getTableName(), "t");
		qb.addColumn("t.billsId", "billsId");//	订单GUID
		qb.addColumn("t.goodsId","goodsId");//	商品Guid
		qb.addColumn("t.goodsCode","goodsCode");//	商品编号
		qb.addColumn("t.goodsName","goodsName");//	商品名称
		qb.addColumn("t.unit","unit");//	单位
		qb.addColumn("t.\"thisTimeCount\"","\"thisTimeCount\"");//	数量
		qb.addColumn("t.creatorId","creatorId");//	经手人
		qb.addColumn("t.creator","creator");//	经手人姓名
		qb.addColumn("t.createDate","createDate");//	日期
		qb.addArgs("billsId", qb.guid, impl.getBillsId());
		qb.addCondition("t.billsId=@billsId");
		qb.addArgs("goodsId", qb.guid, impl.getGoodsId());
		qb.addCondition("t.goodsId=@goodsId");
		qb.addOrderBy("t.createDate");
		RecordSet rs = qb.getRecord();
		while(rs.next())
		{
			ProduceItemImpl.LogImpl log = impl.new LogImpl();
			int index = 0;
			log.setBillsId(rs.getFields().get(index++).getGUID());//	订单GUID
			log.setGoodsId(rs.getFields().get(index++).getGUID());//	商品Guid
			log.setGoodsCode(rs.getFields().get(index++).getString());//	商品编号
			log.setGoodsName(rs.getFields().get(index++).getString());//	商品名称
			log.setUnit(rs.getFields().get(index++).getString());//	单位
			log.setCount(rs.getFields().get(index++).getDouble());//	数量
			log.setCreatorId(rs.getFields().get(index++).getGUID());//	经手人
			log.setCreator(rs.getFields().get(index++).getString());//	经手人姓名
			log.setCreateDate(rs.getFields().get(index++).getDate());//
			list.add(log);
		}
		return list;
	}

	public static List<ProduceItemImpl.ItemImpl> getProduceItems(Context context, ProduceItemImpl impl, GUID bomId) {
		List<ProduceItemImpl.ItemImpl> list = new ArrayList<ProduceItemImpl.ItemImpl>();
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable(ERPTableNames.Base.BomDetail.getTableName(), "t");
		
//		qb.addColumn("t.billsId","billsId");//	订单GUID
		qb.addColumn("t.materialId","materialId");//	商品Guid
		qb.addColumn("t.\"realCount\"","\"realCount\"");//	数量
		qb.addArgs("bomId", qb.guid, bomId);
		qb.addCondition("t.bomId=@bomId");
		RecordSet rs = qb.getRecord();
		while(rs.next())
		{
			ProduceItemImpl.ItemImpl item = impl.new ItemImpl();
			int index = 0;
			
			item.setBillsId(impl.getBillsId());
			item.setMaterialId(rs.getFields().get(index++).getGUID());
			item.setCount(DoubleUtil.mul(impl.getCount(),rs.getFields().get(index++).getDouble()));
			MaterialsItem mi = context.find(MaterialsItem.class, item.getMaterialId());
			item.setMaterialNo(mi.getMaterialNo());
			item.setSpec(mi.getSpec());
			item.setCost(mi.getStandardPrice());
			item.setTotalCost(DoubleUtil.sum(item.getCost(), item.getCount()));
			item.setMaterialCode(mi.getMaterialCode());
			item.setMaterialName(mi.getMaterialName());
			item.setUnit(mi.getMaterialUnit());
			list.add(item);
		}
		return list;
	}

}
