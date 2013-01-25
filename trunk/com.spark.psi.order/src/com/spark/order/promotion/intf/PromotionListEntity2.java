package com.spark.order.promotion.intf;

import java.util.List;

/**
 * <p>促销列表用</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
public class PromotionListEntity2 {
	private final List<Promotion2> list;
	private final int count;//数量
	
	/**
	 * @param list
	 * @param count
	 */
	public PromotionListEntity2(List<Promotion2> list, int count) {
		super();
		this.list = list;
		this.count = count;
	}
	public List<Promotion2> getList() {
		return list;
	}
	public int getCount() {
		return count;
	}
}
