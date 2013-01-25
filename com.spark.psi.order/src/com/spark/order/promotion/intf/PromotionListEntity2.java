package com.spark.order.promotion.intf;

import java.util.List;

/**
 * <p>�����б���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
public class PromotionListEntity2 {
	private final List<Promotion2> list;
	private final int count;//����
	
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
