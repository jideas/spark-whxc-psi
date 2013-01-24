package com.spark.psi.base.browser;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoListKey;

public class MaterialCommon {
	public static boolean isCategoryContainMaterial(Context context, GUID goodsCategoryId) {
		GetMaterialsInfoListKey key = new GetMaterialsInfoListKey();
		key.setCateoryId(goodsCategoryId);
		@SuppressWarnings("unchecked")
		ListEntity<MaterialsInfo> listEntity = (ListEntity<MaterialsInfo>) context
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
		MaterialsCategoryTree categoryTeee = context.find(MaterialsCategoryTree.class);
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
		MaterialsCategoryTree categoryTeee = context.find(MaterialsCategoryTree.class);
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
}
