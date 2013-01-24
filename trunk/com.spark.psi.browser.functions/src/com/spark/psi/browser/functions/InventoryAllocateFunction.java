package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;

public class InventoryAllocateFunction extends PSIFunction {

	public String getName() {
		return "InventoryAllocate";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "������";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> functions = new ArrayList<BaseFunction>();
		ApprovalConfigInfo approvalConfig = context.find(ApprovalConfigInfo.class);
		boolean isSelection = true;  
		if(login.hasAuth(Auth.Tag_InventoryAllocate_Approval)&&approvalConfig.isAllocateNeedApproval()){
			isSelection = false;
			functions.add(new BaseFunction(new PageControllerInstance("ApprovalingAllocateSheetListPage"), "������",true));
		}
		functions.add(new BaseFunction(new PageControllerInstance("SubmitingAllocateSheetListPage"), "�µ���"));
		functions.add(new BaseFunction(new PageControllerInstance("ProcessingAllocateSheetListPage"), "������",isSelection));
		functions.add(new BaseFunction(new PageControllerInstance("ProcessedAllocateSheetListPage"), "�����"));
		return functions.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "KuCunDiaoBo";
	}

	public String getCode(){
	    return Auth.MainFunction_InventoryAllocate.getCode();
    }

}
