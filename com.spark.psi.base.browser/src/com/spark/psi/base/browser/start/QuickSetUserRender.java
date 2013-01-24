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
 * <p>快速设置用户界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-27
 */

public class QuickSetUserRender extends PSIListPageRender{

	/**
	 * 隐藏底部区域
	 */
	@Override
	protected boolean hideFooterArea(){
		return true;
	}

	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		//标题
		Label titleLabel = new Label(headerLeftArea);
		titleLabel.setText("管理用户");
		titleLabel.setFont(new Font("宋体", 9, JWT.FONT_STYLE_BOLD));
		titleLabel.setForeground(new Color(0x008015));
		new Label(headerLeftArea).setText(" ");
		//新增用户
		Button addButton = new Button(headerLeftArea, JWT.APPEARANCE2);
		addButton.setID(QuickSetUserProcessor.ID_Button_Add);
		addButton.setText("新增用户");
		GridData addButtonGridData = new GridData();
		addButtonGridData.widthHint = 80;
		addButtonGridData.heightHint = 24;
		addButton.setLayoutData(addButtonGridData);
	}

	/**
	 * 获取列
	 */
	@Override
	public STableColumn[] getColumns(){
		ArrayList<STableColumn> columnList = new ArrayList<STableColumn>();
		//用户姓名
		STableColumn nameColumn = new STableColumn(QuickSetUserProcessor.Columns.Name.name(), 100, JWT.CENTER, "姓名");
		columnList.add(nameColumn);
		//手机号
		STableColumn mobileColumn =
		        new STableColumn(QuickSetUserProcessor.Columns.MobileNo.name(), 100, JWT.CENTER, "手机");
		columnList.add(mobileColumn);
		//部门
		STableColumn deptColumn =
		        new STableColumn(QuickSetUserProcessor.Columns.DepartmentName.name(), 100, JWT.CENTER, "部门");
		deptColumn.setGrab(true);
		columnList.add(deptColumn);
		//权限
		STableColumn rootColumn =
		        new STableColumn(QuickSetUserProcessor.Columns.RolesName.name(), 100, JWT.CENTER, "权限");
		rootColumn.setGrab(true);
		columnList.add(rootColumn);
		return columnList.toArray(new STableColumn[columnList.size()]);
	}

	/**
	 * 单元格取值
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
	 * 获取表格风格
	 */
	public STableStyle getTableStyle(){
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

}
