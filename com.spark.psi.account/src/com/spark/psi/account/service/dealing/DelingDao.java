package com.spark.psi.account.service.dealing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.account.intf.accountenum.dealing.CusType;
import com.spark.psi.account.intf.entity.dealing.Dealing;
import com.spark.psi.account.intf.entity.dealing.DealingItem;
import com.spark.psi.account.intf.entity.dealing.PartnerDealingRealAmount;
import com.spark.psi.account.intf.entity.dealing.pri.DueDealings;
import com.spark.psi.account.intf.event.DealingAmountChanageEvent;
import com.spark.psi.account.intf.key.dealing.CusdealInitCountKey;
import com.spark.psi.account.intf.key.dealing.GetBalanceListKey;
import com.spark.psi.account.intf.key.dealing.GetDealingsItemListKey;
import com.spark.psi.account.intf.key.dealing.GetInitDealingListKey;
import com.spark.psi.account.intf.key.dealing.ReceiptingListKey;
import com.spark.psi.account.intf.key.dealing.GetBalanceListKey.SortField;
import com.spark.psi.account.intf.task.dealing.DealingItemTask;
import com.spark.psi.account.intf.task.dealing.DealingTask;
import com.spark.psi.account.intf.task.dealing.InitDealingTask;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.account.service.orm.Orm_Dealing;
import com.spark.psi.account.service.orm.Orm_DealingItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.task.UpdatePartnerStatusTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.partner.entity.CustomerItem;
import com.spark.psi.publish.base.partner.entity.PartnerItem;
import com.spark.psi.publish.base.partner.entity.SupplierItem;
import com.spark.psi.publish.base.partner.key.GetCustomerListKey;
import com.spark.psi.publish.base.partner.key.GetSupplierListKey;

/**
 * 客户/供应商往来服务管理
 * 
 */
public class DelingDao extends Service {

	private Orm_Dealing orm_Dealing;
	private Orm_DealingItem orm_DealingItem;

	final static String balanceTable = ERPTableNames.Account.DEALING.getTableName();
	final static String balanceItemTable = ERPTableNames.Account.DEALING_Det.getTableName();

	protected DelingDao(Orm_DealingItem ormFinanceCusdeal, Orm_Dealing orm_Dealing) {
		super("DelingDao");
		this.orm_DealingItem = ormFinanceCusdeal;
		this.orm_Dealing = orm_Dealing;
	}

	/**
	 * 新增往来款明细
	 * 
	 * @author yanglin
	 * 
	 */
	@Publish
	protected class AddCusdealDaoHanlder extends SimpleTaskMethodHandler<DealingItemTask> {
		@Override
		protected void handle(Context context, DealingItemTask task) throws Throwable {
			DealingItem item = task.getItem();
			if (null == item) {
				return;
			}
			DealingTask dTask = new DealingTask();
			dTask.setItem(item);
			context.handle(dTask);

			ORMAccessor<DealingItem> orm = context.newORMAccessor(orm_DealingItem);
			try {

				orm.insert(item);
				if (!isIrregularOrRetail(item)) {
					DealingAmountChanageEvent event = new DealingAmountChanageEvent(item.getId(), item.getPartnerId());
					context.dispatch(event);
				}
			} finally {
				orm.unuse();
			}
		}
	}

	/**
	 * 新增/修改往来
	 */
	@Publish
	protected class DealingHandler extends SimpleTaskMethodHandler<DealingTask> {
		@Override
		protected void handle(Context context, DealingTask task) throws Throwable {

			DealingItem item = task.getItem();
			if (CheckIsNull.isEmpty(item)) {
				return;
			}
			Dealing dealing = context.find(Dealing.class, item.getPartnerId());
			Partner partner = context.find(Partner.class, item.getPartnerId());

			ORMAccessor<Dealing> orm = context.newORMAccessor(orm_Dealing);
			if (null == dealing) {
				dealing = new Dealing();
				dealing.setId(item.getPartnerId());
				dealing.setPartnerId(item.getPartnerId());
				if (null != partner) {
					dealing.setPartnerName(partner.getName());
					dealing.setPartnerShortName(partner.getShortName());
					dealing.setNamePY(PinyinHelper.getLetter(partner.getName()));
				}
				if (isReturn(partner, item)) {
					item.setRealAmount(DoubleUtil.mul(-1, item.getRealAmount(), 2));
				}
				dealing.setAmount(DoubleUtil.sub(item.getPlanAmount(), item.getRealAmount()));
				item.setBalance(DoubleUtil.sub(item.getPlanAmount(), item.getRealAmount()));
				// dealing.setTenantsId(item.getTenantsGuid());
				if (CheckIsNull.isNotEmpty(partner)) {
					dealing.setType(partner.getPartnerType().getCode());
				}

				try {
					orm.insert(dealing);
				} finally {
					orm.unuse();
				}
			} else {
				if (isReturn(partner, item)) {
					item.setRealAmount(DoubleUtil.mul(-1, item.getRealAmount(), 2));
				}
				dealing.setAmount(DoubleUtil.sum(dealing.getAmount(), DoubleUtil.sub(item.getPlanAmount(), item
						.getRealAmount())));
				item.setBalance(dealing.getAmount());
				try {
					orm.update(dealing);
				} finally {
					orm.unuse();
				}
			}
		}

		/**
		 * 是否对供应商收款（采购退款）/对客户股款（销售退款）
		 * 
		 * @param partner
		 * @param item
		 * @return Object
		 */
		private boolean isReturn(Partner partner, DealingItem item) {

			return (PartnerType.Customer.equals(partner.getPartnerType())
					&& DealingsType.CUS_XSTK.getCode().equals(item.getBillsType()) && item.getRealAmount() > 0)
					|| (PartnerType.Supplier.equals(partner.getPartnerType())
							&& DealingsType.PRO_CGTK.getCode().equals(item.getBillsType()) && item.getRealAmount() > 0);
		}
	}

	/**
	 * 往来初始化保存
	 */
	@Publish
	protected class SaveInitDealingHandler extends TaskMethodHandler<InitDealingTask, InitDealingTask.Method> {

		protected SaveInitDealingHandler() {
			super(InitDealingTask.Method.Save);
		}

		@Override
		protected void handle(Context context, InitDealingTask task) throws Throwable {
			if (CheckIsNull.isEmpty(task.getItems())) {
				return;
			}

			deleteInitAmount(context, task.getPartnerType());
			for (InitDealingTask.Item item : task.getItems()) {
				StringBuilder dnaSql = new StringBuilder();
				dnaSql.append("define update updateDealing(@partnerId guid,@initAmount double)\n");
				dnaSql.append("begin\n");
				dnaSql.append("update \n");
				dnaSql.append(balanceTable).append(" as t\n");
				dnaSql.append("set initAmount=@initAmount\n");
				dnaSql.append("where t.partnerId=@partnerId\n");
				dnaSql.append("end");

				DBCommand db = context.prepareStatement(dnaSql);
				db.setArgumentValues(context.find(Login.class).getTenantId(), item.getPartnerId(), item.getAmount());

				try {
					if (db.executeUpdate() < 1) {
						Dealing dealing = new Dealing();
						dealing.setId(context.newRECID());
						dealing.setPartnerId(item.getPartnerId());
						dealing.setInitAmount(item.getAmount());
						// dealing.setTenantsId(context.find(Login.class).getTenantId());
						dealing.setType(item.getPartnerType().getCode());
						dealing.setPartnerId(item.getPartnerId());
						Partner partner = context.find(Partner.class, item.getPartnerId());
						if (null != partner) {
							dealing.setPartnerName(partner.getName());
							dealing.setPartnerShortName(partner.getShortName());
							dealing.setNamePY(PinyinHelper.getLetter(partner.getName()));
						}

						ORMAccessor<Dealing> orm = context.newORMAccessor(orm_Dealing);
						try {
							orm.insert(dealing);
						} finally {
							orm.unuse();
						}
					}
				} finally {
					db.unuse();
				}
			}

		}

		private void deleteInitAmount(Context context, PartnerType partnerType) {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define update deleteInitAmount(@tenantId guid)\n");
			dnaSql.append("begin\n");
			dnaSql.append("update \n");
			dnaSql.append(balanceTable).append(" as t\n");
			dnaSql.append("set initAmount=0\n");
			dnaSql.append("where t.type='" + partnerType.getCode() + "' \n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(context.find(Login.class).getTenantId());

			try {
				db.executeUpdate();
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 往来初始化完成
	 */
	@Publish
	protected class FinishInitDealingHandler extends TaskMethodHandler<InitDealingTask, InitDealingTask.Method> {

		protected FinishInitDealingHandler() {
			super(InitDealingTask.Method.Finish);
		}

		@Override
		protected void handle(Context context, InitDealingTask task) throws Throwable {
			GetInitDealingListKey key = new GetInitDealingListKey(null);
			List<Dealing> list = context.getList(Dealing.class, key);
			if (CheckIsNull.isEmpty(list)) {
				return;
			}
			for (Dealing item : list) {
				CusdealTask cTask = new CusdealTask();
				if (PartnerType.Customer.getCode().equals(item.getType())) {
					cTask.setCusdealType(DealingsType.CUS_INIT);
				} else {
					cTask.setCusdealType(DealingsType.PRO_INIT);
				}
				cTask.setPartnerId(item.getId());
				cTask.setPlanAmount(item.getInitAmount());
				context.handle(cTask);

				/**
				 * 客户转正标记
				 */
				UpdatePartnerStatusTask pTask = new UpdatePartnerStatusTask(item.getId(), PartnerStatus.Official);
				context.handle(pTask);
			}

		}

	}

	/**
	 * 查询单个往来款
	 * 
	 * @author yanglin
	 * 
	 */
	@Publish
	protected class QueryCusdealDaoPrivader extends OneKeyResultProvider<Dealing, GUID> {
		@Override
		protected Dealing provide(Context context, GUID recid) throws Throwable {
			if (recid != null) {
				ORMAccessor<Dealing> orm = context.newORMAccessor(orm_Dealing);
				try {
					return orm.findByRECID(recid);
				} finally {
					orm.unuse();
				}
			} else {
				return null;
			}
		}
	}

	/**
	 * 主键查询单个明细
	 */
	@Publish
	protected class GetDealingItemById extends OneKeyResultProvider<DealingItem, GUID> {
		@Override
		protected DealingItem provide(Context context, GUID id) throws Throwable {
			ORMAccessor<DealingItem> orm = context.newORMAccessor(orm_DealingItem);
			try {
				return orm.findByRECID(id);
			} finally {
				orm.unuse();
			}
		}

	}

	/**
	 * 查询客户/供应商余额列表
	 */
	@Publish
	protected class GetDealingsList extends OneKeyResultListProvider<Dealing, GetBalanceListKey> {

		@Override
		protected void provide(Context context, GetBalanceListKey key, List<Dealing> list) throws Throwable {
			List<GUID> partnerIdList = getPartnerIdList(context, key);
			if (partnerIdList.size() < 1)
				return;
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();

			dnaSql.append("define query queryDealingList(@defaultCondtion int");
			conditionSql.append(" where 1=@defaultCondtion ");
			paramList.add(1);
			for (int index = 0; index < partnerIdList.size(); index++) {
				dnaSql.append(",@partnerId").append(index).append(" guid");
				if (0 == index) {
					conditionSql.append(" and t.partnerId in (@partnerId").append(index);
				} else {
					conditionSql.append(",@partnerId").append(index);
				}
				if (index == partnerIdList.size() - 1) {
					conditionSql.append(") \n");
				}
				paramList.add(partnerIdList.get(index));
			}
			if (key.getSearchText() != null) {

			}
			dnaSql.append(") \n");

			dnaSql.append("begin \n");
			dnaSql.append(" select \n");
			dnaSql.append(getColums());
			dnaSql.append("\n ");
			dnaSql.append(" from ").append(balanceTable).append(" as t\n");
			dnaSql.append(conditionSql);

			if (SortField.None.equals(key.getSortField())) {
				dnaSql.append(" order by ").append("t.amount ");

			} else if (GetBalanceListKey.SortField.PartnerName.equals(key.getSortField())) {
				dnaSql.append(" order by ").append("t.recid ");
			} else {
				dnaSql.append(" order by ").append(key.getSortField().getFieldName());
			}
			if (CheckIsNull.isNotEmpty(key.getSortType())) {
				if (SortType.Asc.equals(key.getSortType())) {
					dnaSql.append(" asc ");
				} else {
					dnaSql.append(" desc ");
				}
			} else {
				dnaSql.append(" desc ");
			}

			dnaSql.append("\n end");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}
			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					list.add(fillDealing(rs));
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 查询应收列表
	 */
	@Publish
	protected class GetReceiptingItemImplList extends OneKeyResultListProvider<DueDealings, ReceiptingListKey> {

		@Override
		protected void provide(Context context, ReceiptingListKey key, List<DueDealings> list) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();

			final Map<GUID, PartnerItem> partnerItemInfos = new HashMap<GUID, PartnerItem>();

			GetCustomerListKey getCustomerKey = new GetCustomerListKey(PartnerStatus.Official);
			if (context.find(Login.class).hasAuth(Auth.Account)) {
				getCustomerKey.setShowAll(true);
			}
			@SuppressWarnings("unchecked")
			ListEntity<CustomerItem> customerListEntity = context.find(ListEntity.class, getCustomerKey);
			GetSupplierListKey getSupplierKey = new GetSupplierListKey();
			@SuppressWarnings("unchecked")
			ListEntity<SupplierItem> supplierListEntity = context.find(ListEntity.class, getSupplierKey);

			dnaSql.append("define query queryReceiptingItemImplLlist(@tenantsId guid");
			conditionSql.append(" and t.tenantsId=@tenantsId \n");
			paramList.add(context.find(Login.class).getTenantId());

			conditionSql.append(" and ((t.amount>0 and t.type='");
			conditionSql.append(PartnerType.Customer.getCode());
			conditionSql.append("') or (t.amount<0 and t.type='");
			conditionSql.append(PartnerType.Supplier.getCode());
			conditionSql.append("')) \n");

			conditionSql.append(" and ( 1=2 ");
			int partnerIndex = 0;
			if (customerListEntity != null) {
				for (CustomerItem item : customerListEntity.getItemList()) {
					dnaSql.append(", @partnerId" + String.valueOf(partnerIndex) + " GUID");
					conditionSql.append(" or t.recid=@partnerId" + String.valueOf(partnerIndex));
					paramList.add(item.getId());
					partnerItemInfos.put(item.getId(), item);
					partnerIndex++;
				}
			}
			if (supplierListEntity != null) {
				for (SupplierItem item : supplierListEntity.getItemList()) {
					dnaSql.append(", @partnerId" + String.valueOf(partnerIndex) + " GUID");
					conditionSql.append(" or t.recid=@partnerId" + String.valueOf(partnerIndex));
					paramList.add(item.getId());
					partnerItemInfos.put(item.getId(), item);
					partnerIndex++;
				}
			}
			conditionSql.append(" )");

			if (null != key.getSearchText()) {
				conditionSql.append(" and (");
				conditionSql.append(" t.partnerName like '%'+'").append(key.getSearchText().trim()).append("'+'%'");
				conditionSql.append(" or t.partnerShortName like '%'+'").append(key.getSearchText().trim()).append(
						"'+'%'");
				if (ReceiptType.RECEIPT_CGTK.getName().indexOf(key.getSearchText()) != -1) {
					conditionSql.append(" or t.type='").append(PartnerType.Supplier.getCode()).append("'");
				}
				if (ReceiptType.RECEIPT_XSHK.getName().indexOf(key.getSearchText()) != -1) {
					conditionSql.append(" or t.type='").append(PartnerType.Customer.getCode()).append("'");
				}
				conditionSql.append(") \n");
			}

			dnaSql.append(") \n");
			dnaSql.append("begin \n");

			dnaSql.append(" select \n");
			dnaSql.append(getDueDealingsColums());
			dnaSql.append(" \n from sa_account_dealing as t \n");
			dnaSql.append(" where 1=1 " + conditionSql);

			dnaSql.append("\n end");

			DBCommand db = context.prepareStatement(dnaSql);
			for (int index = 0; index < paramList.size(); index++) {
				db.setArgumentValue(index, paramList.get(index));
			}

			try {
				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					list.add(fillReceipting(rs, partnerItemInfos));
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 查询应付列表
	 */
	// @Publish
	// protected class GetPayingItemImplList extends
	// OneKeyResultListProvider<DueDealings, PayingListKey> {
	//
	// @Override
	// protected void provide(Context context, PayingListKey key,
	// List<DueDealings> list) throws Throwable {
	// StringBuilder dnaSql = new StringBuilder();
	// StringBuilder conditionSql = new StringBuilder();
	// List<Object> paramList = new ArrayList<Object>();
	//			
	// final Map<GUID, PartnerItem> partnerItemInfos = new HashMap<GUID,
	// PartnerItem>();
	//			
	// GetCustomerListKey getCustomerKey = new
	// GetCustomerListKey(PartnerStatus.Official);
	// if(context.find(Login.class).hasAuth(Auth.Account)){
	// getCustomerKey.setShowAll(true);
	// }
	// @SuppressWarnings("unchecked")
	// ListEntity<CustomerItem> customerListEntity = context.find(
	// ListEntity.class, getCustomerKey);
	// GetSupplierListKey getSupplierKey = new GetSupplierListKey();
	// @SuppressWarnings("unchecked")
	// ListEntity<SupplierItem> supplierListEntity = context.find(
	// ListEntity.class, getSupplierKey);
	//			
	// dnaSql
	// .append("define query queryReceiptingItemImplLlist(@tenantsId guid");
	// conditionSql.append(" and t.tenantsId=@tenantsId \n");
	// paramList.add(context.find(Login.class).getTenantId());
	//
	// conditionSql.append(" and ((t.amount<0 and t.type='");
	// conditionSql.append(PartnerType.Customer.getCode());
	// conditionSql.append("') or (t.amount>0 and t.type='");
	// conditionSql.append(PartnerType.Supplier.getCode());
	// conditionSql.append("')) \n");
	//			
	// conditionSql.append(" and ( 1=2 ");
	// int partnerIndex = 0;
	// if(customerListEntity != null) {
	// for(CustomerItem item : customerListEntity.getItemList()) {
	// dnaSql.append(", @partnerId" + String.valueOf(partnerIndex) + " GUID");
	// conditionSql.append(" or t.recid=@partnerId" +
	// String.valueOf(partnerIndex));
	// paramList.add(item.getId());
	// partnerItemInfos.put(item.getId(), item);
	// partnerIndex++;
	// }
	// }
	// if(supplierListEntity != null) {
	// for(SupplierItem item : supplierListEntity.getItemList()) {
	// dnaSql.append(", @partnerId" + String.valueOf(partnerIndex) + " GUID");
	// conditionSql.append(" or t.recid=@partnerId" +
	// String.valueOf(partnerIndex));
	// paramList.add(item.getId());
	// partnerItemInfos.put(item.getId(), item);
	// partnerIndex++;
	// }
	// }
	// conditionSql.append(" )");
	// if(null!=key.getSearchText())
	// {
	// conditionSql.append(" and (");
	// conditionSql.append(" t.partnerName like '%'+'").append(key.getSearchText().trim()).append("'+'%'");
	// conditionSql.append(" or t.partnerShortName like '%'+'").append(key.getSearchText().trim()).append("'+'%'");
	// if(PaymentType.PAY_CGFK.getName().indexOf(key.getSearchText())!=-1)
	// {
	// conditionSql.append(" or t.type='").append(PartnerType.Supplier.getCode()).append("'");
	// }
	// if(PaymentType.PAY_XSTK.getName().indexOf(key.getSearchText())!=-1)
	// {
	// conditionSql.append(" or t.type='").append(PartnerType.Customer.getCode()).append("'");
	// }
	// conditionSql.append(") \n");
	// }
	// dnaSql.append(") \n");
	// dnaSql.append("begin \n");
	//
	// dnaSql.append(" select \n");
	// dnaSql.append(getDueDealingsColums());
	// dnaSql.append(" \n from sa_account_dealing as t \n");
	// dnaSql.append(" where 1=1 " + conditionSql);
	//
	// dnaSql.append("\n end");
	//
	// DBCommand db = context.prepareStatement(dnaSql);
	// for (int index = 0; index < paramList.size(); index++) {
	// db.setArgumentValue(index, paramList.get(index));
	// }
	//
	// try {
	// RecordSet rs = db.executeQuery();
	// while (rs.next()) {
	// list.add(fillReceipting(rs, partnerItemInfos));
	// }
	// } finally {
	// db.unuse();
	// }
	// }
	//
	// }

	/**
	 * 通过客户/供应商guid查询往来明细列表
	 * 
	 * @author yanglin
	 * 
	 */
	@Publish
	protected class QueryCusdealListDao extends OneKeyResultListProvider<DealingItem, GetDealingsItemListKey> {

		@Override
		protected void provide(Context context, GetDealingsItemListKey key, List<DealingItem> list) throws Throwable {

			GUID partnerId = key.getPartnerId();
			Long beginDate = DateUtil.getDayStartTime(key.getStartTime());
			Long endDate = DateUtil.getDayEndTime(key.getEndTime());

			StringBuilder definedSql = new StringBuilder();
			definedSql
					.append(" define query QD_FinanceCusdealList(@partnerId guid not null,@beginDate date,@endDate date)");
			definedSql.append(" begin");
			definedSql.append(" select ");
			definedSql.append(getDealingItemColums());
			definedSql.append(" from ").append(balanceItemTable).append(" as t\n");
			definedSql.append(" where t.partnerId = @partnerId");

			StringBuilder sb = new StringBuilder();
			sb.append(definedSql);
			if (beginDate != null && endDate != null) {
				sb.append(" and t.createDate between @beginDate and @endDate");
			}
			sb.append(" order by t.createDate asc");
			sb.append(" end");

			DBCommand db = context.prepareStatement(sb);
			db.setArgumentValues(partnerId, beginDate, endDate);

			RecordSet rs = db.executeQueryLimit(key.getOffSet(), key.getCount());
			db.unuse();

			// 期初余额
			double oldBalance = 0;
			List<DealingItem> itemList = new ArrayList<DealingItem>();
			while (rs.next()) {
				itemList.add(fillDealingItem(rs));
				if (1 == itemList.size()) {
					DealingItem item = itemList.get(0);
					oldBalance = DoubleUtil.sub(DoubleUtil.sum(item.getBalance(), item.getRealAmount()), item
							.getPlanAmount());
				}
			}
			if (rs.isEmpty()) {
				Dealing dealing = context.find(Dealing.class, key.getPartnerId());
				if (CheckIsNull.isNotEmpty(dealing)) {
					oldBalance = dealing.getAmount();
				}
			}
			if (key.getOffSet() == 0) {
				DealingItem item = new DealingItem();
				item.setBillsNo("期初余额");
				item.setBalance(oldBalance);
				list.add(item);
			}
			list.addAll(itemList);

		}

	}

	/**
	 * 查询客户供应商实收实付金额PartnerDealingRealAmount
	 * 
	 */
	// @Publish
	// protected class GetPartnerDealingRealAmount extends
	// OneKeyResultProvider<PartnerDealingRealAmount, GUID> {
	//
	// @Override
	// protected PartnerDealingRealAmount provide(Context context, GUID
	// partnerId) throws Throwable {
	// StringBuilder dnaSql = new StringBuilder();
	// dnaSql.append("define query queryPartnerDealingRealAmount(@tenantId guid,@partnerId guid,@billsType string)\n");
	// dnaSql.append("begin\n");
	// dnaSql.append("select sum(t.realAmount) as realAmount \n");
	// dnaSql.append("from SA_FINANCE_CUSDEAL_DEAL as t\n");
	// dnaSql.append("where t.tenantsGuid=@tenantId\n");
	// dnaSql.append("and t.cusproGuid=@partnerId\n");
	// dnaSql.append("and t.billsType=@billsType\n");
	// dnaSql.append("end");
	//
	// DBCommand orderAmountDb = context.prepareStatement(dnaSql);
	// DBCommand returnAmountDb = context.prepareStatement(dnaSql);
	//
	// Partner partner = context.find(Partner.class, partnerId);
	// if (null == partner) {
	// return null;
	// }
	// if (PartnerType.Customer.equals(partner.getPartnerType())) {
	// orderAmountDb.setArgumentValues(context.find(Login.class).getTenantId(),
	// partnerId, DealingsType.CUS_XSSK.getCode());
	// returnAmountDb.setArgumentValues(context.find(Login.class).getTenantId(),
	// partnerId, DealingsType.CUS_XSTK.getCode());
	// } else {
	// orderAmountDb.setArgumentValues(context.find(Login.class).getTenantId(),
	// partnerId, DealingsType.PRO_CGFK.getCode());
	// returnAmountDb.setArgumentValues(context.find(Login.class).getTenantId(),
	// partnerId, DealingsType.PRO_CGTK.getCode());
	// }
	// try {
	// PartnerDealingRealAmount partnerDealingRealAmount = new
	// PartnerDealingRealAmount();
	// RecordSet orderAmountRs = orderAmountDb.executeQuery();
	// RecordSet returnAmountRs = returnAmountDb.executeQuery();
	// while (orderAmountRs.next()) {
	// partnerDealingRealAmount.setOrderAmount(orderAmountRs.getFields().get(0).getDouble());
	// }
	// while (returnAmountRs.next()) {
	// partnerDealingRealAmount.setReturnAmount(returnAmountRs.getFields().get(0).getDouble());
	// }
	// return partnerDealingRealAmount;
	// } finally {
	// orderAmountDb.unuse();
	// returnAmountDb.unuse();
	// }
	// }
	//
	// }

	private DealingItem fillDealingItem(RecordSet rs) {
		int index = 0;
		DealingItem entity = new DealingItem();
		entity.setId(rs.getFields().get(index++).getGUID());
		entity.setPartnerId(rs.getFields().get(index++).getGUID());
		entity.setBillsId(rs.getFields().get(index++).getGUID());
		entity.setBillsNo(rs.getFields().get(index++).getString());
		entity.setBillsType(rs.getFields().get(index++).getString());
		entity.setReceiptType(rs.getFields().get(index++).getString());
		entity.setPlanAmount(rs.getFields().get(index++).getDouble());
		entity.setRealAmount(rs.getFields().get(index++).getDouble());
		entity.setBalance(rs.getFields().get(index++).getDouble());
		entity.setCreateDate(rs.getFields().get(index++).getDate());
		entity.setRemark(rs.getFields().get(index++).getString());
		entity.setAccountBillsId(rs.getFields().get(index++).getGUID());
		entity.setAccountBillsNo(rs.getFields().get(index++).getString());

		return entity;
	}

	public Object getDealingItemColums() {
		StringBuffer sql = new StringBuffer();

		sql.append("t.RECID as id,");
		sql.append("t.partnerId as partnerId,");
		sql.append("t.billsId as billsId,");
		sql.append("t.billsNo as billsNo,");
		sql.append("t.billsType as billsType,");
		sql.append("t.receiptType as receiptType,");
		sql.append("t.planAmount as planAmount,");
		sql.append("t.realAmount as realAmount,");
		sql.append("t.balance as balance,");
		sql.append("t.createDate as createDate,");
		sql.append("t.remark as remark,");
		sql.append("t.accountBillsId as accountBillsId,");
		sql.append("t.accountBillsNo as accountBillsNo\n");

		return sql;
	}

	/**
	 * 零采or零售往来
	 * 
	 * @param item
	 * @return boolean
	 */
	public boolean isIrregularOrRetail(DealingItem item) {

		return DealingsType.CUS_LSCK.getCode().equals(item.getBillsType())
				|| DealingsType.CUS_LSSK.getCode().equals(item.getBillsType())
				|| DealingsType.CUS_LSTH.getCode().equals(item.getBillsType())
				|| DealingsType.CUS_LSTK.getCode().equals(item.getBillsType())
				|| DealingsType.PRO_LCFK.getCode().equals(item.getBillsType())
				|| DealingsType.PRO_LXCG.getCode().equals(item.getBillsType());
	}

	/**
	 * 应收/付实体
	 * 
	 * @param rs
	 * @return ReceiptingItemImpl
	 */
	public DueDealings fillReceipting(RecordSet rs, Map<GUID, PartnerItem> itemInfos) {
		DueDealings dueDealings = new DueDealings();
		int index = 0;
		dueDealings.setId(rs.getFields().get(index++).getGUID());
		dueDealings.setType(rs.getFields().get(index++).getString());
		PartnerItem item = itemInfos.get(dueDealings.getId());
		dueDealings.setCusproName(item.getName());
		dueDealings.setCusproShortName(item.getShortName());
		dueDealings.setAmount(item.getBalanceAmount());
		if (item instanceof CustomerItem) {
			CustomerItem cItem = (CustomerItem) item;
			dueDealings.setCreditline(cItem.getCreditAmount());
			dueDealings.setAccountRemind((int) cItem.getRemindDay());
			dueDealings.setAccountPeriod(cItem.getAccountPeriod());
		}

		return dueDealings;
	}

	/**
	 * 获取应收列表字段
	 * 
	 * @return Object
	 */
	public Object getDueDealingsColums() {
		StringBuilder sql = new StringBuilder();

		sql.append("t.recid as id");
		// sql.append(",c.cusproShortName as cusproShortName");
		// sql.append(",c.creditline as creditline");
		// sql.append(",c.accountRemind as accountRemind");
		// sql.append(",t.amount as amount");
		//
		// sql.append(",c.accountPeriod as accountPeriod");
		// sql.append(",c.cusproName as cusproName");
		sql.append(",t.type as type");

		return sql;
	}

	public Dealing fillDealing(RecordSet rs) {
		Dealing dealing = new Dealing();
		int index = 0;
		dealing.setId(rs.getFields().get(index++).getGUID());
		dealing.setPartnerId(rs.getFields().get(index++).getGUID());
		dealing.setPartnerName(rs.getFields().get(index++).getString());
		dealing.setNamePY(rs.getFields().get(index++).getString());
		dealing.setPartnerShortName(rs.getFields().get(index++).getString());
		dealing.setType(rs.getFields().get(index++).getString());
		dealing.setAmount(rs.getFields().get(index++).getDouble());
		dealing.setInitAmount(rs.getFields().get(index++).getDouble());

		return dealing;
	}

	public Object getColums() {
		StringBuilder sql = new StringBuilder();

		sql.append(" t.RECID as id,");
		sql.append("t.partnerId as partnerId,");
		sql.append("t.partnerName as partnerName,");
		sql.append("t.namePY as namePY,");
		sql.append("t.partnerShortName as partnerShortName,");
		sql.append("t.type as type,");
		sql.append("t.amount as amount,");
		sql.append("t.initAmount as initAmount");
		return sql;
	}

	/**
	 * 获取客户/供应商ID list
	 * 
	 * @param util
	 * @param context
	 * @param key
	 * @return List<GUID>
	 */

	public List<GUID> getPartnerIdList(Context context, GetBalanceListKey key) {
		List<GUID> idList = new ArrayList<GUID>();
		if (PartnerType.Customer.equals(key.getPartnerType())) {
			GetCustomerListKey lKey = new GetCustomerListKey(PartnerStatus.Official);
			if (context.find(Login.class).hasAuth(Auth.Account)) {
				lKey.setShowAll(true);
			}
			lKey.setQueryScope(key.getQueryScope());
			lKey.setSearchText(key.getSearchText());
			if (key.getSortField() != null && key.getSortField().equals(GetBalanceListKey.SortField.PartnerName)) {
				lKey.setSortField(GetCustomerListKey.SortField.Name);
			}
			if (key.getSortType() != null) {
				lKey.setSortType(key.getSortType());
			}
			@SuppressWarnings("unchecked")
			ListEntity<CustomerItem> listEntity = context.find(ListEntity.class, lKey);
			if (listEntity != null) {
				List<CustomerItem> pList = listEntity.getItemList();
				if (CheckIsNull.isNotEmpty(pList)) {
					for (CustomerItem partner : pList) {
						idList.add(partner.getId());
					}
				}
			}
		} else {
			GetSupplierListKey supplierKey = new GetSupplierListKey();
			supplierKey.setSearchText(key.getSearchText());
			if (key.getSortField() != null && key.getSortField().equals(GetBalanceListKey.SortField.PartnerName)) {
				supplierKey.setSortField(GetSupplierListKey.SortField.Name);
			}
			@SuppressWarnings("unchecked")
			ListEntity<SupplierItem> listEntity = context.find(ListEntity.class, supplierKey);
			if (listEntity != null) {
				List<SupplierItem> pList = listEntity.getItemList();
				if (CheckIsNull.isNotEmpty(pList)) {
					for (SupplierItem partner : pList) {
						idList.add(partner.getId());
					}
				}
			}
			// List<Partner> pList = context.getList(Partner.class,
			// PartnerType.Supplier);
			// if (CheckIsNull.isNotEmpty(pList)) {
			// for (Partner partner : pList) {
			// idList.add(partner.getId());
			// }
			// }

		}
		return idList;
	}

	/**
	 * 查询往来初始化列表
	 */
	@Publish
	protected class GetInitDealingItemList extends OneKeyResultListProvider<Dealing, GetInitDealingListKey> {

		@Override
		protected void provide(Context context, GetInitDealingListKey key, List<Dealing> resultList) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query queryInitDealings(@tenantId guid,@partnerType string)\n");
			dnaSql.append("begin\n");
			dnaSql.append("select\n");
			dnaSql.append(getColums()).append("\n");
			dnaSql.append("from sa_account_dealing as t\n");
			dnaSql.append("where t.tenantsId=@tenantId\n");
			if (null != key.getPartnerType()) {
				dnaSql.append("and t.type=@partnerType\n");
			}
			dnaSql.append("and t.initAmount>0\n");
			dnaSql.append("end");

			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(context.find(Login.class).getTenantId(), key.getPartnerType() == null ? null : key
					.getPartnerType().getCode());
			try {

				RecordSet rs = db.executeQuery();
				while (rs.next()) {
					resultList.add(fillDealing(rs));
				}
			} finally {
				db.unuse();
			}

		}

	}

	/**
	 * 查询还未初始化的供应商和客户数量
	 * 
	 * @author yanglin
	 * 
	 */
	@Publish
	protected class GetCusdealInitCountProvider extends ResultProvider<CusdealInitCountKey> {
		@Override
		protected CusdealInitCountKey provide(Context context) throws Throwable {
			CusdealInitCountKey key = new CusdealInitCountKey();
			StringBuilder sb = new StringBuilder();
			sb.append(" define query QD_CusproviderPyList(@tenantsGuid guid not null)");
			sb.append(" begin");
			sb.append(" select t.cusproType as cusproType,count(1) as c from SA_CUSPROVIDER_INFO as t");
			sb
					.append(" where t.tenantsGuid = @tenantsGuid and (t.isReflag is null or t.isReflag = false) group by t.cusproType");
			sb.append(" end");

			DBCommand db = context.prepareStatement(sb);
			GUID tenantsGuid = context.find(Login.class).getTenantId();
			db.setArgumentValues(tenantsGuid);
			RecordSet rs = db.executeQuery();
			db.unuse();

			while (rs.next()) {
				String type = rs.getFields().get(0).getString();
				if (CusType.CUSTOMER.toCode().equals(type)) {
					key.setCusCount(rs.getFields().get(1).getInt());
				} else if (CusType.PROVIDER.toCode().equals(type)) {
					key.setProCount(rs.getFields().get(1).getInt());
				}
			}
			return key;
		}
	}
}
