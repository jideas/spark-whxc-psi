package com.spark.psi.base.browser.supplier;

import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;

/**
 * �޸Ĺ�Ӧ����ͼ
 * 
 */
public final class EditSupplierRender extends SupplierInfoRender {

	@Override
	protected PartnerType getPartnerType() {
		return PartnerType.Supplier;
	}

	@Override
	protected boolean isCanSetCredit(){
		LoginInfo login = getContext().find(LoginInfo.class);
	    return login.hasAuth(Auth.SubFunction_SupplierManage_Credit);
	} 
}
