package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;

/**
 * 新增供应商视图
 * 
 */
public final class NewSupplierRender extends SupplierInfoRender {

	@Override
	protected PartnerType getPartnerType() {
		return PartnerType.Supplier;
	}

	@Override
	protected boolean isCanSetCredit(){
		LoginInfo login = getContext().find(LoginInfo.class);
	    return login.hasAuth(Auth.SubFunction_SupplierManage_Credit);
	} 

	@Override
	protected void renderButton(Composite buttonArea) {
		super.renderButton(buttonArea);
		Button saveAndNewButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveAndNewButton.setID(NewSupplierProcessor.ID_Button_SaveAndNew);
		saveAndNewButton.setText(" 保存并新建 ");

	}
}
