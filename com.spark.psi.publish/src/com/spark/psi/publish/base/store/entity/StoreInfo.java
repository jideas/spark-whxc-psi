package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 仓库详细信息<br>
 * 查询方法：通过仓库ID直接查询StoreInfo对象
 */
public interface StoreInfo  {

	/**
	 * 版本号
	 * 
	 * @return int
	 */
	public int getRecver();
	
	/**
	 * id
	 * @return the id
	 */
	public GUID getId();

	/**
	 * 仓库名称
	 * @return the name
	 */
	public String getName();
	
	/**
	 * 获得仓库编号
	 * @return
	 */
	public String getStoreNo();

	/**
	 * 状态
	 * @return the status
	 */
	public StoreStatus getStatus();

	/**
	 * 省
	 * @return the province
	 */
	public EnumEntity getProvince();

	/**
	 * 市
	 * @return the city
	 */
	public EnumEntity getCity();

	/**
	 * 县
	 * @return the district
	 */
	public EnumEntity getTown();

	/**
	 * 详细地址
	 * @return the address
	 */
	public String getAddress();
	/**
	 * 邮编
	 * @return the postCode
	 */
	public String getPostcode();

	/**
	 * 收货人
	 * @return the consignee
	 */
	public String getConsignee();

	/**
	 * 收货人手机
	 * @return the mobileNumber
	 */
	public String getMobileNo();

	/**
	 * 对应的仓库负责人
	 * @return the keepers
	 */
	public EmployeeInfo[] getKeepers();
	
	/**
	 * 获得该仓库的货位
	 * @return
	 */
	public ShelfItem[] getShelfItems();
	
	/**
	 * 获得该仓库的货位个数
	 * @return
	 */
	public int getShelfCount();
	
	/**
	 * 获得货位的默认层数
	 * @return
	 */
	public int getDefaultTiersCount();

}
