package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ȡ��ָ�����ε����еĻ�λ��Ϣ��ֻ�е�����Ļ�λ��Ϣ��
 * @author Administrator
 *
 */
public class GetAllocateItemDetBySheetIdKey {
	private GUID sheetId;
	public GetAllocateItemDetBySheetIdKey(GUID sheetId) {
		this.sheetId = sheetId;
	}
	
	public GUID getSheetId() {
		return this.sheetId;
	}
}
