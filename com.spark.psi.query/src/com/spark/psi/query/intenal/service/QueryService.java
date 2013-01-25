package com.spark.psi.query.intenal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.query.intf.intenal.entity.GoodsCheckInItemImpl;
import com.spark.psi.query.intf.intenal.entity.MaterialsCheckInItemImpl;
import com.spark.psi.query.intf.intenal.entity.MaterialsCheckOutItemImpl;
import com.spark.psi.query.intf.intenal.entity.OnlineSalesItemImpl;
import com.spark.psi.query.intf.intenal.entity.ProduceItemImpl;
import com.spark.psi.query.intf.intenal.entity.PurchaseItemImpl;
import com.spark.psi.query.intf.intenal.entity.SalesItemImpl;
import com.spark.psi.query.intf.intenal.entity.TicketItemImpl;
import com.spark.psi.query.intf.publish.entity.GoodsCheckInItem;
import com.spark.psi.query.intf.publish.entity.GoodsCheckInListEntity;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInItem;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInListEntity;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckOutItem;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckOutListEntity;
import com.spark.psi.query.intf.publish.entity.OnlineSalesItem;
import com.spark.psi.query.intf.publish.entity.OnlineSalesListEntity;
import com.spark.psi.query.intf.publish.entity.ProduceItem;
import com.spark.psi.query.intf.publish.entity.ProduceListEntity;
import com.spark.psi.query.intf.publish.entity.PurchaseItem;
import com.spark.psi.query.intf.publish.entity.PurchaseListEntity;
import com.spark.psi.query.intf.publish.entity.SalesItem;
import com.spark.psi.query.intf.publish.entity.SalesListEntity;
import com.spark.psi.query.intf.publish.entity.TicketItem;
import com.spark.psi.query.intf.publish.entity.TicketListEntity;
import com.spark.psi.query.intf.publish.key.GetGoodsCheckInListKey;
import com.spark.psi.query.intf.publish.key.GetMaterialsCheckInListKey;
import com.spark.psi.query.intf.publish.key.GetMaterialsCheckOutListKey;
import com.spark.psi.query.intf.publish.key.GetOnlineSalesListKey;
import com.spark.psi.query.intf.publish.key.GetProduceListKey;
import com.spark.psi.query.intf.publish.key.GetPurchaseListKey;
import com.spark.psi.query.intf.publish.key.GetSalesListKey;
import com.spark.psi.query.intf.publish.key.GetTicketListKey;
import com.spark.psi.publish.DeliverTicketType;

public class QueryService extends Service {

	public QueryService() {
		super("com.spark.psi.query.intenal.service.QueryService");
	}

	@Publish
	protected class GetGoodsCheckInList extends OneKeyResultProvider<GoodsCheckInListEntity, GetGoodsCheckInListKey> {

		@Override
		protected GoodsCheckInListEntity provide(Context context, GetGoodsCheckInListKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addTable(ERPTableNames.Inventory.CheckinSheet_Det.getTableName(), "d");
			qb.addEquals("t.RECID", "d.sheetId");
			qb.addArgs("stype", qb.STRING, CheckingInType.RealGoods.getCode());
			qb.addEquals("t.sheetType", "@stype");
			qb.addColumn("t.RECID", "recid");
			qb.addColumn("d.goodsId", "goodsId");
			qb.addColumn("d.goodsCode", "goodsCode");
			qb.addColumn("d.goodsName", "goodsName");
			qb.addColumn("d.unit", "unit");
			qb.addColumn("d.price", "price");
			qb.addColumn("d.amount", "amount");
			qb.addColumn("d.realCount", "realCount");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.deptName", "deptName");
			qb.addColumn("t.checkinDate", "checkinDate");
			qb.addColumn("t.sheetNo", "sheetNo");

			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("gname", qb.STRING, key.getGoodsName());
				qb.addLike("d.goodsName", "@gname");
			}
			if (CheckIsNull.isNotEmpty(key.getProduceSheetNo())) {
				qb.addArgs("relano", qb.STRING, key.getProduceSheetNo());
				qb.addLike("t.relaBillsNo", "@relano");
			}
			if (CheckIsNull.isNotEmpty(key.getDepartment())) {
				qb.addArgs("deptname", qb.STRING, key.getDepartment());
				qb.addLike("t.deptName", "@deptname");
			}
			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThanOrEquals("t.checkinDate", "@cBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("t.checkinDate", "@cEnd");
			}

			qb.addOrderBy("t.checkinDate");
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<GoodsCheckInItem> list = new ArrayList<GoodsCheckInItem>();
			while (rs.next()) {
				GoodsCheckInItemImpl item = new GoodsCheckInItemImpl();
				int index = 0;
				item.setSheetId(rs.getFields().get(index++).getGUID());
				item.setGoodsId(rs.getFields().get(index++).getGUID());
				item.setGoodsCode(rs.getFields().get(index++).getString());// 商品编码
				item.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				item.setUnit(rs.getFields().get(index++).getString());// 商品单位
				item.setCost(rs.getFields().get(index++).getDouble());// 实际成本
				item.setAmount(rs.getFields().get(index++).getDouble());// 商品金额
				item.setCount(rs.getFields().get(index++).getDouble());// 入库数量
				GoodsItem gi = context.find(GoodsItem.class, item.getGoodsId());
				item.setStandardCost(gi.getStandardCost());
				item.setStandardAmount(DoubleUtil.mul(item.getCount(), gi.getStandardCost()));
				item.setProduceSheetId(rs.getFields().get(index++).getGUID());
				item.setProduceSheetNo(rs.getFields().get(index++).getString());
				item.setDepartment(rs.getFields().get(index++).getString());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setSheetNo(rs.getFields().get(index++).getString());
				item.setNeedProduce(gi.isNeedProduce());
				if (null == key.isNeedProduce() || (key.isNeedProduce() == gi.isNeedProduce()))
					list.add(item);
			}
			return new GoodsCheckInListEntity(list, list.size());
		}
	}

	@Publish
	protected class GetMaterialsCheckInList extends
			OneKeyResultProvider<MaterialsCheckInListEntity, GetMaterialsCheckInListKey> {

		@Override
		protected MaterialsCheckInListEntity provide(Context context, GetMaterialsCheckInListKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckinSheet.getTableName(), "t");
			qb.addTable(ERPTableNames.Inventory.CheckinSheet_Det.getTableName(), "d");
			qb.addEquals("t.RECID", "d.sheetId");
			qb.addArgs("stype1", qb.STRING, CheckingInType.Irregular.getCode());
			qb.addArgs("stype2", qb.STRING, CheckingInType.Purchase.getCode());
			qb.addCondition("(t.sheetType = @stype1 or t.sheetType = @stype2)");
			qb.addColumn("t.RECID", "recid");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("d.goodsId", "goodsId");
			qb.addColumn("d.goodsCode", "goodsCode");
			qb.addColumn("d.goodsName", "goodsName");
			qb.addColumn("d.unit", "unit");
			qb.addColumn("d.price", "price");
			qb.addColumn("d.amount", "amount");
			qb.addColumn("d.realCount", "realCount");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.deptName", "deptName");
			qb.addColumn("t.checkinDate", "checkinDate");
			qb.addColumn("t.sheetType", "stype");
			qb.addColumn("t.partnerId", "partnerId");
			qb.addColumn("t.partnerName", "partnerName");
			qb.addColumn("t.partnerCode", "partnerCode");
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("gname", qb.STRING, key.getGoodsName());
				qb.addLike("d.goodsName", "@gname");
			}
			if (CheckIsNull.isNotEmpty(key.getPurchaseSheetNo())) {
				qb.addArgs("relano", qb.STRING, key.getPurchaseSheetNo());
				qb.addLike("t.relaBillsNo", "@relano");
			}
			if (CheckIsNull.isNotEmpty(key.getSheetNo())) {
				qb.addArgs("sheetNo", qb.STRING, key.getSheetNo());
				qb.addLike("t.sheetNo", "@sheetNo");
			}
			if (CheckIsNull.isNotEmpty(key.getPartnerName())) {
				qb.addArgs("pName", qb.STRING, key.getPartnerName());
				qb.addLike("t.partnerName", "@pName");
			}
			if (CheckIsNull.isNotEmpty(key.getPartnerNo())) {
				qb.addArgs("pCode", qb.STRING, key.getPartnerNo());
				qb.addLike("t.partnerCode", "@pCode");
			}
			if (key.getCheckinDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCheckinDateBegin());
				qb.addGreaterThanOrEquals("t.checkinDate", "@cBegin");
			}
			if (key.getCheckinDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCheckinDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("t.checkinDate", "@cEnd");
			}
			qb.addOrderBy("t.checkinDate");
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<MaterialsCheckInItem> list = new ArrayList<MaterialsCheckInItem>();
			while (rs.next()) {
				MaterialsCheckInItemImpl item = new MaterialsCheckInItemImpl();
				int index = 0;
				item.setSheetId(rs.getFields().get(index++).getGUID());
				item.setSheetNo(rs.getFields().get(index++).getString());
				item.setGoodsId(rs.getFields().get(index++).getGUID());
				item.setGoodsCode(rs.getFields().get(index++).getString());// 商品编码
				item.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				item.setUnit(rs.getFields().get(index++).getString());// 商品单位
				item.setPrice(rs.getFields().get(index++).getDouble());// 实际成本
				item.setAmount(rs.getFields().get(index++).getDouble());// 商品金额
				item.setCount(rs.getFields().get(index++).getDouble());// 入库数量
				MaterialsItem mi = context.find(MaterialsItem.class, item.getGoodsId());
				item.setStandardCost(mi.getStandardPrice());
				item.setStandardAmount(DoubleUtil.mul(item.getCount(), mi.getStandardPrice()));
				item.setPurchaseSheetId(rs.getFields().get(index++).getGUID());
				item.setPurchaseSheetNo(rs.getFields().get(index++).getString());
				item.setDepartment(rs.getFields().get(index++).getString());
				item.setCheckinDate(rs.getFields().get(index++).getDate());
				item.setCheckingInType(CheckingInType.getCheckingInType(rs.getFields().get(index++).getString()));
				item.setPartnerId(rs.getFields().get(index++).getGUID());
				item.setPartnerName(rs.getFields().get(index++).getString());
				item.setPartnerNo(rs.getFields().get(index++).getString());
				list.add(item);
			}
			return new MaterialsCheckInListEntity(list, list.size());
		}

	}

	@Publish
	protected class GetMaterialsCheckOutList extends
			OneKeyResultProvider<MaterialsCheckOutListEntity, GetMaterialsCheckOutListKey> {

		@Override
		protected MaterialsCheckOutListEntity provide(Context context, GetMaterialsCheckOutListKey key)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet.getTableName(), "t");
			qb.addTable(ERPTableNames.Inventory.CheckoutSheet_Det.getTableName(), "d");
			qb.addEquals("t.RECID", "d.sheetId");

			qb.addColumn("t.RECID", "recid");
			qb.addColumn("t.sheetNo", "sheetNo");
			qb.addColumn("d.goodsId", "goodsId");
			qb.addColumn("d.goodsCode", "goodsCode");
			qb.addColumn("d.goodsName", "goodsName");
			qb.addColumn("d.unit", "unit");
			qb.addColumn("d.price", "price");
			qb.addColumn("d.amount", "amount");
			qb.addColumn("d.realCount", "realCount");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.deptName", "deptName");
			qb.addColumn("t.checkoutDate", "checkoutDate");
			qb.addColumn("t.checkoutType", "stype");
			qb.addColumn("t.partnerId", "partnerId");
			qb.addColumn("t.partnerName", "partnerName");
			qb.addColumn("t.partnerCode", "partnerCode");

			if (null != key.getCheckingOutType() && key.getCheckingOutType().length > 0) {
				StringBuffer sql = new StringBuffer();
				if (key.getCheckingOutType().length > 1)
					sql.append(" (");
				for (int i = 0; i < key.getCheckingOutType().length; i++) {
					if (i != 0) {
						sql.append(" or ");
					}
					sql.append(" t.checkoutType='").append(key.getCheckingOutType()[i].getCode()).append("' ");
				}
				if (key.getCheckingOutType().length > 1)
					sql.append(") ");
				qb.addCondition(sql.toString());
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("gname", qb.STRING, key.getGoodsName());
				qb.addLike("d.goodsName", "@gname");
			}
			if (CheckIsNull.isNotEmpty(key.getProduceSheetNo())) {
				qb.addArgs("relano", qb.STRING, key.getProduceSheetNo());
				qb.addLike("t.relaBillsNo", "@relano");
			}
			if (CheckIsNull.isNotEmpty(key.getSheetNo())) {
				qb.addArgs("sheetNo", qb.STRING, key.getSheetNo());
				qb.addLike("t.sheetNo", "@sheetNo");
			}
			if (CheckIsNull.isNotEmpty(key.getDepartment())) {
				qb.addArgs("deptname", qb.STRING, key.getDepartment());
				qb.addLike("t.deptName", "@deptname");
			}
			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThanOrEquals("t.checkoutDate", "@cBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("t.checkoutDate", "@cEnd");
			}
			qb.addOrderBy("t.checkoutDate");
			qb.getRecordLimit(key.getOffset(), key.getCount());
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<MaterialsCheckOutItem> list = new ArrayList<MaterialsCheckOutItem>();
			while (rs.next()) {
				MaterialsCheckOutItemImpl item = new MaterialsCheckOutItemImpl();
				int index = 0;
				item.setSheetId(rs.getFields().get(index++).getGUID());
				item.setSheetNo(rs.getFields().get(index++).getString());
				item.setGoodsId(rs.getFields().get(index++).getGUID());
				item.setGoodsCode(rs.getFields().get(index++).getString());// 商品编码
				item.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				item.setUnit(rs.getFields().get(index++).getString());// 商品单位
				item.setCost(rs.getFields().get(index++).getDouble());// 实际成本
				item.setAmount(rs.getFields().get(index++).getDouble());// 商品金额
				item.setCount(rs.getFields().get(index++).getDouble());// 入库数量
				item.setProduceSheetId(rs.getFields().get(index++).getGUID());
				item.setProduceSheetNo(rs.getFields().get(index++).getString());
				item.setDepartment(rs.getFields().get(index++).getString());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setCheckingOutType(CheckingOutType.getCheckingOutType(rs.getFields().get(index++).getString()));
				// item.setPartnerId(rs.getFields().get(index++).getGUID());
				// item.setPartnerName(rs.getFields().get(index++).getString());
				// item.setPartnerNo(rs.getFields().get(index++).getString());
				list.add(item);
			}
			return new MaterialsCheckOutListEntity(list, list.size());
		}

	}

	@Publish
	protected class GetProduceList extends OneKeyResultProvider<ProduceListEntity, GetProduceListKey> {

		@Override
		protected ProduceListEntity provide(Context context, GetProduceListKey key) throws Throwable {
			List<ProduceItem> dataList = new ArrayList<ProduceItem>();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Sales.Produce_GoodsDet.getTableName(), "d");
			qb.addTable(ERPTableNames.Sales.Produce_Order.getTableName(), "t");
			qb.addEquals("d.billsId", "t.RECID");
			qb.addColumn("d.billsId", "billsId");// 订单GUID
			qb.addColumn("t.billsNo", "billsNo");// 订单编号
			qb.addColumn("d.goodsId", "goodsId");// 商品Guid
			qb.addColumn("d.goodsCode", "goodsCode");// 商品编号
			qb.addColumn("d.goodsName", "goodsName");// 商品名称
			qb.addColumn("d.unit", "unit");// 单位
			qb.addColumn("d.\"count\"", "\"count\"");// 数量
			qb.addColumn("d.finishedCount", "finishedCount");// 已完成数量
			qb.addColumn("t.createDate", "createDate");// 制单日期
			qb.addColumn("t.planDate", "planDate");
			qb.addColumn("d.bomId", "bomId");
			qb.addCondition(" d.finishedCount>0 ");
			if (CheckIsNull.isNotEmpty(key.getBillsNo())) {
				qb.addArgs("billsNo", qb.STRING, key.getBillsNo().trim());
				qb.addLike("t.billsNo", "@billsNo");
			}
			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("createDateBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThanOrEquals("t.createDate", "@createDateBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("createDateEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("t.createDate", "@createDateEnd");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode().trim());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("goodsName", qb.STRING, key.getGoodsName().trim());
				qb.addLike("d.goodsName", "@goodsName");
			}
			if (key.getPlanDateBegin() > 0) {
				qb.addArgs("planDateBegin", qb.DATE, key.getPlanDateBegin());
				qb.addGreaterThanOrEquals("t.planDate", "@planDateBegin");
			}
			if (key.getPlanDateEnd() > 0) {
				qb.addArgs("planDateEnd", qb.DATE, key.getPlanDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("t.createDate", "@planDateEnd");
			}
			qb.addOrderBy("t.createDate");
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			while (rs.next()) {
				ProduceItemImpl impl = new ProduceItemImpl();
				int index = 0;
				impl.setBillsId(rs.getFields().get(index++).getGUID());// 订单GUID
				impl.setBillsNo(rs.getFields().get(index++).getString());// 订单编号
				impl.setGoodsId(rs.getFields().get(index++).getGUID());// 商品Guid
				impl.setGoodsCode(rs.getFields().get(index++).getString());// 商品编号
				impl.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				impl.setUnit(rs.getFields().get(index++).getString());// 单位
				impl.setCount(rs.getFields().get(index++).getDouble());// 数量
				impl.setFinishedCount(rs.getFields().get(index++).getDouble());// 已完成数量
				impl.setCreateDate(rs.getFields().get(index++).getDate());// 制单日期
				impl.setPlanDate(rs.getFields().get(index++).getDate());// 预计完成日期
				GUID bomId = rs.getFields().get(index++).getGUID();
				List<ProduceItemImpl.LogImpl> logList = QueryServiceUtil.getProduceLogs(context, impl);
				// ProduceItemImpl.LogImpl[] logs = new
				// ProduceItemImpl.LogImpl[logList.size()];
				List<ProduceItemImpl.ItemImpl> itemList = QueryServiceUtil.getProduceItems(context, impl, bomId);
				// ProduceItemImpl.ItemImpl[] items = new
				// ProduceItemImpl.ItemImpl[itemList.size()];
				impl.setLogs(logList.toArray(new ProduceItemImpl.LogImpl[0]));
				impl.setItems(itemList.toArray(new ProduceItemImpl.ItemImpl[0]));
				dataList.add(impl);
			}
			return new ProduceListEntity(dataList, dataList.size());
		}

	}

	@Publish
	protected class GetPurchaseList extends OneKeyResultProvider<PurchaseListEntity, GetPurchaseListKey> {

		@Override
		protected PurchaseListEntity provide(Context context, GetPurchaseListKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.Checkingin.getTableName(), "t");
			qb.addTable(ERPTableNames.Inventory.Checkingin_Det.getTableName(), "d");
			qb.addTable(ERPTableNames.Purchase.Order.getTableName(), "o");
			qb.addEquals("o.RECID", "t.relaBillsId");
			qb.addEquals("t.RECID", "d.sheetId");
			qb.addColumn("t.partnerCode", "partnerCode");
			qb.addColumn("t.partnerName", "partnerName");
			qb.addColumn("t.partnerShortName", "partnerShortName");
			qb.addColumn("t.partnerId", "partnerId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("d.goodsId", "goodsId");
			qb.addColumn("d.goodsCode", "goodsCode");
			qb.addColumn("d.goodsName", "goodsName");
			qb.addColumn("d.unit", "unit");
			qb.addColumn("d.price", "price");
			qb.addColumn("d.planCount", "planCount");
			qb.addColumn("o.totalAmount", "totalAmount");
			qb.addColumn("d.checkinCount", "checkinCount");
			qb.addColumn("o.createDate", "createDate");
			qb.addColumn("o.deliveryDate", "deliveryDate");
			qb.addColumn("o.status", "status");
			if (CheckIsNull.isNotEmpty(key.getBillsNo())) {
				qb.addArgs("billsNo", qb.STRING, key.getBillsNo());
				qb.addLike("t.relaBillsNo", "@billsNo");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("gName", qb.STRING, key.getGoodsName());
				qb.addLike("d.goodsName", "@gName");
			}
			if (CheckIsNull.isNotEmpty(key.getSupplierName())) {
				qb.addArgs("pName", qb.STRING, key.getSupplierName());
				qb.addLike("t.partnerName", "@pName");
			}
			if (CheckIsNull.isNotEmpty(key.getSupplierNo())) {
				qb.addArgs("pCode", qb.STRING, key.getSupplierNo());
				qb.addLike("t.partnerCode", "@pCode");
			}
			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThanOrEquals("o.createDate", "@cBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("o.createDate", "@cEnd");
			}
			if (key.getDeliveryDateBegin() > 0) {
				qb.addArgs("dBegin", qb.DATE, key.getDeliveryDateBegin());
				qb.addGreaterThanOrEquals("o.deliveryDate", "@dBegin");
			}
			if (key.getDeliveryDateEnd() > 0) {
				qb.addArgs("dEnd", qb.DATE, key.getDeliveryDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("o.deliveryDate", "@dEnd");
			}
			qb.addOrderBy("o.createDate");
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<PurchaseItem> dataList = new ArrayList<PurchaseItem>();
			while (rs.next()) {
				PurchaseItemImpl item = new PurchaseItemImpl();
				int index = 0;
				item.setSupplierNo(rs.getFields().get(index++).getString());
				item.setSupplierName(rs.getFields().get(index++).getString());
				item.setShortName(rs.getFields().get(index++).getString());
				item.setSupplierId(rs.getFields().get(index++).getGUID());
				item.setBillsNo(rs.getFields().get(index++).getString());
				item.setBillsId(rs.getFields().get(index++).getGUID());
				item.setGoodsId(rs.getFields().get(index++).getGUID());// 商品Guid
				item.setGoodsCode(rs.getFields().get(index++).getString());// 商品编码
				item.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				item.setUnit(rs.getFields().get(index++).getString());// 单位
				item.setPrice(rs.getFields().get(index++).getDouble());// 单价
				item.setCount(rs.getFields().get(index++).getDouble());// 数量
				item.setAmount(rs.getFields().get(index++).getDouble());// 金额
				item.setCheckedinCount(rs.getFields().get(index++).getDouble());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setDeliveryDate(rs.getFields().get(index++).getDate());
				item.setStatus(rs.getFields().get(index++).getString());
				item.setCheckedinAmount(item.getCount() == item.getCheckedinCount() ? item.getAmount() : DoubleUtil
						.mul(item.getCheckedinCount(), item.getPrice()));
				item.setCheckinAmount(DoubleUtil.sub(item.getAmount(), item.getCheckedinAmount()));
				item.setCheckinCount(DoubleUtil.sub(item.getCount(), item.getCheckedinCount()));
				MaterialsItem mi = context.find(MaterialsItem.class, item.getGoodsId());
				item.setStandardPrice(mi.getStandardPrice());
				dataList.add(item);
			}
			return new PurchaseListEntity(dataList, dataList.size());
		}
	}

	@Publish
	protected class GetSalesList extends OneKeyResultProvider<SalesListEntity, GetSalesListKey> {

		@Override
		protected SalesListEntity provide(Context context, GetSalesListKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.Checkingout.getTableName(), "t");
			qb.addTable(ERPTableNames.Inventory.Checkingout_Det.getTableName(), "d");
			qb.addTable(ERPTableNames.Sales.Order.getTableName(), "o");
			qb.addEquals("o.RECID", "t.relaBillsId");
			qb.addEquals("t.RECID", "d.sheetId");
			qb.addColumn("t.partnerCode", "partnerCode");
			qb.addColumn("t.partnerName", "partnerName");
			qb.addColumn("t.partnerShortName", "partnerShortName");
			qb.addColumn("t.partnerId", "partnerId");
			qb.addColumn("t.relaBillsNo", "relaBillsNo");
			qb.addColumn("t.relaBillsId", "relaBillsId");
			qb.addColumn("d.goodsId", "goodsId");
			qb.addColumn("d.goodsCode", "goodsCode");
			qb.addColumn("d.goodsName", "goodsName");
			qb.addColumn("d.unit", "unit");
			qb.addColumn("d.price", "price");
			qb.addColumn("d.planCount", "planCount");
			qb.addColumn("o.totalAmount", "totalAmount");
			qb.addColumn("d.checkoutCount", "checkoutCount");
			qb.addColumn("o.createDate", "createDate");
			qb.addColumn("o.deliveryDate", "deliveryDate");
			qb.addColumn("o.status", "status");
			if (CheckIsNull.isNotEmpty(key.getBillsNo())) {
				qb.addArgs("billsNo", qb.STRING, key.getBillsNo());
				qb.addLike("t.relaBillsNo", "@billsNo");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("gName", qb.STRING, key.getGoodsName());
				qb.addLike("d.goodsName", "@gName");
			}
			if (CheckIsNull.isNotEmpty(key.getCustomerName())) {
				qb.addArgs("pName", qb.STRING, key.getCustomerName());
				qb.addLike("t.partnerName", "@pName");
			}
			// if (CheckIsNull.isNotEmpty(key.getSupplierNo())) {
			// qb.addArgs("pCode", qb.STRING, key.getSupplierNo());
			// qb.addLike("t.partnerCode", "@pCode");
			// }
			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThanOrEquals("o.createDate", "@cBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("o.createDate", "@cEnd");
			}
			if (key.getDeliveryDateBegin() > 0) {
				qb.addArgs("dBegin", qb.DATE, key.getDeliveryDateBegin());
				qb.addGreaterThanOrEquals("o.deliveryDate", "@dBegin");
			}
			if (key.getDeliveryDateEnd() > 0) {
				qb.addArgs("dEnd", qb.DATE, key.getDeliveryDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("o.deliveryDate", "@dEnd");
			}

			qb.addOrderBy("o.createDate");
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<SalesItem> dataList = new ArrayList<SalesItem>();
			while (rs.next()) {
				SalesItemImpl item = new SalesItemImpl();
				int index = 0;
				item.setCustomerNo(rs.getFields().get(index++).getString());
				item.setCustomerName(rs.getFields().get(index++).getString());
				item.setCustomerShortName(rs.getFields().get(index++).getString());
				item.setCustomerId(rs.getFields().get(index++).getGUID());
				item.setBillsNo(rs.getFields().get(index++).getString());
				item.setBillsId(rs.getFields().get(index++).getGUID());
				item.setGoodsId(rs.getFields().get(index++).getGUID());// 商品Guid
				item.setGoodsCode(rs.getFields().get(index++).getString());// 商品编码
				item.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				item.setUnit(rs.getFields().get(index++).getString());// 单位
				item.setPrice(rs.getFields().get(index++).getDouble());// 单价
				item.setCount(rs.getFields().get(index++).getDouble());// 数量
				item.setAmount(rs.getFields().get(index++).getDouble());// 金额
				item.setCheckedoutCount(rs.getFields().get(index++).getDouble());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setDeliveryDate(rs.getFields().get(index++).getDate());
				item.setStatus(rs.getFields().get(index++).getString());
				item.setCheckedoutAmount(item.getCount() == item.getCheckedoutCount() ? item.getAmount() : DoubleUtil
						.mul(item.getCheckedoutCount(), item.getPrice()));
				item.setCheckoutAmount(DoubleUtil.sub(item.getAmount(), item.getCheckedoutAmount()));
				item.setCheckoutCount(DoubleUtil.sub(item.getCount(), item.getCheckedoutCount()));
				MaterialsItem mi = context.find(MaterialsItem.class, item.getGoodsId());
				item.setGoodsNo(mi.getMaterialNo());
				dataList.add(item);
			}
			return new SalesListEntity(dataList, dataList.size());
		}
	}

	@Publish
	protected class GetTicketList extends OneKeyResultProvider<TicketListEntity, GetTicketListKey> {

		@Override
		protected TicketListEntity provide(Context context, GetTicketListKey key) throws Throwable {
			List<TicketItem> dataList = new ArrayList<TicketItem>();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Sales.OnlineOrder_Det.getTableName(), "d");
			qb.addTable(ERPTableNames.Sales.Deliver_Ticket.getTableName(), "t");
			qb.addEquals("d.billsId", "t.onlineOrderId");
			qb.addColumn("t.recid", "id");// 订单GUID
			qb.addColumn("d.goodsId", "goodsId");// 商品ID
			qb.addColumn("d.goodsCode", "goodsCode");// 商品编码
			qb.addColumn("d.goodsNo", "goodsNo");// 商品条码
			qb.addColumn("d.goodsName", "goodsName");// 商品名称
			qb.addColumn("d.unit", "unit");// 单位
			qb.addColumn("d.price", "price");// 单价
			qb.addColumn("d.\"count\"", "\"count\"");// 数量
			qb.addColumn("d.amount", "amount");// 金额
			qb.addColumn("t.sheetNo", "sheetNo");// 单据编号
			qb.addColumn("t.createDate", "createDate");// 发货日期
			qb.addColumn("t.deliverTicketType", "deliverTicketType");// 发货类型
			qb.addColumn("t.memberId", "memberId");// 客户ID
			qb.addColumn("t.memberRealName", "memberRealName");// 客户名称

			// if (CheckIsNull.isNotEmpty(key.getCreateDateBegin())) {
			// qb.addColumn("t.RECID", "RECID");// 订单GUID
			// qb.addColumn("t.goodsId", "goodsId");// 商品ID
			// qb.addColumn("t.goodsCode", "goodsCode");// 商品编码
			// qb.addColumn("t.goodsNo", "goodsNo");// 商品条码
			// qb.addColumn("t.goodsName", "goodsName");// 商品名称
			// qb.addColumn("t.unit", "unit");// 单位
			// qb.addColumn("t.price", "price");// 单价
			// qb.addColumn("t.\"count\"", "\"count\"");// 数量
			// qb.addColumn("t.amount", "amount");// 金额
			// qb.addColumn("t.sheetNo", "sheetNo");// 单据编号
			// qb.addColumn("t.createDate", "createDate");// 发货日期
			// qb.addColumn("t.deliverTicketType", "deliverTicketType");// 发货类型
			// qb.addColumn("t.memberId", "memberId");// 客户ID
			// qb.addColumn("t.memberRealName", "memberRealName");// 客户名称

			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThanOrEquals("t.createDate", "@cBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThanOrEquals("t.createDate", "@cEnd");
			}
			if (CheckIsNull.isNotEmpty(key.getDeliverTicketType())) {
				qb.addArgs("deliverTicketType", qb.STRING, key.getDeliverTicketType().getCode());
				qb.addCondition("t.deliverTicketType=@deliverTicketType");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("goodsCode", qb.STRING, key.getGoodsCode().trim());
				qb.addLike("d.goodsCode", "@goodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("goodsName", qb.STRING, key.getGoodsName().trim());
				qb.addLike("d.goodsName", "@goodsName");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsNo())) {
				qb.addArgs("goodsNo", qb.STRING, key.getGoodsNo().trim());
				qb.addLike("d.goodsNo", "@goodsNo");
			}
			if (CheckIsNull.isNotEmpty(key.getMemberRealName())) {
				qb.addArgs("memberRealName", qb.STRING, key.getMemberRealName().trim());
				qb.addLike("t.memberRealName", "@memberRealName");
			}
			if (CheckIsNull.isNotEmpty(key.getSheetNo())) {
				qb.addArgs("sheetNo", qb.STRING, key.getSheetNo());
				qb.addLike("t.sheetNo", "@sheetNo");
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			while (rs.next()) {
				TicketItemImpl impl = new TicketItemImpl();
				int index = 0;
				impl.setTicketId(rs.getFields().get(index++).getGUID());// 订单GUID
				impl.setGoodsId(rs.getFields().get(index++).getGUID());// 商品ID
				impl.setGoodsCode(rs.getFields().get(index++).getString());// 商品编码
				impl.setGoodsNo(rs.getFields().get(index++).getString());// 商品条码
				impl.setGoodsName(rs.getFields().get(index++).getString());// 商品名称
				impl.setUnit(rs.getFields().get(index++).getString());// 单位
				impl.setPrice(rs.getFields().get(index++).getDouble());// 单价
				impl.setCount(rs.getFields().get(index++).getDouble());// 数量
				impl.setAmount(rs.getFields().get(index++).getDouble());// 金额
				impl.setSheetNo(rs.getFields().get(index++).getString());// 单据编号
				impl.setCreateDate(rs.getFields().get(index++).getDate());// 发货日期
				impl.setDeliverTicketType(DeliverTicketType.getDeliverTicketType(rs.getFields().get(index++)
						.getString()));// 发货类型
				impl.setMemberId(rs.getFields().get(index++).getGUID());// 客户ID
				impl.setMemberRealName(rs.getFields().get(index++).getString());// 客户名称
				dataList.add(impl);
			}
			// }
			return new TicketListEntity(dataList, dataList.size());

		}
	}

	@Publish
	protected class GetOnlineSalesList extends OneKeyResultProvider<OnlineSalesListEntity, GetOnlineSalesListKey> {

		@Override
		protected OnlineSalesListEntity provide(Context context, GetOnlineSalesListKey key) throws Throwable {
			List<OnlineSalesItem> list = new ArrayList<OnlineSalesItem>();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Sales.OnlineOrder_Det.getTableName(), "d");
			qb.addTable(ERPTableNames.Sales.OnlineOrder.getTableName(), "t");
			qb.addEquals("d.billsId", "t.recid");
			qb.addColumn("d.goodsId", "goodsId");
			qb.addColumn("d.goodsCode", "goodsCode");
			qb.addColumn("d.goodsNo", "goodsNo");
			qb.addColumn("d.goodsName", "goodsName");
			qb.addColumn("d.goodsSpec", "goodsSpec");
			qb.addColumn("d.unit", "unit");
			qb.addColumn("sum(d.\"count\")", "\"count\"");
			qb.addColumn("sum(d.amount)", "amount");
			qb.addArgs("status", qb.STRING, OnlineOrderStatus.Finished.getCode());
			qb.addEquals("t.status", "@status");
			if (null != key.getGoodsCategoryId()) {
				GoodsCategory gc = context.find(GoodsCategory.class, key.getGoodsCategoryId());
				if (null != gc) {
					GoodsItem[] goods = gc.getGoodsItems(context);

					if (null != goods && goods.length > 0) {
						List<String> params = new ArrayList<String>();
						for (int i = 0; i < goods.length; i++) {
							params.add("@goodsId" + i);
							qb.addArgs("goodsId" + i, qb.guid, goods[i].getId());
						}
						qb.addIn("d.goodsId", params);
					} else {
						qb.addCondition(" 1<>1 ");
					}
				}
			}
			if (CheckIsNull.isNotEmpty(key.getCustomerName())) {
				qb.addArgs("customerName", qb.STRING, key.getCustomerName().trim());
				qb.addLike("t.realName", "@customerName");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsName())) {
				qb.addArgs("getGoodsName", qb.STRING, key.getGoodsName().trim());
				qb.addLike("d.goodsName", "@getGoodsName");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsCode())) {
				qb.addArgs("getGoodsCode", qb.STRING, key.getGoodsCode().trim());
				qb.addLike("d.goodsCode", "@getGoodsCode");
			}
			if (CheckIsNull.isNotEmpty(key.getGoodsNo())) {
				qb.addArgs("getGoodsNo", qb.STRING, key.getGoodsNo().trim());
				qb.addLike("d.goodsNo", "@getGoodsNo");
			}
			if (CheckIsNull.isNotEmpty(key.getStationId())) {
				qb.addArgs("stationId", qb.guid, key.getStationId());
				qb.addEquals("t.stationId", "@stationId");
			}
			if (key.getCreateDateBegin() > 0) {
				qb.addArgs("cBegin", qb.DATE, key.getCreateDateBegin());
				qb.addGreaterThan("t.createDate", "@cBegin");
			}
			if (key.getCreateDateEnd() > 0) {
				qb.addArgs("cEnd", qb.DATE, key.getCreateDateEnd()+24*3600000-1);
				qb.addLessThan("t.createDate", "@cEnd");
			}
			if (null != key.getSearchText()) {
				String searchText = key.getSearchText().trim();
				StringBuffer sql = new StringBuffer();
				// sql.append(" (t.realName like '%").append(searchText).append("%' ");
				sql.append(" (d.goodsCode like '%").append(searchText).append("%' ");
				sql.append(" or d.goodsNo like '%").append(searchText).append("%' ");
				sql.append(" or d.goodsName like '%").append(searchText).append("%') ");
				// sql.append(" or d.stationName like '%").append(searchText).append("%') ");
				qb.addCondition(sql.toString());
			}
			qb.addGroupBy("d.goodsId");

			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			double totalAmount = 0;
			while (rs.next()) {
				int index = 0;
				OnlineSalesItemImpl impl = new OnlineSalesItemImpl();
				impl.setGoodsId(rs.getFields().get(index++).getGUID());
				impl.setGoodsCode(rs.getFields().get(index++).getString());
				impl.setGoodsNo(rs.getFields().get(index++).getString());
				impl.setGoodsName(rs.getFields().get(index++).getString());
				impl.setGoodsSpec(rs.getFields().get(index++).getString());
				impl.setUnit(rs.getFields().get(index++).getString());
				impl.setCount(rs.getFields().get(index++).getDouble());
				impl.setAmount(rs.getFields().get(index++).getDouble());

				totalAmount += impl.getAmount();
				list.add(impl);
			}
			OnlineSalesListEntity listEntity = new OnlineSalesListEntity(list, list.size());
			listEntity.setTotalAmount(totalAmount);
			return listEntity;
		}

	}

}
