package com.spark.psi.base.internal.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.task.resource.GenerateSerialNumberTask;
import com.spark.psi.publish.Auth;

/**
*
*/
public class BaseInternalService extends Service {

	/**
	 * 
	 */
	protected BaseInternalService() {
		super("����ģ���ڲ�����");
	}

	/**
	 * ��ѯ��ǰ�û���Ȩ��
	 */
	@Publish
	class AuthDataProvider extends OneKeyResultProvider<Boolean, Auth> {

		protected Boolean provide(Context context, Auth auth) throws Throwable {
			Login login = context.find(Login.class);
			return login.hasAuth(auth);
		}

	}

	/**
	 * ��ѯ��ǰ�û���Ȩ��
	 */
	@Publish
	class AuthDataProvider2 extends OneKeyResultProvider<Boolean, Auth[]> {

		protected Boolean provide(Context context, Auth[] auths) throws Throwable {
			Login login = context.find(Login.class);
			return login.hasAuth(auths);
		}

	}

	/**
	 * ��ѯָ���û���Ȩ�ޣ�������ϸ���ȵ��ã����ҪƵ�����ã�������Ա����Ϣ�л��壩
	 */
	@Publish
	class AuthDataProvider3 extends TwoKeyResultProvider<Boolean, Auth, GUID> {

		protected Boolean provide(Context context, Auth target, GUID employeeId) throws Throwable {
			Employee employee = context.find(Employee.class, employeeId);
			if (null == employee) {
				return false;
			}
			if (employeeId.equals(employee.getTenantId())) {
				return true; // XXX���ܾ����˻������ǲ�Ӧ����ô����
			}
			return context.getList(Auth.class, employee.getTenantId(), employee.getRoles()).indexOf(target) != -1;
		}

	}

	/**
	 * ��ѯָ���û���Ȩ�ޣ�������ϸ���ȵ��ã����ҪƵ�����ã�������Ա����Ϣ�л��壩
	 */
	@Publish
	class AuthDataProvider4 extends TwoKeyResultProvider<Boolean, Auth[], GUID> {

		protected Boolean provide(Context context, Auth[] auths, GUID employeeId) throws Throwable {
			Employee employee = context.find(Employee.class, employeeId);
			if (employee.getTenantId().equals(employeeId)) {
				return true; // XXX���ܾ����˻������ǲ�Ӧ����ô����
			}
			Set<Auth> allAcls = new HashSet<Auth>();
			allAcls.addAll(context.getList(Auth.class, employee.getTenantId(), employee.getRoles()));
			for (Auth auth : auths) {
				if (!allAcls.contains(auth)) {
					return false;
				}
			}
			return true;
		}

	}

	/**
	 * ��ѯָ���⻧��ָ����ɫ���ϵ�Ȩ�ޣ�������Դ��ʼ��ʱ����ѭ������ʱ���ã�������ϸ���ȵ��ã����ҪƵ�����ã�������Ա����Ϣ�л��壩
	 */
	@Publish
	class AuthDataProvider5 extends ThreeKeyResultProvider<Boolean, Auth, GUID, Integer> {

		protected Boolean provide(Context context, Auth target, GUID tenantId, Integer roles) throws Throwable {
			return context.getList(Auth.class, tenantId, roles).indexOf(target) != -1;
		}

	}

	/**
	 * 
	 * ����ָ����Ա���Թ�Ͻ��������Ա�б�<br>
	 * (1)������ܾ����򷵻�����Ա����<br>
	 * (2)��������۾����򷵻����Լ��Լ����š��Ӳ��ŵ�����Ա����<br>
	 * (3)�������ͨ���ۣ��򷵻����Լ� <br>
	 * (4)���������Ա��Ա�����ؿ�
	 */
	@Publish
	class SalesmanListProvider extends OneKeyResultListProvider<Employee, Employee> {

		@Override
		protected void provide(Context context, Employee employee, List<Employee> resultList) throws Throwable {
			if (context.find(Boolean.class, Auth.Boss, employee.getId())) {
				resultList.addAll(context.getList(Employee.class));
				// �ܾ���
			} else if (context.find(Boolean.class, Auth.Sales, employee.getId())) {
				// ���۾���
				if (context.find(Boolean.class, Auth.SalesManager, employee.getId())) {
					// TODO��
				} else {
					// ��ͨ������Ա
					resultList.add(employee);
				}
			}

		}
	}

	@Publish
	class GetNextSheetNumberProvider extends OneKeyResultProvider<String, SheetNumberType> {

		@Override
		protected String provide(Context context, SheetNumberType key) throws Throwable {
			// TODO�������⻧��������ǰ׺

			//
			StringBuffer buffer = new StringBuffer(key.getDefaultPrefix());
			if (!key.isOnlyOrderNo()) {
				Calendar calendar = Calendar.getInstance();
				// year
				buffer.append(calendar.get(Calendar.YEAR));
				// month
				int month = calendar.get(Calendar.MONTH) + 1;
				if (month < 10) {
					buffer.append('0');
				}
				buffer.append(month);
				// day
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				if (day < 10) {
					buffer.append('0');
				}
				buffer.append(day);
			}
			//
			int serialLength = key.getLength(); // XXX�����ڲ��ԣ�����Ϊ��󳤶�
			StringBuffer serialBuffer = new StringBuffer(serialLength);
			GenerateSerialNumberTask task = new GenerateSerialNumberTask(key.name(), buffer.toString());
			context.handle(task);
			serialBuffer.append(task.getResult());
			if (serialBuffer.length() > serialLength) {
				throw new IllegalStateException("���ݱ�ų������õ����Χ");
			}
			while (serialBuffer.length() < serialLength) {
				serialBuffer.insert(0, '0');
			}

			//
			return buffer.append(serialBuffer).toString();
		}
	}

}
