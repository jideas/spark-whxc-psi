package com.spark.psi.inventory.browser.count;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;
import com.spark.psi.publish.inventory.entity.KitInventoryItem;
import com.spark.psi.publish.inventory.task.InventoryCountTask;
import com.spark.psi.publish.inventory.task.InventoryCountTask.TaskKitCountItem;

/**
 * ������Ʒ����̵㵥��ϸ���洦����1
 * 
 */
public class KitCountSheetDetailProcessor extends SimpleSheetPageProcessor<KitInventoryDetail> implements
        IDataProcessPrompt
{
	// �����ı�
	//	public final static String ID_TEXT_SEARCH = "Text_Search";	
	public final static String ID_Label_Store = "label_Store";
	public final static String ID_Button_AddKit = "Button_AddKit";
	public final static String ID_Button_Export = "Button_Export";//��������ӡû��
	public final static String ID_Button_Finish = "Button_Finish";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Composite_Export = "Composite_Export";

	public static enum Columns{
		GoodsCode,
		GoodsName,
		GoodsProperties,
		GoodsUnit,
		Count,
		AcutalCount,
		BalanceCount,
		Memo
	}

	private GUID sheetId;
	private InventoryCountSheetInfo info;
	//	private Text searchText;
	private String sheetstatus;//�̵㵥״̬ "01", "�̵���","02", "�����"
	private Button btnFinish;
	private Button btnSave;
	private Composite compositeExport;
	private Set<String> kitIds = new HashSet<String>();//������

	//OneKeyResultListProvider<KitInventoryItem, GUID>��ԭ�ֿ�����Ʒ���ж��Ƿ�ɾ��
	private List<KitInventoryItem> KitInventoryItems;

	@Override
	public void init(Situation context){
		//����
		info = (InventoryCountSheetInfo)this.getArgument();
		sheetId = info.getSheetId();//ͨ�����ݵ��̵㵥GUID	
		sheetstatus = info.getSheetstatus().getCode();
		KitInventoryItems = context.getList(KitInventoryItem.class, info.getStoreId());
	}

	@Override
	protected void initSheetData(){

	}

	private TaskKitCountItem[] setData(){
		if(!validateInput())
		{
			return null;
		}
		String[] rowIds = table.getAllRowId();
		TaskKitCountItem[] items = new TaskKitCountItem[rowIds != null ? rowIds.length : 0];
		if(rowIds != null){
			for(int i = 0; i < rowIds.length; i++){
				String[] values =
				        table.getEditValue(rowIds[i], Columns.GoodsName.name(), Columns.GoodsProperties.name(),
				                Columns.GoodsUnit.name(), Columns.AcutalCount.name(), Columns.Memo.name());
				items[i] = new TaskKitCountItem(values[0], values[1], values[2], Double.parseDouble(values[3]), values[4]);
			}
		}
		return items;
	}

	/**
	 * ��Ӳ��ϰ�ť
	 */
	private void createAddGoodsButton(){
		Button btnAddKit = this.createControl(ID_Button_AddKit, Button.class);
		if(null != btnAddKit) btnAddKit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PageController pc = new PageController(KitEditProcessor.class, KitEditRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);

				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(324, 165);
				MsgRequest request = new MsgRequest(pci, "����������Ʒ", windowStyle);
				request.setResponseHandler(new ResponseHandler(){
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						if(returnValue != null){
							if(kitIds.contains(((Kit)returnValue).getId())){//��ͬ��ʾ
								alert("���ֿ��д��ھ�����ͬ��Ʒ���ơ���Ʒ�����͵�λ�Ĳ��ϣ���ֱ���޸ĸ���Ʒ��ʵ������");
								return;
							}
							kitIds.add(((Kit)returnValue).getId());//����
							table.addRow((Kit)returnValue);
							table.renderUpate();
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	/**
	 * ����
	 */
	private void createExportButton(){
		Button btnExport = this.createControl(ID_Button_Export, Button.class);
		if(null != btnExport) btnExport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(table.getAllRowId() == null || table.getAllRowId().length == 0){
					alert("�̵���Ʒ��ϸ�б���Ϊ��");
					return;
				}
				final KitSheetDetailInfo kitInfo = new KitSheetDetailInfo();
				kitInfo.setStore(info.getStoreName());
				kitInfo.setCreator(info.getCreatorName());
				//�����Ʒ��
				String[] rowIds = table.getAllRowId();
				List<Kit> lists = new ArrayList<Kit>();
				if(rowIds != null){
					for(int i = 0; i < rowIds.length; i++){
						String[] extraValues =
						        table.getExtraData(rowIds[i], Columns.GoodsName.name(), Columns.GoodsProperties.name(),
						                Columns.GoodsUnit.name());
						String[] editValues =
						        table.getEditValue(rowIds[i], Columns.Count.name(), Columns.AcutalCount.name(),
						                Columns.Memo.name());
						double count =
						        DoubleUtil.strToDouble(editValues[0]) == null ? 0 : DoubleUtil
						                .strToDouble(editValues[0]);
						double actualCount =
						        DoubleUtil.strToDouble(editValues[1]) == null ? 0 : DoubleUtil
						                .strToDouble(editValues[1]);
						lists.add(new Kit(extraValues[0], extraValues[1], extraValues[2], count, actualCount,
						        editValues[2]));
					}
				}
				kitInfo.setKits(lists.toArray(new Kit[0]));
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				compositeExport.getDisplay().exportFile(date + ".xls", "application/vnd.ms-excel", 0,
				        new ExporterWithContext(){
					        public void run(Context context, OutputStream outputStream) throws IOException{
						        KitCountSheetDetailExport.export(outputStream, kitInfo);
					        }
				        });
			}
		});
	}

	@Override
	public void process(Situation situation){
		super.process(situation);

		//�ؼ�
		Label lbl = this.createControl(ID_Label_Store, Label.class);
		lbl.setText(info.getStoreName());
		btnFinish = this.createControl(ID_Button_Finish, Button.class);
		btnSave = this.createControl(ID_Button_Save, Button.class);
		compositeExport = this.createControl(ID_Composite_Export, Composite.class);
		
		registerValidator();
		
		//�̵��в�������Ӳ��Ϻ͵���
		if(!InventoryCountStatus.Counted.equals(info.getSheetstatus())){
			//����ѡ����ϰ�ť
			createAddGoodsButton();
			//����������ť
			createExportButton();
		}

		//����
		if(null != btnFinish) btnFinish.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(setData() == null || setData().length == 0){
					return;
				}
				InventoryCountTask task = new InventoryCountTask(sheetId, setData());//������Ʒ�̵��б�
				task.setMemo(createMemoText().getText());//���汸ע
				getContext().handle(task, InventoryCountTask.Method.Modify);//�ȱ���
				getContext().handle(task, InventoryCountTask.Method.Finish);//���޸�״̬���
				if(!task.isSuccess())
				{
					if(null!=task.getErrors()&&task.getErrors().length>0)
					{
						StringBuffer message = new StringBuffer();
						for (InventoryCountTask.Error error : task.getErrors()) {
							message.append(error.getMessage());
						}
						alert(message.toString());
						return;
					}
				}
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});

		//����
		if(null != btnSave) btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				processData();
			}
		});

		createMemoText().setText(info.getRemark());
		if(InventoryCountStatus.Counted.equals(info.getSheetstatus())){
			createMemoText().setEnabled(false);
		}
	}

	/**
	 * ����
	 */
	private boolean save(){
		if(setData() == null || setData().length == 0){
			return false;
		}
		InventoryCountTask task = new InventoryCountTask(sheetId, setData());//������Ʒ�̵��б�
		task.setMemo(createMemoText().getText());//���汸ע
		getContext().handle(task, InventoryCountTask.Method.Modify);//�༭
		if(!task.isSuccess())
		{
			if(null!=task.getErrors()&&task.getErrors().length>0)
			{
				StringBuffer message = new StringBuffer();
				for (InventoryCountTask.Error error : task.getErrors()) {
					message.append(error.getMessage());
				}
				alert(message.toString());
				return false;
			}
		}
		hint("����ɹ���");
		return true;
	}
	
	
	/**
	 * ע������֤�� void
	 */
	protected void registerValidator(){
		registerInputValidator(new TableDataValidator(table){

			@Override
			protected String validateRowCount(int rowCount){
				if(rowCount < 1){
					return "�̵���Ʒ����Ϊ��";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName){
				if(Columns.Memo.name().equals(columnName)){
					String[] values = table.getEditValue(rowId, Columns.AcutalCount.name());
					String memo = table.getEditValue(rowId, Columns.Memo.name())[0];
					String countString = table.getExtraData(rowId, Columns.Count.name())[0];
					String goodsName = table.getExtraData(rowId, Columns.GoodsName.name())[0];
					if(CheckIsNull.isNotEmpty(values[0]) && CheckIsNull.isNotEmpty(countString)
					        && !Double.valueOf(countString).equals(Double.valueOf(values[0])))
					{
						if(CheckIsNull.isEmpty(memo)){
							return "��Ʒ��" + goodsName + "��˵������Ϊ�գ�";
						}
					}
				}
				return null;
			}
		});
	}

	@Override
	protected String[] getBaseInfoCellContent(){
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent(){
		return null;
	}

	@Override
	protected String getRemark(){
		return null;
	}

	@Override
	protected String getSheetApprovalInfo(){
		if(info != null){
			String str = "�̵㿪ʼʱ�䣺" + DateUtil.dateFromat(info.getStartDate(), "yyyy-MM-dd HH:mm:ss");
			if(sheetstatus.equals("02"))
			    str += "���̵����ʱ�䣺" + DateUtil.dateFromat(info.getEndDate(), "yyyy-MM-dd HH:mm:ss");
			return str;
		}
		return "";
	}

	@Override
	protected String getSheetCreateInfo(){
		if(info != null) return "�Ƶ��ˣ�" + info.getCreatorName() + "���̵��ˣ�" + info.getName();
		return "";
	}

	@Override
	protected String[] getSheetExtraInfo(){
		return null;
	}

	@Override
	protected String getSheetNumber(){
		if(info != null)
			return info.getSheetNumber();
		else
			return "";
	}

	@Override
	protected String getSheetTitle(){
		return "��������̵㵥";
	}

	@Override
	protected String[] getSheetType(){
		return null;
	}

	@Override
	protected String getStopCause(){
		return null;
	}

	@Override
	public String getElementId(Object element){
		if(element instanceof Kit){
			return ((Kit)element).getId();
		}
		return "";
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus){
		if(null == info){
			return null;
		}
		if(CheckIsNull.isNotEmpty(info.getKitCountItems()) && info.getKitCountItems().length > 0){
			Kit[] kits = new Kit[info.getKitCountItems().length];
			for(int i = 0, len = info.getKitCountItems().length; i < len; i++){
				kits[i] = new Kit();
				kits[i].setKitName(info.getKitCountItems()[i].getKitName());
				kits[i].setKitDesc(info.getKitCountItems()[i].getKitDesc());
				kits[i].setKitUnit(info.getKitCountItems()[i].getKitUnit());
				kits[i].setCount(info.getKitCountItems()[i].getCount());
				kits[i].setActualCount(info.getKitCountItems()[i].getActualCount());
				kits[i].setMemo(info.getKitCountItems()[i].getRemark());
				kits[i].setExistInventory(info.getKitCountItems()[i].isExistInventory());
				kitIds.add(kits[i].getId());
			}
			return kits;
		}
		else
			return null;
	}

	@Override
	public SNameValue[] getExtraData(Object element){
		Kit kit = (Kit)element;
		return new SNameValue[] {new SNameValue(Columns.GoodsName.name(), kit.getKitName() + ""),
		        new SNameValue(Columns.GoodsProperties.name(), kit.getKitDesc() + ""),
		        new SNameValue(Columns.GoodsUnit.name(), kit.getKitUnit() + ""),
		        new SNameValue(Columns.Count.name(), kit.getCount() + ""),
		        new SNameValue(Columns.AcutalCount.name(), kit.getActualCount() + ""),
		        new SNameValue(Columns.Memo.name(), kit.getRemark() + "")};
	}

	@Override
	public String getValue(Object element, String columnName){

		if(sheetstatus.equals("02")){
			return null;
		}

		if(element instanceof Kit){
			Kit item = (Kit)element;

			if(columnName.equals(Columns.GoodsName.name())){
				return item.getKitName();
			}

			if(columnName.equals(Columns.GoodsProperties.name())){
				return item.getKitDesc();
			}

			if(columnName.equals(Columns.GoodsUnit.name())){
				return item.getKitUnit();
			}

			if(columnName.equals(Columns.Count.name())){
				return String.valueOf(item.getCount());
			}

			if(columnName.equals(Columns.AcutalCount.name())){//�༭ʵ������
				return String.valueOf(item.getActualCount());
			}
			if(columnName.equals(Columns.Memo.name())){//�༭��ע
				String str = item.getRemark();
				if(str != null) return str;
				return "";
			}
		}

		return null;
	}

	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.Delete.name()};
	}

	@Override
	protected String[] getElementActionIds(Object element){

		if(element instanceof Kit){
			if(!((Kit)element).isExistInventory()) //����ɾ��ԭ�ֿ��е���Ʒ
			    return new String[] {Action.Delete.name()};
		}

		return null;
	}

	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Delete.name())){

			confirm("ȷ��ɾ����ǰ��¼��", new Runnable(){
				public void run(){
					table.removeRow(rowId);
					table.renderUpate();
					kitIds.remove(rowId);
				}
			});
		}
	}

	/**
	 * ��ʾ��Ϣ
	 */
	public String getPromptMessage(){
		return null;
	}

	/**
	 * ��������
	 */
	public boolean processData(){
		if(save()){
			resetDataChangedstatus();
			return true;
		}
		else{
			return false;
		}
	}
}