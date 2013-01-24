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
 * ������ϸ��Ϣ���ݣ���������������Ϣ����������ϸ��Ŀ����Ϣ�� <BR>
 * ��ѯ˵����<br>
 * (1)���ݲ���ID��ѯ������MaterialsInfo<br>
 * (2)����GetMaterialsInfoListKey��ѯ������MaterialsInfo�б�
 */
public class MaterialsInfoImpl implements MaterialsInfo{

	private boolean isJointVenture ;
	private GUID supplierId;
	private String supplier;
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
	protected MaterialsStatus status;

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
	protected MaterialsCategoryInfo category;
	/**
	 * ���Ԥ������
	 */
	protected InventoryWarningType materialsWarnningType;

	/**
	 * ��ϸ��Ŀ
	 */
	protected List<MaterialsItemDataImpl> items = new ArrayList<MaterialsItemDataImpl>();
	
	private double percentage;//	���
    
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

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
	public MaterialsStatus getStatus() {
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
	public MaterialsCategoryInfo getCategory() {
		return category;
	}

	/**
	 * ��ȡ������Ŀ
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
