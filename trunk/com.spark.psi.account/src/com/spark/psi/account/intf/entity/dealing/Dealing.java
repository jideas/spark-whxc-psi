/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.account.intf.entity.dealing
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-13       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.entity.dealing;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>往来</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class Dealing {
		
	/**
	 * 应收/应付
	 */
	private double amount;	
	/**
	 * 初始化应收/应付
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
	 * 名称
	 */
	private String partnerName;
	
	private String namePY;
	/**
	 * 简称
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
	 * 客户 or 供应商
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
