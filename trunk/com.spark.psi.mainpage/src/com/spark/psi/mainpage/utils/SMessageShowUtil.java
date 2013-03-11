package com.spark.psi.mainpage.utils;

import org.json.JSONObject;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.RowData;
import com.jiuqi.dna.ui.wt.layouts.RowLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.mainpage.ImageUtil;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.smessage.entity.SMessageItem;

public abstract class SMessageShowUtil {

	private static final int Length = 50;
	private static Composite sMessageShowComp;

	public static void show(Composite comp, final SMessageItem item) {
		if (null == item) {
			return;
		}

		SMessageTemplateEnum template = item.getTemplate();
		if (template == null) {
			return;
		}
		sMessageShowComp = comp;
		Composite panel = createPanel(comp);
		switch (template) {
		case UnreadNotices01:// ("0101", "δ������", "",null),
			final Label unreadNotices01 = createClickLabel(panel, "noticeInfoPage", "����", item.getRelaObjId()
					.toString());
			if (getMaxLength(item.getStringValue1()) > Length) {
				String str = getMaxLength(item.getStringValue1(), Length);
				unreadNotices01.setText(str);
				addRow(comp, item.getStringValue1().substring(str.length()), item.getStringValue2(),
						"DistributeSalesOrderPage", "����", item.getRelaObjId().toString());
				return;
			}
			unreadNotices01.setText(item.getStringValue1());
			if (getMaxLength(item.getStringValue1() + item.getStringValue2()) > Length) {
				String str = getMaxLength(item.getStringValue2(), Length - getMaxLength(item.getStringValue1()));
				createClicklable(panel, str);
				addRow(comp, item.getStringValue2().substring(str.length()), null, "DistributeSalesOrderPage", "����",
						item.getRelaObjId().toString());
			} else {
				createClicklable(panel, item.getStringValue2());
			}
			return;
		case UnapproveOrders01:// ("0201", "����������", "�ɹ�",null),
			createClicklable(panel, "�ɹ�����");
			final Label unapproveOrders01 = createClickLabel(panel, "PurchaseOrderDetailPage", "�ɹ�����", item
					.getRelaObjId().toString());
			unapproveOrders01.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UnapproveOrders02:// ("0202", "����������", "����",null),
			createClicklable(panel, "���۶���");
			final Label unapproveOrders02 = createClickLabel(panel, "SalesOrderDetailPage", "���۶���", item.getRelaObjId()
					.toString());
			unapproveOrders02.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UnapproveOrders03:// ("0203", "����������", "����",null),
			createClicklable(panel, "�ɹ��˻�");
			final Label unapproveOrders03 = createClickLabel(panel, "PurchaseReturnSheetDetailPage", "�ɹ��˻���", item
					.getRelaObjId().toString());
			unapproveOrders03.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UnapproveOrders04:// ("0204", "����������", "�����˻�",null),
			createClicklable(panel, "�����˻�");
			final Label unapproveOrders04 = createClickLabel(panel, "SalesReturnSheetDetailPage", "�����˻���", item
					.getRelaObjId().toString());
			unapproveOrders04.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
			// case UnapproveOrders05:// ("0205", "����������", "����",null),
			// createClicklable(panel, "��������");
			// final Label unapproveOrders05 = createClickLabel(panel,
			// "ApprovalingPromotionGoodsListPage", "��������", item.getRelaObjId()
			// .toString());
			// unapproveOrders05.setText(item.getStringValue1());
			// unapproveOrders05.addMouseClickListener(new MouseClickListener()
			// {
			// public void click(MouseEvent e) {
			// // openPage(context, "", "�ɹ�����", entity);
			// }
			// });
			// createClicklable(panel, "������");
			// return;
		case UnapproveOrders06:
			// ("0206", "����������", "����",null),
			createClicklable(panel, "��������");
			final Label unapproveOrders06 = createClickLabel(panel, "ApprovalingSheetDetailPage", "��������", item
					.getRelaObjId().toString());
			unapproveOrders06.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UndistributeOrders01:
			// ("0301", "���������", "",null),
			createClicklable(panel, "���۶���");
			final Label undistributeOrders01 = createClickLabel(panel, "DistributeSalesOrderPage", "���������", item
					.getRelaObjId().toString());
			undistributeOrders01.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UncheckoutOrders01:
			// ("0401", "��ȷ�ϳ���", "����",null),
			createClicklable(panel, "���۳���");
			final Label uncheckoutOrders01 = createClickLabel(panel, "CheckingOutDetailPage", "���ⵥ��ϸ", item
					.getRelaObjId().toString());
			uncheckoutOrders01.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UncheckoutOrders02:
			// ("0402", "��ȷ�ϳ���", "����",null),
			createClicklable(panel, "�ɹ��˻�");
			final Label uncheckoutOrders02 = createClickLabel(panel, "CheckingOutDetailPage", "���ⵥ��ϸ", item
					.getRelaObjId().toString());
			uncheckoutOrders02.setText(item.getStringValue1());

			createClicklable(panel, "������");
			return;
		case UncheckinOrders01:
			// ("0501", "��ȷ�����", "�ɹ�",null),
			createClicklable(panel, "�ɹ����");
			final Label uncheckinOrders01 = createClickLabel(panel, "CheckingInDetailPage", "��ⵥ��ϸ", item
					.getRelaObjId().toString());
			uncheckinOrders01.setText(item.getStringValue1());

			createClicklable(panel, "�����");
			return;
		case UncheckinOrders02:
			// ("0502", "��ȷ�����", "�����˻�",null),
			createClicklable(panel, "�����˻�");
			final Label uncheckinOrders02 = createClickLabel(panel, "CheckingInDetailPage", "��ⵥ��ϸ", item
					.getRelaObjId().toString());
			uncheckinOrders02.setText(item.getStringValue1());

			createClicklable(panel, "�����");
			return;
		case NearBirthday01:
			// ("0601", "��������", "",null),
			createClicklable(panel, item.getStringValue1() + " " + item.getStringValue2() + " "
					+ item.getStringValue3());
			return;
		case NearOrderDate01:
			// ("0701", "ҵ�񵥾�Ԥ��", "�ɹ��ٽ�",null),
			createClicklable(panel, "�ɹ�����");
			final Label NearOrderDate01 = createClickLabel(panel, "PurchaseOrderDetailPage", "�ɹ�����", item
					.getRelaObjId().toString());
			NearOrderDate01.setText(item.getStringValue1());

			createClicklable(panel, "�ٽ���������");
			return;
		case NearOrderDate02:
			// ("0702", "ҵ�񵥾�Ԥ��", "�ɹ��ѹ�",null),
			createClicklable(panel, "�ɹ�����");
			final Label NearOrderDate02 = createClickLabel(panel, "PurchaseOrderDetailPage", "�ɹ�����", item
					.getRelaObjId().toString());
			NearOrderDate02.setText(item.getStringValue1());
			createClicklable(panel, "�ѹ���������");
			return;
		case NearOrderDate03:
			// ("0703", "ҵ�񵥾�Ԥ��", "�����ٽ�",null),
			createClicklable(panel, "���۶���");
			final Label NearOrderDate03 = createClickLabel(panel, "SalesOrderDetailPage", "���۶���", item.getRelaObjId()
					.toString());
			NearOrderDate03.setText(item.getStringValue1());

			createClicklable(panel, "�ٽ���������");
			return;
		case NearOrderDate04:
			// ("0704", "ҵ�񵥾�Ԥ��", "�����ѹ�",null),
			createClicklable(panel, "���۶���");
			final Label NearOrderDate04 = createClickLabel(panel, "SalesOrderDetailPage", "���۶���", item.getRelaObjId()
					.toString());
			NearOrderDate04.setText(item.getStringValue1());
			createClicklable(panel, "�ѹ���������");
			return;
		case GoodsInventory01:
			// ("0801", "��Ʒ���Ԥ��", "�������������",null),
			String str0 = "\"" + item.getStringValue1() + "\"֮�µ���Ʒ����ܶ�������ޣ�" + item.getStringValue2() + "Ԫ";
			if (getMaxLength(str0) < Length) {
				createClicklable(panel, str0);
			} else {
				String str = getMaxLength(str0, Length);
				createClicklable(panel, str);
				addRow(comp, str0.substring(str.length()), null);
			}
			return;
		case GoodsInventory02:
			// ("0802", "��Ʒ���Ԥ��", "�����ڵ�������",null),
			String str1 = item.getStringValue1() + "��������[" + item.getStringValue2() + "]�Ŀ��������"
					+ item.getStringValue3() + "Ԫ";
			if (getMaxLength(str1) < Length) {
				createClicklable(panel, str1);
			} else {
				String str = getMaxLength(str1, Length);
				createClicklable(panel, str);
				addRow(comp, str1.substring(str.length()), null);
			}
			return;
		case GoodsInventory03:
			// ("0803", "��Ʒ���Ԥ��", "�������ڵ�������",null),
			String str2 = item.getStringValue1() + "�����������[" + item.getStringValue2() + "]�Ŀ������"
					+ item.getStringValue3();
			if (getMaxLength(str2) < Length) {
				createClicklable(panel, str2);
			} else {
				String str = getMaxLength(str2, Length);
				createClicklable(panel, str);
				addRow(comp, str2.substring(str.length()), null);
			}
			return;
		case GoodsInventory04:
			// ("0804", "��Ʒ���Ԥ��", "�������ڵ�������",null),
			String str3 = item.getStringValue1() + "�����������[" + item.getStringValue2() + "]�Ŀ������"
					+ item.getStringValue3();
			if (getMaxLength(str3) < Length) {
				createClicklable(panel, str3);
			} else {
				String str = getMaxLength(str3, Length);
				createClicklable(panel, str);
				addRow(comp, str3.substring(str.length()), null);
			}
			return;
		case GoodsInventory05:
			// ("0805", "��Ʒ���Ԥ��", "������������",null),
			String str4 = item.getStringValue1() + "�����������вֿ�Ŀ�����޽��" + item.getStringValue3() + "Ԫ";
			if (getMaxLength(str4) < Length) {
				createClicklable(panel, str4);
			} else {
				String str = getMaxLength(str4, Length);
				createClicklable(panel, str);
				addRow(comp, str4.substring(str.length()), null);
			}
			return;
		case GoodsInventory06:
			// ("0806", "��Ʒ���Ԥ��", "��������������",null),
			String str5 = item.getStringValue1() + "��������������вֿ�Ŀ������" + item.getStringValue3();
			if (getMaxLength(str5) < Length) {
				createClicklable(panel, str5);
			} else {
				String str = getMaxLength(str5, Length);
				createClicklable(panel, str);
				addRow(comp, str5.substring(str.length()), null);
			}
			return;
		case GoodsInventory07:
			// ("0807", "��Ʒ���Ԥ��", "��������������",null),
			String str6 = item.getStringValue1() + "��������������вֿ�Ŀ������" + item.getStringValue3();
			if (getMaxLength(str6) < Length) {
				createClicklable(panel, str6);
			} else {
				String str = getMaxLength(str6, Length);
				createClicklable(panel, str);
				addRow(comp, str6.substring(str.length()), null);
			}

			return;
		}
	}

	private static void addRow(Composite comp, String text, String suffix, String... item) {
		if (null == text || text.length() == 0) {
			return;
		}
		Composite panel = new Composite(comp);
		RowLayout fl = new RowLayout();
		fl.marginLeft = 38;
		GridData gd = new GridData();
		gd.heightHint = 18;
		panel.setLayoutData(gd);
		panel.setLayout(fl);
		if (null == item || item.length == 0) {
			if (getMaxLength(text) < Length) {
				createClicklable(panel, text);
			} else {
				String str = getMaxLength(text, Length);
				createClicklable(panel, str);
				addRow(comp, text.substring(str.length()), suffix, item);
			}
		} else {
			final Label unapproveOrders02 = createClickLabel(panel, item);
			if (getMaxLength(text) < Length) {
				unapproveOrders02.setText(text);
			} else {
				String str = getMaxLength(text, Length);
				unapproveOrders02.setText(str);
				addRow(comp, text.substring(str.length()), suffix, item);
				return;
			}
		}
		if (null == suffix || suffix.length() == 0) {
			return;
		}
		if (getMaxLength(text + suffix) < Length) {
			createClicklable(panel, suffix);
		} else {
			String str = getMaxLength(suffix, Length - getMaxLength(text + Length));
			createClicklable(panel, str);
			addRow(comp, suffix.substring(str.length()), null, item);
		}
	}

	private static int getMaxLength(String takerText) {
		int len = 0;
		char[] chars = takerText.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			len += 1;
			if (isGBK(bytes) || Character.isUpperCase(chars[i])) {
				len += 1;
			}
		}
		return len;
	}

	private static boolean isGBK(byte[] bytes) {
		if (bytes.length == 2) {
			int[] ints = new int[2];
			ints[0] = bytes[0] & 0xff;
			ints[1] = bytes[1] & 0xff;
			if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
				return true;
			}
		}
		return false;
	}

	private static String getMaxLength(String takerText, int maxLength) {
		int len = 0;
		char[] chars = takerText.toCharArray();
		StringBuilder ss = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			len += 1;
			if (isGBK(bytes) || Character.isUpperCase(chars[i])) {
				len += 1;
			}
			if (len > maxLength) {
				break;
			} else {
				ss.append(chars[i]);
			}
		}
		return ss.toString();
	}

	private static Composite createPanel(Composite comp) {
		ImageDescriptor image = ImageUtil.crateImage("inf_category_arrow.png");
		Composite panel = new Composite(comp);
		RowLayout fl = new RowLayout();
		fl.marginLeft = 20;
		GridData gd = new GridData();
		gd.heightHint = 18;
		panel.setLayoutData(gd);
		panel.setLayout(fl);
		Composite ccc = new Composite(panel);
		ccc.setBackimage(image);
		RowData rd = new RowData();
		rd.width = 15;
		ccc.setLayoutData(rd);
		return panel;
	}

	private static Label createClicklable(Composite panel, String text) {
		Label label = new Label(panel);
		label.setForeground(Color.COLOR_WHITE);
		label.setText(text);
		return label;
	}

	private static Label createClickLabel(Composite panel, String... args) {
		final Label labelKey = new Label(panel);
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", args[0]);
			jsonObject.put("name", args[1]);
			jsonObject.put("id", args[2]);
			labelKey.setCustomObject("openWindow", jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		labelKey.setData(args);
		labelKey.addClientEventHandler(JWT.CLIENT_EVENT_CLICK, "SaasMainPage.handleMessageClick");
		labelKey.addMouseClickListener(new MouseClickListener() {

			public void click(MouseEvent e) {
				sMessageShowComp.getParent().getParent().getParent().notifyClientAction();
			}
		});
		Font font = new Font();
		font.setStyle(1 << 3);// �»���
		labelKey.setFont(font);
		labelKey.setForeground(Color.COLOR_GREEN);
		labelKey.setCursor(Cursor.HAND);
		return labelKey;
	}

	public static StringArray getShowStr(SMessageItem item) {
		if (null == item) {
			return null;
		}
		SMessageTemplateEnum template = item.getTemplate();
		if (template == null) {
			return null;
		}
		switch (template) {
		case UnreadNotices01:// ("0101", "δ������", "",null),
			return new StringArray(item.getStringValue1() + " " + item.getStringValue2(), "noticeInfoPage", "����", item
					.getRelaObjId().toString());
		case UnapproveOrders01:// ("0201", "����������", "�ɹ�",null),
			return new StringArray("�ɹ����� " + item.getStringValue1() + " ������", "PurchaseOrderDetailPage", "�ɹ�����", item
					.getRelaObjId().toString());
		case UnapproveOrders02:// ("0202", "����������", "����",null),
			return new StringArray("���۶��� " + item.getStringValue1() + " ������", "SalesOrderDetailPage", "���۶���", item
					.getRelaObjId().toString());
		case UnapproveOrders03:// ("0203", "����������", "����",null),
			return new StringArray("�ɹ��˻� " + item.getStringValue1() + " ������", "PurchaseReturnSheetDetailPage", "�ɹ��˻���",
					item.getRelaObjId().toString());
		case UnapproveOrders04:// ("0204", "����������", "�����˻�",null),
			return new StringArray("�����˻� " + item.getStringValue1() + " ������", "SalesReturnSheetDetailPage", "�����˻���",
					item.getRelaObjId().toString());
			// case UnapproveOrders05:// ("0205", "����������", "����",null),
			// return new StringArray("�������� " + item.getStringValue1() + "������",
			// "ApprovalingPromotionGoodsListPage", "��������", item
			// .getRelaObjId().toString());
		case UnapproveOrders06:
			// ("0206", "����������", "����",null),
			return new StringArray("�������� " + item.getStringValue1() + " ������", "ApprovalingSheetDetailPage", "��������",
					item.getRelaObjId().toString());
		case UndistributeOrders01:
			// ("0301", "���������", "",null),
			return new StringArray("���۶��� " + item.getStringValue1() + " ������", "DistributeSalesOrderPage", "���������", item
					.getRelaObjId().toString());
		case UncheckoutOrders01:
			return new StringArray("���۳��� " + item.getStringValue1() + " ������", "CheckingOutDetailPage", "���ⵥ��ϸ", item
					.getRelaObjId().toString());
		case UncheckoutOrders02:
			// ("0402", "��ȷ�ϳ���", "����",null),
			return new StringArray("�ɹ��˻� " + item.getStringValue1() + " ������", "CheckingOutDetailPage", "���ⵥ��ϸ", item
					.getRelaObjId().toString());
		case UncheckinOrders01:
			// ("0501", "��ȷ�����", "�ɹ�",null),
			return new StringArray("�ɹ���� " + item.getStringValue1() + " �����", "CheckingInDetailPage", "��ⵥ��ϸ", item
					.getRelaObjId().toString());
		case UncheckinOrders02:
			// ("0502", "��ȷ�����", "�����˻�",null),
			return new StringArray("�����˻� " + item.getStringValue1() + " �����", "CheckingInDetailPage", "��ⵥ��ϸ", item
					.getRelaObjId().toString());
		case NearBirthday01:
			// ("0601", "��������", "",null),
			return new StringArray(item.getStringValue1() + " " + item.getStringValue2() + " " + item.getStringValue3());
		case NearOrderDate01:
			// ("0701", "ҵ�񵥾�Ԥ��", "�ɹ��ٽ�",null),
			return new StringArray("�ɹ����� " + item.getStringValue1() + " �ٽ���������", "PurchaseOrderDetailPage", "�ɹ�����",
					item.getRelaObjId().toString());
		case NearOrderDate02:
			// ("0702", "ҵ�񵥾�Ԥ��", "�ɹ��ѹ�",null),
			return new StringArray("�ɹ����� " + item.getStringValue1() + " �ѹ���������", "PurchaseOrderDetailPage", "�ɹ�����",
					item.getRelaObjId().toString());
		case NearOrderDate03:
			// ("0703", "ҵ�񵥾�Ԥ��", "�����ٽ�",null),
			return new StringArray("���۶��� " + item.getStringValue1() + " �ٽ���������", "SalesOrderDetailPage", "���۶���", item
					.getRelaObjId().toString());
		case NearOrderDate04:
			// ("0704", "ҵ�񵥾�Ԥ��", "�����ѹ�",null),
			return new StringArray("�����˻� " + item.getStringValue1() + " �ѹ���������", "SalesOrderDetailPage", "���۶���", item
					.getRelaObjId().toString());
		case GoodsInventory01:
			// ("0801", "��Ʒ���Ԥ��", "�������������",null),
			return new StringArray("\"" + item.getStringValue1() + "\"֮�µ���Ʒ����ܶ�������ޣ�" + item.getStringValue2() + "Ԫ");
		case GoodsInventory02:
			// ("0802", "��Ʒ���Ԥ��", "�����ڵ�������",null),
			return new StringArray(item.getStringValue1() + "��������[" + item.getStringValue2() + "]�Ŀ������"
					+ item.getStringValue3() + "Ԫ");
		case GoodsInventory03:
			// ("0803", "��Ʒ���Ԥ��", "�������ڵ�������",null),
			return new StringArray(item.getStringValue1() + "�����������[" + item.getStringValue2() + "]�Ŀ������"
					+ item.getStringValue3());
		case GoodsInventory04:
			// ("0804", "��Ʒ���Ԥ��", "�������ڵ�������",null),
			return new StringArray(item.getStringValue1() + "�����������[" + item.getStringValue2() + "]�Ŀ������"
					+ item.getStringValue3());
		case GoodsInventory05:
			// ("0805", "��Ʒ���Ԥ��", "������������",null),
			return new StringArray(item.getStringValue1() + "��������������вֿ�Ŀ�����޽��" + item.getStringValue3() + "Ԫ");
		case GoodsInventory06:
			// ("0806", "��Ʒ���Ԥ��", "��������������",null),
			return new StringArray(item.getStringValue1() + "��������������вֿ�Ŀ������" + item.getStringValue3());
		case GoodsInventory07:
			// ("0807", "��Ʒ���Ԥ��", "��������������",null),
			return new StringArray(item.getStringValue1() + "��������������вֿ�Ŀ������" + item.getStringValue3());
		case DeliveryException01:
			return new StringArray("�����쳣 " + item.getStringValue1() + " ������");
			
		case ShelfLifeWarning01:
			return new StringArray(item.getStringValue1());
		case ShelfLifeWarning02:
			return new StringArray(item.getStringValue1());
		}
		return null;
	}
}
