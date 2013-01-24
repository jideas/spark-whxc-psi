package com.spark.psi.base.browser.notice;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.notice.entity.NoticeItem;

/**
 * 
 * <p>历史公告列表视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-20
 */
public class HistoryNoticeListRender extends PSIListPageRender{
	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		new LWComboList(headerLeftArea).setID(HistoryNoticeListProcessor.ID_COMBO_TIME);
		new Label(headerLeftArea).setText("  ");
		new Label(headerLeftArea).setText("公告数量：");
		new Label(headerLeftArea).setText(" ");
		new Label(headerLeftArea).setID(HistoryNoticeListProcessor.ID_LABEL_COUNT);
		new Label(headerLeftArea).setText(" ");
		new Label(headerLeftArea).setText("条");
		new SSearchText2(headerRightArea).setID(HistoryNoticeListProcessor.ID_TEXT_SEARCHTEXT);
	}

	/**
	 * 获取列
	 */
	public STableColumn[] getColumns(){
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn("noticeTitle", 200, JWT.LEFT, "公告标题");
		columns[0].setGrab(true);
		columns[0].setSortable(true);
		columns[1] = new STableColumn("publishTime", 150, JWT.CENTER, "发布日期");
		columns[1].setSortable(true);
		columns[2] = new STableColumn("createPerson", 150, JWT.CENTER, "创建人");
		columns[2].setSortable(true);
		return columns;
	}

	/**
	 * 获取表格风格
	 */
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	/**
	 * 单元格取值
	 */
	public String getText(Object element, int columnIndex){
		NoticeItem noticeItem = (NoticeItem)element;
		String text = "";
		switch(columnIndex){
	        case 0:
	        	text = StableUtil.toLink(HistoryNoticeListProcessor.ID_ACTION_VIEW, "", noticeItem.getNoticeTitle());
		        break;
	        case 1:
	        	text = DateUtil.dateFromat(noticeItem.getPublishTime());
	        	break;
	        case 2:
	        	text = noticeItem.getCreatePerson();
	        	break;
        }
		return text;
	}
}
