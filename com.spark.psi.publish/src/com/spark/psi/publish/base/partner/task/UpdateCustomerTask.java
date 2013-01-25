/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.intf.partner.task.supplier
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.intf.partner.task.supplier
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;



/**
 * <p>新建和更新客户信息Task</p>
 * 新增客户界面
 * 快速新增客户界面
 * 编辑客户界面 
 *


 *
 
 * @version 2012-3-2
 */

public class UpdateCustomerTask extends UpdatePartnerTask<UpdatePartnerTask.Method>{
	
	private String pricePolicy;

	/**
	 * @return 价格策略
	 */
	public String getPricePolicy() {
		return pricePolicy;
	}

	public void setPricePolicy(String pricePolicy) {
		this.pricePolicy = pricePolicy;
	}






}
