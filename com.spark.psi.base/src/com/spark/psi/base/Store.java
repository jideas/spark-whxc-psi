package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;

/**
 * 仓库信息<br>
 * 查询方法： <br>
 * (1)直接查询Store列表<br>
 * (2)根据仓库ID查询Store对象<br>
 * (3)查询Store列表<br>
 * (4)....
 */
public interface Store {
	static final GUID GoodsStoreId = GUID.valueOf("00000100000000000000000000000000");
	static final String GoodsStoreName = "成品库";
	public int getRecver();

	/**
	 * 仓库id
	 * @return the id
	 */
	public GUID getId();

	/**
	 * 仓库名称
	 * @return the name
	 */
	public String getName();

	/**
	 * 仓库状态
	 * @return the status
	 */
	public StoreStatus getStatus();

	/**
	 * 仓库所在省份
	 * @return the province
	 */
	
	
	public EnumEntity getProvince();

	/**
	 * 仓库所在城市
	 * @return the city
	 */
	public EnumEntity getCity();

	/**
	 * 仓库所在区县
	 * @return the district
	 */
	public EnumEntity getTown();

	/**
	 * 仓库详细地址
	 * @return the address
	 */
	public String getAddress();

	/**
	 * 仓库邮编
	 * @return the postCode
	 */
	public String getPostcode();

	/**
	 * 仓库收货人
	 * @return the consignee
	 */
	public String getConsignee();

	/**
	 * 手机
	 * @return the mobileNumber
	 */
	public String getMobileNo(); 

	/**
	 * 对应的库管人员
	 * @return the keeperIds
	 */
	public GUID[] getKeeperIds(); 
	public GUID getCreatorId();
	
	public String getStoreNo();

	public int getShelfCount();

	public int getDefaultTiersCount();

	public String getStoreType();

}
