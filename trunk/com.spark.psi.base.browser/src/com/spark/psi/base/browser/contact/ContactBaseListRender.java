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
 * <p>通讯录基类列表视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-9
 */

public abstract class ContactBaseListRender extends PSIListPageRender{
	
	public static final int nameColumnWidth = 100;
	public static final int unitColumnWidth = 150;
	public static final int positionColumnWidth = 100;
	public static final int phoneNumberColumnWidth = 100;
	public static final int cellPhoneNumberColumnWidth = 100;
	public static final int emailColumnWidth = 120;

	/**内容区*/
	protected Composite newContentArea;

	/**拼音搜索列表区*/
	protected Composite phoneticArea;

	/**
	 * 创建内容区之前
	 */
	@Override
	protected void beforeHeaderRender(){

		beforePhoneticArea();

		GridLayout gridLayout = new GridLayout(2);
		gridLayout.horizontalSpacing = 0;
		super.contentArea.setLayout(gridLayout);
		//拼音搜索列表区
		phoneticArea = new Composite(super.contentArea);
		GridData phoneticGridData = new GridData(GridData.FILL_VERTICAL);
		phoneticGridData.widthHint = 60;
		phoneticArea.setLayoutData(phoneticGridData);
		afterPhoneticArea();
		//内容区
		newContentArea = new Composite(super.contentArea);
		newContentArea.setLayout(new GridLayout());
		newContentArea.setLayoutData(GridData.INS_FILL_BOTH);
		//重置
		super.contentArea = newContentArea;
	}

	@Override
	protected void afterFooterRender(){
		super.afterFooterRender();
		//搜索框
		new SSearchText2(headerRightArea).setID(ContactBaseListProcessor.ID_TEXT_SEARCHTEXT);
	}

	/**
	 * 拼音内容区之前
	 */
	protected void beforePhoneticArea(){

	}

	/**
	 * 拼音内容区之后
	 */
	protected void afterPhoneticArea(){
		//默认
		phoneticArea.setLayout(new GridLayout());
		//头部
		Composite phoneticHeadArea = new Composite(phoneticArea);
		GridData phoneticHeadAreaData = new GridData();
		phoneticHeadAreaData.heightHint = 32;
		phoneticHeadArea.setLayoutData(phoneticHeadAreaData);
		//
		ContactPersonPhoneticNavigatorBar phoneticNavigatorBar = new ContactPersonPhoneticNavigatorBar(phoneticArea);
		phoneticNavigatorBar.setID(ContactBaseListProcessor.ID_PHONETICE_NAVIGATOR_BAR);
		phoneticNavigatorBar.setLayoutData(GridData.INS_FILL_BOTH);
		this.controls.put(ContactBaseListProcessor.ID_PHONETICE_NAVIGATOR_BAR, phoneticNavigatorBar);
		//底部
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
