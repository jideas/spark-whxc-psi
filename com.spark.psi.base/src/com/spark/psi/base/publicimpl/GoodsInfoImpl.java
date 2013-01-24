package com.spark.psi.base.publicimpl;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryInfo;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;

/**
 * ��Ʒ��ϸ��Ϣ���ݣ���������������Ϣ����������ϸ��Ŀ����Ϣ�� <BR>
 * ��ѯ˵����<br>
 * (1)������ƷID��ѯ������GoodsInfo<br>
 * (2)����GetGoodsInfoListKey��ѯ������GoodsInfo�б�
 */
public class GoodsInfoImpl implements GoodsInfo{

//	private boolean isJointVenture ;
//	private GUID supplierId;
//	private String supplier;
	private int shelfLife;
	private int warningDay;

	/**
	 * ��ƷID
	 */
	protected GUID id;

	/**
	 * ��Ʒ����
	 */
	protected String code;

	/**
	 * ��Ʒ����
	 */
	protected String name;

	/**
	 * ͳһ���ۼ۸�
	 */
	protected double salePrice;

	/**
	 * ��Ʒ״̬
	 */
	protected GoodsStatus status;

	/**
	 * ��ע
	 */
	protected String memo;

	/**
	 * ������ʶ XXX:
	 */
	protected boolean refFlag;

	/**
	 * ��������
	 */
	protected GoodsCategoryInfo category;
	/**
	 * ���Ԥ������
	 */
	protected InventoryWarningType goodsWarnningType;

	/**
	 * ��ϸ��Ŀ
	 */
	protected List<GoodsItemDataImpl> items = new ArrayList<GoodsItemDataImpl>();

	/**
	 * ��ȡ��ƷID
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * ��ȡͳһ���ۼ۸�
	 * 
	 * @return
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * ��ȡ��Ʒ״̬
	 * 
	 * @return
	 */
	public GoodsStatus getStatus() {
		return status;
	}

	/**
	 * ��ȡ��ע��Ϣ
	 * 
	 * @return
	 */
	public String getRemark() {
		return memo;
	}

	/**
	 * �Ƿ��Ѿ������õı�־
	 * 
	 * @return
	 */
	public boolean isRefFlag() {
		return refFlag;
	}

	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public GoodsCategoryInfo getCategory() {
		return category;
	}

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @return
	 */
	public GoodsItemData[] getItems() {
		return items.toArray(new GoodsItemData[items.size()]);
	}

	public void setId(GUID id){
    	this.id = id;
    }

	public void setCode(String code){
    	this.code = code;
    }

	public void setName(String name){
    	this.name = name;
    }

	public void setSalePrice(double salePrice){
    	this.salePrice = salePrice;
    }

	public void setStatus(GoodsStatus status){
    	this.status = status;
    }

	public void setMemo(String memo){
    	this.memo = memo;
    }

	public void setRefFlag(boolean refFlag){
    	this.refFlag = refFlag;
    }

	public void setCategory(GoodsCategoryInfo category){
    	this.category = category;
    }
	
	public void addItem(GoodsItemDataImpl item){
		this.items.add(item);
	}

	public void setItems(List<GoodsItemDataImpl> goodsItem){
	    this.items = goodsItem;
    }

	public InventoryWarningType getGoodsWarnningType(){
    	return goodsWarnningType;
    }

	public void setGoodsWarnningType(InventoryWarningType goodsWarnningType){
    	this.goodsWarnningType = goodsWarnningType;
    }

//	public boolean isJointVenture() {
//		return isJointVenture;
//	}
//
//	public void setJointVenture(boolean isJointVenture) {
//		this.isJointVenture = isJointVenture;
//	}

//	public GUID getSupplierId() {
//		return supplierId;
//	}
//
//	public void setSupplierId(GUID supplierId) {
//		this.supplierId = supplierId;
//	}
//
//	public String getSupplier() {
//		return supplier;
//	}
//
//	public void setSupplier(String supplier) {
//		this.supplier = supplier;
//	}

	public int getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	public int getWarningDay() {
		return warningDay;
	}

	public void setWarningDay(int warningDay) {
		this.warningDay = warningDay;
	}

	public String getMemo() {
		return memo;
	}
	
}
