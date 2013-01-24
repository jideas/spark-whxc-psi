package com.spark.psi.base.internal.service;

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
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.DeleteSqlBuilder;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.BalanceAmount;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.event.CustomerCreateEvent;
import com.spark.psi.base.event.CustomerCreditReduceEvent;
import com.spark.psi.base.event.CustomerDataChangeEvent;
import com.spark.psi.base.internal.entity.helper.PartnerHelper;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetCustomerAvailableCreditAmountKey;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.base.key.RemindingAmountKey;
import com.spark.psi.base.publicimpl.CustomerInfoImpl;
import com.spark.psi.base.publicimpl.PartnerItemImpl;
import com.spark.psi.base.task.resource.UpdatePartnerResourceTask;
import com.spark.psi.base.utils.QueryTableColumnUtil;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.QueryScope.ScopeType;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.CustomerItem;
import com.spark.psi.publish.base.partner.entity.CustomerOverCredit;
import com.spark.psi.publish.base.partner.entity.CustomerShortItem;
import com.spark.psi.publish.base.partner.key.FindCustomerCountKey;
import com.spark.psi.publish.base.partner.key.GetCreditAmountUsableKey;
import com.spark.psi.publish.base.partner.key.GetCustomerListKey;
import com.spark.psi.publish.base.partner.key.GetCustomerOverCreditDayKey;
import com.spark.psi.publish.base.partner.key.GetNewCustomerNoKey;
import com.spark.psi.publish.base.partner.key.GetShortCustomerListKey;
import com.spark.psi.publish.base.partner.task.DeleteCustomerTask;
import com.spark.psi.publish.base.partner.task.UpdateCustomerCreditTask;
import com.spark.psi.publish.base.partner.task.UpdateCustomerSalesManTask;
import com.spark.psi.publish.base.partner.task.UpdateCustomerTask;
import com.spark.psi.publish.base.partner.task.UpdatePartnerTask;
import com.spark.psi.publish.base.partner.task.ValidatePartnerNameTask;

public class CustomerPublishERPService extends Service {

	protected CustomerPublishERPService() {
		super("com.spark.psi.base.internal.service.CustomerPublishERPService");
	}

	protected static String LockedFlag = "locked";

	/**
	 * 获得客户维护对象
	 */
	@Publish
	protected class GetCustomerInfoByIdProvider extends OneKeyResultProvider<CustomerInfo, GUID> {

		@Override
		protected CustomerInfo provide(Context context, GUID key) throws Throwable {
			CustomerInfoImpl info = null;
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Customer.getTableName(), "t");
			QueryTableColumnUtil.setCustomerInfoColumn(qb);
			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				info = QueryTableColumnUtil.getCustomerInfo(context, rs);
			}
			return info;
		}
	}

	/**
	 * 选择客户小界面 列表
	 */
	@Publish
	protected final class GetCustomerShortItemProvider extends
			OneKeyResultProvider<ListEntity<CustomerShortItem>, GetShortCustomerListKey> {

		@Override
		protected ListEntity<CustomerShortItem> provide(Context context, GetShortCustomerListKey key) throws Throwable {
			List<CustomerShortItem> resultList = new ArrayList<CustomerShortItem>();
			final String searchText = key.getSearchText();
			List<Partner> list = context.getList(Partner.class, PartnerType.Customer);
			for (Partner item : list) {
				if (!key.isHasRetail() && item.getId().equals(Partner.RetailCustomerId)) {
					continue;
				}
				if (!checkAuth(context, item)) {
					continue;
				}
				if (CheckIsNull.isEmpty(searchText)) {
					resultList.add(PartnerHelper.partnerToShortItem(item));
					continue;
				}
				boolean b = false;
				if (item.getAddress() != null && item.getAddress().indexOf(searchText) > -1) {
					b = true;
				}
				b = b || item.getName().indexOf(searchText) > -1 || item.getShortName().indexOf(searchText) > -1;
				if (b) {
					resultList.add(PartnerHelper.partnerToShortItem(item));
				}
			}
			return new ListEntity<CustomerShortItem>(resultList, resultList.size());

		}

		private boolean checkAuth(Context context, Partner partner) {
			Employee emp = context.find(Employee.class);
			// if(context.find(Auth.class,))
			// 判断当前用户是否是总经理或者财务 ，如果符合条件，则可以看到所有客户
			if (emp.hasAuth(Auth.Boss) || emp.hasAuth(Auth.Account)) {
				return true;
			}
			// 判断当前用户是否是销售经理 如果是销售经理可以看到自己和自己部门内的员工的客户
			if (emp.hasAuth(Auth.SalesManager)) {
				if (partner.getBusinessPerson() == null)
					return true;
				List<Employee> emps = context.getList(Employee.class, new GetChildrenDeptEmployeeListByAuthKey(emp
						.getDepartmentId(), Auth.Sales));
				for (Employee employee : emps) {
					if (employee.getId().equals(partner.getBusinessPerson().getId())) {
						return true;
					}
				}
			}
			// 如果是销售员工 则只能看到属于自己的
			if (emp.hasAuth(Auth.Sales)) {
				if (partner.getBusinessPerson() == null)
					return false;
				return emp.getId().equals(partner.getBusinessPerson().getId());
			}
			return false;
		}

	}

	/**
	 * 查询客户列表
	 */
	@Publish
	protected class GetCustomerItemListProvider extends
			OneKeyResultProvider<ListEntity<CustomerItem>, GetCustomerListKey> {

		@Override
		protected ListEntity<CustomerItem> provide(Context context, final GetCustomerListKey key) throws Throwable {
			int getType = 0;
			Employee emp = context.find(Employee.class);
			if (!emp.hasAuth(Auth.Boss, Auth.Sales, Auth.Account) && !key.isShowAll()) {
				// 如果没有总经理、财务、销售的智能，没有查询客户列表的权限
				return null;
			}
			if (emp.hasAuth(Auth.Boss, Auth.Assistant, Auth.Account) || key.isShowAll()) {
				// 如果职能为总经理或者财务，则可以看所有的
				getType = 1;
			} else if (emp.hasAuth(Auth.SalesManager)) {
				// 经理只能看自己及下属负责的和共享的的
				getType = 2;
			} else {
				getType = 3;
			}
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Customer.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.customerName", "customerName");
			qb.addColumn("t.shortName", "shortName");
			qb.addColumn("t.customerNo", "customerNo");
			qb.addColumn("t.accountPeriod", "accountPeriod");
			qb.addColumn("t.accountRemind", "accountRemind");
			qb.addColumn("t.creditAmount", "creditAmount");
			qb.addColumn("t.businessPerson", "businessPerson");
			qb.addColumn("t.canDelete", "canDelete");
			qb.addColumn("t.totalSalesAmount", "totalSalesAmount");
			qb.addColumn("t.lastSalesDate", "lastSalesDate");
			qb.addColumn("t.totalSalesTimes", "totalSalesTimes");
			qb.addColumn("t.linkmanName", "linkmanName");
			qb.addColumn("t.linkmanTel", "linkmanTel");
			qb.addColumn("t.linkmanMobile", "linkmanMobile");
			qb.addColumn("t.linkmanEmail", "linkmanEmail");
			qb.addColumn("t.province", "province");
			qb.addColumn("t.city", "city");
			qb.addColumn("t.town", "town");
			qb.addColumn("t.address", "adress");
			if (key.getPartnerStatus() != null) {
				qb.addArgs("statusValue", qb.STRING, key.getPartnerStatus().getCode());
				qb.addEquals("t.status", "@statusValue");
			}
			switch (getType) {
			case 2:
				StringBuilder ss = new StringBuilder(" (t.businessPerson is null or t.deptId in (");
				Department dept = context.find(Department.class);
				int i = 0;
				qb.addArgs("deptId" + i, qb.guid, dept.getId());
				ss.append("@deptId" + i++);
				for (Department dep : dept.getDescendants(context, Auth.Sales)) {
					qb.addArgs("deptId" + i, qb.guid, dep.getId());
					ss.append(",@deptId" + i++);
				}
				ss.append(")) ");
				qb.addCondition(ss.toString());
				break;
			case 3:
				qb.addArgs("personId", qb.guid, emp.getId());
				qb.addEquals("t.businessPerson", "@personId");
				break;
			default:
				break;
			}
			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				StringBuilder ss = new StringBuilder(" (");
				qb.addArgs("text", qb.STRING, key.getSearchText());
				ss.append("t.customerName like '%'+@text+'%' ");
				ss.append(" or t.shortName like '%'+@text+'%' ");
				ss.append(" or t.linkmanName like '%'+@text+'%' ");
				// ss.append(" or t.customerNo like '%'+@text+'%' ");
				ss.append(" or t.businessPerson like '%'+@text+'%' ");
				ss.append(")");
				qb.addCondition(ss.toString());
			}
			if (key.getQueryScope() != null) {
				if (key.getQueryScope().getType() == ScopeType.Mine) {
					qb.addArgs("userId", qb.guid, emp.getId());
					qb.addEquals("t.businessPerson", "@userId");
				} else if (key.getQueryScope().getType() == ScopeType.Department && key.getQueryScope().isRecursive()) {
					Department dep = context.find(Department.class, key.getQueryScope().getDepartmentId());
					List<String> args = new ArrayList<String>();
					int index = 0;
					for (Department d : dep.getDescendants(context, Auth.Sales)) {
						args.add("@depId" + index);
						qb.addArgs("depId" + index++, qb.guid, d.getId());
					}
					qb.addIn("t.deptId", args);
				} else if (key.getQueryScope().getType() == ScopeType.Department) {
					qb.addArgs("depId", qb.guid, emp.getDepartmentId());
					qb.addEquals("t.deptId", "@depId");
				}
			}

			if (key.getSortField() != GetCustomerListKey.SortField.None) {
				if (key.getSortField() == GetCustomerListKey.SortField.Address) {
					if (key.getSortType() == SortType.Desc) {
						qb.addOrderBy(key.getSortField().getFieldName() + " desc ");
					} else {
						qb.addOrderBy(key.getSortField().getFieldName() + " asc ");
					}
				} else if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " asc ");
				}
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<CustomerItem> resultList = new ArrayList<CustomerItem>();
			while (rs.next()) {
				int i = 0;
				PartnerItemImpl entity = new PartnerItemImpl();
				GUID id = rs.getFields().get(i++).getGUID();
				entity.setId(id);
				entity.setName(rs.getFields().get(i++).getString());
				entity.setShortName(rs.getFields().get(i++).getString());
				entity.setPartnerNo(rs.getFields().get(i++).getString());
				entity.setCreditDay(rs.getFields().get(i++).getInt());
				entity.setRemindDay(rs.getFields().get(i++).getInt());
				entity.setCreditAmount(rs.getFields().get(i++).getDouble());
				entity.setSalesmanId(rs.getFields().get(i++).getGUID());
				entity.setUsed(!rs.getFields().get(i++).getBoolean());
				if (entity.getSalesmanId() != null) {
					entity.setSalesmanName(context.find(Employee.class, entity.getSalesmanId()).getName());
				} else {
					entity.setSalesmanName("共享客户");
				}
				entity.setTradeTotalAmount(rs.getFields().get(i++).getDouble());
				entity.setRecentTradeDate(rs.getFields().get(i++).getDate());
				entity.setTradeTotalCount(rs.getFields().get(i++).getInt());
				entity.setContactName(rs.getFields().get(i++).getString());
				entity.setContactMobileNo(rs.getFields().get(i++).getString());
				entity.setContactLandLineNumber(rs.getFields().get(i++).getString());
				entity.setContactEmail(rs.getFields().get(i++).getString());
				entity.setProvince(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName());
				entity.setCity(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName());
				entity.setAddress(context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName()
						+ rs.getFields().get(i++).getString());

				// 应收应付金额
				BalanceAmount balanceAmount = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(id));
				if (balanceAmount != null)
					entity.setBalanceAmount(balanceAmount.getDueAmount());
				else {
					entity.setBalanceAmount(0d);
				}

				// Long orderDate = // 最早一笔未付款已出库订单的出库日期
				// context.find(Long.class, new
				// GetRemindDateByPartnerIdKey(id));
				// orderDate = orderDate == null ? 0 : orderDate;
				// if (orderDate > 0) {
				// // 计算是否已超账期 计算公式: (当前日期 减去 最早一张已出库未付款的出库单的确认出库日期) 转换成天数 减去
				// // 账期天数 ,如果结果大于0,则表示已超账期
				// // 延伸注释 ： 已超账期金额 等于 所有 出库日期小于（之前）当前日期减去账期天数的已出库未付款的出库单的金额合计。
				// boolean creditExpired = false;
				// Calendar orderDateCal = Calendar.getInstance();
				// orderDateCal.setTimeInMillis(orderDate);
				// orderDateCal.add(Calendar.DATE, entity.getAccountPeriod());
				// creditExpired =
				// orderDateCal.compareTo(Calendar.getInstance()) == -1;
				// entity.setCreditExpired(creditExpired);
				// // 帐期预警计算公式 帐期天数-（最早一笔未付款订单的日期与今天的天数差） 如果大于预警天数则需要预警
				// entity
				// .setCreditTowards(entity.getAccountPeriod()
				// - DateUtil.getDaysBetween(new Date(orderDate), new Date()) <=
				// entity.getRemindDay());
				// } else {
				entity.setCreditExpired(false);
				entity.setCreditTowards(false);
				// }
				resultList.add(entity);
			}
			return new ListEntity<CustomerItem>(resultList, resultList.size());
		}
	}

	/**
	 * 修改客户信用额度
	 */
	@Publish
	protected class UpdateCustomerCreditHandler extends SimpleTaskMethodHandler<UpdateCustomerCreditTask> {

		@Override
		protected void handle(Context context, UpdateCustomerCreditTask task) throws Throwable {
			for (GUID customer : task.getCustomers()) {
				CustomerInfo entity = context.find(CustomerInfo.class, customer);
				if (entity.getCreditAmount() > task.getCreditAmount()
						|| entity.getAccountPeriod() > task.getAccountPeriod()) { // 如果客户的信用额度或者帐期相对减少，则抛出客户信用额度减少事件，将客户的销售订单打回待审批状态
					context.dispatch(new CustomerCreditReduceEvent(entity.getId()));
				}
				this.update(context, task);
				context.handle(new UpdatePartnerResourceTask(customer), UpdatePartnerResourceTask.Method.Modify);
				context.dispatch(new CustomerDataChangeEvent(entity.getId())); // 触发客户改变事件
			}
		}

		private void update(Context context, UpdateCustomerCreditTask task) {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.Customer.getTableName());
			ub.addColumn("creditAmount", ub.DOUBLE, task.getCreditAmount());
			ub.addColumn("accountPeriod", ub.INT, task.getAccountPeriod());
			ub.addColumn("accountRemind", ub.INT, task.getRemindDay());
			ub.addIn("RECID", ub.guid, task.getCustomers());
			ub.execute();
		}
	}

	/**
	 * 修改客户的销售员工
	 */
	@Publish
	protected class UpdateCustomerSalesManHandler extends SimpleTaskMethodHandler<UpdateCustomerSalesManTask> {

		@Override
		protected void handle(Context context, UpdateCustomerSalesManTask task) throws Throwable {
			PartnerModuleERPService.changeCustomerBusPerson(context, task.getCustomerId(), task.getEmployeeId());
		}
	}

	/**
	 * 创建一个客户
	 */
	@Publish
	protected class CreateCustomerHandler extends TaskMethodHandler<UpdateCustomerTask, UpdatePartnerTask.Method> {

		protected CreateCustomerHandler() {
			super(UpdatePartnerTask.Method.CREATE);
		}

		@Override
		protected void handle(Context context, UpdateCustomerTask task) throws Throwable {
			task.setId(context.newRECID());
			doSave(context, task);
			context.dispatch(new CustomerCreateEvent(task.getId()));
		}

		private void doSave(Context context, UpdateCustomerTask task) {
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.Customer.getTableName());
			ib.addColumn("RECID", ib.guid, task.getId());
			ib.addColumn("customerName", ib.STRING, task.getName());
			ib.addColumn("namePY", ib.STRING, PinyinHelper.getLetter(task.getName()));
			ib.addColumn("shortName", ib.STRING, task.getShortName());
			ib.addColumn("status", ib.STRING, PartnerStatus.Potential.getCode());
			ib.addColumn("province", ib.STRING, task.getProvince());
			ib.addColumn("city", ib.STRING, task.getCity());
			ib.addColumn("town", ib.STRING, task.getTown());
			ib.addColumn("address", ib.STRING, task.getAddress());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			ib.addColumn("postcode", ib.STRING, task.getPostcode());
			ib.addColumn("taxRate", ib.DOUBLE, task.getTaxRate());
			ib.addColumn("totalSalesAmount", ib.DOUBLE, 0);
			ib.addColumn("lastSalesDate", ib.DATE, 0);
			ib.addColumn("totalSalesTimes", ib.INT, 0);
			ib.addColumn("workTel", ib.STRING, task.getWorkTel());
			ib.addColumn("fax", ib.STRING, task.getFax());
			ib.addColumn("creditAmount", ib.DOUBLE, task.getCreditAmount());
			ib.addColumn("accountPeriod", ib.INT, task.getAccountPeriod());
			ib.addColumn("accountRemind", ib.INT, task.getAccountRemind());
			ib.addColumn("pricePolicy", ib.STRING, task.getPricePolicy());
			ib.addColumn("linkmanName", ib.STRING, task.getLinkmanName());
			ib.addColumn("linkmanSuffix", ib.STRING, task.getLinkmanSuffix());
			ib.addColumn("linkmanNamePY", ib.STRING, PinyinHelper.getLetter(task.getLinkmanName()));
			ib.addColumn("linkmanTel", ib.STRING, task.getLinkmanTel());
			ib.addColumn("linkmanMobile", ib.STRING, task.getLinkmanMobile());
			ib.addColumn("linkmanEmail", ib.STRING, task.getLinkmanEmail());
			ib.addColumn("canDelete", ib.BOOLEAN, true);
			ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
			ib.addColumn("creatorId", ib.guid, emp.getId());
			ib.addColumn("creator", ib.STRING, emp.getName());
			ib.addColumn("customerType", ib.STRING, task.getCustomerType());
			ib.addColumn("taxNumber", ib.STRING, task.getTaxNumber());
			if (login.hasAuth(Auth.Sales) && !login.hasAuth(Auth.Boss, Auth.SalesManager)) {
				ib.addColumn("deptId", ib.guid, emp.getDepartmentId());
				ib.addColumn("businessPerson", ib.STRING, emp.getId());
			}
			synchronized (LockedFlag) {
				task.setPartnerNo(context.find(String.class, new GetNewCustomerNoKey(task.getTown())));
				ib.addColumn("customerNo", ib.STRING, task.getPartnerNo());
				ib.execute();
			}
			context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Put);
		}
	}

	/**
	 * 修改客户基础信息
	 */
	@Publish
	protected class UpdateCustomerHandler extends TaskMethodHandler<UpdateCustomerTask, UpdatePartnerTask.Method> {

		protected UpdateCustomerHandler() {
			super(UpdatePartnerTask.Method.UPDATE);
		}

		@Override
		protected void handle(Context context, UpdateCustomerTask task) throws Throwable {
			doUpdate(context, task);
			context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Modify);
			context.dispatch(new CustomerDataChangeEvent(task.getId())); // 触发客户改变事件
		}

		private void doUpdate(Context context, UpdateCustomerTask task) {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.Customer.getTableName());
			ub.addColumn("customerName", ub.STRING, task.getName());
			ub.addColumn("namePY", ub.STRING, PinyinHelper.getLetter(task.getName()));
			ub.addColumn("shortName", ub.STRING, task.getShortName());
			ub.addColumn("creditAmount", ub.DOUBLE, task.getCreditAmount());
			// ub.addColumn("customerNo", ub.STRING, task.getPartnerNo());
			// ub.addColumn("status", ub.STRING, task.getStatus().getCode());
			ub.addColumn("province", ub.STRING, task.getProvince());
			ub.addColumn("city", ub.STRING, task.getCity());
			ub.addColumn("town", ub.STRING, task.getTown());
			ub.addColumn("address", ub.STRING, task.getAddress());
			ub.addColumn("remark", ub.STRING, task.getRemark());
			ub.addColumn("postcode", ub.STRING, task.getPostcode());
			ub.addColumn("taxRate", ub.DOUBLE, task.getTaxRate());
			// ub.addColumn("totalSalesAmount", ub.DOUBLE, 0);
			ub.addColumn("taxNumber", ub.STRING, task.getTaxNumber());
			ub.addColumn("customerType", ub.STRING, task.getCustomerType());
			ub.addColumn("workTel", ub.STRING, task.getWorkTel());
			ub.addColumn("fax", ub.STRING, task.getFax());
			ub.addColumn("accountPeriod", ub.INT, task.getAccountPeriod());
			ub.addColumn("accountRemind", ub.INT, task.getAccountRemind());
			ub.addColumn("pricePolicy", ub.STRING, task.getPricePolicy());
			ub.addColumn("linkmanName", ub.STRING, task.getLinkmanName());
			ub.addColumn("linkmanSuffix", ub.STRING, task.getLinkmanSuffix());
			ub.addColumn("linkmanNamePY", ub.STRING, PinyinHelper.getLetter(task.getLinkmanName()));
			ub.addColumn("linkmanTel", ub.STRING, task.getLinkmanTel());
			ub.addColumn("linkmanMobile", ub.STRING, task.getLinkmanMobile());
			ub.addColumn("linkmanEmail", ub.STRING, task.getLinkmanEmail());
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID = @id");
			ub.execute();
		}
	}

	/**
	 * 校验客户全称是否是唯一的
	 */
	@Publish
	protected class ValidateCustomerNameIsOnlyHandler extends
			TaskMethodHandler<ValidatePartnerNameTask, ValidatePartnerNameTask.Method> {

		protected ValidateCustomerNameIsOnlyHandler() {
			super(ValidatePartnerNameTask.Method.CustomerName);
		}

		@Override
		protected void handle(Context context, ValidatePartnerNameTask task) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Customer.getTableName(), "t");
			qb.addColumn("count(1)", "ccc");
			qb.addArgs("id", "guid", task.getId());
			qb.addArgs("name", qb.STRING, task.getName());
			qb.addNotEquals("t.RECID", "@id");
			qb.addEquals("t.customerName", "@name");
			RecordSet rs = qb.getRecord();
			rs.next();
			if (rs.getFields().get(0).getInt() > 0) {
				task.setExist(true);
			} else {
				task.setExist(false);
			}
		}
	}

	/**
	 * 校验客户简称是否是唯一的
	 */
	@Publish
	protected class ValidateCustomerShortNameIsOnlyHandler extends
			TaskMethodHandler<ValidatePartnerNameTask, ValidatePartnerNameTask.Method> {

		protected ValidateCustomerShortNameIsOnlyHandler() {
			super(ValidatePartnerNameTask.Method.CustomerShortName);
		}

		@Override
		protected void handle(Context context, ValidatePartnerNameTask task) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Customer.getTableName(), "t");
			qb.addColumn("count(1)", "ccc");
			qb.addArgs("id", "guid", task.getId());
			qb.addArgs("name", qb.STRING, task.getName());
			qb.addNotEquals("t.RECID", "@id");
			qb.addEquals("t.shortName", "@name");
			RecordSet rs = qb.getRecord();
			rs.next();
			if (rs.getFields().get(0).getInt() > 0) {
				task.setExist(true);
			} else {
				task.setExist(false);
			}
		}
	}

	/**
	 * 查询客户已用信用额度
	 */
	@Publish
	protected final class GetCreditAmountUsableProvider extends OneKeyResultProvider<Double, GetCreditAmountUsableKey> {

		@Override
		protected Double provide(Context context, GetCreditAmountUsableKey key) throws Throwable {
			return context.find(Double.class, new GetCustomerAvailableCreditAmountKey(key.getId()));
		}

	}

	/**
	 * 查询客户已超帐期情况
	 */
	@Publish
	protected final class GetCustomerOverCreditProvider extends
			OneKeyResultProvider<CustomerOverCredit, GetCustomerOverCreditDayKey> {

		@Override
		protected CustomerOverCredit provide(final Context context, final GetCustomerOverCreditDayKey key)
				throws Throwable {
			final Partner p = context.find(Partner.class, key.getId());
			return new CustomerOverCredit() {

				public double getOverCreditAmount() {
					OverPeriodAmountKey overPeriodAmountKey = new OverPeriodAmountKey();
					overPeriodAmountKey.setPartnerId(key.getId());
					overPeriodAmountKey.setAccountPeriod(p.getAccountPeriod());
					Double result = context.find(Double.class, overPeriodAmountKey);
					return result == null ? 0 : result;
				}

				public double getApproachedCreditAmount() {
					RemindingAmountKey r = new RemindingAmountKey();
					r.setPartnerId(key.getId());
					r.setRemindingDay(p.getAccountRemind());
					r.setAccountPeriod(p.getAccountPeriod());
					Double result = context.find(Double.class, r);
					return result == null ? 0 : result;
				}
			};
		}
	}

	/**
	 * 查询客户数量
	 */
	@Publish
	protected final class FindCustomerCountProvider extends OneKeyResultProvider<Integer, FindCustomerCountKey> {

		/**
		 * 查询客户数量的SQL
		 */
		private String getCustomerCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_CustomerCount() ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from " + ERPTableNames.Base.Customer.getTableName() + " as t ");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindCustomerCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getCustomerCountSql());
			try {
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 得到客户最新编号
	 */
	@Publish
	protected class GetNewCustomerNoProvider extends OneKeyResultProvider<String, GetNewCustomerNoKey> {

		@Override
		protected String provide(Context context, GetNewCustomerNoKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			String number = null;
			qb.addTable(ERPTableNames.Base.Customer.getTableName(), "t");
			qb.addArgs("stown", qb.STRING, key.getTown().substring(0, 6));
			qb.addColumn("t.customerNo", "ccc");
			qb.addCondition("t.customerNo like @stown+'%'");
			qb.addOrderBy("t.createDate desc ");
			RecordSet rs = qb.getRecordLimit(0, 1);
			if (rs.next()) {
				String count = rs.getFields().get(0).getString();
				if (CheckIsNull.isNotEmpty(count)) {
					number = com.spark.common.utils.character.StringHelper.addOneInt(count, 10);
					return number;
				}
			}
			return key.getTown().substring(0, 6) + "0001";
		}

	}

	/**
	 * 删除供应商服务
	 */
	@Publish
	protected class DeleteCustomerHandler extends SimpleTaskMethodHandler<DeleteCustomerTask> {

		@Override
		protected void handle(Context context, DeleteCustomerTask task) throws Throwable {
			CustomerInfo info = context.find(CustomerInfo.class, task.getId());
			if (info.isUsed()) {
				throw new IllegalArgumentException(info.getShortName() + "已经被使用过,不能删除！");
			}
			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable(ERPTableNames.Base.Customer.getTableName());
			db.addEquals("RECID", db.guid, task.getId());
			db.addEquals("canDelete", db.BOOLEAN, true);
			int i = db.execute();
			if (i == 1) {
				context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Remove);

			}
		}
	}
}
