package com.spark.psi.account.service.pay;

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
import com.spark.psi.account.intf.entity.pay.PaymentEntity;
import com.spark.psi.account.intf.entity.pay.PaymentItemEntity;
import com.spark.psi.account.intf.entity.pay.PaymentLogEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptLogEntity;
import com.spark.psi.account.intf.event.PaymentEffectiveEvent;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.account.service.orm.Orm_PayMent;
import com.spark.psi.account.service.orm.Orm_PaymentItem;
import com.spark.psi.account.service.orm.Orm_PaymentItemBySheetId;
import com.spark.psi.account.service.orm.Orm_PaymentLog;
import com.spark.psi.account.service.orm.Orm_PaymentLogBySheetId;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.entity.PaymentInfo;
import com.spark.psi.publish.account.entity.PaymentInfoItem;
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.psi.publish.account.entity.PaymentListEntity;
import com.spark.psi.publish.account.key.GetPaymentListKey;
import com.spark.psi.publish.account.task.CreatePaymentTask;
import com.spark.psi.publish.account.task.DeletePaymentTask;
import com.spark.psi.publish.account.task.PayTask;
import com.spark.psi.publish.account.task.ReceiptTask;
import com.spark.psi.publish.account.task.UpdatePaymentStatusTask;
import com.spark.psi.publish.account.task.UpdatePaymentTask;
import com.spark.psi.publish.account.task.ReceiptTask.Item;

public class PaymentPublishService extends Service {

	final Orm_PayMent orm_Payment;
	final Orm_PaymentItem orm_PaymentItem;
	final Orm_PaymentLog orm_PaymentLog;
	final Orm_PaymentItemBySheetId orm_PaymentItemBySheetId;
	final Orm_PaymentLogBySheetId orm_PaymentLogBySheetId;

	final static String paymentTable = ERPTableNames.Account.Payment.getTableName();
	final static String paymentDetTable = ERPTableNames.Account.Payment_Det.getTableName();

	protected PaymentPublishService(Orm_PayMent orm_Payment, Orm_PaymentItem orm_PaymentItem, Orm_PaymentLog orm_PaymentLog,
			Orm_PaymentItemBySheetId orm_PaymentItemBySheetId, Orm_PaymentLogBySheetId orm_PaymentLogBySheetId) {
		super("com.spark.psi.account.service.pay.PayPublishService");
		this.orm_Payment = orm_Payment;
		this.orm_PaymentItem = orm_PaymentItem;
		this.orm_PaymentLog = orm_PaymentLog;
		this.orm_PaymentItemBySheetId = orm_PaymentItemBySheetId;
		this.orm_PaymentLogBySheetId = orm_PaymentLogBySheetId;
	}

	@Publish
	protected class InsertPaymentHandle extends SimpleTaskMethodHandler<CreatePaymentTask> {
		@Override
		protected void handle(Context context, CreatePaymentTask task) throws Throwable {
			if (null == task.getItems() || task.getItems().length < 1) {
				throw new Throwable("明细不能为空，请检查！");
			}
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			GUID id = context.newRECID();
			PaymentEntity entity = new PaymentEntity();
			new BeanUtils().copyProperties(task, entity);
			entity.setId(id);
			entity.setCreateDate(System.currentTimeMillis());
			entity.setCreator(emp.getName());
			entity.setCreatorId(login.getEmployeeId());
//			entity.setPaymentMode(task.getPaymentMode());
			entity.setPaymentNo(context.find(String.class, SheetNumberType.Payment));
			entity.setPaymentType(task.getPaymentType());
			entity.setDealingsWay(task.getDealingsWay());
			entity.setStatus(PaymentStatus.Submitting.getCode());
			if(PaymentType.PAY_LCFK.getCode().equals(task.getPaymentType())||PaymentType.PAY_LSTK.getCode().equals(task.getPaymentType())
					||PaymentType.PAY_JOINTVENTRUE.getCode().equals(task.getPaymentType()))
			{
				entity.setStatus(PaymentStatus.Paid.getCode());
				entity.setPaidAmount(entity.getAmount());
			}
			ORMAccessor<PaymentEntity> orm = context.newORMAccessor(orm_Payment);
			orm.insert(entity);
			task.setId(id);
			doSaveItem(context, task);
			if(PaymentType.PAY_JOINTVENTRUE.getCode().equals(task.getPaymentType()))
			{
				PaymentInfo info = context.find(PaymentInfo.class, id);
				for(PaymentInfoItem item:info.getItems())
				{
					changeDealing(context, info, item, DoubleUtil.sum(item.getAmount(),item.getMolingAmount()));
				}
				
			}
			orm.unuse();
		}

		private void doSaveItem(Context context, CreatePaymentTask task) {
			ORMAccessor<PaymentItemEntity> orm = context.newORMAccessor(orm_PaymentItem);
			PaymentItemEntity[] entities = new PaymentItemEntity[task.getItems().length];
			for (int i = 0; i < task.getItems().length; i++) {
				CreatePaymentTask.Item item = task.getItems()[i];
				PaymentItemEntity entity = new PaymentItemEntity();
//				try {
//					new BeanUtils().copyProperties(item, entity);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				if(PaymentType.PAY_LCFK.getCode().equals(task.getPaymentType())||PaymentType.PAY_LSTK.getCode().equals(task.getPaymentType())
						||PaymentType.PAY_JOINTVENTRUE.getCode().equals(task.getPaymentType()))
				{
					entity.setPaidAmount(entity.getAmount());
				}
				entity.setCheckinSheetId(item.getCheckinSheetId());
				entity.setSheetNo(item.getSheetNo());
				entity.setRelevantBillId(item.getRelevantBillId());
				entity.setRelevantBillNo(item.getRelevantBillNo());
				entity.setCheckinDate(item.getCheckinDate());
				entity.setAmount(item.getAmount());
				entity.setAskAmount(item.getAskAmount());
				entity.setPaymentId(task.getId());
				entity.setMolingAmount(item.getMolingAmount());
				entity.setId(GUID.randomID());
				entities[i] = entity;
			}
			orm.insert(entities);
			orm.unuse();

		}

	}
	
	@Publish
	protected class UpdatePaymentHandle extends SimpleTaskMethodHandler<UpdatePaymentTask> {
		@Override
		protected void handle(Context context, UpdatePaymentTask task) throws Throwable {
			if (null == task.getItems() || task.getItems().length < 1) {
				throw new Throwable("明细不能为空，请检查！");
			}

			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Account.Payment.getTableName());
//			ub.addColumn("PaymentMode", ub.STRING, task.getPaymentMode());
			ub.addColumn("payDate", ub.DATE, task.getPayDate());
			ub.addColumn("amount", ub.DOUBLE, task.getAmount());
			ub.addColumn("remark", ub.STRING, task.getRemark());
			ub.addColumn("PaymentType", ub.STRING, task.getPaymentType().getCode());
			ub.addColumn("dealingsWay", ub.STRING, task.getDealingsWay());
			
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID = @id");
			ub.addCondition(" (t.status='"+PaymentStatus.Submitting.getCode()+"' or t.status='"+PaymentStatus.Deny.getCode()+"') ");
			int count = ub.execute();
			if(count<1)
			{
				throw new Throwable("您操作的数据已发生了改变，请检查！");
			}
			deleteDets(context,task.getId());
			doSaveItem(context, task);
		}

		private void doSaveItem(Context context, UpdatePaymentTask task) {
			ORMAccessor<PaymentItemEntity> orm = context.newORMAccessor(orm_PaymentItem);
			PaymentItemEntity[] entities = new PaymentItemEntity[task.getItems().length];
			for (int i = 0; i < task.getItems().length; i++) {
				UpdatePaymentTask.Item item = task.getItems()[i];
				PaymentItemEntity entity = new PaymentItemEntity();
				try {
					new BeanUtils().copyProperties(item, entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
				entity.setPaymentId(task.getId());
				entity.setId(GUID.randomID());
				entities[i] = entity;
			}
			orm.insert(entities);
			orm.unuse();

		}

	}
	
	@Publish
	protected class SubmitPaymentHandle extends TaskMethodHandler<UpdatePaymentStatusTask,UpdatePaymentStatusTask.Method> {
		protected SubmitPaymentHandle() {
			super(UpdatePaymentStatusTask.Method.Submit);
		}

		@Override
		protected void handle(Context context, UpdatePaymentStatusTask task) throws Throwable {
			
			for(GUID id:task.getIds())
			{
				UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
				ub.setTable(ERPTableNames.Account.Payment.getTableName());
				ub.addColumn("status", ub.STRING, PaymentStatus.Submitted.getCode());
				
				ub.addCondition("id", ub.guid, id, "t.RECID = @id");
				ub.addCondition(" (t.status='"+PaymentStatus.Submitting.getCode()+"' or t.status='"+PaymentStatus.Deny.getCode()+"') ");
				int count = ub.execute();
				if(count<1)
				{
					throw new Throwable("您操作的数据已发生了改变，请检查！");
				}
			}
		}

	}
	@Publish
	protected class DenyPaymentHandle extends TaskMethodHandler<UpdatePaymentStatusTask,UpdatePaymentStatusTask.Method> {
		protected DenyPaymentHandle() {
			super(UpdatePaymentStatusTask.Method.Deny);
		}

		@Override
		protected void handle(Context context, UpdatePaymentStatusTask task) throws Throwable {
			if(null==task.getDenyReason()||"".equals(task.getDenyReason()))
			{
				throw new Throwable("退回原因不能为空！");
			}
			for(GUID id:task.getIds())
			{
				UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
				ub.setTable(ERPTableNames.Account.Payment.getTableName());
				ub.addColumn("status", ub.STRING, PaymentStatus.Deny.getCode());
				ub.addColumn("denyReason", ub.STRING, task.getDenyReason());
				ub.addCondition("id", ub.guid, id, "t.RECID = @id");
				ub.addCondition(" t.status='"+PaymentStatus.Submitted.getCode()+"' ");
				int count = ub.execute();
				if(count<1)
				{
					throw new Throwable("您操作的数据已发生了改变，请检查！");
				}
			}
		}

	}
	@Publish
	protected class ApprovePaymentHandle extends TaskMethodHandler<UpdatePaymentStatusTask,UpdatePaymentStatusTask.Method> {
		protected ApprovePaymentHandle() {
			super(UpdatePaymentStatusTask.Method.Approve);
		}

		@Override
		protected void handle(Context context, UpdatePaymentStatusTask task) throws Throwable {
			
			for(GUID id:task.getIds())
			{
				UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
				ub.setTable(ERPTableNames.Account.Payment.getTableName());
				ub.addColumn("status", ub.STRING, PaymentStatus.Paying.getCode());
				
				ub.addCondition("id", ub.guid, id, "t.RECID = @id");
				ub.addCondition(" t.status='"+PaymentStatus.Submitted.getCode()+"' ");
				int count = ub.execute();
				if(count<1)
				{
					throw new Throwable("您操作的数据已发生了改变，请检查！");
				}
				PaymentInfo info = context.find(PaymentInfo.class, id);
				for(PaymentInfoItem item:info.getItems())
				{
					context.dispatch(new PaymentEffectiveEvent(item.getCheckinSheetId(), item.getAskAmount(), info.getPaymentType()));
				}
			}
		}

	}

	@Publish
	protected class DeletePayment extends SimpleTaskMethodHandler<DeletePaymentTask> {

		@Override
		protected void handle(Context context, DeletePaymentTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("ids不能为空，请检查！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define delete deleteReceopt(@id guid)\n");
			sql.append("begin\n");
			sql.append("delete from ");
			sql.append(paymentTable).append(" as t\n");
			sql.append(" where t.RECID=@id \n");
			sql.append(" and (t.status='").append(PaymentStatus.Submitting.getCode()).append("'\n");
			sql.append(" or t.status='").append(PaymentStatus.Deny.getCode()).append("')\n");
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
	protected class GetPaymentItem extends OneKeyResultProvider<PaymentListEntity, GetPaymentListKey> {

		@Override
		protected PaymentListEntity provide(Context context, GetPaymentListKey key) throws Throwable {

			if (null == key.getStatus() || key.getStatus().length < 1) {
				throw new Throwable("status不能为空！");
			}
			return PaymentServiceUtil.getPaymentItemList(context, key);
		}
	}

	@Publish
	protected class GetPaymentInfo extends OneKeyResultProvider<PaymentInfo, GUID> {

		@Override
		protected PaymentInfo provide(Context context, GUID id) throws Throwable {
			ORMAccessor<PaymentEntity> orm = context.newORMAccessor(orm_Payment);
			ORMAccessor<PaymentItemEntity> ormd = context.newORMAccessor(orm_PaymentItemBySheetId);
			ORMAccessor<PaymentLogEntity> orml = context.newORMAccessor(orm_PaymentLogBySheetId);
			PaymentEntity entity = orm.findByRECID(id);
			List<PaymentItemEntity> itemList = ormd.fetch(id);
			List<PaymentLogEntity> logList = orml.fetch(id);
			return PaymentServiceUtil.getPaymentInfo(context, entity, itemList, logList);
		}

	}

	@Publish
	protected class DoingPaymentHandle extends SimpleTaskMethodHandler<PayTask> {
		@Override
		protected void handle(Context context, PayTask task) throws Throwable {
			Login login = context.find(Login.class);
			PaymentInfo info = context.find(PaymentInfo.class, task.getPaymentId());
			if (info.getStatus() == PaymentStatus.Paid) {
				return;
			}
			Map<GUID, PaymentInfoItem> map = new HashMap<GUID, PaymentInfoItem>();
			for (PaymentInfoItem item : info.getItems()) {
				map.put(item.getCheckinSheetId(), item);
			}
			double totalAmount = 0;
			for (PayTask.Item t : task.getItems()) {
				if (t.getAmount() == 0) {
					continue;
				}
				PaymentInfoItem item = map.get(t.getCheckinSheetId());
				
				if (DoubleUtil.sub(item.getAmount(), item.getPaidAmount()) < t.getAmount()) {
					throw new Exception("您操作的数据已发生了改变，请重试！");
				}
				totalAmount += DoubleUtil.sum(t.getAmount(),t.getMolingAmount());
				UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
				ub.setTable(ERPTableNames.Account.Payment_Det.getTableName());
				ub.addColumn("paidAmount", ub.DOUBLE, DoubleUtil.sum(item.getPaidAmount(), t.getAmount()));
				ub.addColumn("molingAmount", ub.DOUBLE, DoubleUtil.sum(item.getMolingAmount(), t.getMolingAmount()));

				ub.addCondition("id", ub.guid, t.getCheckinSheetId(), "t.checkinSheetId = @id");
				ub.addCondition("amounted", ub.DOUBLE, item.getPaidAmount(), "t.paidAmount=@amounted");
				int i = ub.execute();
				if (i == 0) {
					throw new Exception("您操作的数据已发生了改变，请重试！");
				}
				insertLog(context, task, t);
				changeDealing(context, info, item, DoubleUtil.sum(t.getAmount(),t.getMolingAmount()));
			}
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Account.Payment.getTableName());
			ub.addColumn("paidAmount", ub.DOUBLE, info.getPaidAmount() + totalAmount);
			if (info.getAmount()==DoubleUtil.sum(info.getPaidAmount(), totalAmount)) {
				ub.addColumn("status", ub.STRING, PaymentStatus.Paid.getCode());
			}
			ub.addCondition("id", ub.guid, task.getPaymentId(), "t.RECID = @id");
			ub.addCondition("amounted", ub.DOUBLE, info.getPaidAmount(), "t.paidAmount=@amounted");
			int count = ub.execute();
			if (count == 0) {
				throw new Exception("您操作的数据已发生了改变，请重试！");
			}
		}
	}
	
	public void insertLog(Context context, PayTask task, PayTask.Item t) {
		Login login = context.find(Login.class);
		Employee emp = context.find(Employee.class, login.getEmployeeId());
		ORMAccessor<PaymentLogEntity> orm = context.newORMAccessor(orm_PaymentLog);

		PaymentLogEntity entity = new PaymentLogEntity();
		entity.setAmount(t.getAmount());
		entity.setCheckinDate(t.getCheckinDate());
		entity.setCheckinSheetId(t.getCheckinSheetId());
		entity.setSheetNo(t.getSheetNo());
		entity.setId(context.newRECID());
		entity.setMolingAmount(t.getMolingAmount());
		entity.setPayDate(System.currentTimeMillis());
		entity.setPaymentNo(task.getPaymentNo());
		entity.setPayPersonId(emp.getId());
		entity.setPayPersonName(emp.getName());
		entity.setPaymentId(task.getPaymentId());
		entity.setRelevantBillId(t.getRelevantBillId());
		entity.setRelevantBillNo(t.getRelevantBillNo());
		entity.setSheetNo(t.getSheetNo());

		orm.insert(entity);
		orm.unuse();
	}

	public void deleteDets(Context context, GUID id) {
		StringBuffer sql = new StringBuffer();
		sql.append("define delete deletePaymentDet(@id guid)\n");
		sql.append("begin\n");
		sql.append("delete from ");
		sql.append(paymentDetTable).append(" as t\n");
		sql.append(" where t.paymentId=@id \n");
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		db.setArgumentValues(id);
		db.executeUpdate();
		db.unuse();

	}

	public void changeDealing(Context context, PaymentInfo info, PaymentInfoItem item, double totalAmount) {
		CusdealTask task = null;
		if(PaymentType.PAY_CGFK.equals(info.getPaymentType()))
		{
			task = new CusdealTask(info.getPartnerId(), DealingsType.PRO_CGFK, 0, totalAmount,item.getCheckinSheetId(),item.getSheetNo() , info
					.getPaymentType().getCode(),info.getId(), info.getPaymentNo());
		}
		else if(PaymentType.PAY_XSTK.equals(info.getPaymentType()))
		{
			task = new CusdealTask(info.getPartnerId(), DealingsType.CUS_XSTK, 0, totalAmount,item.getCheckinSheetId(),item.getSheetNo() , info
					.getPaymentType().getCode(),info.getId(), info.getPaymentNo());
		}
		if(null==task)
		{
			return;
		}
		context.handle(task);
	}
}
