package com.spark.psi.base.browser.notice;

import java.util.ArrayList;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.notice.entity.NoticeItem;

/**
 * 
 * <p>未发布公告列表视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */
public class PublishingNoticeListRender extends PSIListPageRender{
	
	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		new Label(headerLeftArea).setText("公告数量：");
		new Label(headerLeftArea).setText(" ");
		new Label(headerLeftArea).setID(PublishingNoticeListProcessor.ID_LABEL_COUNT);
		new Label(headerLeftArea).setText(" ");
		new Label(headerLeftArea).setText("条");
		new SSearchText2(headerRightArea).setID(PublishingNoticeListProcessor.ID_TEXT_SEARCHTEXT);
		//新建公告
		Button addButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		addButton.setID(PublishingNoticeListProcessor.ID_BUTTON_ADDNOTICE);
		addButton.setText("新建公告");
		GridData addButtonGridData = new GridData();
		addButtonGridData.widthHint = 80;
		addButtonGridData.heightHint = 30;
		addButton.setLayoutData(addButtonGridData);
	}

	/**
	 * 获取列
	 */
	public STableColumn[] getColumns(){
		ArrayList<STableColumn> columnList = new ArrayList<STableColumn>();
		//公告标题
		STableColumn titleColumn = new STableColumn("noticeTitle", 200, JWT.LEFT, "公告标题");
		titleColumn.setGrab(true);
		titleColumn.setSortable(true);
		columnList.add(titleColumn);
		//发布日期
		STableColumn publishTimeColumn = new STableColumn("publishTime", 150, JWT.CENTER, "发布日期");
		publishTimeColumn.setSortable(true);
		columnList.add(publishTimeColumn);
		//撤消日期
		STableColumn cancelTimeColumn = new STableColumn("cancelTime", 150, JWT.CENTER, "撤消日期");
		cancelTimeColumn.setSortable(true);
		columnList.add(cancelTimeColumn);
		//只有总经理显示该列
		if(getContext().find(LoginInfo.class).hasAuth(Auth.Boss)){
			STableColumn createPersonColumn = new STableColumn("createPerson", 150, JWT.CENTER, "创建人");
			createPersonColumn.setSortable(true);
			columnList.add(createPersonColumn);
		}
		//发布范围
		STableColumn deptNameStrColumn = new STableColumn("deptNameStr", 300, JWT.CENTER, "发布范围");
		deptNameStrColumn.setSortable(true);
		deptNameStrColumn.setGrab(true);
		columnList.add(deptNameStrColumn);
		//置顶
		STableColumn isTopColumn = new STableColumn("isTop", 50, JWT.CENTER, "置顶");
		isTopColumn.setSortable(true);
		columnList.add(isTopColumn);
		return columnList.toArray(new STableColumn[columnList.size()]);
	}
	
	/**
	 * 获取表格风格
	 */
	public STableStyle getTableStyle(){
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	/**
	 * 单元格取值
	 */
	public String getText(Object element, int columnIndex){
		STableColumn[] columns = getColumns();
		String text = "";
		if(columnIndex >= 0 && columnIndex < columns.length){
			String fieldName = columns[columnIndex].getName();
			NoticeItem noticeItem = (NoticeItem)element;
			if(fieldName.equals("noticeTitle")){ //公告标题
				text = StableUtil.toLink(PublishingNoticeListProcessor.ID_ACTION_EDIT, "", noticeItem.getNoticeTitle());
			}else if(fieldName.equals("publishTime")){ //发布日期
				text = DateUtil.dateFromat(noticeItem.getPublishTime());
			}else if(fieldName.equals("cancelTime")){ //撤消日期
				text = DateUtil.dateFromat(noticeItem.getCancelTime());
			}else if(fieldName.equals("createPerson")){ //创建人
				text = noticeItem.getCreatePerson();
			}else if(fieldName.equals("deptNameStr")){ //发布范围
				text = noticeItem.getDeptNameStr();
			}else if(fieldName.equals("isTop")){ //是否置顶
				text = noticeItem.getIsTop() ? "置顶" : "正常";
			}
		}
		return text;
	}
}
