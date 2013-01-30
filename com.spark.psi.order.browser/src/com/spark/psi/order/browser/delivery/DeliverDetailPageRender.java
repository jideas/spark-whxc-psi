package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SSpanProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.AbstractFormRender;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.deliver.entity.DeliverInfo;

public class DeliverDetailPageRender extends AbstractFormRender implements SSpanProvider {

	protected DeliverInfo deliverInfo;
	
	@Override
	public void init(final Situation context) {
		super.init(context);
		GUID deliverId = (GUID)getArgument();
		deliverInfo = context.find(DeliverInfo.class, deliverId);
	}

	protected void fillContent() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		STable table = new STable(contentArea, getColumns(), tableStyle);
		table.setLayoutData(GridData.INS_FILL_BOTH);
		table.setLabelProvider(new LabelProvider());
		table.setSpanProvider(this);
		table.setID(DeliverDetailPageProcessor.ID_Table);
		
		if (DeliverStatus.Exception.equals(deliverInfo.getStatus())
				|| DeliverStatus.Handled.equals(deliverInfo.getStatus())) {
			Composite remarkArea = new Composite(contentArea);
			GridLayout glRemark = new GridLayout(2);
			glRemark.marginTop = 5;
			glRemark.marginBottom = 5;
			glRemark.marginLeft = 10;
			glRemark.marginRight = 10;
			remarkArea.setLayout(glRemark);
			remarkArea.setBackground(Color.COLOR_WHITE);
			remarkArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));
			
			GridData gdMemoMain = new GridData(GridData.FILL_HORIZONTAL);
			gdMemoMain.heightHint = 60;
			gdMemoMain.verticalIndent = 5;
			remarkArea.setLayoutData(gdMemoMain);
	
			Label titleLabel = new Label(remarkArea);
			titleLabel.setText("异常原因：");
			titleLabel.setLayoutData(new GridData(GridData.GRAB_VERTICAL
					| GridData.VERTICAL_ALIGN_BEGINNING
					| GridData.HORIZONTAL_ALIGN_END));
	
			Label label = new Label(remarkArea, JWT.APPEARANCE3);
			GridData gdMemo = new GridData(GridData.FILL_BOTH);
			label.setLayoutData(gdMemo);
			label.setID(DeliverDetailPageProcessor.ID_Label_ExceptionReason);
			
			if (DeliverStatus.Handled.equals(deliverInfo.getStatus())) {
				titleLabel = new Label(remarkArea);
				titleLabel.setText("处理方案：");
				titleLabel.setLayoutData(new GridData(GridData.GRAB_VERTICAL
						| GridData.VERTICAL_ALIGN_BEGINNING
						| GridData.HORIZONTAL_ALIGN_END));
				label = new Label(remarkArea);
				GridData gdHandleInfo = new GridData(GridData.FILL_BOTH);
				label.setLayoutData(gdHandleInfo);
				label.setID(DeliverDetailPageProcessor.ID_Label_HandleInfo);
			}
		}
	}

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(DeliverDetailPageProcessor.ColumnName.sheetNo.name(), 180, JWT.LEFT, "订单编号");
		columns[1] = new STableColumn(DeliverDetailPageProcessor.ColumnName.goodsName.name(), 250, JWT.LEFT, "商品名称");
		columns[1].setGrab(true);
		columns[2] = new STableColumn(DeliverDetailPageProcessor.ColumnName.goodsSpec.name(), 150, JWT.LEFT, "规格");
		columns[3] = new STableColumn(DeliverDetailPageProcessor.ColumnName.goodsCount.name(), 150, JWT.RIGHT, "数量");
		columns[4] = new STableColumn(DeliverDetailPageProcessor.ColumnName.memeberName.name(), 150, JWT.CENTER, "会员");
//		columns[5] = new STableColumn(DeliverDetailPageProcessor.ColumnName.amount.name(), 150, JWT.RIGHT, "金额");
		return columns;
	}
	
	private void renderButton() {
		Composite hideArea = new Composite(footerRightArea);
		GridData gdHide = new GridData();
		gdHide.exclude = true;
		hideArea.setLayoutData(gdHide);
		hideArea.setVisible(false);
		hideArea.setID(DeliverDetailPageProcessor.ID_Area_Hide);
		
		Button button = null;
		switch(deliverInfo.getStatus()) {
		case Deliver:
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setText(" 确认配送 ");
			button.setID(DeliverDetailPageProcessor.ID_Button_ConfirmDeliver);
			
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setText(" 打印 ");
			button.setID(DeliverDetailPageProcessor.ID_Button_Print);
			break;
		case Delivering:
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setText(" 配送异常 ");
			button.setID(DeliverDetailPageProcessor.ID_Button_Exception);
			
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setText(" 确认到货 ");
			button.setID(DeliverDetailPageProcessor.ID_Button_ConfrimArrive);
			break;
		case Exception:
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setText(" 处理异常 ");
			button.setID(DeliverDetailPageProcessor.ID_Button_Handle);
			break;
		}
	}
	@Override
	protected void fillFooter() {
		// footer
		new Label(footerLeftArea).setID(DeliverDetailPageProcessor.ID_Label_OtherInfo);
		
		renderButton();
	}
	@Override
	protected void fillHeader() {
		// header
		String tempStr = "    ";
		new Label(headerLeftArea).setText("门店：");
		new Label(headerLeftArea).setID(DeliverDetailPageProcessor.ID_Label_StationName);
		new Label(headerLeftArea).setText(tempStr);
		
		new Label(headerLeftArea).setText("地址：");
		new Label(headerLeftArea).setID(DeliverDetailPageProcessor.ID_Label_Address);
		new Label(headerLeftArea).setText(tempStr);
		new Label(headerLeftArea).setText("包装箱数：");
		Text packageText = new Text(headerLeftArea, JWT.APPEARANCE3);
		GridData gdInput = new GridData();
		gdInput.widthHint = 120;
		packageText.setID(DeliverDetailPageProcessor.ID_Text_PackageCount);
		packageText.setLayoutData(gdInput);
		packageText.setRegExp(TextRegexp.NUMBER);
		
		
		new Label(headerRightArea).setText("单据状态：");
		new Label(headerRightArea).setID(DeliverDetailPageProcessor.ID_Label_Status);
	}
	
	private class LabelProvider implements SLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			DeliverDetailShowItem item = (DeliverDetailShowItem)element;
			switch(columnIndex) {
			case 0:
				return item.getSheetNo();
			case 1:
				return item.getGoodsName();
			case 2:
				return item.getGoodsSpec();
			case 3:
				return DoubleUtil.getRoundStr(item.getGoodsCount());
			case 4:
				return item.getMemeberName();
//			case 5:
//				return DoubleUtil.getRoundStr(item.getAmount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}

	public int getColSpan(Object element, int columnIndex) {
		return 0;
	}

	public int getMaxRowSpan(Object element) {
		DeliverDetailShowItem item = (DeliverDetailShowItem)element;
		return item.getRowSpan();
	}

	public int getRowSpan(Object element, int columnIndex) {
		DeliverDetailShowItem item = (DeliverDetailShowItem)element;
		switch(columnIndex) {
		case 0:
		case 4:
		case 5:
			return item.getRowSpan();
		}
		return 0;
	}

	public boolean isSpanAlready(Object element, int columnIndex) {
		DeliverDetailShowItem item = (DeliverDetailShowItem)element;
		if (item.isFirstItem()) return false;
		switch(columnIndex) {
		case 0:
		case 4:
		case 5:
			return true;
		default:
			return false;
		}
	}

}
