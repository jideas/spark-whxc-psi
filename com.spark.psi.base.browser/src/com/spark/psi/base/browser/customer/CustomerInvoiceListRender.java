package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * 客户的开票记录列表处理器视图
 */
public class CustomerInvoiceListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Label customerNameLabel = new Label(headerLeftArea);
		customerNameLabel.setID(CustomerInvoiceListProcessor.ID_LABEL_CUTOMERNAME);
	}
	
	
	
	@Override
	protected void beforeTableRender() {
		super.beforeTableRender();
		
		Composite tempCmp = new Composite(contentArea);
		tempCmp.setLayout(new GridLayout(2));
		GridData h_gd = new GridData(GridData.FILL_HORIZONTAL);
		h_gd.heightHint = 24;
		tempCmp.setLayoutData(h_gd);
		
		Composite leftCmp = new Composite(tempCmp);
		Composite rightCmp = new Composite(tempCmp);
		GridData h_gd1 = new GridData(GridData.VERTICAL_ALIGN_END
				| GridData.HORIZONTAL_ALIGN_BEGINNING);
		h_gd1.heightHint = 24;
		
		GridData h_gd2 = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		h_gd2.heightHint = 24;
		
		leftCmp.setLayout(new GridLayout(7));
		leftCmp.setLayoutData(h_gd1);
		
		rightCmp.setLayout(new GridLayout(2));
		rightCmp.setLayoutData(h_gd2);
		
		new ComboList(leftCmp)
				.setID(CustomerInvoiceListProcessor.ID_COMBO_TIME);
		
		new Label(leftCmp).setText("    ");
		
		new Label(leftCmp).setText("开票数量：");
		new Label(leftCmp)
				.setID(CustomerInvoiceListProcessor.ID_LABEL_COUNT); 
		
		new Label(leftCmp).setText("    ");
		
		//
		new Label(leftCmp).setText("开票金额：");
		new Label(leftCmp)
				.setID(CustomerInvoiceListProcessor.ID_LABEL_AMOUNT);
		
		new Text(rightCmp)
				.setID(CustomerInvoiceListProcessor.ID_TEXT_SEARCHTEXT);
		
		new Button(rightCmp)
				.setID(CustomerInvoiceListProcessor.ID_TEXT_SEARCHBUTTON);
		
		leftCmp.layout();
		rightCmp.layout();
		tempCmp.layout();
	}



	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn("", 150, JWT.CENTER, "开票日期");
		columns[1] = new STableColumn("", 150, JWT.CENTER, "客户名称");
		columns[1].setGrab(true);
		columns[2] = new STableColumn("", 150, JWT.CENTER, "发票类型");
		columns[3] = new STableColumn("", 150, JWT.CENTER, "开票金额");
		columns[4] = new STableColumn("", 150, JWT.CENTER, "开票人");
		columns[5] = new STableColumn("", 150, JWT.CENTER, "登记人");
		columns[6] = new STableColumn("", 150, JWT.CENTER, "发票号");
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		return columnIndex + "_" + element.toString();
	}
}
