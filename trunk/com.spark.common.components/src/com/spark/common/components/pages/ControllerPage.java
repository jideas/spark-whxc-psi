package com.spark.common.components.pages;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.hotkey.HotkeyDefine;
import com.jiuqi.dna.ui.custom.hotkey.HotkeyTargetBindUtils;
import com.jiuqi.dna.ui.custom.hotkey.MsgHotkeyPressed;
import com.jiuqi.dna.ui.wt.JWTException;
import com.jiuqi.dna.ui.wt.events.WidgetAdapter;
import com.jiuqi.dna.ui.wt.events.WidgetEvent;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.StyledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.edit.SEditTable;

/**
 * 通用控制页面（根据处理器和渲染器来加载页面）
 */
public final class ControllerPage extends Page {

	public final static String NAME = "SaaS_ControllerPage";

	private PageProcessor processor;
	private PageRender render;

	public ControllerPage(Composite parent,
			PageControllerInstance pageControllerInstance) {
		super(parent);
		// 创建处理器和渲染器
		try {
			render = (PageRender) pageControllerInstance.getPageController()
					.getRenderClazz().newInstance();
			render.setContentArea(this);
			processor = pageControllerInstance.getPageController()
					.getProcessorClazz().newInstance();
			processor.setRender(render);
			processor.setArgument(pageControllerInstance.getArgument());
			processor.setArgument2(pageControllerInstance.getArgument2());
			processor.setArgument3(pageControllerInstance.getArgument3());
			processor.setArgument4(pageControllerInstance.getArgument4());
			render.setProcessor(processor);
		} catch (Throwable t) {
			throw new JWTException("初始化处理器和渲染器失败：" + t.getMessage());
		}

		// 初始化控制器
		processor.init(getContext());

		//
		render.init(getContext());
		render.beforeRender();
		render.doRender();
		render.afterRender();
		//
		processor.process(getContext());
		// 添加快捷键监听
		HotkeyDefine[] hotkeyDefines = processor.getHotkeyDefines();
		if (hotkeyDefines != null && hotkeyDefines.length > 0) {
			// 添加的快捷键设置和监听器之后才添加客户端的快捷键监听
			HotkeyTargetBindUtils.bindTarget(this, hotkeyDefines);
			this.getContext().regMessageListener(MsgHotkeyPressed.class,
					new MessageListener<MsgHotkeyPressed>() {
						public void onMessage(Situation context,
								MsgHotkeyPressed message,
								MessageTransmitter<MsgHotkeyPressed> transmitter) {
							processor.hotkeyPressed(message.getHotkeyDefine());
						}
					});
		}
		//
		processor.postProcess(getContext());
		//
		//
		this.addWidgetListener(new WidgetAdapter() {
			public void widgetDisposed(WidgetEvent e) {
				processor.postDisposed(getContext());
			}
		});
		//
		prepareCurrentPageData();
		
		//
		pageControllerInstance.setInstancePage(this);
	}

	/**
	 * @return the processor
	 */
	public PageProcessor getProcessor() {
		return processor;
	}

	/**
	 * @return the render
	 */
	public PageRender getRender() {
		return render;
	}

	private void prepareCurrentPageData() {
		PageProcessor processor = getProcessor();
		if (processor instanceof IDataProcessPrompt) {
			JSONObject promptInfo = new JSONObject();
			try {
				promptInfo.put("message", ((IDataProcessPrompt) processor)
						.getPromptMessage());
			} catch (JSONException ex) {
			}
			setCustomObject("promptInfo", promptInfo);
			addClientMessageHandler("QueryDataState",
					"SaasNavigator.handleQueryDataState");
			addClientMessageHandler("DataChanged",
					"SaasNavigator.handlePageDataChanged");
			addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY, "SaasNavigator.resetDataChangedState");
			addClientEventHandler(JWT.CLIENT_EVENT_INIT, "SaasNavigator.resetDataChangedState");
			getContext().regMessageListener(MsgResetDataStatus.class,
					new MessageListener<MsgResetDataStatus>() {
						public void onMessage(
								Situation context,
								MsgResetDataStatus message,
								MessageTransmitter<MsgResetDataStatus> transmitter) {
//							JSONObject promptInfo = getCustomObject("promptInfo");
//							promptInfo.remove("dataChanged");
//							setCustomObject("promptInfo", promptInfo);
							notifyClientAction();
						}
					});
			getContext().regMessageListener(MsgMarkDataChanged.class,
					new MessageListener<MsgMarkDataChanged>() {
						public void onMessage(
								Situation context,
								MsgMarkDataChanged message,
								MessageTransmitter<MsgMarkDataChanged> transmitter) {
							JSONObject promptInfo = getCustomObject("promptInfo");
							try {
								promptInfo.put("dataChanged", true);
							} catch (JSONException e) {
							}
							setCustomObject("promptInfo", promptInfo);
						}
					});
			getContext().regMessageListener(MsgProcessData.class,
					new MessageListener<MsgProcessData>() {
						public void onMessage(Situation context,
								MsgProcessData message,
								MessageTransmitter<MsgProcessData> transmitter) {
							PageProcessor processor = getProcessor();
							if (processor instanceof IDataProcessPrompt) {
								IDataProcessPrompt dataProcessPrompt = (IDataProcessPrompt) processor;
								if (!dataProcessPrompt.processData()) {
									message.setFailed();
								}
							}
						}
					});
		}
		// 遍历子控件，给文本输入框和其他输入控件增加事件
		registerDataChangeEvent(this);
	}

	public final boolean tryProcessData() {
		MsgProcessData msgProcessData = new MsgProcessData();
		getContext().broadcastMessage(msgProcessData);
		return !msgProcessData.isFailed();
	}

	private void registerDataChangeEvent(Composite composite) {
		Control[] controls = composite.getChildren();
		if (controls != null) {
			for (Control control : controls) {
				if (control instanceof STable) {
					control.addClientEventHandler(
							SEditTable.CLIENT_EVENT_VALUE_CHANGED,
							"SaasNavigator.$1");
				} else if (control instanceof Text) {
					if(!(control instanceof SSearchText2)) {
						control.addClientEventHandler(JWT.CLIENT_EVENT_DOCUMENT,
								"SaasNavigator.$2");
						control.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
								"SaasNavigator.$3");
					}
				} else if (control instanceof Composite) {
					if (!(control instanceof ControllerPage)) {
						registerDataChangeEvent((Composite) control);
					}
				} else if (control instanceof ScrolledPanel) {
					registerDataChangeEvent(((ScrolledPanel) control)
							.getComposite());
				} else if (control instanceof StyledPanel) {
					registerDataChangeEvent(((StyledPanel) control)
							.getContentArea());
				}
			}
		}
	}
}
