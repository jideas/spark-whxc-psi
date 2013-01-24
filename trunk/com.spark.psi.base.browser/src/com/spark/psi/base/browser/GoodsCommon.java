package com.spark.psi.base.browser;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;

public class GoodsCommon {
	
	public static boolean isCategoryContainGoods(Context context, GUID goodsCategoryId) {
		GetGoodsInfoListKey key = new GetGoodsInfoListKey();
		key.setCateoryId(goodsCategoryId);
		@SuppressWarnings("unchecked")
		ListEntity<GoodsInfo> listEntity = (ListEntity<GoodsInfo>) context
				.find(ListEntity.class, key);
		if(listEntity.getTotalCount() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isExistCategoryName(Context context, GUID parentNodeId,  String categoryName) {
		return isExistCategoryName(context, parentNodeId, categoryName, null);
	}
	
	public static boolean isExistCategoryName(Context context, GUID parentNodeId,  String categoryName, GUID categoryGuid) {
		GoodsCategoryTree categoryTeee = context.find(GoodsCategoryTree.class);
		CategoryNode[] nodes;
		if(null == parentNodeId) {
			nodes = categoryTeee.getRootNodes();
		} else {
			CategoryNode parentNode = categoryTeee.getNodeById(parentNodeId);
			nodes = parentNode.getChildren();
		}
		if(nodes != null) {
			for(CategoryNode node : nodes) {
				if(node.getId().equals(categoryGuid)) { //不比较自己
					continue;
				}
				if(node.getName().equals(categoryName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static final boolean isExistCategoryCode(Context context, GUID parentNodeId, String categoryNo) {
		GoodsCategoryTree categoryTeee = context.find(GoodsCategoryTree.class);
		CategoryNode[] nodes = new CategoryNode[0];
		if(null == parentNodeId) {
			nodes = categoryTeee.getRootNodes();
		} else {
			CategoryNode parentNode = categoryTeee.getNodeById(parentNodeId);
			nodes = parentNode.getChildren();
		}
		if(nodes != null) {
			for(CategoryNode node : nodes) {
				if(node.getCategoryNo().equals(categoryNo)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public enum GoodsInventoryScope {
		Total("总库存"),
		EachStore("分仓库");
		private String title;
		private GoodsInventoryScope(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return title;
		}
		
		public static GoodsInventoryScope getScopeByName(String name) {
			if(GoodsInventoryScope.Total.name().equals(name)) {
				return GoodsInventoryScope.Total;
			} else if(GoodsInventoryScope.EachStore.name().equals(name)){
				return GoodsInventoryScope.EachStore;
			} else {
				return null;
			}
		}
	}
}
