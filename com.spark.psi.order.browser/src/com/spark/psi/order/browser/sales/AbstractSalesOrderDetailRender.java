/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SEditColumn;
import com.spark.common.components.table.edit.SFormula;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.order.browser.util.OrderDetailRender;
import com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;

/**
 * 
 *
 */
public abstract class AbstractSalesOrderDetailRender extends OrderDetailRender {

	@Override
	protected final Cloumns[] getColumnsEnumList() {
		return new Cloumns[] { Cloumns.GoodsItemCode, Cloumns.GoodsNo, Cloumns.GoodsName, Cloumns.GoodsProperties,
				Cloumns.GoodsUnit, Cloumns.Count, Cloumns.Buy_Avg_Price, Cloumns.Price, Cloumns.DisCount,
				Cloumns.DisAmount, Cloumns.Amount };
	}

	/**
	 * 
	 */
	public final int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 5:
			return 2;
		case 6:
			return 2;
		case 7:
			return 2;
		case 8:
			return 2;
		case 9:
			return 2;
		case 10:
			return 2;
		}
		return -1;
	}

	@Override
	protected final String getText(Object element, Cloumns columnEnum) {
		SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
		switch (columnEnum) {
		case DisCount:
			return DoubleUtil.getRoundStr(item.getDiscountCount() * 100);
		case DisAmount:
			return DoubleUtil.getRoundStr(item.getDiscountAmount());
		case Buy_Avg_Price:
			return DoubleUtil.getRoundStr(item.getBuyAvgPrice());
		case Price:
			return DoubleUtil.getRoundStr(item.getPrice());
		default:
			return super.getText(element, columnEnum);
		}
	}

	@Override
	protected void addColumnsSAsignFormula(Cloumns e, STableColumn c) {
		if (!(c instanceof SEditColumn)) {
			return;
		}
		SEditColumn column = (SEditColumn) c;
		final String $priceExtra = "[" + Cloumns.Price.name() + "]";
		final String $targetPriceExtra = "#" + SalesOrderDetailProcessor.PriceExtra + "";
		final String $amountExtra = "#" + Cloumns.Amount.name() + "";
		final SAsignFormula lastFormula = new SAsignFormula("[" + $amountExtra + "]", Cloumns.Amount.name());
		switch (e) {
		case Count:
			column.setFormulas(new SFormula[] {
					new SAsignFormula($count + "*" + $priceExtra + "*" + $disCount + "/100", Cloumns.DisAmount.name()),// $disAmount),
					new SAsignFormula("(" + $count + "*" + $priceExtra + ")-(" + $count + "*" + $priceExtra + "*"
							+ $disCount + "/100)", $amountExtra), lastFormula });// $amount)});
			break;
		case Price:
			column.setFormulas(new SFormula[] {
					new SAsignFormula($priceExtra, $targetPriceExtra),
					new SAsignFormula($count + "*" + $priceExtra + "*" + $disCount + "/100", Cloumns.DisAmount.name()),// $disAmount),
					new SAsignFormula("(" + $count + "*" + $priceExtra + ") - (" + $count + "*" + $priceExtra + "*"
							+ $disCount + "/100)", $amountExtra), lastFormula });// $amount)});
			break;
		case DisCount:
			column.setFormulas(new SFormula[] {
					new SAsignFormula($count + "*" + $priceExtra + "*" + $disCount + "/100", Cloumns.DisAmount.name()),// $disAmount),
					new SAsignFormula("(" + $count + "*" + $priceExtra + ") - (" + $count + "*" + $priceExtra + "*"
							+ $disCount + "/100)", $amountExtra), lastFormula });// $amount)});
			break;
		case DisAmount:
			column
					.setFormulas(new SFormula[] {
							new SAsignFormula($disAmount + "/(" + $count + "*" + $priceExtra + ") * 100",
									Cloumns.DisCount.name()),// $disCount),
							new SAsignFormula("(" + $count + "*" + $priceExtra + ")-" + $disAmount, $amountExtra),
							lastFormula });// $amount)});
			break;
		default:
			super.addColumnsSAsignFormula(e, column);
			break;
		}
	}

	@Override
	protected final void fillDataInfoControl(Composite dataInfoArea) {
		Label label = new Label(dataInfoArea);
		label.setText("整单折扣：");
		SNumberText text = new SNumberText(dataInfoArea, 2);
		text.setID(SalesOrderDetailProcessor.ID_DISCOUNT_AMOUNT);
		GridData gd = new GridData();
		gd.widthHint = 120;
		text.setLayoutData(gd);
		super.fillDataInfoControl(dataInfoArea);
	}

	@Override
	protected String getOrderTotalAmountTitle() {
		return "订单金额";
	}

}
