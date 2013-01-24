/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.inventoryenum
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-23       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum;

/**
 * <p>商品拆装明细类型</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-23
 */

public enum RefactorGoodsItemType {

	Source("1","源"),
	Destination("2","目的");
	
	private String code;
	private String name;
	
	private RefactorGoodsItemType(String code,String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public RefactorGoodsItemType getEnum(String code)
	{
		RefactorGoodsItemType typeEnum = null;
		if(RefactorGoodsItemType.Source.getCode().equals(code))
		{
			typeEnum = RefactorGoodsItemType.Source;
		}
		else if(RefactorGoodsItemType.Destination.getCode().equals(code))
		{
			typeEnum = RefactorGoodsItemType.Destination;
		}
		return typeEnum;
	}
}
