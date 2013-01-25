package com.spark.order.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.psi.base.Department;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.base.key.GetSalesManagerListByDepartmentIdKey;

/**
 * <p>销售订单工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-23
 */
public final class SalesUtil {
	private SalesUtil(){
	}
	//=========================销售审核部门========================
	/**
	 * 销售订单保存是，审核部门
	 * @param context
	 * @return GUID
	 */
	public static GUID getInitSalesApproveDept(Context context){
		GUID deptId = BillsConstant.getUser(context).getDepartmentId();
		return getInitSalesApproveDept(context, deptId);
	}
	/**
	 * 销售订单保存是，审核部门
	 * @param context
	 * @return GUID
	 */
	public static GUID getInitSalesApproveDept(Context context, GUID deptId){
		switch (BillsConstant.getUserAuth(context, OrderEnum.Sales)) {
		case BOSS:
			break;
		case MANGER:
			deptId = getSuperiorSalesManagerDeptByManagerCreate(context, deptId);
			break;
		case EMPLOYEE:
			if(!isHaveSalesManagerByDept(context, deptId)){
				deptId = getSuperiorSalesManagerDept(context, deptId);
			}
			break;
		}
		return deptId;
	}
	
	/**
	 * 获得上级有销售经理的部门，无上级返回租户编号
	 * @return GUID
	 */
	public static GUID getSuperiorSalesManagerDept(Context context, GUID deptId){
		Department dept = context.find(Department.class, deptId);
		while(null != dept.getParent()){
			if(isHaveSalesManagerByDept(context, dept.getId())){
				return dept.getId();
			}
			dept = dept.getParent(context);
		}
		return BillsConstant.getTenantsGuid(context);
	}
	
	/**
	 * 获得上级有销售经理的部门，无上级返回租户编号(创建人为经理和审核时使用)
	 * @param context
	 * @param deptId
	 * @return
	 */
	public static GUID getSuperiorSalesManagerDeptByManagerCreate(Context context, GUID deptId){
		Department dept = context.find(Department.class, deptId);
		return getSuperiorSalesManagerDept(context, dept.getParent());
//		dept = dept.getParent(context);
//		while(null != dept.getParent()){
//			GUID[] gs = getSalesManagerListByDepartmentId(context, dept.getId());
//			if(null != gs && 0 < gs.length){
//				if(gs.length > 1){
//					return dept.getId();
//				}
//				else if(deptId != gs[0]){
//					return deptId;
//				}
//			}
//			dept = dept.getParent(context);
//		}
//		return BillsConstant.getTenantsGuid(context);
	}
	
	/**
	 * 判断指定部门是否有销售经理
	 * @param context
	 * @param deptId
	 * @return boolean
	 */
	public static boolean isHaveSalesManagerByDept(Context context, GUID deptId){
		return 0 != getDeptSalesManagerCount(context, deptId);
	}
	
	/**
	 * 部分销售经理个数
	 * @param context
	 * @param deptId
	 * @return boolean
	 */
	private static int getDeptSalesManagerCount(Context context, GUID deptId){
		GUID[] gs = getSalesManagerListByDepartmentId(context, deptId);
		return gs == null?0:gs.length;
	}
	
	/**
	 * getSalesManagerListByDepartmentId
	 * @param context
	 * @param deptId
	 * @return GUID[]
	 */
	public static GUID[] getSalesManagerListByDepartmentId(Context context, GUID deptId){
		GetSalesManagerListByDepartmentIdKey key = new GetSalesManagerListByDepartmentIdKey(deptId);
		return context.find(GUID[].class, key);
	}
	
	//====================销售经理审核信息=====================
	/**
	 * 获得用户销售审核信息
	 * @param context
	 * @param userId
	 * @return SalesmanCredit
	 */
	public static SalesmanCredit getSalesmanCredit(Context context, GUID userId){
		return context.find(SalesmanCredit.class, userId);
	}
	
}
