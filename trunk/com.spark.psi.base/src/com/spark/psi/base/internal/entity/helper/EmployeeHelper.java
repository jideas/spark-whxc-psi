package com.spark.psi.base.internal.entity.helper;

import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.Employee;
import com.spark.psi.base.publicimpl.EmployeePublishImpl;
import com.spark.psi.publish.EmployeeStatus;

public class EmployeeHelper{
	
	/**
	 * ����Ա���б����
	 * 
	 * @param e
	 * @return EmployeeItem
	 */
	public static EmployeePublishImpl employeeToItem(Employee e){
		EmployeePublishImpl item = new EmployeePublishImpl();
		item.setId(e.getId());
		item.setBirthday(e.getBirthday());
		item.setDepartmentId(e.getDepartmentId());
		item.setEmail(StringHelper.toStr(e.getEmail()));
		item.setIdNumber(StringHelper.toStr(e.getIdNumber()));
		item.setMobileNo(StringHelper.toStr(e.getMobileNo()));
		item.setName(StringHelper.toStr(e.getName()));
		item.setPosition(StringHelper.toStr(e.getPosition()));
		item.setRoles(e.getRoles());
		item.setStatus(EmployeeStatus.getEmployeeStatus(e.getStatus()));
		item.setLogo(e.getLogo());
		item.setLandLineNumber(e.getLandLineNumber());
		item.setStyle(e.getStyle());
		return item;
	}
	
	/**
	 * ����Ա��Info����
	 * 
	 * @param e
	 * @return EmployeeItem
	 */
	public static EmployeePublishImpl employeeToInfo(Employee e){
		return EmployeeHelper.employeeToItem(e);
	}
	
//	/**
//	 * ����Ա���б����
//	 * 
//	 * @param e
//	 * @return EmployeeItem
//	 */
//	public static EmployeeImpl resourceToBaseImpl(Employee e){
//		EmployeeImpl item = new EmployeeImpl();
//		item.setId(e.getId());
//		item.setBirthday(e.getBirthday());
//		item.setDepartmentId(e.getDepartmentId());
//		item.setEmail(StringHelper.toStr(e.getEmail()));
//		item.setIdNumber(StringHelper.toStr(e.getIdNumber()));
//		item.setMobileNo(StringHelper.toStr(e.getMobileNo()));
//		item.setName(StringHelper.toStr(e.getName()));
//		item.setPosition(StringHelper.toStr(e.getPosition()));
//		item.setRoles(e.getRoles());
//		item.setStatus(EmployeeStatus.getEmployeeStatus(e.getStatus()));		
//		return item;
//	}
//	
}
