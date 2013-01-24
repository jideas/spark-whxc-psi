package com.spark.oms.publish.message.key;

import com.jiuqi.dna.core.type.GUID;

public class GetMegChannelAndConfigByMsgChannelIdKey {

	private GUID RECID;
	
	private String code;

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}