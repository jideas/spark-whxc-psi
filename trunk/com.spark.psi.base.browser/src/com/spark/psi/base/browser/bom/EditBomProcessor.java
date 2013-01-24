package com.spark.psi.base.browser.bom;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.material.MaterialsSelectParameters;
import com.spark.psi.base.browser.util.BomDetailItem;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;
import com.spark.psi.publish.base.bom.entity.BomInfo;
import com.spark.psi.publish.base.bom.entity.BomInfoItem;
import com.spark.psi.publish.base.bom.task.BomApproveTask;
import com.spark.psi.publish.base.bom.task.BomInEffectTask;
import com.spark.psi.publish.base.bom.task.BomInfoTask;
import com.spark.psi.publish.base.bom.task.BomInfoTaskItem;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;

public class EditBomProcessor extends BaseFormPageProcessor implements IDataProcessPrompt {
	public final static String ID_Label_Name = "Label_Name";
	public final static String ID_Label_Spec = "Label_Spec";
	public final static String ID_Label_Code = "Label_Code";

	public final static String ID_Label_ItemNumber = "Label_ItemNumber";
	public final static String ID_Label_Unit = "Label_Unit";
	public final static String ID_Label_LossRate = "Label_LossRate";

	public final static String ID_Table_Bom = "Table";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Except = "Button_Except";
	public final static String ID_Button_Submit = "Button_Submit";
	public final static String ID_Button_AddMaterial = "Button_AddMaterial";
	public final static String ID_Button_Active = "Button_Active";
	public final static String ID_Button_Approve = "Button_Approve";
	public final static String ID_Button_Reject = "Button_Reject";

	public final static String ID_Text_Remark = "Text_Remark";
	public static final int DEFAULT_Table_COUNT = 10;

	public enum Column {
		goodsCode, goodsNo, goodsName, goodsSpec, goodsUnit, count, lossRate, realCount,
	}

	private BomInfo bom;

	private GoodsItemInfo info;
	private SEditTable table;

	private Label code, name, number, spec, unit, lossRate;
	private Button submitButton, addMaterial, saveButton, approveButton, rejectButton, activeButton, exportButton;
	private Text remarkText;

	private Set<GUID> itemIdSet = new HashSet<GUID>();
	private List<BomDetailItem> itemList = new ArrayList<BomDetailItem>();

	@Override
	public void process(Situation context) {
		initControls();
		addActionToControls();

	}

	protected boolean doSave(BOM_STATUS status) {
		if (!validateTableInput())
			return false;
		BomInfoTask task = new BomInfoTask();
		if (null != bom) {
			task.setId(bom.getId());
		}
		task.setRemark(this.remarkText.getText());
		task.setGoodsItemId(info.getItemData().getId());
		task.setGoodsNo(info.getItemData().getGoodsItemNo());
		task.setStatus(status);
		String[] rowIds = this.table.getAllRowId();
		List<BomInfoTaskItem> items = new ArrayList<BomInfoTaskItem>();
		for (String id : rowIds) {
			BomInfoTaskItem item = new BomInfoTaskItem();
			item.setMaterialId(GUID.valueOf(id));
			String[] count = table.getEditValue(id, Column.count.name());
			if(null==count){
				continue;
			}
			item.setCount(DoubleUtil.strToDouble(count[0], 2));
			String[] lossRate = table.getEditValue(id, Column.lossRate.name());
			if(null==lossRate){
				lossRate = new String[]{"0"};
			}
			item.setLossRate(DoubleUtil.strToDouble(lossRate[0], 4));
			item.setRealCount(DoubleUtil.div(item.getCount(), DoubleUtil.sub(1, item.getLossRate()), 2));
			items.add(item);
		}
		if (items.size() == 0) {
			alert("请填写BOM子件信息！");
			return false;
		}
		task.setItems(items);
		context.handle(task);
		MsgResponse response = new MsgResponse(true);
		getContext().bubbleMessage(response);
		return true;
	}

	private boolean validateTableInput() {
		String[] rowIds = this.table.getAllRowId();
		for (String id : rowIds) {
			String lossRateStr = table.getEditValue(id, Column.lossRate.name())[0];
			if (StringUtils.isNotEmpty(lossRateStr)) {
				if (DoubleUtil.strToDouble(lossRateStr, 4) < 0 || DoubleUtil.strToDouble(lossRateStr, 4) >= 1) {
					alert("损耗率只能为大于等于0且小于1的小数。");
					return false;
				}
			}
			String baseCount = table.getEditValue(id, Column.count.name())[0];
			if (CheckIsNull.isEmpty(baseCount) || DoubleUtil.strToDouble(baseCount) == 0) {
				alert("子件数量不可以为0，请检查！");
				return false;
			}
		}
		return true;
	}

	private void addActionToControls() {
		if (saveButton != null && !saveButton.isDisposed()) {
			this.saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doSave(BOM_STATUS.Submit);
				}
			});
		}
		if (exportButton != null && !exportButton.isDisposed()) {
			this.exportButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doExport();
				}
			});
		}
		if (submitButton != null && !submitButton.isDisposed()) {
			this.submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doSave(BOM_STATUS.Approveing);
				}
			});
		}
		if (addMaterial != null && !addMaterial.isDisposed()) {
			addMaterial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addMaterialAction();
				}
			});
		}
		if (null != approveButton && !approveButton.isDisposed())
			approveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					context.handle(new BomApproveTask(bom.getId(), true));
					MsgResponse response = new MsgResponse(true);
					getContext().bubbleMessage(response);
				}
			});
		if (null != rejectButton && !rejectButton.isDisposed()) {
			rejectButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					context.handle(new BomApproveTask(bom.getId(), false));
					MsgResponse response = new MsgResponse(true);
					getContext().bubbleMessage(response);
				}
			});
		}
		if (null != activeButton && !activeButton.isDisposed()) {
			activeButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					context.handle(new BomInEffectTask(info.getItemData().getId(), bom.getId()));
					MsgResponse response = new MsgResponse(true);
					getContext().bubbleMessage(response);
				}
			});
		}

		addTableAction();
	}

	/**
	 * table相关事件
	 */
	private void addTableAction() {
		table.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName, String actionValue) {
				if (Action.Delete.name().equals(actionName)) {
					if (itemIdSet.contains(GUID.valueOf(rowId))) {
						itemIdSet.remove(GUID.valueOf(rowId));
					}
					if (null != itemList) {
						for (BomDetailItem item : itemList) {
							if (item.getId().equals(GUID.valueOf(rowId))) {
								itemList.remove(item);
								break;
							}
						}
					}
					table.removeRow(rowId);
					table.renderUpate();
				}
			}
		});
		table.setContentProvider(new SEditContentProvider() {
			public String[] getActionIds(Object element) {
				if (null != bom && bom.getStatus() != BOM_STATUS.Submit && bom.getStatus() != BOM_STATUS.Rejected) {
					return null;
				}
				return new String[] { Action.Delete.name() };
			}

			public SNameValue[] getExtraData(Object element) {
				return null;
			}

			public Object getNewElement() {
				return null;
			}

			public String getValue(Object element, String columnName) {
				if (null != bom && bom.getStatus() != BOM_STATUS.Submit && bom.getStatus() != BOM_STATUS.Rejected) {
					return null;
				}
				BomDetailItem item = (BomDetailItem) element;
				if (Column.count.name().equals(columnName)) {
					return DoubleUtil.getRoundStrWithOutQfw(item.getBaseCount(), 2);
				} else if (Column.lossRate.name().equals(columnName)) {
					return DoubleUtil.getRoundStrWithOutQfw(item.getLossRate(), 4);
				}
				return null;
			}

			public String getElementId(Object element) {
				if (element instanceof BomDetailItem) {
					return ((BomDetailItem) element).getId().toString();
				}
				return null;
			}

			public Object[] getElements(Context context, STableStatus tableState) {
				return itemList.toArray();
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public boolean isSelected(Object element) {
				return false;
			}

		});
		table.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				BomDetailItem item = (BomDetailItem) element;
				switch (columnIndex) {
				case 0:

					return item.getGoodsCode();
				case 1:

					return item.getGoodsNo();
				case 2:

					return item.getGoodsName();
				case 3:

					return item.getGoodsSpec();
				case 4:

					return item.getGoodsUnit();
				case 5:

					return DoubleUtil.getRoundStr(item.getBaseCount());
				case 6:
					return DoubleUtil.getRoundStr(item.getGoodsLossRate(), 4);
				case 7:
					return DoubleUtil.getRoundStr(item.getLossRate(), 4);
				case 8:

					return DoubleUtil.getRoundStr(item.getRealCount());
				}
				return null;
			}

			public Color getForeground(Object element, int columnIndex) {
				return null;
			}

			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		table.render();
	}

	/**
	 * 添加商品窗口打开
	 */
	protected void addMaterialAction() {
		MsgRequest request = CommonSelectRequest.createSelectMaterialRequest(new MaterialsSelectParameters(null, false,
				false, false, null));
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue) {
					return;
				}
				MaterialsItemInfo[] items = (MaterialsItemInfo[]) returnValue;
				for (MaterialsItemInfo g : items) {
					if (itemIdSet.add(g.getItemData().getId())) {
						BomDetailItem item = new BomDetailItem();
						item.setId(g.getItemData().getId());
						item.setGoodsCode(g.getBaseInfo().getCode());
						item.setGoodsNo(g.getItemData().getMaterialNo());
						item.setGoodsName(g.getBaseInfo().getName());
						item.setGoodsSpec(g.getItemData().getMaterialSpec());
						item.setGoodsUnit(g.getItemData().getUnit());
						item.setScale(g.getItemData().getScale());
						item.setGoodsLossRate(g.getItemData().getLossRate());
						itemList.add(item);
					}
				}
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	private void initControls() {

		remarkText = this.createControl(ID_Text_Remark, Text.class);
		code = this.createControl(ID_Label_Code, Label.class);
		name = this.createControl(ID_Label_Name, Label.class);
		number = this.createControl(ID_Label_ItemNumber, Label.class);
		spec = this.createControl(ID_Label_Spec, Label.class);
		unit = this.createControl(ID_Label_Unit, Label.class);
		lossRate = this.createControl(ID_Label_LossRate, Label.class);
		table = createControl(ID_Table_Bom, SEditTable.class);

		if (null != this.getArgument2()) {
			bom = getContext().find(BomInfo.class, GUID.valueOf(this.getArgument2().toString()));
			this.remarkText.setText(bom.getRemark());
			for (BomInfoItem item : bom.getBomInfoItems()) {
				BomDetailItem det = new BomDetailItem();
				det.setGoodsCode(item.getMaterialCode());
				det.setGoodsNo(item.getMaterialNo());
				det.setId(item.getMaterialId());
				det.setGoodsName(item.getMaterialName());
				det.setGoodsSpec(item.getMaterialSpec());
				det.setGoodsUnit(item.getMaterialUnit());
				det.setBaseCount(item.getCount());
				det.setLossRate(item.getLossRate());
				det.setRealCount(item.getRealCount());
				this.itemList.add(det);
			}
		}
		GUID id = (GUID) this.getArgument();
		if (null == id) {
			id = bom.getGoodsItemId();
		}
		info = getContext().find(GoodsItemInfo.class, id);
		code.setText(info.getBaseInfo().getCode());
		name.setText(info.getBaseInfo().getName());
		number.setText(info.getItemData().getGoodsItemNo());
		spec.setText(info.getItemData().getSpec());
		unit.setText(info.getItemData().getUnit());
		// lossRate.setText(DoubleUtil.getRoundStr(DoubleUtil.mul(100,
		// info.getItemData().getLossRate())) + "%");
		lossRate.setText(DoubleUtil.getRoundStr(info.getItemData().getLossRate(), 4));

		approveButton = this.createControl(ID_Button_Approve, Button.class);
		rejectButton = this.createControl(ID_Button_Reject, Button.class);
		activeButton = this.createControl(ID_Button_Active, Button.class);
		saveButton = this.createControl(ID_Button_Save, Button.class);
		submitButton = this.createControl(ID_Button_Submit, Button.class);
		addMaterial = this.createControl(ID_Button_AddMaterial, Button.class);
		exportButton = this.createButtonControl(ID_Button_Except);
		if (null != bom && (bom.getStatus() == BOM_STATUS.Approveing || bom.getStatus() == BOM_STATUS.InEffect)) {
			remarkText.setEnabled(false);
		}

		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "PSIBase.Bom.handleTableDataChange");
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		return false;
	}

	private void doExport() {
		Display.getCurrent().exportFile("材料清单" + bom.getBomNo() + ".xls", "application/vnd.ms-excel", 1000000,
				new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle("材料清单");
						bw.addLabel("母件名称", info.getBaseInfo().getName());
						bw.addLabel("母件编码", info.getBaseInfo().getCode());
						bw.addLabel("母件条码", info.getItemData().getGoodsItemNo());
						bw.addLabel("母件规格", info.getItemData().getSpec());
						bw.addLabel("母件单位", info.getItemData().getUnit());
						bw.addLabel("创建人", bom.getCreator());
						bw.addLabel("备注", bom.getRemark());
						String[] head = new String[] { "子件编号", "子件条码", "子件名称", "子件规格", "计量单位", "基本数量", "损耗率", "需求数量" };
						List<String[]> list = new ArrayList<String[]>();
						for (BomDetailItem item : itemList) {
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getGoodsName(),
									item.getGoodsSpec(), item.getGoodsUnit(),
									DoubleUtil.getRoundStr(item.getBaseCount()),
									DoubleUtil.getRoundStr(item.getLossRate()),
									DoubleUtil.getRoundStr(item.getRealCount()) });
						}
						bw.setTable(head, list);
						try {
							bw.write(bom.getBomNo());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}
}
