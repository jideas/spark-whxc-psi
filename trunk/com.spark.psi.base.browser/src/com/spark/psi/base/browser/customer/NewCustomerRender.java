package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;

/**
 * 新增客户视图
 * 
 */
public class NewCustomerRender extends CustomerInfoRender {

	@Override
	protected PartnerType getPartnerType() {
		return PartnerType.Customer;
	}

	@Override
    protected boolean isCanSetCredit(){
		LoginInfo login = getContext().find(LoginInfo.class);
	    return login.hasAuth(Auth.SubFunction_CustomerMange_Credit);
    }

	@Override
	protected void renderBaseInfo(Composite composite) {
		super.renderBaseInfo(composite);
	} 

	@Override
	protected void renderButton(Composite buttonArea) {
		super.renderButton(buttonArea);
		Button saveAndNewButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveAndNewButton.setID(NewCustomerProcessor.ID_Button_SaveAndNew);
		saveAndNewButton.setText(" 保存并新建 ");

	}
	
	

}
