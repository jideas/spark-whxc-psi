package com.spark.psi.inventory.service.outstorage;

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
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.inventory.internal.task.ChangeCheckoutRealAmount;
import com.spark.psi.inventory.intf.event.CheckOutSheetAmountBalanceCompleteEvent;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTask;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTaskItem;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;

public class CheckoutSheetDao extends Service {

	protected CheckoutSheetDao() {
		super("com.spark.psi.inventory.service.outstorage.CheckoutSheetDao");
	}

	/**
	 * 向出库单中插入一条记录
	 */
	@Publish
	protected class CreateCheckOutSheetHandle extends SimpleTaskMethodHandler<CreateCheckOutSheetTask> {

		@Override
		protected void handle(Context context, CreateCheckOutSheetTask task) throws Throwable {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Inventory.CheckoutSheet.getTableName());
			Employee user = context.find(Employee.class, context.find(Login.class).getEmployeeId());

			if (CheckingOutType.Mateiral_Return.getCode().equals(task.getCheckoutType())) {
				task.setAmount(DoubleUtil.sub(0, task.getAmount()));
			}

			ib.addColumn("RECID", ib.guid, task.getRECID());
			ib.addColumn("sheetNo", ib.STRING, task.getSheetNo());
			ib.addColumn("checkoutType", ib.STRING, task.getCheckoutType());
			ib.addColumn("partnerId", ib.guid, task.getPartnerId());
			ib.addColumn("partnerName", ib.STRING, task.getPartnerName());
			ib.addColumn("namePY", ib.STRING, task.getNamePY());
			ib.addColumn("partnerShortName", ib.STRING, task.getPartnerShortName());
			ib.addColumn("relaBillsId", ib.guid, task.getRelaBillsId());
			ib.addColumn("relaBillsNo", ib.STRING, task.getRelaBillsNo());
			ib.addColumn("storeId", ib.guid, task.getStoreId());
			ib.addColumn("storeName", ib.STRING, task.getStoreName());
			ib.addColumn("storeNamePY", ib.STRING, task.getStoreNamePY());
			ib.addColumn("goodsFrom", ib.STRING, task.getGoodsFrom());
			ib.addColumn("goodsUse", ib.STRING, task.getGoodsUse());
			ib.addColumn("takePerson", ib.STRING, task.getTakePerson());
			ib.addColumn("takeUnit", ib.STRING, task.getTakeUnit());
			ib.addColumn("vouchersNo", ib.STRING, task.getVouchersNo());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			ib.addColumn("amount", ib.DOUBLE, task.getAmount());
			ib.addColumn("receiptedAmount", ib.DOUBLE, task.getReceiptedAmount());
			ib.addColumn("receiptedFlag", ib.STRING, task.getReceiptedFlag());
			ib.addColumn("checkoutDate", ib.DATE, System.currentTimeMillis());
			ib.addColumn("checkoutPerson", ib.guid, user.getId());
			ib.addColumn("checkoutPersonName", ib.STRING, user.getName());
			ib.addColumn("deptId", ib.guid, user.getDepartmentId());
			ib.addColumn("isReceipting", ib.BOOLEAN, task.isReceipting());
			ib.addColumn("creatorId", ib.guid, user.getId());
			ib.addColumn("creator", ib.STRING, user.getName());
			if (null != user.getDepartmentId()) {
				Department depart = context.find(Department.class, user.getDepartmentId());
				ib.addColumn("deptName", ib.STRING, depart.getName());
			}
			if (null != task.getPartnerId()) {
				Partner p = context.find(Partner.class, task.getPartnerId());
				ib.addColumn("partnerCode", ib.STRING, p.getCode());
			}
			if (ib.execute() == 1) {
				saveDetails(context, task, user);
			}
			task.setSuccess(true);
			if (task.getCheckoutType().equals(CheckingOutType.Joint.getCode())
					|| task.getCheckoutType().equals(CheckingOutType.RealGoods.getCode())
					|| task.getCheckoutType().equals(CheckingOutType.Kit.getCode())) {
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
		private void writeJointRecord(Context context, CreateCheckOutSheetTask sheet) throws Exception {
			List<JointVentureTaskItem> items = new ArrayList<JointVentureTaskItem>();
			for (CreateCheckOutSheetTaskItem det : sheet.getItems()) {
				JointVentureTaskItem item = new JointVentureTaskItem();
				item.setGoodsId(det.getGoodsId());
				item.setSheetId(sheet.getRECID());
				item.setSheetNo(sheet.getSheetNo());
				item.setCount(det.getRealCount());
				items.add(item);
			}
			context.handle(new CreateJointVentureTask(items));
		}

		private void saveDetails(Context context, CreateCheckOutSheetTask task, Employee user) {
			for (CreateCheckOutSheetTaskItem item : task.getItems()) {
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable(ERPTableNames.Inventory.CheckoutSheet_Det.getTableName());
				if (CheckingOutType.Mateiral_Return.getCode().equals(task.getCheckoutType())) {
					item.setAmount(DoubleUtil.sub(0, item.getAmount()));
					item.setRealCount(DoubleUtil.sub(0, item.getRealCount()));
				}
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
				ib.addColumn("avgCost", ib.DOUBLE, item.getAvgCost());
				ib.addColumn("amount", ib.DOUBLE, item.getAmount());
				ib.addColumn("realCount", ib.DOUBLE, item.getRealCount());
				ib.addColumn("createPerson", ib.STRING, user.getName());
				ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
				ib.execute();
			}
		}
	}

	@Publish
	protected class ChangeCheckoutRealAmountHandle extends SimpleTaskMethodHandler<ChangeCheckoutRealAmount> {

		@Override
		protected void handle(Context context, ChangeCheckoutRealAmount task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			double amount = task.getAmount();
			if (amount < 0) {
				amount = DoubleUtil.sub(0, amount);
			}
			ub.setTable(ERPTableNames.Inventory.CheckoutSheet.getTableName());
			ub.addExpression("value", ub.DOUBLE, amount, "receiptedAmount = t.receiptedAmount+@value");
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID=@id");
			ub.addCondition("xxx", ub.STRING, "a", "t.amount-t.receiptedAmount>=@value");
			int count = ub.execute();
			if (0 == count) {
				throw new Exception("收款金额超出出库金额，请核对！");
			}
			CheckOutBaseInfo info = context.find(CheckOutBaseInfo.class, task.getId());
			if (info.getAmount() == info.getReceiptedAmount()) {
				context.dispatch(new CheckOutSheetAmountBalanceCompleteEvent(info.getRelaBillsId(), CheckingOutType
						.getCheckingOutType(info.getCheckoutType())));
			}
		}
	}

}
