package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.List;

/**
 * 
 * <p>������������ͼ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-13
 */
public abstract class BatchSaveRender extends WizardBaseStepRender{

	/**
	 * �Ƿ��в�����ť
	 */
	protected boolean hasOperateButton(){
		return false;
	}

	/**
	 * ������Ϣ��
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		GridLayout layout = new GridLayout();
		layout.marginTop = 10;
		layout.verticalSpacing = 10;
		infoArea.setLayout(layout);
		//�½�ģ����
		Composite downLoadArea = new Composite(infoArea);
		downLoadArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		downLoadArea.setLayout(new GridLayout(3));
		//
		new Label(downLoadArea).setText("����������" + getTitle() + "��Excelģ�壺");
		Label downLoadLabel = new Label(downLoadArea);
		downLoadLabel.setText("�������");
		downLoadLabel.setID(BatchSaveProcessor.ID_Lable_Down);
		downLoadLabel.setCursor(Cursor.HAND);
		downLoadLabel.setForeground(getWizardStepColor());
		downLoadLabel.setHoverForeground(getWizardStepHoverColor());
		//�ϴ��ļ���
		Composite upLoadArea = new Composite(infoArea);
		upLoadArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		upLoadArea.setLayout(new GridLayout(3));
		//
		new Label(upLoadArea).setText("�ϴ�Excel:");
		FileChooser fileChooser = new FileChooser(upLoadArea);
		fileChooser.setID(BatchSaveProcessor.ID_File_Upload);
		GridData fileChooserGridData = new GridData();
		fileChooserGridData.widthHint = 200;
		fileChooser.setLayoutData(fileChooserGridData);
		//
		Button saveButton = new Button(upLoadArea);
		saveButton.setText("����");
		saveButton.setID(BatchSaveProcessor.ID_Button_Save);
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 60;
		buttonGridData.heightHint = 23;
		saveButton.setLayoutData(buttonGridData);
		//������Ϣ
		Composite errorArea = new Composite(infoArea);
		errorArea.setLayoutData(GridData.INS_FILL_BOTH);
		addErrorInfoArea(errorArea);
	}

	/**
	 * ������Ϣ����
	 */
	private void addErrorInfoArea(Composite errorArea){
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		errorArea.setLayout(layout);
		//
		Label errorTitle = new Label(errorArea);
		errorTitle.setText("ִ�������");
		errorTitle.setFont(new Font("����", 9, JWT.FONT_STYLE_BOLD));
		errorTitle.setForeground(new Color(0x008015));
		//
		Composite errorInfoArea = new Composite(errorArea);
		errorInfoArea.setLayoutData(GridData.INS_FILL_BOTH);
		errorInfoArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));
		errorInfoArea.setLayout(new FillLayout());
		//
		List list = new List(errorInfoArea);
		list.setBorder(new CBorder(0, 0, 0));
		list.setID(BatchSaveProcessor.ID_List_Err);
	}

}
