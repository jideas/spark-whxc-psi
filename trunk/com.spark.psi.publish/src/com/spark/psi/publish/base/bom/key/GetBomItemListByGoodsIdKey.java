package com.spark.psi.publish.base.bom.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯBomItem����BomHistoryItem�б�
 */
public class GetBomItemListByGoodsIdKey {

	private GUID id;

	public GetBomItemListByGoodsIdKey(GUID id) {
		this.id = id;
	}

	public GUID getId() {
		return this.id;
	}
}
