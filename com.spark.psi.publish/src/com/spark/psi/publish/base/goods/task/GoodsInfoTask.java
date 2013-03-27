package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * �������߸�����Ʒ���ࣨ��������Ŀ��
 * 
 */
public class GoodsInfoTask extends Task<GoodsInfoTask.Method> {

	public enum Method {
		Create, Update
	}
	
	public enum ItemMethod{
		/**�༭*/
		Update,
		/**ɾ��*/
		Delete
	}

	/**
	 * ��ƷID
	 */
	private GUID id;

	/**
	 * ��Ʒ����
	 */
	private String code;

	/**
	 * ��Ʒ����
	 */
	private String name;

	/**
	 * ͳһ���ۼ۸�
	 */
	private double salePrice;

	/**
	 * ��ע
	 */
	private String remark;

	/**
	 * ��������ID
	 */
	private GUID categoryId;
	
	private boolean jointVenture;
	private GUID supplierId;//	��Ӫ��Ӧ��
	private int shelfLife;//	������
	private int warningDay;//	Ԥ������

	/**
	 * ��ϸ��Ŀ
	 */
	private Item[] items;

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the memo
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the memo to set
	 */
	public void seRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the categoryId
	 */
	public GUID getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(GUID categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isJointVenture() {
		return jointVenture;
	}

	public void setJointVenture(boolean jointVenture) {
		this.jointVenture = jointVenture;
	}

	public GUID getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
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

	/**
	 * @return the items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Item[] items) {
		this.items = items;
	}

	/**
	 * ��Ŀ
	 */
	public final static class Item {
		
		private ItemMethod method = ItemMethod.Update;
		
		/**
		 * ��ĿID
		 */
		private GUID id;

		/**
		 * ���ۼ۸�
		 */
		private double salePrice;
		private double standardCost;//	��׼�ɱ�;

		/**
		 * ��Ŀ����ֵ
		 */
		private String[] propertyValues;

		/**
		 * ״̬
		 */
		private boolean onsale;
		
		
		private String unit;
		
		private String goodsNo;//����
		private double originalPrice;//	ԭ��
		private double lossRate;// 	�����
		private String goodsSpec;//���
		private double halfkgPrice;
		
		public double getHalfkgPrice() {
			return halfkgPrice;
		}
		public void setHalfkgPrice(double halfkgPrice) {
			this.halfkgPrice = halfkgPrice;
		}

		/**
		 * @return the id
		 */
		public GUID getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(GUID id) {
			this.id = id;
		}

		/**
		 * @return the salePrice
		 */
		public double getSalePrice() {
			return salePrice;
		}

		/**
		 * @param salePrice
		 *            the salePrice to set
		 */
		public void setSalePrice(double salePrice) {
			this.salePrice = salePrice;
		}

		/**
		 * @return the propertyValues
		 */
		public String[] getPropertyValues() {
			return propertyValues;
		}

		/**
		 * @param propertyValues
		 *            the propertyValues to set
		 */
		public void setPropertyValues(String[] propertyValues) {
			this.propertyValues = propertyValues;
		}

		/**
		 * @return the onsale
		 */
		public boolean isOnsale() {
			return onsale;
		}

		/**
		 * @param onsale
		 *            the onsale to set
		 */
		public void setOnsale(boolean onsale) {
			this.onsale = onsale;
		}

		public String getGoodsNo() {
			return goodsNo;
		}

		public void setGoodsNo(String goodsNo) {
			this.goodsNo = goodsNo;
		}

		public double getOriginalPrice() {
			return originalPrice;
		}

		public void setOriginalPrice(double originalPrice) {
			this.originalPrice = originalPrice;
		}

		public double getLossRate() {
			return lossRate;
		}

		public void setLossRate(double lossRate) {
			this.lossRate = lossRate;
		}

		public String getGoodsSpec() {
			return goodsSpec;
		}

		public void setGoodsSpec(String goodsSpec) {
			this.goodsSpec = goodsSpec;
		}

		public ItemMethod getMethod(){
        	return method;
        }

		public void setMethod(ItemMethod method){
        	this.method = method;
        }

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public void setStandardCost(double standardCost) {
			this.standardCost = standardCost;
		}

		public double getStandardCost() {
			return standardCost;
		}

		
		
	}

}
