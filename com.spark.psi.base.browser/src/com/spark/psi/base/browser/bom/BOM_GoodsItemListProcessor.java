package com.spark.psi.base.browser.bom;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.goods.entity.ShortGoodsItem;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.goods.key.GetShortGoodsItemListKey;

public final class BOM_GoodsItemListProcessor extends PSIGoodsListPageProcessor {

	public final static String ID_Label_Count = "Label_Count";

	public static enum ColumnName {
		name, code, goodsNo, goodsSpec, unit, bomStatus,
	}

	private Label countLabel;

	private List<ShortGoodsItem> lastShowList;

	@Override
	public void init(Situation situation) {
		super.init(situation);
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		countLabel = this.createControl(ID_Label_Count, Label.class);
	}

	private void editGoodsInfoDetail(final GUID goodsId) {
		BaseFunction[] functions = new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(new PageController(GoodsItemBomlistProcessor.class,
						GoodsItemBomlistRender.class), goodsId), "BOM列表"),
				new BaseFunction(new PageControllerInstance(new PageController(GoodsBomHistorylistProcessor.class,
						GoodsBomHistorylistRender.class), goodsId), "BOM记录") };
		MsgRequest request = new MsgRequest(functions, "商品BOM列表");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	public Object[] getElements(Context context, String searchText, GUID categoryId, STableStatus tablestatus) {
		if (null != lastShowList && lastShowList.size() >= tablestatus.getPageSize()) {
			List<ShortGoodsItem> list = new ArrayList<ShortGoodsItem>();
			list.addAll(lastShowList.subList(0, tablestatus.getPageSize()));
			for (int index = tablestatus.getPageSize() - 1; index >= 0; index--) {
				lastShowList.remove(index);
			}
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				countLabel.setText(preSize + 20);
			}
			return list.toArray();
		}
		GetShortGoodsItemListKey key = new GetShortGoodsItemListKey(tablestatus.getBeginIndex(), tablestatus
				.getPageSize(), false, categoryId, searchText, GoodsStatus.ON_SALE);
		if (null != tablestatus.getSortDirection() && SSortDirection.ASC.equals(tablestatus.getSortDirection())) {
			key.setSortType(SortType.Asc);
		} else {
			key.setSortType(SortType.Desc);
		}
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(GetGoodsInfoListKey.SortField.valueOf(tablestatus.getSortColumn()));
		} else {
			key.setSortField(GetGoodsInfoListKey.SortField.None);
		}
		@SuppressWarnings("unchecked")
		ListEntity<ShortGoodsItem> listEntity = (ListEntity<ShortGoodsItem>) context.find(ListEntity.class, key);
		if (listEntity != null) {
			int size = listEntity.getItemList().size();
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				if (StringHelper.isNotEmpty(preSize)) {
					size += Integer.parseInt(preSize);
				}
				if(listEntity.getItemList().size()<20&&null!=lastShowList&&lastShowList.size()>0){
					if(listEntity.getItemList().size()+lastShowList.size()<tablestatus.getPageSize()){
						size += lastShowList.size();
					}else{
						size += tablestatus.getPageSize()-listEntity.getItemList().size();
					}
				}
			}
			List<ShortGoodsItem> list = new ArrayList<ShortGoodsItem>();
			if (listEntity.getItemList().size() > tablestatus.getPageSize()) {
				size = size - (listEntity.getItemList().size() - tablestatus.getPageSize());
			}
			if (null == lastShowList) {
				lastShowList = new ArrayList<ShortGoodsItem>();
			}
			list.addAll(lastShowList);
			int subcount = (tablestatus.getPageSize() - lastShowList.size()) > listEntity.getItemList().size() ? listEntity
					.getItemList().size()
					: tablestatus.getPageSize() - lastShowList.size();
			list.addAll(listEntity.getItemList().subList(0, subcount));
			lastShowList.clear();
			if (listEntity.getItemList().size() > subcount) {
				lastShowList.addAll(listEntity.getItemList().subList(subcount,listEntity.getItemList().size()));
			}
			countLabel.setText(String.valueOf(size));
			// countLabel.setText(String.valueOf(listEntity.getTotalCount()));
			return list.toArray();
		} else {
			return null;
		}
	}

	@Override
	public String getElementId(Object element) {
		return ((ShortGoodsItem) element).getId().toString();
	}

	protected boolean isViewJointOnly() {
		return false;
	}

	protected boolean isViewOnSale() {
		return true;
	}

	@Override
	public String[] getTableActionIds() {
		List<String> actionList = new ArrayList<String>();
		actionList.add(Action.Detail.name());
		return actionList.toArray(new String[actionList.size()]);
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		List<String> actionList = new ArrayList<String>();
		actionList.add(Action.Detail.name());
		return actionList.toArray(new String[actionList.size()]);
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if ("Detail".toUpperCase().equals(actionName.toUpperCase())) {
			editGoodsInfoDetail(GUID.valueOf(rowId)); // XXX：表格控件缺陷，无法直接获取到rowId
		}
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}
