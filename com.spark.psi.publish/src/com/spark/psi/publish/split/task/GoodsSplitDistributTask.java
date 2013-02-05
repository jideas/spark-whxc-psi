package com.spark.psi.publish.split.task;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class GoodsSplitDistributTask extends SimpleTask {

	private GUID billId;
	private String billNo;
	private String remark;
	private List<GoodsSplitDistributeEntity> list;

	public List<GoodsSplitDistributeEntity> getList() {
		return list;
	}

	public GoodsSplitDistributTask(GUID billId,String billNo,String remark,List<GoodsSplitDistributeEntity> list) {
		this.list = list;
		this.billId = billId;
		this.billNo = billNo;
		this.remark = remark;
	}

	public GUID getBillId() {
		return billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public String getRemark() {
		return remark;
	}

}
