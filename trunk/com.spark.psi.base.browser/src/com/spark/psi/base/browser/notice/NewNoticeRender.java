package com.spark.psi.base.browser.notice;


import java.util.Date;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.text.HtmlEditor;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.notice.entity.NoticeInfo;

/**
 * 
 * <p>新建或编辑公告界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-20
 */
public class NewNoticeRender extends BaseFormPageRender {
	
	/** 左边宽度 */
	private final int leftWidth = 60;

	/**
	 * 自定义Form布局
	 */
	protected boolean customizeFormLayout() {
		return true;
	}

	/**
	 * 自定义按钮布局
	 */
	@Override
	protected boolean customizeButtonLayout() {
		return true;
	}
	
	/**
	 * 表单区域添加容器
	 */
	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(new GridLayout(6));
		
		//第一列数据
		GridData firstGridData = new GridData();
		firstGridData.widthHint = leftWidth;
		firstGridData.horizontalAlignment = JWT.RIGHT;
		
		
		/**
		 * 第一行  公告标题
		 */
		Label label = new Label(formArea);
		label.setText("公告标题：");
		label.setLayoutData(firstGridData);
		//文本框 占4列
		Text noticeTitleText = new Text(formArea, JWT.APPEARANCE3);
		noticeTitleText.setID(NewNoticeProcessor.ID_Text_NoticeTile);
		noticeTitleText.setMaximumLength(50);
		GridData noticeTitleTextgGridData = new GridData(GridData.FILL_HORIZONTAL);
		noticeTitleTextgGridData.horizontalSpan = 4;
		noticeTitleText.setLayoutData(noticeTitleTextgGridData);
		Button isTopCheck = new Button(formArea, JWT.CHECK);
		GridData isTopGridData = new GridData();
		isTopGridData.widthHint = 120;
		isTopCheck.setID(NewNoticeProcessor.ID_CheckBox_IsTop);
		isTopCheck.setText("置顶显示");
		
		/**
		 * 第二行 发布范围
		 */
		label = new Label(formArea);
		label.setText("发布范围：");
		label.setLayoutData(firstGridData);
		//文本框 占4列
		Text deptNameStrText = new Text(formArea, JWT.APPEARANCE3 | JWT.BUTTON);
		deptNameStrText.setEditable(false);
		deptNameStrText.setID(NewNoticeProcessor.ID_Text_DeptNameStr);
		GridData deptNameStrTextGridData = new GridData(GridData.FILL_HORIZONTAL);
		deptNameStrTextGridData.horizontalSpan = 4;
		deptNameStrText.setLayoutData(deptNameStrTextGridData);
		new Label(formArea);
		
		/**
		 * 第三行 发布日期 和撤消日期
		 */
		label = new Label(formArea);
		label.setText("发布日期：");
		label.setLayoutData(firstGridData);
		new DatePicker(formArea, JWT.APPEARANCE3).setID(NewNoticeProcessor.ID_Date_Publishing);
		new Label(formArea).setText("撤消日期：");
		new DatePicker(formArea, JWT.APPEARANCE3).setID(NewNoticeProcessor.ID_Date_Cancel);
		new Label(formArea).setText("在发布和撤消日，系统将自动发布或撤消公告。");
		new Label(formArea);

		/**
		 * 第四行 公告内容
		 */
		label = new Label(formArea);
		GridData contentLabelGridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
		contentLabelGridData.widthHint = leftWidth;
		label.setLayoutData(contentLabelGridData);
		label.setText("公告内容：");
		//子容器
		Composite contentContainer = new Composite(formArea);
		GridData contentContainerDataGrid = new GridData(GridData.FILL_BOTH);
		contentContainerDataGrid.horizontalSpan = 5;
		contentContainer.setLayoutData(contentContainerDataGrid);
		contentContainer.setLayout(new GridLayout());
		Composite separtaor = new Composite(contentContainer);
		GridData separatorGridData = new GridData(GridData.FILL_HORIZONTAL);
		separatorGridData.heightHint = 2;
		separtaor.setLayoutData(separatorGridData);
		separtaor.setBackground(Color.COLOR_BLACK);
		HtmlEditor contentText = new HtmlEditor(contentContainer);
		contentText.setID(NewNoticeProcessor.ID_Text_Content);
		GridData contentGridData = new GridData(GridData.FILL_BOTH);
		contentText.setLayoutData(contentGridData);
		
	}
	
	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea) {
		buttonArea.setLayout(new GridLayout(3));
		//左边空白宽度
		Label label = new Label(buttonArea);
		GridData firstGridData = new GridData();
		firstGridData.widthHint = leftWidth;
		firstGridData.horizontalAlignment = JWT.RIGHT;
		label.setLayoutData(firstGridData);
		//创建人信息及创建时间
		label = new Label(buttonArea);
		label.setText(getCreateInfo());
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalIndent = 10;
		label.setLayoutData(gridData);
		//保存按钮
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		GridData saveButtonGridData = new GridData();
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButtonGridData.verticalIndent = 5;
		saveButton.setText("保存");
		saveButton.setID(NewNoticeProcessor.ID_Button_Save);
		saveButton.setLayoutData(saveButtonGridData);
	}
	
	/**
	 * 获得创建人信息
	 */
	private String getCreateInfo(){
		if(this.getArgument() == null){ //新增
			return "创建：" + getContext().get(LoginInfo.class).getEmployeeInfo().getName() + "(" +DateUtil.dateFromat(new Date().getTime())+ ")";
		}else{ //编辑
			NoticeInfo noticeInfo = (NoticeInfo)this.getArgument();
			if(noticeInfo.getCreateTime() == 0){
				return "创建：" + noticeInfo.getCreatePerson();
			}else{
				return "创建：" + noticeInfo.getCreatePerson() + "(" +DateUtil.dateFromat(noticeInfo.getCreateTime())+ ")";
			}
		}
	}
	

}
