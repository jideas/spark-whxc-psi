/**
 * 
 */
package com.spark.psi.publish;

/**
 *
 */
public enum SMessageTemplateEnum {

	UnreadNotices01("0101", "未读公告", "", null), UnapproveOrders01("0201", "待审批单据", "采购", null), UnapproveOrders02(
			"0202", "待审批单据", "销售", null), UnapproveOrders03("0203", "待审批单据", "采退", null), UnapproveOrders04("0204",
			"待审批单据", "销售退货", null),
	// UnapproveOrders05("0205", "待审批单据", "促销", null),
	UnapproveOrders06("0206", "待审批单据", "调拨", null),

	UndistributeOrders01("0301", "待销售配货", "", null),

	UncheckoutOrders01("0401", "待确认出库", "销售", null), UncheckoutOrders02("0402", "待确认出库", "采退", null), UncheckinOrders01(
			"0501", "待确认入库", "采购", null), UncheckinOrders02("0502", "待确认入库", "销售退货", null),

	NearBirthday01("0601", "生日提醒", "", null),

	NearOrderDate01("0701", "业务单据预警", "采购临近", null), NearOrderDate02("0702", "业务单据预警", "采购已过", null), NearOrderDate03(
			"0703", "业务单据预警", "销售临近", null), NearOrderDate04("0704", "业务单据预警", "销售已过", null),

	GoodsInventory01("0801", "商品库存预警", "分类金额大于上线", null), GoodsInventory02("0802", "商品库存预警", "金额大于单库上线", null), GoodsInventory03(
			"0803", "商品库存预警", "数量小于单库下线", null), GoodsInventory04("0804", "商品库存预警", "数量大于单库上线", null), GoodsInventory05(
			"0805", "商品库存预警", "金额大于总上线", null), GoodsInventory06("0806", "商品库存预警", "数量小于总下线", null), GoodsInventory07(
			"0807", "商品库存预警", "数量大于总上线", null),

	DeliveryException01("0901", "配送异常", "", null), //

	// ShelfLifeWarning01("1001", "临近保质期", "", null),
	// ShelfLifeWarning02("1002", "已过保质期", "", null);
	;

	private String code;

	private SMessageTemplateEnum(String code, String type, String detail, Object obj) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public static SMessageTemplateEnum getTemplate(String code) {
		if ("0101".equals(code)) {
			return UnreadNotices01;
		} else if ("0201".equals(code)) {
			return UnapproveOrders01;
		} else if ("0202".equals(code)) {
			return UnapproveOrders02;
		} else if ("0203".equals(code)) {
			return UnapproveOrders03;
		} else if ("0204".equals(code)) {
			return UnapproveOrders04;
		}
		// else if("0205".equals(code)){
		// return UnapproveOrders05;
		// }
		else if ("0206".equals(code)) {
			return UnapproveOrders06;
		} else if ("0301".equals(code)) {
			return UndistributeOrders01;
		} else if ("0401".equals(code)) {
			return UncheckoutOrders01;
		} else if ("0402".equals(code)) {
			return UncheckoutOrders02;
		} else if ("0501".equals(code)) {
			return UncheckinOrders01;
		} else if ("0502".equals(code)) {
			return UncheckinOrders02;
		} else if ("0601".equals(code)) {
			return NearBirthday01;
		} else if ("0701".equals(code)) {
			return NearOrderDate01;
		} else if ("0702".equals(code)) {
			return NearOrderDate02;
		} else if ("0703".equals(code)) {
			return NearOrderDate03;
		} else if ("0704".equals(code)) {
			return NearOrderDate04;
		} else if ("0801".equals(code)) {
			return GoodsInventory01;
		} else if ("0802".equals(code)) {
			return GoodsInventory02;
		} else if ("0803".equals(code)) {
			return GoodsInventory03;
		} else if ("0804".equals(code)) {
			return GoodsInventory04;
		} else if ("0805".equals(code)) {
			return GoodsInventory05;
		} else if ("0806".equals(code)) {
			return GoodsInventory06;
		} else if ("0807".equals(code)) {
			return GoodsInventory07;
		} else if (DeliveryException01.getCode().equals(code)) {
			return DeliveryException01;
		}
		// else if (ShelfLifeWarning01.getCode().equals(code)) {
		// return ShelfLifeWarning01;
		// } else if (ShelfLifeWarning02.getCode().equals(code)) {
		// return ShelfLifeWarning02;
		// }
		return null;
	}
}
