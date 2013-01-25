/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service.impl
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service.impl
 * 修改记录：
 * 日期                作者           内容
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
 * <p>短信服务</p>
 *


 *
 
 * @version 2012-4-12
 */

public class SendMsgService extends Service{

	protected SendMsgService(){
	    super("发送短消息服务");
    }

	@Publish
	protected final class SendMsgHandler extends SimpleTaskMethodHandler<SendMsgTask>{

		@Override
        protected void handle(Context context, SendMsgTask task)
                throws Throwable
        {
			System.out.println("向"+task.getMobileNo()+"发送一条短消息");
			System.out.println("发送内容\n"+task.getMsg());
			try{
			TenantInfo tenant = context.find(TenantInfo.class,task.getTenantId());
			RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(context.find(HostInfo.class,HostType.HOST_TYPE_OPERATION).getURL());
			remote.handle(new SendMessageTask(task.getMobileNo(),task.getMsg()+"["+tenant.getTenantTitle()+"][鱼儿多]"));
	        System.out.println("发送短消息成功!");
			}catch (Exception e) {
				System.err.println("未启动运营平台，不能发送短信");
			}
        }
		
	}
	
}
