package com.spark.order.intf.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.publish.Auth;

/**
 * ��̬����
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-23
 */
public final class BillsConstant {
	public static SimpleDateFormat DATA_yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.CHINESE);
	
	//ֱ���嵥ȫ�ֻ���
	/**
	 * ����ʹ���е�ֱ����Ʒ
	 */
	public static List<GUID> useingDirectGoods = new ArrayList<GUID>();//����ʹ���е�ֱ����Ʒ

	private BillsConstant() {
	}

	/**
	 * �ɹ��������ⲿ����
	 * 
	 * @author modi
	 * 
	 */
	public interface BillsWithout {
		/**
		 * δ������ݣ��ͻ�/��Ӧ��ģ��ҳǩ��
		 */
		String FINISH_NO = "01";
		/**
		 * ��������ݣ��ͻ�/��Ӧ��ģ��ҳǩ��
		 */
		String FINISH_YES = "02";
	}
	/**
	 * ����ֹ�ı�
	 */
	public static String STOPED = "����ֹ";
	/**
	 * ϵͳ������󼶴�
	 */
	public final static int deptTierMax = 17;
	// ---------------------------����-------------------------------
	/**
	 * ������̱����д���˻�ԭ��
	 */
	public static String FLOW_CAUSE = "������̱����";

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
		return TenantHelper.getTenant(context);
	}

	/**
	 * ��õ�ǰ�⻧ID
	 * 
	 * @param context
	 * @return Tenant
	 */
	public static GUID getTenantsGuid(Context context) {
		return context.find(Login.class).getTenantId();
	}

	/**
	 * ��õ�ǰ�û�����Ȩ��
	 * 
	 * @param context
	 * @return UserAuthEnum
	 */
	public static UserAuthEnum getUserAuth(Context context, BillsEnum billsEnum) {
		if (isAuth(context, Auth.Boss)) {
			return UserAuthEnum.BOSS;
		} else if ((billsEnum.isInEnum(BillsEnum.PURCHASE, BillsEnum.PURCHASE_CANCEL) && isAuth(context, Auth.PurchaseManager))
				|| ((billsEnum.isInEnum(BillsEnum.SALE, BillsEnum.SALE_CANCEL, BillsEnum.SALE_PROMOTION, BillsEnum.Retail_Order, BillsEnum.Retail_Return) && isAuth(context, Auth.SalesManager)))) {
			return UserAuthEnum.MANGER;
		} else {
			return UserAuthEnum.EMPLOYEE;
		}
	}
	
	/**
	 * ��õ�ǰ�û�����Ȩ��(�ڶ���)
	 * 
	 * @param context
	 * @return UserAuthEnum
	 */
	public static UserAuthEnum getUserAuth(Context context, OrderEnum billsEnum) {
		if (isAuth(context, Auth.Boss)) {
			return UserAuthEnum.BOSS;
		} else if ((billsEnum.isIn(OrderEnum.Purchase, OrderEnum.Purchase_Return) && isAuth(context, Auth.PurchaseManager))
				|| ((billsEnum.isIn(OrderEnum.Sales, OrderEnum.Sales_Return, OrderEnum.Sales_Promotion, OrderEnum.Retail, OrderEnum.Retail_Return) && isAuth(context, Auth.SalesManager)))) {
			return UserAuthEnum.MANGER;
		} else {
			return UserAuthEnum.EMPLOYEE;
		}
	}
	
	//=========================��ǰ�⻧��Ϣ==========================
	/**
	 * ��ǰ�⻧�Ƿ���ֱ��ģʽ
	 * @return boolean
	 */
	public static boolean isDirect(Context context){
		return BillsConstant.getTenant(context).isDirectSupply();
	}
	
	/**
	 * ��ȡ������˿�����������Զ��������
	 * @return ApprovalConfig
	 */
	public static ApprovalConfig getApprovalConfig(Context context){
		return context.find(ApprovalConfig.class);
	}
}
