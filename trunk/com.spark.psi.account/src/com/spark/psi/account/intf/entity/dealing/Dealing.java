/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.account.intf.entity.dealing
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-13       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.entity.dealing;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class Dealing {
		
	/**
	 * Ӧ��/Ӧ��
	 */
	private double amount;	
	/**
	 * ��ʼ��Ӧ��/Ӧ��
	 */
	private double initAmount;
	/**
	 * recid
	 */
	private GUID id;
	/**
	 * partnerId
	 */
	private GUID partnerId;
	/**
	 * ����
	 */
	private String partnerName;
	
	private String namePY;
	/**
	 * ���
	 */
	private String partnerShortName;
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerShortName() {
		return partnerShortName;
	}
	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}
	/**
	 * �ͻ� or ��Ӧ��
	 */
	private String type;
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getInitAmount() {
		return initAmount;
	}
	public void setInitAmount(double initAmount) {
		this.initAmount = initAmount;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getId() {
		return id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getNamePY() {
		return namePY;
	}	

	

}
