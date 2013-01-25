package com.spark.psi.publish.base.goods.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>根据商品分类id查询商品条目列表</p>
 *  递归所有子分类的商品条目


 *
 
 * @version 2012-3-12
 */
public class GetGoodsItemListKey extends LimitKey{
	
	/**
	 * 商品分类id
	 */
	private GUID categoryId;
	
	/**
	 * 商品条目状态
	 * 
	 */
	private GoodsStatus goodsstatus = GoodsStatus.PART_SALE;

	
	/**
	 * 带关键字搜索的商品条目查询
	 * @param gategoryId 商品分类ID
	 * @param searchKey 搜索关键字
	 */
	public GetGoodsItemListKey(GUID categoryId,String searchText){
		super(0,0,false);
		this.categoryId = categoryId;
		this.searchText = searchText;
	}

	
	/**
	 * 带关键字搜索的商品条目查询
	 * @param gategoryId 商品分类ID
	 * @param searchKey 搜索关键字
	 * @param goodsstatus 商品状态 在售 or 停售
	 */
	public GetGoodsItemListKey(GUID categoryId,String searchText,GoodsStatus goodsstatus){
		this(categoryId,searchText);
		this.goodsstatus = goodsstatus;
	}
	
	/**
	 * 查询指定商品分类下的所有商品条目
	 * @param gategoryId
	 */
	public GetGoodsItemListKey(GUID categoryId, int offset, int count, boolean queryTotal){
		super(offset,count,false);
		this.categoryId = categoryId;
	}
	
	/**
	 * 查询指定商品分类下的所有商品条目
	 * @param gategoryId
	 * @param goodsstatus 商品状态 在售 or 停售
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
