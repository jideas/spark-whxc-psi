package com.spark.psi.base.browser.customer;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SMessageConfirmWindow;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.QueryScopeSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.partner.entity.CustomerItem;
import com.spark.psi.publish.base.partner.key.GetCustomerListKey;
import com.spark.psi.publish.base.partner.key.GetCustomerListKey.SortField;
import com.spark.psi.publish.base.partner.task.DeleteCustomerTask;
import com.spark.psi.publish.base.partner.task.UpdateCustomerSalesManTask;

/**
 * 潜在客户列表
 */
public class PotentialCustomerListProcessor extends PSIListPageProcessor<CustomerItem> {

	public final static String ID_COMBO_CUSTOMERSCOPE = "Combo_CutomerScope";
	// public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public final static String ID_BUTTON_DETAILSEARCH = "Button_DetailSearch";
	public final static String ID_BUTTON_NEW = "Button_New";
	public final static String ID_BUTTON_ALLOCATE = "Button_Allocate";
	public final static String ID_BUTTON_SHARE = "Button_Share";
	public final static String ID_BUTTON_DELETE = "Button_Delete";

	public static enum Columns {
		Name, Province, City, Address, ContactPerson, SalesName;
	}

	private GetCustomerListKey key = new GetCustomerListKey(PartnerStatus.Potential);

	private Label countLabel;

	@Override
	public void process(Situation situation) {

		super.process(situation);
		LoginInfo login = situation.find(LoginInfo.class);

		final LWComboList cmb_scope = this.createControl(ID_COMBO_CUSTOMERSCOPE, LWComboList.class);
		// final ComboList cmb_term = this.createControl(ID_COMBO_TIME,
		// ComboList.class);
		final QueryScopeSource source = PSIProcessorUtils.initQueryScopeSource(cmb_scope, "我的客户", "全部客户", Auth.Sales);
		// getContext().getList(QueryTerm.class);
		// cmb_term.addItem("当前");
		// cmb_term.setData("当前", "");
		// cmb_term.addItem("本月新增");
		// cmb_term.setData("本月新增", QueryTerm.ID_MONTH);
		// cmb_term.addItem("本季新增");
		// cmb_term.setData("本季新增", QueryTerm.ID_QUARTER);
		// cmb_term.addItem("本周新增");
		// cmb_term.setData("本周新增", QueryTerm.ID_WEEK);
		// cmb_term.setSelection(0);
		// cmb_term.addSelectionListener(new SelectionListener() {
		//
		// public void widgetSelected(SelectionEvent e) {
		// key.setQueryTerm(getContext().find(QueryTerm.class,
		// cmb_term.getData(cmb_term.getText())));
		// table.render();
		// }
		// });

		cmb_scope.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				key.setQueryScope((QueryScope) source.getElementById(cmb_scope.getText()));
				key.setSearchText(null);
				table.render();
			}
		});

		countLabel = this.createControl(ID_LABEL_COUNT, Label.class);

		this.createControl(ID_BUTTON_NEW, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageControllerInstance pci = new PageControllerInstance("NewCustomerPage");
				MsgRequest request = new MsgRequest(pci);
				getContext().bubbleMessage(request);
			}
		});
		Button but_allocate = this.createControl(ID_BUTTON_ALLOCATE, Button.class, JWT.NONE);
		but_allocate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				allocate(table.getSelections());
			}
		});
		if (!login.hasAuth(Auth.SubFunction_CustomerMange_BusPerson)) {
			but_allocate.getNext().dispose();
			but_allocate.dispose();
		}
		Button but_share = this.createControl(ID_BUTTON_SHARE, Button.class, JWT.NONE);
		but_share.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				share(table.getSelections());
			}
		});
		if (!login.hasAuth(Auth.SubFunction_CustomerMange_Share)) {
			but_share.getNext().dispose();
			but_share.dispose();
		}

		this.createControl(ID_BUTTON_DELETE, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(table.getSelections());
			}
		});
		final Text searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		searchText.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				key.setSearchText(searchText.getText());
				table.render();
			}
		});

	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		if (element instanceof CustomerItem) {
			CustomerItem item = (CustomerItem) element;
			return new SNameValue[] { new SNameValue("salesman", String.valueOf(item.getSalesmanId())) };
		}
		return super.getExtraData(element);
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Context context, STableStatus tablestatus) {
		// GetCustomerListKey key = new
		// GetCustomerListKey(PartnerStatus.Potential);
		if (!StringUtils.isEmpty(tablestatus.getSortColumn())) {
			key.setSortField(SortField.valueOf(tablestatus.getSortColumn()));
			if (tablestatus.getSortDirection() == SSortDirection.ASC) {
				key.setSortType(SortType.Asc);
			} else {
				key.setSortType(SortType.Desc);
			}
		}
		ListEntity<CustomerItem> listEntity = context.find(ListEntity.class, key);
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
		List<String> list = new ArrayList<String>();
		LoginInfo login = getContext().find(LoginInfo.class);
		if (login.hasAuth(Auth.SubFunction_CustomerMange_BusPerson)) {
			list.add(Action.Allocate.name());
		}
		list.add(Action.Delete.name());
		return list.toArray(new String[0]);
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		if (element instanceof CustomerItem) {
			CustomerItem item = (CustomerItem) element;
			if (!item.isUsed()) {
				return getTableActionIds();
			} else {
				return new String[] { Action.Allocate.name() };
			}
		} else {
			return super.getElementActionIds(element);
		}
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Allocate.name())) {
			allocate(rowId);
		} else if (actionName.equals(Action.Delete.name())) {
			delete(rowId);
		} else if (actionName.equals("edit")) {
			edit(rowId);
		}
	}

	private void edit(String customerId) {
		//
		BaseFunction[] functions = new BaseFunction[] { new BaseFunction(new PageControllerInstance(new PageController(
				EditCustomerProcessor.class, EditCustomerRender.class), GUID.valueOf(customerId)), "客户信息") };
		MsgRequest request = new MsgRequest(functions, "客户详情");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	private void share(String... ids) {
		if (!checkSelection(ids)) {
			return;
		}
		for (String id : ids) {
			getContext().handle(new UpdateCustomerSalesManTask(GUID.valueOf(id)));
		}
		PotentialCustomerListProcessor.this.hint("设置成功");
		table.render();
	}

	private void allocate(final String... ids) {
		if (!checkSelection(ids)) {
			return;
		}
		String salesman = null;
		if (ids.length == 1) {
			salesman = table.getExtraData(ids[0], "salesman")[0];
		}
		PageController pc = new PageController(SalesmanSelectProcessor.class, SalesmanSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, salesman);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(640, 420);
		MsgRequest request = new MsgRequest(pci, "选择销售人员", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				GUID salesId = (GUID) returnValue;
				for (String id : ids) {
					getContext().handle(new UpdateCustomerSalesManTask(GUID.valueOf(id), salesId));
				}
				PotentialCustomerListProcessor.this.hint("设置成功");
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	private void delete(final String... ids) {
		new SMessageConfirmWindow("确定删除所选客户？", new Runnable() {

			public void run() {
				if (!checkSelection(ids)) {
					return;
				}
				for (String id : ids) {
					try {
						getContext().handle(new DeleteCustomerTask(GUID.valueOf(id)));
					} catch (IllegalArgumentException e) {
						alert(e.getMessage());
						return;
					}
					table.removeRow(id);
				}
				table.renderUpate();
				countLabel.setText(String.valueOf(table.getAllRowId().length));
			}
		});
	}

	private boolean checkSelection(String[] ids) {
		if (ids == null || ids.length == 0) {
			alert("请选择客户");
			return false;
		}
		return true;
	}

	@Override
	protected String getExportFileTitle() {
		return "潜在客户";
	}

}
