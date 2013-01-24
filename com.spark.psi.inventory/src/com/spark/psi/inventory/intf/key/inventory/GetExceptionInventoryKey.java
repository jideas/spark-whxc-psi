/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-14       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

/**
 * 查询库存异常的商品的库存信息
 * 异常商品：库存数量高于库存上限 or 库存数量低于库存下限 or 库存金额高于库存上限金额
 * @author zhongxin
 *
 */
public class GetExceptionInventoryKey {
	private String sortCloumName;//排序
    
    private String sortType;//排序类型（升序/降序）
    
	public String getSortCloumName() {
		return sortCloumName;
	}
	public void setSortCloumName(String sortCloumName) {
		this.sortCloumName = sortCloumName;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	
	
}
