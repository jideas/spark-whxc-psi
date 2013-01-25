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

package com.spark.uac.service.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.RemoteServiceInvoker;
import com.jiuqi.dna.core.service.Service;
import com.spark.oms.publish.message.task.SendMessageTask;
import com.spark.uac.entity.TenantInfo;
import com.spark.uac.publish.HostType;
import com.spark.uac.publish.entity.HostInfo;
import com.spark.uac.task.SendMsgTask;

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
			TenantInfo tenant = context.find(TenantInfo.class,task.getTenantId());
			RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(context.find(HostInfo.class,HostType.HOST_TYPE_OPERATION).getURL());
			remote.handle(new SendMessageTask(task.getMobileNo(),task.getMsg()+"["+tenant.getTenantTitle()+"][�����]"));
	        System.out.println("���Ͷ���Ϣ�ɹ�!");
			}catch (Exception e) {
				System.err.println("δ������Ӫƽ̨�����ܷ��Ͷ���");
			}
        }
		
	}
	
}
