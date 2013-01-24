package com.spark.psi.base.browser.store;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

public class AuthListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" �� �� ");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.exportDatas("Ȩ���б�" + ".xls", "application/vnd.ms-excel", 102400000, "Ȩ���б�");
			}
		});
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[2];
		columns[0] = new STableColumn("role", 200, JWT.CENTER, "��ɫ");
		columns[1] = new STableColumn("auth", 200, JWT.CENTER, "Ȩ��");
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		AuthItem item = (AuthItem)element;
		switch(columnIndex) {
		case 0:
			return item.getRoleName();
		case 1:
			return item.getAuthName();
		}
		return null;
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
