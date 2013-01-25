package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;

/**
 * 库存调拨单详情项目<br>
 * 查询方法：根据调拨单ID查询InventoryAllocateSheetInfo对象
 */
public interface InventoryAllocateSheetInfo {

	/**
	 * 获取调拨单ID
	 * 
	 * @return
	 */
	public GUID getSheetId();

	/**
	 * 获取调拨单号码
	 * 
	 * @return
	 */
	public String getSheetNumber();

	/**
	 * 获取调拨单状态
	 * 
	 * @return
	 */
	public InventoryAllocateStatus getStatus();

	/**
	 * 获取源仓库ID
	 * 
	 * @return
	 */
	public GUID getSourceStoreId();

	/**
	 * 获取源仓库名称
	 * 
	 * @return
	 */
	public String getSourceStoreName();

	/**
	 * 获取目的仓库ID
	 * 
	 * @return
	 */
	public GUID getDestinationStoreId();

	/**
	 * 获取目的仓库名称
	 * 
	 * @return
	 */
	public String getDestinationStoreName();
	
	/**
	 * 审批日期
	 * 
	 * @return
	 */
	public long getApproveDate();

	/**
	 * 审批人
	 * 
	 * @return
	 */
	public String getApprovePerson();
	/**
	 * 审批人ID
	 * 
	 * @return
	 */
	public GUID getApprovePersonId();

	/**
	 * 获取调入人
	 * 
	 * @return
	 */
	public String getAllocateInName();

	/**
	 * 获取调入时间
	 * 
	 * @return
	 */
	public long getAllocateInDate();
	/**
	 * 获取调出人
	 * 
	 * @return
	 */
	public String getAllocateOutName();

	/**
	 * 获取调出时间
	 * 
	 * @return
	 */
	public long getAllocateOutDate();

	/**
	 * 获取创建时间
	 * 
	 * @return
	 */
	public long getCreateDate();

	/**
	 * 获取制单人ID
	 * 
	 * @return
	 */
	public GUID getCreatorId();

	/**
	 * 获取制单人名称
	 * 
	 * @return
	 */
	public String getCreatorName();

	/**
	 * 获取调拨原因
	 * 
	 * @return
	 */
	public String getCause();
	/**
	 * 获取退回原因
	 */
	public String getDanyCause();

	/**
	 * 获取 调拨商品条目列表
	 * 
	 * @return
	 */
	public AllocateGoodsItem[] getItems();

	/**
	 * 调拨商品条目信息
	 */
	public static interface AllocateGoodsItem {

		public GUID getId();
		
		/**
		 * 获取商品条目ID
		 * 
		 * @return
		 */
		public GUID getGoodsItemId();

		/**
		 * 获取商品条目编码
		 * 
		 * @return
		 */
		public String getGoodsItemCode();
		
		public String getStockNumber();

		/**
		 * 获取商品条目名称
		 * 
		 * @return
		 */
		public String getGoodsItemName();

		/**
		 * 获取商品条目属性
		 * 
		 * @return
		 */
		public String getGoodsItemProperties();

		/**
		 * 获取商品条目单位
		 * 
		 * @return
		 */
		public String getGoodsItemUnit();

		/**
		 * 获取商品数量小数位
		 * 
		 * @return
		 */
		public int getScale();

		/**
		 * 获取可用库存
		 * 
		 * @return
		 */
		public double getAvailableCount();

		/**
		 * 获取调拨数量
		 * 
		 * @return
		 */
		public double getAllocateCount();

	}

}
