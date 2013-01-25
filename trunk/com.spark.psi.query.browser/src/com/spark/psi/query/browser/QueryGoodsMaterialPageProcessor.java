package com.spark.psi.query.browser;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem.MaterialsItem;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey;

public class QueryGoodsMaterialPageProcessor extends BaseFormPageProcessor {
	
	public static final String ID_Table_Goods = "Table_Goods";
	public static final String ID_Table_Material = "Table_Material";
	public static final String ID_Button_Goods = "Button_Goods";
	public static final String ID_Button_Calculate = "Button_Calculate";
	public static final String ID_Button_Reset = "Button_Reset";
	

	public static enum GoodsTableName {
		goodsName("商品名称"), 
		spec("规格"), 
		unit("单位"), 
		count("数量");
		
		private String title;
		private GoodsTableName(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
	
	public static enum MaterialTableName {
		name("材料名称"), 
		unit("单位"), 
		count("预计数量");
		
		private String title;
		private MaterialTableName(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
	
	public static enum GoodsExtraName {
		goodsId, goodsCode, goodsNo, goodsName, goodsSpec, unit, goodsScale, bomId
	}

	
	private SEditTable goodsTable        = null;
	private STable materialTable = null;
	
	private GoodsItemInfo[] showGoods   = null;
	
	@Override
	public void process(Situation context) {
		goodsTable = createControl(ID_Table_Goods, SEditTable.class);
		materialTable = createControl(ID_Table_Material, STable.class);
		
		goodsTable.setContentProvider(new GoodsTableContentProvider());
		materialTable.setContentProvider(new MaterialTableConentProvider());
		
		goodsTable.addActionListener(new SActionListener() {
			
			@Override
			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				goodsTable.removeRow(rowId);
				goodsTable.renderUpate();
			}
		});
		goodsTable.render();
		materialTable.render();
		
		final Button goodsButton = createButtonControl(ID_Button_Goods);
		final Button calculateButton = createButtonControl(ID_Button_Calculate);
		final Button resetButton = createButtonControl(ID_Button_Reset);
		
		addGoodsAction(goodsButton);
		addCalculateAction(calculateButton);
		addResetAction(resetButton);
		
	}
	
	private void addGoodsAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 添加商品
				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(null);
				request.setResponseHandler(new ResponseHandler() {
					
					@Override
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null == returnValue) return;
						showGoods = (GoodsItemInfo[])returnValue;
						goodsTable.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}
	
	private void addResetAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 重置
				showGoods = null;
				goodsTable.render();
				materialTable.render();
			}
		});
	}
	
	private void addCalculateAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 计算材料
				materialTable.render();
			}
		});
	}
	private TotalMaterialsItem getTotalMaterialsItem() {
		if (!validateGoodsTableInput()) return null;
		String[] goodsRowIds = goodsTable.getAllRowId();
		GetTotalMaterialsKey key = new GetTotalMaterialsKey();
		GetTotalMaterialsKey.GoodsItem[] items = new GetTotalMaterialsKey.GoodsItem[goodsRowIds.length]; 
		GetTotalMaterialsKey.GoodsItem item = null;
		for (int index = 0; index < goodsRowIds.length; index++) {
			item = key.new GoodsItem();
			String rowId = goodsRowIds[index];
			String[] goodsInfos = goodsTable.getExtraData(rowId, GoodsExtraName.goodsId.name(),
					GoodsExtraName.goodsCode.name(), GoodsExtraName.goodsNo.name(), 
					GoodsExtraName.goodsName.name(), GoodsExtraName.goodsSpec.name(), 
					GoodsExtraName.unit.name(),	GoodsExtraName.goodsScale.name());
			String countStr = goodsTable.getEditValue(rowId, GoodsTableName.count.name())[0];
			item.setGoodsId(GUID.tryValueOf(goodsInfos[0]));
			item.setGoodsCode(goodsInfos[1]);
			item.setGoodsNo(goodsInfos[2]);
			item.setGoodsName(goodsInfos[3]);
			item.setGoodsSpec(goodsInfos[4]);
			item.setUnit(goodsInfos[5]);
			item.setGoodsScale(Integer.parseInt(goodsInfos[6]));
			item.setCount(DoubleUtil.strToDouble(countStr));
			
			items[index] = item;
		}
		key.setGoodsItems(items);
		TotalMaterialsItem totalMItem = getContext().find(TotalMaterialsItem.class, key);
		return totalMItem;
	}
	
	private boolean validateGoodsTableInput() {
		String[] goodsRowIds = goodsTable.getAllRowId();
		if (goodsRowIds == null || goodsRowIds.length < 1) { 
			alert("请先选择商品。");
			return false;
		}
		for (String rowId : goodsRowIds) {
			String nameStr = goodsTable.getExtraData(rowId, GoodsExtraName.goodsName.name())[0];
			String spec = goodsTable.getExtraData(rowId, GoodsExtraName.goodsSpec.name())[0];
			String countStr = goodsTable.getEditValue(rowId, GoodsTableName.count.name())[0];
			String bomIdStr = goodsTable.getExtraData(rowId, GoodsExtraName.bomId.name())[0];
			if (StringUtils.isEmpty(bomIdStr)) {
				alert("商品：" + nameStr + "[" + spec + "]" + "未设置BOM，不能进行计算。");
				return false;
			}
			if (StringUtils.isEmpty(countStr)) {
				alert("商品：" + nameStr + "[" + spec + "]" + "的数量为空，请填写。");
				return false;
			}
		}
		return true;
	}
	
	private class GoodsTableContentProvider implements SEditContentProvider {

		@Override
		public String[] getActionIds(Object element) {
			return new String[]{Action.Delete.name()};
		}

		@Override
		public SNameValue[] getExtraData(Object element) {
			GoodsItemInfo item = (GoodsItemInfo)element;
			return new SNameValue[] {new SNameValue(GoodsExtraName.goodsId.name(), item.getItemData().getId().toString()),
					new SNameValue(GoodsExtraName.goodsCode.name(), item.getBaseInfo().getCode()),
					new SNameValue(GoodsExtraName.goodsNo.name(), item.getItemData().getGoodsItemNo()),
					new SNameValue(GoodsExtraName.goodsName.name(), item.getBaseInfo().getName()),
					new SNameValue(GoodsExtraName.goodsSpec.name(), item.getItemData().getSpec()),
					new SNameValue(GoodsExtraName.unit.name(), item.getItemData().getUnit()),
					new SNameValue(GoodsExtraName.goodsScale.name(), item.getItemData().getScale() + ""),
					new SNameValue(GoodsExtraName.bomId.name(), item.getItemData().getBomId() == null ? "" : item.getItemData().getBomId().toString())};
		}

		@Override
		public Object getNewElement() {
			return null;
		}

		@Override
		public String getValue(Object element, String columnName) {
			if (GoodsTableName.count.name().equals(columnName)) {
				return "";
			}
			return null;
		}

		@Override
		public String getElementId(Object element) {
			GoodsItemInfo item = (GoodsItemInfo)element;
			return item.getItemData().getId().toString();
		}

		@Override
		public Object[] getElements(Context context, STableStatus tablestatus) {
			return showGoods;
		}

		@Override
		public boolean isSelectable(Object element) {
			return true;
		}

		@Override
		public boolean isSelected(Object element) {
			return false;
		}
		
	}
	
	private class MaterialTableConentProvider implements SContentProvider {

		@Override
		public String getElementId(Object element) {
			MaterialsItem item = (MaterialsItem)element;
			return item.getMaterialId().toString();
		}

		@Override
		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (showGoods == null) return null;
			TotalMaterialsItem mItem = getTotalMaterialsItem();
			return mItem == null ? null : mItem.getMaterials();
		}

		@Override
		public boolean isSelectable(Object element) {
			return false;
		}

		@Override
		public boolean isSelected(Object element) {
			return false;
		}
		
	}
}
