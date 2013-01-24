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
 * <p>快速设置仓库界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-27
 */

public class QuickSetStoreRender extends PSIListPageRender{

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
		titleLabel.setText("管理仓库");
		titleLabel.setForeground(new Color(0x008015));
		titleLabel.setFont(new Font("宋体", 9, JWT.FONT_STYLE_BOLD));
		new Label(headerLeftArea).setText(" ");
		//新增仓库
		Button addButton = new Button(headerLeftArea, JWT.APPEARANCE2);
		addButton.setID(QuickSetStoreProcessor.ID_Button_Add);
		addButton.setText("新增仓库");
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
		//仓库名称
		STableColumn nameColumn = new STableColumn(QuickSetStoreProcessor.Columns.Name.name(), 100, JWT.CENTER, "仓库名称");
		columnList.add(nameColumn);
		//库管员
		STableColumn keeperColumn =
		        new STableColumn(QuickSetStoreProcessor.Columns.KeeperNames.name(), 100, JWT.CENTER, "库管员");
		keeperColumn.setGrab(true);
		columnList.add(keeperColumn);
		//部门
		return columnList.toArray(new STableColumn[columnList.size()]);
	}

	/**
	 * 单元格取值
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
	 * 获取表格风格
	 */
	public STableStyle getTableStyle(){
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

}
