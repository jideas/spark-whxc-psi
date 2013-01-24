package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedInItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;

public abstract class ExtendSimpleSheetPageProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public final static String ID_Label_ProcessingStatusValue = "Label_ProcessingStatusValue";
	public final static String ID_Label_Link = "Label_Link";

	protected Label lblProcessingStatusValue;
	protected Label lblLink;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		lblProcessingStatusValue = createControl(ID_Label_ProcessingStatusValue, Label.class);
		lblLink = createControl(ID_Label_Link, Label.class, JWT.CURSOR_HAND | JWT.LINK);
		lblLink.setForeground(Color.COLOR_BLUE);
	}
	/**
	 * 显示入库确认浮出框
	 * 
	 */
	public void showCheckedInItemPage(final CheckInBaseInfoItem[] items) {
		if (null == items) {
			return;
		}
		SMenuWindow menuWindow = new SMenuWindow(null, lblLink, Direction.Down);
		menuWindow.bindTargetControl(lblLink);
		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW, "PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
		Composite windowArea = menuWindow.getContentArea();
		GridLayout gl = new GridLayout();
		gl.marginTop = 15;
		gl.marginBottom = 8;
		gl.marginLeft = gl.marginRight = 6;
		windowArea.setLayout(gl);
		final ScrolledPanel area = new ScrolledPanel(windowArea);
		GridData gd = new GridData();
		gd.widthHint = 230;
		int height = items.length * 28;
		gd.heightHint = height;
		area.setLayoutData(gd);
		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				area.getComposite().showPage(
						ControllerPage.NAME,
						new PageControllerInstance(new PageController(InfoPageProcessor.class, InfoPageRender.class), items,
								ShowType.CheckIngIn));
			}
		});
	}
	/**
	 * 显示入库确认浮出框
	 * 
	 */
	public void showCheckedInItemPage(final CheckedInItem[] items) {
		if (null == items) {
			return;
		}
		SMenuWindow menuWindow = new SMenuWindow(null, lblLink, Direction.Down);
		menuWindow.bindTargetControl(lblLink);
		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW, "PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
		Composite windowArea = menuWindow.getContentArea();
		GridLayout gl = new GridLayout();
		gl.marginTop = 15;
		gl.marginBottom = 8;
		gl.marginLeft = gl.marginRight = 6;
		windowArea.setLayout(gl);
		final ScrolledPanel area = new ScrolledPanel(windowArea);
		GridData gd = new GridData();
		gd.widthHint = 230;
		int height = items.length * 28;
		gd.heightHint = height;
		area.setLayoutData(gd);
		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				area.getComposite().showPage(
						ControllerPage.NAME,
						new PageControllerInstance(new PageController(InfoPageProcessor.class, InfoPageRender.class), items,
								ShowType.CheckIngIn));
			}
		});
	}

	/**
	 * 显示出库提货记录浮出框
	 * 
	 */
	public void showCheckedOutItemPage(final CheckedOutItem[] items) {
		SMenuWindow menuWindow = new SMenuWindow(null, lblLink, Direction.Down);
		menuWindow.bindTargetControl(lblLink);
		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW, "PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
		Composite windowArea = menuWindow.getContentArea();
		GridLayout gl = new GridLayout();
		gl.marginTop = 15;
		gl.marginBottom = 8;
		gl.marginLeft = gl.marginRight = 6;
		windowArea.setLayout(gl);
		final ScrolledPanel area = new ScrolledPanel(windowArea);
		GridData gd = new GridData();
		gd.widthHint = 365;
		int height = (items.length - 1) + items.length * 40 + 6;
		gd.heightHint = height;
		area.setLayoutData(gd);

		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				area.getComposite().showPage(
						ControllerPage.NAME,
						new PageControllerInstance(new PageController(InfoPageProcessor.class, InfoPageRender.class), items,
								ShowType.CheckIngOut));
			}
		});
	}
}