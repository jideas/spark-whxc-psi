package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.browser.store.StoreKeeperSelectProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;

/**
 * <p>快速选择库管人员</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-4
 */

public class QuickKeepersSelectProcessor extends StoreKeeperSelectProcessor{

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		List<TempUserInfo> userList = WizardUtil.getUserInfoList((SEditTable)this.getArgument3());
		if(CheckIsNull.isEmpty(userList)){
			userList = new ArrayList<TempUserInfo>();
		}
		LoginInfo login = context.get(LoginInfo.class);
		//查询所有员工
		GetEmployeeListKey key = new GetEmployeeListKey();
		key.setQueryAll(false);
		ListEntity<EmployeeItem> result = context.find(ListEntity.class, key);
		//
		List<EmployeeItem> employeeItemList = new ArrayList<EmployeeItem>();
		employeeItemList.addAll(result.getItemList());
		for(TempUserInfo userInfo : userList){
			//只加有库管权限的用户
			List<Auth> authList = context.getList(Auth.class, login.getTenantId(), userInfo.getRoles());
			if(CheckIsNull.isNotEmpty(authList) && authList.contains(Auth.StoreKeeper) && userInfo.isCreate()){
				employeeItemList.add(change(userInfo));
			}
			
		}
		return employeeItemList.toArray();
	}
	
	/**
	 * TempUserInfo转换为TempTemployeeItemImpl
	 */
	private TempEmployeeItemImpl change(TempUserInfo userInfo){
		TempEmployeeItemImpl item = new TempEmployeeItemImpl();
		item.setId(userInfo.getId());
		item.setName(userInfo.getName());
		item.setDepartmentName(userInfo.getDepartmentName());
		return item;
	}
}
