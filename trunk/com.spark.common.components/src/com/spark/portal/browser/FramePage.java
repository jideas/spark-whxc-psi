package com.spark.portal.browser;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.SelectionChangingEvent;
import com.jiuqi.dna.ui.wt.events.SelectionChangingListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.ImageBorderComposite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.spark.common.components.CommonImages;
import com.spark.common.components.pages.AdapterPageProcessor;
import com.spark.common.components.pages.AdapterPageRender;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.Functions;
import com.spark.common.components.pages.PageController;

/**
 * 
 */
public class FramePage extends Page {

	public final static String NAME = "SaaS_FramePage";

	private final static GridLayout gl;
	private final static FillLayout fl;
	private final static GridData gd1;
	private final static GridData gd2;
	private final static GridData gd3;

	private final static Color defaulBackground = new Color(0xd6e8f4);

	static {
		//
		fl = new FillLayout();
		fl.marginWidth = 7;
		//
		gl = new GridLayout();
		gl.numColumns = 3;
		gl.horizontalSpacing = 0;
		gl.verticalSpacing = 0;
		//
		gd1 = new GridData();
		gd1.widthHint = 4;
		gd1.heightHint = 30;
		//
		gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.heightHint = 30;
		//
		gd3 = new GridData();
		gd3.widthHint = 4;
		gd3.heightHint = 30;
	}

	private final static ImageBorder imageBorder1 = new ImageBorder(
			new ImageDescriptor[] {
					CommonImages.getImage("img/page/MTabsTopbg-left.png"),
					CommonImages.getImage("img/page/MTabsTopbg-center.png"),
					CommonImages.getImage("img/page/MTabsTopbg-right.png"),
					CommonImages.getImage("img/page/MTabsbg-right.png"),
					CommonImages.getImage("img/page/MTabsFooterbg-right.png"),
					CommonImages.getImage("img/page/MTabsFooterbg-center.png"),
					CommonImages.getImage("img/page/MTabsFooterbg-left.png"),
					CommonImages.getImage("img/page/MTabsbg-left.png") });

	private final static ImageBorder imageBorder2 = new ImageBorder(
			new ImageDescriptor[] {
					CommonImages.getImage("img/page/MTabsTopnobg-left.png"),
					CommonImages.getImage("img/page/MTabsTopnobg-center.png"),
					CommonImages.getImage("img/page/MTabsTopnobg-right.png"),
					CommonImages.getImage("img/page/MTabsbg-right.png"),
					CommonImages.getImage("img/page/MTabsFooterbg-right.png"),
					CommonImages.getImage("img/page/MTabsFooterbg-center.png"),
					CommonImages.getImage("img/page/MTabsFooterbg-left.png"),
					CommonImages.getImage("img/page/MTabsbg-left.png") });

	private BaseFunction[] functions;

	private STabs tabs;
	private ImageBorderComposite panel;

	private ControllerPage currentPage;

	public FramePage(Composite parent, Functions functions) {
		super(parent);
		init(functions.getBaseFunctions(getContext()));
	}

	public FramePage(Composite parent, BaseFunction function) {
		super(parent);
		init(function);
	}

	public boolean processCurrentPageData() {
		if (this.currentPage != null) {
			return this.currentPage.tryProcessData();
		}
		return true;
	}

	private void init(final BaseFunction... functions) {
		this.setID("FramePage");
		this.functions = functions;
		if (functions.length > 1) {
			//
			this.setLayout(gl);
			//
			Composite leftArea = new Composite(this);
			leftArea.setLayoutData(gd1);
			leftArea.setBackimage(CommonImages
					.getImage("img/page/MTabsybg-left.png"));
			//
			final Composite centerArea = new Composite(this);
			centerArea.setLayoutData(gd2);
			centerArea.setBackimage(CommonImages
					.getImage("img/page/MTabsybg-center.png"));
			centerArea.setLayout(fl);
			int firstSelection = 0;
			String[] titles = new String[functions.length];
			for (int i = 0; i < functions.length; i++) {
				if (functions[i].isDefault()) {
					firstSelection = i;
				}
				titles[i] = functions[i].getTitle();
			}
			tabs = new STabs(centerArea, titles, new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					BaseFunction subFunction = functions[tabs.getSelection()];
					currentPage = (ControllerPage) panel.showPage(
							ControllerPage.NAME,
							subFunction.getPageControllerInstance());
				}
			}, new SelectionChangingListener() {
				public void selectionChanging(SelectionChangingEvent e) {
					if (processCurrentPageData()) {
						tabs.setSelection((Integer) e.target,false);
					}
				}
			});
			//
			Composite rightArea = new Composite(this);
			rightArea.setLayoutData(gd3);
			rightArea.setBackimage(CommonImages
					.getImage("img/page/MTabsybg-right.png"));
			//
			panel = new ImageBorderComposite(this);
			panel.setBackground(defaulBackground);
			panel.setImageBorder(imageBorder1);
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.horizontalSpan = 3;
			panel.setLayoutData(gd);
			tabs.setSelection(firstSelection,false);
		} else {
			this.setLayout(new FillLayout());
			panel = new ImageBorderComposite(this);
			panel.setImageBorder(imageBorder2);
			panel.setBackground(defaulBackground);
			if (functions.length > 0) {
				currentPage = (ControllerPage) panel.showPage(
						ControllerPage.NAME,
						functions[0].getPageControllerInstance());
			}
		}
		this.getContext().regMessageListener(MsgReloadFunction.class,
				new MessageListener<MsgReloadFunction>() {
					public void onMessage(Situation context,
							MsgReloadFunction message,
							MessageTransmitter<MsgReloadFunction> transmitter) {
						PageController pageController = message
								.getBaseFunction().getPageControllerInstance()
								.getPageController();
						for (int i = 0; i < FramePage.this.functions.length; i++) {
							BaseFunction function = FramePage.this.functions[i];
							PageController functionPageController = function
									.getPageControllerInstance()
									.getPageController();
							if (functionPageController.getProcessorClazz() == AdapterPageProcessor.class
									|| functionPageController.getRenderClazz() == AdapterPageRender.class) {
								continue;
							}
							if (functionPageController.getProcessorClazz() == pageController
									.getProcessorClazz()
									&& functionPageController.getRenderClazz() == pageController
											.getRenderClazz()) {
								if (tabs != null) {
									tabs.setSelection(i,true);
									message
									.getBaseFunction().getPageControllerInstance().setInstancePage(currentPage);
								} else {
									panel.showPage(ControllerPage.NAME, message
											.getBaseFunction()
											.getPageControllerInstance());
								}
								message.setReloaded(true);
								break;
							}
						}
					}
				});
	}

	/**
	 * 
	 */
	public final static class MsgReloadFunction {
		/**
		 * 
		 */
		private BaseFunction function;

		/**
		 * 
		 */
		private boolean reloaded;

		/**
		 * 
		 * @param function
		 */
		public MsgReloadFunction(BaseFunction function) {
			super();
			this.function = function;
		}

		/**
		 * @return the reloaded
		 */
		public boolean isReloaded() {
			return reloaded;
		}

		/**
		 * @param reloaded
		 *            the reloaded to set
		 */
		public void setReloaded(boolean reloaded) {
			this.reloaded = reloaded;
		}

		/**
		 * @return the function
		 */
		public BaseFunction getBaseFunction() {
			return function;
		}

	}

}

class STabs extends Composite {

	final static String CLIENT_EVENGT_TAB_CHANGING = "tabChanging";

	private SelectionListener selectionListener;

	private SelectionChangingListener selectionChangingListener;

	private int selection = -1;

	private Composite[] tabs;

	private final static ImageDescriptor imageLeft = CommonImages
			.getImage("img/tabs/header-left-top.gif");
	private final static ImageDescriptor imageLeftActive = CommonImages
			.getImage("img/tabs/header-left-a-top.gif");
	private final static ImageDescriptor imageCenter = CommonImages
			.getImage("img/tabs/header-center-top.gif");
	private final static ImageDescriptor imageCenterActive = CommonImages
			.getImage("img/tabs/header-center-a-top.gif");
	private final static ImageDescriptor imageRight = CommonImages
			.getImage("img/tabs/header-right-top.gif");
	private final static ImageDescriptor imageRightActive = CommonImages
			.getImage("img/tabs/header-right-a-top.gif");

	private final static GridLayout centerGridLayout;

	private final static GridData gd;
	private final static GridData gdActive;
	// private final static Font titleFont;
	// private final static Font titleFontActive;
	private final static Color titleColor;
	private final static Color titleColorActive;

	static {
		centerGridLayout = new GridLayout(1);
		centerGridLayout.marginLeft = centerGridLayout.marginRight = 8;

		gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.verticalIndent = 12;
		gdActive = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gdActive.verticalIndent = 10;

		titleColor = Color.COLOR_WHITE;
		titleColorActive = Color.COLOR_BLACK;
	}

	public STabs(Composite parent, String[] titles,
			SelectionListener selectionListener,
			SelectionChangingListener selectionChangingListener) {
		super(parent);
		GridLayout gl = new GridLayout(titles.length);
		gl.horizontalSpacing = 0;
		this.setLayout(gl);
		tabs = new Composite[titles.length];
		gl = new GridLayout(3);
		gl.horizontalSpacing = 0;
		for (int i = 0; i < titles.length; i++) {
			Composite tab = new Composite(this);
			tab.setLayout(gl);
			Label leftLabel = new Label(tab);
			leftLabel.setImage(imageLeft);
			leftLabel.setLayoutData(GridData.INS_FILL_VERTICAL);
			Composite centerArea = new Composite(tab);
			centerArea.setLayout(centerGridLayout);
			centerArea.setLayoutData(GridData.INS_FILL_BOTH);
			centerArea.setBackimage(imageCenter);
			Label rightLabel = new Label(tab);
			rightLabel.setImage(imageRight);
			rightLabel.setLayoutData(GridData.INS_FILL_VERTICAL);

			Label titleLabel = new Label(centerArea);
			titleLabel.setText(titles[i]);
			titleLabel.setLayoutData(gd);
			titleLabel.setForeground(titleColor);

			leftLabel.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
					"SaasNavigator.handleTabClick");
			centerArea.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
					"SaasNavigator.handleTabClick");
			titleLabel.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
					"SaasNavigator.handleTabClick");
			rightLabel.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
					"SaasNavigator.handleTabClick");
			leftLabel.setCursor(Cursor.HAND);
			centerArea.setCursor(Cursor.HAND);
			titleLabel.setCursor(Cursor.HAND);
			rightLabel.setCursor(Cursor.HAND);
			try {
				JSONObject tabInfo = new JSONObject();
				tabInfo.put("index", i);
				tab.setCustomObject("tabInfo", tabInfo);
			} catch (JSONException e) {
			}
			tab.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER
					| GridData.GRAB_VERTICAL));
			tabs[i] = tab;
		}
		this.selectionListener = selectionListener;
		this.selectionChangingListener = selectionChangingListener;
		this.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				JSONObject actionData = getCustomObject("actionData");
				try {
					int selection = actionData.getInt("index");
					boolean processCurrentPage = actionData
							.getBoolean("processCurrentPage");
					if (processCurrentPage) {
						SelectionChangingEvent selectionChangingEvent = new SelectionChangingEvent(
								STabs.this);
						selectionChangingEvent.target = selection;
						STabs.this.selectionChangingListener
								.selectionChanging(selectionChangingEvent);
					} else {
						setSelection(selection,false);
					}
				} catch (JSONException e1) {
				}
			}
		});
	}

	public void setSelection(int selection,boolean reload) {
		if (selection == this.selection && !reload) {
			return;
		}
		for (int i = 0; i < tabs.length; i++) {
			Control[] children = tabs[i].getChildren();
			Label leftLabel = (Label) children[0];
			Composite centerArea = (Composite) children[1];
			Label rightLabel = (Label) children[2];
			Label titleLabel = (Label) centerArea.getChildren()[0];
			if(!reload || selection != this.selection) {
				if (i == this.selection) {
					leftLabel.setImage(imageLeft);
					centerArea.setBackimage(imageCenter);
					rightLabel.setImage(imageRight);
					titleLabel.setForeground(titleColor);
					titleLabel.setLayoutData(gd);
					centerArea.layout();
				} else if (i == selection) {
					leftLabel.setImage(imageLeftActive);
					centerArea.setBackimage(imageCenterActive);
					rightLabel.setImage(imageRightActive);
					titleLabel.setForeground(titleColorActive);
					titleLabel.setLayoutData(gdActive);
					centerArea.layout();
				}
			}
		}
		this.selection = selection;
		try {
			this.setCustomObject("selectionInfo",
					new JSONObject().put("index", selection));
		} catch (JSONException ex) {
		}
		if (selectionListener != null) {
			selectionListener.widgetSelected(new SelectionEvent(this));
		}
	}

	public int getSelection() {
		return selection;
	}

}
