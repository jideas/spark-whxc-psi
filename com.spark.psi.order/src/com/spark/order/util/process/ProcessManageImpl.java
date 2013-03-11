package com.spark.order.util.process;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.psi.base.ApprovalConfig;

/**
 * <p>���̿��Ƴ�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-14
 */
abstract class ProcessManageImpl implements IProcessManage{
	protected Context context;
	protected BillsEnum billsEnum;
	protected OrderInfo orderInfo;
	public ProcessManageImpl(Context context, BillsEnum billsEnum){
		this.context = context;
		this.billsEnum = billsEnum;
	}
	
	protected GUID orderDepartment; 
	
	public GUID getOrderDepartment() {
		return orderDepartment == null?orderInfo.getDeptId():orderDepartment;
	} 

//	public StatusEnum next(GUID orderId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	public StatusEnum prov(GUID orderId) {
		StatusEnum status = null;
		OrderInfo info = getOrderInfo(orderId);
		StatusEnum oldstatus = StatusEnum.getstatus(info.getStatus());
		switch (oldstatus) {
		case Approve:
			status = StatusEnum.Return;
			break;
		case Approveing:
			status = StatusEnum.Return;
			break;
		}
		return status;
	}

	public void setOrderInfo(OrderInfo order) {
		this.orderInfo = order;
	}
	
	/**
	 * ��ö���ʵ��
	 * @param orderId
	 * @return OrderInfo
	 */
	protected abstract OrderInfo getOrderInfo(GUID orderId);
	//------------------------���̹���--------------------------
	/**
	 * ��ȡ������˿�����������Զ��������
	 * @return ApprovalConfig
	 */
	protected ApprovalConfig getApprovalConfig(){
		return context.find(ApprovalConfig.class);
	}
	
	/**
	 * ����޶���ȣ����ж��Ƿ������
	 * @return Double
	 */
	protected double getLimitAmount(){
		ApprovalConfig ac = getApprovalConfig();
		double limitAmount = -1;
		switch (billsEnum) {
		case PURCHASE:
			limitAmount = ac.getPurchaseApprovalLimit();
			break;
		case PURCHASE_CANCEL:
			limitAmount = ac.getPurchaseReturnApprovalLimit();
			break;
		case SALE:
			limitAmount = ac.getSalesApprovalLimit();
			break;
		case SALE_CANCEL:
			limitAmount = ac.getSalesReturnApprovalLimit();
			break;
		}
		return limitAmount == -1 ? Double.MAX_VALUE:limitAmount; 
	}
	
	/**
	 * �ж϶����Ƿ��Ѿ��������
	 * @return boolean
	 */
	protected boolean isLimited(){
		ApprovalConfig ac = getApprovalConfig();
		switch (billsEnum) {
		case PURCHASE:
			return ac.isPurchaseOrderNeedApproval();
		case PURCHASE_CANCEL:
			return ac.isPurchaseReturnNeedApproval();
		case SALE:
			return ac.isSalesOrderNeedApproval();
		case SALE_CANCEL:
			return ac.isSalesReturnNeedApproval();
		}
		return true;
	}
	
	/**
	 * �жϵ����Ƿ������
	 * @param totalAmount
	 * @return boolean
	 */
	protected boolean isHaveExam(OrderInfo orderInfo){
		return isLimited() || orderInfo.getTotalAmount() >= getLimitAmount();
	}
	
	/**
	 * ����ύʱ����ĵ�����������
	 * @return GUID
	 */
	protected GUID getMyDeptGuid(){
		return BillsConstant.getUser(context).getDepartmentId();
	}
}
