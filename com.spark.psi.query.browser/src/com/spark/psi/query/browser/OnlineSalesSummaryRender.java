package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.query.browser.OnlineSalesSummaryProcessor.OnlineSalesListProcessor;
import com.spark.psi.query.intf.publish.entity.OnlineSalesItem;

public class OnlineSalesSummaryRender extends GoodsCategoryFramePageRender{

	/**
	 * 商品库存查询视图
	 */
	public final static class OnlineSalesRender extends PSIGoodsListPageRender{

		private LWComboList stationList;

		@Override
		protected void afterFooterRender(){
			super.afterFooterRender();
			//
			new Label(headerLeftArea).setText("站点：");
			new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(OnlineSalesListProcessor.ID_COMBOLIST_STATION);
//			//
			new Label(headerLeftArea).setText("  销售金额：");
			//
			Label label = new Label(headerLeftArea);
			label.setID(OnlineSalesListProcessor.ID_LABEL_TOTALAMOUNT);
			GridData gd = new GridData();
			gd.widthHint = 100;
			label.setLayoutData(gd);
			
			Button advance = new Button(headerRightArea,JWT.APPEARANCE3);
			advance.setText("高级搜索");
			advance.setID(OnlineSalesListProcessor.ID_BUTTON_ADVANCE);
		}

		public STableColumn[] getColumns(){

			STableColumn[] columns = new STableColumn[7];

			// 需要加SheetId 获取调拨单ID
			columns[0] =
		        new STableColumn(OnlineSalesSummaryProcessor.Cloumns.goodsCode.name(), 100, JWT.CENTER, "商品编号");
			columns[1] =
			        new STableColumn(OnlineSalesSummaryProcessor.Cloumns.GoodNumber.name(), 100, JWT.CENTER, "商品条码");

			columns[2] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.GoodsName.name(), 0, JWT.CENTER, "商品名称");
			columns[2].setGrab(true);

			columns[3] =
			        new STableColumn(OnlineSalesSummaryProcessor.Cloumns.GoodsAttribute.name(), 0, JWT.CENTER, "商品规格");
			columns[3].setGrab(true);

			columns[4] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.Unit.name(), 0, JWT.CENTER, "单位");
			columns[4].setGrab(true);

			columns[5] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.Count.name(), 0, JWT.CENTER, "销售数量");
			columns[5].setGrab(true);
			
			columns[6] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.Amount.name(), 0, JWT.CENTER, "销售金额");
			columns[6].setGrab(true);

			return columns;
		}

		@Override
		public STableStyle getTableStyle(){
			STableStyle sTableStyle = new STableStyle();
			sTableStyle.setPageAble(false);
//			sTableStyle.setSortAll(true);
			return sTableStyle;
		}

		public String getText(Object element, int columnIndex){
			OnlineSalesItem item = (OnlineSalesItem)element;
			stationList =
			        this.createControl(OnlineSalesListProcessor.ID_COMBOLIST_STATION, LWComboList.class, JWT.NO);
			switch(columnIndex){
				case 0:
					return item.getGoodsCode();
				case 1:
					return item.getGoodsNo();
				case 2:
					return item.getGoodsName();
				case 3:
					return item.getGoodsSpec();
				case 4:
					return item.getUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getCount());
				case 6:
					return DoubleUtil.getRoundStr(item.getAmount()); 
				default:
					return "";
			}
		}

		/**
		 * 获得精度
		 */
		@Override
		public int getDecimal(Object element, int columnIndex){
			return -1;
		}

		private boolean isBoss(){
			return getContext().find(Boolean.class, Auth.Boss);
		}
	}

}
