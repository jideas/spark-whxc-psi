package com.spark.portal.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.widgets.Control;

/**
 * 可在窗口显示时动态加载内容的MenuWindow
 * 
 */
public abstract class SDelayLoadMenuWindow extends SMenuWindow {

	/**
	 * 
	 * @param targetAndOwner
	 * @param defaultDirection
	 * @param activeMode
	 */
	public SDelayLoadMenuWindow(final Control targetAndOwner,
			Direction defaultDirection, ActiveMode activeMode) {
		super(null, targetAndOwner, defaultDirection, activeMode);
		//
		this.addClientEventHandler("beforeShow",
				"SComponent.handleDelayWindowShow");
		this.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent event) {
				updateWindowContent();
				//
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				targetAndOwner.notifyClientAction();
			}
		});
		//
		targetAndOwner.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY,
				"SComponent.handleDelayWindowTargetNotify");
		targetAndOwner.setID(this.getID());// 设置窗口ID和ownerID一致，以便在加载完毕后重新定位窗口位置和大小
		// 自动绑定
		super.bindTargetControl(targetAndOwner);
	}

	/**
	 * 当动态加载时，更新窗口内容
	 */
	protected abstract void updateWindowContent();

	/**
	 * 绑定到目标控件
	 */
	public void bindTargetControl(Control target) {
	}

	/**
	 * 取消绑定
	 */
	public void unbindTargetControl(Control target) {
	}

}
