package com.spark.psi.report.eventlistener;

import com.jiuqi.dna.core.service.Service;

/**
 * ��������ģ���¼�����
 * 
 */
public class AccountListener extends Service {

	protected AccountListener() {
		super("AccountListener");
	}

	/**
	 * �����仯ʱ�䣨��ʼ����ʵ��������������Ӧ����
	 * �����仯ʱ�䣨��ʼ����ʵ�ա� ����������Ӧ�� ��
	 */
	// @Publish
	// class PP extends EventListener<DealingAmountChanageEvent> {
	//
	// @Override
	// protected void occur(Context context,
	// DealingAmountChanageEvent event) throws Throwable {
	// context.handle(new QueueAddTask(event));
	// }
	//
	// }
}
