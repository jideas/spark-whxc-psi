package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Goods;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.utils.GoodsProperyUtil;
import com.spark.psi.publish.GoodsStatus;

@StructClass
public class GoodsItemImpl implements GoodsItem {

	private GUID id;//	��ʶ
	private String goodsCode;//	��Ʒ��Ŀ����
	private String  goodsNo;//	��Ʒ��Ŀ����
	private String  goodsName;//	��Ʒ��Ŀ����
	private String  namePY;//	����ƴ��
	private GUID categoryId;//	��Ʒ����GUID
	private GoodsCategory category;
	private String  spec;//	���
	private int scale;//	����
	private boolean needProduce;//	�Ƿ���Ҫ����
	private String inventoryStrategy ;//	������
	private String goodsUnit;//	��Ʒ��λ
	private double avgCost;//	ʵ�ʳɱ�
	private double standardCost;//	��׼�ɱ�
	private double assessCost;//	���˳ɱ�
	private int shelfLife;//	������
	private int warningDay;//	Ԥ������
	private double salePrice;//	���ۼ۸�
	private double originalPrice;//	ԭ��
	private double lossRate ;//	�����
	private GoodsStatus status;//	��Ʒ״̬
	private boolean canDelete;//	�Ƿ����ɾ��
	private boolean refFlag	;//������ʶ
	private long createDate;//	���ݲ���ʱ��
	private long lastModifyDate;//	��������޸�ʱ��
	private String lastModifyPerson;//	��������޸���
	private String[] goodsProperties;//	��������ֵ
	private GUID bomId;//	bom��־
	private GUID creatorId;//	������Guid
	private String creator;//	������
	private GUID goodsId;//	��Ʒid
	private Goods goods;
	private String serialNumber;
//	private boolean jointVenture;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public GUID getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(GUID categoryId) {
		this.categoryId = categoryId;
	}
	public GoodsCategory getCategory() {
		return category;
	}
	public void setCategory(GoodsCategory category) {
		this.category = category;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public boolean isNeedProduce() {
		return needProduce;
	}
	public void setNeedProduce(boolean needProduce) {
		this.needProduce = needProduce;
	}
	public String getInventoryStrategy() {
		return inventoryStrategy;
	}
	public void setInventoryStrategy(String inventoryStrategy) {
		this.inventoryStrategy = inventoryStrategy;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public double getAvgCost() {
		return avgCost;
	}
	public void setAvgCost(double avgCost) {
		this.avgCost = avgCost;
	}
	public double getStandardCost() {
		return standardCost;
	}
	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}
	public double getAssessCost() {
		return assessCost;
	}
	public void setAssessCost(double assessCost) {
		this.assessCost = assessCost;
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
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
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
	public GoodsStatus getStatus() {
		return status;
	}
	public void setStatus(GoodsStatus status) {
		this.status = status;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public boolean isRefFlag() {
		return refFlag;
	}
	public void setRefFlag(boolean refFlag) {
		this.refFlag = refFlag;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(long lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifyPerson() {
		return lastModifyPerson;
	}
	public void setLastModifyPerson(String lastModifyPerson) {
		this.lastModifyPerson = lastModifyPerson;
	}
	public String[] getGoodsProperties() {
		return goodsProperties;
	}
	public void setGoodsProperties(String[] goodsProperties) {
		this.goodsProperties = goodsProperties;
	}
	public GUID getBomId() {
		return bomId;
	}
	public void setBomId(GUID bomId) {
		this.bomId = bomId;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getPropertiesWithoutUnit() {
		return this.spec;
//		return GoodsProperyUtil.splitPropertyWithoutUnit(GoodsProperyUtil.subGoodsPropertyToString(this.goodsProperties));
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
//	public void setJointVenture(boolean jointVenture) {
//		this.jointVenture = jointVenture;
//	}
//	public boolean isJointVenture() {
//		return jointVenture;
//	}

}
