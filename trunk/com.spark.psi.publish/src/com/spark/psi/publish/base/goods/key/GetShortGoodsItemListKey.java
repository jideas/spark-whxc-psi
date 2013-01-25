package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey.SortField;

public class GetShortGoodsItemListKey extends LimitKey {
	
	/**
	 * 商品分类id
	 */
	private GUID categoryId;
	
	/**
	 * 商品条目状态
	 * 
	 */
	private GoodsStatus goodsstatus = GoodsStatus.PART_SALE;

	private SortField sortField;
	/**
	 * 带关键字搜索的商品条目查询
	 * @param gategoryId 商品分类ID
	 * @param searchKey 搜索关键字
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId,String searchText){
		super(offset, count, queryTotal);
		this.categoryId = categoryId;
		this.searchText = searchText;
	}

	
	/**
	 * 带关键字搜索的商品条目查询
	 * @param gategoryId 商品分类ID
	 * @param searchKey 搜索关键字
	 * @param goodsstatus 商品状态 在售 or 停售
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId,String searchText,GoodsStatus goodsstatus){
		this(offset, count, queryTotal, categoryId,searchText);
		this.goodsstatus = goodsstatus;
	}
	
	/**
	 * 查询指定商品分类下的所有商品条目
	 * @param gategoryId
	 */
	public GetShortGoodsItemListKey(int offset, int count, boolean queryTotal, GUID categoryId){
		super(offset, count, queryTotal);
		this.categoryId = categoryId;
	}
	
	/**
	 * 查询指定商品分类下的所有商品条目
	 * @param gategoryId
	 * @param goodsstatus 商品状态 在售 or 停售
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
