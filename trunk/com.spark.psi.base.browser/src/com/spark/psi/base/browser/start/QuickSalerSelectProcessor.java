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
 * <p>����ѡ��������Ա</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-4
 */

public class QuickSalerSelectProcessor extends StoreSalesSelectProcessor{
	
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		//��ǰ�ֿ�ID
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
		//��ѯ����Ա��
		GetEmployeeListKey key = new GetEmployeeListKey();
		key.setQueryAll(false);
		ListEntity<EmployeeItem> result = context.find(ListEntity.class, key);
		//
		List<TempEmployeeItemImpl> employeeItemList = new ArrayList<TempEmployeeItemImpl>();
		employeeItemList.addAll(changeList(result.getItemList()));
		TempEmployeeItemImpl tempItem = null;
		TempStorageInfo storageInfo = null;
		for(TempUserInfo userInfo : userList){
			//ֻ��������Ȩ�޵��û�
			List<Auth> authList = context.getList(Auth.class, login.getTenantId(), userInfo.getRoles());
			if(CheckIsNull.isNotEmpty(authList) && authList.contains(Auth.Sales) && userInfo.isCreate()){
				tempItem = change(userInfo);
				//���û��ѱ������ֿ�ʹ�ã����ñ�����Ϊtrue
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
	 * TempUserInfoת��ΪTempTemployeeItemImpl
	 */
	private TempEmployeeItemImpl change(TempUserInfo userInfo){
		TempEmployeeItemImpl item = new TempEmployeeItemImpl();
		item.setId(userInfo.getId());
		item.setName(userInfo.getName());
		item.setDepartmentName(userInfo.getDepartmentName());
		return item;
	}
	
	/**
	 * EmployeeItem�б�ת��ΪTempTemployeeItemImpl�б�
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
		// ��������Ա���Ƿ��Ѿ����������ֿ⣬ȷ���Ƿ�ɸ���ѡ��
		return !((TempEmployeeItemImpl)element).isDisable(); 
	}
}
