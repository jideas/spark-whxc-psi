package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;


/**
 * <p>零售获得出现商品价格</p>
 * <result>Double</result>
 *
 *场景一： 
1、总经理商品A促销，促销价格为200 
     销售经理对商品A也促销，促销价格是180 
2、总经理零售商品A，添加零售商品界面该商品的价格为200 

场景二： 
1、只有销售经理对商品B进行促销，促销价格200 
2、总经理零售商品B，添加零售商品界面该商品的价格为"销售价格" 

场景三：
1、总经理对商品A促销，促销价格为200； 
   销售经理对商品A也促销，促销价格是180 
2、销售员工零售该商品，添加零售商品界面显示的价格应该为180 



当商品不存在任何促销价格时，显示销售价格；
当商品仅存在一个促销价格时，显示该促销价格；
当商品的公司促销价格和其各级上级部门经理所设定的促销价格总数大于2个时，按照其所属部门的组织机构，显示其中最末一级部门经理所设定的促销价格；
总经理在创建零售订单时，若该商品存在公司促销价格，则显示该促销价格，若该商品不存在公司促销价格时，则显示其销售价格。
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-7-9
 */
public class GetPromotionPriceByRetailKey{
	private final GUID goodsItemId;

	/**
	 * @param goodsItemId
	 */
	public GetPromotionPriceByRetailKey(GUID goodsItemId) {
		super();
		this.goodsItemId = goodsItemId;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}
	
}
