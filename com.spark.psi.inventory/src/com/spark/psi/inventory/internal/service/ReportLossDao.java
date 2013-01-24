package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.Login;
import com.spark.psi.inventory.internal.entity.ReportLoss;
import com.spark.psi.inventory.internal.entity.ReportLossItem;
import com.spark.psi.inventory.internal.entity.ReportLossItemDet;
import com.spark.psi.inventory.internal.key.GetReportLossListKey;
import com.spark.psi.inventory.internal.key.GetReportLossListKey.ViewStatus;
import com.spark.psi.inventory.internal.task.ChangeReportLossStatusTask;
import com.spark.psi.inventory.internal.task.CreateReportLossItemDetTask;
import com.spark.psi.inventory.internal.task.DeleteReportLossItemDetTask;
import com.spark.psi.inventory.internal.task.DeleteReportLossItemTask;
import com.spark.psi.inventory.internal.task.DeleteReportLossTask;
import com.spark.psi.inventory.internal.task.ReportLossItemTask;
import com.spark.psi.inventory.internal.task.ReportLossTask;
import com.spark.psi.inventory.service.orm.Orm_ReportLoss;
import com.spark.psi.inventory.service.orm.Orm_ReportLossItem;
import com.spark.psi.inventory.service.orm.Orm_ReportLossItemDet;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;

public class ReportLossDao extends Service {

	private Orm_ReportLoss orm_reportLoss;
	private Orm_ReportLossItem orm_reportLossItem;
	private Orm_ReportLossItemDet orm_reportLossItemDet;
	
	protected ReportLossDao(Orm_ReportLoss orm_reportLoss, Orm_ReportLossItem orm_reportLossItem,  Orm_ReportLossItemDet orm_reportLossItemDet) {
		super("ReportLossDao");
		this.orm_reportLoss = orm_reportLoss;
		this.orm_reportLossItem = orm_reportLossItem;
		this.orm_reportLossItemDet = orm_reportLossItemDet;
	}
	
	/**
	 * 创建报损单
	 * @author Administrator
	 *
	 */
	@Publish
	protected class CrateReportLossHandler extends TaskMethodHandler<ReportLossTask, ReportLossTask.Method> {

		protected CrateReportLossHandler() {
			super(ReportLossTask.Method.Create);
		}

		@Override
		protected void handle(Context context, ReportLossTask task)
				throws Throwable {
			ORMAccessor<ReportLoss> reportLossAccessor = context.newORMAccessor(orm_reportLoss);
			ReportLoss entity = new ReportLoss();
			entity.setId(task.getId());
			entity.setStoreId(task.getStoreId());
			entity.setRemark(task.getRemark());
			entity.setCreatorId(task.getCreatorId());
			entity.setCreator(task.getCreator());
			entity.setCreateDate(task.getCreateDate());
			entity.setStatus(task.getStatus());
			entity.setStoreName(task.getStoreName());
			entity.setSheetNo(task.getSheetNo());
			try {
				reportLossAccessor.insert(entity);
			} finally {
				reportLossAccessor.unuse();
			}
		}
	}
	/**
	 * 修改报损单
	 * @author Administrator
	 *
	 */
	@Publish
	protected class ModifyReportLossHandler extends TaskMethodHandler<ReportLossTask, ReportLossTask.Method> {

		protected ModifyReportLossHandler() {
			super(ReportLossTask.Method.Modify);
		}

		@Override
		protected void handle(Context context, ReportLossTask task)
				throws Throwable {
			ORMAccessor<ReportLoss> reportLossAccessor = context.newORMAccessor(orm_reportLoss);
			ReportLoss entity = new ReportLoss();
			entity.setId(task.getId());
			entity.setStoreId(task.getStoreId());
			entity.setRemark(task.getRemark());
			entity.setCreatorId(task.getCreatorId());
			entity.setCreator(task.getCreator());
			entity.setCreateDate(task.getCreateDate());
			entity.setStatus(task.getStatus());
			entity.setStoreName(task.getStoreName());
			entity.setSheetNo(task.getSheetNo());
			try {
				reportLossAccessor.update(entity);
			} finally {
				reportLossAccessor.unuse();
			}
			
		}
		
	}
	
	/**
	 * 删除报损单
	 * @author Administrator
	 *
	 */
	@Publish
	protected class DeleteReportLossHandler extends SimpleTaskMethodHandler<DeleteReportLossTask> {

		@Override
		protected void handle(Context context, DeleteReportLossTask task)
				throws Throwable {
			ORMAccessor<ReportLoss> reportLossAccessor = context.newORMAccessor(orm_reportLoss);
			try {
				reportLossAccessor.delete(task.getId());
			} finally {
				reportLossAccessor.unuse();
			}
		}
		
	}
	
	/**
	 * 修改报损单状态
	 * @author Administrator
	 *
	 */
	@Publish
	protected class ChangeReportLossStatusHandler extends SimpleTaskMethodHandler<ChangeReportLossStatusTask> {

		@Override
		protected void handle(Context context, ChangeReportLossStatusTask task)
				throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("define update updateAllocateInfo(@id guid, @applyDate date, @approvalDate date, " +
					"@approvalPersonId guid, @approvalPersonName string, @rejectReason string) \n");

			sb.append("begin \n");
			sb.append("update PSI_Inventory_ReportLoss as t \n");
			sb.append("set status='" + task.getNextStatus() + "' ");
			if (task.getApplyDate() > 0) {
				sb.append(", applyDate=@applyDate ");
			}
			if (task.getApprovalDate() > 0) {
				sb.append(", approvalDate=@approvalDate ");
			}
			if (task.getApprovalPersonId() != null) {
				sb.append(", approvalPersonId=@approvalPersonId ");
			}
			if (task.getApprovalPersonName() != null) {
				sb.append(", approvalPersonName=@approvalPersonName ");
			}
			if (!StringUtils.isEmpty(task.getRejectReason())) {
				sb.append(", rejectReason=@rejectReason ");
			}
			sb.append("where t.RECID=@id and t.status='" + task.getStatus() + "' \n");
			sb.append("end");
			DBCommand db = context.prepareStatement(sb);
			db.setArgumentValue(0, task.getId());
			db.setArgumentValue(1, task.getApplyDate());
			db.setArgumentValue(2, task.getApprovalDate());
			db.setArgumentValue(3, task.getApprovalPersonId());
			db.setArgumentValue(4, task.getApprovalPersonName());
			db.setArgumentValue(5, task.getRejectReason());
			try {
				if (db.executeUpdate() > 0) {
					task.setSuccess(true);
				}
			} finally {
				
			}
		}
		
	}
	/**
	 * 根据ID取得ReportLoss信息
	 * @author Administrator
	 *
	 */
	@Publish
	protected class GetRepotLossById extends OneKeyResultProvider<ReportLoss, GUID> {

		@Override
		protected ReportLoss provide(Context context, GUID key)
				throws Throwable {
			ReportLoss reportLoss = null;
			ORMAccessor<ReportLoss> reportLossAccessor = context.newORMAccessor(orm_reportLoss);
			try {
				reportLoss = reportLossAccessor.findByRECID(key);
			} finally {
				reportLossAccessor.unuse();
			}
			return reportLoss;
		}
		
	}
	
	/**
	 * 查询报损单列表
	 * @author Administrator
	 *
	 */
	@Publish
	protected class QueryReportLossList extends OneKeyResultListProvider<ReportLoss, GetReportLossListKey> {

		@SuppressWarnings("unchecked")
		@Override
		protected void provide(Context context, GetReportLossListKey key,
				List<ReportLoss> resultList) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			
			StringBuffer parameterStr = new StringBuffer();
			List<Object> parameterList = new ArrayList<Object>();
			StringBuffer conditionStr = new StringBuffer();
			
			conditionStr.append("1=1 ");
			if (StringHelper.isNotEmpty(key.getSearchKey())) {
				parameterStr.append("@searchKey string,");
				parameterList.add(key.getSearchKey());
				conditionStr.append(" and t.storeName like '%" + key.getSearchKey() + "%'");
				conditionStr.append(" or t.creator like '%" + key.getSearchKey() + "%'");
				conditionStr.append(" or t.sheetNo like '%" + key.getSearchKey() + "%'");
			}
			
			if (key.getBeginTime() > 0 && key.getEndTime()> 0) {
				parameterStr.append("@beginTime date, @endTime date, ");
				parameterList.add(key.getBeginTime());
				parameterList.add(key.getEndTime());
				conditionStr.append(" and t.approvalDate >= @beginTime and t.approvalDate <= @endTime");
			}
			Login login = context.find(Login.class);
			if (ViewStatus.submitting == key.getViewStatus()) {
				// status creator
				parameterStr.append("@creatorId guid, ");
				parameterList.add(login.getEmployeeId());
				conditionStr.append(" and t.creatorId = @creatorId ");
				
				conditionStr.append(" and (");
				conditionStr.append("t.status='" + ReportLossStatus.Submitting.getCode() + "' or t.status='" + ReportLossStatus.Deied.getCode() + "'");
				conditionStr.append(")");
			} else if (ViewStatus.approvling == key.getViewStatus()) {
				if (login.hasAuth(Auth.AccountManager) 
						&& login.hasAuth(Auth.StoreKeeperManager)) {
					conditionStr.append(" and (");
					conditionStr.append("t.status='" + ReportLossStatus.Approvling.getCode() + "' or t.status='" + ReportLossStatus.AccountApprovling.getCode() + "'");
					conditionStr.append(")");
				} else if (login.hasAuth(Auth.AccountManager)) {
					// status: AccountApprovling
					conditionStr.append(" and (");
					conditionStr.append("t.status='" + ReportLossStatus.AccountApprovling.getCode() + "'");
					conditionStr.append(")");
				} else if (login.hasAuth(Auth.StoreKeeperManager)) {
					// status: Approvling, storeId: storeIds
					conditionStr.append(" and (");
					conditionStr.append("t.status='" + ReportLossStatus.Approvling.getCode() + "' or t.status='" + ReportLossStatus.AccountApprovling.getCode() + "'");
					conditionStr.append(")");
					
					GetStoreListKey sKey = new GetStoreListKey(StoreStatus.ALL);
					ListEntity<StoreItem> listEntity = context.find(ListEntity.class, sKey);
					if (null != listEntity) {
						int index = 0;
						String storeCondition = " and (1=2";
						for (StoreItem item : listEntity.getItemList()) {
							parameterStr.append("@storeId" + index + " guid, ");
							parameterList.add(item.getId());
							storeCondition += " or t.storeId=@storeId" + index;
							index++;
						}
						storeCondition += ")";
						conditionStr.append(storeCondition);
					}
				} else {
					parameterStr.append("@creatorId guid, ");
					parameterList.add(login.getEmployeeId());
					conditionStr.append(" and t.creatorId = @creatorId ");
					
					conditionStr.append(" and (");
					conditionStr.append("t.status='" + ReportLossStatus.Approvling.getCode() + "' or t.status='" + ReportLossStatus.AccountApprovling.getCode() + "'");
					conditionStr.append(")");
				}
			} else if (ViewStatus.finished == key.getViewStatus()) {
				conditionStr.append(" and (");
				conditionStr.append("t.status='" + ReportLossStatus.Finished.getCode() + "'");
				conditionStr.append(")");
				
				if (login.hasAuth(Auth.StoreKeeperManager)) {
					// status: Approvling, storeId: storeIds
					conditionStr.append(" and (");
					conditionStr.append("t.status='" + ReportLossStatus.Finished.getCode() + "'");
					conditionStr.append(")");
					
					GetStoreListKey sKey = new GetStoreListKey(StoreStatus.ALL);
					ListEntity<StoreItem> listEntity = context.find(ListEntity.class, sKey);
					if (null != listEntity) {
						int index = 0;
						String storeCondition = " and (1=2";
						for (StoreItem item : listEntity.getItemList()) {
							parameterStr.append("@storeId" + index + " guid, ");
							parameterList.add(item.getId());
							storeCondition += " or t.storeId=@storeId" + index;
							index++;
						}
						storeCondition += ")";
						conditionStr.append(storeCondition);
					}
				} else if (!login.hasAuth(Auth.StoreKeeperManager)
						&& !login.hasAuth(Auth.AccountManager)){
					parameterStr.append("@creatorId guid, ");
					parameterList.add(login.getEmployeeId());
					conditionStr.append(" and t.creatorId = @creatorId ");
				}
			}
			parameterStr.append("@temp string");
			
			dnaSql
					.append("define query queryReportLossList(" + parameterStr.toString() + ")\n");
			dnaSql.append("begin\n");
			dnaSql.append("select \n");
			dnaSql.append(getReportLossColumnsForSQL());
			dnaSql.append("  from PSI_Inventory_ReportLoss as t\n");
			dnaSql.append("where \n");
			dnaSql.append(conditionStr.toString());
			dnaSql.append(" order by " + key.getSortColumn()+ " " + key.getSortType() + " \n");
			dnaSql.append(" end");
			
			DBCommand db = context.prepareStatement(dnaSql);
			for (int paramterIndex = 0; paramterIndex < parameterList.size(); paramterIndex++) {
				db.setArgumentValue(paramterIndex, parameterList.get(paramterIndex));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					resultList.add(getReportLossEntityByRs(rs));
				}
			} finally {
				db.unuse();
			}
		}
		
	}
	
	
	private String getReportLossColumnsForSQL() {
		StringBuffer sb = new StringBuffer();
		sb.append("t.\"applyDate\" as \"applyDate\",");
		sb.append("t.\"approvalDate\" as \"approvalDate\",");
		sb.append("t.\"approvalPersonId\" as \"approvalPersonId\",");
		sb.append("t.\"approvalPersonName\" as \"approvalPersonName\",");
		sb.append("t.\"createDate\" as \"createDate\",");
		sb.append("t.\"creator\" as \"creator\",");
		sb.append("t.\"creatorId\" as \"creatorId\",");
		sb.append("t.\"RECID\" as \"id\",");
		sb.append("t.\"rejectReason\" as \"rejectReason\",");
		sb.append("t.\"remark\" as \"remark\",");
		sb.append("t.\"reportType\" as \"reportType\",");
		sb.append("t.\"sheetNo\" as \"sheetNo\",");
		sb.append("t.\"status\" as \"status\",");
		sb.append("t.\"storeId\" as \"storeId\",");
		sb.append("t.\"storeName\" as \"storeName\" ");
		return sb.toString();
	}
	
	private ReportLoss getReportLossEntityByRs(RecordSet rs) {
		ReportLoss entity = new ReportLoss();
		entity.setApplyDate(rs.getFields().get(0).getDate());
		entity.setApprovalDate(rs.getFields().get(1).getDate());
		entity.setApprovalPersonId(rs.getFields().get(2).getGUID());
		entity.setApprovalPersonName(rs.getFields().get(3).getString());
		entity.setCreateDate(rs.getFields().get(4).getDate());
		entity.setCreator(rs.getFields().get(5).getString());
		entity.setCreatorId(rs.getFields().get(6).getGUID());
		entity.setId(rs.getFields().get(7).getGUID());
		entity.setRejectReason(rs.getFields().get(8).getString());
		entity.setRemark(rs.getFields().get(9).getString());
		entity.setSheetNo(rs.getFields().get(11).getString());
		entity.setStatus(rs.getFields().get(12).getString());
		entity.setStoreId(rs.getFields().get(13).getGUID());
		entity.setStoreName(rs.getFields().get(14).getString());
		return entity;
	}
	
	/************************* 报损条目  *****************************************/
	
	/**
	 * 创建报损条目
	 */
	@Publish
	protected class CreateReportLossItem extends TaskMethodHandler<ReportLossItemTask, ReportLossItemTask.Method> {

		protected CreateReportLossItem() {
			super(ReportLossItemTask.Method.Create);
		}

		@Override
		protected void handle(Context context, ReportLossItemTask task)
				throws Throwable {
			ORMAccessor<ReportLossItem> itemAccessor = context.newORMAccessor(orm_reportLossItem);
			try {
				itemAccessor.insert(task.getItems());
			} finally {
				itemAccessor.unuse();
			}
		}
		
	}
	/**
	 * 根据报损单ID查询报损单明细
	 * @author Administrator
	 *
	 */
	@Publish
	protected class QueryReportLossItemBySheetId extends OneKeyResultListProvider<ReportLossItem, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<ReportLossItem> resultList) throws Throwable {
			ORMAccessor<ReportLossItem> itemAccessor = context.newORMAccessor(orm_reportLossItem);
			try {
				resultList.addAll(itemAccessor.fetch(key));
			} finally {
				itemAccessor.unuse();
			}
		}
		
	}
	
	/**
	 * 删除指定的报损单条目
	 * @author Administrator
	 *
	 */
	@Publish
	protected class DeleteReportLossItemBySheetId extends SimpleTaskMethodHandler<DeleteReportLossItemTask> {

		@Override
		protected void handle(Context context, DeleteReportLossItemTask task)
				throws Throwable {
			ORMAccessor<ReportLossItem> itemAccessor = context.newORMAccessor(orm_reportLossItem);
			try {
				itemAccessor.delete(task.getItemIds());
			} finally {
				itemAccessor.unuse();
			}
		}
		
	}
	
	/************************* 报损条目货位 *****************************************/
	
	/**
	 * 批量创建报损条目货位分配信息
	 */
	@Publish
	protected class CreateReportLossItemDetHandler extends SimpleTaskMethodHandler<CreateReportLossItemDetTask> {

		@Override
		protected void handle(Context context, CreateReportLossItemDetTask task)
				throws Throwable {
			ORMAccessor<ReportLossItemDet> itemAccessor = context.newORMAccessor(orm_reportLossItemDet);
			try {
				itemAccessor.insert(task.getItemDets());
			} finally {
				itemAccessor.unuse();
			}
		}
		
	}
	
	/**
	 * 删除货位分配信息
	 * @author Administrator
	 *
	 */
	@Publish
	protected class DeleteReportLossItemByItemId extends SimpleTaskMethodHandler<DeleteReportLossItemDetTask> {

		@Override
		protected void handle(Context context, DeleteReportLossItemDetTask task)
				throws Throwable {
			ORMAccessor<ReportLossItemDet> itemAccessor = context.newORMAccessor(orm_reportLossItemDet);
			try {
				itemAccessor.delete(task.getIds());
			} finally {
				itemAccessor.unuse();
			}
		}
	}
	
	/**
	 * 查询指定报损单条目的所有货位分配信息
	 * @author Administrator
	 *
	 */
	@Publish
	protected class QueryReportLossItemDetByItemId extends OneKeyResultListProvider<ReportLossItemDet, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<ReportLossItemDet> resultList) throws Throwable {
			ORMAccessor<ReportLossItemDet> itemAccessor = context.newORMAccessor(orm_reportLossItemDet);
			try {
				resultList.addAll(itemAccessor.fetch(key));
			} finally {
				itemAccessor.unuse();
			}
		}
		
	}
}
