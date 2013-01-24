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
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;

/**
 * <p>
 * 租户信息内部配置
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-9
 */

public class TenantModuleService extends Service {

	protected TenantModuleService() {
		super("com.spark.psi.base.internal.service.TenantModuleService");
	}

	/**
	 * 
	 * <p>
	 * 获得指定租户的服务信息
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2012<br>
	 * Company: 久其
	 * </p>
	 * 
	 
	 * @version 2012-3-9
	 */
	protected class GetTenantByIdProvider extends
			OneKeyResultProvider<Tenant, GUID> {

		@Override
		protected Tenant provide(Context context, GUID key) throws Throwable {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * 
	 * <p>
	 * 获得当前登录租户的服务信息
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2012<br>
	 * Company: 久其
	 * </p>
	 * 
	 
	 * @version 2012-3-9
	 */
	@Publish
	protected class GetTenantProvider extends ResultProvider<Tenant> {

		@Override
		protected Tenant provide(Context context) throws Throwable {
			return context.find(Tenant.class, context.find(Login.class)
					.getTenantId());
		}

	}

}
