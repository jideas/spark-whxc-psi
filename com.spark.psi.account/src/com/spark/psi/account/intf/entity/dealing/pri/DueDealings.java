/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.account.intf.entity.dealing.pri
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-30       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.entity.dealing.pri;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>Ӧ��/��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-30
 */

public class DueDealings {

	private GUID id;
	private String cusproShortName;
	/**
	 * ���ö��
	 */
	private double creditline;
	/**
	 * Ԥ������
	 */
	private int accountRemind;
	/**
	 * Ӧ��/�����
	 */
	private double amount;
	/**
	 * ����
	 */
	private int accountPeriod;
	private String cusproName;
	/**
	 * �ͻ� or ��Ӧ��
	 */
	private String type;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getCusproShortName() {
		return cusproShortName;
	}
	public void setCusproShortName(String cusproShortName) {
		this.cusproShortName = cusproShortName;
	}
	/**
	 * ���ö��
	 */
	public double getCreditline() {
		return creditline;
	}
	/**
	 * ���ö��
	 */
	public void setCreditline(double creditline) {
		this.creditline = creditline;
	}
	/**
	 * Ԥ������
	 */
	public int getAccountRemind() {
		return accountRemind;
	}
	/**
	 * Ԥ������
	 */
	public void setAccountRemind(int accountRemind) {
		this.accountRemind = accountRemind;
	}
	/**
	 * Ӧ��/�����
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * Ӧ��/�����
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * ����
	 */
	public int getAccountPeriod() {
		return accountPeriod;
	}
	/**
	 * ����
	 */
	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	public String getCusproName() {
		return cusproName;
	}
	public void setCusproName(String cusproName) {
		this.cusproName = cusproName;
	}
	/**
	 * �ͻ� or ��Ӧ��
	 */
	public String getType() {
		return type;
	}
	/**
	 * �ͻ� or ��Ӧ��
	 */
	public void setType(String type) {
		this.type = type;
	}
}
