package com.spark.psi.base.browser.supplier;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.partner.entity.CustomerItem;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.partner.entity.SupplierItem;
import com.spark.psi.publish.base.partner.key.GetSupplierListKey;
import com.spark.psi.publish.base.partner.task.DeleteSupplierTask;
import com.spark.psi.publish.constant.SupplierCooperation;

/**
 * 供应商列表页面处理器
 */
public class SupplierListProcessor extends PSIListPageProcessor<CustomerItem> {

	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public final static String ID_BUTTON_DETAILSEARCH = "Button_DetailSearch";
	public final static String ID_BUTTON_NEW = "Button_New";

	public static enum Columns {
		Number, Name, SupplierType, TaxRate, TotalTradeAmount, TotalTradeCount, RecentTradeDate, BalanceAmount, ContactPerson;
	}

	private Text searchText;

	private Label countLabel;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		countLabel = this.createControl(ID_LABEL_COUNT, Label.class);
		searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});

		this.createControl(ID_BUTTON_NEW, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = new MsgRequest(new PageControllerInstance(new PageController(
						NewSupplierProcessor.class, NewSupplierRender.class)));
				getContext().bubbleMessage(request);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetSupplierListKey key = new GetSupplierListKey();
		if (!StringUtils.isEmpty(tablestatus.getSortColumn())) {
			key.setSortField(GetSupplierListKey.SortField.valueOf(tablestatus.getSortColumn()));
			if (tablestatus.getSortDirection() == SSortDirection.ASC) {
				key.setSortType(SortType.Asc);
			} else {
				key.setSortType(SortType.Desc);
			}
		}
		key.setSearchText(searchText.getText());
		ListEntity<SupplierItem> listEntity = context.find(ListEntity.class, key);
		if (listEntity != null) {
			countLabel.setText(String.valueOf(listEntity.getItemList().size()));
			return listEntity.getItemList().toArray();
		}
		countLabel.setText("0");
		return null;
	}

	public String getElementId(Object element) {
		return ((CustomerItem) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		if (element instanceof SupplierItem) {
			SupplierItem item = (SupplierItem) element;
			if (!item.isUsed()) {
				return getTableActionIds();
			} else {
				return new String[] {};
			}
		} else {
			return super.getElementActionIds(element);
		}
	}

	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Delete.name())) {
			confirm("确定删除所选供应商？", new Runnable() {
				public void run() {
					try {
						getContext().handle(new DeleteSupplierTask(GUID.valueOf(rowId)));
					} catch (IllegalArgumentException e) {
						alert(e.getMessage());
						return;
					}
					table.render();
				}
			});
		} else if ("edit".equals(actionName)) {
			GUID supplierId = GUID.valueOf(rowId);
			List<BaseFunction> functionList = new ArrayList<BaseFunction>();
			functionList.add(new BaseFunction(new PageControllerInstance(new PageController(
					EditSupplierProcessor.class, EditSupplierRender.class), supplierId), "供应商信息"));
			functionList.add(new BaseFunction(new PageControllerInstance("供应商未完成交易列表页面*", supplierId), "未完交易"));
			functionList.add(new BaseFunction(new PageControllerInstance("供应商已完成交易列表页面*", supplierId), "交易记录"));
			functionList.add(new BaseFunction(new PageControllerInstance("SupplierDealingsListPage", supplierId),
					"往来账款"));
			SupplierInfo supplierInfo = getContext().find(SupplierInfo.class, supplierId);
			if (SupplierCooperation.JointVenture.getCode().equals(supplierInfo.getSupplierCooperation())) {
				functionList.add(new BaseFunction(new PageControllerInstance(new PageController(
						SupplierJoinGoodsListProcessor.class, SupplierJoinGoodsListRender.class), supplierInfo),
						"联营商品记录"));
				functionList.add(new BaseFunction(new PageControllerInstance(new PageController(
						JoinSaleRecordListProcessor.class, JoinSaleRecordListRender.class), supplierInfo), "联营销售记录"));
			}

			MsgRequest request = new MsgRequest(functionList.toArray(new BaseFunction[0]), "供应商详情");
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	@Override
	protected String getExportFileTitle() {
		return "供应商";
	}
}