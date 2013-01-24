package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

public class InventoryCountTypeSelectRender extends BaseFormPageRender {

	private final static GridLayout gl;
	private final static GridData gdLabel;
	private final static GridData gd2;

	static {
		gl = new GridLayout();
		gl.numColumns = 3;
		gl.verticalSpacing = 8;

		gdLabel = new GridData();
		gdLabel.widthHint = 70;

		gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.horizontalSpan = 2;
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(gl);
		Label label = new Label(formArea);
		label.setText("�̵�ֿ⣺");
		label.setLayoutData(gdLabel);

		LWComboList comboList = new LWComboList(formArea, JWT.APPEARANCE3);
		comboList.setID(InventoryCountTypeSelectProcessor.ID_List_Store);
		comboList.setLayoutData(gd2);
		comboList.setHint("��ѡ���̵�ֿ�");

		label = new Label(formArea);
		label.setText("�� �� �ˣ�");
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(InventoryCountTypeSelectProcessor.ID_Text_Counter);
		text.setLayoutData(gd2);

		label = new Label(formArea);
		label.setText("�̵����");
		Button button = new Button(formArea, JWT.RADIO);
		button.setText("���Ͽ��");
		button.setID(InventoryCountTypeSelectProcessor.ID_Radio_Goods);
		button = new Button(formArea, JWT.RADIO);
		button.setText("�������");
		button.setID(InventoryCountTypeSelectProcessor.ID_Radio_Kit);

		label = new Label(formArea);
		label.setText("�������ݣ�");
		FileChooser fc = new FileChooser(formArea,JWT.SINGLE);
		fc.setID("FileChooser");
		fc.setLayoutData(gd2); 
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" ȷ��ѡ�� ");
		button.setID(InventoryCountTypeSelectProcessor.ID_Button_Confirm);
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" ����ѡ�� ");
		button.setID(InventoryCountTypeSelectProcessor.ID_Button_Cancel);
	}

}
