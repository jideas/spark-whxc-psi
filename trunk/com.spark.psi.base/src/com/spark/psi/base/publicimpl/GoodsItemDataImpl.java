package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * ��Ʒ��ϸ��Ŀ���ݶ���<br>
 * ��ѯ��������
 */
public class GoodsItemDataImpl implements GoodsItemData {

	/**
	 * ��ĿID
	 */
	protected GUID id;
	private GUID bomId;
	/**
	 * ���ۼ۸�
	 */
	protected double salePrice;

	/**
	 * ����ɹ�����
	 */
	protected double recentPurchasePrice;

	private double standardCost;// ��׼�ɱ�;
	private double averageCost;

	/**
	 * ��Ŀ����ֵ
	 */
	protected String[] propertyValues;

	/**
	 * ��Ŀ����ֵ
	 */
	protected String propertiesWithoutUnit;

	/**
	 * ��Ŀ����ֵ
	 */
	protected String propertiesWithUnit;

	/**
	 * �ܿ��������
	 */
	protected double inventoryUpperLimit;
	/**
	 * �ܿ����������
	 */
	protected double totalStoreUpperLimit;
	/**
	 * �ܿ����������
	 */
	protected double totalStoreLowerLimit;

	/**
	 * ״̬
	 */
	protected GoodsStatus status;

	/**
	 * ������ʶ
	 */
	protected boolean refFlag;

	/**
	 * ����С��λ
	 */
	protected int countDecimal;

	private double originalPrice;
	private String spec;

	public String goodsItemNo;

	public double lossRate;

	private String serialNumber;

	private double halfkgPrice;

	public double getHalfkgPrice() {
		return halfkgPrice;
	}

	public void setHalfkgPrice(double halfkgPrice) {
		this.halfkgPrice = halfkgPrice;
	}

	public String getUnit() {
		return this.getPropertyValues()[0];
	}

	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	public GUID getBomId() {
		return bomId;
	}

	public void setBomId(GUID bomId) {
		this.bomId = bomId;
	}

	/**
	 * 
	 * @return
	 */

	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getRecentPurchasePrice() {
		return recentPurchasePrice;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getPropertyValues() {
		return propertyValues;
	}

	/**
	 * 
	 * @return
	 */
	public String getPropertiesWithoutUnit() {
		return propertiesWithoutUnit;
	}

	/**
	 * 
	 * @return
	 */
	public String getPropertiesWithUnit() {
		return propertiesWithUnit;
	}

	/**
	 * 
	 * @return
	 */
	public double getInventoryUpperLimit() {
		return inventoryUpperLimit;
	}

	/**
	 * 
	 * @return
	 */
	public GoodsStatus getStatus() {
		return status;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRefFlag() {
		return refFlag;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getGoodsItemNo() {
		return goodsItemNo;
	}

	public void setGoodsItemNo(String goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}

	public double getLossRate() {
		return lossRate;
	}

	public void setLossRate(double lossRate) {
		this.lossRate = lossRate;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public void setRecentPurchasePrice(double recentPurchasePrice) {
		this.recentPurchasePrice = recentPurchasePrice;
	}

	public void setPropertyValues(String[] propertyValues) {
		this.propertyValues = propertyValues;
	}

	public void setPropertiesWithoutUnit(String propertiesWithoutUnit) {
		this.propertiesWithoutUnit = propertiesWithoutUnit;
	}

	public void setPropertiesWithUnit(String propertiesWithUnit) {
		this.propertiesWithUnit = propertiesWithUnit;
	}

	public void setInventoryUpperLimit(double inventoryUpperLimit) {
		this.inventoryUpperLimit = inventoryUpperLimit;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public void setRefFlag(boolean refFlag) {
		this.refFlag = refFlag;
	}

	public int getScale() {
		return GoodsScale.PSI_SCALE;
	}

	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}

	public double getTotalStoreUpperLimit() {
		return totalStoreUpperLimit;
	}

	public void setTotalStoreUpperLimit(double totalStoreUpperLimit) {
		this.totalStoreUpperLimit = totalStoreUpperLimit;
	}

	public double getTotalStoreLowerLimit() {
		return totalStoreLowerLimit;
	}

	public void setTotalStoreLowerLimit(double totalStoreLowerLimit) {
		this.totalStoreLowerLimit = totalStoreLowerLimit;
	}

	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}

	public double getStandardCost() {
		return standardCost;
	}

	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}

	public double getAverageCost() {
		return averageCost;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
