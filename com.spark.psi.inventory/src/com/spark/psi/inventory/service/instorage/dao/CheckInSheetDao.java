package com.spark.psi.inventory.service.instorage.dao;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.b2c.publish.JointVenture.task.CreateJointVentureTask;
import com.spark.b2c.publish.JointVenture.task.JointVentureTaskItem;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Department;
import com.spark.psi.base.Partner;
import com.spark.psi.inventory.internal.task.ChangeCheckinRealAmount;
import com.spark.psi.inventory.internal.task.CheckinAskAmountChangeTask;
import com.spark.psi.inventory.intf.entity.instorage.mod.CheckInSheetItem;
import com.spark.psi.inventory.intf.event.CheckInSheetAmountBalanceCompleteEvent;
import com.spark.psi.inventory.intf.task.instorage.CheckinSheetTask;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;

public class CheckInSheetDao extends Service {

	public CheckInSheetDao() {
		super("com.spark.psi.inventory.service.instorage.dao.CheckInSheetDao");
	}

	@Publish
	protected class InsertSheetHandle extends SimpleTaskMethodHandler<CheckinSheetTask> {

		@Override
		protected void handle(Context context, CheckinSheetTask task) throws Throwable {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Inventory.CheckinSheet.getTableName());
			ib.addColumn("RECID", ib.guid, task.getEntity().getRECID());
			ib.addColumn("sheetNo", ib.STRING, task.getEntity().getSheetNo());
			ib.addColumn("sheetType", ib.STRING, task.getEntity().getSheetType());
			ib.addColumn("partnerId", ib.guid, task.getEntity().getPartnerId());
			ib.addColumn("partnerName", ib.STRING, task.getEntity().getPartnerName());
			ib.addColumn("namePY", ib.STRING, task.getEntity().getNamePY());
			ib.addColumn("partnerShortName", ib.STRING, task.getEntity().getPartnerShortName());
			ib.addColumn("relaBillsId", ib.guid, task.getEntity().getRelaBillsId());
			ib.addColumn("relaBillsNo", ib.STRING, task.getEntity().getRelaBillsNo());
			ib.addColumn("storeId", ib.guid, task.getEntity().getStoreId());
			ib.addColumn("storeName", ib.STRING, task.getEntity().getStoreName());
			ib.addColumn("storeNamePY", ib.STRING, task.getEntity().getStoreNamePY());
			ib.addColumn("goodsFrom", ib.STRING, task.getEntity().getGoodsFrom());
			ib.addColumn("remark", ib.STRING, task.getEntity().getRemark());
			ib.addColumn("buyPerson", ib.STRING, task.getEntity().getBuyPerson());
			ib.addColumn("buyDate", ib.DATE, task.getEntity().getBuyDate());
			ib.addColumn("amount", ib.DOUBLE, task.getEntity().getAmount());
			ib.addColumn("askedAmount", ib.DOUBLE, task.getEntity().getAskedAmount());
			ib.addColumn("paidAmount", ib.DOUBLE, task.getEntity().getPaidAmount());
			ib.addColumn("disAmount", ib.DOUBLE, task.getEntity().getDisAmount());
			ib.addColumn("checkinDate", ib.DATE, task.getEntity().getCheckinDate());
			ib.addColumn("checkinPerson", ib.guid, task.getEntity().getCheckinPerson());
			ib.addColumn("checkinPersonName", ib.STRING, task.getEntity().getCheckinPersonName());
			ib.addColumn("deptId", ib.guid, task.getEntity().getDeptId());
			ib.addColumn("creatorId", ib.guid, task.getEntity().getCreatorId());
			ib.addColumn("creator", ib.STRING, task.getEntity().getCreator());
			if (null != task.getEntity().getDeptId()) {
				Department depart = context.find(Department.class, task.getEntity().getDeptId());
				if (null != depart) {
					ib.addColumn("deptName", ib.STRING, depart.getName());
				} else {
					throw new Exception("获取用户部门信息出错！");
				}
			}
			if (null != task.getEntity().getPartnerId()) {
				Partner p = context.find(Partner.class, task.getEntity().getPartnerId());
				ib.addColumn("partnerCode", ib.STRING, p.getCode());
			}
			if (ib.execute() == 1) {
				doSaveDetails(context, task);
				task.setSuccess(true);
			}
			if (task.getEntity().getSheetType().equals(CheckingInType.Joint.getCode())
					|| task.getEntity().getSheetType().equals(CheckingInType.RealGoods.getCode())
					|| task.getEntity().getSheetType().equals(CheckingInType.Kit.getCode())) {
				return;
			}
			writeJointRecord(context, task);
		}

		/**
		 * 联营交易信息
		 * 
		 * @param context
		 * @param task
		 * @throws Exception
		 */
		private void writeJointRecord(Context context, CheckinSheetTask sheet) throws Exception {
			List<JointVentureTaskItem> items = new ArrayList<JointVentureTaskItem>();
			for (CheckInSheetItem det : sheet.getItems()) {
				JointVentureTaskItem item = new JointVentureTaskItem();
				item.setGoodsId(det.getGoodsId());
				item.setSheetId(sheet.getEntity().getRECID());
				item.setSheetNo(sheet.getEntity().getSheetNo());
				item.setCount(DoubleUtil.sub(0, det.getRealCount()));
				items.add(item);
			}
			context.handle(new CreateJointVentureTask(items));
		}

		public void doSaveDetails(Context context, CheckinSheetTask task) {
			for (CheckInSheetItem item : task.getItems()) {
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable(ERPTableNames.Inventory.CheckinSheet_Det.getTableName());
				ib.addColumn("RECID", ib.guid, item.getRECID());
				ib.addColumn("sheetId", ib.guid, item.getSheetId());
				ib.addColumn("goodsId", ib.guid, item.getGoodsId());
				ib.addColumn("goodsCode", ib.STRING, item.getGoodsCode());
				ib.addColumn("goodsNo", ib.STRING, item.getGoodsNo());
				ib.addColumn("goodsName", ib.STRING, item.getGoodsName());
				ib.addColumn("goodsSpec", ib.STRING, item.getGoodsSpec());
				ib.addColumn("unit", ib.STRING, item.getUnit());
				ib.addColumn("scale", ib.INT, item.getScale());
				ib.addColumn("price", ib.DOUBLE, item.getPrice());
				ib.addColumn("amount", ib.DOUBLE, item.getAmount());
				ib.addColumn("realCount", ib.DOUBLE, item.getRealCount());
				ib.execute();
			}
		}
	}

	@Publish
	protected class ModifyAskAmountHandle extends SimpleTaskMethodHandler<CheckinAskAmountChangeTask> {

		@Override
		protected void handle(Context context, CheckinAskAmountChangeTask task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Inventory.CheckinSheet.getTableName());
			ub.addExpression("value", ub.DOUBLE, task.getAskAmount(), "askedAmount = t.askedAmount+@value");
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID=@id");
			ub.addCondition("xxx", ub.STRING, "a", "t.amount-t.askedAmount>=@value");
			int count = ub.execute();
			if (0 == count) {
				throw new Exception("申请金额超出入库金额，请核对！");
			}
		}
	}

	@Publish
	protected class ChangeCheckinRealAmountHandle extends SimpleTaskMethodHandler<ChangeCheckinRealAmount> {

		@Override
		protected void handle(Context context, ChangeCheckinRealAmount task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			double amount = task.getAmount();
			if (amount < 0) {
				amount = DoubleUtil.sub(0, amount);
			}
			ub.setTable(ERPTableNames.Inventory.CheckinSheet.getTableName());
			ub.addExpression("value", ub.DOUBLE, amount, "paidAmount = t.paidAmount+@value");
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID=@id");
			ub.addCondition("xxx", ub.STRING, "a", "t.amount-t.paidAmount>=@value");
			int count = ub.execute();
			if (0 == count) {
				throw new Exception("付款金额超出入库金额，请核对！");
			}
			CheckInBaseInfo info = context.find(CheckInBaseInfo.class, task.getId());
			if (info.getAmount() == info.getPaidAmount()) {
				context
						.dispatch(new CheckInSheetAmountBalanceCompleteEvent(info.getRelaBillsId(), info.getSheetType()));
			}
		}
	}

}
