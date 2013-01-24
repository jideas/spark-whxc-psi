package com.spark.psi.account.service.receipt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.account.intf.entity.receipt.ReceiptEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptItemEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptLogEntity;
import com.spark.psi.account.intf.event.ReceiptEffectiveEvent;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.account.service.orm.Orm_Receipt;
import com.spark.psi.account.service.orm.Orm_ReceiptItem;
import com.spark.psi.account.service.orm.Orm_ReceiptItemBySheetId;
import com.spark.psi.account.service.orm.Orm_ReceiptLog;
import com.spark.psi.account.service.orm.Orm_ReceiptLogBySheetId;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.account.entity.ReceiptInfo;
import com.spark.psi.publish.account.entity.ReceiptInfoItem;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.ReceiptListEntity;
import com.spark.psi.publish.account.key.GetReceiptListKey;
import com.spark.psi.publish.account.task.CreateReceiptTask;
import com.spark.psi.publish.account.task.DeleteReceiptTask;
import com.spark.psi.publish.account.task.ReceiptTask;
import com.spark.psi.publish.account.task.SubmitReceiptTask;
import com.spark.psi.publish.account.task.UpdateReceiptTask;
import com.spark.psi.publish.account.task.ReceiptTask.Item;

public class ReceiptPublishService extends Service {

	final Orm_Receipt orm_Receipt;
	final Orm_ReceiptItem orm_ReceiptItem;
	final Orm_ReceiptLog orm_ReceiptLog;
	final Orm_ReceiptItemBySheetId orm_ReceiptItemBySheetId;
	final Orm_ReceiptLogBySheetId orm_ReceiptLogBySheetId;

	final static String receiptTable = ERPTableNames.Account.Receipts.getTableName();
	final static String receiptDetTable = ERPTableNames.Account.Receipts_Det.getTableName();

	protected ReceiptPublishService(Orm_Receipt orm_Receipt, Orm_ReceiptItem orm_ReceiptItem, Orm_ReceiptLog orm_ReceiptLog,
			Orm_ReceiptItemBySheetId orm_ReceiptItemBySheetId, Orm_ReceiptLogBySheetId orm_ReceiptLogBySheetId) {
		super("com.spark.psi.account.service.pub.ReceiptPublishService");
		this.orm_Receipt = orm_Receipt;
		this.orm_ReceiptItem = orm_ReceiptItem;
		this.orm_ReceiptLog = orm_ReceiptLog;
		this.orm_ReceiptItemBySheetId = orm_ReceiptItemBySheetId;
		this.orm_ReceiptLogBySheetId = orm_ReceiptLogBySheetId;
	}

	@Publish
	protected class InsertReceiptHandle extends SimpleTaskMethodHandler<CreateReceiptTask> {
		@Override
		protected void handle(Context context, CreateReceiptTask task) throws Throwable {
			if (null == task.getItems() || task.getItems().length < 1) {
				throw new Throwable("明细不能为空，请检查！");
			}
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			GUID id = context.newRECID();
			ReceiptEntity entity = new ReceiptEntity();
			new BeanUtils().copyProperties(task, entity);
			entity.setId(id);
			entity.setCreateDate(System.currentTimeMillis());
			entity.setCreator(emp.getName());
			entity.setCreatorId(login.getEmployeeId());
			entity.setReceiptMode(task.getReceiptMode());
			entity.setReceiptsNo(context.find(String.class, SheetNumberType.Receipt));
			entity.setReceiptType(task.getReceiptType());
			entity.setStatus(ReceiptStatus.Submitting.getCode());
			if (ReceiptType.RECEIPT_LSSK.getCode().equals(task.getReceiptType())) {
				entity.setStatus(ReceiptStatus.Receipted.getCode());
				entity.setReceiptedAmount(entity.getAmount());
			}
			ORMAccessor<ReceiptEntity> orm = context.newORMAccessor(orm_Receipt);
			orm.insert(entity);
			task.setId(id);
			doSaveItem(context, task);
			orm.unuse();
		}

		private void doSaveItem(Context context, CreateReceiptTask task) throws Exception {
			ORMAccessor<ReceiptItemEntity> orm = context.newORMAccessor(orm_ReceiptItem);
			ReceiptItemEntity[] entities = new ReceiptItemEntity[task.getItems().length];
			for (int i = 0; i < task.getItems().length; i++) {
				CreateReceiptTask.Item item = task.getItems()[i];
				ReceiptItemEntity entity = new ReceiptItemEntity();

				new BeanUtils().copyProperties(item, entity);

				if (ReceiptType.RECEIPT_LSSK.getCode().equals(task.getReceiptType())) {
					entity.setReceiptedAmount(entity.getAmount());
				}
				entity.setReceiptsId(task.getId());
				entity.setId(GUID.randomID());
				entities[i] = entity;
			}
			orm.insert(entities);
			orm.unuse();

		}

	}

	@Publish
	protected class UpdateReceiptHandle extends SimpleTaskMethodHandler<UpdateReceiptTask> {
		@Override
		protected void handle(Context context, UpdateReceiptTask task) throws Throwable {
			if (null == task.getItems() || task.getItems().length < 1) {
				throw new Throwable("明细不能为空，请检查！");
			}

			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Account.Receipts.getTableName());
			ub.addColumn("receiptMode", ub.STRING, task.getReceiptMode());
			ub.addColumn("receiptDate", ub.DATE, task.getReceiptDate());
			ub.addColumn("amount", ub.DOUBLE, task.getAmount());
			ub.addColumn("remark", ub.STRING, task.getRemark());
			ub.addColumn("receiptType", ub.STRING, task.getReceiptType());

			ub.addCondition("id", ub.guid, task.getId(), "t.RECID = @id");
			ub.addCondition("status", ub.STRING, ReceiptStatus.Submitting.getCode(), "t.status=@status");
			int count = ub.execute();
			if (count < 1) {
				throw new Throwable("您操作的数据已发生了改变，请检查！");
			}
			deleteDets(context, task.getId());
			doSaveItem(context, task);
		}

		private void doSaveItem(Context context, UpdateReceiptTask task) throws Exception {
			ORMAccessor<ReceiptItemEntity> orm = context.newORMAccessor(orm_ReceiptItem);
			ReceiptItemEntity[] entities = new ReceiptItemEntity[task.getItems().length];
			for (int i = 0; i < task.getItems().length; i++) {
				UpdateReceiptTask.Item item = task.getItems()[i];
				ReceiptItemEntity entity = new ReceiptItemEntity();
//				BeanUtils.copyProperties(item, entity);
				entity.setCheckoutSheetId(item.getCheckoutSheetId());
				entity.setSheetNo(item.getSheetNo());
				entity.setRelevantBillId(item.getRelevantBillId());
				entity.setRelevantBillNo(item.getRelevantBillNo());
				entity.setCheckoutDate(item.getCheckoutDate());
				entity.setAmount(item.getAmount());

				entity.setReceiptsId(task.getId());
				entity.setId(context.newRECID());
				entities[i] = entity;
			}
			orm.insert(entities);
			orm.unuse();

		}

	}

	@Publish
	protected class SubmitReceiptHandle extends SimpleTaskMethodHandler<SubmitReceiptTask> {
		@Override
		protected void handle(Context context, SubmitReceiptTask task) throws Throwable {
			ReceiptInfo info = context.find(ReceiptInfo.class, task.getId());
			if (info.getStatus() != ReceiptStatus.Submitting) {
				return;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update submitReceipt(@id guid)\n");
			sql.append("begin\n");
			sql.append("update ").append(receiptTable).append(" as t\n");
			sql.append("set status='").append(ReceiptStatus.Receipting.getCode()).append("'\n");
			sql.append("where ").append("t.recid=@id\n");
			sql.append(" and t.status='").append(ReceiptStatus.Submitting.getCode()).append("'\n");
			sql.append("end");
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(task.getId());
			if (db.executeUpdate() < 1) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			for (ReceiptInfoItem item : info.getItems()) {
				context.dispatch(new ReceiptEffectiveEvent(item.getCheckoutSheetId(), info.getReceiptType()));
			}

		}
	}

	@Publish
	protected class DeleteReceipt extends SimpleTaskMethodHandler<DeleteReceiptTask> {

		@Override
		protected void handle(Context context, DeleteReceiptTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("ids不能为空，请检查！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define delete deleteReceopt(@id guid)\n");
			sql.append("begin\n");
			sql.append("delete from ");
			sql.append(receiptTable).append(" as t\n");
			sql.append(" where t.RECID=@id \n");
			sql.append(" and t.status='").append(ReceiptStatus.Submitting.getCode()).append("'\n");
			sql.append("end");

			DBCommand db = context.prepareStatement(sql);
			for (GUID id : task.getIds()) {
				db.setArgumentValues(id);
				if (db.executeUpdate() < 1) {
					throw new Throwable("您操作的数据已发生了改变，请检查！");
				}
				deleteDets(context, id);
			}
			db.unuse();
		}

	}

	/**
	 * 收款记录列表项<br>
	 * 
	 */
	@Publish
	protected class GetReceiptItem extends OneKeyResultProvider<ReceiptListEntity, GetReceiptListKey> {

		@Override
		protected ReceiptListEntity provide(Context context, GetReceiptListKey key) throws Throwable {

			if (null == key.getStatus() || key.getStatus().length < 1) {
				throw new Throwable("status不能为空！");
			}

			List<ReceiptItem> list = ReceiptServiceUtil.getReceiptItemList(context, key);

			return new ReceiptListEntity(list, list.size());
		}
	}

	@Publish
	protected class GetReceiptInfo extends OneKeyResultProvider<ReceiptInfo, GUID> {

		@Override
		protected ReceiptInfo provide(Context context, GUID id) throws Throwable {
			ORMAccessor<ReceiptEntity> orm = context.newORMAccessor(orm_Receipt);
			ORMAccessor<ReceiptItemEntity> ormd = context.newORMAccessor(orm_ReceiptItemBySheetId);
			ORMAccessor<ReceiptLogEntity> orml = context.newORMAccessor(orm_ReceiptLogBySheetId);
			ReceiptEntity entity = orm.findByRECID(id);
			List<ReceiptItemEntity> itemList = ormd.fetch(id);
			List<ReceiptLogEntity> logList = orml.fetch(id);
			return ReceiptServiceUtil.getReceiptInfo(context, entity, itemList, logList);
		}

	}

	@Publish
	protected class DoingReceiptHandle extends SimpleTaskMethodHandler<ReceiptTask> {
		@Override
		protected void handle(Context context, ReceiptTask task) throws Throwable {
			ReceiptInfo info = context.find(ReceiptInfo.class, task.getReceiptsId());
			if (info.getStatus() == ReceiptStatus.Receipted) {
				return;
			}
			Map<GUID, ReceiptInfoItem> map = new HashMap<GUID, ReceiptInfoItem>();
			for (ReceiptInfoItem item : info.getItems()) {
				map.put(item.getCheckoutSheetId(), item);
			}
			double totalAmount = 0;
			for (ReceiptTask.Item t : task.getItems()) {
				if (t.getAmount() == 0) {
					continue;
				}
				ReceiptInfoItem item = map.get(t.getCheckoutSheetId());
				if (DoubleUtil.sub(item.getAmount(), item.getReceiptedAmount()) < t.getAmount()) {
					throw new Exception("您操作的数据已发生了改变，请重试！");
				}
				totalAmount += DoubleUtil.sum(t.getAmount(), t.getMolingAmount());
				UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
				ub.setTable(ERPTableNames.Account.Receipts_Det.getTableName());
				ub.addColumn("receiptedAmount", ub.DOUBLE, DoubleUtil.sum(item.getReceiptedAmount(), t.getAmount()));
				ub.addColumn("molingAmount", ub.DOUBLE, DoubleUtil.sum(item.getMolingAmount(), t.getMolingAmount()));

				ub.addCondition("id", ub.guid, t.getCheckoutSheetId(), "t.checkoutSheetId = @id");
				ub.addCondition("amounted", ub.DOUBLE, item.getReceiptedAmount(), "t.receiptedAmount=@amounted");
				int i = ub.execute();
				if (i == 0) {
					throw new Exception("您操作的数据已发生了改变，请重试！");
				}
				insertLog(context, task, t);
				changeDealing(context, info, item, DoubleUtil.sum(t.getAmount(), t.getMolingAmount()));
			}
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Account.Receipts.getTableName());
			ub.addColumn("receiptedAmount", ub.DOUBLE, info.getReceiptedAmount() + totalAmount);
			if (info.getAmount() == DoubleUtil.sum(info.getReceiptedAmount(), totalAmount)) {
				ub.addColumn("status", ub.STRING, ReceiptStatus.Receipted.getCode());
			}
			ub.addCondition("id", ub.guid, task.getReceiptsId(), "t.RECID = @id");
			ub.addCondition("amounted", ub.DOUBLE, info.getReceiptedAmount(), "t.receiptedAmount=@amounted");
			int count = ub.execute();
			if (count == 0) {
				throw new Exception("您操作的数据已发生了改变，请重试！");
			}

		}
	}

	public void deleteDets(Context context, GUID id) {
		StringBuffer sql = new StringBuffer();
		sql.append("define delete deleteReceiptDet(@id guid)\n");
		sql.append("begin\n");
		sql.append("delete from ");
		sql.append(receiptDetTable).append(" as t\n");
		sql.append(" where t.receiptsId=@id \n");
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		db.setArgumentValues(id);
		db.executeUpdate();
		db.unuse();

	}

	public void insertLog(Context context, ReceiptTask task, Item t) {
		Login login = context.find(Login.class);
		Employee emp = context.find(Employee.class, login.getEmployeeId());
		ORMAccessor<ReceiptLogEntity> orm = context.newORMAccessor(orm_ReceiptLog);

		ReceiptLogEntity entity = new ReceiptLogEntity();
		entity.setAmount(t.getAmount());
		entity.setCheckinDate(t.getCheckinDate());
		entity.setCheckoutSheetId(t.getCheckoutSheetId());
		entity.setId(context.newRECID());
		entity.setMolingAmount(t.getMolingAmount());
		entity.setReceiptDate(System.currentTimeMillis());
		entity.setReceiptNo(task.getReceiptNo());
		entity.setReceiptPersonId(emp.getId());
		entity.setReceiptPersonName(emp.getName());
		entity.setReceiptsId(task.getReceiptsId());
		entity.setRelevantBillId(t.getRelevantBillId());
		entity.setRelevantBillNo(t.getRelevantBillNo());
		entity.setSheetNo(t.getSheetNo());

		orm.insert(entity);
		orm.unuse();
	}

	public void changeDealing(Context context, ReceiptInfo info, ReceiptInfoItem item, double totalAmount) {
		CusdealTask task = null;
		if(ReceiptType.RECEIPT_CGTK.equals(info.getReceiptType()))
		{
			task = new CusdealTask(info.getPartnerId(), DealingsType.PRO_CGTK, 0, totalAmount, item.getCheckoutSheetId(), item.getSheetNo(),
					info.getReceiptType().getCode(), info.getId(), info.getReceiptsNo());
		}
		else if(ReceiptType.RECEIPT_XSHK.equals(info.getReceiptType()))
		{
			task = new CusdealTask(info.getPartnerId(), DealingsType.CUS_XSSK, 0, totalAmount, item.getCheckoutSheetId(), item.getSheetNo(),
					info.getReceiptType().getCode(), info.getId(), info.getReceiptsNo());
		}
		
		context.handle(task);
	}
}
