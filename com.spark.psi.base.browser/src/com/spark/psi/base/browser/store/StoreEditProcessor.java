package com.spark.psi.base.browser.store;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListByAuthKey;
import com.spark.psi.publish.base.store.entity.ShelfItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.task.StoreInfoTask;
import com.spark.psi.publish.base.store.task.ValidationStoreNameIsValidTask;
import com.spark.psi.publish.base.store.task.StoreInfoTask.ShelfInfoItem;

public class StoreEditProcessor extends BaseFormPageProcessor implements
		IDataProcessPrompt {

	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_StoreCode = "Text_StoreCode";
	public final static String ID_Text_Consignee = "Text_Consignee";
	public final static String ID_Text_MobileNo = "Text_MobileNo";
	public final static String ID_Text_Keepers = "Text_Keepers";
	public final static String ID_Label_AddKeepers = "Label_AddKeepers";
	public final static String ID_Text_ShelfCount = "Text_ShelfCount";
	public final static String ID_Text_TiersCount = "Text_TiersCount";
	public final static String ID_Button_ConfirmShelf = "Button_ConfirmShelf";
	public final static String ID_Button_ResetShelf = "Button_ResetShelf";
	public final static String ID_Table_Shelf = "Button_Shelf";
	public final static String ID_Button_Save = "Button_Save";
	
	public static enum ShelfTableColumn {
		name, tiersCount
	}
	
	/**
	 * 最大货位个数
	 */
	private static final int MAX_SHELF_COUNT = 100;
	
	/**
	 * 货位的最大层数
	 */
	private static final int MAX_TIERE_COUNT = 10;
	
	private Text nameText;
	private Text addressText;
	private LWComboList provinceList;
	private LWComboList cityList;
	private LWComboList districtList;
	private Text postCodeText;
	private Text storeCodeText;
	private Text consigneeText;
	private Text mobileNumberText;
	private Text keepersText;
	private Label addKeepersButton;
	private Text shelfCountText;
	private Text tiersCountText;
	private Button shelfConfirmButton;
	private Button shelfResetButton;
	private SEditTable shelfTable;
	private Button buttonSave;

	private StoreInfo storeInfo;
	private boolean isShelfEditable = false;
	public void init(Situation context) {
		GUID storeId = (GUID) this.getArgument();
		if (storeId != null) {
			storeInfo = context.find(StoreInfo.class, storeId);
			if (storeInfo == null) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public void process(final Situation context) {
		LoginInfo login = context.find(LoginInfo.class);
		nameText = this.createControl(ID_Text_Name, Text.class);
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		cityList = this.createControl(ID_List_City, LWComboList.class);
		districtList = this.createControl(ID_List_District, LWComboList.class);
		addressText = this.createControl(ID_Text_Address, Text.class);
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		storeCodeText = this.createControl(ID_Text_StoreCode,
				Text.class);
		consigneeText = this.createControl(ID_Text_Consignee, Text.class);
		mobileNumberText = this.createControl(ID_Text_MobileNo, Text.class);
		keepersText = this.createControl(ID_Text_Keepers, Text.class);
		addKeepersButton = this.createControl(ID_Label_AddKeepers, Label.class);
		shelfCountText = this.createControl(ID_Text_ShelfCount, Text.class);
		tiersCountText = this.createControl(ID_Text_TiersCount, Text.class);
		shelfConfirmButton = this.createControl(ID_Button_ConfirmShelf, Button.class);
		shelfResetButton = createButtonControl(ID_Button_ResetShelf);
		shelfTable = this.createControl(ID_Table_Shelf, SEditTable.class);
		buttonSave = this.createControl(ID_Button_Save, Button.class);

		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);

		keepersText.setEditable(false);
		if (null != storeInfo) {
			shelfCountText.setEnabled(false);
			tiersCountText.setEnabled(false);
		}
		
		if (null == storeInfo || StoreStatus.DISABLED.equals(storeInfo.getStatus())) {
			isShelfEditable = true;
		} else {
			isShelfEditable = false;
			shelfCountText.setEnabled(false);
			tiersCountText.setEnabled(false);
			shelfConfirmButton.setVisible(false);
			shelfResetButton.setVisible(false);
//			shelfResetButton.getParent().setVisible(false);
		}
		
		if(null != storeInfo)	{
			shelfTable.setContentProvider(new ShelfContentProvider(storeInfo.getShelfItems(), isShelfEditable));
		} else {
			shelfTable.setContentProvider(new ShelfContentProvider());
		}
		shelfTable.setLabelProvider(new ShelfLabelProvider());
		shelfTable.render();
		
		registValidates();
		//
		if (storeInfo != null) {
			nameText.setText(storeInfo.getName());
			storeCodeText.setText(storeInfo.getStoreNo());
			provinceList.setSelection(storeInfo.getProvince());
			cityList.setSelection(storeInfo.getCity());
			districtList.setSelection(storeInfo.getTown());
			addressText.setText(storeInfo.getAddress());
			postCodeText.setText(storeInfo.getPostcode()); 
			consigneeText.setText(storeInfo.getConsignee());
			mobileNumberText.setText(storeInfo.getMobileNo()); 
			shelfCountText.setText(String.valueOf(storeInfo.getShelfCount()));
			tiersCountText.setText(String.valueOf(storeInfo.getDefaultTiersCount()));
			keepersText.setText(getEmployeeIds(storeInfo.getKeepers()));
			keepersText
					.setDescription(getEmployeeInfos(getContext(),storeInfo.getKeepers()));
		}
		//
		shelfConfirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!shelfCountText.isEnabled()) {
					return;
				}
				if (StringUtils.isEmpty(shelfCountText.getText())) {
					alert("货位个数不能为空！");
					return ;
				}
				if (StringUtils.isEmpty(tiersCountText.getText())) {
					alert("默认层数不能为空！");
					return ;
				}
				int shelfCount = Integer.parseInt(shelfCountText.getText());
				int tiersCount = Integer.parseInt(tiersCountText.getText());
				
				if (shelfCount > MAX_SHELF_COUNT) {
					alert("货位个数不能大于" + MAX_SHELF_COUNT);
					return;
				}
				
				if (tiersCount > MAX_TIERE_COUNT) {
					alert("默认层数不能大于" + MAX_TIERE_COUNT);
					return;
				}
				shelfCountText.setEnabled(false);
				tiersCountText.setEnabled(false);
				shelfTable.setContentProvider(new ShelfContentProvider(shelfCount, tiersCount, isShelfEditable));
				shelfTable.setLabelProvider(new ShelfLabelProvider(tiersCount));
				shelfTable.render();
			}
		});
		shelfResetButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				shelfCountText.setText(null);
				tiersCountText.setText(null);
				shelfCountText.setEnabled(true);
				tiersCountText.setEnabled(true);
				shelfTable.setContentProvider(new ShelfContentProvider());
				shelfTable.setLabelProvider(new ShelfLabelProvider());
				shelfTable.render();
			}
		});
		//
		addKeepersButton.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				String keepers = keepersText.getText();
				if(keepers==null){
					keepers = getContext().find(LoginInfo.class).getTenantId().toString();
				}
				PageController pc = new PageController(
						StoreKeeperSelectProcessor.class,
						StoreEmployeeSelectRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc,
						keepers);
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
				windowStyle.setSize(540, 360);
				MsgRequest request = new MsgRequest(pci, "选择库管人员", windowStyle);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						keepersText.setText((String) returnValue);
						keepersText.setDescription((String) returnValue2);
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		if(!login.hasAuth(Auth.SubFunction_StoreMange_UpdateManger)){
			addKeepersButton.setVisible(false);
		}
		//
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkStoreKeeperIsMy()){
					processData();					
				}else{
					confirm("目前您和您的下属不是当前仓库的库管员，保存之后，您将不能再对该仓库进行后续操作，是否继续保存？", new Runnable(){
	                    public void run(){
	                    	processData();
	                    }
                    });
				}
			}
		});
		if(!login.hasAuth(Auth.SubFunction_StoreMange_Update)){
			buttonSave.setVisible(false);
		}
	}

	private void registValidates() {
		// 注册不为空控件
		registerNotEmptyValidator(nameText, "仓库名称");
		if (isShelfEditable) {
			registerNotEmptyValidator(shelfCountText, "货位个数");
			registerNotEmptyValidator(tiersCountText, "默认层数");
			registerInputValidator(new TextInputValidator(shelfCountText, "货位个数不能大于" + MAX_SHELF_COUNT) {
	
				@Override
				protected boolean validateText(String text) {
					if (StringUtils.isEmpty(text)) return false;
					int shelfCount = Integer.parseInt(text);
					if (shelfCount > MAX_SHELF_COUNT) {
						return false;
					} else {
						return true;
					}
				}
			});
			registerInputValidator(new TextInputValidator(tiersCountText, "默认层数不能大于" + MAX_TIERE_COUNT) {
	
				@Override
				protected boolean validateText(String text) {
					if (StringUtils.isEmpty(text)) return false;
					int tiersCount = Integer.parseInt(text);
					if (tiersCount > MAX_TIERE_COUNT) {
						return false;
					} else {
						return true;
					}
				}
			});
		}
		registerInputValidator(new TextInputValidator(nameText,"仓库名称重复"){
			
			@Override
			protected boolean validateText(String text){
				GUID id = null;
				if(storeInfo!=null)
					id = storeInfo.getId();
				ValidationStoreNameIsValidTask task = new ValidationStoreNameIsValidTask(id, text);
				context.handle(task);
				
				return task.isValid();
			}
		});
		
		registerInputValidator(new TextInputValidator(nameText,"仓库名称中不能保存特殊字符"){
			
			@Override
			protected boolean validateText(String text){
				if(text==null)return true;
				String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
		        Pattern   p   =   Pattern.compile(regEx);     
		        Matcher   m   =   p.matcher(text);
				return !m.find();
			}
		});
		
		registerInputValidator(new TextInputValidator(consigneeText, "收货人姓名不能为空！"){

			@Override
            protected boolean validateText(String text){
				if(StringUtils.isEmpty(mobileNumberText.getText()))return true;
	            return !StringUtils.isEmpty(text);
           }
		});
		
		registerInputValidator(new TextInputValidator(provinceList,
				provinceList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(cityList,
				cityList.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(districtList,
				districtList.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(addressText,
				addressText.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(postCodeText,
				postCodeText.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		
		
		registerInputValidator(new TableDataValidator(shelfTable) {
			
			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "请填写货位信息！";
				}
				return null;
			}
			
			@Override
			protected String validateCell(String rowId, String columnName) {
				return null;
			}
		});
	}
	
	private ShelfInfoItem[] getShelfList() {
		ShelfInfoItem[] shelfItems = new ShelfInfoItem[0];
		if (null == storeInfo || StoreStatus.DISABLED.equals(storeInfo.getStatus())) {
			String[] rowIds = shelfTable.getAllRowId();
			shelfItems = new ShelfInfoItem[rowIds.length];
			for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
				String rowId = rowIds[rowIndex];
				String tiersCountStr = shelfTable.getEditValue(rowId, ShelfTableColumn.tiersCount.name())[0];
				ShelfInfoItem shelfItem = new ShelfInfoItemEntity(GUID.tryValueOf(rowId), rowIndex + 1, Integer.parseInt(tiersCountStr));
				shelfItems[rowIndex] = shelfItem;
			}
		}
		return shelfItems;
	}
	
	/**
	 * 判断要保存的仓库管理员是否包含创建人或者创建人下属（创建人为库管经理时才判断）
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
    protected boolean checkStoreKeeperIsMy(){
	    LoginInfo login = getContext().find(LoginInfo.class);
	    if(login.getTenantId().equals(login.getEmployeeInfo().getId()))return true;
	    GUID[] keepers = getEmployeeIds(keepersText.getText());
	    if(login.hasAuth(Auth.StoreKeeperManager)){
	    	ListEntity<EmployeeItem> emps = getContext().find(ListEntity.class,new GetEmployeeListByAuthKey(login.getEmployeeInfo().getDepartmentId(),Auth.StoreKeeper));
	    	for(GUID guid : keepers){
	            for(EmployeeItem emp : emps.getItemList()){
	                if(emp.getId().equals(guid))return true;
                }
            }
	    }else{
	    	for(GUID guid : keepers){
	            if(guid.equals(login.getEmployeeInfo().getId()))return true;
            }
	    }
	    return false;
    }

	private GUID[] getEmployeeIds(String text) {
		GUID[] ids = new GUID[0];
		if (text != null) {
			String[] arr = StringUtils.split(text, ";");
			ids = new GUID[arr.length];
			for (int i = 0; i < arr.length; i++) {
				ids[i] = GUID.valueOf(arr[i]);
			}
		}
		return ids;
	}

	private String getEmployeeInfos(Context context,EmployeeInfo[] employees) {
		DepartmentTree depts = context.find(DepartmentTree.class);
		if (employees != null) {
			StringBuffer buffer = new StringBuffer();
			for (EmployeeInfo employee : employees) {
				buffer.append(employee.getName());
				buffer.append("(");
				buffer.append(depts.getNodeById(employee.getDepartmentId()).getName()).append(")");
				buffer.append(';');
			}
			if (buffer.length() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
			}
			return buffer.toString();
		}
		return "";
	}

	private String getEmployeeIds(EmployeeInfo[] employees) {
		if (employees != null) {
			StringBuffer buffer = new StringBuffer();
			for (EmployeeInfo employee : employees) {
				buffer.append(employee.getId().toString());
				buffer.append(';');
			}
			if (buffer.length() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
			}
			return buffer.toString();
		}
		return "";
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		// 校验
		if (!validateInput()) {
			return false;
		}
		if (isShelfEditable && !validateTableInput()) {
			return false;
		}
		//
		StoreInfoTask task = new StoreInfoTask();
		task.setName(nameText.getText());
		task.setStoreNo(storeCodeText.getText());
		task.setAddress(addressText.getText());
		task.setProvinceCode(provinceList.getText());
		task.setCityCode(cityList.getText());
		task.setTownCode(districtList.getText());
		task.setConsignee(consigneeText.getText());
		task.setPostcode(postCodeText.getText());
		task.setMobileNo(mobileNumberText.getText());
		String keepers = keepersText.getText();
		if(keepers == null){
			keepers = getContext().find(LoginInfo.class).getTenantId().toString();
		}
		task.setKeeperIds(getEmployeeIds(keepers));
		try{
			// 新增仓库或仓库未启用时才能修改货位
			if (isShelfEditable) {
				task.setShelfCount(Integer.parseInt(shelfCountText.getText()));
	    		task.setDefaultTiersCount(Integer.parseInt(tiersCountText.getText()));
				task.setShelfInfoItems(getShelfList());
			} else {
				task.setShelfCount(storeInfo.getShelfCount());
				task.setDefaultTiersCount(storeInfo.getDefaultTiersCount());
				task.setShelfInfoItems(convertStoreInfoItemToTaskEntity(storeInfo.getShelfItems()));
			}
	        if (storeInfo != null) {
	        	task.setRecver(storeInfo.getRecver());
	        	task.setId(storeInfo.getId());
	        	task.setStatus(storeInfo.getStatus());
	        	getContext().handle(task, StoreInfoTask.Method.Update);
	        } else {
	        	task.setStatus(StoreStatus.DISABLED);
	        	getContext().handle(task, StoreInfoTask.Method.Create);
	        }
        } catch(Exception e){
        	e.printStackTrace();
	        alert(e.getMessage());
	        return false;
        }
		getContext().bubbleMessage(new MsgResponse(true));
		return true;
	}
	
	private ShelfInfoItemEntity[] convertStoreInfoItemToTaskEntity (ShelfItem... shelfItems) {
		ShelfInfoItemEntity[] entities = new ShelfInfoItemEntity[shelfItems.length];
		int index = 0;
		for (ShelfItem item : shelfItems) {
			entities[index] = new ShelfInfoItemEntity(item.getId(), item.getShelfNo(), item.getTiersCount());
			index++;
		}
		return entities;
	}
	
	private boolean validateTableInput() {
		String[] rowIds = shelfTable.getAllRowId();
		for (String rowId : rowIds) {
			String tiersCountStr = shelfTable.getEditValue(rowId, ShelfTableColumn.tiersCount.name())[0];
			if (StringUtils.isEmpty(tiersCountStr)) {
				alert("层数不能为空。");
				return false;
			}
			int tiersCount = 0;
			try {
				tiersCount = Integer.parseInt(tiersCountStr);
			} catch (Exception e) {
				alert("层数只能为小于等于10的整数。");
				return false;
			}
			if (tiersCount < 1) {
				alert("层数不能小于1");
				return false;
			}
			if (tiersCount > MAX_TIERE_COUNT) {
				alert("层数不能大于" + MAX_TIERE_COUNT);
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否验证收货地址 如果省、市、县、详细地址、邮编任意一项不为空，则需要验证收货地址
	 * 
	 * @return boolean
	 */
	private boolean isValidationDeliveryAdderss() {
		return !StringUtils.isEmpty(provinceList.getText())
				|| !StringUtils.isEmpty(cityList.getText())
				|| !StringUtils.isEmpty(districtList.getText())
				|| !StringUtils.isEmpty(addressText.getText())
				|| !StringUtils.isEmpty(postCodeText.getText());
	}
	
	class ShelfContentProvider implements SEditContentProvider {
		private int count = 0;
		private int defaultTiersCount = 0;
		
		private ShelfItem[] shelfItems = null;
		private boolean isEdtable = false;
		
		public ShelfContentProvider(int count, int defaultTiersCount, boolean isEdtable) {
			this.count = count;
			this.defaultTiersCount = defaultTiersCount;
			this.isEdtable = isEdtable;
		}
		
		public ShelfContentProvider(ShelfItem[] shelfItems, boolean isEdtable) {
			this.isEdtable = isEdtable;
			this.shelfItems = shelfItems;
		}
		
		public ShelfContentProvider() {
			
		}
		
		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			return null;
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			if (!isEdtable) return null;
			if (ShelfTableColumn.tiersCount.name().equals(columnName)) {
				if (element instanceof SNameValue) {
					return String.valueOf(defaultTiersCount);
				} else if (element instanceof ShelfItem) {
					return String.valueOf(((ShelfItem)element).getTiersCount());
				}
			}
			return null;
		}

		public String getElementId(Object element) {
			if (element instanceof ShelfItem) {
				return ((ShelfItem)element).getId().toString();
			} else if (element instanceof SNameValue) {
				return ((SNameValue)element).getName();
			}
			return null;
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (shelfItems != null) {
				return shelfItems;
			} else {
				SNameValue[] shelfNoIdArray = new SNameValue[count];
				for (int index = 0; index < count; index++) {
					shelfNoIdArray[index] = new SNameValue(GUID.randomID().toString(), String.valueOf(index + 1));
				}
				return shelfNoIdArray;
			}
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}
		
	}
	
	class ShelfLabelProvider implements SLabelProvider {
		private int defaultTiersCount = 0;
		
		public ShelfLabelProvider(int defaultTiersCount) {
			this.defaultTiersCount = defaultTiersCount;
		}
		
		public ShelfLabelProvider() {
			
		}

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			if (columnIndex == 0)  {
				if (element instanceof SNameValue) {
					return "货位" + ((SNameValue)element).getValue();
				} else if (element instanceof ShelfItem){
					ShelfItem shelfItem = (ShelfItem)element;
					return "货位" + String.valueOf(shelfItem.getShelfNo());
				}
			} else if (columnIndex == 1) {
				if (element instanceof SNameValue) {
					return String.valueOf(defaultTiersCount);
				} else if (element instanceof ShelfItem){
					ShelfItem shelfItem = (ShelfItem)element;
					return String.valueOf(shelfItem.getTiersCount());
				}
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}
	
}

class ShelfInfoItemEntity implements ShelfInfoItem {
	private GUID id;
	private int shelfNo;
	private int tiersCount;
	
	public ShelfInfoItemEntity(GUID id, int shelfNo, int tiersCount) {
		this.id = id;
		this.shelfNo = shelfNo;
		this.tiersCount = tiersCount;
	}
	
	public GUID getId() {
		return id;
	}
	public int getShelfNo() {
		return shelfNo;
	}
	public int getTiersCount() {
		return tiersCount;
	}
}
