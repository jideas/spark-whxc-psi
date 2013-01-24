package com.spark.portal.browser;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.common.components.pages.BaseFunction;

public class FrameWindow extends Window {

	public FrameWindow(String parentHandler, Control owner,
			BaseFunction[] functions, String title, WindowStyle windowStyle,
			final ResponseHandler responseHandler,
			final CancelHandler cancelHandler) {
		super(parentHandler, owner, windowStyle.getStyle() | JWT.MODAL); // XXX：暂时先设置为Modal
		this.setTitle(title);
		if (windowStyle.getWidth() > 0) {
			this.setWidth(windowStyle.getWidth());
		}
		if (windowStyle.getHeight() > 0) {
			this.setHeight(windowStyle.getHeight());
		}
		final FramePage framePage = (FramePage) this.showPage(FramePage.NAME,
				new FunctionsImpl(functions));

		//
		framePage.getContext().regMessageListener(MsgRequest.class,
				new MessageListener<MsgRequest>() {
					public void onMessage(Situation context,
							MsgRequest message,
							MessageTransmitter<MsgRequest> transmitter) {
						transmitter.terminate();
						// 这里暂时支持继续打开新窗口
						WindowStyle windowStyle = message.getWindowStyle();
						if (windowStyle == null) {
							windowStyle = new WindowStyle(JWT.CLOSE);
						}
						FrameWindow window = new FrameWindow(getHandle(),
								framePage, message.getFunctions(), message
										.getTitle(), windowStyle, message
										.getResponseHandler(), message
										.getCancelHandler());
						if (message.getIcon() != null) {
							window.setIcon(message.getIcon());
						}
					}
				});
		framePage.getContext().regMessageListener(MsgResponse.class,
				new MessageListener<MsgResponse>() {
					public void onMessage(Situation context,
							MsgResponse message,
							MessageTransmitter<MsgResponse> transmitter) {
						transmitter.terminate();
						if (responseHandler != null) {
							responseHandler.handle(message.getReturnValue(),
									message.getReturnValue2(),
									message.getReturnValue3(),
									message.getReturnValue4());
						}
						if (message.isFinished()) {
							close();
						}
						if(responseHandler instanceof ResponseHandler2){
							((ResponseHandler2)responseHandler).afterHandle(message.getReturnValue(), message
							        .getReturnValue2(), message.getReturnValue3(), message.getReturnValue4());
						}
					}
				});
		framePage.getContext().regMessageListener(MsgCancel.class,
				new MessageListener<MsgCancel>() {
					public void onMessage(Situation context, MsgCancel message,
							MessageTransmitter<MsgCancel> transmitter) {
						transmitter.terminate();
						if (cancelHandler != null) {
							cancelHandler.handle();
						}
						close();
					}
				});
		this.addClientEventHandler(JWT.CLIENT_EVENT_WINDOW,
				"SaasNavigator.onFrameWindowCloseButtonClick");
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
						processed = framePage.processCurrentPageData();
					} catch (RuntimeException t) {
						setVisible(true);
						throw t;
					}
				}
				if (processed) {
					close();
				}
			}
		});
	}

}
