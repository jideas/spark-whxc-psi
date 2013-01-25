package com.spark.order.intf.type;

import java.util.ArrayList;
import java.util.List;

import com.spark.order.intf.OrderConstant2;
import com.spark.order.intf.OrderEnum;
import com.spark.psi.publish.OrderStatus;

/**
 * <p>
 * 订单流程控制
 * </p>
 */
// 待审批、未入库、部分入库、全部入库、未发货、已发货、已退回、已中止、已完成
public enum StatusEnum {
	/**
	 * "01", "待提交", "待提交", "未确认"
	 */
	Submit("01", "待提交"),
	/**
	 * 待审批单据，只要该当前人审批的。
	 */
	Approveing("02", "待审批"),
	/**
	 * "02", "待审批", "待审批", "待审批"
	 */
	Approve("02", "待审批"),
	/**
	 * 未入库（出库）
	 */
	Store_N0("03", "未入库", "未出库"),
	/**
	 * 部分入库（出库）
	 */
	Store_Part("04", "部分入库", "部分出库"),
	/**
	 * 全部入库（出库）
	 */
	Store_All("05", "全部入库", "全部出库"),
	/**
	 * 已驳回
	 */
	Return("06", "已退回"),
	/**
	 * "07", "已完成", "已完成", "已售完"
	 */
	Finished("07", "已完成"),
	/**
	 * 未发货
	 */
	Consignment_No("08", "未发货"),
	/**
	 * 已发货
	 */
	Consignment_Yes("09", "已发货"),
//	 ---------零售-------
	/**
	 * 挂单
	 */
	Working("21"),
	/**
	 * 待收款
	 */
	Receipt("22");

	/**
	 * 判断指定key是否是当前状态
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean isThis(String key) {
		return this.getKey().equals(key);
	}
	
	/**
	 * 当前状态是否是指定状态中的一种
	 * @param enums
	 * @return boolean
	 */
	public boolean isIn(StatusEnum... enums){
		for(StatusEnum s : enums){
			if(this == s){
				return true;
			}
		}
		return false; 
	}

	private final String id;
	private final String[] names;

	private StatusEnum(String key, String... names) {
		this.id = key;
		this.names = names;
		EnumUtil.StatusEnumMap.put(key, this);
		for(OrderEnum orderEnum : OrderEnum.values()){
			EnumUtil.addOrderStatusSearch(orderEnum, key, getName(orderEnum));
			EnumUtil.addOrderStatusSearch(orderEnum, OrderConstant2.stopCode, OrderConstant2.stopName);
		}
	}
	
	/**
	 * 搜索用，返回code集合
	 * @param searchText
	 * @return String[]
	 */
	public static List<String> searchstatus(OrderEnum orderEnum, String searchText){
		return EnumUtil.getOrderStatusSearch(orderEnum, searchText);
	}
	
	/**
	 * 将外部状态转换成内部状态数组
	 * @param pubstatuss
	 * @return PromotionStatus2[]
	 * @throws Throwable 
	 */
	public static StatusEnum[] getPubToMe(OrderStatus... pubstatuss) throws Throwable{
		List<StatusEnum> list = new ArrayList<StatusEnum>();
		for(OrderStatus status : pubstatuss){
			StatusEnum se = getPubToMe(status);
			if(null != se){
				list.add(se);
			}
		}
		return list.toArray(new StatusEnum[list.size()]);
	}
	
	/**
	 * 根据外部状态获取内部状态
	 * @param pubstatus
	 * @return PromotionStatus2
	 * @throws Throwable 
	 */
	public static StatusEnum getPubToMe(OrderStatus pubstatus) throws Throwable{
		StatusEnum status = null;
		switch (pubstatus) {
		case Submit:
			status = StatusEnum.Submit;
			break;
		case Approval_No:
			status = StatusEnum.Approveing;
			break;
		case Approval_Yes:
			status = StatusEnum.Approve;
			break;
		case CheckedIn:
			status = StatusEnum.Store_All;
			break;
		case CheckedOut:
			status = StatusEnum.Store_All;
			break;
		case CheckingIn_NO:
			status = StatusEnum.Store_N0;
			break;
		case CheckingIn_Part:
			status = StatusEnum.Store_Part;
			break;
		case CheckingOut_No:
			status = StatusEnum.Store_N0;
			break;
		case CheckingOut_Part:
			status = StatusEnum.Store_Part;
			break;
		case CONSIGNMENT_NO:
			status = StatusEnum.Consignment_No;
			break;
		case CONSIGNMENT_YES:
			status = StatusEnum.Consignment_Yes;
			break;
		case Denied:
			status = StatusEnum.Return;
			break;
		case finish:
			status = StatusEnum.Finished;
			break;
		case Stop:
			status = null;
			break;
		default:
			throw new Throwable("当前外部枚举转换未设置，请到StatusEnum中设置对应关系");
		}
		return status;
	}

	/**
	 * 获得状态标识
	 * 
	 * @return String
	 */
	public String getKey() {
		return id;
	}
	/**
	 * 获取当前节点名称
	 * 
	 * @param e
	 * @return String
	 */
	@Deprecated
	public String getName(BillsEnum e) {
		if ((BillsEnum.SALE == e || BillsEnum.PURCHASE_CANCEL == e)
				&& names.length > 1) {
			return names[1];
		} else if (BillsEnum.SALE_PROMOTION == e && names.length > 2) {
			return names[2];
		} else if (names.length > 0) {
			return names[0];
		}
		return null;
	}
	
	/**
	 * 获得当前枚举在指定类型订单中的名称
	 * @param e
	 * @return String
	 */
	public String getName(OrderEnum e){
		if(e.isIn(OrderEnum.Sales, OrderEnum.Purchase_Return) && names.length > 1){
			return names[1];
		}
		else if(names.length > 0){
			return names[0];
		}
		return null;
	}

	/**
	 * 销售订单
	 * 
	 * @return String
	 */
	public String getSalesOrderStatusName() {
		return getName(OrderEnum.Sales);
	}

	/**
	 * 销售退货
	 * 
	 * @return String
	 */
	public String getSalesReturnstatusName() {
		return getName(OrderEnum.Sales_Return);
	}

	/**
	 * 采购订单
	 * 
	 * @return String
	 */
	public String getPurchaseOrderStatusName() {
		return getName(OrderEnum.Purchase);
	}

	/**
	 * 采购退货
	 * 
	 * @return String
	 */
	public String getPurchaseReturnstatusName() {
		return getName(OrderEnum.Purchase_Return);
	}

	/**
	 * 获取指定标识节点的节点名称
	 * 
	 * @param key
	 * @return String
	 */
	public static StatusEnum getstatus(String key) {
		return EnumUtil.StatusEnumMap.get(key);
	}
}
