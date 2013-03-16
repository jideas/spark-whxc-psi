/**
 * 
 */
package com.spark.psi.publish;

/**
 *
 */
public enum SMessageTemplateEnum {

	UnreadNotices01("0101", "δ������", "", null), UnapproveOrders01("0201", "����������", "�ɹ�", null), UnapproveOrders02(
			"0202", "����������", "����", null), UnapproveOrders03("0203", "����������", "����", null), UnapproveOrders04("0204",
			"����������", "�����˻�", null),
	// UnapproveOrders05("0205", "����������", "����", null),
	UnapproveOrders06("0206", "����������", "����", null),

	UndistributeOrders01("0301", "���������", "", null),

	UncheckoutOrders01("0401", "��ȷ�ϳ���", "����", null), UncheckoutOrders02("0402", "��ȷ�ϳ���", "����", null), UncheckinOrders01(
			"0501", "��ȷ�����", "�ɹ�", null), UncheckinOrders02("0502", "��ȷ�����", "�����˻�", null),

	NearBirthday01("0601", "��������", "", null),

	NearOrderDate01("0701", "ҵ�񵥾�Ԥ��", "�ɹ��ٽ�", null), NearOrderDate02("0702", "ҵ�񵥾�Ԥ��", "�ɹ��ѹ�", null), NearOrderDate03(
			"0703", "ҵ�񵥾�Ԥ��", "�����ٽ�", null), NearOrderDate04("0704", "ҵ�񵥾�Ԥ��", "�����ѹ�", null),

	GoodsInventory01("0801", "��Ʒ���Ԥ��", "�������������", null), GoodsInventory02("0802", "��Ʒ���Ԥ��", "�����ڵ�������", null), GoodsInventory03(
			"0803", "��Ʒ���Ԥ��", "����С�ڵ�������", null), GoodsInventory04("0804", "��Ʒ���Ԥ��", "�������ڵ�������", null), GoodsInventory05(
			"0805", "��Ʒ���Ԥ��", "������������", null), GoodsInventory06("0806", "��Ʒ���Ԥ��", "����С��������", null), GoodsInventory07(
			"0807", "��Ʒ���Ԥ��", "��������������", null),

	DeliveryException01("0901", "�����쳣", "", null), //

	// ShelfLifeWarning01("1001", "�ٽ�������", "", null),
	// ShelfLifeWarning02("1002", "�ѹ�������", "", null);
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
