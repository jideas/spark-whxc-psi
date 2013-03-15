package com.spark.psi.account.service.jointSettlement;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfo;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementItem;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementListEntity;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementLog;
import com.spark.b2c.publish.JointVenture.key.GetJointSettlementListKey;
import com.spark.b2c.publish.JointVenture.task.DeleteJointSettlementTask;
import com.spark.b2c.publish.JointVenture.task.JointSettlementPayTask;
import com.spark.b2c.publish.JointVenture.task.JointSettlementTask;
import com.spark.b2c.publish.JointVenture.task.MakeJointRecordSettlementedTask;
import com.spark.b2c.publish.JointVenture.task.UpdateJointSettlementStatusTask;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.account.intf.entity.jointSettlement.JointSettlementDetEntity;
import com.spark.psi.account.intf.entity.jointSettlement.JointSettlementEntity;
import com.spark.psi.account.service.orm.Orm_JointSettlement;
import com.spark.psi.account.service.orm.Orm_JointSettlementDet;
import com.spark.psi.account.service.orm.Orm_JointSettlementDetBySheetId;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.publish.JointSettlementStatus;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.task.CreatePaymentTask;

/**
 * 
 * 结算Service
 * 
 */
public class JointSettlementPublishService extends Service {

	final Orm_JointSettlement orm_JointSettlement;
	final Orm_JointSettlementDet orm_JointSettlementDet;
	final Orm_JointSettlementDetBySheetId orm_JointSettlementDetBySheetId;

	public JointSettlementPublishService(Orm_JointSettlement orm_JointSettlement, Orm_JointSettlementDet orm_JointSettlementDet,
			Orm_JointSettlementDetBySheetId orm_JointSettlementDetBySheetId) {
		super("com.spark.psi.account.service.jointSettlement.JointSettlementPublishService");
		this.orm_JointSettlement = orm_JointSettlement;
		this.orm_JointSettlementDet = orm_JointSettlementDet;
		this.orm_JointSettlementDetBySheetId = orm_JointSettlementDetBySheetId;
	}

	@Publish
	protected class Create extends TaskMethodHandler<JointSettlementTask, JointSettlementTask.Method> {

		protected Create() {
			super(JointSettlementTask.Method.Create);
		}

		@Override
		protected void handle(Context context, JointSettlementTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getItems())) {
				throw new Throwable("明细不能为空！");
			}
			if (CheckIsNull.isEmpty(task.getRecordIds())) {
				throw new Throwable("交易记录ids不能为空！");
			}
			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			JointSettlementEntity entity = new JointSettlementEntity();
			GUID id = context.newRECID();
			new BeanUtils().copyProperties(task, entity);
			entity.setId(id);
			task.setId(id);
			entity.setSheetNo(context.find(String.class, SheetNumberType.JointSettlement));
			entity.setCreateDate(System.currentTimeMillis());
			entity.setCreator(emp.getName());
			entity.setCreatorId(emp.getId());
			entity.setStatus(JointSettlementStatus.Submitting.getCode());

			orm.insert(entity);

			saveDet(context, task, entity);
			orm.unuse();
		}
	}

	@Publish
	protected class Update extends TaskMethodHandler<JointSettlementTask, JointSettlementTask.Method> {

		protected Update() {
			super(JointSettlementTask.Method.Update);
		}

		@Override
		protected void handle(Context context, JointSettlementTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getItems())) {
				throw new Throwable("明细不能为空！");
			}
			if (CheckIsNull.isEmpty(task.getId())) {
				throw new Throwable("ID不能为空！");
			}
			if (CheckIsNull.isEmpty(task.getAdjustAmount()) || CheckIsNull.isEmpty(task.getAmount()) || CheckIsNull.isEmpty(task.getBeginDate())
					|| CheckIsNull.isEmpty(task.getEndDate()) || CheckIsNull.isEmpty(task.getPercentageAmount())
					|| CheckIsNull.isEmpty(task.getSalesAmount())) {
				throw new Throwable("adjustAmount||amount||beginDate||endDate||percentageAmount||salesAmount不能为空！");
			}
			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			JointSettlementEntity entity = orm.findByRECID(task.getId());
			if (!(entity.getStatus().equals(JointSettlementStatus.Deny.getCode()) || entity.getStatus().equals(
					JointSettlementStatus.Submitting.getCode()))) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			entity.setAdjustAmount(task.getAdjustAmount());
			entity.setAmount(task.getAmount());
			entity.setBeginDate(task.getBeginDate());
			entity.setEndDate(task.getEndDate());
			entity.setPercentageAmount(task.getPercentageAmount());
			entity.setSalesAmount(task.getSalesAmount());
			entity.setRemark(task.getRemark());
			if(CheckIsNull.isNotEmpty(task.getRecordIds()))
			{
				entity.setRecordIds(task.getRecordIds());
			}

			if (orm.update(entity)) {
				deleteDets(context, entity.getId());
				saveDet(context, task, entity);
			}
			orm.unuse();
		}
	}

	@Publish
	protected class Delete extends SimpleTaskMethodHandler<DeleteJointSettlementTask> {

		@Override
		protected void handle(Context context, DeleteJointSettlementTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("ids不能为空！");
			}
			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);

			for (GUID id : task.getIds()) {
				JointSettlementEntity entity = orm.findByRECID(id);
				if (JointSettlementStatus.Deny.getCode().equals(entity.getStatus())
						|| JointSettlementStatus.Submitting.getCode().equals(entity.getStatus())) {
					deleteDets(context, id);
					orm.delete(id);
				} else {
					throw new Throwable("您操作的数据已发生改变，请检查！");
				}
			}
			orm.unuse();

		}
	}

	@Publish
	protected class Submit extends TaskMethodHandler<UpdateJointSettlementStatusTask, UpdateJointSettlementStatusTask.Method> {

		protected Submit() {
			super(UpdateJointSettlementStatusTask.Method.Submit);
		}

		@Override
		protected void handle(Context context, UpdateJointSettlementStatusTask task) throws Throwable {

			if (CheckIsNull.isEmpty(task.getId())) {
				throw new Throwable("ID不能为空！");
			}

			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			JointSettlementEntity entity = orm.findByRECID(task.getId());
			if (!(entity.getStatus().equals(JointSettlementStatus.Deny.getCode()) || entity.getStatus().equals(
					JointSettlementStatus.Submitting.getCode()))) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			entity.setStatus(JointSettlementStatus.Submitted.getCode());

			orm.update(entity);

			orm.unuse();
		}
	}

	@Publish
	protected class Approve extends TaskMethodHandler<UpdateJointSettlementStatusTask, UpdateJointSettlementStatusTask.Method> {

		protected Approve() {
			super(UpdateJointSettlementStatusTask.Method.Approve);
		}

		@Override
		protected void handle(Context context, UpdateJointSettlementStatusTask task) throws Throwable {

			if (CheckIsNull.isEmpty(task.getId())) {
				throw new Throwable("ID不能为空！");
			}

			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			JointSettlementEntity entity = orm.findByRECID(task.getId());
			if (!(entity.getStatus().equals(JointSettlementStatus.Submitted.getCode()))) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			entity.setStatus(JointSettlementStatus.Paying.getCode());

			orm.update(entity);
			setRecordsFlag(context, entity);

			orm.unuse();
		}
	}

	@Publish
	protected class Deny extends TaskMethodHandler<UpdateJointSettlementStatusTask, UpdateJointSettlementStatusTask.Method> {

		protected Deny() {
			super(UpdateJointSettlementStatusTask.Method.Deny);
		}

		@Override
		protected void handle(Context context, UpdateJointSettlementStatusTask task) throws Throwable {

			if (CheckIsNull.isEmpty(task.getId())) {
				throw new Throwable("ID不能为空！");
			}
			if (CheckIsNull.isEmpty(task.getDenyReason())) {
				throw new Throwable("退回原因不能为空！");
			}
			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			JointSettlementEntity entity = orm.findByRECID(task.getId());
			if (!(entity.getStatus().equals(JointSettlementStatus.Submitted.getCode()))) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			entity.setStatus(JointSettlementStatus.Deny.getCode());
			entity.setDenyReason(task.getDenyReason());

			orm.update(entity);

			orm.unuse();
		}
	}

	@Publish
	protected class Pay extends SimpleTaskMethodHandler<JointSettlementPayTask> {

		@Override
		protected void handle(Context context, JointSettlementPayTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getId())) {
				throw new Throwable("id不能为空！");
			}
			if (CheckIsNull.isEmpty(task.getAmount())) {
				throw new Throwable("付款金额不能为空！");
			}
			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			JointSettlementEntity entity = orm.findByRECID(task.getId());
			double amount = checkAmount(entity, task);
			if (amount < 0) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			entity.setPaidAmount(DoubleUtil.sum(entity.getPaidAmount(), task.getAmount()));
			entity.setMolingAmount(DoubleUtil.sum(entity.getMolingAmount(), task.getMolingAmount()));

			if (0 == amount) {
				entity.setStatus(JointSettlementStatus.Paid.getCode());
			}
			orm.update(entity);
			createPayment(context, entity, task);

		}

	}

	@Publish
	protected class GetJointSettlementList extends OneKeyResultProvider<JointSettlementListEntity, GetJointSettlementListKey> {

		@Override
		protected JointSettlementListEntity provide(Context context, GetJointSettlementListKey key) throws Throwable {
			if (null == key.getStatus() || key.getStatus().length < 1) {
				throw new Throwable("状态不能为空！");
			}
			List<JointSettlementItem> dataList = JointSettlementServiceUtil.getJointSettlementList(context, key);

			return new JointSettlementListEntity(dataList, dataList.size());
		}

	}

	@Publish
	protected class GetJointSettlementInfo extends OneKeyResultProvider<JointSettlementInfo, GUID> {

		@Override
		protected JointSettlementInfo provide(Context context, GUID id) throws Throwable {
			ORMAccessor<JointSettlementEntity> orm = context.newORMAccessor(orm_JointSettlement);
			ORMAccessor<JointSettlementDetEntity> ormd = context.newORMAccessor(orm_JointSettlementDetBySheetId);
			List<JointSettlementLog> logList = JointSettlementServiceUtil.getJointSettlementLogList(context, id);
			JointSettlementEntity entity = orm.findByRECID(id);
			List<JointSettlementDetEntity> detList = ormd.fetch(id);
			return JointSettlementServiceUtil.getJointSettlementInfo(context, entity, detList, logList);
		}

	}

	public void saveDet(Context context, JointSettlementTask task, JointSettlementEntity entity) throws Exception {
		ORMAccessor<JointSettlementDetEntity> orm = context.newORMAccessor(orm_JointSettlementDet);
		JointSettlementDetEntity[] entities = new JointSettlementDetEntity[task.getItems().length];
		for (int i = 0; i < task.getItems().length; i++) {
			JointSettlementTask.Item item = task.getItems()[i];
			JointSettlementDetEntity e = new JointSettlementDetEntity();

			new BeanUtils().copyProperties(item, e);

			e.setId(context.newRECID());
			e.setSheetId(entity.getId());
			entities[i] = e;
		}
		orm.insert(entities);
		orm.unuse();
	}

	public void setRecordsFlag(Context context, JointSettlementEntity entity) {

		String recordIdsStr = entity.getRecordIds();
		List<GUID> list = new ArrayList<GUID>();
		for (String id : recordIdsStr.split(",")) {
			list.add(GUID.valueOf(id));
		}
		MakeJointRecordSettlementedTask task = new MakeJointRecordSettlementedTask(list);
		context.handle(task);
	}

	public void createPayment(Context context, JointSettlementEntity entity, JointSettlementPayTask task) {
		CreatePaymentTask pt = new CreatePaymentTask();
		pt.setPaymentType(PaymentType.PAY_JOINTVENTRUE.getCode());
		pt.setAmount(DoubleUtil.sub(task.getAmount(), task.getMolingAmount()));
		pt.setPartnerId(entity.getSupplierId());
		pt.setPartnerName(entity.getSupplierName());
		pt.setPayDate(System.currentTimeMillis());
		pt.setRemark(entity.getRemark());
		CreatePaymentTask.Item[] items = new CreatePaymentTask.Item[1];
		CreatePaymentTask.Item item = pt.new Item(null, null, entity.getId(), entity.getSheetNo(), System.currentTimeMillis(), task.getAmount(), task
				.getAmount(), task.getMolingAmount());
		items[0] = item;
		pt.setItems(items);
		context.handle(pt);

	}

	public double checkAmount(JointSettlementEntity entity, JointSettlementPayTask task) {

		return DoubleUtil.sub(entity.getAmount(), (entity.getPaidAmount() + entity.getMolingAmount() + task.getAmount() + task.getMolingAmount()));
	}

	public void deleteDets(Context context, GUID id) {
		ORMAccessor<JointSettlementDetEntity> orm = context.newORMAccessor(orm_JointSettlementDetBySheetId);
		List<JointSettlementDetEntity> list = orm.fetch(id);
		JointSettlementDetEntity[] entities = list.toArray(new JointSettlementDetEntity[0]);
		orm.delete(entities);
		orm.unuse();
	}

}
