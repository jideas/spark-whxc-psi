/**
 * 
 */
package com.spark.psi.report.constant;

import com.spark.common.utils.character.CheckIsNull;

/**
 * 报表分析数据主体枚举
 */
public enum SubjectEnum{

	/**
	 * 时间
	 */
	DateTime("DateTime"),
	/**
	 * 商品条目
	 */
	GoodsItem("GoodsItem"),
	/**
	 * 客户
	 */
	Customer("Customer"),
	/**
	 * 客户并销售人员
	 */
	Customer_Employee("Customer_Employee"),
	/**
	 * 供应商
	 */
	Supplier("Supplier"),
	/**
	 * 员工
	 */
	Employee("Employee"),
	/**
	 * 部门
	 */
	Department("Department"),
	/**
	 * 地区
	 */
	Area("Area"),
	/**
	 * 库存台账
	 */
	InventoryBook("InventoryBook"),
	/**
	 * 公司
	 */
	Company("Company");
	private String code;

	private SubjectEnum(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	/**
	 * 根据code返回枚举值
	 * 
	 * @param code
	 * @return
	 */
	public static SubjectEnum getSubject(String code){
		
		if(CheckIsNull.isEmpty(code)){
			return null;
		}
		code = code.toUpperCase();
		if("GoodsItem".toUpperCase().equals(code)){
			return GoodsItem;
		}
		else if("Customer".toUpperCase().equals(code)){
			return Customer;
		}
		else if("Customer_Employee".toUpperCase().equals(code)){
			return Customer_Employee;
		}
		else if("Supplier".toUpperCase().equals(code)){
			return Supplier;
		}
		else if("Employee".toUpperCase().equals(code)){
			return Employee;
		}
		else if("Department".toUpperCase().equals(code)){
			return Department;
		}
		else if("Area".toUpperCase().equals(code)){
			return Area;
		}
		else if("InventoryBook".toUpperCase().equals(code)){
			return InventoryBook;
		}
		else if("Company".toUpperCase().equals(code)){
			return Company;
		}
		else if("DateTime".toUpperCase().equals(code)){
			return DateTime;
		}
		return null;
	}
}
