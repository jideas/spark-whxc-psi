package com.spark.psi.account.service.pub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.psi.account.intf.entity.dealing.Dealing;
import com.spark.psi.account.intf.entity.dealing.DealingItem;
import com.spark.psi.account.intf.entity.invoice.Invoice;
import com.spark.psi.account.intf.entity.pay.PaymentEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptEntity;
import com.spark.psi.account.intf.entity.receipt.RetailReceipt;
import com.spark.psi.account.intf.key.dealing.GetBalanceListKey;
import com.spark.psi.account.intf.key.dealing.GetDealingsItemListKey;
import com.spark.psi.account.intf.key.dealing.GetInitDealingListKey;
import com.spark.psi.account.intf.key.invoice.InvoiceKey;
import com.spark.psi.account.intf.key.pay.PayRecordKey;
import com.spark.psi.account.intf.key.receipt.RetailReceiptRecordKey;
import com.spark.psi.account.intf.task.dealing.InitDealingTask;
import com.spark.psi.account.intf.task.invoice.InvoiceDaoTask;
import com.spark.psi.account.intf.task.pub.CusdealTask;
import com.spark.psi.account.intf.task.receipt.InternalRetailReceiptTask;
import com.spark.psi.account.intf.util.AccountUtil;
import com.spark.psi.account.service.orm.Orm_Dealing;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.task.TenantsSysParamTask;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.account.entity.BalanceInfo;
import com.spark.psi.publish.account.entity.BalanceItem;
import com.spark.psi.publish.account.entity.BalanceListEntity;
import com.spark.psi.publish.account.entity.DealingAdjustInfo;
import com.spark.psi.publish.account.entity.BalanceInfoItem;
import com.spark.psi.publish.account.entity.InvoiceItem;
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.psi.publish.account.entity.PaymentListEntity;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.ReceiptListEntity;
import com.spark.psi.publish.account.entity.RetailSubmitingItem;
import com.spark.psi.publish.account.key.GetCustomerBalanceListKey;
import com.spark.psi.publish.account.key.GetDealingsListKey;
import com.spark.psi.publish.account.key.GetInitBalanceItemListKey;
import com.spark.psi.publish.account.key.GetInitedPartnerSummaryKey;
import com.spark.psi.publish.account.key.GetInvoiceListKey;
import com.spark.psi.publish.account.key.GetPaymentListKey;
import com.spark.psi.publish.account.key.GetReceiptListKey;
import com.spark.psi.publish.account.key.GetRetailSubmitingListKey;
import com.spark.psi.publish.account.key.GetSupplierBalanceListKey;
import com.spark.psi.publish.account.task.AdjustCustomerBalanceTask;
import com.spark.psi.publish.account.task.AdjustSupplierBalanceTask;
import com.spark.psi.publish.account.task.ConfirmRetailRecordTask;
import com.spark.psi.publish.account.task.InitBalanceTask;
import com.spark.psi.publish.account.task.InvalidateInvoiceTask;
import com.spark.psi.publish.account.task.InvoiceTask;

/**
 * 
 *
 */
public class AccountPubService extends Service {
	protected AccountPubService(Orm_Dealing orm_Dealing) {
		super("com.spark.psi.account.service.pub.AccountPubService");
	}

	/**
	 * 查询客户的BalanceItem列表
	 * 
	 */
	@Publish
	protected class GetCustomBalanceItem extends
			OneKeyResultProvider<BalanceListEntity, GetCustomerBalanceListKey> {

		@Override
		protected BalanceListEntity provide(Context context, GetCustomerBalanceListKey key
				) throws Throwable {
			GetBalanceListKey lKey = new GetBalanceListKey(PartnerType.Customer);
			lKey.setCount(key.getCount());
			lKey.setOffset(key.getOffset());
			lKey.setQueryScope(key.getQueryScope());
			lKey.setSortType(key.getSortType());
			lKey.setSearchText(key.getSearchText());
			if(key.getSortField() != null) {
				lKey.setSortField(GetBalanceListKey.SortField.valueOf(key.getSortField().name()));
			}
//			if(key.getSortField().equals(GetCustomerBalanceListKey.SortField.PartnerName)) {
//				lKey.setSortField(GetBalanceListKey.SortField.PartnerName);
//			} else if(key.getSortField().equals(GetCustomerBalanceListKey.SortField.Balance)){
//				lKey.setSortField(GetBalanceListKey.SortField.Balance);
//			}
			

			List<Dealing> list = context.getList(Dealing.class, lKey);
			List<BalanceItem> resultList = new ArrayList<BalanceItem>();
			if (CheckIsNull.isNotEmpty(list)) {
				for (Dealing dealing : list) {
					resultList.add(AccountUtil.getBalanceItem(context,dealing,false));
				}
			}
			return new BalanceListEntity(resultList, resultList.size());
		}

	}

	/**
	 * 查询供应商的BalanceItem列表
	 * 
	 * 
	 */
	@Publish
	protected class GetSupplierBalanceItem extends
			OneKeyResultProvider<BalanceListEntity, GetSupplierBalanceListKey> {

		@Override
		protected BalanceListEntity provide(Context context, GetSupplierBalanceListKey key) throws Throwable {

			GetBalanceListKey lKey = new GetBalanceListKey(PartnerType.Supplier);
			lKey.setCount(key.getCount());
			lKey.setOffset(key.getOffset());
			lKey.setSortType(key.getSortType());
			lKey.setSearchText(key.getSearchText());
			if(key.getSortField() != null) {
				lKey.setSortField(GetBalanceListKey.SortField.valueOf(key.getSortField().name()));
			}

			List<Dealing> list = context.getList(Dealing.class, lKey);
			List<BalanceItem> resultList = new ArrayList<BalanceItem>();
			if (CheckIsNull.isNotEmpty(list)) {
				for (Dealing dealing : list) {
					resultList.add(AccountUtil.getBalanceItem(context,dealing,false));
				}
			}
			return new BalanceListEntity(resultList,resultList.size());
		}

	}
	/**
	 * 取得指定往来信息
	 * 
	 *
	 */
	@Publish
	protected class GetBalanceInfo extends OneKeyResultProvider<BalanceInfo, GUID> {

		@Override
		protected BalanceInfo provide(Context context, GUID key)
				throws Throwable {
			BalanceInfo balanceInfo = null;
			Dealing dealing = context.find(Dealing.class, key);
			if(null != dealing) {
				balanceInfo = AccountUtil.getBalanceInfo(context,dealing);
			}
			return balanceInfo;
		}
		
	}
	/**
	 * 往来明细条目 查询方法：<br>
	 * 
	 */
	@Publish
	protected class GetDelingsItem extends
			OneKeyResultListProvider<BalanceInfoItem, GetDealingsListKey> {

		@Override
		protected void provide(Context context, GetDealingsListKey key,
				List<BalanceInfoItem> resultList) throws Throwable {
			GetDealingsItemListKey lKey = new GetDealingsItemListKey();
			lKey.setCount(key.getCount());
			lKey.setOffSet(key.getOffset());
			lKey.setStartTime(key.getStartDate());
			lKey.setEndTime(key.getEndDate());
			lKey.setPartnerId(key.getPartnerId());

			List<DealingItem> list = context.getList(DealingItem.class, lKey);
			if (CheckIsNull.isNotEmpty(list)) {
				for (DealingItem item : list) {
					resultList.add(AccountUtil.getDealingsItem(context,item));
				}
			}

		}
	}
	/**
	 * 取得指定往来明细的详细信息
	 * 
	 *
	 */
	@Publish
	protected class GetDealingItemInfo extends OneKeyResultProvider<DealingAdjustInfo, GUID> {

		@Override
		protected DealingAdjustInfo provide(Context context, GUID key)
				throws Throwable {
			DealingAdjustInfo itemInfo = null;
			DealingItem item = context.find(DealingItem.class, key);
			if(null != item) {
				itemInfo = AccountUtil.getDealingAdjustInfo(item);
			}
			return itemInfo;
		}
		
	}

	/**
	 * 开票记录列表项<br>
	 * 
	 */
	@Publish
	protected class GetInovoiceItem extends
			OneKeyResultListProvider<InvoiceItem, GetInvoiceListKey> {

		@Override
		protected void provide(Context context, GetInvoiceListKey key,
				List<InvoiceItem> resultList) throws Throwable {
			InvoiceKey k = new InvoiceKey();
			k.setCount(key.getCount());
			k.setCusGuid(key.getCustomerId());
			k.setDeptGuid(key.getDepartmentId());
			k.setOffset(key.getOffset());
			k.setSearchText(key.getSearchText());
			k.setSortField(key.getSortField().getFieldName());
			k.setSortType(key.getSortType());
			if(null != key.getQueryTerm()) {
				k.setStartDate(key.getQueryTerm().getStartTime());
				k.setEndDate(key.getQueryTerm().getEndTime());
			}

			List<Invoice> list = context.getList(Invoice.class, k);
			if (CheckIsNull.isNotEmpty(list)) {
				for (Invoice invoice : list) {
					resultList.add(AccountUtil.getInvoiceItem(context,invoice));
				}
			}

		}

	}

	/**
	 * 应付数据条目<BR>
	 * 
	 */
//	@Publish
//	protected class GetPayingItem extends
//			OneKeyResultListProvider<PayingItem, GetPayingListKey> {
//
//		@Override
//		protected void provide(Context context, GetPayingListKey key,
//				List<PayingItem> resultList) throws Throwable {
//			PayingListKey lKey = new PayingListKey();
//			lKey.setCount(key.getCount());
//			lKey.setOffset(key.getOffset());
//			// lKey.setQueryTotal(key.get)
//			lKey.setSearchText(key.getSearchText());
//			lKey.setSortField(key.getSortField());
//			lKey.setSortType(key.getSortType());
//
//			List<DueDealings> list = context.getList(DueDealings.class, lKey);
//			List<PayingItem> sourceList = new ArrayList<PayingItem>();
//			if (CheckIsNull.isNotEmpty(list)) {
//				for (DueDealings dueDealings : list) {
//					sourceList.add(AccountUtil.getPayingItem(context,dueDealings));
//				}
//			}
//
//			boolean desc = true;
//			String sortField = GetPayingListKey.SortField.PayingAmount
//					.getFieldName();
//			if (CheckIsNull.isNotEmpty(key.getSortField())) {
//				sortField = key.getSortField().getFieldName();
//			}
//			if (SortType.Asc.equals(key.getSortType())) {
//				desc = false;
//			}
////			if(sourceList.size() < 1) return;
//			if(key.getSortField()==GetPayingListKey.SortField.None){
//				Collections.sort(sourceList,new SortComparator<PayingItem>(){
//
//					public int compare(PayingItem o1, PayingItem o2){
//						int result = 0;
//						if(o1.getPayingAmount()!=o2.getPayingAmount()){
//							result = compare(o1.getPayingAmount(), o2.getPayingAmount());
//							result = desc(result, SortType.Desc.name());
//						}else if(o1.getOverPeriodAmount()!=o2.getOverPeriodAmount()){
//							result = compare(o1.getOverPeriodAmount(), o2.getOverPeriodAmount());
//							result = desc(result,SortType.Desc.name());
//						}else {
//							result = compare(o1.getPaymentTargetName(), o1.getPaymentTargetName());
//							result = desc(result,SortType.Asc.name());
//						}
//	                    return result;
//                    }
//				});
//			}else {
//				ComparatorUtils.sort(sourceList, sortField, desc);
//			}
//			// int startRow = (int) DoubleUtil
//			// .mul(key.getCount(), key.getOffset());
//			int endRow = (int) DoubleUtil.sub(DoubleUtil.mul(DoubleUtil.sum(key
//					.getOffset(), 1), key.getCount(),2), 1);
//			if(sourceList.size() > 0 && endRow > 0) {
//				resultList.addAll(sourceList.subList(0, endRow < sourceList.size() ? endRow : sourceList.size()));
//			}
//
//		}
//
//	}

//	/**
//	 * 付款记录列表项<br>
//	 * 
//	 */
//	@Publish
//	protected class GetPaymentItem extends
//			OneKeyResultProvider<PaymentListEntity, GetPaymentListKey> {
//
//		@Override
//		protected PaymentListEntity provide(Context context, GetPaymentListKey key) throws Throwable {
//			PayRecordKey oldKey = new PayRecordKey();
//			oldKey.setSearchText(key.getSearchText());
//			oldKey.setOffset(key.getOffset());
//			oldKey.setCount(key.getCount());
//			oldKey.setSortCloumName(key.getSortField().getFieldName());
//			oldKey.setSortType(SortType.Asc == key.getSortType() ? "" : "desc");
//			
//			if(key.getQueryTerm() != null) {
//				oldKey.setCompBeginTime(key.getQueryTerm().getStartTime());
//				oldKey.setCompEndTime(key.getQueryTerm().getEndTime());
//			}
//			if (key.getAdvanceCondition() != null) {
//				oldKey.setAdvance(true);
//				oldKey.setPayObject(key.getAdvanceCondition().getPaymentTargetName());
//				oldKey.setPayDateBegin(key.getAdvanceCondition().getStartDate());
//				oldKey.setPayDateEnd(key.getAdvanceCondition().getEndDate());
//				oldKey.setPayer(key.getAdvanceCondition().getPayer());
//				if (key.getAdvanceCondition().getPaymentTypes() != null) {
//					String[] typeCodes = new String[key.getAdvanceCondition().getPaymentTypes().length];
//					int typeIndex = 0;
//					for (PaymentType type : key.getAdvanceCondition().getPaymentTypes()) {
//						typeCodes[typeIndex] = type.getCode();
//						typeIndex++;
//					}
//					oldKey.setPayType(typeCodes);
//				}
//				oldKey.setPayMoneyBegin(key.getAdvanceCondition().getMinAmount());
//				oldKey.setPayMoneyEnd(key.getAdvanceCondition().getMaxAmount());
//				if (key.getAdvanceCondition().getPaymentWay() != null) {
//					String[] wayCodes = new String[key.getAdvanceCondition().getPaymentWay().length];
//					int wayIndex = 0;
//					for (DealingsWay way : key.getAdvanceCondition().getPaymentWay()) {
//						wayCodes[wayIndex] = way.getCode();
//						wayIndex++;
//					}
//					oldKey.setPayWay(wayCodes);
//				}
//			}
//			List<PaymentEntity> oldList = context.getList(PaymentEntity.class, oldKey);
//			List<PaymentItem> list = new ArrayList<PaymentItem>();
//			if (CheckIsNull.isNotEmpty(oldList)) {
//				for (PaymentEntity rr : oldList) {
//					list.add(AccountUtil.getPaymentItem(rr));
//				}
//			}
//			return new PaymentListEntity(list, list.size());
//
//		}
//
//	}

	

	/**
	 * 零售带交款数据<BR>
	 * 
	 */
	@Publish
	protected class GetRetailSubmitingItem
			extends
			OneKeyResultProvider<ListEntity<RetailSubmitingItem>, GetRetailSubmitingListKey> {

		@Override
		protected ListEntity<RetailSubmitingItem> provide(Context context, GetRetailSubmitingListKey key) throws Throwable {
		
			RetailReceiptRecordKey oldKey = new RetailReceiptRecordKey();
			oldKey.setSearchText(key.getSearchText());
			oldKey.setOffset(key.getOffset());
			oldKey.setCount(key.getCount());
			if(null!=key.getSortField())
			{
				oldKey.setSortField(key.getSortField().getFieldName());
				oldKey.setSortType(SortType.Asc == key.getSortType() ? "asc" : "desc");
			}
			

			List<RetailReceipt> oldList = context.getList(RetailReceipt.class,
					oldKey);
			List<RetailSubmitingItem> resultList = new ArrayList<RetailSubmitingItem>();
			if (CheckIsNull.isNotEmpty(oldList)) {
				for (RetailReceipt rr : oldList) {
					resultList.add(AccountUtil.getRetailSubmitingItem(rr));
				}
			}
			return new ListEntity<RetailSubmitingItem>(resultList, resultList.size());
		}
	}

	/**
	 * 调整客户往来（应收余额）
	 * 
	 * 
	 */
	@Publish
	protected class AdjustCustomerBalanceHandle extends
			SimpleTaskMethodHandler<AdjustCustomerBalanceTask> {

		@Override
		protected void handle(Context context, AdjustCustomerBalanceTask task)
				throws Throwable {
			String sheetNumber = context.get(String.class,SheetNumberType.BalanceAdjust);
			CusdealTask cTask = new CusdealTask();
			cTask.setBillsNo(sheetNumber);
			cTask.setCusdealType(DealingsType.CUS_TZYS);
			cTask.setPlanAmount(task.getAdjustAmount());
			cTask.setRemark(task.getReason());
			cTask.setPartnerId(task.getCustomerId());

			context.handle(cTask);

		}

	}

	/**
	 * 调整供应商往来（应付余额）
	 * 
	 * 
	 */
	@Publish
	protected class AdjustSupplierBalanceHandle extends
			SimpleTaskMethodHandler<AdjustSupplierBalanceTask> {

		@Override
		protected void handle(Context context, AdjustSupplierBalanceTask task)
				throws Throwable {
			String sheetNumber = context.get(String.class, SheetNumberType.BalanceAdjust);
			CusdealTask cTask = new CusdealTask();
			cTask.setBillsNo(sheetNumber);
			cTask.setCusdealType(DealingsType.PRO_TZYF);
			cTask.setPlanAmount(task.getAdjustAmount());
			cTask.setRemark(task.getReason());
			cTask.setPartnerId(task.getSupplierId());

			context.handle(cTask);
		}

	}

	/**
	 * 发票作废
	 * 
	 * 
	 */
	@Publish
	protected class InvalidateInvoiceHandle extends
			SimpleTaskMethodHandler<InvalidateInvoiceTask> {

		@Override
		protected void handle(Context context, InvalidateInvoiceTask task)
				throws Throwable {
			InvoiceDaoTask iTask = new InvoiceDaoTask();
			Invoice invoice = new Invoice();
			invoice.setRECID(task.getId());
			invoice.setInvalidReason(task.getReason());
			iTask.setInvalided(true);
			iTask.setInvoice(invoice);

			context.handle(iTask, InvoiceDaoTask.Method.MODIFYstatus);

		}

	}

	/**
	 * 创建开票记录
	 * 
	 * 
	 */
	@Publish
	protected class InvoiceHandle extends SimpleTaskMethodHandler<InvoiceTask> {

		@Override
		protected void handle(Context context, InvoiceTask task)
				throws Throwable {
			Partner partner = context.find(Partner.class, task.getCustomerId());
			if (CheckIsNull.isEmpty(partner)) {
				return;
			}
			Invoice invoice = new Invoice();
			invoice.setCreateDate(new Date().getTime());
			invoice.setCreatePerson(context.find(Employee.class,context.find(Login.class).getEmployeeId()).getName());
			if (CheckIsNull.isEmpty(partner.getBusinessPerson())) {
				invoice.setCusDeptGuid(context.find(Login.class).getTenantId());
			} else {
				Employee busEmp = context.find(Employee.class, partner.getBusinessPerson());
				if (CheckIsNull.isEmpty(busEmp)) {
					invoice.setCusDeptGuid(context.find(Login.class).getTenantId());
				} else {
					invoice.setCusDeptGuid(busEmp.getDepartmentId());
				}
			}
			invoice.setCusproFullName(partner.getName());
			invoice.setCusproFullNamePY(PinyinHelper.getLetter(partner
					.getName()));
			invoice.setCusproGuid(partner.getId());
			invoice.setCusproName(partner.getShortName());
			invoice.setCusproNamePY(PinyinHelper.getLetter(partner
					.getShortName()));
			invoice.setInvalided(false);
			invoice.setInvoAmount(task.getAmount());
			invoice.setInvoCode(task.getInvoiceNumber());
			invoice.setInvoDate(task.getInvoiceDate());
			invoice.setInvoPersonName(task.getDrawer());
			invoice.setInvoType(task.getInvoiceTypeCode());
			invoice.setRECID(context.newRECID());
			invoice.setTenantsGuid(context.find(Login.class).getTenantId());
			
			Employee loginEmployee = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			invoice.setDeptGuid(loginEmployee.getDepartmentId());

			InvoiceDaoTask dTask = new InvoiceDaoTask();
			dTask.setInvoice(invoice);
			context.handle(dTask, InvoiceDaoTask.Method.INSERT);

		}

	}

	  
	/**
	 * 零售交款确认
	 * 
	 * 
	 */
	@Publish
	protected class ConfirmRetailRecordHandle extends
			SimpleTaskMethodHandler<ConfirmRetailRecordTask> {

		@Override
		protected void handle(Context context, ConfirmRetailRecordTask task)
				throws Throwable {
			InternalRetailReceiptTask iTask = new InternalRetailReceiptTask();
			iTask.setRecid(task.getId());
			context.handle(iTask, InternalRetailReceiptTask.Method.Confirm);
		}

	}
	
	/**
	 * 往来初始化保存
	 * 
	 */
	@Publish
	protected class InitBalanceHandle extends
			TaskMethodHandler<InitBalanceTask,InitBalanceTask.Method> {

		protected InitBalanceHandle() {
			super(InitBalanceTask.Method.Save);
		}

		@Override
		protected void handle(Context context, InitBalanceTask task)
				throws Throwable {
			if(CheckIsNull.isEmpty(task.getItems()))
			{
				return;
			}
			InitDealingTask.Item[] dItems = new InitDealingTask.Item[task.getItems().length];
			for(int i=0;i<task.getItems().length;i++)
			{
				InitBalanceTask.Item item = task.getItems()[i];
				InitDealingTask.Item dItem = new InitDealingTask.Item(item.getPartnerId(),item.getPartnerType(),item.getAmount());
				dItems[i] = dItem;
			}
			InitDealingTask dTask = new InitDealingTask(dItems, task.getPartnerType());
			context.handle(dTask, InitDealingTask.Method.Save);
		}
	}
	
	/**
	 * 往来初始化完成
	 * 
	 */
	@Publish
	protected class FinishInitBalanceHandle extends
			TaskMethodHandler<InitBalanceTask,InitBalanceTask.Method> {

		protected FinishInitBalanceHandle() {
			super(InitBalanceTask.Method.Finish);
		}

		@Override
		protected void handle(Context context, InitBalanceTask task)
				throws Throwable {
//			if(CheckIsNull.isEmpty(task.getItems()))
//			{
//				return;
//			}
//			InitDealingTask.Item[] dItems = new InitDealingTask.Item[task.getItems().length];
//			for(int i=0;i<task.getItems().length;i++)
//			{
//				InitBalanceTask.Item item = task.getItems()[i];
//				InitDealingTask.Item dItem = new InitDealingTask.Item(item.getPartnerId(),item.getPartnerType(),item.getAmount());
////				dItems[i] = dItem;
//			}
			InitDealingTask dTask = new InitDealingTask(null, task.getPartnerType());
			context.handle(dTask, InitDealingTask.Method.Finish);
			
			/**
			 * 租户已完成初始化标记
			 */
			TenantsSysParamTask sTask = new TenantsSysParamTask(SysParamKey.CUSPRO_INIT, true);
			context.handle(sTask);
			
		}
	}
	
	/**
	 * 往来初始化查询
	 */
	@Publish
	protected class GetInitBalanceItem  extends OneKeyResultListProvider<BalanceItem, GetInitBalanceItemListKey>
	{

		@Override
		protected void provide(Context context, GetInitBalanceItemListKey key,
				List<BalanceItem> resultList) throws Throwable {
			GetInitDealingListKey dKey = new GetInitDealingListKey(key.getPartnerType());
			List<Dealing> list = context.getList(Dealing.class, dKey);
			if(CheckIsNull.isEmpty(list))
			{
				return;
			}
			for(Dealing dealing:list)
			{
				resultList.add(AccountUtil.getBalanceItem(context, dealing,true));
			}
		}
		
	}
	/**
	 * 查询已设置初始化往来金额客户总数
	 */
	@Publish
	protected class GetInitedPartnerSummary extends OneKeyResultProvider<Integer, GetInitedPartnerSummaryKey>
	{

		@Override
		protected Integer provide(Context context,
				GetInitedPartnerSummaryKey key) throws Throwable {
			GetInitDealingListKey dKey = new GetInitDealingListKey(key.getPartnerType());
			List<Dealing> list = context.getList(Dealing.class, dKey);
			if(null==list)
			{
				return 0;
			}
			return list.size();
		}
		
	}
}
