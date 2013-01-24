package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PromotionAction;
import com.spark.psi.publish.PromotionStatus;

/**
 * 
 * <p>�����б�</p>
 * ��ѯδȷ�ϵĴ����б� ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Unrecognized
 * ��ѯ�������Ĵ����б�  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Examine
 * ��ѯ�����еĴ����б�  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Promotion
 * ��ѯ������¼  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = null


 *
 
 * @version 2012-3-6
 */
public interface PromotionItem{
	
//	private GUID id;
//	
//	/**
//	 * ��Ʒid
//	 */
//	private GUID goodsItemId;  
//	
//	/**
//	 * ��Ʒ����
//	 */
//	private String goodsName;
//	
//	/**
//	 * ��Ʒ����
//	 */
//	private String goodsProperties;
//	
//	/**
//	 * ������Ʒ����
//	 */
//	private double promotionCount;
//	
//	/**
//	 * ԭʼ����
//	 */
//	private double originalPrice;
//	/**
//	 * ��������
//	 */
//	private double promotionPrice;
//	
//	/**
//	 * ��ʼ����
//	 */
//	private long startDate;
//	
//	/**
//	 * ��������
//	 */
//	private long endDate;
//	
//	/**
//	 * ����״̬
//	 */
//	private PromotionStatus promotionstatus;
//	
//	/**
//	 * ������
//	 */
//	private String creator; 
//	
//	/**
//	 * ��������
//	 */
//	private double soldCount;

	public GUID getId();

	public GUID getGoodsItemId();

	public String getGoodsName();

	public String getGoodsProperties();

	public double getPromotionCount();
	
	public int getScale();

	public double getOriginalPrice();

	public double getPromotionPrice();

	public long getStartDate();

	public long getEndDate();

	public PromotionStatus getPromotionStatus();

	public String getCreator();

	public double getSoldCount();
	
	public PromotionAction[] getActions();

		
}
