package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;

/**
 * 盘点单详细信息<br>
 * 查询方法：根据盘点单ID查询InventoryCountSheetInfo对象
 */
public interface InventoryCountSheetInfo {

	/**
	 * 获取单据ID
	 * 
	 * @return
	 */
	public GUID getSheetId();

	/**
	 * 获取单据号
	 * 
	 * @return
	 */
	public String getSheetNumber();

	/**
	 * 获取盘点单类型
	 * 
	 * @return
	 */
	public InventoryCountType getSheetType();

	/**
	 * 获取盘点单状态
	 * 
	 * @return
	 */
	public InventoryCountStatus getSheetstatus();

	/**
	 * 获取开始日期
	 * 
	 * @return
	 */
	public long getStartDate();

	/**
	 * 获取结束日期
	 * 
	 * @return
	 */
	public long getEndDate();

	/**
	 * 获取仓库ID
	 * 
	 * @return
	 */
	public GUID getStoreId();

	/**
	 * 获取仓库名称
	 * 
	 * @return
	 */
	public String getStoreName();

	/**
	 * 获取制单人ID
	 * 
	 * @return
	 */
	public GUID getCreatorId();

	/**
	 * 获取制单人姓名
	 * 
	 * @return
	 */
	public String getCreatorName();
	
	/**
	 * 获取盘点人姓名
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * 获取备注信息
	 * 
	 * @return
	 */
	public String getRemark();

	/**
	 * 获取商品盘点条目列表
	 * 
	 * @return
	 */
	public GoodsCountItem[] getGoodsCountItems();

	/**
	 * 获取其他物品盘点条目列表
	 * 
	 * @return
	 */
	public KitCountItem[] getKitCountItems();

	/**
	 * 商品盘点条目
	 */
	public static interface GoodsCountItem {

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
		public String getGoodsCode();
		/**
		 * 获取商品条目编码
		 * 
		 * @return
		 */
		public String getGoodsNo();

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
		 * 获取商品账面数量
		 * 
		 * @return
		 */
		public double getCount();

		/**
		 * 获取商品实际数量
		 * 
		 * @return
		 */
		public double getActualCount();

		/**
		 * 获取说明
		 * 
		 * @return
		 */
		public String getRemark();
		
		/**
		 * 是否在原仓库中存在，判断是新增商品，还是原始商品
		 * @return
		 */
		public boolean isExistInventory();

	}

	/**
	 * 其他物品盘点条目
	 */
	public static interface KitCountItem {
		/**
		 * 获取物品名称
		 * 
		 * @return
		 */
		public String getKitName();

		/**
		 * 获取物品描述
		 * 
		 * @return
		 */
		public String getKitDesc();

		/**
		 * 获取物品单位
		 * 
		 * @return
		 */
		public String getKitUnit();

		/**
		 * 获取物品账面数量
		 * 
		 * @return
		 */
		public double getCount();

		/**
		 * 获取物品实际数量
		 * 
		 * @return
		 */
		public double getActualCount();

		/**
		 * 获取说明
		 * 
		 * @return
		 */
		public String getRemark();
		
		/**
		 * 是否在原仓库中存在，判断是新增物品，还是原始物品
		 * @return
		 */
		public boolean isExistInventory();

	}

}
