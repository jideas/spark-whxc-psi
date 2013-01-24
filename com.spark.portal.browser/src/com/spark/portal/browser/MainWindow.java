package com.spark.portal.browser;

import java.util.Iterator;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageListenerRegHandle;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Shell;
import com.jiuqi.dna.ui.wt.widgets.StyledPanel;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.Functions;
import com.spark.common.components.pages.MainFunction;
import com.spark.common.components.pages.MainFunctionGather;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SMessagePromptWindow;
import com.spark.portal.browser.FramePage;
import com.spark.portal.browser.FrameWindow;
import com.spark.portal.browser.FunctionsImpl;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.ResponseHandler2;
import com.spark.portal.browser.SExceptionHandler;

public final class MainWindow extends Window {

	private Label iconLabel;
	private Label titleLabel;
	private Composite contentArea;

	private final static GridLayout glHeader;

	private final static GridData gdTitleLabel;

	private final static Font titleFont = new Font(11, "宋体", JWT.NONE);

	private final static GridData gdHidden;

	private Stack<TitledPage> pageStack = new Stack<TitledPage>();

	private final static ImageBorder imageBorder = new ImageBorder(
			new ImageDescriptor[] {
					PortalImages.getImage("window/MTitlebg-left.png"),
					PortalImages.getImage("window/MTitlebg-center.png"),
					PortalImages.getImage("window/MTitlebg-right.png"),
					PortalImages.getImage("window/MpageCenterbg-right.png"),
					PortalImages.getImage("window/MFooterbg-right.png"),
					PortalImages.getImage("window/MFooterbg-center.png"),
					PortalImages.getImage("window/MFooterbg-left.png"),
					PortalImages.getImage("window/MpageCenterbg-left.png") });

	private final static ImageDescriptor closeImage1 = PortalImages
			.getImage("window/menu2-Exit-n.png");
	private final static ImageDescriptor closeImage2 = PortalImages
			.getImage("window/menu2-Exit-h.png");

	private final static GridData gdCloseLabel;

	private TitledPage currentPage;

	static {
		glHeader = new GridLayout();
		glHeader.numColumns = 3;
		glHeader.marginLeft = 2;
		glHeader.marginRight = 8;

		gdHidden = new GridData();
		gdHidden.exclude = true;
		// gdHidden.widthHint = 0;
		// gdHidden.heightHint = 0;

		gdTitleLabel = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER | GridData.FILL_HORIZONTAL);
		gdTitleLabel.verticalIndent = 5;

		gdCloseLabel = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gdCloseLabel.widthHint = closeImage1.getWidth();
		gdCloseLabel.heightHint = closeImage1.getHeight();
	}

	public final static void init(Shell shell) {
		if (shell.getData("init") != null) {
			return;
		}
		shell.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				Display display = Display.getCurrent();
				Shell shell = display.getMainShell();
				JSONObject actionData = shell.getCustomObject("actionData");
				String actionType = null;
				try {
					actionType = actionData.getString("type");
				} catch (JSONException ex) {
				}
				if ("function".equals(actionType) || "page".equals(actionType)) {
					JSONObject windowBounds = shell
							.getCustomObject("windowBounds");
					int x = 0;
					int y = 0;
					int width = 0;
					int height = 0;
					try {
						x = windowBounds.getInt("x");
						y = windowBounds.getInt("y");
						width = windowBounds.getInt("width");
						height = windowBounds.getInt("height");
					} catch (Throwable t) {
					}
					//
					MainWindow mainWindow = (MainWindow) display
							.getData("MainWindow");
					if (mainWindow == null || mainWindow.isDisposed()) {
						mainWindow = new MainWindow();
						display.setData("MainWindow", mainWindow);
					}
					mainWindow.setX(x);
					mainWindow.setY(y);
					mainWindow.setWidth(width);
					mainWindow.setHeight(height);

					//
					boolean processCurrentPage = false;
					try {
						processCurrentPage = actionData
								.getBoolean("processCurrentPage");
					} catch (JSONException ex) {
					}
					boolean processed = true;
					if (processCurrentPage) {
						processed = mainWindow.currentPage.framePage
								.processCurrentPageData();
					}
					if (processed) {
						//
						if ("function".equals(actionType)) {
							String pageName = null;
							try {
								pageName = actionData.getString("value");
							} catch (Throwable t) {
							}
							MainFunction function = MainFunctionGather
									.getFunction(pageName);
							if (function != null) {
								mainWindow.openMainFunction(function);
							}
						} else {
							String saasPageName = null;
							String title = null;
							String argument = null;
							try {
								JSONObject value = actionData
										.getJSONObject("value");
								saasPageName = value.getString("name");
								title = value.getString("title");
								argument = value.getString("argument");
							} catch (Throwable t) {
							}
							mainWindow.openPage(saasPageName, title, argument);
						}
					}
				} else {
					try {
						new SMessagePromptWindow(actionData
								.getString("message"), actionData
								.getString("button1Title"), actionData
								.getString("button2Title"));
					} catch (Throwable t) {
					}
				}
			}
		});
		shell.addClientEventHandler(JWT.CLIENT_EVENT_RESIZE,
				"SaasNavigator.onShellResize");
		shell.getDisplay().setExceptionHandler(SExceptionHandler.INSTANCE);
		shell.setData("init", true);
	}

	private MainWindow() {
		super(JWT.CLOSE | JWT.RESIZE | JWT.NO_TRIM);
		this.setID("MainWindow");
		this.setLayout(new FillLayout());
		StyledPanel panel = new StyledPanel(this);
		panel.setImageBorder(imageBorder);

		Composite headerArea = panel.getHeaderArea();

		headerArea.setLayout(glHeader);
		iconLabel = new Label(headerArea);
		titleLabel = new Label(headerArea);
		iconLabel.setLayoutData(GridData.INS_CENTER_VERTICAL);

		titleLabel.setLayoutData(gdTitleLabel);
		titleLabel.setForeground(Color.COLOR_WHITE);
		titleLabel.setFont(titleFont);

		Composite closeLabel = new Composite(headerArea);

		closeLabel.setBackimage(closeImage1);
		closeLabel.setHoverBackimage(closeImage2);
		closeLabel.setCursor(Cursor.HAND);
		closeLabel.setLayoutData(gdCloseLabel);
		closeLabel.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
				"SaasNavigator.processMainWindowClose");
		this.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				JSONObject actionData = getCustomObject("actionData");
				boolean processCurrentPage = false;
				try {
					processCurrentPage = actionData
							.getBoolean("processCurrentPage");
				} catch (JSONException ex) {
				}
				boolean processed = true;
				if (processCurrentPage) {
					//
					try {
						processed = currentPage.framePage
								.processCurrentPageData();
					} catch (RuntimeException t) {
						setVisible(true);
						throw t;
					}
				}
				if (processed) {
					closeCurrentPage();
					if (pageStack.size() == 0 && currentPage == null) {
						setVisible(false);
					}
				}
			}
		});

		contentArea = panel.getContentArea().showPage("SaaS_PortalContentPage");
		contentArea.setLayout(new GridLayout()); //

		this.addClientEventHandler(JWT.CLIENT_EVENT_INIT,
				"SaasNavigator.onWindowInit");
		this.addClientEventHandler(JWT.CLIENT_EVENT_WINDOW,
				"SaasNavigator.onMainWindowClose");

		//
		contentArea.getContext().regMessageListener(MsgRequest.class,
				new MessageListener<MsgRequest>() {
					public void onMessage(Situation context,
							MsgRequest message,
							MessageTransmitter<MsgRequest> transmitter) {
						transmitter.terminate();
						//
						if (message.getWindowStyle() != null) {
							FrameWindow window = new FrameWindow(getHandle(),
									contentArea, message.getFunctions(),
									message.getTitle(), message
											.getWindowStyle(), message
											.getResponseHandler(), message
											.getCancelHandler());
							if (message.getIcon() != null) {
								window.setIcon(message.getIcon());
							}
						} else {
							BaseFunction[] functions = message.getFunctions();
							boolean reload = false;
							if (functions.length == 1) {
								FramePage.MsgReloadFunction reloadMessage = new FramePage.MsgReloadFunction(
										functions[0]);
								contentArea.getContext().broadcastMessage(
										reloadMessage);
								reload = reloadMessage.isReloaded();
							}
							if (!reload) {
								if (message.isReplace()) {
									closeCurrentPage();
								} else {
									// 隐藏入栈
									currentPage.functionArea
											.setLayoutData(gdHidden);
									currentPage.functionArea.setVisible(false);
									pageStack.push(currentPage);
								}
								//
								openFunctions(new FunctionsImpl(functions),
										message.getTitle(),
										message.getResponseHandler());
							}
						}
					}
				});
	}

	private void openMainFunction(MainFunction function) {
		contentArea.setVisible(true);
		pageStack.clear();
		contentArea.clear();

		openFunctions(function, function.getTitle(), null);
		//
		this.setIcon(function.getTitleIcon());
	}

	private void openPage(String saasPageName, String title, String argument) {
		contentArea.setVisible(true);
		pageStack.clear();
		contentArea.clear();
		BaseFunction function = new BaseFunction(new PageControllerInstance(
				saasPageName, argument), title);
		openFunctions(new FunctionsImpl(new BaseFunction[] { function }),
				title, null);
		// this.setIcon(function.getTitleIcon());
	}

	private void openFunctions(Functions functions, String title,
			final ResponseHandler responseHandler) {
		final Composite functionArea = new Composite(contentArea);
		functionArea.setLayoutData(GridData.INS_FILL_BOTH);
		MessageListenerRegHandle<MsgCancel> regHandle = functionArea
				.getContext().regMessageListener(MsgCancel.class,
						new MessageListener<MsgCancel>() {
							public void onMessage(Situation context,
									MsgCancel message,
									MessageTransmitter<MsgCancel> transmitter) {
								transmitter.terminate();
								functionArea.setData(false);
							}
						});
		try {
			FramePage framePage = (FramePage) functionArea.showPage(
					FramePage.NAME, functions);
			if (framePage != null) {
				framePage.setID("FramePage_" + GUID.randomID().toString());
				framePage.getContext().regMessageListener(MsgResponse.class,
						new MessageListener<MsgResponse>() {
							public void onMessage(Situation context,
									MsgResponse message,
									MessageTransmitter<MsgResponse> transmitter) {
								transmitter.terminate();
								if (responseHandler != null) {
									responseHandler.handle(
											message.getReturnValue(),
											message.getReturnValue2(),
											message.getReturnValue3(),
											message.getReturnValue4());
								}
								if (message.isFinished()) {
									closeCurrentPage();
								}
								if (responseHandler instanceof ResponseHandler2) {
									((ResponseHandler2) responseHandler)
											.afterHandle(
													message.getReturnValue(),
													message.getReturnValue2(),
													message.getReturnValue3(),
													message.getReturnValue4());
								}
							}
						});
				framePage.getContext().regMessageListener(MsgCancel.class,
						new MessageListener<MsgCancel>() {
							public void onMessage(Situation context,
									MsgCancel message,
									MessageTransmitter<MsgCancel> transmitter) {
								transmitter.terminate();
								closeCurrentPage();
							}
						});
			}
			currentPage = new TitledPage(functionArea, framePage, title);
			contentArea.layout();
			//
			processPageStack();
		} finally {
			regHandle.unRegister();
			if (functionArea.getData() != null) {
				closeCurrentPage();
			} else {
				this.setVisible(true);
			}
		}
	}

	private void closeCurrentPage() {
		if (currentPage == null) {
			return;
		}
		//
		currentPage.functionArea.dispose();
		currentPage = null;
		// 出栈显示
		if (pageStack.size() > 0) {
			currentPage = pageStack.pop();
			currentPage.functionArea.setLayoutData(GridData.INS_FILL_BOTH);
			currentPage.functionArea.setVisible(true);
		}
		contentArea.layout();
		processPageStack();
		if (currentPage == null) {
			this.setVisible(false);
		}
	}

	private void processPageStack() {
		Iterator<TitledPage> it = pageStack.iterator();
		StringBuffer buffer = new StringBuffer();
		while (it.hasNext()) {
			TitledPage titledPage = it.next();
			buffer.append(titledPage.title);
			buffer.append(" -> ");
		}
		if (currentPage != null) {
			buffer.append(currentPage.title);
		}
		this.setTitle(buffer.toString());
		try {
			JSONObject data = new JSONObject();
			data.put("count", pageStack.size());
			if (currentPage != null) {
				data.put("current", currentPage.framePage.getID());
			}
			this.setCustomObject("PageStack", data);
		} catch (JSONException e) {
		}
	}

	public void setIcon(ImageDescriptor image) {
		this.iconLabel.setImage(image);
		this.iconLabel.getParent().layout();
	}

	public void setTitle(String title) {
		this.titleLabel.setText(title);
		this.iconLabel.getParent().layout();
	}

	class TitledPage {
		Composite functionArea;
		String title;
		FramePage framePage;

		private TitledPage(Composite functionArea, FramePage framePage,
				String title) {
			super();
			this.functionArea = functionArea;
			this.framePage = framePage;
			this.title = title;
		}
	}
}
