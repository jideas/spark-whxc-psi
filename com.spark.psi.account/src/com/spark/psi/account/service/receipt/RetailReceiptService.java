/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.finance.receipt.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       向中秋        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.finance.receipt.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       向中秋        
 * ============================================================*/

package com.spark.psi.account.service.receipt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceQuerier;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.service.orm.Orm_RetailReceiptAll;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.account.intf.entity.receipt.RetailReceipt;
import com.spark.psi.account.intf.key.receipt.RetailReceiptRecordKey;
import com.spark.psi.account.intf.task.pay.ConfirmRetailPayRecordTask;
import com.spark.psi.account.intf.task.receipt.ConfirmRetailReceiptRecordTask;
import com.spark.psi.account.intf.task.receipt.InternalRetailReceiptTask;
import com.spark.psi.account.service.orm.Orm_RetailReceipt;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.publish.Auth;

/**
 * <p>
 * 零售交款service
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 */

public class RetailReceiptService extends Service {

	protected final Orm_RetailReceipt q_Orm_RetailReceipt;
	protected final Orm_RetailReceiptAll q_Orm_RetailReceiptAll;

	protected RetailReceiptService(Orm_RetailReceipt qOrmRetailReceipt,
			Orm_RetailReceiptAll qOrmRetailReceiptAll) {
		super("RetailReceiptService");
		q_Orm_RetailReceipt = qOrmRetailReceipt;
		q_Orm_RetailReceiptAll = qOrmRetailReceiptAll;
	}

	/**
	 * 根据RECID查找对象
	 */
	@Publish
	protected class BaseRetailReceiptProvider extends
			OneKeyResultProvider<RetailReceipt, GUID> {
		@Override
		protected RetailReceipt provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<RetailReceipt> acc = context
					.newORMAccessor(q_Orm_RetailReceipt);
			return acc.findByRECID(id);
		}
	}

	/**
	 * 查找所有对象
	 */
	@Publish
	protected class AllRetailReceiptProvider extends
			ResultListProvider<RetailReceipt> {
		@Override
		protected void provide(Context context, List<RetailReceipt> resultList)
				throws Throwable {
			ORMAccessor<RetailReceipt> acc = context
					.newORMAccessor(q_Orm_RetailReceiptAll);
			resultList.addAll(acc.fetch());
		}
	}

	/**
	 * 添加
	 */
	@Publish
	protected class AddRetailReceiptHandler
			extends
			TaskMethodHandler<InternalRetailReceiptTask, InternalRetailReceiptTask.Method> {
		public AddRetailReceiptHandler() {
			super(InternalRetailReceiptTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, InternalRetailReceiptTask task)
				throws Throwable {
			ORMAccessor<RetailReceipt> acc = context
					.newORMAccessor(q_Orm_RetailReceipt);
			task.getEntity().setRECID(context.newRECID());
			task.getEntity().setTenantsGuid(
					context.find(Login.class).getTenantId());
			acc.insert(task.getEntity());
		}
	}

	/**
	 * 修改
	 */
	@Publish
	protected class ModifyRetailReceiptHandler
			extends
			TaskMethodHandler<InternalRetailReceiptTask, InternalRetailReceiptTask.Method> {
		public ModifyRetailReceiptHandler() {
			super(InternalRetailReceiptTask.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, InternalRetailReceiptTask task)
				throws Throwable {
			ORMAccessor<RetailReceipt> acc = context
					.newORMAccessor(q_Orm_RetailReceipt);
			acc.update(task.getEntity());
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define update modifyRetailReceipt(@tenantId guid");
			dnaSql.append(",@saleEmpGuid guid,@shouldMoney double");
			dnaSql.append(",@shouldCardCount int,@shouldCardMoney double)\n");

			dnaSql.append("begin\n");
			dnaSql.append("update SA_FINANCE_RECEIPT_RETAIL as t\n");
			dnaSql.append("set shouldMoney=@shouldMoney+t.shouldMoney");
			dnaSql
					.append(",shouldCardCount=@shouldCardCount+t.shouldCardCount");
			dnaSql
					.append(",shouldCardMoney=@shouldCardMoney+t.shouldCardMoney\n");
			dnaSql.append("where t.tenantsGuid=@tenantId\n");
			dnaSql.append("and t.saleEmpGuid=@saleEmpGuid\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(context.find(Login.class).getTenantId(), task
					.getEntity().getSaleEmpGuid(), task.getEntity()
					.getShouldMoney(), task.getEntity().getShouldCardCount(),
					task.getEntity().getShouldCardMoney());
			try {
				int count = db.executeUpdate();
				if (0 == count) {
					context.handle(task, InternalRetailReceiptTask.Method.ADD);
				}
			} finally {
				db.unuse();
			}

		}
	}

	/**
	 * 确认
	 */
	@Publish
	protected class ConfirmRetailReceiptHandler
			extends
			TaskMethodHandler<InternalRetailReceiptTask, InternalRetailReceiptTask.Method> {
		public ConfirmRetailReceiptHandler() {
			super(InternalRetailReceiptTask.Method.Confirm);
		}

		@Override
		protected void handle(Context context, InternalRetailReceiptTask task)
				throws Throwable {
			RetailReceipt info = context.find(RetailReceipt.class, task
					.getRecid());
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define update confirmRetailReceipt(@tenantId guid");
			dnaSql.append(",@recid guid,@receiptDate date)\n");

			dnaSql.append("begin\n");
			dnaSql.append("update SA_FINANCE_RECEIPT_RETAIL as t\n");
			dnaSql.append("set shouldMoney=0");
			dnaSql.append(",shouldCardCount=0");
			dnaSql.append(",shouldCardMoney=0");
			dnaSql.append(",receiptDate=@receiptDate\n");
			dnaSql.append("where t.tenantsGuid=@tenantId\n");
			dnaSql.append("and t.recid=@recid\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(context.find(Login.class).getTenantId(), task
					.getRecid(), new Date().getTime());
			try {
				int count = db.executeUpdate();
				if (count > 0 && null != info) {
					ConfirmRetailReceiptRecordTask cTask = new ConfirmRetailReceiptRecordTask(
							info.getSaleEmpGuid());
					context.handle(cTask);
					ConfirmRetailPayRecordTask pTask = new ConfirmRetailPayRecordTask(
							info.getSaleEmpGuid());
					context.handle(pTask);

				}
			} finally {
				db.unuse();
			}

		}
	}

	/**
	 * 刪除
	 */
	@Publish
	protected class DeleteRetailReceiptHandler
			extends
			TaskMethodHandler<InternalRetailReceiptTask, InternalRetailReceiptTask.Method> {
		public DeleteRetailReceiptHandler() {
			super(InternalRetailReceiptTask.Method.DELETE);
		}

		@Override
		protected void handle(Context context, InternalRetailReceiptTask task)
				throws Throwable {
			ORMAccessor<RetailReceipt> acc = context
					.newORMAccessor(q_Orm_RetailReceipt);
			acc.delete(task.getRecid());
		}
	}

	/**
	 * 查找所有对象
	 */
	@Publish
	protected class AllRetailReceiptByKeyProvider extends
			OneKeyResultListProvider<RetailReceipt, RetailReceiptRecordKey> {

		@Override
		protected void provide(Context context,
				RetailReceiptRecordKey recordKey, List<RetailReceipt> list)
				throws Throwable {
			StringBuffer dnaSql = new StringBuffer();
			StringBuffer conditionSql = new StringBuffer();
			List<Object> paramList = new ArrayList<Object>();
			Login login = context.find(Login.class);
			Employee employee = context.find(Employee.class, login
					.getEmployeeId());
			dnaSql.append(" define query AllRetailReceiptByKeyProvider ");
			dnaSql.append("(");
			dnaSql.append("@tenantsGuid guid");
			conditionSql.append(" where t.tenantsGuid=@tenantsGuid \n");
			conditionSql.append(" and (t.shouldMoney<>0 or t.shouldCardMoney<>0)\n");
			paramList.add(login.getTenantId());
			
			if (!isFinance(context)) { //财务可以查看所有数据，非财务要带条件
				if (isEmployee(context)) {
					dnaSql.append(",@saleEmpGuid guid");
					conditionSql.append(" and t.saleEmpGuid=@saleEmpGuid\n");
					paramList.add(login.getEmployeeId());
				}
				if (isManager(context)) {
	
					List<GUID> departMentIds = getDepartMentIds(context, employee
							.getDepartmentId());
	
					conditionSql.append(" and t.deptGuid in (");
					for (int i = 0; i < departMentIds.size(); i++) {
						dnaSql.append(",@deptGuid").append(i).append(" guid");
						if (0 != i) {
							conditionSql.append(",");
						}
						conditionSql.append("@deptGuid").append(i);
						paramList.add(departMentIds.get(i));
					}
					conditionSql.append(")\n");
				}
			}
			if (null != recordKey.getSearchText()) {
				dnaSql.append(",@searchText string");
				conditionSql
						.append(" and t.saleEmpName like '%'+@searchText+'%' ");
				paramList.add(recordKey.getSearchText());
			}

			dnaSql.append(") ");
			dnaSql.append(" begin ");
			dnaSql.append(" select ");
			dnaSql.append(" t.RECID as RECID, ");
			dnaSql.append(" t.deptGuid as deptGuid, ");
			dnaSql.append(" t.receiptDate as receiptDate, ");
			dnaSql.append(" t.retailGuid as retailGuid, ");
			dnaSql.append(" t.saleEmpGuid as saleEmpGuid, ");
			dnaSql.append(" t.saleEmpName as saleEmpName, ");
			dnaSql.append(" t.shouldCardCount as shouldCardCount, ");
			dnaSql.append(" t.shouldCardMoney as shouldCardMoney, ");
			dnaSql.append(" t.shouldMoney as shouldMoney, ");
			dnaSql.append(" t.tenantsGuid as tenantsGuid ");
			dnaSql.append(" from SA_FINANCE_RECEIPT_RETAIL as t \n");

			dnaSql.append(conditionSql);

			dnaSql.append(getOrderBySql(recordKey));
			DBCommand db = context.prepareStatement(dnaSql);
			for (int i = 0; i < paramList.size(); i++) {
				db.setArgumentValue(i, paramList.get(i));
			}
			RecordSet rs = db.executeQueryLimit(recordKey.getOffset(),
					recordKey.getCount());
			RetailReceipt retailReceipt;
			while (rs.next()) {
				retailReceipt = new RetailReceipt();
				retailReceipt.setRECID(rs.getFields().get(0).getGUID());
				retailReceipt.setDeptGuid(rs.getFields().get(1).getGUID());
				retailReceipt.setReceiptDate(rs.getFields().get(2).getDate());
				retailReceipt.setRetailGuid(rs.getFields().get(3).getGUID());
				retailReceipt.setSaleEmpGuid(rs.getFields().get(4).getGUID());
				retailReceipt.setSaleEmpName(rs.getFields().get(5).getString());
				retailReceipt
						.setShouldCardCount(rs.getFields().get(6).getInt());
				retailReceipt.setShouldCardMoney(rs.getFields().get(7)
						.getDouble());
				retailReceipt.setShouldMoney(rs.getFields().get(8).getDouble());
				retailReceipt.setTenantsGuid(rs.getFields().get(9).getGUID());
				list.add(retailReceipt);
			}
		}
	}

	/**
	 * 得到排序sql
	 * 
	 * @param key
	 * @return StringBuilder
	 */
	private String getOrderBySql(RetailReceiptRecordKey recordKey) {
		StringBuilder dnaSql = new StringBuilder();
		if (CheckIsNull.isNotEmpty(recordKey.getSortField())) {
			dnaSql.append(" order by t.");
			dnaSql.append(recordKey.getSortField());
			dnaSql.append("  ");
			dnaSql.append(recordKey.getSortType());
			dnaSql.append("  ");
		} else {
			dnaSql.append(" order by t.receiptDate desc ");
		}
		dnaSql.append(" end ");
		return dnaSql.toString();
	}

	public boolean isManager(Context context) {
		return !context.find(Boolean.class, Auth.Boss)
				&& context.find(Boolean.class, Auth.SalesManager);
	}

	public boolean isEmployee(Context context) {
		return !context.find(Boolean.class, Auth.Boss)
				&& !context.find(Boolean.class, Auth.SalesManager)
				&& context.find(Boolean.class, Auth.Sales);
	}
	
	public boolean isFinance(Context context) {
		if (context.find(Boolean.class, Auth.Account) || context.find(Boolean.class, Auth.AccountManager)) {
			return true;
		} else {
			return false;
		}
	}

	public List<GUID> getDepartMentIds(Context context, GUID departmentId) {
		List<GUID> list = new ArrayList<GUID>();
		list.add(departmentId);
		Department department = context.find(Department.class, departmentId);
		Department[] departments = department.getChildren(context);
		if (departments.length > 0) {
			for (Department dept : departments) {
				list.add(dept.getId());
			}
		}
		return list;
	}

}
