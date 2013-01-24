/**
 * 
 */
package com.spark.psi.base.browser;


import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.search.StableSearch;
import com.spark.common.components.table.edit.SEditTableStyle;

/**
 * 
 *
 */
public abstract class SimpleSheetPageRender extends PSIListPageRender {
	
	@SuppressWarnings("unused")
	private Composite headerLeftArea;
	@SuppressWarnings("unused")
	private Composite headerCenterArea;
	@SuppressWarnings("unused")
	private Composite headerRightArea;
	@SuppressWarnings("unused")
	private Composite footerLeftArea;
	@SuppressWarnings("unused")
	private Composite footerCenterArea;
	@SuppressWarnings("unused")
	private Composite footerRightArea;
	@SuppressWarnings("unused")
	private Composite footerArea;
	@SuppressWarnings("unused")
	private Composite contentArea;
	

	private Composite titleArea;

	private static GridData gdTitle;
	private static GridData gdTableHeaderLeft;
	private static GridData gdTableHeaderRight;

	private static GridLayout glTitle;
	private static GridLayout glTableHeaderCell;

	private Text memoText; 
	private Composite findSearchArea;
	
	static {
		gdTitle = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gdTitle.heightHint = 40;

		gdTableHeaderLeft = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_BEGINNING);
		gdTableHeaderRight = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END);
		gdTableHeaderLeft.heightHint = gdTableHeaderRight.heightHint = 24;

		glTitle = new GridLayout();
		glTitle.numColumns = 2;

		glTableHeaderCell = new GridLayout();
		glTableHeaderCell.numColumns = 20;
		glTableHeaderCell.horizontalSpacing = 0;
//		glTableHeader.marginTop = 5;
	}

	@Override
	protected final void beforeTableRender() {
		//
		titleArea = new Composite(super.contentArea);
		titleArea.setLayoutData(gdTitle);
		titleArea.setLayout(glTitle);

		// do header render
		SheetTitleComposite titleCmp = new SheetTitleComposite(titleArea);
		GridData gdTitle = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_CENTER);
		gdTitle.widthHint = 330;
		titleCmp.setLayoutData(gdTitle);
		titleCmp.setID(SimpleSheetPageProcessor.ID_SheetTitleComposite);
		SheetNumberComposite codeCmp = new SheetNumberComposite(titleArea);
		GridData gdCode = new GridData(GridData.HORIZONTAL_ALIGN_CENTER
				| GridData.VERTICAL_ALIGN_BEGINNING);
		codeCmp.setLayoutData(gdCode);
		codeCmp.setID(SimpleSheetPageProcessor.ID_SheetNumberComposite);

		//
		afterTitleRender();

	}

	protected final void afterTitleRender() {
		int tableHeaderRowCount = getBaseInfoAreaRowCount();
		if (tableHeaderRowCount < 1)
			return;

		GridData gdTableHeader1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		for (int rowIndex = 0; rowIndex < tableHeaderRowCount; rowIndex++) {
			GridLayout glTableHeader = new GridLayout();
			glTableHeader.numColumns = 2;
			if(rowIndex == 0) {
				glTableHeader.marginTop = 2;
			}
			if(rowIndex == tableHeaderRowCount - 1) {
				glTableHeader.marginBottom = 2;
			}

			Composite tableHeaderArea = new Composite(super.contentArea);
			tableHeaderArea.setLayout(glTableHeader);
			tableHeaderArea.setLayoutData(gdTableHeader1);

			Composite tempLeft = new Composite(tableHeaderArea);
			tempLeft.setLayoutData(gdTableHeaderLeft);
			tempLeft.setLayout(glTableHeaderCell);

			Composite tempRight = new Composite(tableHeaderArea);
			tempRight.setLayoutData(gdTableHeaderRight);
			tempRight.setLayout(glTableHeaderCell);
			
			fillBaseInfoCellControl(tempLeft, rowIndex, 0);
			
			if(isShowFind() && rowIndex == (tableHeaderRowCount - 1)) {
				findSearchArea = tempRight;
			} else {
				fillBaseInfoCellControl(tempRight, rowIndex, 1);
			}
		}
	}

	protected final void afterTableRender() {
		Composite tableBottomArea = new Composite(super.contentArea);
		GridData gdTableBottom = new GridData(GridData.FILL_HORIZONTAL);
//		gdTableBottom.heightHint = 85;
		gdTableBottom.verticalIndent = -1;
		GridLayout glTableBottom = new GridLayout();
		glTableBottom.verticalSpacing = 10;
		glTableBottom.marginTop = 5;
		glTableBottom.marginBottom = 5;
		glTableBottom.marginLeft = 10;
		glTableBottom.marginRight = 10;
		tableBottomArea.setLayout(glTableBottom);
		tableBottomArea.setLayoutData(gdTableBottom);
		tableBottomArea.setBackground(Color.COLOR_WHITE);
		tableBottomArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));
		
		
		GridLayout glMemo = new GridLayout();
		glMemo.horizontalSpacing = 10;
		glMemo.numColumns = 5;
		
		Composite memoMainAream = new Composite(tableBottomArea);
		memoMainAream.setLayout(glMemo);
		GridData gdMemoMain = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoMain.heightHint = 60;
		memoMainAream.setLayoutData(gdMemoMain);
		
		Composite tableButtonArea = new Composite(memoMainAream);
		tableButtonArea.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		GridLayout glTableButtonArea = new GridLayout(5);
		glTableButtonArea.horizontalSpacing = 0;
		tableButtonArea.setLayout(glTableButtonArea);
		tableButtonArea.setID(SimpleSheetPageProcessor.ID_Area_TableButton);
		renderTableButtonArea(tableButtonArea);
		
		if(tableButtonArea.getChildren() == null || tableButtonArea.getChildren().length < 1) {
			tableButtonArea.dispose();
		} else {
			Control[] children = tableButtonArea.getChildren();
			glTableButtonArea.numColumns = children.length;
			GridData gdButton = new GridData();
			gdButton.heightHint = 28;
			gdButton.widthHint = 80;
			for(Control control : children) {
				control.setLayoutData(gdButton);
			}
		}
		
		Label titleLabel = new Label(memoMainAream);
		titleLabel.setText("备注：");
		GridData gdMemo = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END);
		gdMemo.verticalIndent = 3;
		titleLabel.setLayoutData(gdMemo);
		
		memoText = new Text(memoMainAream, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		GridData gdMemoText = new GridData(GridData.FILL_BOTH);
		memoText.setLayoutData(gdMemoText);
		memoText.setID(SimpleSheetPageProcessor.ID_Text_Memo);
		
		Composite dataInfoArea = new Composite(memoMainAream);
		dataInfoArea.setLayout(new GridLayout(2));
		GridData gdDataInfo = new GridData(GridData.FILL_VERTICAL);
		dataInfoArea.setLayoutData(gdDataInfo);
		
		fillDataInfoControl(dataInfoArea);
		
		if(dataInfoArea.getChildren() == null || dataInfoArea.getChildren().length < 1) {
			dataInfoArea.dispose();
			glMemo.numColumns--;
		}
		
		//
		new Label(tableBottomArea).setID(SimpleSheetPageProcessor.ID_Label_StopCause);
		
		if(isShowFind()) {
			Composite findTextArea = new Composite(super.contentArea);
			GridData gdFindTextArea = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER);
			gdFindTextArea.heightHint = 25;
			gdFindTextArea.widthHint = 320;
			gdFindTextArea.verticalIndent = 10;
			findTextArea.setLayoutData(gdFindTextArea);
//			findTextArea.setBorder(CBorder.BORDER_NORMAL);
			new StableSearch(findSearchArea, findTextArea, super.contentArea);
		}
	}
	
	@Override
	public final void afterFooterRender() {
		super.afterFooterRender();
		
		Label label = new Label(super.footerLeftArea);
		GridData gd = new GridData();
		gd.verticalIndent = 4;
		label.setID(SimpleSheetPageProcessor.ID_Label_Label_ExtraInfo);
		label.setLayoutData(gd);
		
		
		renderSheetButtonArea(super.footerRightArea);
		
		if(isShowPrint()) {
			// 打印
			Button printButton = new Button(super.footerRightArea, JWT.APPEARANCE3);
			printButton.setText(" 打 印 ");
			printButton.setID(SimpleSheetPageProcessor.ID_Button_Print);
		}
		Button exportButton = new Button(super.footerRightArea, JWT.APPEARANCE3);
		exportButton.setText("  导 出   ");
		exportButton.setID(SimpleSheetPageProcessor.ID_Button_Export_Form);
	}
	
	/**
	 * 加载表格下方的按钮
	 * @param tableButtonArea
	 */
	protected abstract void renderTableButtonArea(Composite tableButtonArea);
	/**
	 * 加载表单右下角按钮
	 * @param sheetButtonArea
	 */
	protected abstract void renderSheetButtonArea(Composite sheetButtonArea);
	/**
	 * 获取显示表单基本信息的行数
	 * @return
	 */
	protected abstract int getBaseInfoAreaRowCount();
	/**
	 * 加载显示基本信息的控件
	 * @param baseInfoArea
	 * @param row
	 * @param column
	 */
	protected abstract void fillBaseInfoCellControl(Composite baseInfoArea,
			int row, int column);
	/**
	 * 加载数据信息的控件
	 * @param dataInfoArea
	 */
	protected abstract void fillDataInfoControl(Composite dataInfoArea);
	
	
	@Deprecated
	protected abstract void fillStopCauseControl(Composite stopCauseArea);
	
	
	final protected boolean isShowPrint() {
		return true;
	}
	
	@Deprecated
	protected boolean isShowFind() {
		return false;
	}

	@Override
	public STableStyle getTableStyle() {
		SEditTableStyle style = new SEditTableStyle();
		style.setPageAble(false);
		return style;
	}
	
	
}
//class MemoComposite extends Composite {
//	private Text memoText;
//
//	public MemoComposite(Composite parent) {
//		super(parent);
//		GridLayout gl = new GridLayout(2);
//		gl.marginLeft = 15;
//		this.setLayout(gl);
//
//		Label titleLabel = new Label(this);
//		titleLabel.setText("备注：");
//		titleLabel.setLayoutData(new GridData(GridData.GRAB_VERTICAL
//				| GridData.VERTICAL_ALIGN_BEGINNING
//				| GridData.HORIZONTAL_ALIGN_END));
//
//		memoText = new Text(this);
//		GridData gdMemo = new GridData(GridData.FILL_BOTH);
//		memoText.setLayoutData(gdMemo);
//
//		this.layout();
//	}
//
//	public String getText() {
//		return memoText.getText();
//	}
//
//	public void setText(String text) {
//		memoText.setText(text);
//		this.layout();
//	}
//	
//}