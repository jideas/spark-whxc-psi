package com.spark.psi.account.intf.util;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.account.intf.entity.dealing.Dealing;
import com.spark.psi.account.intf.entity.dealing.DealingItem;
import com.spark.psi.account.intf.entity.dealing.pri.DueDealings;
import com.spark.psi.account.intf.entity.invoice.Invoice;
import com.spark.psi.account.intf.entity.pay.PaymentEntity;
import com.spark.psi.account.intf.entity.receipt.ReceiptEntity;
import com.spark.psi.account.intf.entity.receipt.RetailReceipt;
import com.spark.psi.account.intf.publish.entity.BalanceInfoImpl;
import com.spark.psi.account.intf.publish.entity.BalanceItemImpl;
import com.spark.psi.account.intf.publish.entity.DealingAdjustInfoImpl;
import com.spark.psi.account.intf.publish.entity.BalanceInfoItemImpl;
import com.spark.psi.account.intf.publish.entity.InvoiceItemImpl;
import com.spark.psi.account.intf.publish.entity.PaymentItemImpl;
import com.spark.psi.account.intf.publish.entity.ReceiptItemImpl;
import com.spark.psi.account.intf.publish.entity.RetailSubmitingItemImpl;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.GetCustomerAvailableCreditAmountKey;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.base.key.RemindingAmountKey;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.account.entity.BalanceInfo;
import com.spark.psi.publish.account.entity.BalanceItem;
import com.spark.psi.publish.account.entity.DealingAdjustInfo;
import com.spark.psi.publish.account.entity.BalanceInfoItem;
import com.spark.psi.publish.account.entity.InvoiceItem;
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.RetailSubmitingItem;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;

/**
 * <p>TODO 类描述</p>
 *
 */

public class AccountUtil {
	
	/**
	 * 余额列表
	 * @param context 
	 * 
	 * @param dealing
	 * @param isInit 
	 * @return BalanceItem
	 */
	public static BalanceItem getBalanceItem(Context context, Dealing dealing, boolean isInit) {

		BalanceItemImpl impl = new BalanceItemImpl();
		if(isInit)
		{
			impl.setAmount(dealing.getInitAmount());
		}
		else
		{
			impl.setAmount(dealing.getAmount());
		}
		impl.setPartnerId(dealing.getId());
		Partner p = context.find(Partner.class,dealing.getId());
		if(CheckIsNull.isNotEmpty(p))
		{
			impl.setPartnerName(p.getName());
			impl.setPartnerShortName(p.getShortName());
		}
		
		return impl;
	}

	/**
	 * 往来明细
	 * @param context 
	 * 
	 * @param item
	 * @return DealingsItem
	 */
	public static BalanceInfoItem getDealingsItem(Context context, DealingItem item) {
		BalanceInfoItemImpl impl = new BalanceInfoItemImpl();
		
		impl.setId(item.getId());
		impl.setBalance(item.getBalance());
		if(item.getBillsType() != null) {
			impl.setBillsType(DealingsType.getEnum(item.getBillsType()));
			if(item.getBillsType().equals(DealingsType.CUS_TZYS.getCode()) 
					|| item.getBillsType().equals(DealingsType.PRO_TZYF.getCode())) {
				impl.setShowLink(true);
			}
		}
		if(CheckIsNull.isNotEmpty(item.getReceiptType()))
		{
			impl.setReceiptType(DealingsWay.getDealingsWay(item.getReceiptType()));
		}
		impl.setRealAmount(item.getRealAmount());
		impl.setPlanAmount(item.getPlanAmount());
		impl.setCreateDate(item.getCreateDate());
		impl.setAccountBillsId(item.getAccountBillsId());
		impl.setAccountBillsNo(item.getAccountBillsNo());
		impl.setBillsId(item.getBillsId());
		impl.setBillsNo(item.getBillsNo());
		
		return impl;
	}
	
	public static BalanceInfo getBalanceInfo(Context context, Dealing dealing) {
		BalanceInfoImpl balanceInfo = new BalanceInfoImpl();
		PartnerInfo partnerInfo;
		if(PartnerType.Customer.getCode().equals(dealing.getType())) {
			partnerInfo = context.find(CustomerInfo.class, dealing.getPartnerId());
		} else {
			partnerInfo = context.find(SupplierInfo.class, dealing.getPartnerId());
		}
		if(null != partnerInfo) {
			balanceInfo.setPartnerName(partnerInfo.getName());
			balanceInfo.setPartnerId(dealing.getId());
		}
		balanceInfo.setAmount(dealing.getAmount());
		return balanceInfo;
	}
	
	public static DealingAdjustInfo getDealingAdjustInfo(DealingItem item) {
		DealingAdjustInfoImpl adjustInfo = new DealingAdjustInfoImpl();
		adjustInfo.setAdjustAmount(item.getPlanAmount());
		adjustInfo.setAdjustDate(item.getCreateDate());
		adjustInfo.setAdjustReason(item.getRemark());
		adjustInfo.setSheetNumber(item.getBillsNo());
		return adjustInfo;
	}
	/**
	 * 排序枚举转字符串
	 * 
	 * @param sortType
	 * @return Object
	 */
	public Object getSortTypeString(SortType sortType) {
		String sortTypeString;
		if(SortType.Asc.equals(sortType))
		{
			sortTypeString = " asc ";
		}
		else
		{
			sortTypeString = " desc ";
		}
		return sortTypeString;
	}

	

	/**
	 * 开票记录列表
	 * @param context 
	 * 
	 * @param invoice
	 * @return InvoiceItem
	 */
	public static InvoiceItem getInvoiceItem(Context context, Invoice invoice) {
		
		InvoiceItemImpl impl = new InvoiceItemImpl();
		impl.setAmount(invoice.getInvoAmount());
		impl.setCustomerId(invoice.getCusproGuid());
		impl.setCustomerName(invoice.getCusproName());
		impl.setCustomerFullName(invoice.getCusproFullName());
		impl.setDate(invoice.getInvoDate());
		impl.setDrawer(invoice.getInvoPersonName());
		impl.setRecorder(invoice.getCreatePerson());
		impl.setId(invoice.getRECID());
		EnumEntity enumEntity = context.find(EnumEntity.class, EnumType.InvoiceType, invoice.getInvoType());
		impl.setInvoiceType(enumEntity.getCode());
		impl.setInvoiceTypeName(enumEntity.getName());
		impl.setInvoiceNumber(invoice.getInvoCode());
		impl.setInvalided(invoice.isInvalided());
		impl.setInvalidPerson(invoice.getInvalidPerson());
		impl.setInvalidDate(invoice.getInvalidDate());
		impl.setInvalidReason(invoice.getInvalidReason());
		return impl;
	}

	

	/**
	 * 零售交款记录
	 * 
	 * @param rr
	 * @return RetailSubmitingItem
	 */
	public static RetailSubmitingItem getRetailSubmitingItem(RetailReceipt rr) {
		RetailSubmitingItemImpl item = new RetailSubmitingItemImpl();
		item.setAmount(rr.getShouldMoney());
		item.setBeginDate(rr.getReceiptDate());
		item.setCardRecordAmount(rr.getShouldCardMoney());
		item.setCardRecordCount(rr.getShouldCardCount());
		item.setId(rr.getRECID());
		item.setSalesName(rr.getSaleEmpName());
		return item;
	}

	
	

}
