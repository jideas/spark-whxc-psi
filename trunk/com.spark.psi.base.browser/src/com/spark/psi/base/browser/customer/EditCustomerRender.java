package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;

/**
 * 修改客户视图
 * 
 */
public class EditCustomerRender extends CustomerInfoRender {
 
	@Override
	protected PartnerType getPartnerType() {
		return PartnerType.Customer;
	}

	@Override
	protected boolean isCanSetCredit(){
		LoginInfo login = getContext().find(LoginInfo.class);
	    return login.hasAuth(Auth.SubFunction_CustomerMange_Credit);
	}

}
