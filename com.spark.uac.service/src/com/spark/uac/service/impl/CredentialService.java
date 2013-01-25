/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service.impl
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-29    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service.impl
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-29    jiuqi
 * ============================================================*/

package com.spark.uac.service.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.service.Publish.Mode;
import com.spark.common.utils.CredentialRegistry;
import com.spark.uac.publish.key.ValidationCredentialKey;
import com.spark.uac.publish.task.CreateCredentialTask;

/**
 * <p>TODO 类描述</p>
 *


 *
 
 * @version 2012-5-29
 */

public class CredentialService extends Service{

	protected CredentialService(){
	    super("验证密钥服务");
    }

	/**
	 * 
	 * <p>生成一个密钥</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-29
	 */
	@Publish
	protected final class CreateCredentialHandler extends SimpleTaskMethodHandler<CreateCredentialTask>{

		@Override
        protected void handle(Context context, CreateCredentialTask task)
                throws Throwable
        {
			task.setCredential(CredentialRegistry.generate(task.getName()));
        }
		
	}
	
	/**
	 * 
	 * <p>验证密钥的有效性</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-29
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class ValidationCredentialProvicer extends OneKeyResultProvider<Boolean, ValidationCredentialKey>{

		@Override
        protected Boolean provide(Context context, ValidationCredentialKey key)
                throws Throwable
        {
	        return CredentialRegistry.validate(key.getCredential(), key.getUserId());
        }
		
	}
	
	
}
