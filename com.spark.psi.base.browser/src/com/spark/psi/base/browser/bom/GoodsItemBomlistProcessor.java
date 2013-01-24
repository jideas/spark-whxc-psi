package com.spark.psi.base.browser.bom;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;
import com.spark.psi.publish.base.bom.entity.BomItem;
import com.spark.psi.publish.base.bom.key.GetBomItemListByGoodsIdKey;
import com.spark.psi.publish.base.bom.task.DeleteBomTask;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;

public class GoodsItemBomlistProcessor extends BaseFormPageProcessor {
	public final static String ID_Label_Name = "Label_Name";
	public final static String ID_Label_Spec = "Label_Spec";
	public final static String ID_Label_Code = "Label_Code";

	public final static String ID_Label_ItemNumber = "Label_ItemNumber";
	public final static String ID_Label_Unit = "Label_Unit";
	public final static String ID_Label_LossRate = "Label_LossRate";

	public final static String ID_Table_Bom = "Table";
	public final static String ID_Button_Add = "Button";
	public static final int DEFAULT_Table_COUNT = 10;

	public enum Column {
		BomNo, Creator, Approvor, CreateDate, Status
	}

	private GoodsItemInfo info;
	private SEditTable table;

	private Label code, name, number, spec, unit, lossRate;
	private Button addNewButton;

	@Override
	public void process(Situation context) {
		initControls();
		addActionToControls();
		initControls();

	}

	private void addActionToControls() {
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFunction[] functions = new BaseFunction[] { new BaseFunction(new PageControllerInstance(new PageController(
						EditBomProcessor.class, EditBomRender.class), info.getItemData().getId()), "新增BOM") };
				MsgRequest request = new MsgRequest(functions, "新增BOM");
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		table.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName, String actionValue) {
				if (Action.Delete.name().equals(actionName)) {
					context.handle(new DeleteBomTask(GUID.valueOf(rowId)));
					table.removeRow(rowId);
					table.renderUpate();
				} else if (Action.Submit.name().equals(actionName)) {
					BaseFunction[] functions = new BaseFunction[] { new BaseFunction(new PageControllerInstance(
							new PageController(EditBomProcessor.class, EditBomRender.class), info.getItemData().getId(), rowId),
							"BOM详情") };
					MsgRequest request = new MsgRequest(functions, "BOM详情");
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							table.render();
						}
					});
					getContext().bubbleMessage(request);
				} else if (Action.Active.name().equals(actionName)) {
					BaseFunction[] functions = new BaseFunction[] { new BaseFunction(new PageControllerInstance(
							new PageController(EditBomProcessor.class, ActivingBomRender.class), info.getItemData().getId(),
							rowId), "BOM详情") };
					MsgRequest request = new MsgRequest(functions, "BOM详情");
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							info = context.find(GoodsItemInfo.class,info.getItemData().getId());
							table.render();
							getContext().bubbleMessage(new MsgResponse(false));
						}
					});
					getContext().bubbleMessage(request);
				} else if (Action.Detail.name().equals(actionName)) {
					BaseFunction[] functions = new BaseFunction[] { new BaseFunction(new PageControllerInstance(
							new PageController(EditBomProcessor.class, IneffectBomRender.class), info.getItemData().getId(),
							rowId), "BOM详情") };
					MsgRequest request = new MsgRequest(functions, "BOM详情");
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							table.render();
						}
					});
					getContext().bubbleMessage(request);
				}
			}
		});
		table.setContentProvider(new SEditContentProvider() {

			public String getElementId(Object element) {
				if (element instanceof String) {
					return (String) element;
				}
				if (element instanceof BomItem) {
					return ((BomItem) element).getId().toString();
				}
				return null;
			}

			public Object[] getElements(Context context, STableStatus tableState) {
				List<BomItem> list = context.getList(BomItem.class, new GetBomItemListByGoodsIdKey(info.getItemData().getId()));
				return list.toArray();
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public boolean isSelected(Object element) {
				return false;
			}

			public String[] getActionIds(Object element) {
				if (null != info.getItemData().getBomId() && info.getItemData().getBomId().equals(((BomItem) element).getId())) {
					return new String[] { Action.Detail.name() };
				} else if (((BomItem) element).getStatus() == BOM_STATUS.InEffect) {
					return new String[] { Action.Active.name() };
				} else {
					return new String[] { Action.Submit.name(), Action.Delete.name() };
				}
			}

			public SNameValue[] getExtraData(Object element) {
				return null;
			}

			public Object getNewElement() {
				return null;
			}

			public String getValue(Object element, String columnName) {
				return null;
			}

		});
		table.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				BomItem item = (BomItem) element;
				switch (columnIndex) {
				case 0:

					return item.getBomNo();
				case 1:

					return item.getCreator();
				case 2:

					return item.getApprovePersonName();
				case 3:

					return item.getCreateDate();
				case 4:
					if (null != info.getItemData().getBomId() && info.getItemData().getBomId().equals(item.getId())) {
						return "启用中";
					}
					return item.getStatus().getTitle();
				case 5:

					return item.getRemark();

				default:
					break;
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

	private void initControls() {
		addNewButton = this.createControl(ID_Button_Add, Button.class);
		info = getContext().find(GoodsItemInfo.class, this.getArgument());
		code = this.createControl(ID_Label_Code, Label.class);
		code.setText(info.getBaseInfo().getCode());
		name = this.createControl(ID_Label_Name, Label.class);
		name.setText(info.getBaseInfo().getName());
		number = this.createControl(ID_Label_ItemNumber, Label.class);
		spec = this.createControl(ID_Label_Spec, Label.class);
		unit = this.createControl(ID_Label_Unit, Label.class);
		lossRate = this.createControl(ID_Label_LossRate, Label.class);
		table = createControl(ID_Table_Bom, SEditTable.class);
		number.setText(info.getItemData().getGoodsItemNo());
		spec.setText(info.getItemData().getSpec());
		unit.setText(info.getItemData().getUnit());
//		lossRate.setText(DoubleUtil.getRoundStr(DoubleUtil.mul(100, info.getItemData().getLossRate())) + "%");
		lossRate.setText(DoubleUtil.getRoundStr(info.getItemData().getLossRate(), 4));
	}

}
