package com.spark.psi.publish.smessage.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SMessageTemplateEnum;

public class SMessageItem{
	private GUID RECID;//	�б�ʶ	guid  
	private GUID relaObjId;//��������id	guid
	private SMessageTemplateEnum template;//	��ʾģ��code	char
	private String stringValue1;//	����	varchar
	private String stringValue2;//	����	varchar
	private String stringValue3;//	����	varchar	
	private boolean showFlag;//�Ѹ�����־��trueΪ�Ѹ�������falseΪδ������

	/**
	 * @return the showFlag
	 */
	public boolean isShowFlag(){
		return showFlag;
	}

	/**
	 * @param showFlag the showFlag to set
	 */
	public void setShowFlag(boolean showFlag){
		this.showFlag = showFlag;
	}

	/**
	 * @return the rECID
	 */
	public GUID getRECID(){
		return RECID;
	}

	/**
	 * @param rECID the rECID to set
	 */
	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	/**
	 * @return the relaObjId
	 */
	public GUID getRelaObjId(){
		return relaObjId;
	}

	/**
	 * @param relaObjId the relaObjId to set
	 */
	public void setRelaObjId(GUID relaObjId){
		this.relaObjId = relaObjId;
	}

	/**
	 * @return the template
	 */
	public SMessageTemplateEnum getTemplate(){
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(SMessageTemplateEnum template){
		this.template = template;
	}

	/**
	 * @return the stringValue1
	 */
	public String getStringValue1(){
		return stringValue1;
	}

	/**
	 * @param stringValue1 the stringValue1 to set
	 */
	public void setStringValue1(String stringValue1){
		this.stringValue1 = stringValue1;
	}

	/**
	 * @return the stringValue2
	 */
	public String getStringValue2(){
		return stringValue2;
	}

	/**
	 * @param stringValue2 the stringValue2 to set
	 */
	public void setStringValue2(String stringValue2){
		this.stringValue2 = stringValue2;
	}

	/**
	 * @return the stringValue3
	 */
	public String getStringValue3(){
		return stringValue3;
	}

	/**
	 * @param stringValue3 the stringValue3 to set
	 */
	public void setStringValue3(String stringValue3){
		this.stringValue3 = stringValue3;
	}

}
