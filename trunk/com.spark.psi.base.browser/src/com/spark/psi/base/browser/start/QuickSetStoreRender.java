package com.spark.psi.base.browser.start;

import java.util.ArrayList;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * <p>�������òֿ������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-27
 */

public class QuickSetStoreRender extends PSIListPageRender{

	/**
	 * ���صײ�����
	 */
	@Override
	protected boolean hideFooterArea(){
		return true;
	}
	
	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		//����
		Label titleLabel = new Label(headerLeftArea);
		titleLabel.setText("����ֿ�");
		titleLabel.setForeground(new Color(0x008015));
		titleLabel.setFont(new Font("����", 9, JWT.FONT_STYLE_BOLD));
		new Label(headerLeftArea).setText(" ");
		//�����ֿ�
		Button addButton = new Button(headerLeftArea, JWT.APPEARANCE2);
		addButton.setID(QuickSetStoreProcessor.ID_Button_Add);
		addButton.setText("�����ֿ�");
		GridData addButtonGridData = new GridData();
		addButtonGridData.widthHint = 80;
		addButtonGridData.heightHint = 24;
		addButton.setLayoutData(addButtonGridData);
	}

	/**
	 * ��ȡ��
	 */
	@Override
	public STableColumn[] getColumns(){
		ArrayList<STableColumn> columnList = new ArrayList<STableColumn>();
		//�ֿ�����
		STableColumn nameColumn = new STableColumn(QuickSetStoreProcessor.Columns.Name.name(), 100, JWT.CENTER, "�ֿ�����");
		columnList.add(nameColumn);
		//���Ա
		STableColumn keeperColumn =
		        new STableColumn(QuickSetStoreProcessor.Columns.KeeperNames.name(), 100, JWT.CENTER, "���Ա");
		keeperColumn.setGrab(true);
		columnList.add(keeperColumn);
		//����
		return columnList.toArray(new STableColumn[columnList.size()]);
	}

	/**
	 * ��Ԫ��ȡֵ
	 */
	@Override
	public String getText(Object element, int columnIndex){
		TempStorageInfo item = (TempStorageInfo)element;
		switch(columnIndex){
	        case 0:
		        return StableUtil.toLink(QuickSetStoreProcessor.ID_ACTION_EDIT, "", item.getName());
		    case 1:
		    	return item.getKeeperNames();
	        default:
		        return "";
        }
	}

	/**
	 * ��ȡ�����
	 */
	public STableStyle getTableStyle(){
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

}
