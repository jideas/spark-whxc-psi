package com.spark.psi.base.browser.notice;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 * <p>公告查看界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-23
 */
public class NoticeViewRender extends BaseFormPageRender{

	/**
	 * 自定义Form布局
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * 自定义按钮布局
	 */
	@Override
	protected boolean customizeButtonLayout(){
		return true;
	}

	/**
	 * 表单区域添加容器
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		formArea.setBackground(new Color(0xD6E8F4));
		formArea.setLayout(new GridLayout(3));
		//公告标题 
		Label noticeTitleLabel = new Label(formArea);
		noticeTitleLabel.setID(NoticeViewProcessor.ID_Label_NoticeTile);
		GridData titleGridData = new GridData(GridData.FILL_HORIZONTAL);
		titleGridData.horizontalAlignment = JWT.CENTER;
		titleGridData.horizontalSpan = 3;
		noticeTitleLabel.setLayoutData(titleGridData);
		Font fontTitle = new Font();
		fontTitle.setSize(12);
		fontTitle.setStyle(JWT.FONT_STYLE_BOLD);
		noticeTitleLabel.setFont(fontTitle);

		//公告内容
		Browser noticeContentBrowser = new Browser(formArea);
		noticeContentBrowser.setBackground(Color.COLOR_CORAL);
		noticeContentBrowser.setForeground(Color.COLOR_CORAL);
		noticeContentBrowser.setID(NoticeViewProcessor.ID_Label_Content);
		GridData contentGridData = new GridData(GridData.FILL_BOTH);
		contentGridData.horizontalSpan = 3;
		noticeContentBrowser.setLayoutData(contentGridData);
		//发布范围与发布日期
		Label deptNameStrLabel = new Label(formArea, JWT.WRAP);
		deptNameStrLabel.setID(NoticeViewProcessor.ID_Label_DeptNameStr);
		deptNameStrLabel.setFont(new Font("宋体", 9, JWT.FONT_STYLE_BOLD));
		GridData deptNameStrGridData = new GridData(GridData.FILL_HORIZONTAL);
		deptNameStrGridData.horizontalAlignment = JWT.RIGHT;
		deptNameStrGridData.heightHint = 30;
		deptNameStrLabel.setLayoutData(deptNameStrGridData);
		Label spaceLabel = new Label(formArea);
		GridData spaceGridData = new GridData();
		spaceGridData.widthHint = 20;
		spaceLabel.setLayoutData(spaceGridData);
		Label publishDateLabel = new Label(formArea);
		publishDateLabel.setFont(new Font("宋体", 9, JWT.FONT_STYLE_BOLD));
		publishDateLabel.setID(NoticeViewProcessor.ID_Label_Publishing);
		GridData publishDateGridData = new GridData();
		publishDateGridData.widthHint = 180;
		publishDateGridData.heightHint = 30;
		publishDateLabel.setLayoutData(publishDateGridData);
	}

	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		buttonArea.setLayout(new GridLayout());
		//保存按钮
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setText("撤消公告");
		saveButton.setID(NoticeViewProcessor.ID_Button_Cancel);
		GridData saveButtonGridData = new GridData(GridData.FILL_BOTH);
		saveButtonGridData.verticalIndent = 5;
		saveButtonGridData.horizontalAlignment = JWT.RIGHT;
		saveButtonGridData.verticalAlignment = JWT.BOTTOM;
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButton.setLayoutData(saveButtonGridData);
	}
}
