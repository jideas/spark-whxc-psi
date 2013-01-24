package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.spi.application.Session;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.internal.entity.LoginImpl;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.config.task.LoginTask;
import com.spark.psi.publish.base.config.task.LogoutTask;

public class LoginService extends ResourceService<Login, LoginImpl, LoginImpl> {

	protected LoginService() {
		super("系统会话服务", ResourceKind.SINGLETON_IN_LOGIN);
	}

	@Override
	protected void initResources(Context context,
			ResourceInserter<Login, LoginImpl, LoginImpl> initializer)
			throws Throwable {
		LoginImpl login = new LoginImpl();
		// login.setTenantId(GUID.valueOf("33A0D122000000015430DCD5D0865EA4"));
		// login.setEmployeeId(GUID.valueOf("33A0D757C0000001F7EC76A07B18309B"));
		initializer.putResource(login);
	}

	@Publish
	class LoginTaskHandler extends SimpleTaskMethodHandler<LoginTask> {

		@Override
		protected void handle(
				ResourceContext<Login, LoginImpl, LoginImpl> context,
				LoginTask task) throws Throwable {
			LoginImpl login = context.modifyResource();
			// if (login.getEmployeeId() == null || login.getTenantId() == null)
			// {
			Employee employee = context.find(Employee.class,
					task.getEmployeeId());
			login.setTenantId(employee.getTenantId());
			login.setEmployeeId(employee.getId());
			login.setAcls(context.getList(Auth.class, employee.getTenantId(),
					employee.getTenantId().equals(employee.getId()) ? 1
							: employee.getRoles()));
			context.postModifiedResource(login);
			// }
		}
	}

	@Publish
	class LogoutTaskHandler extends SimpleTaskMethodHandler<LogoutTask> {

		@Override
		protected void handle(
				ResourceContext<Login, LoginImpl, LoginImpl> context,
				LogoutTask task) throws Throwable {
			LoginImpl login = context.modifyResource();
			login.setTenantId(null);
			login.setEmployeeId(null);
			context.postModifiedResource(login);
			((Session) context.getLogin()).dispose(1000);
		}
	}

	@Publish
	class LoginProvider extends SingletonResourceProvider {

	}

}
