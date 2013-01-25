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
 * 静态常量
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-23
 */
public final class BillsConstant {
	public static SimpleDateFormat DATA_yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.CHINESE);
	
	//直供清单全局缓存
	/**
	 * 正在使用中的直供商品
	 */
	public static List<GUID> useingDirectGoods = new ArrayList<GUID>();//正在使用中的直供商品

	private BillsConstant() {
	}

	/**
	 * 采购、销售外部常量
	 * 
	 * @author modi
	 * 
	 */
	public interface BillsWithout {
		/**
		 * 未完成数据（客户/供应商模块页签）
		 */
		String FINISH_NO = "01";
		/**
		 * 已完成数据（客户/供应商模块页签）
		 */
		String FINISH_YES = "02";
	}
	/**
	 * 已中止文本
	 */
	public static String STOPED = "已中止";
	/**
	 * 系统部门最大级次
	 */
	public final static int deptTierMax = 17;
	// ---------------------------订单-------------------------------
	/**
	 * 审核流程变更！写入退回原因
	 */
	public static String FLOW_CAUSE = "审核流程变更！";

	// --------------------------当前登录用户相关-------------------
	/**
	 * 根据指定用户id查询用户信息
	 * 
	 * @param context
	 * @param employeeID
	 * @return Employee
	 */
	public static Employee getEmployee(Context context, GUID employeeID) {
		return context.find(Employee.class, employeeID);
	}

	/**
	 * 获得当前员工信息
	 * 
	 * @param context
	 * @return Employee
	 */
	public static Employee getUser(Context context) {
		GUID id = context.find(Login.class).getEmployeeId();
		return context.find(Employee.class, id);
	}

	/**
	 * 获得当前用户ID
	 * 
	 * @param context
	 * @return GUID
	 */
	public static GUID getUserGuid(Context context) {
		Employee emp = getUser(context);
		return emp == null ? null : emp.getId();
	}

	/**
	 * 获得当前用户名称
	 * 
	 * @param context
	 * @return String
	 */
	public static String getUserName(Context context) {
		Employee emp = getUser(context);
		return emp == null ? null : emp.getName();
	}

	/**
	 * 验证当前用户是否是指定角色中的一个
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
	 * 验证当前指定用户是否是指定角色中的一个
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
	 * 获得当前租户信息
	 * 
	 * @param context
	 * @return Tenant
	 */
	public static Tenant getTenant(Context context) {
		return TenantHelper.getTenant(context);
	}

	/**
	 * 获得当前租户ID
	 * 
	 * @param context
	 * @return Tenant
	 */
	public static GUID getTenantsGuid(Context context) {
		return context.find(Login.class).getTenantId();
	}

	/**
	 * 获得当前用户操作权限
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
	 * 获得当前用户操作权限(第二版)
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
	
	//=========================当前租户信息==========================
	/**
	 * 当前租户是否开启直供模式
	 * @return boolean
	 */
	public static boolean isDirect(Context context){
		return BillsConstant.getTenant(context).isDirectSupply();
	}
	
	/**
	 * 获取订单审核开启情况及其自动开启金额
	 * @return ApprovalConfig
	 */
	public static ApprovalConfig getApprovalConfig(Context context){
		return context.find(ApprovalConfig.class);
	}
}
