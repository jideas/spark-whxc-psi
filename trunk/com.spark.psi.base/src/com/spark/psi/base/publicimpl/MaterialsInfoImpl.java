package com.spark.psi.base.publicimpl;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;

/**
 * 材料详细信息数据（包括所属分类信息和其所有明细条目的信息） <BR>
 * 查询说明：<br>
 * (1)根据材料ID查询，返回MaterialsInfo<br>
 * (2)根据GetMaterialsInfoListKey查询，返回MaterialsInfo列表
 */
public class MaterialsInfoImpl implements MaterialsInfo{

	private boolean isJointVenture ;
	private GUID supplierId;
	private String supplier;
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
	protected MaterialsStatus status;

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
	protected MaterialsCategoryInfo category;
	/**
	 * 库存预警类型
	 */
	protected InventoryWarningType materialsWarnningType;

	/**
	 * 明细条目
	 */
	protected List<MaterialsItemDataImpl> items = new ArrayList<MaterialsItemDataImpl>();
	
	private double percentage;//	提成
    
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

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
	public MaterialsStatus getStatus() {
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
	public MaterialsCategoryInfo getCategory() {
		return category;
	}

	/**
	 * 获取所有条目
	 * 
	 * @return
	 */
	public MaterialsItemData[] getItems() {
		return items.toArray(new MaterialsItemData[items.size()]);
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

	public void setStatus(MaterialsStatus status){
    	this.status = status;
    }

	public void setMemo(String memo){
    	this.memo = memo;
    }

	public void setRefFlag(boolean refFlag){
    	this.refFlag = refFlag;
    }

	public void setCategory(MaterialsCategoryInfo category){
    	this.category = category;
    }
	
	public void addItem(MaterialsItemDataImpl item){
		this.items.add(item);
	}

	public void setItems(List<MaterialsItemDataImpl> materialsItem){
	    this.items = materialsItem;
    }

	public InventoryWarningType getMaterialsWarnningType(){
    	return materialsWarnningType;
    }

	public void setMaterialsWarnningType(InventoryWarningType materialsWarnningType){
    	this.materialsWarnningType = materialsWarnningType;
    }

	public boolean isJointVenture() {
		return isJointVenture;
	}

	public void setJointVenture(boolean isJointVenture) {
		this.isJointVenture = isJointVenture;
	}

	public GUID getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

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
