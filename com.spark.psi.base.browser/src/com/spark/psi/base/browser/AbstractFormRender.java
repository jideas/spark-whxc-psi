package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.spark.common.components.pages.AbstractBoxPageRender;

public abstract class AbstractFormRender extends AbstractBoxPageRender {

	private Composite titleArea;
	/**
	 * 上部左边区域
	 */
	protected Composite headerLeftArea;
	
	/**
	 * 上部右边区域
	 */
	protected Composite headerRightArea;
	
	/**
	 * 下部左边区域
	 */
	protected Composite footerLeftArea;

	/**
	 * 下图右边区域
	 */
	protected Composite footerRightArea;
	
	private static GridData gdTitle;
	private static GridLayout glTitle;
	
	private final static GridLayout headerAreaLayout;
	private final static GridLayout footerAreaLayout;
	
	private final static GridLayout glFooter;
	private final static GridData gdFooter;
	
	private final static GridData h_gdLeft;
	private final static GridData h_gdRight;
	private final static GridData f_gdLeft;
	private final static GridData f_gdRight;
	
	private final static GridLayout glHeader;
	private final static GridData gdHeader;
	
	private final static GridData headerData;
	private final static GridData footerData;
	
	static {
		gdTitle = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gdTitle.heightHint = 40;
		glTitle = new GridLayout();
		glTitle.numColumns = 2;
		
		glHeader = new GridLayout();
		glHeader.numColumns = 3;
		gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader.heightHint = 32;
		
		glFooter = new GridLayout();
		glFooter.numColumns = 3;
		gdFooter = new GridData(GridData.FILL_HORIZONTAL);
		gdFooter.heightHint = 29;
		
		h_gdLeft = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.GRAB_HORIZONTAL);
		h_gdLeft.heightHint = 24;
		h_gdRight = new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		h_gdRight.heightHint = 24;
		
		f_gdLeft = new GridData(GridData.VERTICAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.GRAB_HORIZONTAL);
		f_gdLeft.heightHint = 29;
		f_gdRight = new GridData(GridData.VERTICAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_END
				| GridData.GRAB_HORIZONTAL);
		f_gdRight.heightHint = 29;
		
		headerAreaLayout = new GridLayout();
		headerAreaLayout.numColumns = 15;
		headerAreaLayout.horizontalSpacing = 0;
		
		footerAreaLayout = new GridLayout();
		footerAreaLayout.numColumns = 10;
		footerAreaLayout.horizontalSpacing = 0;
		
		headerData = new GridData(GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_VERTICAL);
		footerData = new GridData(GridData.FILL_VERTICAL);
	}
	
	@Override
	protected void afterFooterRender() {
		footerArea.setLayout(glFooter);
		footerLeftArea = new Composite(footerArea);
		footerRightArea = new Composite(footerArea);
		footerLeftArea.setLayoutData(f_gdLeft);
		footerRightArea.setLayoutData(f_gdRight);

		//
		footerLeftArea.setLayout(headerAreaLayout);
		footerRightArea.setLayout(headerAreaLayout);
		
		fillHeader();
		fillFooter();
	}

	@Override
	protected void beforeFooterRender() {
		titleArea = new Composite(super.contentArea);
		titleArea.setLayoutData(gdTitle);
		titleArea.setLayout(glTitle);

		// do title render
		SheetTitleComposite titleCmp = new SheetTitleComposite(titleArea);
		GridData gdTitle = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_CENTER);
		gdTitle.widthHint = 330;
		titleCmp.setLayoutData(gdTitle);
		titleCmp.setID(AbstractFormProcessor.ID_SheetTitleComposite);
		SheetNumberComposite codeCmp = new SheetNumberComposite(titleArea);
		GridData gdCode = new GridData(GridData.HORIZONTAL_ALIGN_CENTER
				| GridData.VERTICAL_ALIGN_BEGINNING);
		codeCmp.setLayoutData(gdCode);
		codeCmp.setID(AbstractFormProcessor.ID_SheetNumberComposite);
		
		// do header render
		Composite headerArea = new Composite(contentArea);
		headerArea.setLayoutData(gdHeader);
		headerArea.setLayout(glHeader);
		
		headerLeftArea = new Composite(headerArea);
		headerRightArea = new Composite(headerArea);
		
		headerLeftArea.setLayoutData(h_gdLeft);
		headerRightArea.setLayoutData(h_gdRight);
		
		headerLeftArea.setLayout(headerAreaLayout);
		headerRightArea.setLayout(headerAreaLayout);
		
		//do content render
		fillContent();
	}
	
	protected abstract void fillContent();
	protected abstract void fillHeader();
	protected abstract void fillFooter();
	
	protected int getHeaderRowCount() {
		return 1;
	}
	
	protected final void afterRender() {
		//
		centerizeControls(headerLeftArea, headerData);
		centerizeControls(headerRightArea, headerData);
		centerizeControls(footerLeftArea, footerData);
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
