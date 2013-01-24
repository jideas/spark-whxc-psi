/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-9    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
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
 * �⻧��Ϣ�ڲ�����
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
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
	 * ���ָ���⻧�ķ�����Ϣ
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
	 * Company: ����
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
	 * ��õ�ǰ��¼�⻧�ķ�����Ϣ
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
	 * Company: ����
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
