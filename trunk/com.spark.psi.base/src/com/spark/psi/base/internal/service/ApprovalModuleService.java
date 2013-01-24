/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-9    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-9    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.base.Tenant;

/**
 * <p>审核配置内部服务</p>
 *


 *
 
 * @version 2012-3-9
 */

public class ApprovalModuleService extends Service{

	protected ApprovalModuleService(){
	    super("com.spark.psi.base.internal.service.ApprovalModuleService");
    }

	
	/**
	 * 
	 * <p>获得审核配置</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-9
	 */
	@Publish
	protected class GetApprovalConfigProvider extends ResultProvider<ApprovalConfig>{

		@Override
        protected ApprovalConfig provide(Context context) throws Throwable{
	        return context.find(Tenant.class).getApprovalConfig();
        }		
	}
	
	/**
	 * 
	 * <p>获得审核配置</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-9
	 */
	@Publish
	protected class GetApprovalConfigProvider2 extends OneKeyResultProvider<ApprovalConfig,GUID>{

		@Override
        protected ApprovalConfig provide(Context context,GUID key) throws Throwable{
			Tenant tenant = context.find(Tenant.class,key);
	        return tenant.getApprovalConfig();
        }		
	}

	
}
