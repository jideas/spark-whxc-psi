package com.spark.b2c.publish.JointVenture.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * 变更联营交易记录为已结算
 *
 */
public class MakeJointRecordSettlementedTask extends SimpleTask {

	private GUID id;

	private List<GUID> idList;

	public GUID getId() {
		return id;
	}

	public MakeJointRecordSettlementedTask(GUID id) {
		this.id = id;
	}

	public List<GUID> getIdList() {
		return idList;
	}

	public MakeJointRecordSettlementedTask(List<GUID> idList) {
		this.idList = idList;
	}
}
