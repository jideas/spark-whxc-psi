package com.spark.psi.base.browser.goods;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.excel.ExcelWriteHelperPoi;
import com.spark.psi.publish.base.goods.entity.GoodsItemDetail;
import com.spark.psi.publish.base.goods.key.GetGoodsItemDetailListKey;

public final class GoodsListOnSaleProcessor extends GoodsListProcessor {

	public final static String ID_Button_Export777 = "ID_Button_Export777";

	public static enum ColumnName {
		name, code, shelfLife, warningDay
	}

	@Override
	protected String getExportFileTitle() {
		return "商品条目";
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		Button button = this.createButtonControl(ID_Button_Export777);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				realActionPerformed(e);
			}
		});
	}

	public void realActionPerformed(ActionEvent e) {
		List<GoodsItemDetail> list = getContext().getList(GoodsItemDetail.class,
				new GetGoodsItemDetailListKey(0, Integer.MAX_VALUE, true));
		final List<GoodsItemDetail> itemList = new ArrayList<GoodsItemDetail>();
		for (GoodsItemDetail gd : list) {
			if (gd.getGoodsNo().startsWith("777")) {
				itemList.add(gd);
			}
		}
		Display.getCurrent().exportFile("商品条目(称重).xls", "application/vnd.ms-excel", 102400000,
				new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(itemList) {
							@Override
							protected String getText(Object element, int columnIndex) {
								GoodsItemDetail item = (GoodsItemDetail) element;
								switch (columnIndex) {
								case 0:
									return item.getSerialNumber();
								case 1:
									return item.getGoodsNo().substring(3);
								case 2:
									return item.getGoodsName();
								case 3:
									return DoubleUtil.getRoundStr(item.getSalePrice());
								case 4:
									return item.getShelfLife() + "";
								case 5:
									return "1";
								default:
									return "";
								}
							}

							@Override
							protected String[] getHead() {
								return null;
							}

							protected int getColumnCount() {
								return 6;
							}
						};
						writer.writeNormal(outputStream, "商品条目(称重)");
					}
				});
	}

	@Override
	protected void doExport() {
		final List<GoodsItemDetail> itemList = getContext().getList(GoodsItemDetail.class,
				new GetGoodsItemDetailListKey(0, Integer.MAX_VALUE, true));
		Display.getCurrent().exportFile("商品条目.xls", "application/vnd.ms-excel", 102400000, new ExporterWithContext() {

			public void run(Context context, OutputStream outputStream) throws IOException {
				ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(itemList) {
					@Override
					protected String getText(Object element, int columnIndex) {
						GoodsItemDetail item = (GoodsItemDetail) element;
						switch (columnIndex) {
						case 0:
							return item.getSerialNumber();
						case 1:
							return item.getGoodsNo();
						case 2:
							return item.getGoodsName();
						case 3:
							return DoubleUtil.getRoundStr(item.getSalePrice());
						case 4:
							return item.getShelfLife() + "";
						case 5:
							return "1";
						default:
							return "";
						}
					}

					@Override
					protected String[] getHead() {
						// String[] headerTitles = new String[7];
						// headerTitles[0] = "序号";
						// headerTitles[1] = "条码";
						// headerTitles[2] = "商品名称";
						// headerTitles[3] = "单价";
						// headerTitles[4] = "序号";
						// headerTitles[5] = "有效期";
						// headerTitles[6] = "称重标志";
						// return headerTitles;
						return null;
					}

					protected int getColumnCount() {
						return 6;
					}
				};
				writer.writeNormal(outputStream, "商品条目");
			}
		});
	}

}
