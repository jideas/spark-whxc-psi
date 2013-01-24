package com.spark.psi.inventory.browser.init;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageRender;

public class InitImportFileSelectRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setID(InitImportFileSelectProccessor.ID_Button_Confirm);
		button.setText(" 确认 ");
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setID(InitImportFileSelectProccessor.ID_Button_Cancel);
		button.setText(" 取消 ");
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 2;
		formArea.setLayout(glForm);
		
		Label label = new Label(formArea);
		label.setText("文件路径：");
		
		FileChooser fc = new FileChooser(formArea,JWT.SINGLE);
		fc.setID(InitImportFileSelectProccessor.ID_FileChooser);
		
//		Text text = new Text(formArea, JWT.APPEARANCE3);
//		text.setID(InitImportFileSelectProccessor.ID_Text_FilePath);
//		
//		Button button = new Button(formArea, JWT.APPEARANCE3);
//		button.setID(InitImportFileSelectProccessor.ID_Button_Select);
//		button.setText("选择文件");
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

}
