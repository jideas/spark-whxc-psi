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
 * <p>���������û�������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-27
 */

public class QuickSetUserRender extends PSIListPageRender{

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
		titleLabel.setText("�����û�");
		titleLabel.setFont(new Font("����", 9, JWT.FONT_STYLE_BOLD));
		titleLabel.setForeground(new Color(0x008015));
		new Label(headerLeftArea).setText(" ");
		//�����û�
		Button addButton = new Button(headerLeftArea, JWT.APPEARANCE2);
		addButton.setID(QuickSetUserProcessor.ID_Button_Add);
		addButton.setText("�����û�");
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
		//�û�����
		STableColumn nameColumn = new STableColumn(QuickSetUserProcessor.Columns.Name.name(), 100, JWT.CENTER, "����");
		columnList.add(nameColumn);
		//�ֻ���
		STableColumn mobileColumn =
		        new STableColumn(QuickSetUserProcessor.Columns.MobileNo.name(), 100, JWT.CENTER, "�ֻ�");
		columnList.add(mobileColumn);
		//����
		STableColumn deptColumn =
		        new STableColumn(QuickSetUserProcessor.Columns.DepartmentName.name(), 100, JWT.CENTER, "����");
		deptColumn.setGrab(true);
		columnList.add(deptColumn);
		//Ȩ��
		STableColumn rootColumn =
		        new STableColumn(QuickSetUserProcessor.Columns.RolesName.name(), 100, JWT.CENTER, "Ȩ��");
		rootColumn.setGrab(true);
		columnList.add(rootColumn);
		return columnList.toArray(new STableColumn[columnList.size()]);
	}

	/**
	 * ��Ԫ��ȡֵ
	 */
	@Override
	public String getText(Object element, int columnIndex){
		TempUserInfo item = (TempUserInfo)element;
		switch(columnIndex){
			case 0:
				if(!item.isCreate()){
					return item.getName();
				}
				else{
					return StableUtil.toLink(QuickSetUserProcessor.ID_ACTION_EDIT, "", item.getName());
				}
			case 1:
				return item.getMobile();
			case 2:
				return item.getDepartmentName();
			case 3:
				return item.getRolesName();
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
