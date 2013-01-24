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
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.internal.entity.helper.PartnerHelper;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;
import com.spark.psi.base.publicimpl.CustomerInfoImpl;
import com.spark.psi.base.publicimpl.PartnerItemImpl;
import com.spark.psi.base.publicimpl.SupplierInfoImpl;
import com.spark.psi.base.task.resource.UpdatePartnerResourceTask;
import com.spark.psi.base.utils.QueryTableColumnUtil;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.RetailPartner;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.partner.entity.BankAccountItem;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.partner.entity.SupplierItem;
import com.spark.psi.publish.base.partner.entity.SupplierShortItem;
import com.spark.psi.publish.base.partner.key.FindProviderCountKey;
import com.spark.psi.publish.base.partner.key.GetNewSupplierNoKey;
import com.spark.psi.publish.base.partner.key.GetShortSupplierListKey;
import com.spark.psi.publish.base.partner.key.GetSupplierListKey;
import com.spark.psi.publish.base.partner.task.DeleteSupplierTask;
import com.spark.psi.publish.base.partner.task.ItemOfSupplier;
import com.spark.psi.publish.base.partner.task.MarkPartnerUsedTask;
import com.spark.psi.publish.base.partner.task.UpdatePartnerTask;
import com.spark.psi.publish.base.partner.task.UpdateSupplierTask;
import com.spark.psi.publish.base.partner.task.ValidatePartnerNameTask;
import com.spark.psi.publish.constant.SupplierCooperation;

public class SupplierPublishERPService extends Service {

	public SupplierPublishERPService() {
		super("com.spark.psi.base.internal.service.SupplierPublishERPService");
	}

	protected static String LockedFlag = "locked";

	/**
	 * 标识供应商已使用过
	 */
	@Publish
	protected final class MarkPartnerUsedHandler extends SimpleTaskMethodHandler<MarkPartnerUsedTask> {

		@Override
		protected void handle(Context context, MarkPartnerUsedTask task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			if (PartnerType.Customer == task.getType()) {
				ub.setTable(ERPTableNames.Base.Customer.getTableName());
			} else if (PartnerType.Supplier == task.getType()) {
				ub.setTable(ERPTableNames.Base.Supplier.getTableName());
			} else {
				ub.setTable(ERPTableNames.Base.Supplier.getTableName());
				context.handle(new MarkPartnerUsedTask(task.getId(), PartnerType.Customer));
			}
			ub.addColumn("canDelete", ub.BOOLEAN, false);
			ub.addCondition("ID", ub.guid, task.getId(), "t.RECID=@ID");
			int count = ub.execute();
			if (count == 1) {
				context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Modify);
			}
		}
	}

	/**
	 * 查询零售客户或零采供应商
	 */
	@Publish
	final protected class GetRetailPartnerProvider extends OneKeyResultProvider<PartnerInfo, RetailPartner> {

		@Override
		protected PartnerInfo provide(Context context, RetailPartner key) throws Throwable {

			if (key == RetailPartner.Customer) {
				CustomerInfoImpl entity = new CustomerInfoImpl();
				entity.setId(Partner.RetailCustomerId);
				entity.setName(Partner.RetailCustomerName);
				entity.setShortName(Partner.RetailCustomerName);
				entity.setPartnerType(PartnerType.Customer);
				return entity;
			} else {
				SupplierInfoImpl entity = new SupplierInfoImpl();
				entity.setId(Partner.RetailPurchaseSupplierId);
				entity.setName(Partner.RetailPurchaseSupplierName);
				entity.setShortName(Partner.RetailPurchaseSupplierName);
				entity.setPartnerType(PartnerType.Supplier);
				return entity;
			}

		}
	}

	/**
	 * 创建一个供应商
	 */
	@Publish
	protected class CreateSupplierHandler extends TaskMethodHandler<UpdateSupplierTask, UpdatePartnerTask.Method> {

		protected CreateSupplierHandler() {
			super(UpdatePartnerTask.Method.CREATE);
		}

		@Override
		protected void handle(Context context, UpdateSupplierTask task) throws Throwable {
			task.setId(context.newRECID());
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.Supplier.getTableName());
			ib.addColumn("RECID", ib.guid, task.getId());
			ib.addColumn("supplierName", ib.STRING, task.getName());
			ib.addColumn("namePY", ib.STRING, PinyinHelper.getLetter(task.getName()));
			ib.addColumn("shortName", ib.STRING, task.getShortName());
			ib.addColumn("supplierType", ib.STRING, task.getType());
			ib.addColumn("vatNo", ib.STRING, task.getVatNo());
			ib.addColumn("taxRate", ib.DOUBLE, task.getTaxRate());
			ib.addColumn("province", ib.STRING, task.getProvince());
			ib.addColumn("city", ib.STRING, task.getCity());
			ib.addColumn("town", ib.STRING, task.getTown());
			ib.addColumn("address", ib.STRING, task.getAddress());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			ib.addColumn("postcode", ib.STRING, task.getPostcode());
			ib.addColumn("totalPurchaseAmount", ib.DOUBLE, 0);
			ib.addColumn("lastPurchaseDate", ib.DATE, 0);
			ib.addColumn("totalPurchaseTimes", ib.INT, 0);
			ib.addColumn("noticeAddress", ib.STRING, task.getNoticeAddress());
			ib.addColumn("noticePostcode", ib.STRING, task.getNoticePostcode());
			ib.addColumn("workTel", ib.STRING, task.getWorkTel());
			ib.addColumn("fax", ib.STRING, task.getFax());
			ib.addColumn("accountPeriod", ib.INT, task.getAccountPeriod());
			ib.addColumn("accountRemind", ib.INT, task.getAccountRemind());
			ib.addColumn("linkmanName", ib.STRING, task.getLinkmanName());
			ib.addColumn("linkmanNamePY", ib.STRING, PinyinHelper.getLetter(task.getLinkmanName()));
			ib.addColumn("linkmanSuffix", ib.STRING, task.getLinkmanSuffix());
			ib.addColumn("linkmanTel", ib.STRING, task.getLinkmanTel());
			ib.addColumn("linkmanMobile", ib.STRING, task.getLinkmanMobile());
			ib.addColumn("linkmanEmail", ib.STRING, task.getLinkmanEmail());
			ib.addColumn("canDelete", ib.BOOLEAN, true);
			ib.addColumn("creditAmount", ib.DOUBLE, task.getCreditAmount());
			ib.addColumn("createDate", ib.DATE, System.currentTimeMillis());
			ib.addColumn("cooperation", ib.STRING, task.getSupplierCooperation());
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			ib.addColumn("creatorId", ib.guid, emp.getId());
			ib.addColumn("creator", ib.STRING, emp.getName());
			synchronized (LockedFlag) {
				String number = context.find(String.class, new GetNewSupplierNoKey(task.getType()));
				task.setPartnerNo(number);
				ib.addColumn("supplierNo", ib.STRING, task.getPartnerNo());
				int i = ib.execute();
				if (i > 0) {
					for (ItemOfSupplier item : task.getItems()) {
						if (CheckIsNull.isEmpty(item.getAccount()) || CheckIsNull.isEmpty(item.getAccountHolder())
								|| CheckIsNull.isEmpty(item.getBankName())) {
							continue;
						}
						InsertSqlBuilder insert = new InsertSqlBuilder(context);
						insert.setTable(ERPTableNames.Base.BankAccount.getTableName());
						insert.addColumn("RECID", insert.guid, item.getId());
						insert.addColumn("supplierId", insert.guid, task.getId());
						insert.addColumn("bankName", insert.STRING, item.getBankName());
						insert.addColumn("account", insert.STRING, item.getAccount());
						insert.addColumn("accountHolder", insert.STRING, item.getAccountHolder());
						insert.addColumn("remark", insert.STRING, item.getRemark());
						insert.execute();
					}
				}
			}
			context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Put);
		}
	}

	/**
	 * 修改供应商基础信息
	 */
	@Publish
	protected class UpdateSupplierHandler extends TaskMethodHandler<UpdateSupplierTask, UpdatePartnerTask.Method> {

		protected UpdateSupplierHandler() {
			super(UpdatePartnerTask.Method.UPDATE);
		}

		@Override
		protected void handle(Context context, UpdateSupplierTask task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.Supplier.getTableName());
			ub.addColumn("supplierName", ub.STRING, task.getName());
			ub.addColumn("shortName", ub.STRING, task.getShortName());
			ub.addColumn("namePY", ub.STRING, PinyinHelper.getLetter(task.getName()));
			ub.addColumn("address", ub.STRING, task.getAddress());
			ub.addColumn("accountPeriod", ub.INT, task.getAccountPeriod());
			ub.addColumn("accountRemind", ub.INT, task.getAccountRemind());
			ub.addColumn("city", ub.STRING, task.getCity());
			ub.addColumn("postcode", ub.STRING, task.getPostcode());
			ub.addColumn("town", ub.STRING, task.getTown());
			ub.addColumn("province", ub.STRING, task.getProvince());
			ub.addColumn("supplierType", ub.STRING, task.getType());
			ub.addColumn("creditAmount", ub.DOUBLE, task.getCreditAmount());
			ub.addColumn("fax", ub.STRING, task.getFax());
			ub.addColumn("linkmanEmail", ub.STRING, task.getLinkmanEmail());
			ub.addColumn("linkmanMobile", ub.STRING, task.getLinkmanMobile());
			ub.addColumn("linkmanName", ub.STRING, task.getLinkmanName());
			ub.addColumn("linkmanSuffix", ub.STRING, task.getLinkmanSuffix());
			ub.addColumn("linkmanTel", ub.STRING, task.getLinkmanTel());
			ub.addColumn("linkmanNamePY", ub.STRING, PinyinHelper.getLetter(task.getLinkmanName()));
			ub.addColumn("noticeAddress", ub.STRING, task.getNoticeAddress());
			ub.addColumn("noticePostcode", ub.STRING, task.getNoticePostcode());
			ub.addColumn("remark", ub.STRING, task.getRemark());
			ub.addColumn("vatNo", ub.STRING, task.getVatNo());
			ub.addColumn("workTel", ub.STRING, task.getWorkTel());
			ub.addColumn("taxRate", ub.DOUBLE, task.getTaxRate());
			ub.addColumn("cooperation", ub.STRING, task.getSupplierCooperation());
			ub.addCondition("ID", ub.guid, task.getId(), "t.RECID=@ID");
			int i = ub.execute();
			if (i == 1) {
				DeleteSqlBuilder db = new DeleteSqlBuilder(context);
				db.setTable(ERPTableNames.Base.BankAccount.getTableName());
				db.addEquals("supplierId", db.guid, task.getId());
				db.execute();
				if (CheckIsNull.isEmpty(task.getItems())) {
					return;
				}
				for (ItemOfSupplier item : task.getItems()) {
					if (CheckIsNull.isEmpty(item) || CheckIsNull.isEmpty(item.getAccount())
							|| CheckIsNull.isEmpty(item.getAccountHolder()) || CheckIsNull.isEmpty(item.getBankName())) {
						continue;
					}
					InsertSqlBuilder insert = new InsertSqlBuilder(context);
					insert.setTable(ERPTableNames.Base.BankAccount.getTableName());
					insert.addColumn("RECID", insert.guid, context.newRECID());
					insert.addColumn("supplierId", insert.guid, task.getId());
					insert.addColumn("bankName", insert.STRING, item.getBankName());
					insert.addColumn("account", insert.STRING, item.getAccount());
					insert.addColumn("accountHolder", insert.STRING, item.getAccountHolder());
					insert.addColumn("remark", insert.STRING, item.getRemark());
					insert.execute();
				}
			}
			context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Modify);
		}

	}

	/**
	 * 获得供应商列表
	 */
	@Publish
	protected class GetSupplierListProvider extends OneKeyResultProvider<ListEntity<SupplierItem>, GetSupplierListKey> {
		@Override
		protected ListEntity<SupplierItem> provide(Context context, GetSupplierListKey key) throws Throwable {
			Employee emp = context.find(Employee.class);
			if (!emp.hasAuth(Auth.Boss, Auth.Assistant, Auth.Purchase, Auth.Account)) {
				// 如果没有总经理、财务、销售的智能，没有查询客户列表的权限
				return null;
			}
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Supplier.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.supplierName", "supplierName");
			qb.addColumn("t.shortName", "shortName");
			qb.addColumn("t.canDelete", "canDelete");
			qb.addColumn("t.supplierNo", "supplierNo");
			qb.addColumn("t.supplierType", "supplierType");
			qb.addColumn("t.linkmanName", "linkmanName");
			qb.addColumn("t.linkmanTel", "linkmanTel");
			qb.addColumn("t.linkmanMobile", "linkmanMobile");
			qb.addColumn("t.linkmanEmail", "linkmanEmail");
			qb.addColumn("t.totalPurchaseAmount", "totalPurchaseAmount");
			qb.addColumn("t.lastPurchaseDate", "lastPurchaseDate");
			qb.addColumn("t.totalPurchaseTimes", "totalPurchaseTimes");
			qb.addColumn("t.taxRate", "taxRate");
			if (key.getSearchText() != null) {
				qb.addArgs("text", qb.STRING, key.getSearchText());
				qb
						.addCondition("( t.linkmanName like '%'+@text+'%' or t.supplierName like '%'+@text+'%'  or t.shortName like '%'+@text+'%'  )");
			}
			if (key.getSortField() != GetSupplierListKey.SortField.None
					&& key.getSortField() != GetSupplierListKey.SortField.BalanceAmount) {
				if (key.getSortType() == SortType.Desc) {
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
			List<SupplierItem> resultList = new ArrayList<SupplierItem>();
			while (rs.next()) {
				int i = 0;
				PartnerItemImpl entity = new PartnerItemImpl();
				GUID id = rs.getFields().get(i++).getGUID();
				entity.setId(id);
				entity.setName(rs.getFields().get(i++).getString());
				entity.setShortName(rs.getFields().get(i++).getString());
				entity.setUsed(!rs.getFields().get(i++).getBoolean());
				entity.setPartnerNo(rs.getFields().get(i++).getString());
				String type = rs.getFields().get(i++).getString();
				entity.setSupplierType(type);
				entity.setContactName(rs.getFields().get(i++).getString());
				entity.setContactLandLineNumber(rs.getFields().get(i++).getString());
				entity.setContactMobileNo(rs.getFields().get(i++).getString());
				entity.setContactEmail(rs.getFields().get(i++).getString());
				entity.setTradeTotalAmount(rs.getFields().get(i++).getDouble());
				entity.setRecentTradeDate(rs.getFields().get(i++).getDate());
				entity.setTradeTotalCount(rs.getFields().get(i++).getInt());
				entity.setTaxRate(rs.getFields().get(i++).getDouble());

				// 应收应付金额
				BalanceAmount balanceAmount = context.find(BalanceAmount.class, new GetBalanceAmountByPartnerKey(id));
				if (balanceAmount != null)
					entity.setBalanceAmount(balanceAmount.getDueAmount());
				else {
					entity.setBalanceAmount(0d);
				}
				resultList.add(entity);
			}
			// if (key.getSortField() != GetSupplierListKey.SortField.None) {
			// ComparatorUtils.sort(resultList, key.getSortField()
			// .getFieldName(), key.getSortType() == SortType.Asc,
			// true);
			// }
			return new ListEntity<SupplierItem>(resultList, resultList.size());

		}

	}

	/**
	 * 删除供应商服务
	 */
	@Publish
	protected class DeleteSupplierHandler extends SimpleTaskMethodHandler<DeleteSupplierTask> {

		@Override
		protected void handle(Context context, DeleteSupplierTask task) throws Throwable {
			SupplierInfo info = context.find(SupplierInfo.class, task.getId());
			if (info.isUsed()) {
				throw new IllegalArgumentException(info.getShortName() + "已经被使用过,不能删除！");
			}
			DeleteSqlBuilder db = new DeleteSqlBuilder(context);
			db.setTable(ERPTableNames.Base.Supplier.getTableName());
			db.addEquals("RECID", db.guid, task.getId());
			db.addEquals("canDelete", db.BOOLEAN, true);
			int i = db.execute();
			if (i == 1) {
				DeleteSqlBuilder del = new DeleteSqlBuilder(context);
				del.setTable(ERPTableNames.Base.BankAccount.getTableName());
				del.addEquals("supplierId", del.guid, task.getId());
				del.execute();

				DeleteSqlBuilder del1 = new DeleteSqlBuilder(context);
				del1.setTable(ERPTableNames.Base.JointVenture.getTableName());
				del1.addEquals("supplierId", del.guid, task.getId());
				del1.execute();

				context.handle(new UpdatePartnerResourceTask(task.getId()), UpdatePartnerResourceTask.Method.Remove);
			}
		}

	}

	/**
	 * 获得供应商维护对象
	 */
	@Publish
	protected class GetSupplierInfoByIdProvider extends OneKeyResultProvider<SupplierInfo, GUID> {

		@Override
		protected SupplierInfo provide(Context context, GUID key) throws Throwable {
			if (Partner.RetailPurchaseSupplierId.equals(key)) {
				SupplierInfoImpl retailPurchaseSupplier = new SupplierInfoImpl();
				retailPurchaseSupplier.setId(Partner.RetailPurchaseSupplierId);
				retailPurchaseSupplier.setName(Partner.RetailPurchaseSupplierName);
				retailPurchaseSupplier.setShortName(Partner.RetailPurchaseSupplierName);
				retailPurchaseSupplier.setPartnerType(PartnerType.Supplier);
				return retailPurchaseSupplier;
			}
			SupplierInfoImpl info = null;
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Supplier.getTableName(), "t");
			QueryTableColumnUtil.setSupplierInfoColumn(qb);
			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				info = QueryTableColumnUtil.getSupplierInfo(context, rs);
			}
			if (null == info) {
				return info;
			}
			info.setBanks(this.getBankAccount(context, key));
			return info;
		}

		/**
		 * @return 供应商银行账户列表
		 */
		protected List<BankAccountItem> getBankAccount(Context context, GUID key) {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.BankAccount.getTableName(), "t");
			QueryTableColumnUtil.setBankAccountColumn(qb);
			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.supplierId", "@id");
			RecordSet rs = qb.getRecord();
			List<BankAccountItem> list = new ArrayList<BankAccountItem>();
			while (rs.next()) {
				list.add(QueryTableColumnUtil.getBankAccountItem(rs, key));
			}
			return list;
		}
	}

	/**
	 * 供应商选择界面迷你列表
	 */
	@Publish
	protected class GetSupplierShortItemProvider2 extends
			OneKeyResultProvider<ListEntity<SupplierShortItem>, GetShortSupplierListKey> {

		@Override
		protected ListEntity<SupplierShortItem> provide(Context context, GetShortSupplierListKey key) throws Throwable {
			List<SupplierShortItem> resultList = new ArrayList<SupplierShortItem>();
			List<Partner> list = context.getList(Partner.class, PartnerType.Supplier);
			for (Partner item : list) {
				boolean b = false;
				if (key.isOnlyJointVenture()
						&& !SupplierCooperation.JointVenture.getCode().equals(item.getSupplierCooperation())) {
					continue;
				}
				if (!key.isHasLxcg() && item.getId().equals(Partner.RetailPurchaseSupplierId)) {
					continue;
				}
				if (CheckIsNull.isEmpty(key.getSearchText())) {
					resultList.add(PartnerHelper.partnerToShortItem(item));
					continue;
				}
				if (item.getAddress() != null && item.getAddress().indexOf(key.getSearchText()) > -1) {
					b = true;
				}
				b = b || item.getName().indexOf(key.getSearchText()) > -1
						|| item.getShortName().indexOf(key.getSearchText()) > -1;
				if (b) {
					resultList.add(PartnerHelper.partnerToShortItem(item));
				}
			}
			return new ListEntity<SupplierShortItem>(resultList, resultList.size());
		}
	}

	/**
	 * 校验供应商全称是否是唯一的
	 */
	@Publish
	protected class ValidateCustomerNameIsOnlyHandler extends
			TaskMethodHandler<ValidatePartnerNameTask, ValidatePartnerNameTask.Method> {

		protected ValidateCustomerNameIsOnlyHandler() {
			super(ValidatePartnerNameTask.Method.SupplierName);
		}

		@Override
		protected void handle(Context context, ValidatePartnerNameTask task) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Supplier.getTableName(), "t");
			qb.addColumn("count(1)", "ccc");
			qb.addArgs("id", "guid", task.getId());
			qb.addArgs("name", qb.STRING, task.getName());
			qb.addNotEquals("t.RECID", "@id");
			qb.addEquals("t.supplierName", "@name");
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
	 * 校验供应商简称是否是唯一的
	 */
	@Publish
	protected class ValidateCustomerShortNameIsOnlyHandler extends
			TaskMethodHandler<ValidatePartnerNameTask, ValidatePartnerNameTask.Method> {

		protected ValidateCustomerShortNameIsOnlyHandler() {
			super(ValidatePartnerNameTask.Method.SupplierShortName);
		}

		@Override
		protected void handle(Context context, ValidatePartnerNameTask task) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Supplier.getTableName(), "t");
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
	 * 查询供应商数量
	 */
	@Publish
	protected class FindProviderCountProvider extends OneKeyResultProvider<Integer, FindProviderCountKey> {

		/**
		 * 查询供应商数量的SQL
		 */
		private String getProviderCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_ProviderCount() ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from " + ERPTableNames.Base.Supplier.getTableName() + " as t ");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindProviderCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getProviderCountSql());
			try {
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 得到最新供应商编号
	 */
	@Publish
	protected class GetNewSupplierNoProvider extends OneKeyResultProvider<String, GetNewSupplierNoKey> {

		@Override
		protected String provide(Context context, GetNewSupplierNoKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			String number = null;
			qb.addTable(ERPTableNames.Base.Supplier.getTableName(), "t");
			qb.addArgs("stype", qb.STRING, key.getSupplierType());
			qb.addColumn("t.supplierNo", "ccc");
			qb.addCondition("t.supplierNo like @stype+'%'");
			qb.addOrderBy("t.createDate desc ");
			RecordSet rs = qb.getRecordLimit(0, 1);
			if (rs.next()) {
				String count = rs.getFields().get(0).getString();
				if (CheckIsNull.isNotEmpty(count)) {
					number = com.spark.common.utils.character.StringHelper.addOneInt(count, 6);
					return number;
				}
			}
			return key.getSupplierType() + "0001";
		}
	}
}
