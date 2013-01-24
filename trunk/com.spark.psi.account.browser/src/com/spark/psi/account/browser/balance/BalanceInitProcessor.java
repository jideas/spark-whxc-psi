/**
 * 
 */
package com.spark.psi.account.browser.balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.psi.account.browser.PartnerTypeSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.account.entity.BalanceItem;
import com.spark.psi.publish.account.key.GetInitBalanceItemListKey;
import com.spark.psi.publish.account.task.InitBalanceTask;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.partner.entity.CustomerShortItem;
import com.spark.psi.publish.base.partner.entity.PartnerShortItem;
import com.spark.psi.publish.base.partner.entity.SupplierShortItem;
import com.spark.psi.publish.base.partner.key.GetShortCustomerListKey;
import com.spark.psi.publish.base.partner.key.GetShortSupplierListKey;
import com.spark.psi.publish.base.partner.key.GetShortCustomerListKey.SearchType;

/**
 * 
 *
 */
public class BalanceInitProcessor extends PageProcessor  implements IDataProcessPrompt{

	public static final String ID_List_PartnerType = "List_PartnerTyle";
	public static final String ID_Area_UnInited = "Area_UnInited";
	public static final String ID_Area_Inited = "Area_Inited";
	public static final String ID_Label_UnInitedCount = "Label_UnInitedCount";
	public static final String ID_Label_InitedCount = "Label_InitedCount";
	public static final String ID_Text_SearchText = "Text_SearchText";
	public static final String ID_Button_SearchUnInited = "ID_SearchUnInited";
	public static final String ID_Button_SearchInited = "ID_SearchInited";
	public static final String ID_Button_TempSave = "Button_TempSave";
	public static final String ID_Button_Finish = "Button_Finish";

	private Composite unInitedArea;
	private Composite initedArea;
	private Label unInitedCountLabel;
	private Label initedCountLabel;
	private Text searchText;

	private SEditTable unInitedTable;
	private SEditTable initedTable;

	private List<BalanceItem> initedBalanceList;
	private PartnerType partnerType;
	private static final STableStyle tableStyle = new STableStyle();
	//页面没有保存的值，切换客户/供应商时要写值，保存时要清空
	private Map<String, Double> unSavedCustomerData = new HashMap<String, Double>();
	private Map<String, Double> unSavedSupplierData = new HashMap<String, Double>();
	// 页面上撤消的初始数据(还未保存的，点撤消时写该值，保存时要清空)
	private List<String> pageDeleteCustomerIdList = new ArrayList<String>();
	private List<String> pageDeleteSupplierIdList = new ArrayList<String>();
	
	private BaseFunction[] normalFunctions;

	@Override
	public void init(final Situation context) {
		super.init(context);
		this.normalFunctions = (BaseFunction[]) this.getArgument();
	}

	@Override
	public void process(final Situation context) {
		//
		unInitedCountLabel = createControl(ID_Label_UnInitedCount, Label.class);
		initedCountLabel = createControl(ID_Label_InitedCount, Label.class);
		searchText = createControl(ID_Text_SearchText, Text.class);

		final LWComboList listType = createControl(ID_List_PartnerType,
				LWComboList.class);
		unInitedArea = createControl(ID_Area_UnInited, Composite.class);
		initedArea = createControl(ID_Area_Inited, Composite.class);

		listType.getList().setSource(new PartnerTypeSource());
		listType.getList().setInput(null);
		listType.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ui_tempSaveData();
				partnerType = PartnerType.getPartnerTypeByValue(listType
						.getList().getSeleted());
				searchText.setText(null);
				loadContentShow(context);
				resetDataChangedstatus();
			}
		});

		final Button searchUnInited = createControl(ID_Button_SearchUnInited,
				Button.class);
		searchUnInited.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				unInitedTable.render();
			}
		});
		final Button searchInited = createControl(ID_Button_SearchInited,
				Button.class);
		searchInited.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				initedTable.render();
			}
		});

		Button tempSaveBtn = createControl(ID_Button_TempSave, Button.class);
		tempSaveBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TenantInfo tenantInfo = context.find(TenantInfo.class);
				if (tenantInfo.isDealingsInited()) {
					alert("初始化已完成，不能重复初始化。");
					return;
				}

				doSave(context);
				unSavedCustomerData.clear();
				unSavedSupplierData.clear();
				loadContentShow(context);
				hint("保存成功。");
			}
		});

		Button finishBtn = createControl(ID_Button_Finish, Button.class);
		finishBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TenantInfo tenantInfo = context.find(TenantInfo.class);
				if (tenantInfo.isDealingsInited()) {
					alert("初始化已完成，不能重复初始化。");
					return;
				}
				if (!isAllInited(context)) {
					confirm("您还有未填写初始数据的客户和供应商，初始化完成后将不能对客户或供应商往来初始值余额进行修改。",
							new Runnable() {

								public void run() {
									doFinish(context);
								}

							});
				} else {
					confirm("初始化完成后将不能对客户或供应商往来初始值余额进行修改", new Runnable() {

						public void run() {
							doFinish(context);
						}
						
					});
					
				}

			}
		});

		listType.setSelection(PartnerType.Customer.getCode());
	}

	private void doSave(final Situation context) {
		// 保存当前选择的类型数据
		List<InitBalanceTask.Item> itemList = new ArrayList<InitBalanceTask.Item>();
		String[] unInitedRowIds = unInitedTable.getAllRowId();
		String[] initedRowIds = initedTable.getAllRowId();
		if (unInitedRowIds != null) {
			for (String rowId : unInitedRowIds) {
				String[] amount = unInitedTable.getEditValue(rowId, "amount");
				if (amount == null || "".equals(amount[0].trim())) {
					continue;
				}
				InitBalanceTask.Item item = new InitBalanceTask.Item(GUID
						.tryValueOf(rowId), partnerType, Double
						.parseDouble(amount[0]));
				itemList.add(item);
			}
		}
		if (initedRowIds != null) {
			for (String rowId : initedRowIds) {
				String[] amount = initedTable.getExtraData(rowId, "amount");
				if (amount == null || "".equals(amount[0].trim())) {
					continue;
				}
				InitBalanceTask.Item item = new InitBalanceTask.Item(GUID
						.tryValueOf(rowId), partnerType, Double
						.parseDouble(amount[0]));
				itemList.add(item);
			}
		}
		InitBalanceTask task = new InitBalanceTask(itemList
				.toArray(new InitBalanceTask.Item[itemList.size()]),
				partnerType);
		context.handle(task, InitBalanceTask.Method.Save);
		
		// 保存另一个类型的数据
		List<InitBalanceTask.Item> otherItemList = new ArrayList<InitBalanceTask.Item>();
		Map<String, Double> unSavedData = PartnerType.Customer.equals(partnerType) ? unSavedSupplierData : unSavedCustomerData;
		List<String> deleteIdList = PartnerType.Customer.equals(partnerType) ? pageDeleteSupplierIdList : pageDeleteCustomerIdList;
		PartnerType theOtherType = PartnerType.Customer.equals(partnerType) ? PartnerType.Supplier : PartnerType.Customer;
		List<BalanceItem> otherIinitedList = context.getList(BalanceItem.class,
				new GetInitBalanceItemListKey(theOtherType));
		for (BalanceItem bItem : otherIinitedList) {
			if (deleteIdList.contains(bItem.getPartnerId().toString())) { //过滤已撤消的
				continue;
			}
			InitBalanceTask.Item item = new InitBalanceTask.Item(bItem.getPartnerId(), theOtherType, bItem.getAmount());
			otherItemList.add(item);
		}
		//
		Iterator<String> unSavedIterator = unSavedData.keySet().iterator();
		while (unSavedIterator.hasNext()) {
			String unSavedId = unSavedIterator.next();
			double amount = unSavedData.get(unSavedId) == null ? 0.0 : unSavedData.get(unSavedId).doubleValue() ;
			InitBalanceTask.Item item = new InitBalanceTask.Item(GUID.valueOf(unSavedId), theOtherType, amount);
			otherItemList.add(item);
		}
		InitBalanceTask otherTask = new InitBalanceTask(otherItemList
				.toArray(new InitBalanceTask.Item[otherItemList.size()]),
				theOtherType);
		context.handle(otherTask, InitBalanceTask.Method.Save);
		
		pageDeleteCustomerIdList.clear();
		pageDeleteSupplierIdList.clear();
		
		super.resetDataChangedstatus();
	}

	private void doFinish(final Situation context) {
		doSave(context);
		InitBalanceTask task = new InitBalanceTask(partnerType);
		context.handle(task, InitBalanceTask.Method.Finish);
		hint("初始化成功。");
		unSavedCustomerData.clear();
		unSavedSupplierData.clear();
		loadContentShow(context);

		//
		MsgRequest request = new MsgRequest(normalFunctions, "往来管理");
		request.setReplace(true);
		context.bubbleMessage(request);
	}

	/**
	 * 判断所有的客户/供应商是否都已初始化
	 * 
	 * @return
	 */
	private boolean isAllInited(Context context) {

		// 判断listType当前选择的类型
		String[] rowIds = unInitedTable.getAllRowId();
		if (rowIds != null) {
			for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
				String[] amountStrs = unInitedTable.getEditValue(
						rowIds[rowIndex], "amount");
				try {
					if (amountStrs == null || amountStrs.length < 1
							|| StringHelper.isEmpty(amountStrs[0])
							|| Double.parseDouble(amountStrs[0]) <= 0) {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		// 判断listType非当前选择的类型
		if (PartnerType.Customer.equals(partnerType)) {
			GetShortSupplierListKey sKey = new GetShortSupplierListKey();
			@SuppressWarnings("unchecked")
			ListEntity<SupplierShortItem> supplierItems = context.find(
					ListEntity.class, sKey);
			if (null != supplierItems) {
				List<BalanceItem> initedSupplierBalanceList = context.getList(
						BalanceItem.class, new GetInitBalanceItemListKey(
								PartnerType.Supplier));
				if (initedSupplierBalanceList.size() != supplierItems
						.getItemList().size()) {
					return false;
				}
			}
		} else {
			GetShortCustomerListKey cKey = new GetShortCustomerListKey();
			if(getContext().find(LoginInfo.class).hasAuth(Auth.Account)){
				cKey.setSearchType(SearchType.All);
			}
			@SuppressWarnings("unchecked")
			ListEntity<CustomerShortItem> customItems = context.find(
					ListEntity.class, cKey);
			if (null != customItems) {
				List<BalanceItem> initedCustomerBalanceList = context.getList(
						BalanceItem.class, new GetInitBalanceItemListKey(
								PartnerType.Customer));
				if (initedCustomerBalanceList.size() != customItems
						.getItemList().size()) {
					return false;
				}
			}
		}
		return true;
	}

	private void loadContentShow(final Situation context) {
		initedBalanceList = context.getList(BalanceItem.class,
				new GetInitBalanceItemListKey(partnerType));

		unInitedArea.clear();
		initedArea.clear();
		unInitedTable = new SEditTable(unInitedArea, getColumns(), tableStyle,
				null);
		unInitedTable.setContentProvider(new UnInitedContentProvider());
		unInitedTable.setLabelProvider(new UnInitedLabelProvider());

		SActionInfo[] actionInfos = new SActionInfo[] { new SActionInfo(
				Action.Cancel.name(), Action.Cancel.getTitle(), null, null) };
		initedTable = new SEditTable(initedArea, getColumns(), tableStyle,
				actionInfos);
		initedTable.setContentProvider(new InitedContentProvider());
		initedTable.setLabelProvider(new InitedLabelProvider());
		initedTable.addActionListener(new SActionListener() {

			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				if (actionName.equals(Action.Cancel.name())) {
					initedTable.removeRow(rowId);
					initedTable.renderUpate();
					if (PartnerType.Customer.equals(partnerType)) {
						pageDeleteCustomerIdList.add(rowId);
					} else {
						pageDeleteSupplierIdList.add(rowId);
					}
					initedCountLabel.setText("已初始化"
							+ partnerType.getName()
							+ " "
							+ (initedTable.getAllRowId() == null ? "0" : String
									.valueOf(initedTable.getAllRowId().length))
							+ " 家");
					unInitedTable.render();
				}
			}
		});

		unInitedTable.render();
		initedTable.render();
		unInitedArea.layout();
		initedArea.layout();
	}

	private void ui_tempSaveData() {
		if (unInitedTable == null)
			return;
		String[] rowIds = unInitedTable.getAllRowId();
		if (rowIds == null)
			return;
		if (PartnerType.Customer.equals(partnerType)) {
			// unSavedCustomerData
			for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
				String[] amountStrs = unInitedTable.getEditValue(
						rowIds[rowIndex], "amount");
				try {
					if (amountStrs != null && amountStrs.length > 0
							&& Double.parseDouble(amountStrs[0]) > 0) {
						unSavedCustomerData.put(rowIds[rowIndex], Double
								.parseDouble(amountStrs[0]));
					}
				} catch (Exception e) {

				}
			}
		} else if (PartnerType.Supplier.equals(partnerType)) {
			for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
				String[] amountStrs = unInitedTable.getEditValue(
						rowIds[rowIndex], "amount");
				try {
					if (amountStrs != null && amountStrs.length > 0
							&& Double.parseDouble(amountStrs[0]) > 0) {
						unSavedSupplierData.put(rowIds[rowIndex], Double
								.parseDouble(amountStrs[0]));
					}
				} catch (Exception e) {

				}
			}
		}
	}

	// private String

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[2];
		if (PartnerType.Customer.equals(partnerType)) {
			columns[0] = new STableColumn("partnerName", 200, JWT.CENTER,
					"客户名称");
			columns[1] = new SNumberEditColumn("amount", 200, JWT.RIGHT, "应收余额");
			columns[0].setGrab(true);
			columns[1].setGrab(true);
		} else {
			columns[0] = new STableColumn("partnerName", 200, JWT.CENTER,
					"供应商名称");
			columns[1] = new SNumberEditColumn("amount", 200, JWT.RIGHT, "应付余额");
			columns[0].setGrab(true);
			columns[1].setGrab(true);
		}
		return columns;
	}

	class UnInitedContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getNewElement() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getValue(Object element, String columnName) {
			PartnerShortItem partner = (PartnerShortItem) element;
			if (columnName.equals("partnerName")) {
				return partner.getName();
			} else if (columnName.equals("amount")) {
				if (PartnerType.Customer.equals(partnerType)) {
					return unSavedCustomerData.get(partner.getId().toString()) == null ? ""
							: String.valueOf(unSavedCustomerData.get(partner
									.getId().toString()));
				} else if (PartnerType.Supplier.equals(partnerType)) {
					return unSavedSupplierData.get(partner.getId().toString()) == null ? ""
							: String.valueOf(unSavedSupplierData.get(partner
									.getId().toString()));
				}
				return "";
			}
			return "";
		}

		public String getElementId(Object element) {
			PartnerShortItem partner = (PartnerShortItem) element;
			return partner.getId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			PartnerShortItem[] partnerList = getPartnerList(context);
			unInitedCountLabel.setText("未初始化" + partnerType.getName() + " "
					+ partnerList.length + " 家");
			return partnerList;
		}

		public boolean isSelectable(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isSelected(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		private PartnerShortItem[] getPartnerList(Context context) {
			if (PartnerType.Customer.equals(partnerType)) {
				// List<CustomerShortItem> customerList =
				// context.getList(CustomerShortItem.class, new
				// GetShortCustomerListKey());
				GetShortCustomerListKey key = new GetShortCustomerListKey();
				if(getContext().find(LoginInfo.class).hasAuth(Auth.Account)){
					key.setSearchType(SearchType.All);
				}
				key.setSearchText(searchText.getText() == null ? ""
						: searchText.getText().trim());
				@SuppressWarnings("unchecked")
				ListEntity<CustomerShortItem> customItems = context.find(
						ListEntity.class, key);
				if (null == customItems)
					return null;
				List<CustomerShortItem> customList = customItems.getItemList();
				// GetInitBalanceItemListKey
				List<CustomerShortItem> initedCustomerList = new ArrayList<CustomerShortItem>();
				for (CustomerShortItem custom : customList) {
					for (BalanceItem balanceItem : initedBalanceList) {

						if (pageDeleteCustomerIdList.contains(balanceItem.getPartnerId().toString())) { //过滤掉页面删除的
							continue;
						}
						if (balanceItem.getPartnerId().equals(custom.getId())) {
							// customList.remove(custom);
							initedCustomerList.add(custom);
							break;
						}
					}
				}
				customList.removeAll(initedCustomerList);
				return customList.toArray(new PartnerShortItem[customList
						.size()]);
			} else {
				// List<SupplierShortItem> suplierList =
				// context.getList(SupplierShortItem.class, new
				// GetShortSupplierListKey());
				GetShortSupplierListKey key = new GetShortSupplierListKey();
				key.setSearchText(searchText.getText() == null ? ""
						: searchText.getText().trim());
				@SuppressWarnings("unchecked")
				ListEntity<SupplierShortItem> supplierItems = context.find(
						ListEntity.class, key);
				if (null == supplierItems)
					return null;
				List<SupplierShortItem> supplierList = supplierItems
						.getItemList();
				List<SupplierShortItem> initedSupplierList = new ArrayList<SupplierShortItem>();
				for (SupplierShortItem supplier : supplierList) {
					for (BalanceItem balanceItem : initedBalanceList) {
						if (pageDeleteSupplierIdList.contains(balanceItem.getPartnerId().toString())) { //过滤掉页面删除的
							continue;
						}
						if (balanceItem.getPartnerId().equals(supplier.getId())) {
							// supplierList.remove(supplier);
							initedSupplierList.add(supplier);
							break;
						}
					}
				}
				supplierList.removeAll(initedSupplierList);
				return supplierList.toArray(new PartnerShortItem[supplierList
						.size()]);
			}
		}
	}

	class UnInitedLabelProvider implements SLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			PartnerShortItem partner = (PartnerShortItem) element;
			switch (columnIndex) {
			case 0:
				return partner.getShortName();
			case 1:
				if (PartnerType.Customer.equals(partnerType)) {
					return unSavedCustomerData.get(partner.getId().toString()) == null ? ""
							: DoubleUtil.getRoundStr(unSavedCustomerData
									.get(partner.getId().toString()));
				} else if (PartnerType.Supplier.equals(partnerType)) {
					return unSavedSupplierData.get(partner.getId().toString()) == null ? ""
							: DoubleUtil.getRoundStr(unSavedSupplierData
									.get(partner.getId().toString()));
				}
				return "";
			}

			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			PartnerShortItem partner = (PartnerShortItem) element;
			if (columnIndex == 0) {
				return partner.getName();
			}
			return null;
		}
	}

	class InitedContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return new String[] { Action.Cancel.name() };
		}

		public SNameValue[] getExtraData(Object element) {
			BalanceItem item = (BalanceItem) element;
			SNameValue nameValue = new SNameValue("amount", String.valueOf(item
					.getAmount()));
			return new SNameValue[] { nameValue };
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			// BalanceItem item = (BalanceItem)element;
			// if(columnName.equals("partnerName")) {
			// return item.getPartnerName();
			// } else if(columnName.equals("amount")){
			// return DoubleUtil.getRoundStr(item.getAmount());
			// }
			return null;
		}

		public String getElementId(Object element) {
			BalanceItem item = (BalanceItem) element;
			return item.getPartnerId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			List<BalanceItem> resultList = new ArrayList<BalanceItem>();
			if (searchText.getText() != null
					&& !"".equals(searchText.getText().trim())) {
				for (BalanceItem item : initedBalanceList) {
					if (item.getPartnerName().indexOf(
							searchText.getText().trim()) > -1) {
						resultList.add(item);
					}
				}
			} else {
				for (BalanceItem item : initedBalanceList) {
					if (PartnerType.Customer.equals(partnerType) && pageDeleteCustomerIdList.contains(item.getPartnerId().toString())) { //过滤掉页面删除的
						continue;
					}
					if (PartnerType.Supplier.equals(partnerType) && pageDeleteSupplierIdList.contains(item.getPartnerId().toString())) { //过滤掉页面删除的
						continue;
					}
					resultList.add(item);
				}
			}
			initedCountLabel.setText("已初始化" + partnerType.getName() + " "
					+ String.valueOf(resultList.size()) + " 家");
			return resultList.toArray();
		}

		public boolean isSelectable(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isSelected(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	class InitedLabelProvider implements SLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getText(Object element, int columnIndex) {
			BalanceItem item = (BalanceItem) element;
			switch (columnIndex) {
			case 0:
				return item.getPartnerShortName();
			case 1:
				return DoubleUtil.getRoundStr(item.getAmount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			BalanceItem item = (BalanceItem) element;
			if (columnIndex == 0) {
				return item.getPartnerName();
			}
			return null;
		}

	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		try {
			doSave(getContext());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
