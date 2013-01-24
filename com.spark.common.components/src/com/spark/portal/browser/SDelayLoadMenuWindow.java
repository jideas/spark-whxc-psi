package com.spark.portal.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.widgets.Control;

/**
 * ���ڴ�����ʾʱ��̬�������ݵ�MenuWindow
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
		targetAndOwner.setID(this.getID());// ���ô���ID��ownerIDһ�£��Ա��ڼ�����Ϻ����¶�λ����λ�úʹ�С
		// �Զ���
		super.bindTargetControl(targetAndOwner);
	}

	/**
	 * ����̬����ʱ�����´�������
	 */
	protected abstract void updateWindowContent();

	/**
	 * �󶨵�Ŀ��ؼ�
	 */
	public void bindTargetControl(Control target) {
	}

	/**
	 * ȡ����
	 */
	public void unbindTargetControl(Control target) {
	}

}
