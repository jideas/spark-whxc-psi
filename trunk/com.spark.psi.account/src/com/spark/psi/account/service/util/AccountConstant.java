package com.spark.psi.account.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.CodeBuilder;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.publish.Auth;

/**
 * <p>
 * ��̬����
 * </p>
 * 
 */
public final class AccountConstant {
	public static SimpleDateFormat DATA_yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.CHINESE);

	private AccountConstant() {

	}

	/**
	 * һ��ĺ�����
	 */
	public static long ONE_DATE_TIME = 24 * 60 * 60 * 1000;
	/**
	 * Ԥ������
	 */
	public static int WARNING_DAY = 3;

	/**
	 * ��õ��ݱ��
	 * @param context
	 * @param billsEnum
	 * @return String
	 */
	public static String getNumber(Context context, CodeBuilder code){
		return context.get(String.class, code);
	}
	// --------------------------��ǰ��¼�û����-------------------
	/**
	 * ����ָ���û�id��ѯ�û���Ϣ
	 * 
	 * @param context
	 * @param employeeID
	 * @return Employee
	 */
	public static Employee getEmployee(Context context, GUID employeeID) {
		return context.find(Employee.class, employeeID);
	}

	/**
	 * ��õ�ǰԱ����Ϣ
	 * 
	 * @param context
	 * @return Employee
	 */
	public static Employee getUser(Context context) {
		GUID id = context.find(Login.class).getEmployeeId();
		return context.find(Employee.class, id);
	}

	/**
	 * ��õ�ǰ�û�ID
	 * 
	 * @param context
	 * @return GUID
	 */
	public static GUID getUserGuid(Context context) {
		Employee emp = getUser(context);
		return emp == null ? null : emp.getId();
	}

	/**
	 * ��õ�ǰ�û�����
	 * 
	 * @param context
	 * @return String
	 */
	public static String getUserName(Context context) {
		Employee emp = getUser(context);
		return emp == null ? null : emp.getName();
	}

	/**
	 * ��֤��ǰ�û��Ƿ���ָ����ɫ�е�һ��
	 * 
	 * @param context
	 * @param auth
	 * @return Boolean
	 */
	public static boolean isAuth(Context context, Auth... auths) {
		for (Auth auth : auths) {
			if (getBoolean(context.find(Boolean.class, auth))) {
				return true;
			}
		}
		return false;
	}

	private static boolean getBoolean(Boolean b) {
		if (null == b) {
			return false;
		}
		return b;
	}

	/**
	 * ��֤��ǰָ���û��Ƿ���ָ����ɫ�е�һ��
	 * 
	 * @param context
	 * @param employeeId
	 * @param auths
	 * @return Boolean
	 */
	public static boolean isAuth(Context context, GUID employeeId,
			Auth... auths) {
		for (Auth auth : auths) {
			if (getBoolean(context.find(Boolean.class, auth,
					getUserGuid(context)))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��õ�ǰ�⻧��Ϣ
	 * 
	 * @param context
	 * @return Tenant
	 */
	public static Tenant getTenant(Context context) {
		return context.find(Tenant.class);
	}

	/**
	 * ��õ�ǰ�⻧ID
	 * 
	 * @param context
	 * @return Tenant
	 */
	public static GUID getTenantsGuid(Context context) {
		Tenant tenant = getTenant(context);
		return tenant == null ? null : tenant.getId();
	}

	// ----------------------------------Ԥ����ʾ-------------------------

	/**
	 * ���ظ������ڼ�ȥ��ǰ���� ����������
	 * 
	 * @param l
	 * @return int
	 */
	public static int sub_date_now(long l) {
		try {
			if (0 == l) {
				return Integer.MAX_VALUE;
			}
			long l_ = DATA_yyyy_MM_dd
					.parse(DATA_yyyy_MM_dd.format(new Date(l))).getTime();
			long l_new = DATA_yyyy_MM_dd.parse(
					DATA_yyyy_MM_dd.format(new Date())).getTime();
			return (int) ((l_ - l_new) / AccountConstant.ONE_DATE_TIME);
		} catch (ParseException e) {
			return AccountConstant.WARNING_DAY + 1;
		}
	}

	/**
	 * ���ظ������ڼ�ȥ��ǰ���� ����������
	 * 
	 * @param l
	 * @return int
	 */
	public static int sub_date_now(Date d) {
		return sub_date_now(d.getTime());
	}

}
