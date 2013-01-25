/**
 * 
 */
package com.spark.psi.report.constant;

import com.spark.common.utils.character.CheckIsNull;

/**
 * ���������������ö��
 */
public enum SubjectEnum{

	/**
	 * ʱ��
	 */
	DateTime("DateTime"),
	/**
	 * ��Ʒ��Ŀ
	 */
	GoodsItem("GoodsItem"),
	/**
	 * �ͻ�
	 */
	Customer("Customer"),
	/**
	 * �ͻ���������Ա
	 */
	Customer_Employee("Customer_Employee"),
	/**
	 * ��Ӧ��
	 */
	Supplier("Supplier"),
	/**
	 * Ա��
	 */
	Employee("Employee"),
	/**
	 * ����
	 */
	Department("Department"),
	/**
	 * ����
	 */
	Area("Area"),
	/**
	 * ���̨��
	 */
	InventoryBook("InventoryBook"),
	/**
	 * ��˾
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
	 * ����code����ö��ֵ
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
