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
import com.spark.psi.publish.base.partner.task.UpdateCustomerCreditTask;
import com.spark.psi.publish.base.partner.task.UpdateCustomerSalesManTask;

/**
 * ��ʽ�ͻ��б�ҳ�洦����
 */
public class FormalCustomerListProcessor extends
		PSIListPageProcessor<CustomerItem> {

	public final static String ID_COMBO_CUSTOMERSCOPE = "Combo_CutomerScope";
//	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public final static String ID_BUTTON_DETAILSEARCH = "Button_DetailSearch";
	public final static String ID_BUTTON_ALLOCATE = "Button_Allocate";
	public final static String ID_BUTTON_SHARE = "Button_Share";
	public final static String ID_BUTTON_CHANGECREDIT = "Button_ChangeCredit";

	public static enum Columns {
		Name, TotalTradeAmount, TotalTradeCount, RecentTradeDate, BalanceAmount, CreditAmount, CreditDay, ContactPerson, SalesName;
	}

	private Label cutomerCountLabel;

	private GetCustomerListKey key = new GetCustomerListKey(
			PartnerStatus.Official);

	@Override
	public void process(Situation situation) {
		
		super.process(situation);
		LoginInfo login = situation.find(LoginInfo.class);
		final LWComboList cmb_scope = this.createControl(
				ID_COMBO_CUSTOMERSCOPE, LWComboList.class);
//		final ComboList cmb_term = this.createControl(ID_COMBO_TIME,
//				ComboList.class);
		final QueryScopeSource source = PSIProcessorUtils.initQueryScopeSource(
				cmb_scope, "�ҵĿͻ�", "ȫ���ͻ�", Auth.Sales);
//		getContext().getList(QueryTerm.class);
//		cmb_term.addItem("��ǰ");
//		cmb_term.setData("��ǰ", "");
//		cmb_term.addItem("��������");
//		cmb_term.setData("��������", QueryTerm.ID_MONTH);
//		cmb_term.addItem("��������");
//		cmb_term.setData("��������", QueryTerm.ID_QUARTER);
//		cmb_term.addItem("��������");
//		cmb_term.setData("��������", QueryTerm.ID_WEEK);
//		cmb_term.setSelection(0);
//		cmb_term.addSelectionListener(new SelectionListener() {
//
//			public void widgetSelected(SelectionEvent e) {
//				key.setQueryTerm(getContext().find(QueryTerm.class,
//						cmb_term.getData(cmb_term.getText())));
//				table.render();
//			}
//		});

		cmb_scope.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				key.setQueryScope((QueryScope) source.getElementById(cmb_scope
						.getText()));
				table.render();
			}
		});

		cutomerCountLabel = this.createControl(ID_LABEL_COUNT, Label.class,
				JWT.NONE);

		Button but_allocate = this.createControl(ID_BUTTON_ALLOCATE, Button.class, JWT.NONE);
		but_allocate.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						allocate(table.getSelections());
					}
				});
		if(!login.hasAuth(Auth.SubFunction_CustomerMange_BusPerson)){
			but_allocate.setVisible(false);
		}

		Button but_share = this.createControl(ID_BUTTON_SHARE, Button.class, JWT.NONE);
		but_share.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						share(table.getSelections());
					}
		});
		if(!login.hasAuth(Auth.SubFunction_CustomerMange_Share)){
			but_share.setVisible(false);
		}

		Button but_credit = this.createControl(ID_BUTTON_CHANGECREDIT, Button.class, JWT.NONE);
		but_credit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						adjustCredit(table.getSelections());
					}
				});
		if(!login.hasAuth(Auth.SubFunction_CustomerMange_Credit)){
			but_credit.setVisible(false);
		}
		
		final Text searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class,JWT.NONE);
		searchText.addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e){
						key.setSearchText(searchText.getText());
						table.render();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if(!StringUtils.isEmpty(tablestatus.getSortColumn())){
			key.setSortField(SortField.valueOf(tablestatus.getSortColumn()));
			if(tablestatus.getSortDirection()==SSortDirection.ASC){
				key.setSortType(SortType.Asc);
			}else{
				key.setSortType(SortType.Desc);
			}
		}
		ListEntity<CustomerItem> listEntity = context.find(ListEntity.class,
				key);
		if (listEntity != null) {
			cutomerCountLabel.setText(String.valueOf(listEntity.getItemList()
					.size()));
			return listEntity.getItemList().toArray();
		}
		cutomerCountLabel.setText("0");
		return null;
	}

	public String getElementId(Object element) {
		return ((CustomerItem) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		List<String> list = new ArrayList<String>();
		LoginInfo login = getContext().find(LoginInfo.class);
		if(login.hasAuth(Auth.SubFunction_CustomerMange_BusPerson)){
			list.add(Action.Allocate.name());
		}
		if(login.hasAuth(Auth.SubFunction_CustomerMange_Credit)){
			list.add(Action.CreditConfig.name());
		}
		return list.toArray(new String[0]);
	}

	/**
	 * ָ����������ʱ���������¼�
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Allocate.name())) {
			allocate(rowId);
		} else if (actionName.equals(Action.CreditConfig.name())) {
			adjustCredit(rowId);
		} else if (actionName.equals("edit")) {
			edit(rowId);
		}
	}

	@Override
	public SNameValue[] getExtraData(Object element){
		if(element instanceof CustomerItem){
			CustomerItem item = (CustomerItem)element;
			return new SNameValue[] {new SNameValue("remindDay",String.valueOf(item.getRemindDay())),
					new SNameValue("creditAmount",String.valueOf(item.getCreditAmount())),
					new SNameValue("creditDay", String.valueOf(item.getAccountPeriod())),
					new SNameValue("salesman",String.valueOf(item.getSalesmanId()))};
		}
	    return super.getExtraData(element);
	}
	
	private void edit(String id) {
		//
		GUID customerId = GUID.valueOf(id);
		LoginInfo login = getContext().find(LoginInfo.class);
		List<BaseFunction> functions = new ArrayList<BaseFunction>();
		functions.add(new BaseFunction(new PageControllerInstance(new PageController(
					EditCustomerProcessor.class, EditCustomerRender.class),
					customerId), "�ͻ���Ϣ"));
		if(login.hasAuth(Auth.Tag_CustomerMange_Trading))
			functions.add(new BaseFunction(new PageControllerInstance("�ͻ�δ��ɽ����б�ҳ��*",
							customerId), "δ�꽻��"));
		if(login.hasAuth(Auth.Tag_CustomerMange_Traded))
			functions.add(new BaseFunction(new PageControllerInstance("�ͻ�����ɽ����б�ҳ��*",
							customerId), "���׼�¼"));
		if(login.hasAuth(Auth.Tag_CustomerMange_Balance))
			functions.add(new BaseFunction(new PageControllerInstance(
							"CustomerDealingsListPage", customerId), "�����˿�"));
		if(login.hasAuth(Auth.Tag_CustomerMange_Invoice))
			functions.add(new BaseFunction(new PageControllerInstance("InvoiceListPage",
							customerId), "��Ʊ��¼") );
		MsgRequest request = new MsgRequest(functions.toArray(new BaseFunction[0]), "�ͻ�����");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
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
		FormalCustomerListProcessor.this.hint("���óɹ�");
		table.render();
	}

	private void allocate(final String... ids) {
		if (!checkSelection(ids)) {
			return;
		}
		String salesman = null;
		if(ids.length==1){
			salesman = table.getExtraData(ids[0], "salesman")[0];
		}
		PageController pc = new PageController(SalesmanSelectProcessor.class,
				SalesmanSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc,salesman);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(640, 420);
		MsgRequest request = new MsgRequest(pci, "ѡ��������Ա", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				GUID salesId = (GUID) returnValue;
				for (String id : ids) {
					getContext().handle(new UpdateCustomerSalesManTask(GUID.valueOf(id),salesId));	
				}
				table.render();
				FormalCustomerListProcessor.this.hint("���óɹ�");
			}
		});
		getContext().bubbleMessage(request);
	}

	private void adjustCredit(final String... ids) {
		if (!checkSelection(ids)) {
			return;
		}
		PageController pc = new PageController(
				CustomerCreditAdjustProcessor.class,
				CustomerCreditAdjustRender.class);
		PageControllerInstance pci ;
		if(ids.length==1){
			pci = new PageControllerInstance(pc,ids,table.getExtraData(ids[0],"creditAmount","creditDay","remindDay"));
		}else{
			pci =  new PageControllerInstance(pc,ids);
		}
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(360, 180);
		MsgRequest request = new MsgRequest(pci, "��������", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				Double[] value = (Double[]) returnValue;
				GUID[] customers = new GUID[ids.length];
				for (int i=0;i<ids.length;i++) {
					customers[i] = GUID.valueOf(ids[i]);
				}
				UpdateCustomerCreditTask task = new UpdateCustomerCreditTask(value[0],(int) value[1].doubleValue(),(int) value[2].doubleValue(),customers);
				getContext().handle(task);
				FormalCustomerListProcessor.this.hint("���óɹ�");
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

//	private boolean isOver
	
	private boolean checkSelection(String[] ids) {
		if (ids == null || ids.length == 0) {
			alert("��ѡ��ͻ�");
			return false;
		}
		return true;
	}

	@Override
	protected String getExportFileTitle() {
		return "��ʽ�ͻ�";
	}
}
