/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.finance.invoice.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.entity.invoice;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��Ʊ�Ǽ�ʵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2011-11-28
 */

public class Invoice {

	private GUID RECID;//	�б�ʶ
	private GUID tenantsGuid;//	�⻧GUID
	private GUID cusproGuid;//	�ͻ�GUID
	private String cusproName;//	�ͻ����
	private String cusproNamePY;//	�ͻ����ƴ��
	private String cusproFullName;//	�ͻ�ȫ��
	private String cusproFullNamePY;//	�ͻ�ȫ��ƴ��
	private String invoType;//	��Ʊ����
	private String invoCode;//	��Ʊ��
	private double invoAmount;//	��Ʊ���
	private GUID invoPerson;//	��Ʊ��GUID
	private String invoPersonName;//	��Ʊ��
	private long invoDate;//	��Ʊ����
	private boolean isInvalided;//	�Ƿ�������
	private String invalidReason;//	����ԭ��
	private String invalidPerson;//	������
	private long invalidDate;//	��������
	private String createPerson;//	������
	private GUID deptGuid;//	��������
	private long createDate;
	private GUID cusDeptGuid;

	
	
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public GUID getDeptGuid() {
		return deptGuid;
	}
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public GUID getCusproGuid() {
		return cusproGuid;
	}
	public void setCusproGuid(GUID cusproGuid) {
		this.cusproGuid = cusproGuid;
	}
	public String getCusproName() {
		return cusproName;
	}
	public void setCusproName(String cusproName) {
		this.cusproName = cusproName;
	}
	public String getCusproNamePY() {
		return cusproNamePY;
	}
	public void setCusproNamePY(String cusproNamePY) {
		this.cusproNamePY = cusproNamePY;
	}
	public String getCusproFullName() {
		return cusproFullName;
	}
	public void setCusproFullName(String cusproFullName) {
		this.cusproFullName = cusproFullName;
	}
	public String getCusproFullNamePY() {
		return cusproFullNamePY;
	}
	public void setCusproFullNamePY(String cusproFullNamePY) {
		this.cusproFullNamePY = cusproFullNamePY;
	}
	public String getInvoType() {
		return invoType;
	}
	public void setInvoType(String invoType) {
		this.invoType = invoType;
	}
	public String getInvoCode() {
		return invoCode;
	}
	public void setInvoCode(String invoCode) {
		this.invoCode = invoCode;
	}
	public double getInvoAmount() {
		return invoAmount;
	}
	public void setInvoAmount(double invoAmount) {
		this.invoAmount = invoAmount;
	}
	public GUID getInvoPerson() {
		return invoPerson;
	}
	public void setInvoPerson(GUID invoPerson) {
		this.invoPerson = invoPerson;
	}
	public String getInvoPersonName() {
		return invoPersonName;
	}
	public void setInvoPersonName(String invoPersonName) {
		this.invoPersonName = invoPersonName;
	}
	public long getInvoDate() {
		return invoDate;
	}
	public void setInvoDate(long invoDate) {
		this.invoDate = invoDate;
	}
	public boolean isInvalided() {
		return isInvalided;
	}
	public void setInvalided(boolean isInvalided) {
		this.isInvalided = isInvalided;
	}
	public String getInvalidReason() {
		return invalidReason;
	}
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}
	public String getInvalidPerson() {
		return invalidPerson;
	}
	public void setInvalidPerson(String invalidPerson) {
		this.invalidPerson = invalidPerson;
	}
	public long getInvalidDate() {
		return invalidDate;
	}
	public void setInvalidDate(long invalidDate) {
		this.invalidDate = invalidDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCusDeptGuid(GUID cusDeptGuid) {
		this.cusDeptGuid = cusDeptGuid;
	}
	public GUID getCusDeptGuid() {
		return cusDeptGuid;
	}

}
