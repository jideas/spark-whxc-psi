package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>������Ʒ����id��ѯ��Ʒ��Ŀ�б�</p>
 *  �ݹ������ӷ������Ʒ��Ŀ


 *
 
 * @version 2012-3-12
 */
public class GetGoodsItemListKey extends LimitKey{
	
	/**
	 * ��Ʒ����id
	 */
	private GUID categoryId;
	
	/**
	 * ��Ʒ��Ŀ״̬
	 * 
	 */
	private GoodsStatus goodsstatus = GoodsStatus.PART_SALE;

	
	/**
	 * ���ؼ�����������Ʒ��Ŀ��ѯ
	 * @param gategoryId ��Ʒ����ID
	 * @param searchKey �����ؼ���
	 */
	public GetGoodsItemListKey(GUID categoryId,String searchText){
		super(0,0,false);
		this.categoryId = categoryId;
		this.searchText = searchText;
	}

	
	/**
	 * ���ؼ�����������Ʒ��Ŀ��ѯ
	 * @param gategoryId ��Ʒ����ID
	 * @param searchKey �����ؼ���
	 * @param goodsstatus ��Ʒ״̬ ���� or ͣ��
	 */
	public GetGoodsItemListKey(GUID categoryId,String searchText,GoodsStatus goodsstatus){
		this(categoryId,searchText);
		this.goodsstatus = goodsstatus;
	}
	
	/**
	 * ��ѯָ����Ʒ�����µ�������Ʒ��Ŀ
	 * @param gategoryId
	 */
	public GetGoodsItemListKey(GUID categoryId, int offset, int count, boolean queryTotal){
		super(offset,count,false);
		this.categoryId = categoryId;
	}
	
	/**
	 * ��ѯָ����Ʒ�����µ�������Ʒ��Ŀ
	 * @param gategoryId
	 * @param goodsstatus ��Ʒ״̬ ���� or ͣ��
	 */
	public GetGoodsItemListKey(GUID categoryId,GoodsStatus goodsstatus){
		super(0,0,false);
		this.categoryId = categoryId;
		this.goodsstatus = goodsstatus;
	}

	public GUID getGategoryId(){
    	return categoryId;
    }

	public GoodsStatus getGoodsStatus(){
	    return goodsstatus;
    }


	
}
