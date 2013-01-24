/**
 * 
 */
package com.spark.psi.publish;

import java.util.List;

/**
 *
 */
public enum SMessageType {
	UnreadNotices("01", "未读公告"), //
	UnapproveOrders("02", "待审批单据"), //
	UndistributeOrders("03", "待销售配货"), //
	UncheckoutOrders("04", "待确认出库"), //
	UncheckinOrders("05", "待确认入库"), //
	NearBirthday("06", "生日提醒"), //
	NearOrderDate("07", "业务单据预警"), //
	GoodsInventory("08", "商品库存预警"), DeliveryException("09", "配送异常");
	private String code;
	private String title;

	private SMessageType(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public boolean getDefShowMonitor(List<Auth> list) {
		switch (this) {
		case UnreadNotices:
			return true;
		case UnapproveOrders:
			return true;
		case UndistributeOrders:
			return true;
		case UncheckoutOrders:
			return true;
		case UncheckinOrders:
			return true;
		case NearBirthday:
			if (list.contains(Auth.Assistant)) {
				return true;
			}
			return false;
		case NearOrderDate:
			if (list.contains(Auth.Purchase) || list.contains(Auth.PurchaseManager) || list.contains(Auth.Boss)
					|| list.contains(Auth.Sales) || list.contains(Auth.SalesManager)) {
				return true;
			}
			return false;
		case GoodsInventory:
			if (!list.contains(Auth.Boss)) {
				return true;
			}
			return false;
		case DeliveryException:
			if (list.contains(Auth.Distribute)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public static SMessageType getType(String code) {
		if (null == code || code.trim().length() == 0) {
			return null;
		}
		int c = Integer.parseInt(code);
		switch (c) {
		case 1:
			return UnreadNotices;
		case 2:
			return UnapproveOrders;
		case 3:
			return UndistributeOrders;
		case 4:
			return UncheckoutOrders;
		case 5:
			return UncheckinOrders;
		case 6:
			return NearBirthday;
		case 7:
			return NearOrderDate;
		case 8:
			return GoodsInventory;
		case 9:
			return DeliveryException;
		}
		return null;
	}
}
