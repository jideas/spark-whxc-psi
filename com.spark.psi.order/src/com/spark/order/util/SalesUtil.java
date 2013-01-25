package com.spark.order.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.psi.base.Department;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.base.key.GetSalesManagerListByDepartmentIdKey;

/**
 * <p>���۶���������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-23
 */
public final class SalesUtil {
	private SalesUtil(){
	}
	//=========================������˲���========================
	/**
	 * ���۶��������ǣ���˲���
	 * @param context
	 * @return GUID
	 */
	public static GUID getInitSalesApproveDept(Context context){
		GUID deptId = BillsConstant.getUser(context).getDepartmentId();
		return getInitSalesApproveDept(context, deptId);
	}
	/**
	 * ���۶��������ǣ���˲���
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
	 * ����ϼ������۾���Ĳ��ţ����ϼ������⻧���
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
	 * ����ϼ������۾���Ĳ��ţ����ϼ������⻧���(������Ϊ��������ʱʹ��)
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
	 * �ж�ָ�������Ƿ������۾���
	 * @param context
	 * @param deptId
	 * @return boolean
	 */
	public static boolean isHaveSalesManagerByDept(Context context, GUID deptId){
		return 0 != getDeptSalesManagerCount(context, deptId);
	}
	
	/**
	 * �������۾������
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
	
	//====================���۾��������Ϣ=====================
	/**
	 * ����û����������Ϣ
	 * @param context
	 * @param userId
	 * @return SalesmanCredit
	 */
	public static SalesmanCredit getSalesmanCredit(Context context, GUID userId){
		return context.find(SalesmanCredit.class, userId);
	}
	
}
