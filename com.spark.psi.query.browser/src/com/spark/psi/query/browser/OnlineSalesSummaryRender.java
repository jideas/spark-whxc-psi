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
	 * ��Ʒ����ѯ��ͼ
	 */
	public final static class OnlineSalesRender extends PSIGoodsListPageRender{

		private LWComboList stationList;

		@Override
		protected void afterFooterRender(){
			super.afterFooterRender();
			//
			new Label(headerLeftArea).setText("վ�㣺");
			new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(OnlineSalesListProcessor.ID_COMBOLIST_STATION);
//			//
			new Label(headerLeftArea).setText("  ���۽�");
			//
			Label label = new Label(headerLeftArea);
			label.setID(OnlineSalesListProcessor.ID_LABEL_TOTALAMOUNT);
			GridData gd = new GridData();
			gd.widthHint = 100;
			label.setLayoutData(gd);
			
			Button advance = new Button(headerRightArea,JWT.APPEARANCE3);
			advance.setText("�߼�����");
			advance.setID(OnlineSalesListProcessor.ID_BUTTON_ADVANCE);
		}

		public STableColumn[] getColumns(){

			STableColumn[] columns = new STableColumn[7];

			// ��Ҫ��SheetId ��ȡ������ID
			columns[0] =
		        new STableColumn(OnlineSalesSummaryProcessor.Cloumns.goodsCode.name(), 100, JWT.CENTER, "��Ʒ���");
			columns[1] =
			        new STableColumn(OnlineSalesSummaryProcessor.Cloumns.GoodNumber.name(), 100, JWT.CENTER, "��Ʒ����");

			columns[2] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.GoodsName.name(), 0, JWT.CENTER, "��Ʒ����");
			columns[2].setGrab(true);

			columns[3] =
			        new STableColumn(OnlineSalesSummaryProcessor.Cloumns.GoodsAttribute.name(), 0, JWT.CENTER, "��Ʒ���");
			columns[3].setGrab(true);

			columns[4] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.Unit.name(), 0, JWT.CENTER, "��λ");
			columns[4].setGrab(true);

			columns[5] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.Count.name(), 0, JWT.CENTER, "��������");
			columns[5].setGrab(true);
			
			columns[6] = new STableColumn(OnlineSalesSummaryProcessor.Cloumns.Amount.name(), 0, JWT.CENTER, "���۽��");
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
		 * ��þ���
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
