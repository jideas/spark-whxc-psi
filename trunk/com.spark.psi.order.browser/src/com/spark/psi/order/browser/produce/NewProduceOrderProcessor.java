package com.spark.psi.order.browser.produce;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.order.browser.common.DistributeGoodsItem;
import com.spark.psi.order.browser.online.DistributeOnlineOrderProcessor;
import com.spark.psi.order.browser.online.DistributeOnlineOrderRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;

public class NewProduceOrderProcessor<Item> extends PSIListPageProcessor<Item> {
	
	public static final String ID_Date_PlanDate = "Date_PlanDate";
	public static final String ID_Button_AddGoods = "Button_AddGoods";
	public static final String ID_Button_Summary = "Button_Summary";
	
	public static enum ColumnName {
		goodsNo, goodsName, goodsSpec, goodsUnit, count
	}
	
	public static enum TableExtraValueName {
		goodsId, goodsCode, goodsNo, goodsName, goodsSpec, unit, goodsScale, bomId
	}
	@Override
	public void process(final Situation context) {
		super.process(context);
		final Button addGoodsButton = createControl(ID_Button_AddGoods, Button.class);
		final Button summaryButton = createControl(ID_Button_Summary, Button.class);
		
		addGoodsButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(true);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						GoodsItemInfo[] result = (GoodsItemInfo[])returnValue;
						for (GoodsItemInfo item : result) {
							table.addRow(item);
						}
						table.renderUpate();
					}
				});
				context.bubbleMessage(request);
			}
		});
		
		summaryButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 打开汇总界面
				if (!validateInput()) return;
				final DistributeGoodsItem[] items = getDisGoodsItems();
				GetTotalMaterialsKey getTotalMateriayKey = new GetTotalMaterialsKey();
				getTotalMateriayKey.setGoodsItems(convertToGoodsItem(getTotalMateriayKey, items));
				// List<TotalMaterialsItem> materialItemList = new ArrayList<TotalMaterialsItem>();
				TotalMaterialsItem totalMateriInfo = null;
				try {
					totalMateriInfo = context.find(TotalMaterialsItem.class, getTotalMateriayKey);
				} catch(Exception ex) {
					ex.printStackTrace();
					alert(ex.getMessage());
					return;
				} catch(Throwable th) {
					th.printStackTrace();
					alert(th.getMessage());
					return;
				}
				if (totalMateriInfo.getMaterials() == null 
						|| totalMateriInfo.getMaterials().length < 1) {
					// 没有材料,直接生产
					CreateProduceOrderTask task = new CreateProduceOrderTask();
					CreateProduceOrderTask.GoodsItem[] goodsItems = new CreateProduceOrderTask.GoodsItem[totalMateriInfo.getGoods().length];
					for (int index = 0; index < goodsItems.length; index++) { 
						CreateProduceOrderTask.GoodsItem cGoodsItem = task.new GoodsItem();
						TotalMaterialsItem.GoodsItem tGoodsItem = totalMateriInfo.getGoods()[0];
						cGoodsItem.setBomId(tGoodsItem.getBomId());
						cGoodsItem.setCount(tGoodsItem.getCount());
						cGoodsItem.setGoodsCode(tGoodsItem.getGoodsCode());
						cGoodsItem.setGoodsId(tGoodsItem.getGoodsId());
						cGoodsItem.setGoodsName(tGoodsItem.getGoodsName());
						cGoodsItem.setGoodsNo(tGoodsItem.getGoodsNo());
						cGoodsItem.setGoodsScale(tGoodsItem.getGoodsScale());
						cGoodsItem.setGoodsSpec(tGoodsItem.getGoodsSpec());
						cGoodsItem.setUnit(tGoodsItem.getUnit());
						goodsItems[index] = cGoodsItem;
					}
					task.setGoods(goodsItems);
//					task.setOnlineOrderIds(getSelectedOrderItemIds());
					task.setMaterials(null);
					task.setGoodsCount(task.getGoods().length);
					task.setPlanDate(0);
					task.setRemark(null);
					task.setStatus(ProduceOrderStatus.Submiting);
					getContext().handle(task);
				} else {
					PageController pc = new PageController(DistributeOnlineOrderProcessor.class, DistributeOnlineOrderRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc, totalMateriInfo.getMaterials(), null, items);
					MsgRequest request = new MsgRequest(pci, "汇总信息");
					request.setResponseHandler(new ResponseHandler() {
						
						public void handle(Object returnValue, Object returnValue2,
								Object returnValue3, Object returnValue4) {
							getContext().bubbleMessage(new MsgResponse(true));
						}
					});
					
					getContext().bubbleMessage(request);
				}
			}
		});
		
		registeValidateors();
	}
	
	private GetTotalMaterialsKey.GoodsItem[] convertToGoodsItem(GetTotalMaterialsKey getTotalMateriayKey, DistributeGoodsItem[] sItems) {
		GetTotalMaterialsKey.GoodsItem[] items = new GetTotalMaterialsKey.GoodsItem[sItems.length];
		GetTotalMaterialsKey.GoodsItem item = null;
		for (int itemIndex = 0; itemIndex < sItems.length; itemIndex++) {
			item = getTotalMateriayKey.new GoodsItem();
			DistributeGoodsItem sItem = sItems[itemIndex];
			
//			item.setBomId(sItem.getBomId());
			item.setCount(sItem.getCount());
			item.setGoodsCode(sItem.getGoodsCode());
			item.setGoodsId(sItem.getGoodsId());
			item.setGoodsName(sItem.getGoodsName());
			item.setGoodsNo(sItem.getGoodsNo());
			item.setGoodsScale(sItem.getGoodsScale());
			item.setGoodsSpec(sItem.getGoodsSpec());
			item.setUnit(sItem.getUnit());
			
			items[itemIndex] = item;
		}
		return items;
	}
	
	private void registeValidateors() {
		registerInputValidator(new TableDataValidator(table) {
			
			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "请先添加商品。";
				}
				return null;
			}
			
			@Override
			protected String validateCell(String rowId, String columnName) {
				if (ColumnName.count.name().equals(columnName)) {
					String value = table.getEditValue(rowId, columnName)[0];
					if (StringUtils.isEmpty(value)) {
						return "数量不能为空。";
					}
				}
				return null;
			}
		});
	}
	
	private DistributeGoodsItem[] getDisGoodsItems() {
		String[] rowIds = table.getAllRowId();
		DistributeGoodsItem[] items = new DistributeGoodsItem[rowIds.length];
		DistributeGoodsItem item = null;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			item = new DistributeGoodsItem();
			String rowId = rowIds[rowIndex];
			String[] goodsInfos = table.getExtraData(rowId, TableExtraValueName.goodsId.name(),
					TableExtraValueName.goodsCode.name(), TableExtraValueName.goodsNo.name(),
					TableExtraValueName.goodsName.name(), TableExtraValueName.goodsSpec.name(),
					TableExtraValueName.unit.name(), TableExtraValueName.goodsScale.name(),
					TableExtraValueName.bomId.name());
			String countStr = table.getEditValue(rowId, ColumnName.count.name())[0];
			item.setCount(DoubleUtil.strToDouble(countStr));
			item.setGoodsId(GUID.tryValueOf(goodsInfos[0]));
			item.setGoodsCode(goodsInfos[1]);
			item.setGoodsNo(goodsInfos[2]);
			item.setGoodsName(goodsInfos[3]);
			item.setGoodsSpec(goodsInfos[4]);
			item.setUnit(goodsInfos[5]);
			item.setGoodsScale(Integer.parseInt(goodsInfos[6]));
			item.setBomId(goodsInfos[7] == null ? null : GUID.tryValueOf(goodsInfos[7]));
			items[rowIndex] = item;
		}
		return items;
	}

	@Override
	public String getElementId(Object element) {
		GoodsItemInfo item = (GoodsItemInfo)element;
		return item.getItemData().getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return null;
	}

	
	
	@Override
	public String getValue(Object element, String columnName) {
		if (ColumnName.count.name().equals(columnName)) {
			return "";
		}
		return null;
	}
	@Override
	public SNameValue[] getExtraData(Object element) {
		GoodsItemInfo item = (GoodsItemInfo)element;
		return new SNameValue[] {new SNameValue(TableExtraValueName.goodsId.name(), item.getItemData().getId().toString()),
				new SNameValue(TableExtraValueName.goodsCode.name(), item.getBaseInfo().getCode()),
				new SNameValue(TableExtraValueName.goodsNo.name(), item.getItemData().getGoodsItemNo()),
				new SNameValue(TableExtraValueName.goodsName.name(), item.getBaseInfo().getName()),
				new SNameValue(TableExtraValueName.goodsSpec.name(), item.getItemData().getSpec()),
				new SNameValue(TableExtraValueName.unit.name(), item.getItemData().getUnit()), 
				new SNameValue(TableExtraValueName.goodsScale.name(), item.getItemData().getScale() + ""),
				new SNameValue(TableExtraValueName.bomId.name(), item.getItemData().getBomId() == null ? null : item.getItemData().getBomId().toString())};
	}
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Delete.name().equals(actionName)) {
			table.removeRow(rowId);
			table.renderUpate();
		}
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] {Action.Delete.name()};
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Delete.name()};
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
	
	

}
