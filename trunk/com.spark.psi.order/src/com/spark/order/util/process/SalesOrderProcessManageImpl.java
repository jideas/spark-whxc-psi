package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.GetCustomerAvailableCreditAmountKey;
import com.spark.psi.base.key.OverPeriodAmountKey;

/**
 * <p>
 * ȱʡ�Ե�ǰ������˶�ȵ��жϣ�5-10�Ѹģ�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * 
 * @version 2012-3-14
 */
class SalesOrderProcessManageImpl extends ProcessManageImpl {
	public SalesOrderProcessManageImpl(Context context) {
		super(context, BillsEnum.SALE);
	}

	@Override
	public StatusEnum prov(GUID orderId) {
		return super.prov(orderId);
	}

	public StatusEnum next(GUID orderId) {
		StatusEnum status = null;
		SaleOrderInfo info = getOrderInfo(orderId);
		StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
		oldstatus = oldstatus == StatusEnum.Approveing ? StatusEnum.Approve : oldstatus;
		switch (oldstatus) {
		case Submit:
			this.orderDepartment = getMyDeptGuid();
			if ((UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE)) || !isHaveExam(info)) {
				status = StatusEnum.Store_N0;
			} else if (UserAuthEnum.MANGER == BillsConstant.getUserAuth(context, BillsEnum.SALE) && !super.isLimited()) {

				status = StatusEnum.Store_N0;

			} else {
				status = StatusEnum.Approve;
			}
			break;
		case Approve:
			status = StatusEnum.Store_N0;
			break;
		case Approveing:
			status = StatusEnum.Store_N0;
			break;
		case Return:
			this.orderDepartment = getMyDeptGuid();
			if ((UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE)) || !isHaveExam(info)) {
				status = StatusEnum.Store_N0;
			} else {
				status = StatusEnum.Approve;
			}
			break;
		}
		return status;
	}

	@Override
	protected boolean isHaveExam(OrderInfo info) {
		return super.isHaveExam(info) || getCreditAmount(info.getPartnerId()) - info.getTotalAmount() < 0
				|| isCreditExpired(info.getPartnerId());// getAccountPeriod(info.getCuspGuid())
														// >= 0;
	}

	/**
	 * ��ȡ�ͻ��������ö�� context.find(Double.class,GetCustomerAvailableCreditAmountKey)
	 * 
	 * @param customerId
	 * @return Double
	 */
	private Double getCreditAmount(GUID customerId) {
		// ��ȡ�ͻ��������ö��
		// context.find(Double.class,GetCustomerAvailableCreditAmountKey)
		GetCustomerAvailableCreditAmountKey creditKey = new GetCustomerAvailableCreditAmountKey(customerId);
		return context.find(Double.class, creditKey);
	}

	/**
	 * �ͻ��Ƿ��ѳ����ڳ��ⵥ(�����ڷ���true)
	 * 
	 * @param customerId
	 * @return boolean
	 */
	private boolean isCreditExpired(GUID customerId) {
		// Long orderDate = // ����һ��δ�����ѳ��ⶩ���ĳ�������
		// context.find(Long.class, new
		// GetRemindDateByPartnerIdKey(customerId));
		// orderDate = orderDate == null ? 0 : orderDate;
		// if (orderDate > 0) {
		// // �����Ƿ��ѳ����� ���㹫ʽ: (��ǰ���� ��ȥ ����һ���ѳ���δ����ĳ��ⵥ��ȷ�ϳ�������) ת�������� ��ȥ ��������
		// // ,����������0,���ʾ�ѳ�����
		// // ����ע�� �� �ѳ����ڽ�� ���� ���� ��������С�ڣ�֮ǰ����ǰ���ڼ�ȥ�����������ѳ���δ����ĳ��ⵥ�Ľ��ϼơ�
		// boolean creditExpired = false;
		// Calendar orderDateCal = Calendar.getInstance();
		// orderDateCal.setTimeInMillis(orderDate);
		// Partner entity = context.find(Partner.class, customerId);
		// orderDateCal.add(Calendar.DATE, entity.getAccountPeriod());
		// creditExpired = orderDateCal.compareTo(Calendar.getInstance()) == -1;
		// return creditExpired;
		// }
		// return false;
		// CustomerOverCredit coc = context.find(CustomerOverCredit.class,new
		// GetCustomerOverCreditDayKey(customerId));
		// return 0 != coc.getOverCreditAmount();
		OverPeriodAmountKey overPeriodAmountKey = new OverPeriodAmountKey();
		overPeriodAmountKey.setPartnerId(customerId);
		Partner p = context.find(Partner.class, customerId);
		overPeriodAmountKey.setAccountPeriod(p.getAccountPeriod());
		Double result = context.find(Double.class, overPeriodAmountKey);
		return null != result && 0 != result;
	}

	@Override
	protected SaleOrderInfo getOrderInfo(GUID orderId) {
		if (CheckIsNull.isNotEmpty(this.orderInfo) && orderInfo instanceof SaleOrderInfo) {
			return (SaleOrderInfo) orderInfo;
		}
		return context.find(SaleOrderInfo.class, orderId);
	}

}
