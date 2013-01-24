/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.allocate.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-24       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.internal.entity.AllocateInventoryItem;
import com.spark.psi.inventory.internal.entity.AllocateItemDet;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.internal.key.GetSheltItemBySheetIdKey;
import com.spark.psi.inventory.internal.task.CreateAllocateItemDetTask;
import com.spark.psi.inventory.intf.inventoryenum.AllocateTab;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.allocateinventory.AllocateItemKey;
import com.spark.psi.inventory.intf.key.allocateinventory.AllocateKey;
import com.spark.psi.inventory.intf.key.allocateinventory.QueryToOutCountKey;
import com.spark.psi.inventory.intf.key.allocateinventory.AllocateKey.QueryObjType;
import com.spark.psi.inventory.intf.key.inventory.AverageInventoryCostKey;
import com.spark.psi.inventory.intf.task.allocateinventory.AllocateInventoryItemTask;
import com.spark.psi.inventory.intf.task.allocateinventory.AllocateInventoryTask;
import com.spark.psi.inventory.intf.task.allocateinventory.UpdateAllocateOnExaChangeTask;
import com.spark.psi.inventory.intf.task.allocateinventory.UpdateAllocateStatusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryLockTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.util.allocateinventory.EntityMapping;
import com.spark.psi.inventory.intf.util.allocateinventory.SqlBuildHelper;
import com.spark.psi.inventory.service.orm.Orm_AllocateDetail;
import com.spark.psi.inventory.service.orm.Orm_AllocateInfo;
import com.spark.psi.inventory.service.orm.Orm_AllocateItemDet;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey;

/**
 * @author zhongxin
 * 
 */
public class AllocateInventoryService extends Service {
	private Orm_AllocateInfo orm_allocateInfo;
	private Orm_AllocateDetail orm_allocateDetail;
	private Orm_AllocateItemDet orm_allocateItemDet;

	public AllocateInventoryService(Orm_AllocateInfo orm_allocateInfo,
			Orm_AllocateDetail orm_allocateDetail, Orm_AllocateItemDet orm_allocateItemDet) {
		super("StoreAllocateService");
		this.orm_allocateDetail = orm_allocateDetail;
		this.orm_allocateInfo = orm_allocateInfo;
		this.orm_allocateItemDet = orm_allocateItemDet;
	}

	/******************************************************************/
	/************************ 主表（AllocateInfo） **********************/
	/*****************************************************************/
	/**
	 * 新增一条库存调拨单记录
	 */
	@Publish
	protected class AddAllocateInfo extends
			TaskMethodHandler<AllocateInventoryTask, Method> {

		protected AddAllocateInfo() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, AllocateInventoryTask task)
				throws Throwable {
			ORMAccessor<AllocateInventory> accessor = context
					.newORMAccessor(orm_allocateInfo);
			ORMAccessor<AllocateInventoryItem> orm = context
					.newORMAccessor(orm_allocateDetail);
			try {
				task.getAllocateEntity().setAllocSheetNo(context.get(String.class,
					SheetNumberType.InventoryAllocate));
				accessor.insert(task.getAllocateEntity());
				for (AllocateInventoryItem item : task.getList()) {
					orm.insert(item);
				}
			} finally {
				accessor.unuse();
				orm.unuse();
			}
		}

	}

	/**
	 * 根据调拨单GUID获得对应的实体
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class FindAllocateInfo extends
			OneKeyResultProvider<AllocateInventory, GUID> {

		@Override
		protected AllocateInventory provide(Context context, GUID allcoateRecid)
				throws Throwable {
			ORMAccessor<AllocateInventory> accessor = context
					.newORMAccessor(orm_allocateInfo);
			AllocateInventory allocateInfo = null;
			try {
				allocateInfo = accessor.findByRECID(allcoateRecid);
			} finally {
				accessor.unuse();
			}
			return allocateInfo;
		}

	}

	/**
	 * 修改库存调拨单记录
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ModifyAllocateInfo extends
			TaskMethodHandler<AllocateInventoryTask, Method> {

		protected ModifyAllocateInfo() {
			super(Method.MODIFY);
		}

		@Override
		protected void handle(Context context, AllocateInventoryTask task)
				throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			dnaSql
					.append("define update updateAllocateInfo(@tenantsGuid guid,\n");
			paramList.add(context.get(Login.class).getTenantId());
			dnaSql.append("@recid guid,\n");
			paramList.add(task.getAllocateEntity().getId());
			dnaSql.append("@allocateOutStoreGuid guid,\n");
			paramList.add(task.getAllocateEntity().getAllocateOutStoreId());
			dnaSql.append("@allocateOutStoreName string,\n");
			paramList.add(task.getAllocateEntity().getAllocateOutStoreName());
			dnaSql.append("@allocateInStoreGuid guid,\n");
			paramList.add(task.getAllocateEntity().getAllocateInStoreId());
			dnaSql.append("@allocateInStoreName string,\n");
			paramList.add(task.getAllocateEntity().getAllocateInStoreName());
			dnaSql.append("@allocateReason string\n");
			paramList.add(task.getAllocateEntity().getAllocateReason());
			dnaSql.append(",@submittingstatus string\n");
			paramList.add(InventoryAllocateStatus.Submitting.getCode());
			dnaSql.append(",@rejectedstatus string\n");
			paramList.add(InventoryAllocateStatus.Rejected.getCode());
			dnaSql.append(")");

			dnaSql.append("begin\n");
			dnaSql.append("update PSI_Inventory_Allocate as t\n");
			dnaSql.append("set allocateOutStoreId=@allocateOutStoreGuid\n");
			dnaSql.append(",allocateOutStoreName=@allocateOutStoreName\n");
			dnaSql.append(",allocateInStoreId=@allocateInStoreGuid\n");
			dnaSql.append(",allocateInStoreName=@allocateInStoreName\n");
			dnaSql.append(",rejectReason=@allocateReason\n");
            
			dnaSql.append("where \n");
			dnaSql.append("t.recid=@recid\n");
			dnaSql.append("and (t.\"status\"=@submittingstatus or t.\"status\"=@rejectedstatus)\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int i = 0; i < paramList.size(); i++) {
				db.setArgumentValue(i, paramList.get(i));
			}
			ORMAccessor<AllocateInventoryItem> orm = context
					.newORMAccessor(orm_allocateDetail);
			try {
				task.setCount(db.executeUpdate());
				if ( task.getCount()> 0) {
					deleteDetails(context, task);
					for (AllocateInventoryItem item : task.getList()) {
						orm.insert(item);
					}
				}
			} finally {
				orm.unuse();
			}
		}

	}
	

	/**
	 * 删除指定的库存调拨单记录
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class DelAllocateInfo extends
			TaskMethodHandler<AllocateInventoryTask, Method> {

		protected DelAllocateInfo() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context, AllocateInventoryTask task)
				throws Throwable {
			ORMAccessor<AllocateInventory> accessor = context
					.newORMAccessor(orm_allocateInfo);
			try {
				if (accessor.delete(task.getAllocateInfoGuid())) {

					deleteDetails(context, task);
				}

			} finally {
				accessor.unuse();
			}
		}

	}

	/**
	 * 查询调拨单 因为调拨单的查询相对不多，所以就用一个key来进行查询
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class QueryAllocateInfo extends
			OneKeyResultListProvider<AllocateInventory, AllocateKey> {

		@Override
		protected void provide(Context context, AllocateKey key,
				List<AllocateInventory> resultList) throws Throwable {
			SqlBuildHelper sqlHelper = new SqlBuildHelper(context.find(
					Login.class).getTenantId());

			if (GetInventoryAllocateSheetListKey.Submittingstatus.equals(key
					.getdealState())) { // 新增页签下只有创建人自己能看见
				sqlHelper.buildQueryAllocateInfoByCreatePersonSQL(context.find(
						Login.class).getEmployeeId(), key.getdealState(), key
						.getSortColumnName(), key.getSortType(), key
						.getSearchString(), key);
			} else {
				
				GetStoreListKey sKey = new GetStoreListKey(StoreStatus.ENABLE,
						StoreStatus.ONCOUNTING);
				ListEntity<StoreItem> listEntity = context.find(ListEntity.class, sKey);
				if (CheckIsNull.isEmpty(listEntity.getItemList())) {
					return;
				}
				if (GetInventoryAllocateSheetListKey.Approvalingstatus
						.equals(key.getdealState())) {
					sqlHelper.buildQueryAllocateInfoByStore(listEntity.getItemList(), key
							.getdealState(), true, key.getSortColumnName(), key
							.getSortType(), key.getSearchString(), key);
				} else {
					sqlHelper.buildQueryAllocateInfoByStore(listEntity.getItemList(), key
							.getdealState(), false, key.getSortColumnName(),
							key.getSortType(), key.getSearchString(), key);
				}
			}
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			RecordSet rs;
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList
						.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList
							.get(parameterIndex));
				}

				rs = db.executeQueryLimit(key.getOffset(), key.getCount());

				while (rs.next()) {
					resultList.add(EntityMapping.mappingAllocateInfo(rs));
				}

			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 获得最后创建单据的日期
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class GetLatestRecord extends
			OneKeyResultProvider<Long, AllocateKey> {

		@Override
		protected Long provide(Context context, AllocateKey key)
				throws Throwable {
			SqlBuildHelper sqlHelper = new SqlBuildHelper(context.find(
					Login.class).getTenantId());
			if (AllocateTab.TAB_CREATE.equals(key.getShowContent())) { // 新增页签下只有创建人自己能看见
				sqlHelper.buildQueryAllocateInfoByCreatePersonSQL(key
						.getQueryObjGuid(), key.getdealState(), key
						.getSortColumnName(), key.getSortType(), key
						.getSearchString(), key);
			} else if (QueryObjType.BOSS.equals(key.getQueryObjType())) {// 总经理能查看所有非新增、退回状态下的单据
				sqlHelper.buildQueryAllocateInfoBystatusSql(key.getdealState(),
						key.getSortColumnName(), key.getSortType(), key
								.getSearchString(), key);
			} else { // 经理和员工只能查看权限范围内了的单据
 
				GetStoreListKey sKey = new GetStoreListKey(StoreStatus.ENABLE,
						StoreStatus.ONCOUNTING);
				ListEntity<StoreItem> listEntity = context.find(ListEntity.class, sKey);
				
				if (AllocateTab.TAB_EXAMINE.equals(key.getShowContent())) {
					sqlHelper.buildQueryAllocateInfoByStore(listEntity.getItemList(), key
							.getdealState(), true, key.getSortColumnName(), key
							.getSortType(), key.getSearchString(), key);
				} else {
					sqlHelper.buildQueryAllocateInfoByStore(listEntity.getItemList(), key
							.getdealState(), false, key.getSortColumnName(),
							key.getSortType(), key.getSearchString(), key);
				}
			}
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer.append("define query Qury_StoreStorage(");
			String parameterStr = sqlHelper.getParamterStr();
			if (parameterStr.length() > 0 && parameterStr.endsWith(",")) {
				scriptBuffer.append(parameterStr.toString().substring(0,
						parameterStr.toString().length() - 1));
			}
			scriptBuffer.append(") \n");
			scriptBuffer.append(" begin \n");
			scriptBuffer.append(sqlHelper.getQueryBody());
			scriptBuffer.append("");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			RecordSet rs;
			Long latestDate = null;
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList
						.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList
							.get(parameterIndex));
				}
				rs = db.executeQuery();
				if (rs.next()) {
					latestDate = rs.getFields().get(13).getDate();
				}

			} finally {
				db.unuse();
			}
			return latestDate;
		}

	}

	/**
	 * 查询指定商品在单据状态为“待出库”中的数量和
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class GetGoodsCountToOut extends
			OneKeyResultProvider<Double, QueryToOutCountKey> {

		@Override
		protected Double provide(Context context, QueryToOutCountKey key)
				throws Throwable {
			SqlBuildHelper sqlHelper = new SqlBuildHelper(key.getTenantsGuid());
			sqlHelper.buildQueryToAllocateOutCountSQL(key.getStoreGuid(), key
					.getGoodsGuid());
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			Double result = 0.0;
			RecordSet rs;
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList
						.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList
							.get(parameterIndex));
				}
				rs = db.executeQuery();
				while (rs.next()) {
					result = rs.getFields().get(0).getDouble();
				}

			} finally {
				db.unuse();
			}
			return result;
		}

	}

	/**
	 * 提交时更新调拨单的状态
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ChangestatusWhenSubmit
			extends
			TaskMethodHandler<UpdateAllocateStatusTask, UpdateAllocateStatusTask.Method> {

		protected ChangestatusWhenSubmit() {
			super(UpdateAllocateStatusTask.Method.SUBMIT);
		}

		@Override
		protected void handle(Context context, UpdateAllocateStatusTask task)
				throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();
			List<Object> paramList = new ArrayList<Object>();
			scriptBuffer
					.append("define update up_updateAllocate(@allocateInfoGuid guid,@applyDate date\n");
			paramList.add(task.getAllocateGuid());
			paramList.add(new Date().getTime());
			if(context.find(Boolean.class, Auth.Boss))
			{
				Login login = context.find(Login.class);
				scriptBuffer.append(",@personId guid");
				paramList.add(login.getEmployeeId());
				scriptBuffer.append(",@personName string");
				paramList.add(context.find(Employee.class,login.getEmployeeId()).getName());
			}
			scriptBuffer.append(") \nbegin \n");
			scriptBuffer.append("update PSI_Inventory_Allocate as allocate \n");
			scriptBuffer.append(" set applyDate = @applyDate, \"status\" = '"
					+ task.getUpTostatus().getCode() + "'");
			if(context.find(Boolean.class, Auth.Boss))
			{
				scriptBuffer.append(",approvePerson=@personName");
				scriptBuffer.append(",approveDate=@applyDate");
				scriptBuffer.append(",approvePersonId=@personId \n");
			}
			scriptBuffer
					.append(
							"where allocate.RECID = @allocateInfoGuid and (allocate.\"status\" = '")
					.append(InventoryAllocateStatus.Submitting.getCode())
					.append("'\n");
			scriptBuffer.append("or allocate.\"status\"='").append(
					InventoryAllocateStatus.Rejected.getCode()).append("')\n");
			scriptBuffer.append("end \n");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			for(int i=0;i<paramList.size();i++)
			{
				db.setArgumentValue(i, paramList.get(i));
			}
			try {
				task.setUpdateResult(db.executeUpdate());
				if (task.getUpdateResult() > 0
						&& InventoryAllocateStatus.Allocating.equals(task
								.getUpTostatus())) {
					lockGoodsInventory(context, task.getAllocateGuid(), true);
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 审核时更新调拨单状态
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ChangestatusWhenExamine
			extends
			TaskMethodHandler<UpdateAllocateStatusTask, UpdateAllocateStatusTask.Method> {

		protected ChangestatusWhenExamine() {
			super(UpdateAllocateStatusTask.Method.EXMAINE);
		}

		@Override
		protected void handle(Context context, UpdateAllocateStatusTask task)
				throws Throwable {
			int result = 0;
			// 更新审核状态，同时更新状态为待出库
			DBCommand db = context.prepareStatement(getUpdateExamineSQL(task,
					true));
			Employee employee = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			db.setArgumentValues(task.getAllocateGuid(), new Date().getTime(),
					employee.getId(), employee.getName(),
					true, true, new Date().getTime());
			try {
				result = db.executeUpdate();
				task.setUpdateResult(result);
				if (result < 1) {
					// 只更新审核状态
					db = context.prepareStatement(getUpdateExamineSQL(task,
							false));
					db.setArgumentValues(task.getAllocateGuid(), new Date()
							.getTime(), employee.getId(), employee.getName(), true, true, new Date()
							.getTime());
					task.setUpdateResult(db.executeUpdate());
				}
			} finally {
				db.unuse();
			}
		}

		/**
		 * 
		 * @param task
		 * @param isUpdatestatus
		 *            是否更新单据状态
		 * @return
		 */
		protected String getUpdateExamineSQL(UpdateAllocateStatusTask task,
				boolean isUpdatestatus) {
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer
					.append("define update up_updateAllocate(@allocateInfoGuid guid,@exmaineDate date,@examinePerson guid,@examinePersonName string,"
							+ "@examineTrue boolean, @isTheOtherExamine boolean, @currentDate date) \n");
			scriptBuffer.append("begin \n");
			scriptBuffer.append("update PSI_Inventory_Allocate as allocate \n");
			scriptBuffer
					.append("set \"status\" = '"	+ InventoryAllocateStatus.Allocating.getCode() + "' \n");
			if (InventoryAllocateStatus.Submitting
					.equals(task.getPrestatus())) {
				scriptBuffer.append(", applyDate = @currentDate \n");
			}
			scriptBuffer
					.append("where allocate.RECID = @allocateInfoGuid and allocate.\"status\"='"
							+ task.getPrestatus().getCode() + "' \n");
			scriptBuffer.append("end \n");
			return scriptBuffer.toString();
		}
	}

	/**
	 * 确认出（入）库时更新调拨单状态
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ChangestatusWhenConfirm
			extends
			TaskMethodHandler<UpdateAllocateStatusTask, UpdateAllocateStatusTask.Method> {

		protected ChangestatusWhenConfirm() {
			super(UpdateAllocateStatusTask.Method.CONFIRM);
		}

		@Override
		protected void handle(Context context, UpdateAllocateStatusTask task)
				throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer
					.append("define update up_updateAllocate(@allocateInfoGuid guid,@allocateDate date, @allocatePerson guid,"
							+ "@allocatePersonName string) \n");
			scriptBuffer.append("begin \n");
			scriptBuffer.append("update PSI_Inventory_Allocate as allocate \n");
			if (task.getPrestatus().equals(InventoryAllocateStatus.Allocating)) {
				scriptBuffer
						.append(" set allocateOutDate = @allocateDate, allocateOutPerson = @allocatePerson, allocateOutPersonName = @allocatePersonName,"
								+ " \"status\" = '"
								+ task.getUpTostatus().getCode() + "' \n");
			} else if (task.getPrestatus().equals(
					InventoryAllocateStatus.AllocateOut)) {
				scriptBuffer
						.append(" set allocateInDate = @allocateDate, allocateInPerson = @allocatePerson, allocateInPersonName = @allocatePersonName,"
								+ " \"status\" = '"
								+ task.getUpTostatus().getCode() + "' \n");
			}
			scriptBuffer
					.append("where allocate.RECID = @allocateInfoGuid and allocate.\"status\" = '"
							+ task.getPrestatus().getCode() + "'\n");
			scriptBuffer.append("end \n");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			Employee employee = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			db.setArgumentValues(task.getAllocateGuid(), new Date().getTime(),
					employee.getId(), employee.getName());
			try {
				task.setUpdateResult(db.executeUpdate());
				if (task.getUpdateResult() > 0) {
					if (InventoryAllocateStatus.AllocateOut.equals(task
							.getUpTostatus())
							&& InventoryAllocateStatus.Allocating.equals(task
									.getPrestatus())) {
						updateGoodsInventory(context, task.getAllocateGuid(),
								true, task.getItems());
						lockGoodsInventory(context, task.getAllocateGuid(),
								false);
					}
					if (InventoryAllocateStatus.AllocateIn.equals(task
							.getUpTostatus())
							&& InventoryAllocateStatus.AllocateOut.equals(task
									.getPrestatus())) {
						handAllocateIn(context, task.getAllocateGuid(), task.getItems());
					}
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 退回时更新调拨单状态
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class ChangestatusWhenReject
			extends
			TaskMethodHandler<UpdateAllocateStatusTask, UpdateAllocateStatusTask.Method> {

		protected ChangestatusWhenReject() {
			super(UpdateAllocateStatusTask.Method.REJECT);
		}

		@Override
		protected void handle(Context context, UpdateAllocateStatusTask task)
				throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer
					.append("define update up_updateAllocate(@allocateInfoGuid guid, @rejectReason string, @isInExamine boolean, @isOutExamine boolean,"
							+ "@exmaineDate date,@examinePerson guid,@examinePersonName string, @examineFalse boolean) \n");
			scriptBuffer.append("begin \n");
			scriptBuffer.append("update PSI_Inventory_Allocate as allocate \n");
			scriptBuffer
					.append(" set \"status\" = '"
							+ task.getUpTostatus().getCode()
							+ "', "	+ "rejectReason = @rejectReason \n");
			scriptBuffer
					.append("where allocate.RECID = @allocateInfoGuid and allocate.\"status\" = '"
							+ task.getPrestatus().getCode() + "'\n");
			scriptBuffer.append("end \n");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			String rejectReason = task.getRejectReason();
			if (rejectReason.length() > 1000) {
				rejectReason = rejectReason.substring(0, 999);
			}
//			Employee employee = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			db.setArgumentValues(task.getAllocateGuid(), rejectReason, false,
					false, new Date().getTime(), null,
					null, false);
			try {
				task.setUpdateResult(db.executeUpdate());
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 审核变化时更新单据状态
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class UpOnExamineChange extends
			SimpleTaskMethodHandler<UpdateAllocateOnExaChangeTask> {

		@Override
		protected void handle(Context context,
				UpdateAllocateOnExaChangeTask task) throws Throwable {
			if (task.isOpenExamine()) {
				return;
			}
			Login login = context.find(Login.class);
			Employee user = context.get(Employee.class, login.getEmployeeId());
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer
					.append("define update up_updateAllocate(@userGuid guid, @userName string, @updateDate date,"
							+ " @examineFalse boolean) \n");
			scriptBuffer.append("begin \n");
			scriptBuffer.append("update PSI_Invetnory_Allocate as allocate \n");
			scriptBuffer
					.append(" set \"status\" = '"
							+ InventoryAllocateStatus.Rejected.getCode()
							+ "', "	+ "rejectReason = '" + task.getCause() + "', \n");
			scriptBuffer
					.append("where allocate.\"status\" = '"
							+ InventoryAllocateStatus.Submitted.getCode()
							+ "'\n");
			scriptBuffer.append("end \n");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				db.setArgumentValues(login.getEmployeeId(), user.getName(), new Date()
								.getTime(), false);
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}

	}

	/********************************************************************/
	/************************ 明细（AllocateDetail） **********************/
	/*******************************************************************/
	/**
	 * 新增一条库存调拨明细
	 */
	@Publish
	protected class AddAllocateDetail extends
			TaskMethodHandler<AllocateInventoryItemTask, Method> {

		protected AddAllocateDetail() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, AllocateInventoryItemTask task)
				throws Throwable {
			ORMAccessor<AllocateInventoryItem> accessor = context
					.newORMAccessor(orm_allocateDetail);
			try {
				accessor.insert(task.getAllocateInventoryItemEntity());
			} finally {
				accessor.unuse();
			}
		}

	}

	/**
	 * 删除指定调拨单的明细记录
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class DelAllocateDetailByInfoId extends
			TaskMethodHandler<AllocateInventoryItemTask, Method> {

		protected DelAllocateDetailByInfoId() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context, AllocateInventoryItemTask task)
				throws Throwable {
			StringBuffer scriptBuffer = new StringBuffer();
			scriptBuffer
					.append("define delete Del_deleteByParentId(@allocateInfoGuid guid) \n");
			scriptBuffer.append("begin \n");
			scriptBuffer
					.append("delete from PSI_Invetnory_Allocate_Det as detail where detail.allocateId = @allocateInfoGuid \n");
			scriptBuffer.append("end \n");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			db.setArgumentValues(task
					.getAllocateInventoryID());
			try {
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 查询指定调拨单的明细记录
	 * 
	 * @author zhongxin
	 * 
	 */
	@Publish
	protected class QueryAllocateDetailByInfoId extends
			OneKeyResultListProvider<AllocateInventoryItem, AllocateItemKey> {

		@Override
		protected void provide(Context context, AllocateItemKey key,
				List<AllocateInventoryItem> resultList) throws Throwable {
			Login login = context.find(Login.class);
			SqlBuildHelper sqlHelper = new SqlBuildHelper(login.getTenantId());
			sqlHelper.buildQueryAllocateDetailSQL(key.getAllocateOrdGuid());
			DBCommand db = context.prepareStatement(sqlHelper.getQuerySql());
			RecordSet rs;
			try {
				List<Object> parameterList = sqlHelper.getParameterList();
				for (int parameterIndex = 0; parameterIndex < parameterList
						.size(); parameterIndex++) {
					db.setArgumentValue(parameterIndex, parameterList
							.get(parameterIndex));
				}
				rs = db.executeQuery();
				while (rs.next()) {
					resultList.add(EntityMapping.mappingAllocateDetail(rs));
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 创建调拔货位信息
	 * @author Administrator
	 *
	 */
	@Publish
	protected class CreateAllocateItemDet extends SimpleTaskMethodHandler<CreateAllocateItemDetTask> {

		@Override
		protected void handle(Context context, CreateAllocateItemDetTask task)
				throws Throwable {
			ORMAccessor<AllocateItemDet> itemAccessor = context.newORMAccessor(orm_allocateItemDet);
			try {
				itemAccessor.insert(task.getItemDets());
			} finally {
				itemAccessor.unuse();
			}
		}
	}
	
	/**
	 * 根据高明明细ID获得该明细对应的货位信息
	 * @author Administrator
	 *
	 */
	@Publish
	protected class GetAllocateItemDetByAllocateItemId extends OneKeyResultListProvider<AllocateItemDet, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<AllocateItemDet> resultList) throws Throwable {
			ORMAccessor<AllocateItemDet> itemAccessor = context.newORMAccessor(orm_allocateItemDet);
			try {
				resultList.addAll(itemAccessor.fetch(key));
			} finally {
				itemAccessor.unuse();
			}
			
		}
		
	}
	/**
	 * 取得指定调拔单所有的货位信息（只有调出库的货位信息）
	 * @author Administrator
	 *
	 */
	@Publish
	protected class GetAllocateItemDetByAllocateSheetId extends OneKeyResultListProvider<AllocateItemDet, GetSheltItemBySheetIdKey> {

		@Override
		protected void provide(Context context,
				GetSheltItemBySheetIdKey key,
				List<AllocateItemDet> resultList) throws Throwable {
			StringBuffer sql = new StringBuffer();
			sql.append("define query queryAllocateShelfItem(@sheetId guid)\n");
			sql.append("begin \n");
			sql.append("select ").append(getColumns());
			sql.append("from PSI_Inventory_Allocate_Shelf as t \n");
			sql.append("where t.sheetId = @sheetId \n");
			sql.append("end \n");
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(key.getSheetId());
			RecordSet rs = db.executeQuery();
			while(rs.next()) {
				AllocateItemDet det = getAllocateItemDetByRs(rs);
				resultList.add(det);
			}
		}
		
		protected AllocateItemDet getAllocateItemDetByRs(RecordSet rs) {
			AllocateItemDet det = new AllocateItemDet();
			det.setId(rs.getFields().get(0).getGUID());
			det.setAllocateItemId(rs.getFields().get(1).getGUID());
			det.setSheetId(rs.getFields().get(2).getGUID());
			det.setStockId(rs.getFields().get(3).getGUID());
			det.setStoreId(rs.getFields().get(4).getGUID());
			det.setCount(rs.getFields().get(5).getDouble());
			det.setSheetId(rs.getFields().get(6).getGUID());
			det.setShelfNo(rs.getFields().get(7).getInt());
			det.setTiersNo(rs.getFields().get(8).getInt());
			det.setProduceDate(rs.getFields().get(9).getDate());
			det.setStockName(rs.getFields().get(10).getString());
			return det;
		}
		
		protected String getColumns() {
			StringBuffer sb = new StringBuffer();
			sb.append("t.recid as recid, \n");
			sb.append("t.allocateItemId as allocateItemId, \n");
			sb.append("t.sheetId as sheetId, \n");
			sb.append("t.stockId as stockId, \n");
			sb.append("t.storeId as storeId, \n");
			sb.append("t.\"count\" as \"count\", \n");
			sb.append("t.shelfId as shelfId, \n");
			sb.append("t.shelfNo as shelfNo, \n");
			sb.append("t.tiersNo as tiersNo, \n");
			sb.append("t.produceDate as produceDate, \n");
			sb.append("t.stockName as stockName \n");
			return sb.toString();
		}
		
	}
	
	
	/**
	 * 删除对应调拨明细列表
	 * 
	 * @param context
	 * 
	 * @param task
	 *            void
	 */
	public void deleteDetails(Context context, AllocateInventoryTask task) {
		StringBuilder dnaSql = new StringBuilder();
		dnaSql
				.append("define delete deleteAllocateDetails(@tenantId guid,@allocateOrdGuid guid)\n");
		dnaSql.append("begin\n");
		dnaSql.append("delete from PSI_Inventory_ALLOCATE_DET as t\n");
		dnaSql.append("where \n");
		dnaSql.append("t.allocateId=@allocateOrdGuid\n");
		dnaSql.append("end");

		DBCommand db = context.prepareStatement(dnaSql);
		GUID sheetId;
		if (CheckIsNull.isEmpty(task.getAllocateEntity())) {
			sheetId = task.getAllocateInfoGuid();
		} else {
			sheetId = task.getAllocateEntity().getId();
		}
		db.setArgumentValues(context.find(Login.class).getTenantId(), sheetId);
		try {
			db.executeUpdate();
		} finally {
			db.unuse();
		}

	}

	/**
	 * 更新库存数量
	 * 
	 * @param context
	 * @param allocateGuid
	 * @param isOut
	 *            void 出库OR入库
	 */
	public void updateGoodsInventory(Context context, GUID sheetId,
			boolean isOut, AllocateItemDet[] shelfInfos) {
		AllocateInventory al = context.find(AllocateInventory.class, sheetId);
		AllocateItemKey key = new AllocateItemKey();
		key.setAllocateOrdGuid(sheetId);
		List<AllocateInventoryItem> itemList = context.getList(
				AllocateInventoryItem.class, key);
		if (CheckIsNull.isEmpty(al) || CheckIsNull.isEmpty(itemList)) {
			return;
		}
		// 只用于调出的时候
		final Map<GUID, List<AllocateItemDet>> detItemMap = new HashMap<GUID, List<AllocateItemDet>>();
		if (isOut) {
			for (AllocateItemDet shefInfo : shelfInfos) {
				List<AllocateItemDet> tempList = null;
				if (detItemMap.containsKey(shefInfo.getAllocateItemId())) {
					tempList = detItemMap.get(shefInfo.getAllocateItemId());
				} else {
					tempList = new ArrayList<AllocateItemDet>();
					detItemMap.put(shefInfo.getAllocateItemId(), tempList);
				}
				tempList.add(shefInfo);
			}
		}
		AllocateItemDet[] itemDets = null;
		for (AllocateInventoryItem item : itemList) {
			double count = 0.0;
			GUID storeId = null;
			if (isOut) {
				count = DoubleUtil.mul(-1, item.getAllocateCount());
				storeId = al.getAllocateOutStoreId();
				List<AllocateItemDet> tempList = detItemMap.get(item.getId());
				itemDets = tempList.toArray(new AllocateItemDet[0]);
			} 
//			else {
//				count = item.getAllocateCount();
//				storeId = al.getAllocateInStoreId();
//				List<AllocateItemDet> itemDetList = context.getList(AllocateItemDet.class, item.getId());
//				itemDets = itemDetList.toArray(new AllocateItemDet[0]);
//			}
			InventoryBusTask task = new InventoryBusTask(storeId, item
					.getStockId());
			InventoryBusTask.DetItem[] iDetItems = new InventoryBusTask.DetItem[itemDets.length];
			InventoryBusTask.DetItem iDetItem = null;
			for (int itemDetIndex = 0; itemDetIndex < itemDets.length; itemDetIndex++) {
				AllocateItemDet itemDet = itemDets[itemDetIndex];
				iDetItem = task.new DetItem(itemDet.getShelfId(), itemDet.getShelfNo(), itemDet.getTiersNo(), 
						itemDet.getStockId(), isOut ? (-1 * itemDet.getCount()) : itemDet.getCount(), itemDet.getProduceDate(), storeId);
				iDetItems[itemDetIndex] = iDetItem;
			}
			task.setDets(iDetItems);
			task.setUpdateAvgPrice(false);
			task.setChangeCount(count);
			context.handle(task);

			StoStreamTask sTask = new StoStreamTask();
			InventoryLogEntity stoStream = new InventoryLogEntity();
			stoStream.setOrderId(al.getId());
			stoStream.setOrderNo(al.getAllocSheetNo());
			stoStream.setCreatedDate(new Date().getTime());
			stoStream.setCreatePerson(context.find(Employee.class,
					context.find(Login.class).getEmployeeId()).getName());
			stoStream.setCreatedDate(new Date().getTime());
			stoStream.setStockId(item.getStockId());
			MaterialsItem materials = context
					.find(MaterialsItem.class, item.getStockId());
			if (null != materials) {
				stoStream.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(materials.getMaterialProperties()));
				stoStream.setName(materials.getMaterialName());
				stoStream.setCode(materials.getMaterialCode());
				stoStream.setCategoryId(materials.getCategoryId());
				stoStream.setInventoryType(InventoryType.Materials.getCode());
				stoStream.setScale(materials.getScale());
				stoStream.setStockNo(materials.getMaterialNo());
			}
			if (isOut) {
				stoStream.setOutstoCount(item.getAllocateCount());
				AverageInventoryCostKey cKey = new AverageInventoryCostKey(item.getStockId(),task.getInventoryType());
				Double cost = context.find(Double.class, cKey);
				if(null!=cost)
				{
					stoStream.setOutstoAmount(DoubleUtil.mul(cost, item.getAllocateCount(),2));
				}
				stoStream.setStoreId(al.getAllocateOutStoreId());
				stoStream.setLogType(InventoryLogType.GOODSOUTSTORAGE
						.getCode());
			} 
//			else {
//				stoStream.setInstoCount(item.getAllocateCount());
//				AverageInventoryCostKey cKey = new AverageInventoryCostKey(item.getStockId(),task.getInventoryType());
//				Double cost = context.find(Double.class, cKey);
//				if(null!=cost)
//				{
//					stoStream.setInstoAmount(DoubleUtil.mul(cost, item.getAllocateCount(),2));
//				}
//				stoStream.setStoreId(al.getAllocateInStoreId());
//				stoStream.setLogType(InventoryLogType.GOODSINSTORAGE
//						.getCode());
//			}
			stoStream.setId(context.newRECID());

			sTask.setStoStream(stoStream);

			context.handle(sTask, StoStreamTask.Task.add);
		}
	}
	
	protected void handAllocateIn(Context context, GUID sheetId, AllocateItemDet[] shelfInfos) {
		AllocateInventory al = context.find(AllocateInventory.class, sheetId);
		Map<GUID, List<AllocateItemDet>> goodsDets = new HashMap<GUID, List<AllocateItemDet>>();
		for (AllocateItemDet det : shelfInfos) {
			GUID goodsId = det.getStockId();
			List<AllocateItemDet> goodsDetList = goodsDets.get(goodsId);
			if (null == goodsDetList) {
				goodsDetList = new ArrayList<AllocateItemDet>();
				goodsDets.put(goodsId, goodsDetList);
			}
			goodsDetList.add(det);
		}
		Iterator<GUID> gIt = goodsDets.keySet().iterator();
		while(gIt.hasNext()) {
			GUID goodsId = gIt.next();
			List<AllocateItemDet> goodsDetList = goodsDets.get(goodsId);
			double count = 0.0;
			for (AllocateItemDet det : goodsDetList) {
				count += det.getCount();
			}
			updateInventoryOnAllocateIn(context, al.getAllocateInStoreId(), goodsId, goodsDetList.toArray(new AllocateItemDet[0]));
			createInventoryLogOnAllocateIn(context, al, goodsId, count);
		}
		
	}
	
	private void updateInventoryOnAllocateIn(Context context, GUID storeId, GUID stockId, AllocateItemDet[] shelfInfos) {
		InventoryBusTask task = new InventoryBusTask(storeId, stockId);
		InventoryBusTask.DetItem[] iDetItems = new InventoryBusTask.DetItem[shelfInfos.length];
		InventoryBusTask.DetItem iDetItem = null;
		double count = 0.0;
		for (int itemDetIndex = 0; itemDetIndex < shelfInfos.length; itemDetIndex++) {
			AllocateItemDet itemDet = shelfInfos[itemDetIndex];
			iDetItem = task.new DetItem(itemDet.getShelfId(), itemDet.getShelfNo(), itemDet.getTiersNo(), 
					itemDet.getStockId(), itemDet.getCount(), itemDet.getProduceDate(), storeId);
			count += itemDet.getCount();
			iDetItems[itemDetIndex] = iDetItem;
		}
		task.setDets(iDetItems);
		task.setUpdateAvgPrice(false);
		task.setChangeCount(count);
		context.handle(task);
	}
	
	private void createInventoryLogOnAllocateIn(Context context, AllocateInventory al, GUID stockId, double count) {
		StoStreamTask sTask = new StoStreamTask();
		InventoryLogEntity stoStream = new InventoryLogEntity();
		stoStream.setOrderId(al.getId());
		stoStream.setOrderNo(al.getAllocSheetNo());
		stoStream.setCreatedDate(new Date().getTime());
		stoStream.setCreatePerson(context.find(Employee.class,
				context.find(Login.class).getEmployeeId()).getName());
		stoStream.setCreatedDate(new Date().getTime());
		stoStream.setStockId(stockId);
		MaterialsItem materials = context
				.find(MaterialsItem.class, stockId);
		if (null != materials) {
			stoStream.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(materials.getMaterialProperties()));
			stoStream.setName(materials.getMaterialName());
			stoStream.setCode(materials.getMaterialCode());
			stoStream.setCategoryId(materials.getCategoryId());
			stoStream.setLogType(InventoryLogType.GOODSINSTORAGE.getCode());
			stoStream.setScale(materials.getScale());
			stoStream.setStockNo(materials.getMaterialNo());
		}
		stoStream.setInventoryType(InventoryType.Materials.getCode());
		stoStream.setInstoCount(count);
		AverageInventoryCostKey cKey = new AverageInventoryCostKey(stockId, InventoryType.Materials);
		Double cost = context.find(Double.class, cKey);
		if(null != cost) {
			stoStream.setInstoAmount(DoubleUtil.mul(cost, count,2));
		}
		stoStream.setStoreId(al.getAllocateInStoreId());
		stoStream.setLogType(InventoryLogType.GOODSINSTORAGE
				.getCode());
		stoStream.setId(context.newRECID());
		sTask.setStoStream(stoStream);

		context.handle(sTask, StoStreamTask.Task.add);
	}
	 /**
	 * 锁定/解锁库存
	 * 
	 * @param context
	 * @param sheetId
	 * @param isLock
	 *            void 锁定OR解锁
	 */
	public void lockGoodsInventory(Context context, GUID sheetId, boolean isLock) {
		AllocateInventory al = context.find(AllocateInventory.class, sheetId);
		AllocateItemKey key = new AllocateItemKey();
		key.setAllocateOrdGuid(sheetId);
		List<AllocateInventoryItem> itemList = context.getList(
				AllocateInventoryItem.class, key);
		if (CheckIsNull.isEmpty(al) || CheckIsNull.isEmpty(itemList)) {
			return;
		}
		for (AllocateInventoryItem item : itemList) {
			InventoryLockTask task = new InventoryLockTask(al
					.getAllocateOutStoreId(), item.getStockId());
			if (isLock) {
				task.setLockedCount(item.getAllocateCount());
			} else {
				task
						.setLockedCount(DoubleUtil.mul(-1, item
								.getAllocateCount()));
			}
			context.handle(task);
		}
	}

	
}
