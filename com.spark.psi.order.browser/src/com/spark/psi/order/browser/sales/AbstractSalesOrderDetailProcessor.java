/**
 * 
 */
package com.spark.psi.order.browser.sales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.table.edit.SEditContentProvider2;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.order.browser.util.OrderDetailProcessor;
import com.spark.psi.publish.order.entity.PromotionItem;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;
import com.spark.psi.publish.order.task.UpdateSalesOrderTask;

/**
 * 
 *
 */
public abstract class AbstractSalesOrderDetailProcessor extends OrderDetailProcessor<SalesOrderGoodsItem> implements
		SEditContentProvider2 {
	public final static String ID_DISCOUNT_AMOUNT = "discount_amount";

	protected SNumberText disAmountText;

	@Override
	public void process(Situation context) {
		super.process(getContext());
		disAmountText = createControl(ID_DISCOUNT_AMOUNT, SNumberText.class);

		//
		double d = getDiscountAmount();
		if (0 == d) {
			disAmountText.setText("");
		} else {
			disAmountText.setValue(getDiscountAmount());
		}
		//
		if (View.Look == viewEnum) {
			disAmountText.setEditable(false);
		} else {
			disAmountText.addClientEventHandler(JWT.CLIENT_EVENT_DOCUMENT, "SaaSOrder.handleTotalDisAmountChanged");
		}

		//
		if (View.Look != viewEnum) {

			this.registerInputValidator(new TextInputValidator(disAmountText, "�����ۿ۲���С��0��") {

				@Override
				protected boolean validateText(String str) {
					if (null == str || "".equals(str)) {
						return true;
					}
					double amount = Double.parseDouble(str);
					if (amount < 0) {
						return false;
					}
					return true;
				}
			});
			this.registerInputValidator(new TextInputValidator(disAmountText, "�����ۿ۲��ܴ����ܽ��(��ȷ����ǰ������С��0)��") {

				@Override
				protected boolean validateText(String str) {
					if (null == str || "".equals(str)) {
						return true;
					}
					double total = Double.parseDouble(Text_totalAmount.getText());
					if (0 > total) {
						return false;
					}
					return true;
				}
			});
		}
		initPageButton();

	}

	protected abstract void initPageButton();

	@Override
	public String getValue(Object element, String columnName) {
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
			if (Cloumns.Count.name().equals(columnName)) {
				return item.getCount() + "";
			}
			if (Cloumns.Price.name().equals(columnName)) {
				return DoubleUtil.getRoundStr(item.getPrice());
			}
			if (Cloumns.DisCount.name().equals(columnName)) {
				if (item.getPromotionId() != null) {
					return null;
				}
				return item.getDiscountCount() * 100 + "";
			}
			if (Cloumns.DisAmount.name().equals(columnName)) {
				if (item.getPromotionId() != null) {
					return null;
				}
				return item.getDiscountAmount() + "";
			}
		}
		return null;
	}

	protected final void save() {
		this.createButton(" ���� ").addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addSaveAction(true);
			}
		});
	}

	@Override
	protected final void lookInventory(String rowId, Point location) {
		inventoryInfoWindow.refresh(getGoodsItem(rowId).getGoodsItemId(), null, location, true);
	}

	public final static String PriceExtra = "priceExtra";
	private final Map<String, PromotionItem> promotionMap = new HashMap<String, PromotionItem>();

	public final SNameValue[] getOptions(Object element, String colummName) {
		if (Cloumns.Price.name().equals(colummName)) {
			SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
			SNameValue[] values = new SNameValue[item.getPromotionList().length + 1];
			values[0] = new SNameValue(DoubleUtil.getRoundStrWithOutQfw(item.getGoodsItemPrice(), 2), DoubleUtil
					.getRoundStr(item.getGoodsItemPrice()));
			int index = 1;
			for (PromotionItem promotion : item.getPromotionList()) {
				promotionMap.put(promotion.getId().toString(), promotion);
				values[index++] = new SNameValue(promotion.getId().toString(), DoubleUtil.getRoundStr(promotion
						.getPromotionPrice()));
			}
			return values;
		}
		return null;
	}

	public SNameValue[] getExtraData(Object element) {
		SalesOrderGoodsItem item = (SalesOrderGoodsItem) element;
		return new SNameValue[] { new SNameValue(PriceExtra, "" + item.getPrice()),
				new SNameValue(Cloumns.Amount.name(), "" + item.getAmount()) };
	}

	protected abstract double getDiscountAmount();

	protected final boolean fillTaskBaseData(UpdateSalesOrderTask task) {
		List<com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem> list = new ArrayList<com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem>();
		com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem item;
		double totalAmount = 0;
		for (String rowId : table.getAllRowId()) {
			SalesOrderGoodsItem goods = getGoodsItem(rowId);
			item = new com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem();
			item.setCount(Double.valueOf(table.getEditValue(rowId, Cloumns.Count.name())[0]));
			try {
				item.setDiscountAmount(Double.valueOf(table.getEditValue(rowId, Cloumns.DisAmount.name())[0]));
			} catch (NumberFormatException ex) {
			} catch (NullPointerException nullEx) {
			}
			try {
				item.setDiscountCount(Double.valueOf(table.getEditValue(rowId, Cloumns.DisCount.name())[0]) / 100);
			} catch (NumberFormatException ex) {
			} catch (NullPointerException nullEx) {
			}
			item.setGoodsItemId(goods.getGoodsItemId());
			item.setId(goods.getId());
			// ========================�۸�==============
			item.setBuyAvgPrice(goods.getBuyAvgPrice());// EditValue(rowId,
			item.setPrice(Double.valueOf(table.getEditValue(rowId, Cloumns.Price.name())[0]));
			if (item.getPrice() == 0) {
				alert("���ۼ۸���Ϊ�㣬���飡");
				return false;
			}
			// =========================================
			item.setAmount(item.getPrice() * item.getCount() - item.getDiscountAmount());
			list.add(item);
			totalAmount += item.getAmount();
		}
		if (CheckIsNull.isNotEmpty(disAmountText.getText())) {
			try {
				task.setDiscountAmount(DoubleUtil.strToDouble(disAmountText.getText()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		task.setAmount(totalAmount - task.getDiscountAmount());
		task.setSalesOrderGoodsItems(list
				.toArray(new com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem[list.size()]));
		return true;
	}

	// ============================У��==========================
	@Override
	protected final String validateCell(String rowId, String columnName) {
		// if (Cloumns.Price.name().equals(columnName)) {
		// String str = getCellString(rowId, columnName);
		//			 
		// } else {
		return super.validateCell(rowId, columnName);
		// }
	}

	@Override
	protected final String getTableNullTitle() {
		return "��������۲��ϣ�";
	}

	@Override
	protected final String validateTitle(com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns columnsEnum,
			com.spark.psi.order.browser.util.OrderDetailProcessor.ValiDateErrorEnum error) {
		switch (columnsEnum) {
		case Count:
			switch (error) {
			case NULL:
				return "������������Ϊ��";
			case Less_than_zero:
				return "���������������0";
			case Zero:
				return "���������������0";
			case Greater_than_Max:
				return "����������������";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.9��97";
			default:
				return null;
			}
		case Price:
			switch (error) {
			case NULL:
				return "���۵��۲���Ϊ��";
			case Greater_than_Max:
				return "�������۵��۹���";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		case DisCount:
			switch (error) {
			case Less_than_zero:
				return "�ۿ��ʲ���С��0";
			case Greater_than_Max:
				return "�ۿ��ʲ��ܴ���100";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		case DisAmount:
			switch (error) {
			// case NULL:
			// return "�ۿ۶��Ϊ��";
			case Less_than_zero:
				return "�ۿ۶��С��0";
			case Greater_than_Max:
				return "�ۿ۶����";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		case Amount:
			switch (error) {
			case NULL:
				return "���۽���Ϊ��";
				// case Less_than_zero:
				// return "���۵��۲���С��0";
			case Greater_than_Max:
				return "�������۽�����";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
