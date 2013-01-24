package com.spark.psi.base.browser.goods;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsTraderLogItem;

/**
 * 指定商品的销售情况列表处理器
 * 
 */
public class GoodsSalesSummaryProcessor extends
		PSIListPageProcessor<GoodsTraderLogItem> {
	public final static String ID_LABEL_GOODSNUMBER = "Label_GoodsNumber";
	public final static String ID_LABEL_GOODSNAME = "Label_GoodsName";
	
	private GoodsInfo goodsInfo;
	
	
	
	@Override
	public void init(Situation context) {
		if(getArgument() != null && getArgument() instanceof GUID) {
			GUID goodsId = (GUID)getArgument();
			goodsInfo = context.find(GoodsInfo.class, goodsId);
		}
	}
	
	@Override
	public void process(Situation situation) {
		super.process(situation);
		Label countLabel = this.createControl(ID_LABEL_GOODSNUMBER, Label.class, JWT.NONE);
		countLabel.setText(goodsInfo.getCode());
		
		Label unCountLabel = this.createControl(ID_LABEL_GOODSNAME, Label.class, JWT.NONE);
		unCountLabel.setText(goodsInfo.getName());
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
//		GetGoodsTraderLogKey key = new GetGoodsTraderLogKey(goodsInfo == null ? null : goodsInfo.getId());
//		@SuppressWarnings("unchecked")
//		ListEntity listEntity = context.find(ListEntity.class, key);
//		if(null == listEntity) {
//			return null;
//		} else {
//			return listEntity.getItemList().toArray();
//		}
		if(goodsInfo == null) return null;
		List<GoodsTraderLogItem> resultList = context.getList(GoodsTraderLogItem.class, goodsInfo.getId(), GoodsTraderLogItem.TraderType.Sales);
		return resultList.toArray();
	}

	public String getElementId(Object element) {
		return GUID.randomID().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return null;
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
