package com.spark.order.intf.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ʵ�常��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public class EntityFather {
	@StructField
	protected GUID recid;//��ʶ
	@StructField
	protected GUID tenantsId;//�⻧ID
	@StructField
	protected GUID creatorId;//������ID
	@StructField
	protected String creator;//����������
	@StructField
	protected long createDate;//����ʱ��
	/**
	 * @return the recid
	 */
	public GUID getRecid() {
		return recid;
	}
	/**
	 * @param recid the recid to set
	 */
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	/**
	 * @return the tenantsId
	 */
	public GUID getTenantsId() {
		return tenantsId;
	}
	/**
	 * @param tenantsId the tenantsId to set
	 */
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	/**
	 * @return the creatorId
	 */
	public GUID getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the createDate
	 */
	public long getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	private final static SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * ���磺����(2012-06-06)
	 * @return String
	 */
	public String getCreatorLable(){
		return creator+getDateLable(true, createDate);
	}
	
	/**
	 * ���ʱ����ʾ�ַ���
	 * @param haveBrackets �Ƿ�������
	 * @param date ʱ��
	 * @return String
	 */
	protected String getDateLable(boolean haveBrackets, long date){
		if(haveBrackets){
			return "("+defaultFormat.format(new Date(date))+")";
		}else{
			return defaultFormat.format(new Date(date));
		}
	}
	
}
