/**
 * 
 */
package com.spark.psi.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class SMessageInfo {
	private GUID RECID;// 行标识 guid 
	private String messageType;// 消息类型 char
	private GUID relaObjId;// 关联对象id guid
	private String templateCode;// 显示模板code char
	private String stringValue1;// 内容 varchar
	private String stringValue2;// 内容 varchar
	private String stringValue3;// 内容 varchar
 
	public GUID getRECID() {
		return RECID;
	}

	/**
	 * @param rECID
	 */
	public void setRECID(GUID rECID) {
		RECID = rECID;
	} 

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType
	 *            the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the relaObjId
	 */
	public GUID getRelaObjId() {
		return relaObjId;
	}

	/**
	 * @param relaObjId
	 *            the relaObjId to set
	 */
	public void setRelaObjId(GUID relaObjId) {
		this.relaObjId = relaObjId;
	}

	/**
	 * @return the templateCode
	 */
	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * @param templateCode
	 *            the templateCode to set
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * @return the stringValue1
	 */
	public String getStringValue1() {
		return stringValue1;
	}

	/**
	 * @param stringValue1
	 *            the stringValue1 to set
	 */
	public void setStringValue1(String stringValue1) {
		this.stringValue1 = stringValue1;
	}

	/**
	 * @return the stringValue2
	 */
	public String getStringValue2() {
		return stringValue2;
	}

	/**
	 * @param stringValue2
	 *            the stringValue2 to set
	 */
	public void setStringValue2(String stringValue2) {
		this.stringValue2 = stringValue2;
	}

	/**
	 * @return the stringValue3
	 */
	public String getStringValue3() {
		return stringValue3;
	}

	/**
	 * @param stringValue3
	 *            the stringValue3 to set
	 */
	public void setStringValue3(String stringValue3) {
		this.stringValue3 = stringValue3;
	}

}
