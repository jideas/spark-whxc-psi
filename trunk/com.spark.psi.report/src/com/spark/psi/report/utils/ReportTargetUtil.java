/**
 * 
 */
package com.spark.psi.report.utils;

import com.spark.psi.report.constant.SubjectEnum;
import com.spark.psi.report.constant.TargetEnum.AreaEnum;
import com.spark.psi.report.constant.TargetEnum.CompanyEnum;
import com.spark.psi.report.constant.TargetEnum.CustomerEnum;
import com.spark.psi.report.constant.TargetEnum.Customer_EmployeeEnum;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.constant.TargetEnum.DepartmentEnum;
import com.spark.psi.report.constant.TargetEnum.EmployeeEnum;
import com.spark.psi.report.constant.TargetEnum.GoodsItemEnum;
import com.spark.psi.report.constant.TargetEnum.InventoryBookEnum;
import com.spark.psi.report.constant.TargetEnum.SupplierEnum;

/**
 * @author Administrator
 *
 */
public abstract class ReportTargetUtil{

	@SuppressWarnings("unchecked")
    public static Enum getTarget(SubjectEnum subject, String code){
		switch(subject){
			case DateTime:
				return DateTimeEnum.getTarget(code);
			case GoodsItem:
				return GoodsItemEnum.getTarget(code);
			case Customer:
				return CustomerEnum.getTarget(code);
			case Customer_Employee:
				return Customer_EmployeeEnum.getTarget(code);
			case Supplier:
				return SupplierEnum.getTarget(code);
			case Employee:
				return EmployeeEnum.getTarget(code);
			case Department:
				return DepartmentEnum.getTarget(code);
			case Area:
				return AreaEnum.getTarget(code);
			case InventoryBook:
				return InventoryBookEnum.getTarget(code);
			case Company:
				return CompanyEnum.getTarget(code);
			default:
				return null;
		}
	}
}
