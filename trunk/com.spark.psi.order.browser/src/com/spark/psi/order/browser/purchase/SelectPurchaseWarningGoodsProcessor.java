/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.goods.task.UpdateGoodsItemThresholdTask;
import com.spark.psi.publish.base.goods.task.UpdateGoodsItemThresholdTask.GoodsItemThresholdItem;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.key.GetWarningGoodsItemListKey;

/**
 * ��Ԥ��������ѡ��ɹ����ϴ�����
 * 
 */
public class SelectPurchaseWarningGoodsProcessor extends PSIListPageProcessor<SelectPurchaseWarningGoodsProcessor.SelectItem> {
	static enum Columns {
		/**
		 * 
		 */
		GoodsName("��������"),
		/**
		 * 
		 */
		GoodsProperties("���Ϲ��"),
		/**
		 * 
		 */
		GoodsUnit("��λ"),
		/**
		 * 
		 */
		Store("�ֿ�"),
		/**
		 * 
		 */
		DeliveryingCount("��������"),
		/**
		 * 
		 */
		PurchasingCount("�ɹ�������"),
		/**
		 * 
		 */
		InventoryCount("�������"),
		/**
		 * 
		 */
		InventoryCountLimit("���������"),
		/**
		 * 
		 */
		AdviseCount("�ɹ�����"),
		/**
		 * 
		 */
		ThisPurchaseCount("���βɹ�");
		private String title;

		/**
		 * @param name
		 */
		private Columns(String name) {
			this.title = name;
		}

		public String getTitle() {
			return title;
		}
	}

	public static final String ID_CONFIRM = "warning_confirm";
	public static final String ID_CANCEL = "warning_cancel";

	@Override
	public void process(Situation situation) {
		super.process(situation);
		this.createControl(ID_CONFIRM, Button.class).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] rowIds = table.getAllRowId();
				List<SelectItem> resultList = new ArrayList<SelectItem>();
				for (String rowId : rowIds) {
					String[] strs = table.getEditValue(rowId, Columns.ThisPurchaseCount.name());
					double purchaseCount = -1;
					if (null != strs && 0 < strs.length) {
						try {
							purchaseCount = Double.parseDouble(strs[0]);
						} catch (NumberFormatException e1) {
							continue;
						}
					}
					if (0 < purchaseCount) {
						itemMap.get(rowId).thisPurchaseCount = purchaseCount;
						resultList.add(itemMap.get(rowId));
					}
				}
				if (0 == resultList.size()) {
					alert("��������Ҫ�ɹ��Ĳ��ϵġ����βɹ���������");
				} else {
					getContext().bubbleMessage(new MsgResponse(true, resultList.toArray(new SelectItem[resultList.size()])));
				}
			}
		});
		this.createControl(ID_CANCEL, Button.class).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private Map<String, SelectItem> itemMap = new HashMap<String, SelectItem>();

	@Override
	public String getElementId(Object element) {
		SelectItem item = (SelectItem) element;
		itemMap.put(item.rowId, item);
		return item.rowId;
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (Columns.ThisPurchaseCount.name().equals(columnName)) {
			SelectItem item = (SelectItem) element;
			return (item.thisPurchaseCount == null ? 0 : item.thisPurchaseCount) + "";
		}
		return super.getValue(element, columnName);
	}

	@Override
	public String[] getTableActionIds() {
		if (getContext().find(Boolean.class, Auth.Boss) || getContext().find(Boolean.class, Auth.PurchaseManager)) {
			return new String[] { Action.SetThreshold.name() };
		}
		return null;
	}

	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (Action.SetThreshold.name().equals(actionName)) {
			new SetInventoryLimitUtil(getContext(), itemMap.get(rowId).goodsInventoryDetail) {

				@Override
				protected void setLimit(LimitType type, double upper, double lower) {
					GoodsItemThresholdItem item;
					if (LimitType.Count == type) {
						item = new GoodsItemThresholdItem(itemMap.get(rowId).goodsInventoryDetail.getGoodsItemId(),
								itemMap.get(rowId).goodsInventoryDetail.getStoreId(), upper, lower);
					} else {
						item = new GoodsItemThresholdItem(itemMap.get(rowId).goodsInventoryDetail.getGoodsItemId(),
								itemMap.get(rowId).goodsInventoryDetail.getStoreId(), upper);
					}

					UpdateGoodsItemThresholdTask task = new UpdateGoodsItemThresholdTask(item);
					getContext().handle(task);
					// ˢ��grid
					table.render();
				}
			};
		} else {
			super.actionPerformed(rowId, actionName, actionValue);
		}
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		// ����GetWarningGoodsItemListKey��ѯGoodsInventoryDetail�б�
		List<InventoryInfo> list = context.getList(InventoryInfo.class, new GetWarningGoodsItemListKey(false));
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (InventoryInfo item : list) {
			String rowId = item.getStoreId().toString() + item.getGoodsItemId().toString();
			if (itemMap.containsKey(rowId)) {
				itemMap.get(rowId).goodsInventoryDetail = item;
				items.add(itemMap.get(rowId));
				// TODO �������У����βɹ���Ԫ�������ʽ���򲻱��
			} else {
				items.add(new SelectItem(rowId, item));
			}
		}
		itemMap.clear();
		return items.toArray();
	}

	protected final static class SelectItem {
		private String rowId;
		private InventoryInfo goodsInventoryDetail;
		private Double thisPurchaseCount;
		private boolean isUpdateFalg;// �����޸ı�־

		/**
		 * @param rowId
		 * @param goodsInventoryDetail
		 */
		private SelectItem(String rowId, InventoryInfo goodsInventoryDetail) {
			this.rowId = rowId;
			this.goodsInventoryDetail = goodsInventoryDetail;
		}

		public InventoryInfo getGoodsInventoryDetail() {
			return goodsInventoryDetail;
		}

		public Double getThisPurchaseCount() {
			return thisPurchaseCount;
		}

		public boolean isUpdateFalg() {
			return isUpdateFalg;
		}
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
