package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.spark.common.components.table.SLabelProvider;

/**
 * ����һ��Header��һ��Footer���Լ��м���һ�����Ļ����б�ҳ����ͼ<br>
 * ���������afterFooterRender�����н����Լ���ͼ�ĳ�ʼ�������ؼ�������ָ��������
 */
public abstract class BaseListPageRender extends SimpleListPageRender implements
		SLabelProvider {

	/**
	 * �ϲ��������
	 */
	protected Composite headerLeftArea;

	/**
	 * �ϲ��м�����
	 */
	protected Composite headerCenterArea;

	/**
	 * �ϲ��ұ�����
	 */
	protected Composite headerRightArea;

	/**
	 * �²��������
	 */
	protected Composite footerLeftArea;

	/**
	 * �²��м�����
	 */
	protected Composite footerCenterArea;

	/**
	 * ��ͼ�ұ�����
	 */
	protected Composite footerRightArea;

	/**
	 * �����ǲ�������
	 */
	private final static GridLayout glHeader;
	private final static GridData gdHeader;

	private final static GridLayout glFooter;
	private final static GridData gdFooter;

	private final static GridData h_gd1;
	private final static GridData h_gd2;
	private final static GridData h_gd3;
	private final static GridData f_gd1;
	private final static GridData f_gd2;
	private final static GridData f_gd3;

	private final static GridLayout headerAreaLayout;
	private final static GridLayout footerAreaLayout;
	private final static GridData headerData;
	private final static GridData footerData;

	static {
		glHeader = new GridLayout();
		glHeader.numColumns = 3;
		gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader.heightHint = 32;

		glFooter = new GridLayout();
		glFooter.numColumns = 3;
		gdFooter = new GridData(GridData.FILL_HORIZONTAL);
		gdFooter.heightHint = 29;

		h_gd1 = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.GRAB_HORIZONTAL);
		h_gd1.heightHint = 24;
		h_gd2 = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL);
		h_gd2.heightHint = 24;
		h_gd3 = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		h_gd3.heightHint = 24;

		f_gd1 = new GridData(GridData.VERTICAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.GRAB_HORIZONTAL);
		f_gd1.heightHint = 29;
		f_gd2 = new GridData(GridData.VERTICAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_CENTER
				| GridData.GRAB_HORIZONTAL);
		f_gd2.heightHint = 29;
		f_gd3 = new GridData(GridData.VERTICAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_END
				| GridData.GRAB_HORIZONTAL);
		f_gd3.heightHint = 29;

		//
		headerAreaLayout = new GridLayout();
		headerAreaLayout.numColumns = 15;
		headerAreaLayout.horizontalSpacing = 0;
		headerData = new GridData(GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_VERTICAL);

		footerAreaLayout = new GridLayout();
		footerAreaLayout.numColumns = 10;
		footerAreaLayout.horizontalSpacing = 0;
		footerData = new GridData(GridData.FILL_VERTICAL);
	}

	@Override
	protected void beforeTableRender() {
		//
		beforeHeaderRender();

		//
		Composite headerArea = new Composite(contentArea);
		headerArea.setLayoutData(gdHeader);
		headerArea.setLayout(glHeader);

		//
		headerLeftArea = new Composite(headerArea);
		headerCenterArea = new Composite(headerArea);
		headerRightArea = new Composite(headerArea);
		headerLeftArea.setLayoutData(h_gd1);
		headerCenterArea.setLayoutData(h_gd2);
		headerRightArea.setLayoutData(h_gd3);
		headerLeftArea.setLayout(headerAreaLayout);
		headerCenterArea.setLayout(headerAreaLayout);
		headerRightArea.setLayout(headerAreaLayout);

	}

	@Override
	protected void afterTableRender() {

	}

	protected void beforeHeaderRender() {

	}

	protected void afterHeaderRender() {

	}

	protected void afterFooterRender() {
		//
		if (!hideFooterArea()) {
			footerArea.setLayout(glFooter);
			footerLeftArea = new Composite(footerArea);
			footerCenterArea = new Composite(footerArea);
			footerRightArea = new Composite(footerArea);
			footerLeftArea.setLayoutData(f_gd1);
			footerCenterArea.setLayoutData(f_gd2);
			footerRightArea.setLayoutData(f_gd3);

			//
			footerLeftArea.setLayout(headerAreaLayout);
			footerCenterArea.setLayout(headerAreaLayout);
			footerRightArea.setLayout(headerAreaLayout);
		}
	}

	protected void afterRender() {
		//
		centerizeControls(headerLeftArea, headerData);
		centerizeControls(headerCenterArea, headerData);
		centerizeControls(headerRightArea, headerData);
		centerizeControls(footerLeftArea, footerData);
		centerizeControls(footerCenterArea, footerData);
		centerizeControls(footerRightArea, footerData);
		super.afterRender();
	}

	private final void centerizeControls(Composite parent, GridData gridData) {
		if (parent == null) {
			return;
		}
		Control[] children = parent.getChildren();
		for (Control control : children) {
			if (control.getLayoutData() == null
					&& parent.getLayout() instanceof GridLayout) {
				control.setLayoutData(gridData);
			}
			if (control.getID() != null) {
				controls.put(control.getID(), control);
			}
		}
	}
}
