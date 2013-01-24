package com.spark.psi.account.service.invoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.psi.account.service.orm.Orm_Invoice;
import com.spark.psi.account.intf.entity.invoice.Invoice;
import com.spark.psi.account.intf.key.invoice.InvoiceKey;
import com.spark.psi.account.intf.task.invoice.InvoiceDaoTask;
import com.spark.psi.account.intf.util.invoice.SearchStringUtil;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.key.organization.GetChildrenDepartmentListKey;

/**
 * <p>
 * TODO 类描述
 * </p>
 */

public class InvoiceService extends Service {

	private final Orm_Invoice orm_Invoice;

	final static String invoiceTable = ERPTableNames.Account.Invoice.getTableName();
	protected InvoiceService(Orm_Invoice orm_Invoice) {
		super("InvoiceService");
		this.orm_Invoice = orm_Invoice;
	}

	/**
	 * 
	 * <p>
	 * 新增
	 * </p>
	 * 
	 */
	@Publish
	protected class AddInvoice extends TaskMethodHandler<InvoiceDaoTask,InvoiceDaoTask.Method > {

		protected AddInvoice() {
			super(InvoiceDaoTask.Method.INSERT);
		}

		@Override
		protected void handle(Context context, InvoiceDaoTask task)
				throws Throwable {
			ORMAccessor<Invoice> orm = context.newORMAccessor(orm_Invoice);
			Invoice invoice = task.getInvoice();
			try {
				orm.insert(invoice);
			} finally {
				orm.unuse();
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 修改状态
	 * </p>
	 * 
	 */
	@Publish
	protected class ModifyInvoicestatus extends
			TaskMethodHandler<InvoiceDaoTask, InvoiceDaoTask.Method> {

		protected ModifyInvoicestatus() {
			super(InvoiceDaoTask.Method.MODIFYstatus);
		}

		@Override
		protected void handle(Context context, InvoiceDaoTask task)
				throws Throwable {
			Invoice invoice = task.getInvoice();
			StringBuilder dnaSql = new StringBuilder();
			List<Object> paramValueList = new ArrayList<Object>();
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());

			dnaSql.append("define update modifystatus(\n");
//			dnaSql.append(" @tenantsGuid guid");
//			paramValueList.add(login.getTenantId());
			dnaSql.append("@deptGuid guid");
			paramValueList.add(user.getDepartmentId());
			dnaSql.append(",@recid guid");
			paramValueList.add(invoice.getRECID());
			dnaSql.append(",@invalidDate date");
			paramValueList.add(new Date());
			dnaSql.append(",@invalidPerson string");
			paramValueList.add(user.getName());
			dnaSql.append(",@invalidReason string");
			paramValueList.add(invoice.getInvalidReason().trim());
			dnaSql.append(")");

			dnaSql.append(" begin \n");
			dnaSql.append(" update ").append(invoiceTable).append(" as t\n");
			dnaSql.append(" set ");

			if (task.isInvalided()) {
				dnaSql.append("isInvalided=true");
				dnaSql.append(",invalidDate=@invalidDate");
				dnaSql.append(",invalidPerson=@invalidPerson");
				dnaSql.append(",invalidReason=@invalidReason");
			}
			dnaSql.append("\n");

			dnaSql.append(" where \n");
			dnaSql.append(" t.recid=@recid \n");
			dnaSql.append(" and t.isInvalided=false \n");

			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);
			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
			}
			try {
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * <p>
	 * 主列表数据查询
	 * </p>
	 * 
	 */
	@Publish
	protected class InvoiceMainProvide extends
			OneKeyResultListProvider<Invoice, InvoiceKey> {

		@Override
		protected void provide(Context context, InvoiceKey key,List<Invoice> list)
				throws Throwable {

			StringBuilder sql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			StringBuilder conditionSql = new StringBuilder();
			List<String> invoTypeList = new SearchStringUtil().getKeys(key
					.getSearchText());
			sql.append("define query queryInvoiceList(\n");

			addParamAndCondition(context, key, sql, paramList, conditionSql);

			sql.append(") begin \n");

			sql.append("select ");

			// sql.append("count(t.RECID) as c \n");
			// sql.append(",sum(t.invoAmount) as s \n");
			// sql.append(",min(t.createDate) as d \n");
			sql.append("t.RECID as RECID \n");// 行标识
//			sql.append(",t.tenantsGuid as tenantsGuid \n");// 租户GUID
			sql.append(",t.cusproGuid as cusproGuid \n");// 客户GUID
			sql.append(",t.cusproName as cusproName \n");// 客户简称
			sql.append(",t.cusproNamePY as cusproNamePY \n");// 客户简称拼音
			sql.append(",t.cusproFullName as cusproFullName \n");// 客户全称
			sql.append(",t.cusproFullNamePY as cusproFullNamePY \n");// 客户全称拼音
			sql.append(",t.invoType as invoType \n");// 发票类型
			sql.append(",t.invoCode as invoCode \n");// 发票号
			sql.append(",t.invoAmount as invoAmount \n");// 开票金额
			sql.append(",t.invoPerson as invoPerson \n");// 开票人GUID
			sql.append(",t.invoPersonName as invoPersonName \n");// 开票人
			sql.append(",t.invoDate as invoDate \n");// 开票日期
			sql.append(",t.isInvalided as isInvalided \n");// 是否已作废
			sql.append(",t.invalidReason as invalidReason \n");// 作废原因
			sql.append(",t.invalidPerson as invalidPerson \n");// 作废人
			sql.append(",t.invalidDate as invalidDate \n");// 作废日期
			sql.append(",t.createPerson as createPerson \n");// 创建人
			sql.append(",t.createDate as createDate \n");// 创建人
			sql.append(",t.deptGuid as deptGuid \n");

			sql.append(" from ").append(invoiceTable).append(" as t\n");

			sql.append(" where  \n");
			sql.append(conditionSql);

			/**
			 * 模糊搜索
			 */
			
			// 其他字段模糊匹配
			if (null != key.getSearchText()) {
				sql.append(" and (");
				String searchText = key.getSearchText().trim();
				sql.append(" t.cusproName like '%");
				sql.append(searchText);
				sql.append("%' ");
				sql.append(" or t.cusproNamePY like '%");
				sql.append(searchText);
				sql.append("%' ");
				sql.append(" or t.cusproFullName like '%");
				sql.append(searchText);
				sql.append("%' ");
				sql.append(" or t.cusproFullNamePY like '%");
				sql.append(searchText);
				sql.append("%' ");
				sql.append(" or t.invoPersonName like '%");
				sql.append(searchText);
				sql.append("%' ");
				sql.append(" or t.invoCode like '%");
				sql.append(searchText);
				sql.append("%' ");
				sql.append(" or t.createPerson like '%");
				sql.append(searchText);
				sql.append("%' ");
				// 类型匹配
				
				for (int i = 0; i < invoTypeList.size(); i++) {
					
					sql.append(" or t.invoType='");
					sql.append(invoTypeList.get(i));
					sql.append("' ");
				}
				
				sql.append(") \n");
			}
			if (null != key.getSortField()) {
				sql.append(" order by ");
				sql.append(key.getSortField());
				if (null != key.getSortType()) {
					sql.append(" ");
					sql.append(key.getSortType());
				}
			} else {
				sql.append(" order by t.createDate desc ");
			}

			sql.append(" end");
			DBCommand db = context.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				db.setArgumentValue(i, paramList.get(i));
			}
			try {
				RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
				while (rs.next()) {
					list.add(setData(rs));
				}

			} finally {
				db.unuse();
			}
		}
	}
	
	
	/**
	 * <p>
	 * 主列表统计值
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author Wangtiancai
	 * @version 2011-11-28
	 */
//	@Publish
//	protected class InvoiceMainCountProvide extends
//			OneKeyResultProvider<TotalEntity, SearchKey> {
//
//		@Override
//		protected TotalEntity provide(Context context, SearchKey key)
//				throws Throwable {
//
//			TotalEntity totalEntity = new TotalEntity();
//			StringBuilder sql = new StringBuilder();
//			List<Object> paramList = new ArrayList<Object>();
//			StringBuilder conditionSql = new StringBuilder();
//			List<String> invoTypeList = new SearchStringUtil().getKeys(key
//					.getSearchText());
//			List<FInvoice> bills = new ArrayList<FInvoice>();
//
//			sql.append("define query queryInvoiceList(\n");
//
//			addParamAndCondition(context, key, sql, paramList, conditionSql);
//
//			sql.append(") begin \n");
//
//			sql.append("select ");
//
//			sql.append("count(t.RECID) as c \n");
//			sql.append(",sum(t.invoAmount) as s \n");
//			
//			sql.append(" from sa_finance_invoice as t \n");
//
//			sql.append(" where  \n");
//			sql.append(conditionSql);
//
//			/**
//			 * 模糊搜索
//			 */
//			
//			// 其他字段模糊匹配
//			if (null != key.getSearchText()) {
//				sql.append(" and (");
//				String searchText = key.getSearchText();
//				sql.append(" t.cusproName like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				sql.append(" or t.cusproNamePY like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				sql.append(" or t.cusproFullName like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				sql.append(" or t.cusproFullNamePY like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				sql.append(" or t.invoPersonName like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				sql.append(" or t.invoCode like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				sql.append(" or t.createPerson like '%");
//				sql.append(searchText);
//				sql.append("%' ");
//				// 类型匹配
//				for (int i = 0; i < invoTypeList.size(); i++) {
//					
//					sql.append(" or t.invoType='");
//					sql.append(invoTypeList.get(i));
//					sql.append("' ");
//				}
//				sql.append(") \n");
//			}
//			if (null != key.getSortField()) {
//				sql.append(" order by ");
//				sql.append(key.getSortField());
//				if (null != key.getSortType()) {
//					sql.append(" ");
//					sql.append(key.getSortType());
//				}
//			} else {
//				sql.append(" order by t.createDate desc ");
//			}
//
//			sql.append(" end");
//			DBCommand db = context.prepareStatement(sql);
//			for (int i = 0; i < paramList.size(); i++) {
//				db.setArgumentValue(i, paramList.get(i));
//			}
//			try {
//				RecordSet rs = db.executeQuery();
//				while (rs.next()) {
//					totalEntity.setCount(rs.getFields().get(0).getInt());
//					totalEntity.setAmount(rs.getFields().get(1).getDouble());
//				}
//
//			} finally {
//				db.unuse();
//			}
//			return totalEntity;
//		}
//	}
	
	/**
	 * <p>
	 * 最近单据日期查询
	 * </p>
	 * 
	 */
	@Publish
	protected class InvoiceMainCreateDateProvide extends
			OneKeyResultProvider<Long, InvoiceKey> {

		@Override
		protected Long provide(Context context, InvoiceKey key)
				throws Throwable {

			StringBuilder sql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			StringBuilder conditionSql = new StringBuilder();

			sql.append("define query queryInvoiceList(\n");

			addParamAndCondition(context, key, sql, paramList, conditionSql);

			sql.append(") begin \n");

			sql.append("select ");

			sql.append("t.createDate as m \n");
			
			sql.append(" from ").append(invoiceTable).append(" as t\n");

			sql.append(" where  \n");
			sql.append(conditionSql);

			
				sql.append(" order by t.createDate desc ");
			

			sql.append(" end");
			DBCommand db = context.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				db.setArgumentValue(i, paramList.get(i));
			}
			try {
				RecordSet rs = db.executeQueryLimit(0, 1);
				while (rs.next()) {
					return rs.getFields().get(0).getDate();
				}
			} finally {
				db.unuse();
			}
			return null;
		}
	}
	

	/**
	 * 获取下级部门GUID
	 * 
	 * @param context
	 * 
	 * @param authDeptGuid
	 * @return List<GUID>
	 */
	private List<GUID> getSubDeptList(Context context, GUID authDeptGuid) {
		List<GUID> list = new ArrayList<GUID>();
		list.add(authDeptGuid);

//		GetDepartmentChildrenListByParentKey key = new GetDepartmentChildrenListByParentKey(authDeptGuid);
//		List<Department> dlist = context.getList(Department.class, key);
		List<Department> dlist = context.getList(Department.class,new GetChildrenDepartmentListKey(authDeptGuid));

		if (dlist != null) {
			for (Department dept : dlist) {
				list.add(dept.getId());
			}
		}
		return list;
	}

	public Invoice setData(RecordSet rs) {
		Invoice invo = new Invoice();
		int index = 0;
		invo.setRECID(rs.getFields().get(index++).getGUID());
//		invo.setTenantsGuid(rs.getFields().get(index++).getGUID());
		invo.setCusproGuid(rs.getFields().get(index++).getGUID());
		invo.setCusproName(rs.getFields().get(index++).getString());
		invo.setCusproNamePY(rs.getFields().get(index++).getString());
		invo.setCusproFullName(rs.getFields().get(index++).getString());
		invo.setCusproFullNamePY(rs.getFields().get(index++).getString());
		invo.setInvoType(rs.getFields().get(index++).getString());
		invo.setInvoCode(rs.getFields().get(index++).getString());
		invo.setInvoAmount(rs.getFields().get(index++).getDouble());
		invo.setInvoPerson(rs.getFields().get(index++).getGUID());
		invo.setInvoPersonName(rs.getFields().get(index++).getString());
		invo.setInvoDate(rs.getFields().get(index++).getDate());
		invo.setInvalided(rs.getFields().get(index++).getBoolean());
		invo.setInvalidReason(rs.getFields().get(index++).getString());
		invo.setInvalidPerson(rs.getFields().get(index++).getString());
		invo.setInvalidDate(rs.getFields().get(index++).getDate());
		invo.setCreatePerson(rs.getFields().get(index++).getString());
		invo.setCreateDate(rs.getFields().get(index++).getDate());
		invo.setDeptGuid(rs.getFields().get(index++).getGUID());
		return invo;
	}

	public void addParamAndCondition(Context context, InvoiceKey key,
			StringBuilder sql, List<Object> paramList,
			StringBuilder conditionSql) {

		Login login = context.find(Login.class);
		Employee user = context.find(Employee.class, login.getEmployeeId());

		List<GUID> subDeptList = getSubDeptList(context, user.getDepartmentId());
		List<GUID> deptList = null;
		if (null != key.getDeptGuid()
				&& !key.getDeptGuid().equals(login.getTenantId())) {
			deptList = getSubDeptList(context, key.getDeptGuid());
		}

//		sql.append("@tenantsGuid guid");
//		paramList.add(login.getTenantId());
		conditionSql.append(" 1=1 ");
		if(null!=key.getCusGuid())
		{
			sql.append("@cusProGuid guid,");
			paramList.add(key.getCusGuid());
			conditionSql.append(" and t.cusProGuid=@cusProGuid ");
		}
		sql.append("@startDate date");
		paramList.add(key.getStartDate());
		conditionSql.append(" and t.invoDate>=@startDate ");
		if (key.getEndDate() != 0) {
			sql.append(",@endDate date");
			paramList.add(key.getEndDate());
			conditionSql.append(" and t.invoDate<=@endDate ");
		}

		int index = 0;
		if(null==key.getCusGuid())
		{
			if(key.isFinanceRole())// 财务员工
			{
				sql.append(",@createPerson guid");
				conditionSql.append(" and t.createPerson=@createPerson ");
				paramList.add(user.getId());
			}
			else
			{
				conditionSql.append(" and ");
				if (subDeptList.size() > 1) {
					conditionSql.append("(");
				}
				for (int i = 0; i < subDeptList.size(); i++) {
					sql.append(",@deptGuid");
					sql.append(index);
					sql.append(" guid");
					paramList.add(subDeptList.get(i));
					if (i != 0) {
						conditionSql.append(" or ");
					}
					conditionSql.append(" t.deptGuid=@deptGuid");
					conditionSql.append(index);
		
					index++;
				}
				if (subDeptList.size() > 1) {
					conditionSql.append(") ");
				}
			}
		}

		/**
		 * 指定部门
		 */
		if (null == deptList) {
			return;
		}
		conditionSql.append(" and ");
		if (deptList.size() > 1) {
			conditionSql.append("(");
		}
		for (int i = 0; i < deptList.size(); i++) {
			sql.append(",@deptGuid");
			sql.append(index);
			sql.append(" guid");
			paramList.add(deptList.get(i));
			if (i != 0) {
				conditionSql.append(" or ");
			}
			conditionSql.append(" t.cusDeptGuid=@deptGuid");
			conditionSql.append(index);

			index++;
		}
		if (deptList.size() > 1) {
			conditionSql.append(") ");
		}

	}

	/**
	 * 
	 * 
	 * 根据拼音首字母模糊查询员工
	 * 
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author wangtiancai
	 * @version 2011-5-17
	 */
//	@Publish
//	protected class EmployeeTipContentProvider extends
//			OneKeyResultListProvider<FTipValue, EmployeeTipContentKey> {
//
//		@Override
//		protected void provide(Context context, EmployeeTipContentKey key,
//				List<FTipValue> list) throws Throwable {
//			FUser user = context.find(FUser.class, context.getLogin().getUser()
//					.getID());
//			Role[] roles = new Role[]{
//					Role.CWJL,Role.CWYG
//			};
//			GetAllChilderEmployeeForParentUserByRoleKey ekey = new GetAllChilderEmployeeForParentUserByRoleKey(user.getTenantsid(), roles);
//
////			GetEmployeeListForDeptIdKey ekey = new GetEmployeeListForDeptIdKey(user.getTenantsid(),roles);
////			GetEmployeeListForTenantsKey ekey = new GetEmployeeListForTenantsKey(
////					user.getTenantsid());
//			List<FEmployee> empList = context.getList(FEmployee.class, ekey);
//			try {
//				if (null == empList || empList.isEmpty()) {
//					return;
//				}
//				if (null != empList && !empList.isEmpty()) {
//					for (FEmployee emp : empList) {
//						FTipValue value;
//						if (emp != null) {
//							value = new FTipValue();
//							value.setKey(emp.getRecid());
//							value.setValue(emp.getEmpname());
//							list.add(value);
//						}
//					}
//				}
//
//			} finally {
//
//			}
//
//		}
//
//	}

}
