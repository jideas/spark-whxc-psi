package com.spark.psi.order.onlinereturn.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.dnasql.DeleteSqlBuilder;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.order.onlinereturn.task.ChangeStatusOnlineReturnTask;
import com.spark.psi.order.onlinereturn.task.CreateOnlineReturnTask;
import com.spark.psi.order.onlinereturn.task.DeleteOnlineReturnInfoTask;
import com.spark.psi.order.onlinereturn.task.UpdateOnlineReturnTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.onlineorder.task.SetOnlineOrderReturnFlagTask;
import com.spark.psi.publish.onlinereturn.task.OnlineReturnInfoTaskItem;

public class OnlineReturnDao extends Service {

	protected OnlineReturnDao() {
		super("com.spark.psi.order.onlinereturn.service.OnlineReturnDao");
	}

	/**
	 * 保存实体
	 */
	@Publish
	protected class InsertOnlineReturnHandle extends SimpleTaskMethodHandler<CreateOnlineReturnTask> {

		@Override
		protected void handle(Context context, CreateOnlineReturnTask task) throws Throwable {
			Employee user = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Sales.OnlineReturn.getTableName());
			ib.addColumn("RECID", ib.guid, task.getRECID());
			ib.addColumn("billsNo", ib.STRING, task.getBillsNo());
			ib.addColumn("memberId", ib.guid, task.getMemberId());
			ib.addColumn("memberName", ib.STRING, task.getMemberName());
			ib.addColumn("onlineBillsId", ib.guid, task.getOnlineBillsId());
			ib.addColumn("onlineBillsNo", ib.STRING, task.getOnlineBillsNo());
			ib.addColumn("stationId", ib.guid, task.getStationId());
			ib.addColumn("stationName", ib.STRING, task.getStationName());
			ib.addColumn("amount", ib.DOUBLE, task.getAmount());
			ib.addColumn("status", ib.STRING, OnlineReturnStatus.Submitting.getCode());
			ib.addColumn("returnReason", ib.STRING, task.getReturnReason());
			ib.addColumn("creatorId", ib.guid, user.getId());
			ib.addColumn("creator", ib.STRING, user.getName());
			ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
			ib.addColumn("memberPhone", ib.STRING, task.getMemberPhone());
			ib.addColumn("salesAmount", ib.DOUBLE, task.getSalesAmount());
			ib.addColumn("onlineCreateDate", ib.DATE, task.getOnlineCreateDate());
			ib.addColumn("vantages", ib.INT, task.getVantages());
			int i = ib.execute();
			if (i == 1) {
				saveDetail(context, task);
			}
		}
	}

	/**
	 * 更新实体
	 */
	@Publish
	protected class UpdateOnlineReturnHandle extends SimpleTaskMethodHandler<UpdateOnlineReturnTask> {

		@Override
		protected void handle(Context context, UpdateOnlineReturnTask task) throws Throwable {
			UpdateSqlBuilder ib = new UpdateSqlBuilder(context);
			ib.setTable(ERPTableNames.Sales.OnlineReturn.getTableName());
			ib.addColumn("memberId", ib.guid, task.getMemberId());
			ib.addColumn("memberName", ib.STRING, task.getMemberName());
			ib.addColumn("onlineBillsId", ib.guid, task.getOnlineBillsId());
			ib.addColumn("onlineBillsNo", ib.STRING, task.getOnlineBillsNo());
			ib.addColumn("stationId", ib.guid, task.getStationId());
			ib.addColumn("stationName", ib.STRING, task.getStationName());
			ib.addColumn("amount", ib.DOUBLE, task.getAmount());
			ib.addColumn("returnReason", ib.STRING, task.getReturnReason());
			ib.addColumn("memberPhone", ib.STRING, task.getMemberPhone());
			ib.addColumn("salesAmount", ib.DOUBLE, task.getSalesAmount());
			ib.addColumn("onlineCreateDate", ib.DATE, task.getOnlineCreateDate());
			ib.addColumn("vantages", ib.INT, task.getVantages());
			ib.addCondition("id", ib.guid, task.getRECID(), "t.RECID=@id");
			if (1 != ib.execute()) {
				throw new Exception("更新退货单据失败，请重试！");
			}
			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable(ERPTableNames.Sales.OnlineReturn_Det.getTableName());
			db.addEquals("sheetId", db.guid, task.getRECID());
			if (db.execute() > 0) {
				saveDetail(context, task);
			}
		}
	}

	/**
	 * 保存明细
	 * 
	 * @param context
	 * @param task
	 */
	private void saveDetail(Context context, UpdateOnlineReturnTask task) {
		for (OnlineReturnInfoTaskItem item : task.getItems()) {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Sales.OnlineReturn_Det.getTableName());
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("sheetId", ib.guid, task.getRECID());
			ib.addColumn("onlineBillsItemId", ib.guid, item.getOnlineBillsItemId());
			ib.addColumn("goodsId", ib.guid, item.getGoodsId());
			ib.addColumn("goodsCode", ib.STRING, item.getGoodsCode());
			ib.addColumn("goodsNo", ib.STRING, item.getGoodsNo());
			ib.addColumn("goodsSpec", ib.STRING, item.getGoodsSpec());
			ib.addColumn("goodsUnit", ib.STRING, item.getGoodsUnit());
			ib.addColumn("goodsName", ib.STRING, item.getGoodsName());
			ib.addColumn("returnCount", ib.DOUBLE, item.getCount());
			ib.addColumn("price", ib.DOUBLE, item.getPrice());
			ib.addColumn("amount", ib.DOUBLE, item.getAmount());
			ib.addColumn("billsCount", ib.DOUBLE, item.getBillsCount());
			ib.execute();
		}
	}

	/**
	 * 变更状态
	 */
	@Publish
	protected class ChangeStatusOnlineReturnHandle extends SimpleTaskMethodHandler<ChangeStatusOnlineReturnTask> {
		@Override
		protected void handle(Context context, ChangeStatusOnlineReturnTask task) throws Throwable {
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			OnlineReturnStatus status = task.getStatus();
			boolean approved = false;
			if (status == OnlineReturnStatus.Approving && login.hasAuth(Auth.Boss)) {
				status = OnlineReturnStatus.Processing;
				approved = true;
			}
			UpdateSqlBuilder ib = new UpdateSqlBuilder(context);
			ib.setTable(ERPTableNames.Sales.OnlineReturn.getTableName());
			ib.addColumn("status", ib.STRING, status.getCode());
			if (status == OnlineReturnStatus.Rejected) {
				ib.addColumn("approvorId", ib.guid, user.getId());
				ib.addColumn("approvor", ib.STRING, user.getName());
				ib.addColumn("approveDate", ib.DATE, System.currentTimeMillis());
				ib.addColumn("rejectReason", ib.STRING, task.getReason());
				ib.addCondition("oldStatus", ib.STRING, OnlineReturnStatus.Approving.getCode(), "t.status=@oldStatus");
			} else if (status == OnlineReturnStatus.Processing && null == task.getReason()) {
				ib.addColumn("approvorId", ib.guid, user.getId());
				ib.addColumn("approvor", ib.STRING, user.getName());
				ib.addColumn("approveDate", ib.DATE, System.currentTimeMillis());
				ib.addCondition("oldStatus", ib.STRING, OnlineReturnStatus.Finished.getCode(), "t.status<>@oldStatus");
				approved = true;
			} else if (status == OnlineReturnStatus.Stopped) {
				ib.addColumn("stopReason", ib.STRING, task.getReason());
				ib.addCondition("oldStatus", ib.STRING, OnlineReturnStatus.Processing.getCode(), "t.status=@oldStatus");
			} else if (status == OnlineReturnStatus.Finished) {
				ib.addColumn("finishPerson", ib.guid, user.getId());
				ib.addColumn("finishPersonName", ib.STRING, user.getName());
				ib.addColumn("finishedDate", ib.DATE, System.currentTimeMillis());
				ib.addCondition("oldStatus", ib.STRING, OnlineReturnStatus.Processing.getCode(), "t.status=@oldStatus");
			}
			ib.addCondition("id", ib.guid, task.getId(), "t.RECID=@id");
			if (1 != ib.execute()) {
				throw new Exception("提交操作失败，请检查！");
			} else if (approved) {
				SetOnlineOrderReturnFlagTask sTask = new SetOnlineOrderReturnFlagTask(task.getRelaBillsId());
				context.handle(sTask);
			}
		}
	}

	/**
	 * 删除
	 */
	@Publish
	protected class DeleteHandle extends SimpleTaskMethodHandler<DeleteOnlineReturnInfoTask> {
		@Override
		protected void handle(Context context, DeleteOnlineReturnInfoTask task) throws Throwable {
			StringBuilder ss = new StringBuilder("define delete Delete_OnlineReturn( @id guid not null");
			ss.append(",@status1 string not null,@status2 string not null,@userId guid not null)begin ");
			ss.append(" delete from " + ERPTableNames.Sales.OnlineReturn.getTableName() + " as t");
			ss.append(" where t.RECID=@id");
			ss.append(" and (t.status = @status1 or t.status = @status2)");
			ss.append(" and t.creatorId = @userId ");
			ss.append(" end ");
			DBCommand db = context.prepareStatement(ss.toString());
			db.setArgumentValues(task.getId(), OnlineReturnStatus.Submitting.getCode(), OnlineReturnStatus.Rejected
					.getCode(), context.find(Login.class).getEmployeeId());
			int count = db.executeUpdate();
			if (count != 1) {
				throw new Exception("删除失败，请核对退货单数据！");
			}
			DeleteSqlBuilder dsb = new DeleteSqlBuilder(context);
			dsb.setTable(ERPTableNames.Sales.OnlineReturn_Det.getTableName());
			dsb.addEquals("sheetId", dsb.guid, task.getId());
			dsb.execute();
		}
	}
}
