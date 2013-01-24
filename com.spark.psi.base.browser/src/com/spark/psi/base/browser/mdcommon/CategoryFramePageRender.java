package com.spark.psi.base.browser.mdcommon;

import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.CommonImages;
import com.spark.common.components.pages.PageRender;

public abstract class CategoryFramePageRender extends PageRender {

	protected Composite headerArea;
	protected Composite leftArea;
	protected Composite rightArea;
	protected Composite leftFooterArea;

	@Override
	protected void beforeRender() {
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		gl.horizontalSpacing = 0;
		this.contentArea.setLayout(gl);
	}

	@Override
	protected void doRender() {
		GridLayout glContent = (GridLayout) contentArea.getLayout();
		glContent.verticalSpacing = 0;
		contentArea.setLayout(glContent);
		
		//
		headerArea = new Composite(contentArea);
		GridData gdHead = new GridData(GridData.FILL_HORIZONTAL);
		gdHead.heightHint = 24;
		gdHead.horizontalSpan = 3;
		headerArea.setLayoutData(gdHead);
		headerArea.setLayout(new FillLayout());

		//
		leftArea = new Composite(contentArea);
		GridData gdLeft = new GridData(GridData.FILL_VERTICAL);
		gdLeft.widthHint = 250;
		leftArea.setLayoutData(gdLeft);
		leftArea.setLayout(new FillLayout());

		//
		Composite emptyCmp = new Composite(contentArea);
		GridData gdEmpty = new GridData(GridData.FILL_VERTICAL);
		gdEmpty.widthHint = 6;
		emptyCmp.setLayoutData(gdEmpty);

		//
		rightArea = new Composite(contentArea);
		GridData gdRright = new GridData(GridData.FILL_BOTH);
		gdRright.verticalSpan = 2;
		rightArea.setLayoutData(gdRright);
		rightArea.setID(CategoryFramePageProcessor.ID_Area_Right);

		//
		if(!hideFooterArea()){
			//
			leftFooterArea = new Composite(contentArea);
			GridData gdFooter = new GridData();
			gdFooter.widthHint = gdLeft.widthHint;
			gdFooter.heightHint = 33;
			leftFooterArea.setLayout(new GridLayout());
			leftFooterArea.setLayoutData(gdFooter);
			leftFooterArea.setBackimage(CommonImages
					.getImage("img/page/MTabsarea_bottom.png"));
	
			//
			Composite emtpyFooter = new Composite(contentArea);
			GridData gdEmtpyFooter = new GridData();
			gdEmtpyFooter.widthHint = 6;
			gdEmtpyFooter.heightHint = 33;
			emtpyFooter.setLayoutData(gdEmtpyFooter);
			emtpyFooter.setBackimage(CommonImages
					.getImage("img/page/MTabsarea_bottom.png"));
		}

		//
//		new GoodsCategoryBarPage(headerArea, isShowAdd());
//		new GoodsCategoryListPage(leftArea, isShowAdd());
		
		rendHeader(headerArea);
		rendLeft(leftArea);

//		this.getContext().broadcastMessage(new GoodsCategorySelectionMsg(null));
	}

	protected abstract void rendHeader(Composite headerArea);
	
	protected abstract void rendLeft(Composite leftArea);
	
	protected boolean hideFooterArea() {
		return false;
	}

}
