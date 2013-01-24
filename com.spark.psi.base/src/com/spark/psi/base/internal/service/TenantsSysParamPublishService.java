package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.oms.publish.order.task.EnableServiceRunTask;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.task.TenantsSysParamTask;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.start.key.FindSysParamValueKey;
import com.spark.psi.publish.base.start.key.FindSystemEnabledKey;
import com.spark.psi.publish.base.start.task.SaveOrUpdateTenantsSysParamTask;
import com.spark.uac.publish.ProductSerialsEnum;

/**
 * <p>
 * 租户系统参数服务
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 20012 - 20018<br>
 * 
 * 
 * @author 刘青
 * @version 2012-7-3
 */

public class TenantsSysParamPublishService extends Service {

	/**
	 *构造方法
	 */
	protected TenantsSysParamPublishService() {
		super("com.spark.psi.base.internal.service.TenantsSysParamPublishService");
	}

	/**
	 * 新增或更新系统参数
	 */
	@Publish
	protected class SaveOrUpdateSysParamHandler extends SimpleTaskMethodHandler<SaveOrUpdateTenantsSysParamTask> {

		@Override
		protected void handle(Context context, SaveOrUpdateTenantsSysParamTask task) throws Throwable {
			TenantsSysParamTask sysParamTask = new TenantsSysParamTask(task.getKey(), task.getYes());
			if (task.getKey() == SysParamKey.HAS_INIT_START && task.getYes()) {
				try {
					EnableServiceRunTask enableServiceRunTask = new EnableServiceRunTask();
					enableServiceRunTask.setTenantsId(context.find(Login.class).getTenantId());
					enableServiceRunTask.setProductSerial(ProductSerialsEnum.PSI.getCode());
					context.handle(enableServiceRunTask);
				} catch (Exception e) {
					System.err.println("运营未启动，或者修改租户状态失败");
				}
			}
			context.handle(sysParamTask);
		}

	}

	/**
	 * 查询系统是否启动
	 */
	@Publish
	protected class SystemEnabledProvider extends OneKeyResultProvider<Boolean, FindSystemEnabledKey> {

		@Override
		protected Boolean provide(Context context, FindSystemEnabledKey key) throws Throwable {
			return context.find(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_START));
		}

	}

	/**
	 * 查询系统参数值
	 */
	@Publish
	protected class InitSysParamValueProvider extends OneKeyResultProvider<Boolean, FindSysParamValueKey> {

		/**
		 * 获得查询SQL
		 */
		private String getSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_SysParamValue(@tenantsGuid guid, @key string) ");
			buffer.append(" begin ");
			buffer.append(" select t.value as value ");
			buffer.append(" from SA_TENANTS_SYS_PARAM as t ");
			buffer.append(" where t.tenantsGuid=@tenantsGuid and t.\"key\"=@key");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Boolean provide(Context context, FindSysParamValueKey key) throws Throwable {
			Boolean result = false;
			DBCommand dbCommand = context.prepareStatement(getSql());
			try {
				dbCommand.setArgumentValue(0, context.find(Tenant.class).getId());
				dbCommand.setArgumentValue(1, key.getKey().name());
				RecordSet recordSet = dbCommand.executeQuery();
				while (recordSet.next()) {
					result = recordSet.getFields().get(0).getBoolean();
				}
			} finally {
				dbCommand.unuse();
			}
			return result;
		}

	}

}
