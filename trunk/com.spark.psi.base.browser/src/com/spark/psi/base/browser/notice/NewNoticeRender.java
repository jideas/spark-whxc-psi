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
 * <p>�½���༭���������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-20
 */
public class NewNoticeRender extends BaseFormPageRender {
	
	/** ��߿�� */
	private final int leftWidth = 60;

	/**
	 * �Զ���Form����
	 */
	protected boolean customizeFormLayout() {
		return true;
	}

	/**
	 * �Զ��尴ť����
	 */
	@Override
	protected boolean customizeButtonLayout() {
		return true;
	}
	
	/**
	 * �������������
	 */
	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(new GridLayout(6));
		
		//��һ������
		GridData firstGridData = new GridData();
		firstGridData.widthHint = leftWidth;
		firstGridData.horizontalAlignment = JWT.RIGHT;
		
		
		/**
		 * ��һ��  �������
		 */
		Label label = new Label(formArea);
		label.setText("������⣺");
		label.setLayoutData(firstGridData);
		//�ı��� ռ4��
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
		isTopCheck.setText("�ö���ʾ");
		
		/**
		 * �ڶ��� ������Χ
		 */
		label = new Label(formArea);
		label.setText("������Χ��");
		label.setLayoutData(firstGridData);
		//�ı��� ռ4��
		Text deptNameStrText = new Text(formArea, JWT.APPEARANCE3 | JWT.BUTTON);
		deptNameStrText.setEditable(false);
		deptNameStrText.setID(NewNoticeProcessor.ID_Text_DeptNameStr);
		GridData deptNameStrTextGridData = new GridData(GridData.FILL_HORIZONTAL);
		deptNameStrTextGridData.horizontalSpan = 4;
		deptNameStrText.setLayoutData(deptNameStrTextGridData);
		new Label(formArea);
		
		/**
		 * ������ �������� �ͳ�������
		 */
		label = new Label(formArea);
		label.setText("�������ڣ�");
		label.setLayoutData(firstGridData);
		new DatePicker(formArea, JWT.APPEARANCE3).setID(NewNoticeProcessor.ID_Date_Publishing);
		new Label(formArea).setText("�������ڣ�");
		new DatePicker(formArea, JWT.APPEARANCE3).setID(NewNoticeProcessor.ID_Date_Cancel);
		new Label(formArea).setText("�ڷ����ͳ����գ�ϵͳ���Զ������������档");
		new Label(formArea);

		/**
		 * ������ ��������
		 */
		label = new Label(formArea);
		GridData contentLabelGridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
		contentLabelGridData.widthHint = leftWidth;
		label.setLayoutData(contentLabelGridData);
		label.setText("�������ݣ�");
		//������
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
	 * ������ť
	 */
	@Override
	protected void renderButton(Composite buttonArea) {
		buttonArea.setLayout(new GridLayout(3));
		//��߿հ׿��
		Label label = new Label(buttonArea);
		GridData firstGridData = new GridData();
		firstGridData.widthHint = leftWidth;
		firstGridData.horizontalAlignment = JWT.RIGHT;
		label.setLayoutData(firstGridData);
		//��������Ϣ������ʱ��
		label = new Label(buttonArea);
		label.setText(getCreateInfo());
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalIndent = 10;
		label.setLayoutData(gridData);
		//���水ť
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		GridData saveButtonGridData = new GridData();
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButtonGridData.verticalIndent = 5;
		saveButton.setText("����");
		saveButton.setID(NewNoticeProcessor.ID_Button_Save);
		saveButton.setLayoutData(saveButtonGridData);
	}
	
	/**
	 * ��ô�������Ϣ
	 */
	private String getCreateInfo(){
		if(this.getArgument() == null){ //����
			return "������" + getContext().get(LoginInfo.class).getEmployeeInfo().getName() + "(" +DateUtil.dateFromat(new Date().getTime())+ ")";
		}else{ //�༭
			NoticeInfo noticeInfo = (NoticeInfo)this.getArgument();
			if(noticeInfo.getCreateTime() == 0){
				return "������" + noticeInfo.getCreatePerson();
			}else{
				return "������" + noticeInfo.getCreatePerson() + "(" +DateUtil.dateFromat(noticeInfo.getCreateTime())+ ")";
			}
		}
	}
	

}
