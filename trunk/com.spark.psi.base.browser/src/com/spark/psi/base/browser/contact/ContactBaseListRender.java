package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.CommonImages;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * <p>ͨѶ¼�����б���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-9
 */

public abstract class ContactBaseListRender extends PSIListPageRender{
	
	public static final int nameColumnWidth = 100;
	public static final int unitColumnWidth = 150;
	public static final int positionColumnWidth = 100;
	public static final int phoneNumberColumnWidth = 100;
	public static final int cellPhoneNumberColumnWidth = 100;
	public static final int emailColumnWidth = 120;

	/**������*/
	protected Composite newContentArea;

	/**ƴ�������б���*/
	protected Composite phoneticArea;

	/**
	 * ����������֮ǰ
	 */
	@Override
	protected void beforeHeaderRender(){

		beforePhoneticArea();

		GridLayout gridLayout = new GridLayout(2);
		gridLayout.horizontalSpacing = 0;
		super.contentArea.setLayout(gridLayout);
		//ƴ�������б���
		phoneticArea = new Composite(super.contentArea);
		GridData phoneticGridData = new GridData(GridData.FILL_VERTICAL);
		phoneticGridData.widthHint = 60;
		phoneticArea.setLayoutData(phoneticGridData);
		afterPhoneticArea();
		//������
		newContentArea = new Composite(super.contentArea);
		newContentArea.setLayout(new GridLayout());
		newContentArea.setLayoutData(GridData.INS_FILL_BOTH);
		//����
		super.contentArea = newContentArea;
	}

	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		//������
		new SSearchText2(headerRightArea).setID(ContactBaseListProcessor.ID_TEXT_SEARCHTEXT);
	}

	/**
	 * ƴ��������֮ǰ
	 */
	protected void beforePhoneticArea(){

	}

	/**
	 * ƴ��������֮��
	 */
	protected void afterPhoneticArea(){
		//Ĭ��
		phoneticArea.setLayout(new GridLayout());
		//ͷ��
		Composite phoneticHeadArea = new Composite(phoneticArea);
		GridData phoneticHeadAreaData = new GridData();
		phoneticHeadAreaData.heightHint = 32;
		phoneticHeadArea.setLayoutData(phoneticHeadAreaData);
		//
		ContactPersonPhoneticNavigatorBar phoneticNavigatorBar = new ContactPersonPhoneticNavigatorBar(phoneticArea);
		phoneticNavigatorBar.setID(ContactBaseListProcessor.ID_PHONETICE_NAVIGATOR_BAR);
		phoneticNavigatorBar.setLayoutData(GridData.INS_FILL_BOTH);
		this.controls.put(ContactBaseListProcessor.ID_PHONETICE_NAVIGATOR_BAR, phoneticNavigatorBar);
		//�ײ�
		Composite phoneticFooterArea = new Composite(phoneticArea);
		GridData phoneticFooterAreaData = new GridData();
		phoneticFooterAreaData.heightHint = 34;
		phoneticFooterArea.setBackimage(CommonImages.getImage("img/page/MTabsarea_bottom.png"));
		phoneticFooterArea.setLayoutData(phoneticFooterAreaData);
	}
	
	public final STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setSelectionMode(SSelectionMode.Multi);
		style.setPageAble(false);
		return style;
	}

}
