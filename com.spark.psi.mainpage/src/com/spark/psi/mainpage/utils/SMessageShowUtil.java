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
		case UnreadNotices01:// ("0101", "未读公告", "",null),
			final Label unreadNotices01 = createClickLabel(panel, "noticeInfoPage", "公告", item.getRelaObjId()
					.toString());
			if (getMaxLength(item.getStringValue1()) > Length) {
				String str = getMaxLength(item.getStringValue1(), Length);
				unreadNotices01.setText(str);
				addRow(comp, item.getStringValue1().substring(str.length()), item.getStringValue2(),
						"DistributeSalesOrderPage", "公告", item.getRelaObjId().toString());
				return;
			}
			unreadNotices01.setText(item.getStringValue1());
			if (getMaxLength(item.getStringValue1() + item.getStringValue2()) > Length) {
				String str = getMaxLength(item.getStringValue2(), Length - getMaxLength(item.getStringValue1()));
				createClicklable(panel, str);
				addRow(comp, item.getStringValue2().substring(str.length()), null, "DistributeSalesOrderPage", "公告",
						item.getRelaObjId().toString());
			} else {
				createClicklable(panel, item.getStringValue2());
			}
			return;
		case UnapproveOrders01:// ("0201", "待审批单据", "采购",null),
			createClicklable(panel, "采购订货");
			final Label unapproveOrders01 = createClickLabel(panel, "PurchaseOrderDetailPage", "采购订单", item
					.getRelaObjId().toString());
			unapproveOrders01.setText(item.getStringValue1());

			createClicklable(panel, "待审批");
			return;
		case UnapproveOrders02:// ("0202", "待审批单据", "销售",null),
			createClicklable(panel, "销售订货");
			final Label unapproveOrders02 = createClickLabel(panel, "SalesOrderDetailPage", "销售订单", item.getRelaObjId()
					.toString());
			unapproveOrders02.setText(item.getStringValue1());

			createClicklable(panel, "待审批");
			return;
		case UnapproveOrders03:// ("0203", "待审批单据", "采退",null),
			createClicklable(panel, "采购退货");
			final Label unapproveOrders03 = createClickLabel(panel, "PurchaseReturnSheetDetailPage", "采购退货单", item
					.getRelaObjId().toString());
			unapproveOrders03.setText(item.getStringValue1());

			createClicklable(panel, "待审批");
			return;
		case UnapproveOrders04:// ("0204", "待审批单据", "销售退货",null),
			createClicklable(panel, "销售退货");
			final Label unapproveOrders04 = createClickLabel(panel, "SalesReturnSheetDetailPage", "销售退货单", item
					.getRelaObjId().toString());
			unapproveOrders04.setText(item.getStringValue1());

			createClicklable(panel, "待审批");
			return;
			// case UnapproveOrders05:// ("0205", "待审批单据", "促销",null),
			// createClicklable(panel, "促销审批");
			// final Label unapproveOrders05 = createClickLabel(panel,
			// "ApprovalingPromotionGoodsListPage", "促销审批", item.getRelaObjId()
			// .toString());
			// unapproveOrders05.setText(item.getStringValue1());
			// unapproveOrders05.addMouseClickListener(new MouseClickListener()
			// {
			// public void click(MouseEvent e) {
			// // openPage(context, "", "采购订货", entity);
			// }
			// });
			// createClicklable(panel, "待审批");
			// return;
		case UnapproveOrders06:
			// ("0206", "待审批单据", "调拨",null),
			createClicklable(panel, "调拨审批");
			final Label unapproveOrders06 = createClickLabel(panel, "ApprovalingSheetDetailPage", "库存调拨单", item
					.getRelaObjId().toString());
			unapproveOrders06.setText(item.getStringValue1());

			createClicklable(panel, "待审批");
			return;
		case UndistributeOrders01:
			// ("0301", "待销售配货", "",null),
			createClicklable(panel, "销售订货");
			final Label undistributeOrders01 = createClickLabel(panel, "DistributeSalesOrderPage", "销售配货单", item
					.getRelaObjId().toString());
			undistributeOrders01.setText(item.getStringValue1());

			createClicklable(panel, "待分配");
			return;
		case UncheckoutOrders01:
			// ("0401", "待确认出库", "销售",null),
			createClicklable(panel, "销售出库");
			final Label uncheckoutOrders01 = createClickLabel(panel, "CheckingOutDetailPage", "出库单明细", item
					.getRelaObjId().toString());
			uncheckoutOrders01.setText(item.getStringValue1());

			createClicklable(panel, "待出库");
			return;
		case UncheckoutOrders02:
			// ("0402", "待确认出库", "采退",null),
			createClicklable(panel, "采购退货");
			final Label uncheckoutOrders02 = createClickLabel(panel, "CheckingOutDetailPage", "出库单明细", item
					.getRelaObjId().toString());
			uncheckoutOrders02.setText(item.getStringValue1());

			createClicklable(panel, "待出库");
			return;
		case UncheckinOrders01:
			// ("0501", "待确认入库", "采购",null),
			createClicklable(panel, "采购入库");
			final Label uncheckinOrders01 = createClickLabel(panel, "CheckingInDetailPage", "入库单明细", item
					.getRelaObjId().toString());
			uncheckinOrders01.setText(item.getStringValue1());

			createClicklable(panel, "待入库");
			return;
		case UncheckinOrders02:
			// ("0502", "待确认入库", "销售退货",null),
			createClicklable(panel, "销售退货");
			final Label uncheckinOrders02 = createClickLabel(panel, "CheckingInDetailPage", "入库单明细", item
					.getRelaObjId().toString());
			uncheckinOrders02.setText(item.getStringValue1());

			createClicklable(panel, "待入库");
			return;
		case NearBirthday01:
			// ("0601", "生日提醒", "",null),
			createClicklable(panel, item.getStringValue1() + " " + item.getStringValue2() + " "
					+ item.getStringValue3());
			return;
		case NearOrderDate01:
			// ("0701", "业务单据预警", "采购临近",null),
			createClicklable(panel, "采购订货");
			final Label NearOrderDate01 = createClickLabel(panel, "PurchaseOrderDetailPage", "采购订单", item
					.getRelaObjId().toString());
			NearOrderDate01.setText(item.getStringValue1());

			createClicklable(panel, "临近交货日期");
			return;
		case NearOrderDate02:
			// ("0702", "业务单据预警", "采购已过",null),
			createClicklable(panel, "采购订货");
			final Label NearOrderDate02 = createClickLabel(panel, "PurchaseOrderDetailPage", "采购订单", item
					.getRelaObjId().toString());
			NearOrderDate02.setText(item.getStringValue1());
			createClicklable(panel, "已过交货日期");
			return;
		case NearOrderDate03:
			// ("0703", "业务单据预警", "销售临近",null),
			createClicklable(panel, "销售订货");
			final Label NearOrderDate03 = createClickLabel(panel, "SalesOrderDetailPage", "销售订单", item.getRelaObjId()
					.toString());
			NearOrderDate03.setText(item.getStringValue1());

			createClicklable(panel, "临近交货日期");
			return;
		case NearOrderDate04:
			// ("0704", "业务单据预警", "销售已过",null),
			createClicklable(panel, "销售订货");
			final Label NearOrderDate04 = createClickLabel(panel, "SalesOrderDetailPage", "销售订单", item.getRelaObjId()
					.toString());
			NearOrderDate04.setText(item.getStringValue1());
			createClicklable(panel, "已过交货日期");
			return;
		case GoodsInventory01:
			// ("0801", "商品库存预警", "分类金额大于上线",null),
			String str0 = "\"" + item.getStringValue1() + "\"之下的商品库存总额高于上限：" + item.getStringValue2() + "元";
			if (getMaxLength(str0) < Length) {
				createClicklable(panel, str0);
			} else {
				String str = getMaxLength(str0, Length);
				createClicklable(panel, str);
				addRow(comp, str0.substring(str.length()), null);
			}
			return;
		case GoodsInventory02:
			// ("0802", "商品库存预警", "金额大于单库上线",null),
			String str1 = item.getStringValue1() + "库存金额高于[" + item.getStringValue2() + "]的库存金额上限"
					+ item.getStringValue3() + "元";
			if (getMaxLength(str1) < Length) {
				createClicklable(panel, str1);
			} else {
				String str = getMaxLength(str1, Length);
				createClicklable(panel, str);
				addRow(comp, str1.substring(str.length()), null);
			}
			return;
		case GoodsInventory03:
			// ("0803", "商品库存预警", "数量低于单库下线",null),
			String str2 = item.getStringValue1() + "库存数量低于[" + item.getStringValue2() + "]的库存下限"
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
			// ("0804", "商品库存预警", "数量大于单库上线",null),
			String str3 = item.getStringValue1() + "库存数量高于[" + item.getStringValue2() + "]的库存上限"
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
			// ("0805", "商品库存预警", "金额大于总上线",null),
			String str4 = item.getStringValue1() + "库存金额高于所有仓库的库存上限金额" + item.getStringValue3() + "元";
			if (getMaxLength(str4) < Length) {
				createClicklable(panel, str4);
			} else {
				String str = getMaxLength(str4, Length);
				createClicklable(panel, str);
				addRow(comp, str4.substring(str.length()), null);
			}
			return;
		case GoodsInventory06:
			// ("0806", "商品库存预警", "数量低于总下线",null),
			String str5 = item.getStringValue1() + "库存数量高于所有仓库的库存上限" + item.getStringValue3();
			if (getMaxLength(str5) < Length) {
				createClicklable(panel, str5);
			} else {
				String str = getMaxLength(str5, Length);
				createClicklable(panel, str);
				addRow(comp, str5.substring(str.length()), null);
			}
			return;
		case GoodsInventory07:
			// ("0807", "商品库存预警", "数量大于总上线",null),
			String str6 = item.getStringValue1() + "库存数量高于所有仓库的库存上限" + item.getStringValue3();
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
		font.setStyle(1 << 3);// 下划线
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
		case UnreadNotices01:// ("0101", "未读公告", "",null),
			return new StringArray(item.getStringValue1() + " " + item.getStringValue2(), "noticeInfoPage", "公告", item
					.getRelaObjId().toString());
		case UnapproveOrders01:// ("0201", "待审批单据", "采购",null),
			return new StringArray("采购订货 " + item.getStringValue1() + " 待审批", "PurchaseOrderDetailPage", "采购订单", item
					.getRelaObjId().toString());
		case UnapproveOrders02:// ("0202", "待审批单据", "销售",null),
			return new StringArray("销售订货 " + item.getStringValue1() + " 待审批", "SalesOrderDetailPage", "销售订单", item
					.getRelaObjId().toString());
		case UnapproveOrders03:// ("0203", "待审批单据", "采退",null),
			return new StringArray("采购退货 " + item.getStringValue1() + " 待审批", "PurchaseReturnSheetDetailPage", "采购退货单",
					item.getRelaObjId().toString());
		case UnapproveOrders04:// ("0204", "待审批单据", "销售退货",null),
			return new StringArray("销售退货 " + item.getStringValue1() + " 待审批", "SalesReturnSheetDetailPage", "销售退货单",
					item.getRelaObjId().toString());
			// case UnapproveOrders05:// ("0205", "待审批单据", "促销",null),
			// return new StringArray("促销审批 " + item.getStringValue1() + "待审批",
			// "ApprovalingPromotionGoodsListPage", "促销审批", item
			// .getRelaObjId().toString());
		case UnapproveOrders06:
			// ("0206", "待审批单据", "调拨",null),
			return new StringArray("调拨审批 " + item.getStringValue1() + " 待审批", "ApprovalingSheetDetailPage", "库存调拨单",
					item.getRelaObjId().toString());
		case UndistributeOrders01:
			// ("0301", "待销售配货", "",null),
			return new StringArray("销售订货 " + item.getStringValue1() + " 待分配", "DistributeSalesOrderPage", "销售配货单", item
					.getRelaObjId().toString());
		case UncheckoutOrders01:
			return new StringArray("销售出库 " + item.getStringValue1() + " 待出库", "CheckingOutDetailPage", "出库单明细", item
					.getRelaObjId().toString());
		case UncheckoutOrders02:
			// ("0402", "待确认出库", "采退",null),
			return new StringArray("采购退货 " + item.getStringValue1() + " 待出库", "CheckingOutDetailPage", "出库单明细", item
					.getRelaObjId().toString());
		case UncheckinOrders01:
			// ("0501", "待确认入库", "采购",null),
			return new StringArray("采购入库 " + item.getStringValue1() + " 待入库", "CheckingInDetailPage", "入库单明细", item
					.getRelaObjId().toString());
		case UncheckinOrders02:
			// ("0502", "待确认入库", "销售退货",null),
			return new StringArray("销售退货 " + item.getStringValue1() + " 待入库", "CheckingInDetailPage", "入库单明细", item
					.getRelaObjId().toString());
		case NearBirthday01:
			// ("0601", "生日提醒", "",null),
			return new StringArray(item.getStringValue1() + " " + item.getStringValue2() + " " + item.getStringValue3());
		case NearOrderDate01:
			// ("0701", "业务单据预警", "采购临近",null),
			return new StringArray("采购订货 " + item.getStringValue1() + " 临近交货日期", "PurchaseOrderDetailPage", "采购订单",
					item.getRelaObjId().toString());
		case NearOrderDate02:
			// ("0702", "业务单据预警", "采购已过",null),
			return new StringArray("采购订货 " + item.getStringValue1() + " 已过交货日期", "PurchaseOrderDetailPage", "采购订单",
					item.getRelaObjId().toString());
		case NearOrderDate03:
			// ("0703", "业务单据预警", "销售临近",null),
			return new StringArray("销售订货 " + item.getStringValue1() + " 临近交货日期", "SalesOrderDetailPage", "销售订单", item
					.getRelaObjId().toString());
		case NearOrderDate04:
			// ("0704", "业务单据预警", "销售已过",null),
			return new StringArray("销售退货 " + item.getStringValue1() + " 已过交货日期", "SalesOrderDetailPage", "销售订单", item
					.getRelaObjId().toString());
		case GoodsInventory01:
			// ("0801", "商品库存预警", "分类金额大于上线",null),
			return new StringArray("\"" + item.getStringValue1() + "\"之下的商品库存总额高于上限：" + item.getStringValue2() + "元");
		case GoodsInventory02:
			// ("0802", "商品库存预警", "金额大于单库上线",null),
			return new StringArray(item.getStringValue1() + "库存金额高于[" + item.getStringValue2() + "]的库存上限"
					+ item.getStringValue3() + "元");
		case GoodsInventory03:
			// ("0803", "商品库存预警", "数量低于单库下线",null),
			return new StringArray(item.getStringValue1() + "库存数量低于[" + item.getStringValue2() + "]的库存下限"
					+ item.getStringValue3());
		case GoodsInventory04:
			// ("0804", "商品库存预警", "数量大于单库上线",null),
			return new StringArray(item.getStringValue1() + "库存数量高于[" + item.getStringValue2() + "]的库存上限"
					+ item.getStringValue3());
		case GoodsInventory05:
			// ("0805", "商品库存预警", "金额大于总上线",null),
			return new StringArray(item.getStringValue1() + "库存数量高于所有仓库的库存上限金额" + item.getStringValue3() + "元");
		case GoodsInventory06:
			// ("0806", "商品库存预警", "数量低于总下线",null),
			return new StringArray(item.getStringValue1() + "库存数量高于所有仓库的库存上限" + item.getStringValue3());
		case GoodsInventory07:
			// ("0807", "商品库存预警", "数量大于总上线",null),
			return new StringArray(item.getStringValue1() + "库存数量高于所有仓库的库存上限" + item.getStringValue3());
		case DeliveryException01:
			return new StringArray("配送异常 " + item.getStringValue1() + " 待处理");
			
		case ShelfLifeWarning01:
			return new StringArray(item.getStringValue1());
		case ShelfLifeWarning02:
			return new StringArray(item.getStringValue1());
		}
		return null;
	}
}
