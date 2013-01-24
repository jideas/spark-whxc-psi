package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.publish.SysParamKey;

/**
 * <p>向导向导的工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-4
 */

public class WizardUtil{

	/**向导配置步骤页面名字常量*/
	public final static String COMPANEY_PAGENAME = "CompanyInfoStepPage"; //配置企业信息

	public final static String DEPARTMENT_PAGENAME = "DepartmentInfoStepPage"; //配置部门信息

	public final static String EMPLOYEE_PAGENAME = "EmployeeInfoStepPage"; //添加员工和用户

	public final static String CUSTOMER_PAGENAME = "CustomerInfoStepPage"; //添加客户信息

	public final static String PROVIDER_PAGENAME = "ProviderInfoStepPage"; //添加供应商信息

	public final static String GOODSTYPE_PAGENAME = "GoodsTypeStepPage"; //添加商品分类

	public final static String GOODS_PAGENAME = "GoodsInfoStepPage"; //添加商品信息

	public final static String STORAGE_PAGENAME = "StorageInfoStepPage"; //配置公司仓库

	public final static String STARTSYSTEM_PAGENAME = "StartSystemStepPage"; //启用系统

	/**
	 * 获得向导pageNameArr数组
	 */
	public static List<String> getPageNameList(){
		List<String> pageList = new ArrayList<String>();
		pageList.add(COMPANEY_PAGENAME);
		pageList.add(DEPARTMENT_PAGENAME);
		pageList.add(EMPLOYEE_PAGENAME);
		pageList.add(CUSTOMER_PAGENAME);
		pageList.add(PROVIDER_PAGENAME);
		pageList.add(GOODSTYPE_PAGENAME);
		pageList.add(GOODS_PAGENAME);
		pageList.add(STORAGE_PAGENAME);
		pageList.add(STARTSYSTEM_PAGENAME);
		return pageList;
	}

	/**
	 * 获得系统参数key
	 */
	public static Map<String, SysParamKey> getSysParamMap(){
		Map<String, SysParamKey> map = new HashMap<String, SysParamKey>();
		map.put(COMPANEY_PAGENAME, SysParamKey.HAS_INIT_COMPANY);
		map.put(DEPARTMENT_PAGENAME, SysParamKey.HAS_INIT_DEPT);
		map.put(EMPLOYEE_PAGENAME, SysParamKey.HAS_INIT_EMP);
		map.put(CUSTOMER_PAGENAME, SysParamKey.HAS_INIT_CUSPRO);
		map.put(PROVIDER_PAGENAME, SysParamKey.HAS_INIT_PROVIDER);
		map.put(GOODSTYPE_PAGENAME, SysParamKey.HAS_INIT_GOODSTYPE);
		map.put(GOODS_PAGENAME, SysParamKey.HAS_INIT_GOODS);
		map.put(STORAGE_PAGENAME, SysParamKey.HAS_INIT_STORE);
		map.put(STARTSYSTEM_PAGENAME, SysParamKey.HAS_INIT_START);
		return map;
	}

	/**
	 * 获得页面Map
	 */
	public static Map<String, WizardStepNode> getPageNameMap(){
		List<String> pageNameList = getPageNameList();
		if(CheckIsNull.isEmpty(pageNameList)){
			return null;
		}
		Map<String, WizardStepNode> map = new HashMap<String, WizardStepNode>();
		int length = pageNameList.size();
		SysParamKey key = null;
		String prevPageName = null;
		String nextPageName = null;
		for(int i = 0; i < length; i++){
			key = getSysParamMap().get(pageNameList.get(i));
			if(i == 0){ //第一个无上页
				prevPageName = null;
				nextPageName = pageNameList.get(i+1);
			}
			else if(i == length - 1){ //最后一个无下页
				prevPageName = pageNameList.get(i - 1);
				nextPageName = null;
			}
			else{ //既有上页，也有下页
				prevPageName = pageNameList.get(i - 1);
				nextPageName = pageNameList.get(i + 1);
			}
			map.put(pageNameList.get(i), new WizardStepNode(key, prevPageName, pageNameList.get(i), nextPageName));
		}
		return map;
	}

	/**
	 * 获得所有的用户
	 */
	public static List<TempUserInfo> getUserInfoList(SEditTable userTable){
		List<TempUserInfo> list = new ArrayList<TempUserInfo>();
		String[] userIdArr = userTable.getAllRowId();
		if(userIdArr != null && userIdArr.length > 0){
			for(int i = 0; i < userIdArr.length; i++){
				list.add(getUserInfoByRowId(userIdArr[i], userTable));
			}
		}
		return list;
	}

	/**
	 * 根据行ID获得用户
	 */
	public static TempUserInfo getUserInfoByRowId(String rowId, SEditTable userTable){
		TempUserInfo tempUserInfo = new TempUserInfo();
		tempUserInfo.setId(GUID.valueOf(rowId));
		//附加值
		String[] extraValues =
		        userTable.getExtraData(rowId, QuickSetUserProcessor.Columns.Name.name(),
		                QuickSetUserProcessor.Columns.MobileNo.name(), QuickSetUserProcessor.Columns.DepartmentName
		                        .name(), QuickSetUserProcessor.Columns.RolesName.name(),
		                QuickSetUserProcessor.Columns.IdentityNumber.name(),
		                QuickSetUserProcessor.Columns.Eamil.name(), QuickSetUserProcessor.Columns.Job.name(),
		                QuickSetUserProcessor.Columns.DepartmentId.name(), QuickSetUserProcessor.Columns.Roles.name(),
		                QuickSetUserProcessor.Columns.IsCreate.name());
		tempUserInfo.setName(extraValues[0]);
		tempUserInfo.setMobile(extraValues[1]);
		tempUserInfo.setDepartmentName(extraValues[2]);
		tempUserInfo.setRolesName(extraValues[3]);
		tempUserInfo.setIdentityNumber(extraValues[4]);
		tempUserInfo.setEmail(extraValues[5]);
		tempUserInfo.setJob(extraValues[6]);
		tempUserInfo.setDepartmentId(GUID.valueOf(extraValues[7]));
		tempUserInfo.setRoles(Integer.parseInt(extraValues[8]));
		tempUserInfo.setCreate(Boolean.parseBoolean(extraValues[9]));
		return tempUserInfo;
	}

	/**
	 * 获得所有的仓库
	 */
	public static List<TempStorageInfo> getStorageInfoList(SEditTable storageTable){
		List<TempStorageInfo> list = new ArrayList<TempStorageInfo>();
		String[] storageIdArr = storageTable.getAllRowId();
		if(storageIdArr != null && storageIdArr.length > 0){
			for(int i = 0; i < storageIdArr.length; i++){
				list.add(getStorageInfoByRowId(storageIdArr[i], storageTable));
			}
		}
		return list;
	}

	/**
	 * 根据行ID获得仓库
	 */
	public static TempStorageInfo getStorageInfoByRowId(String rowId, SEditTable storageTable){
		TempStorageInfo tempStorageInfo = new TempStorageInfo();
		tempStorageInfo.setId(GUID.valueOf(rowId));
		//附加值
		String[] extraValues =
		        storageTable.getExtraData(rowId, QuickSetStoreProcessor.Columns.Name.name(),
		                QuickSetStoreProcessor.Columns.Phone.name(), QuickSetStoreProcessor.Columns.Consignee.name(),
		                QuickSetStoreProcessor.Columns.Mobile.name(), QuickSetStoreProcessor.Columns.Province.name(),
		                QuickSetStoreProcessor.Columns.City.name(), QuickSetStoreProcessor.Columns.District.name(),
		                QuickSetStoreProcessor.Columns.Address.name(), QuickSetStoreProcessor.Columns.Postcode.name(),
		                QuickSetStoreProcessor.Columns.KeeperNames.name(), QuickSetStoreProcessor.Columns.KeeperIds
		                        .name(), QuickSetStoreProcessor.Columns.SaleNames.name(),
		                QuickSetStoreProcessor.Columns.SaleIds.name(), QuickSetStoreProcessor.Columns.Enabled.name());
		tempStorageInfo.setName(extraValues[0]);
		tempStorageInfo.setPhone(extraValues[1]);
		tempStorageInfo.setConsignee(extraValues[2]);
		tempStorageInfo.setMobile(extraValues[3]);
		tempStorageInfo.setProvince(extraValues[4]);
		tempStorageInfo.setCity(extraValues[5]);
		tempStorageInfo.setDistrict(extraValues[6]);
		tempStorageInfo.setAddress(extraValues[7]);
		tempStorageInfo.setPostcode(extraValues[8]);
		tempStorageInfo.setKeeperNames(extraValues[9]);
		tempStorageInfo.setKeeperIds(extraValues[10]);
		tempStorageInfo.setSaleNames(extraValues[11]);
		tempStorageInfo.setSaleIds(extraValues[12]);
		tempStorageInfo.setEnabled(Boolean.valueOf(extraValues[13]));
		return tempStorageInfo;
	}

	/**
	 * 根据仓库列表获得Map　key：员工ID value：TempStorageInfo
	 */
	public static Map<String, TempStorageInfo> getEmployeeIdMap(List<TempStorageInfo> storageList){
		Map<String, TempStorageInfo> map = new HashMap<String, TempStorageInfo>();
		if(CheckIsNull.isEmpty(storageList)){
			return map;
		}
		String tempKeeperIds = null;
		String tempSalerIds = null;
		for(TempStorageInfo storageInfo : storageList){
			tempKeeperIds = storageInfo.getKeeperIds();
			if(CheckIsNull.isNotEmpty(tempKeeperIds)){
				for(String id : tempKeeperIds.split(";")){
					map.put(id, storageInfo);
				}
			}
			tempSalerIds = storageInfo.getSaleIds();
			if(CheckIsNull.isNotEmpty(tempSalerIds)){
				for(String id : tempSalerIds.split(";")){
					map.put(id, storageInfo);
				}
			}
		}
		return map;
	}

	/**
	 * 根据用户列表获得Map　key：手机号  value：TempUserInfo
	 */
	public static Map<String, TempUserInfo> getMobileMap(List<TempUserInfo> userList){
		Map<String, TempUserInfo> map = new HashMap<String, TempUserInfo>();
		if(CheckIsNull.isEmpty(userList)){
			return map;
		}
		for(TempUserInfo userInfo : userList){
			map.put(userInfo.getMobile(), userInfo);
		}
		return map;
	}

	/**
	 * 根据仓库列表获得Map　key：仓库名称  value：TempStorageInfo
	 */
	public static Map<String, TempStorageInfo> getStorageNameMap(List<TempStorageInfo> storageList){
		Map<String, TempStorageInfo> map = new HashMap<String, TempStorageInfo>();
		if(CheckIsNull.isEmpty(storageList)){
			return map;
		}
		for(TempStorageInfo storageInfo : storageList){
			map.put(storageInfo.getName(), storageInfo);
		}
		return map;
	}
}
