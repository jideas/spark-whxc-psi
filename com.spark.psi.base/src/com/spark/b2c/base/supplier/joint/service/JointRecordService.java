package com.spark.b2c.base.supplier.joint.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordItem;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordListEntity;
import com.spark.b2c.publish.JointVenture.key.GetJointVentureRecordListKey;
import com.spark.b2c.publish.JointVenture.task.CreateJointVentureTask;
import com.spark.b2c.publish.JointVenture.task.JointVentureTaskItem;
import com.spark.b2c.publish.JointVenture.task.MakeJointRecordSettlementedTask;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.publicimpl.JointVentureRecordItemImpl;

public class JointRecordService extends Service {

	public JointRecordService() {
		super("com.spark.b2c.base.supplier.joint.service.JointRecordService");
	}

	@Publish
	protected class InsertJointVentureHandle extends SimpleTaskMethodHandler<CreateJointVentureTask> {

		@Override
		protected void handle(Context context, CreateJointVentureTask task) throws Throwable {
			if (null == task.getItems()) {
				return;
			}
			for (JointVentureTaskItem item : task.getItems()) {
				doSave(context, item);
			}
		}

		private void doSave(Context context, JointVentureTaskItem item) throws Exception {
			MaterialsItem goods = context.find(MaterialsItem.class, item.getGoodsId());
			if (!goods.getMaterial().isJointVenture()) {
				return;
			}
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Joint.Joint_Record.getTableName());
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("goodsId", ib.guid, item.getGoodsId());
			ib.addColumn("sheetId", ib.guid, item.getSheetId());
			ib.addColumn("supplierId", ib.guid, goods.getMaterial().getSupplierId());
			Partner p = context.find(Partner.class, goods.getMaterial().getSupplierId());
			ib.addColumn("supplierName", ib.STRING, p.getName());
			ib.addColumn("shortName", ib.STRING, p.getShortName());
			ib.addColumn("supplierNamePY", ib.STRING, PinyinHelper.getLetter(p.getName()));
			ib.addColumn("sheetNo", ib.STRING, item.getSheetNo());
			ib.addColumn("goodsCode", ib.STRING, goods.getMaterialCode());
			ib.addColumn("goodsNo", ib.STRING, goods.getMaterialNo());
			ib.addColumn("goodsSpec", ib.STRING, goods.getSpec());
			ib.addColumn("goodsUnit", ib.STRING, goods.getMaterialUnit());
			ib.addColumn("goodsName", ib.STRING, goods.getMaterialName());
			ib.addColumn("goodsPrice", ib.DOUBLE, goods.getSalePrice());
			ib.addColumn("salesCount", ib.DOUBLE, item.getCount());
			ib.addColumn("amount", ib.DOUBLE, DoubleUtil.mul(item.getCount(), goods.getSalePrice(), 2));
			ib.addColumn("percentage", ib.DOUBLE, goods.getMaterial().getPercentage());
			if(goods.getMaterial().getPercentage()>1){
				throw new Exception("联营商品提点设置出错，请检查！");
			}
			ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
			ib.execute();
		}
	}

	@Publish
	protected class JointVentureRecordProvider extends
			OneKeyResultListProvider<JointVentureRecordItem, GetJointVentureRecordListKey> {

		@Override
		protected void provide(Context context, GetJointVentureRecordListKey key,
				List<JointVentureRecordItem> list) throws Throwable {
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
			qb.addColumn("t.salesCount", "\"count\"");
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

			if (key.getBeginTime() > 0) {
				qb.addArgs("begin", qb.DATE, key.getBeginTime());
				qb.addGreaterThanOrEquals("t.createDate", "@begin");
			}
			if (key.getEndTime() > 0) {
				qb.addArgs("end1", qb.DATE, key.getEndTime());
				qb.addLessThanOrEquals("t.createDate", "@end1");
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
		}
	}

	@Publish
	protected class GetJointVentureRecordList extends
			OneKeyResultProvider<JointVentureRecordListEntity, GetJointVentureRecordListKey> {

		@Override
		protected JointVentureRecordListEntity provide(Context context, GetJointVentureRecordListKey key)
				throws Throwable {
			List<JointVentureRecordItem> dataList = JointSeviceUtil.getJointVentureRecordItems(context, key);
			return new JointVentureRecordListEntity(dataList, dataList.size());
		}

	}

	/**
	 * 变更联营交易记录为已结算
	 */
	@Publish
	protected class MakeJointRecordSettlementedHandle extends
			SimpleTaskMethodHandler<MakeJointRecordSettlementedTask> {

		@Override
		protected void handle(Context context, MakeJointRecordSettlementedTask task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Joint.Joint_Record.getTableName());
			ub.addColumn("alreadySettlement", ub.BOOLEAN, true);
			ub.addCondition("before", ub.BOOLEAN, false, "t.alreadySettlement = @before");
			boolean b = false;
			if (null != task.getId()) {
				ub.addCondition("id", ub.guid, task.getId(), "t.RECID = @id");
				b = ub.execute() == 1;
			}
			if (null != task.getIdList()) {
				ub.addIn("RECID", ub.guid, task.getIdList().toArray());
				
				b = ub.execute() == task.getIdList().size();
			}
			if (!b) {
				throw new Exception("联营交易记录信息已过时，请检查！");
			}
		}
	}
}
