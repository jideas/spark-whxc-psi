package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIGoodsListPageRender;

public abstract class GoodsListRender extends PSIGoodsListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		//
		new Label(headerLeftArea).setText("��Ʒ������");
		Label label = new Label(headerLeftArea);
		label.setID(GoodsListProcessor.ID_Label_Count);
		GridData gdLabel = new GridData();
		gdLabel.widthHint = 120;
		label.setLayoutData(gdLabel);
		new Label(headerLeftArea).setText("  ");

		//
		Button newgoodsButton = new Button(footerLeftArea, JWT.APPEARANCE2);
		newgoodsButton.setID(GoodsListProcessor.ID_Button_NewGoods);
		newgoodsButton.setText(" ������Ʒ ");
		
		new Label(footerLeftArea).setText("  ");
		Button statusButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		statusButton.setID(GoodsListProcessor.ID_Button_status);
		statusButton.setText(getStatusButtonText());
		
		new Label(footerLeftArea).setText("  ");
		Button delButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		delButton.setID(GoodsListProcessor.ID_Button_Delete);
		delButton.setText(" ɾ����Ʒ ");
	}
	
	protected abstract String getStatusButtonText();

	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setSelectionMode(SSelectionMode.Multi);
		tableStyle.setRowHeight(24);
		return tableStyle;
	}

//	public String getText(Object element, int columnIndex) {
//		GoodsInfo item = ((GoodsInfo) element);
//		switch (columnIndex) {
//		case 0:
//			return item.getCode();
//		case 1:
//			String text = StableUtil.toLink("detail", "aaa", item.getName());
//			boolean isSetPrice = true; //δ�������ۼ۸񣺴���δ�������ۼ۸����ϸ
//			for (GoodsItemData itemData : item.getItems()) {
//				if (itemData.getSalePrice() <= 0) {
//					isSetPrice = false;
//					break;
//				}
//			}
//			if (!isSetPrice) {
//				ImageDescriptor image = BaseImages
//					.getImage("images/goods/saas_no_price.png");
//				text += StableUtil.toImg(image.getDNAURI(), "δ�������ۼ۸�", 15);
//			}
//			return text;
//			// return "<a href=\"javascript:notifyGrid('detail','" +
//			// item.getId()
//			// + "');\">" + item.getName() + "</a>"; //
//			// XXX����ʱ��id��Ϊ�������ݣ�ʵ��Ӧ���ؼ����л�ȡ
//		}
//		return "";
//	}
}
