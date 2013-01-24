package com.spark.psi.base.internal.entity.helper;

import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Store;
import com.spark.psi.base.internal.entity.ormentity.StoreOrmEntity;
import com.spark.psi.base.publicimpl.SelectSalesEmployeeByStoreItemImpl;
import com.spark.psi.base.publicimpl.StoreInfoImpl;
import com.spark.psi.base.publicimpl.StoreItemImpl;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.StoreType;
import com.spark.psi.publish.base.store.task.StoreInfoTask;

public class StoreHelper{
	
	public static StoreInfoImpl storeToStoreInfo(Store impl){
		if(impl==null)return null;
		StoreInfoImpl entity = new StoreInfoImpl();
		entity.setAddress(impl.getAddress());
		entity.setCity(impl.getCity());
		entity.setConsignee(impl.getConsignee());
		entity.setTown(impl.getTown());
		entity.setId(impl.getId()); 
		entity.setMobileNo(impl.getMobileNo());
		entity.setName(impl.getName());
		entity.setPostcode(impl.getPostcode());
		entity.setProvince(impl.getProvince());
		entity.setStatus(impl.getStatus());
		entity.setRecver(impl.getRecver());
		entity.setShelfCount(impl.getShelfCount());
		entity.setStoreNo(impl.getStoreNo());
		entity.setDefaultTiersCount(impl.getDefaultTiersCount());
		entity.setStoreType(impl.getStoreType());
		return entity;
	}
	
	
	public static StoreItemImpl storeToStoreItem(Store impl){
		if(impl==null)return null;
		StoreItemImpl entity = new StoreItemImpl();
		entity.setAddress(impl.getAddress());
		entity.setId(impl.getId());
		entity.setName(impl.getName());
		entity.setStatus(impl.getStatus());	
		entity.setKeeperIds(impl.getKeeperIds());
		entity.setCreatePerson(impl.getCreatorId());
		entity.setRecver(impl.getRecver());
		return entity;
	}
	
	public static StoreOrmEntity storeInfoTaskToStoreOrmEntity(StoreInfoTask impl){
		StoreOrmEntity entity = new StoreOrmEntity();
		return storeInfoTaskToStoreOrmEntity(impl, entity);
	}
	
	public static StoreOrmEntity storeInfoTaskToStoreOrmEntity(StoreInfoTask impl,StoreOrmEntity entity){
		entity.setAddress(impl.getAddress());
		entity.setCity(impl.getCityCode());
		entity.setConsignee(impl.getConsignee());
		entity.setTown(impl.getTownCode());
		entity.setId(impl.getId());
		entity.setStoreNo(impl.getStoreNo());
		entity.setMobileNo(impl.getMobileNo());
		entity.setStoreName(impl.getName());
		entity.setNamePY(PinyinHelper.getLetter(impl.getName()));
		entity.setPostCode(impl.getPostcode());
		entity.setProvince(impl.getProvinceCode());
		entity.setStatus(impl.getStoreStatus().getCode());
		entity.setStoreType(StoreType.MerterialsStore.getCode());
		return entity;		
	}
	
	/**
	 * 返回员工列表对象
	 * 
	 * @param e
	 * @return EmployeeItem
	 */
	public static SelectSalesEmployeeByStoreItemImpl employeeToItem(Employee e){
		SelectSalesEmployeeByStoreItemImpl item = new SelectSalesEmployeeByStoreItemImpl();
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
		return item;
	}

}
