package com.spark.order.intf.facade;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��Ʒ�Զ�����ʷ��¼</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-12-1
 */
public interface FGoodsBillsLog {
	/**
	 * @return the cuspGuid
	 */
	public GUID getCuspGuid();
	/**
	 * @return the goodsProGuid
	 */
	public GUID getGoodsProGuid();
	/**
	 * @return the billsGuid
	 */
	public GUID getBillsGuid();
	/**
	 * @return the billsNo
	 */
	public String getBillsNo();
	/**
	 * @return the price
	 */
	public double getPrice();
	/**
	 * @return the okDate
	 */
	public long getOkDate();
}
