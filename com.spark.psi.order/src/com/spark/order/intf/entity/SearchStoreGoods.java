/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       Ī��        
 * ============================================================*/

package com.spark.order.intf.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ�ѳ������Ʒʵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-11
 */

public class SearchStoreGoods {

	/**
	 * ��ƷGUID
	 */
	private GUID goodsGuid;
	private String goodsNo;
	private String goodsName;
	private String goodsAttr;
	private String unit;
	private double recentPrice;
	/**
	 * ��Ʒ����
	 */
	private double totalNum;
	
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	public double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(double totalNum) {
		this.totalNum = totalNum;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit() {
		return unit;
	}
	public void setRecentPrice(double recentPrice) {
		this.recentPrice = recentPrice;
	}
	public double getRecentPrice() {
		return recentPrice;
	}
}
