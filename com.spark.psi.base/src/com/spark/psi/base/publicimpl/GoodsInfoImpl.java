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
 * 商品详细信息数据（包括所属分类信息和其所有明细条目的信息） <BR>
 * 查询说明：<br>
 * (1)根据商品ID查询，返回GoodsInfo<br>
 * (2)根据GetGoodsInfoListKey查询，返回GoodsInfo列表
 */
public class GoodsInfoImpl implements GoodsInfo{

//	private boolean isJointVenture ;
//	private GUID supplierId;
//	private String supplier;
	private int shelfLife;
	private int warningDay;

	/**
	 * 商品ID
	 */
	protected GUID id;

	/**
	 * 商品代码
	 */
	protected String code;

	/**
	 * 商品名称
	 */
	protected String name;

	/**
	 * 统一销售价格
	 */
	protected double salePrice;

	/**
	 * 商品状态
	 */
	protected GoodsStatus status;

	/**
	 * 备注
	 */
	protected String memo;

	/**
	 * 关联标识 XXX:
	 */
	protected boolean refFlag;

	/**
	 * 所属分类
	 */
	protected GoodsCategoryInfo category;
	/**
	 * 库存预警类型
	 */
	protected InventoryWarningType goodsWarnningType;

	/**
	 * 明细条目
	 */
	protected List<GoodsItemDataImpl> items = new ArrayList<GoodsItemDataImpl>();

	/**
	 * 获取商品ID
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 获取商品代码
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取商品名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取统一销售价格
	 * 
	 * @return
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * 获取商品状态
	 * 
	 * @return
	 */
	public GoodsStatus getStatus() {
		return status;
	}

	/**
	 * 获取备注信息
	 * 
	 * @return
	 */
	public String getRemark() {
		return memo;
	}

	/**
	 * 是否已经被引用的标志
	 * 
	 * @return
	 */
	public boolean isRefFlag() {
		return refFlag;
	}

	/**
	 * 获取商品分类
	 * 
	 * @return
	 */
	public GoodsCategoryInfo getCategory() {
		return category;
	}

	/**
	 * 获取所有条目
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
