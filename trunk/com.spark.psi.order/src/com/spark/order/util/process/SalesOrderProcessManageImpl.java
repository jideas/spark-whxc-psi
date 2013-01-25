package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.exceptions.DataStatusExpireException;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.util.SalesUtil;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.base.key.GetCustomerAvailableCreditAmountKey;
import com.spark.psi.base.key.GetCustomerOverCreditDayKey;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;

/**
 * <p>缺省对当前经理审核额度的判断（5-10已改）</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-14
 */
class SalesOrderProcessManageImpl extends ProcessManageImpl{
	public SalesOrderProcessManageImpl(Context context) {
		super(context, BillsEnum.SALE);
	}
	
	@Override
	public StatusEnum prov(GUID orderId) {
		this.examDept = getBeginExamDeptId(orderInfo.getDeptId());
		return super.prov(orderId);
	}

	public StatusEnum next(GUID orderId) {
		StatusEnum status = null;
		SaleOrderInfo info = getOrderInfo(orderId);
		StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
		oldstatus = oldstatus == StatusEnum.Approveing?StatusEnum.Approve:oldstatus;
		switch (oldstatus) {
		case Submit:
			this.orderDepartment = getMyDeptGuid();
			if((UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE)) || !isHaveExam(info)){
				status = StatusEnum.Store_N0;
			}
			else if(UserAuthEnum.MANGER == BillsConstant.getUserAuth(context, BillsEnum.SALE) && !super.isLimited()){
				info.setExamDeptGuid(getMyDeptGuid());
				if(isNextExam(info)){
					this.examDept = getNextExamDeptId(info.getExamDeptGuid());
					status = StatusEnum.Approve;
				}
				else{
					status = StatusEnum.Store_N0;
				}
			}
			else{
				this.examDept = getBeginExamDeptId(getMyDeptGuid());
				status = StatusEnum.Approve;
			}
			break;
		case Approve:
			if(isNextExam(info)){
				if(!getMyDeptGuid().equals(info.getExamDeptGuid())){
					throw new DataStatusExpireException();
				}
				this.examDept = getNextExamDeptId(info.getExamDeptGuid());
				status = StatusEnum.Approve;
			}
			else{
				status = StatusEnum.Store_N0;
			}
			break;
		case Approveing:
			if(isNextExam(info)){
				if(!getMyDeptGuid().equals(info.getExamDeptGuid())){
					throw new DataStatusExpireException();
				}
				this.examDept = getNextExamDeptId(info.getExamDeptGuid());
				status = StatusEnum.Approve;
			}
			else{
				status = StatusEnum.Store_N0;
			}
			break;
		case Return:
			this.orderDepartment = getMyDeptGuid();
			if((UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE)) || !isHaveExam(info)){
				status = StatusEnum.Store_N0;
			}
			else{
				this.examDept = getBeginExamDeptId(getMyDeptGuid());
				status = StatusEnum.Approve;
			}
			break;
		}
		return status;
	}
	@Override
	protected boolean isHaveExam(OrderInfo info) {
		return super.isHaveExam(info) || getCreditAmount(info.getPartnerId()) - info.getTotalAmount() < 0 || isCreditExpired(info.getPartnerId());//getAccountPeriod(info.getCuspGuid()) >= 0;
	}
	
	/**
	 * 是否需要下级审核
	 * @param info
	 * @return boolean
	 */
	private boolean isNextExam(SaleOrderInfo info){
		if(UserAuthEnum.BOSS == BillsConstant.getUserAuth(context, BillsEnum.SALE)){
			return false;
		}
		SalesmanCredit sc = SalesUtil.getSalesmanCredit(context, BillsConstant.getUserGuid(context));
		if(null == sc){
			return true;
		}
		if(info.getTotalAmount() > sc.getOrderApprovalLimit()){
			return true;
		}
		double credit = getCreditAmount(info.getPartnerId());
		CustomerInfo customer = context.find(CustomerInfo.class, info.getPartnerId());
		if(credit - info.getTotalAmount() < 0 && customer.getCreditAmount() - credit + info.getTotalAmount() > sc.getCustomerCreditLimit()){
			return true;
		}
		return false;
	}
	
	/**
	 * 获得初始审核部门
	 * @return GUID
	 */
	private GUID getBeginExamDeptId(GUID dept){
		return SalesUtil.getInitSalesApproveDept(context, dept);
	}
	
	/**
	 * 获得下级审核部门
	 * @param guid 
	 * @return boolean
	 */
	private GUID getNextExamDeptId(GUID deptId){
		return SalesUtil.getSuperiorSalesManagerDeptByManagerCreate(context, deptId);
	}
	
	/**
	 * 获取客户可用信用额度	context.find(Double.class,GetCustomerAvailableCreditAmountKey)
	 * @param customerId
	 * @return Double
	 */
	private Double getCreditAmount(GUID customerId){
//		获取客户可用信用额度	context.find(Double.class,GetCustomerAvailableCreditAmountKey)
		GetCustomerAvailableCreditAmountKey creditKey = new GetCustomerAvailableCreditAmountKey(customerId);
		return context.find(Double.class, creditKey);
	}
	
	/**
	 * 获取客户已超账期天数	context.find(Integer.class,GetCustomerOverCreditDayKey)
	 * @param customerId
	 * @return Integer
	 */
	@Deprecated
	private Integer getAccountPeriod(GUID customerId){
//		获取客户已超账期天数	context.find(Integer.class,GetCustomerOverCreditDayKey)
		GetCustomerOverCreditDayKey creditDayKey = new GetCustomerOverCreditDayKey(customerId);
		Integer i = context.find(Integer.class, creditDayKey);
		return i==null?0:i;
	}

	/**
	 * 客户是否已超账期出库单(超账期返回true)
	 * 
	 * @param customerId
	 * @return boolean
	 */
	private boolean isCreditExpired(GUID customerId) {
//		Long orderDate = // 最早一笔未付款已出库订单的出库日期
//		context.find(Long.class, new GetRemindDateByPartnerIdKey(customerId));
//		orderDate = orderDate == null ? 0 : orderDate;
//		if (orderDate > 0) {
//			// 计算是否已超账期 计算公式: (当前日期 减去 最早一张已出库未付款的出库单的确认出库日期) 转换成天数 减去 账期天数
//			// ,如果结果大于0,则表示已超账期
//			// 延伸注释 ： 已超账期金额 等于 所有 出库日期小于（之前）当前日期减去账期天数的已出库未付款的出库单的金额合计。
//			boolean creditExpired = false;
//			Calendar orderDateCal = Calendar.getInstance();
//			orderDateCal.setTimeInMillis(orderDate);
//			Partner entity = context.find(Partner.class, customerId);
//			orderDateCal.add(Calendar.DATE, entity.getAccountPeriod());
//			creditExpired = orderDateCal.compareTo(Calendar.getInstance()) == -1;
//			return creditExpired;
//		}
//		return false;
//		CustomerOverCredit coc = context.find(CustomerOverCredit.class,new GetCustomerOverCreditDayKey(customerId));
//		return 0 != coc.getOverCreditAmount();
		OverPeriodAmountKey overPeriodAmountKey = new OverPeriodAmountKey();
		overPeriodAmountKey.setPartnerId(customerId);
		Partner p = context.find(Partner.class, customerId);
		overPeriodAmountKey.setAccountPeriod(p.getAccountPeriod());
		Double result = context.find(Double.class,overPeriodAmountKey);
		return null != result && 0 != result;
	}

	@Override
	protected SaleOrderInfo getOrderInfo(GUID orderId) {
		if(CheckIsNull.isNotEmpty(this.orderInfo) && orderInfo instanceof SaleOrderInfo){
			return (SaleOrderInfo) orderInfo;
		}
		return context.find(SaleOrderInfo.class, orderId);
	}


}
