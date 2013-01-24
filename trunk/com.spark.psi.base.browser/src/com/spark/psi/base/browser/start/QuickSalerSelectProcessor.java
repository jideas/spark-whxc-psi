package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.browser.store.StoreSalesSelectProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;

/**
 * <p>快速选择零售人员</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-4
 */

public class QuickSalerSelectProcessor extends StoreSalesSelectProcessor{
	
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		//当前仓库ID
		String storageId = this.getArgument2().toString();
		//
		List<TempUserInfo> userList = WizardUtil.getUserInfoList((SEditTable)this.getArgument3());
		if(CheckIsNull.isEmpty(userList)){
			userList = new ArrayList<TempUserInfo>();
		}
		//
		List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList((SEditTable)this.getArgument4());
		Map<String, TempStorageInfo> map = WizardUtil.getEmployeeIdMap(storageList);
		//
		LoginInfo login = context.get(LoginInfo.class);
		//查询所有员工
		GetEmployeeListKey key = new GetEmployeeListKey();
		key.setQueryAll(false);
		ListEntity<EmployeeItem> result = context.find(ListEntity.class, key);
		//
		List<TempEmployeeItemImpl> employeeItemList = new ArrayList<TempEmployeeItemImpl>();
		employeeItemList.addAll(changeList(result.getItemList()));
		TempEmployeeItemImpl tempItem = null;
		TempStorageInfo storageInfo = null;
		for(TempUserInfo userInfo : userList){
			//只加有销售权限的用户
			List<Auth> authList = context.getList(Auth.class, login.getTenantId(), userInfo.getRoles());
			if(CheckIsNull.isNotEmpty(authList) && authList.contains(Auth.Sales) && userInfo.isCreate()){
				tempItem = change(userInfo);
				//该用户已被其他仓库使用，设置被禁用为true
				storageInfo = map.get(tempItem.getId().toString());
				if(storageInfo != null && !storageInfo.getId().toString().equals(storageId)){
					tempItem.setName(userInfo.getName()+"("+storageInfo.getName()+")");
					tempItem.setDisable(true);
				}
				employeeItemList.add(tempItem);
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
	
	/**
	 * EmployeeItem列表转换为TempTemployeeItemImpl列表
	 */
	private List<TempEmployeeItemImpl> changeList(List<EmployeeItem> list){
		if(CheckIsNull.isEmpty(list)){
			return null;
		}
		List<TempEmployeeItemImpl> itemList = new ArrayList<TempEmployeeItemImpl>();
		TempEmployeeItemImpl itemImpl = null;
		for(EmployeeItem employeeItem : list){
			itemImpl = new TempEmployeeItemImpl();
			itemImpl.setId(employeeItem.getId());
			itemImpl.setName(employeeItem.getName());
			itemImpl.setDepartmentName(employeeItem.getDepartmentName());
			itemList.add(itemImpl);
		}
		return itemList;
	}
	
	@Override
	public boolean isSelectable(Object element) {
		// 根据销售员工是否已经管理其他仓库，确定是否可更改选择
		return !((TempEmployeeItemImpl)element).isDisable(); 
	}
}
