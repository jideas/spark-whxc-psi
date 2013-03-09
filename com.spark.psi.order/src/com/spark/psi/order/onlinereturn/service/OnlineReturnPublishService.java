package com.spark.psi.order.onlinereturn.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.order.intf.publish.entity.OnlineReturnItemImpl;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.order.onlinereturn.entity.OnlineReturnDetImpl;
import com.spark.psi.order.onlinereturn.entity.OnlineReturnInfoImpl;
import com.spark.psi.order.onlinereturn.task.ChangeStatusOnlineReturnTask;
import com.spark.psi.order.onlinereturn.task.CreateOnlineReturnTask;
import com.spark.psi.order.onlinereturn.task.DeleteOnlineReturnInfoTask;
import com.spark.psi.order.onlinereturn.task.UpdateOnlineReturnTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.inventory.sheet.task.RealGoodsCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.RealGoodsCheckinTaskItem;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnDet;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnInfo;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey;
import com.spark.psi.publish.onlinereturn.task.ApproveOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.DeleteOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.ExecuteOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.SaveOrSubmitOnlineReturnInfoTask;
import com.spark.psi.publish.onlinereturn.task.StopOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.SureOnlineReturnFinishTask;

public class OnlineReturnPublishService extends Service {

	protected OnlineReturnPublishService() {
		super("com.spark.psi.order.onlinereturn.service.OnlineReturnPublishService");
	}

	/**
	 * 查询详情
	 */
	@Publish
	protected class LoadOnlineReturnProvider extends OneKeyResultProvider<OnlineReturnInfo, GUID> {
		@Override
		protected OnlineReturnInfo provide(Context context, GUID key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Sales.OnlineReturn.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.billsNo", "billsNo");
			qb.addColumn("t.memberId", "memberId");
			qb.addColumn("t.memberName", "memberName");
			qb.addColumn("t.onlineBillsId", "onlineBillsId");
			qb.addColumn("t.onlineBillsNo", "onlineBillsNo");
			qb.addColumn("t.stationId", "stationId");
			qb.addColumn("t.stationName", "stationName");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.status", "status");
			qb.addColumn("t.returnReason", "returnReason");
			qb.addColumn("t.stopReason", "stopReason");
			qb.addColumn("t.rejectReason", "rejectReason");
			qb.addColumn("t.creatorId", "creatorId");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.approvorId", "approvorId");
			qb.addColumn("t.approvor", "approvor");
			qb.addColumn("t.approveDate", "approveDate");
			qb.addColumn("t.finishPerson", "finishPerson");
			qb.addColumn("t.finishPersonName", "finishPersonName");
			qb.addColumn("t.finishedDate", "finishedDate");
			qb.addColumn("t.memberPhone", "memberPhone");
			qb.addColumn("t.salesAmount", "salesAmount");
			qb.addColumn("t.onlineCreateDate", "onlineCreateDate");
			qb.addColumn("t.vantages", "vantages");

			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");

			RecordSet rs = qb.getRecord();
			OnlineReturnInfoImpl info = new OnlineReturnInfoImpl();
			if (rs.next()) {
				int index = 0;
				info.setRECID(rs.getFields().get(index++).getGUID());
				info.setBillsNo(rs.getFields().get(index++).getString());
				info.setMemberId(rs.getFields().get(index++).getGUID());
				info.setMemberName(rs.getFields().get(index++).getString());
				info.setOnlineBillsId(rs.getFields().get(index++).getGUID());
				info.setOnlineBillsNo(rs.getFields().get(index++).getString());
				info.setStationId(rs.getFields().get(index++).getGUID());
				info.setStationName(rs.getFields().get(index++).getString());
				info.setAmount(rs.getFields().get(index++).getDouble());
				info.setStatus(OnlineReturnStatus.getStatus(rs.getFields().get(index++).getString()));
				info.setReturnReason(rs.getFields().get(index++).getString());
				info.setStopReason(rs.getFields().get(index++).getString());
				info.setRejectReason(rs.getFields().get(index++).getString());
				info.setCreatorId(rs.getFields().get(index++).getGUID());
				info.setCreator(rs.getFields().get(index++).getString());
				info.setCreateDate(rs.getFields().get(index++).getLong());
				info.setApprovorId(rs.getFields().get(index++).getGUID());
				info.setApprovor(rs.getFields().get(index++).getString());
				info.setApproveDate(rs.getFields().get(index++).getLong());
				info.setFinishPerson(rs.getFields().get(index++).getGUID());
				info.setFinishPersonName(rs.getFields().get(index++).getString());
				info.setFinishedDate(rs.getFields().get(index++).getLong());
				info.setMemberPhone(rs.getFields().get(index++).getString());
				info.setSalesAmount(rs.getFields().get(index++).getDouble());
				info.setOnlineCreateDate(rs.getFields().get(index++).getLong());
				info.setVantages(rs.getFields().get(index++).getInt());
			}
			if (null != info.getRECID()) {
				fillDetail(context, info);
			}
			return info;
		}

		private void fillDetail(Context context, OnlineReturnInfoImpl info) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Sales.OnlineReturn_Det.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.sheetId", "sheetId");
			qb.addColumn("t.onlineBillsItemId", "onlineBillsItemId");
			qb.addColumn("t.goodsId", "goodsId");
			qb.addColumn("t.goodsCode", "goodsCode");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.goodsSpec", "goodsSpec");
			qb.addColumn("t.goodsUnit", "goodsUnit");
			qb.addColumn("t.goodsName", "goodsName");
			qb.addColumn("t.returnCount", "returnCount");
			qb.addColumn("t.billsCount", "billsCount");
			qb.addColumn("t.price", "price");
			qb.addColumn("t.amount", "amount");

			qb.addArgs("id", qb.guid, info.getRECID());
			qb.addEquals("t.sheetId", "@id");

			RecordSet rs = qb.getRecord();
			List<OnlineReturnDet> items = new ArrayList<OnlineReturnDet>();
			while (rs.next()) {
				OnlineReturnDetImpl det = new OnlineReturnDetImpl();
				int index = 0;
				det.setRECID(rs.getFields().get(index++).getGUID());
				det.setSheetId(rs.getFields().get(index++).getGUID());
				det.setOnlineBillsItemId(rs.getFields().get(index++).getGUID());
				det.setGoodsId(rs.getFields().get(index++).getGUID());
				det.setGoodsCode(rs.getFields().get(index++).getString());
				det.setGoodsNo(rs.getFields().get(index++).getString());
				det.setGoodsSpec(rs.getFields().get(index++).getString());
				det.setGoodsUnit(rs.getFields().get(index++).getString());
				det.setGoodsName(rs.getFields().get(index++).getString());
				det.setCount(rs.getFields().get(index++).getDouble());
				det.setBillsCount(rs.getFields().get(index++).getDouble());
				det.setPrice(rs.getFields().get(index++).getDouble());
				det.setAmount(rs.getFields().get(index++).getDouble());
				items.add(det);
			}
			info.setItems(items);
		}
	}

	/**
	 * 保存或提交
	 */
	@Publish
	protected class SaveOrSubmitHandle extends SimpleTaskMethodHandler<SaveOrSubmitOnlineReturnInfoTask> {
		@Override
		protected void handle(Context context, SaveOrSubmitOnlineReturnInfoTask task) throws Throwable {
			if (task.getRECID() == null) {
				CreateOnlineReturnTask create = new CreateOnlineReturnTask();
				fillTask(task, create);
				create.setRECID(context.newRECID());
				String billsNo = context.get(String.class, SheetNumberType.OnlineReturn);
				create.setBillsNo(billsNo);
				context.handle(create);
				task.setRECID(create.getRECID());
			} else {
				UpdateOnlineReturnTask update = new UpdateOnlineReturnTask();
				fillTask(task, update);
				context.handle(update);
			}
			if (task.isSubmit()) {
				ChangeStatusOnlineReturnTask csort = new ChangeStatusOnlineReturnTask(task.getRECID(), OnlineReturnStatus.Approving);
				csort.setRelaBillsId(task.getOnlineBillsId());
				context.handle(csort);
			}
		}

		private void fillTask(SaveOrSubmitOnlineReturnInfoTask task, UpdateOnlineReturnTask update) {
			update.setAmount(task.getAmount());
			update.setItems(task.getItems());
			update.setMemberId(task.getMemberId());
			update.setMemberName(task.getMemberName());
			update.setOnlineBillsId(task.getOnlineBillsId());
			update.setOnlineBillsNo(task.getOnlineBillsNo());
			update.setRECID(task.getRECID());
			update.setReturnReason(task.getReturnReason());
			update.setStationId(task.getStationId());
			update.setStationName(task.getStationName());
			update.setMemberPhone(task.getMemberPhone());
			update.setOnlineCreateDate(task.getOnlineCreateDate());
			update.setSalesAmount(task.getSalesAmount());
			update.setVantages(task.getVantages());
		}
	}

	/**
	 * 查询列表
	 */
	@Publish
	protected class OnlineReturnListProvider extends OneKeyResultProvider<ListEntity<OnlineReturnItem>, GetOnlineReturnListKey> {
		@Override
		protected ListEntity<OnlineReturnItem> provide(Context context, GetOnlineReturnListKey key) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Sales.OnlineReturn.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.billsNo", "billsNo");
			qb.addColumn("t.onlineBillsNo", "onlineBillsNo");
			qb.addColumn("t.memberName", "memberName");
			qb.addColumn("t.finishPersonName", "finishPersonName");
			qb.addColumn("t.amount", "amount");
			qb.addColumn("t.createDate", "createDate");
			qb.addColumn("t.finishedDate", "finishedDate");
			qb.addColumn("t.stationName", "stationName");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.status", "status");
			switch (key.getTab()) {
			case Submiting:
				qb.addArgs("userId", qb.guid, login.getEmployeeId());
				qb.addEquals("t.creatorId", "@userId");
				qb.addArgs("status1", qb.STRING, OnlineReturnStatus.Submitting.getCode());
				qb.addArgs("status2", qb.STRING, OnlineReturnStatus.Rejected.getCode());
				qb.addCondition("t.status in (@status1,@status2)");
				break;
			case Approving:
				if (!login.hasAuth(Auth.Boss)&&!login.hasAuth(Auth.CustomerService)&&!login.hasAuth(Auth.AccountManager)) {
					List<Employee> list = context.getList(Employee.class,
							new GetChildrenDeptEmployeeListByAuthKey(user.getDepartmentId(), Auth.Sales));
					List<String> params = new ArrayList<String>();
					int index = 0;
					for (Employee emp : list) {
						qb.addArgs("creator" + index, qb.guid, emp.getId());
						params.add("@creator" + index++);
					}
					qb.addArgs("creator" + index, qb.guid, user.getId());
					params.add("@creator" + index++);
					qb.addIn("t.creatorId", params);
				}
				qb.addArgs("status1", qb.STRING, OnlineReturnStatus.Approving.getCode());
				qb.addEquals("t.status", "@status1");
				break;
			case Processing:
				if (!login.hasAuth(Auth.Boss)&&!login.hasAuth(Auth.CustomerService)&&!login.hasAuth(Auth.ProduceManager)&&!login.hasAuth(Auth.AccountManager)) {
					List<Employee> list = context.getList(Employee.class,
							new GetChildrenDeptEmployeeListByAuthKey(user.getDepartmentId(), Auth.Sales));
					List<String> params = new ArrayList<String>();
					int index = 0;
					for (Employee emp : list) {
						qb.addArgs("creator" + index, qb.guid, emp.getId());
						params.add("@creator" + index++);
					}
					qb.addArgs("creator" + index, qb.guid, user.getId());
					params.add("@creator" + index++);
					qb.addIn("t.creatorId", params);
				}
				qb.addArgs("userId", qb.guid, user.getId());
				qb.addArgs("status1", qb.STRING, OnlineReturnStatus.Processing.getCode());
				// qb.addArgs("status2", qb.STRING,
				// OnlineReturnStatus.Approving.getCode());
				qb.addCondition("(t.status = @status1)");
				break;
			case Finished:
				if (!login.hasAuth(Auth.Boss)&&!login.hasAuth(Auth.CustomerService)&&!login.hasAuth(Auth.ProduceManager)&&!login.hasAuth(Auth.AccountManager)) {
					List<Employee> list = context.getList(Employee.class,
							new GetChildrenDeptEmployeeListByAuthKey(user.getDepartmentId(), Auth.Sales));
					List<String> params = new ArrayList<String>();
					int index = 0;
					for (Employee emp : list) {
						qb.addArgs("creator" + index, qb.guid, emp.getId());
						params.add("@creator" + index++);
					}
					qb.addArgs("creator" + index, qb.guid, user.getId());
					params.add("@creator" + index++);
					qb.addIn("t.creatorId", params);
				}
				qb.addArgs("status1", qb.STRING, OnlineReturnStatus.Finished.getCode());
				qb.addArgs("status2", qb.STRING, OnlineReturnStatus.Stopped.getCode());
				qb.addCondition("t.status in (@status1,@status2)");
				break;
			default:
				break;
			}

			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				if (CheckIsNull.isNotEmpty(key.getSearchText())) {
					qb.addArgs("text", qb.STRING, key.getSearchText());
					StringBuilder ss = new StringBuilder("( ");
					ss.append(" t.onlineBillsNo like '%'+@text+'%' ");
					ss.append(" or t.billsNo like '%'+@text+'%' ");
					ss.append(" or t.memberName like '%'+@text+'%' ");
					ss.append(" or t.creator like '%'+@text+'%' ");
					ss.append(")");
					qb.addCondition(ss.toString());
				}
			}

			qb.addOrderBy("t.createDate desc");

			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<OnlineReturnItem> items = new ArrayList<OnlineReturnItem>();
			while (rs.next()) {
				int index = 0;
				OnlineReturnItemImpl item = new OnlineReturnItemImpl();
				item.setId(rs.getFields().get(index++).getGUID());
				item.setBillsNo(rs.getFields().get(index++).getString());
				item.setOnlineBillsNo(rs.getFields().get(index++).getString());
				item.setCustomer(rs.getFields().get(index++).getString());
				item.setFinishPerson(rs.getFields().get(index++).getString());
				item.setAmount(rs.getFields().get(index++).getDouble());
				item.setCreateDate(rs.getFields().get(index++).getDate());
				item.setFinishedDate(rs.getFields().get(index++).getDate());
				item.setStationName(rs.getFields().get(index++).getString());
				item.setCreator(rs.getFields().get(index++).getString());
				item.setStatus(rs.getFields().get(index++).getString());
				items.add(item);
			}
			return new ListEntity<OnlineReturnItem>(items, items.size());
		}
	}

	/**
	 * 审批
	 */
	@Publish
	protected class ApproveHandle extends SimpleTaskMethodHandler<ApproveOnlineReturnTask> {
		@Override
		protected void handle(Context context, ApproveOnlineReturnTask task) throws Throwable {
			OnlineReturnStatus status = OnlineReturnStatus.Rejected;
			if (task.isApproval()) {
				status = OnlineReturnStatus.Processing;
			}
			ChangeStatusOnlineReturnTask csort = new ChangeStatusOnlineReturnTask(task.getId(), status, task.getReason());
			csort.setRelaBillsId(task.getOnlineOrderId());
			context.handle(csort);
		}
	}

	/**
	 * 确定完成
	 */
	@Publish
	protected class FinishedHandle extends SimpleTaskMethodHandler<SureOnlineReturnFinishTask> {
		@Override
		protected void handle(Context context, SureOnlineReturnFinishTask task) throws Throwable {
			context.handle(new ChangeStatusOnlineReturnTask(task.getId(), OnlineReturnStatus.Finished));
			OnlineReturnInfo info = context.find(OnlineReturnInfo.class, task.getId());
			// 入库
			createCheckInSheet(context, info);
			if (info.getVantages() > 0) {
				deductVantages(context, info);
			}
			if(info.getAmount()>0)
			{
				returnAmount(context,info);
			}
		}
	}

	/**
	 * 中止
	 */
	@Publish
	protected class StopHandle extends SimpleTaskMethodHandler<StopOnlineReturnTask> {
		@Override
		protected void handle(Context context, StopOnlineReturnTask task) throws Throwable {
			context.handle(new ChangeStatusOnlineReturnTask(task.getId(), OnlineReturnStatus.Stopped, task.getReason()));
		}
	}

	/**
	 * 重新执行
	 */
	@Publish
	protected class ExecuteHandle extends SimpleTaskMethodHandler<ExecuteOnlineReturnTask> {
		@Override
		protected void handle(Context context, ExecuteOnlineReturnTask task) throws Throwable {
			context.handle(new ChangeStatusOnlineReturnTask(task.getId(), OnlineReturnStatus.Processing, false + ""));
		}
	}

	/**
	 * 删除
	 */
	@Publish
	protected class DeleteHandle extends SimpleTaskMethodHandler<DeleteOnlineReturnTask> {
		@Override
		protected void handle(Context context, DeleteOnlineReturnTask task) throws Throwable {
			context.handle(new DeleteOnlineReturnInfoTask(task.getId()));
		}
	}

	public void createCheckInSheet(Context context, OnlineReturnInfo info) {

		RealGoodsCheckinTask t = new RealGoodsCheckinTask();
		t.setRelaBillsId(info.getRECID());
		t.setRelaBillsNo(info.getBillsNo());
		t.setStoreId(Store.GoodsStoreId);
		t.setStoreName(Store.GoodsStoreName);

		List<RealGoodsCheckinTaskItem> items = new ArrayList<RealGoodsCheckinTaskItem>();
		for (OnlineReturnDet item : info.getItems()) {
			RealGoodsCheckinTaskItem ri = new RealGoodsCheckinTaskItem();
			ri.setCount(item.getCount());
			ri.setGoodsId(item.getGoodsId());
			GoodsItem gi = context.find(GoodsItem.class, item.getGoodsId());
			ri.setGoodsName(gi.getGoodsName());
			ri.setGoodsCode(gi.getGoodsCode());
			ri.setGoodsNo(gi.getGoodsNo());
			ri.setGoodsScale(gi.getScale());
			ri.setGoodsSpec(gi.getSpec());
			ri.setGoodsUnit(gi.getGoodsUnit());
			ri.setPrice(gi.getStandardCost());
			ri.setAmount(DoubleUtil.mul(item.getCount() , gi.getStandardCost()));
			items.add(ri);
		}
		t.setItems(items);

		context.handle(t);
	}

	public void returnAmount(Context context, OnlineReturnInfo info) {
		StringBuffer is = new StringBuffer();
		is.append("define insert insertDEALING(@recid guid,@memberID guid,");
		is.append("@dType string,@relaBillsId guid,@relaBillsNo string,@amount double,@occurDate date)\n");
		
		is.append(" begin \n");
		is.append(" insert into CMS_MEMBER_DEALING(recid,memberID,dealType,relaBillsId,relaBillsNo,amount,occurDate)\n");
		is.append(" values(@recid,@memberID,@dType,@relaBillsId,@relaBillsNo,@amount,@occurDate)\n");
		is.append("end");
		DBCommand db = context.prepareStatement(is.toString());
		db.setArgumentValues(GUID.randomID(),info.getMemberId(),"04",info.getRECID(),info.getBillsNo(),info.getAmount(),System.currentTimeMillis());
		db.executeUpdate();
		
		StringBuffer up = new StringBuffer();
		up.append("define update updateAccount(@memberId guid,@amount double)\n");
		up.append("begin\n");
		up.append("update CMS_MEMBER_ACCOUNT as t \n");
		up.append(" set moneyBalance=(t.moneyBalance+@amount)\n");
		up.append("where t.recid=@memberId\n");
		up.append("end");
		DBCommand db1 = context.prepareStatement(up);
		db1.setArgumentValues(info.getMemberId(),info.getAmount());
		db1.executeUpdate();
	}

	public void deductVantages(Context context, OnlineReturnInfo info) {
		StringBuffer is = new StringBuffer();
		is.append("define insert insertVantages(@recid guid,@memberID guid,");
		is.append("@vType string,@relaBillsId guid,@relaBillsNo string,@vantages int,@occurDate date)\n");
		
		is.append(" begin \n");
		is.append(" insert into CMS_MEMBER_VANTAGES(RECID,memberID,vType,relaBillsId,relaBillsNo,vantages,occurDate)\n");
		is.append(" values(@recid,@memberID,@vType,@relaBillsId,@relaBillsNo,@vantages,@occurDate)\n");
		is.append("end");
		DBCommand db = context.prepareStatement(is.toString());
		db.setArgumentValues(GUID.randomID(),info.getMemberId(),"04",info.getRECID(),info.getBillsNo(),0-info.getVantages(),System.currentTimeMillis());
		db.executeUpdate();
		
		StringBuffer up = new StringBuffer();
		up.append("define update updateAccount(@memberId guid,@vantages int)\n");
		up.append("begin\n");
		up.append("update CMS_MEMBER_ACCOUNT as t \n");
		up.append(" set vantages=(t.vantages+@vantages)\n");
		up.append("where t.recid=@memberId\n");
		up.append("end");
		DBCommand db1 = context.prepareStatement(up);
		db1.setArgumentValues(info.getMemberId(),0-info.getVantages());
		db1.executeUpdate();
	}
}
