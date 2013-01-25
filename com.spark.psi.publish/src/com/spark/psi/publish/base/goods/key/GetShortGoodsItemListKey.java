package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey.SortField;

public class GetShortGoodsItemListKey extends LimitKey {
	
	/**
	 * ��Ʒ����id
	 */
	private GUID categoryId;
	
	/**
	 * ��Ʒ��Ŀ״̬
	 * 
	 */
	private GoodsStatus goodsstatus = GoodsStatus.PART_SALE;

	private SortField sortField;
	/**
	 * ���ؼ�����������Ʒ��Ŀ��ѯ
	 * @param gategoryId ��Ʒ����ID
	 * @param searchKey �����ؼ���
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId,String searchText){
		super(offset, count, queryTotal);
		this.categoryId = categoryId;
		this.searchText = searchText;
	}

	
	/**
	 * ���ؼ�����������Ʒ��Ŀ��ѯ
	 * @param gategoryId ��Ʒ����ID
	 * @param searchKey �����ؼ���
	 * @param goodsstatus ��Ʒ״̬ ���� or ͣ��
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId,String searchText,GoodsStatus goodsstatus){
		this(offset, count, queryTotal, categoryId,searchText);
		this.goodsstatus = goodsstatus;
	}
	
	/**
	 * ��ѯָ����Ʒ�����µ�������Ʒ��Ŀ
	 * @param gategoryId
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId){
		super(offset, count, queryTotal);
		this.categoryId = categoryId;
	}
	
	/**
	 * ��ѯָ����Ʒ�����µ�������Ʒ��Ŀ
	 * @param gategoryId
	 * @param goodsstatus ��Ʒ״̬ ���� or ͣ��
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId,GoodsStatus goodsstatus){
		super(offset, count, queryTotal);
		this.categoryId = categoryId;
		this.goodsstatus = goodsstatus;
	}

	public GUID getGategoryId(){
    	return categoryId;
    }

	public GoodsStatus getGoodsStatus(){
	    return goodsstatus;
    }


	public SortField getSortField() {
		return sortField;
	}


	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}


	
}
