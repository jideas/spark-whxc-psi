/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.service.impl
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.service.impl
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.RemoteServiceInvoker;
import com.jiuqi.dna.core.service.Service;
import com.spark.oms.publish.message.task.SendMessageTask;
import com.spark.psi.base.Tenant;
import com.spark.psi.publish.base.sms.task.SendMsgTask;
import com.spark.uac.publish.HostType;
import com.spark.uac.publish.entity.HostInfo;

/**
 * <p>���ŷ���</p>
 *


 *
 
 * @version 2012-4-12
 */

public class SendMsgService extends Service{

	protected SendMsgService(){
	    super("���Ͷ���Ϣ����");
    }

	@Publish
	protected final class SendMsgHandler extends SimpleTaskMethodHandler<SendMsgTask>{

		@Override
        protected void handle(Context context, SendMsgTask task)
                throws Throwable
        {
			System.out.println("��"+task.getMobileNo()+"����һ������Ϣ");
			System.out.println("��������\n"+task.getMsg());
			try{
			Tenant tenant = context.find(Tenant.class,task.getTenantId());
			RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(context.find(HostInfo.class,HostType.HOST_TYPE_OPERATION).getURL());
			remote.handle(new SendMessageTask(task.getMobileNo(),task.getMsg()+"["+tenant.getTitle()+"][�ߺ������]"));
	        System.out.println("���Ͷ���Ϣ�ɹ�!");
			}catch (Exception e) {
				System.err.println("δ������Ӫƽ̨�����ܷ��Ͷ���");
			}
        }
		
	}
	
}
