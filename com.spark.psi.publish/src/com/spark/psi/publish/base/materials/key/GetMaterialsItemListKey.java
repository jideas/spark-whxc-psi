package com.spark.psi.publish.base.materials.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.MaterialsStatus;

/**
 * 
 * <p>根据材料分类id查询材料条目列表</p>
 *  递归所有子分类的商品条目
 * 
 */
public class GetMaterialsItemListKey extends LimitKey{
	
	/**
	 * 材料分类id
	 */
	private GUID categoryId;
	
	/**
	 * 材料条目状态
	 * 
	 */
	private MaterialsStatus materialsStatus = MaterialsStatus.PART_SALE;

	
	/**
	 * 带关键字搜索的材料条目查询
	 * @param gategoryId 材料分类ID
	 * @param searchKey 搜索关键字
	 */
	public GetMaterialsItemListKey(GUID categoryId,String searchText){
		super(0,0,false);
		this.categoryId = categoryId;
		this.searchText = searchText;
	}

	
	/**
	 * 带关键字搜索的材料条目查询
	 * @param gategoryId 商品分类ID
	 * @param searchKey 搜索关键字
	 * @param goodsstatus 商品状态 在售 or 停售
	 */
	public GetMaterialsItemListKey(GUID categoryId,String searchText,MaterialsStatus materialsStatus){
		this(categoryId,searchText);
		this.materialsStatus = materialsStatus;
	}
	
	/**
	 * 查询指定商品分类下的所有材料条目
	 * @param gategoryId
	 */
	public GetMaterialsItemListKey(GUID categoryId){
		super(0,0,false);
		this.categoryId = categoryId;
	}
	
	/**
	 * 查询指定材料分类下的所有材料条目
	 * @param gategoryId
	 * @param goodsstatus 材料状态 在售 or 停售
	 */
	public GetMaterialsItemListKey(GUID categoryId,MaterialsStatus materialsStatus){
		super(0,0,false);
		this.categoryId = categoryId;
		this.materialsStatus = materialsStatus;
	}

	public GUID getGategoryId(){
    	return categoryId;
    }

	public MaterialsStatus getMaterialsStatus(){
	    return materialsStatus;
    }


	
}
