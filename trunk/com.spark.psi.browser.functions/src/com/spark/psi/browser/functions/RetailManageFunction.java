package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.key.GetSalesEmployeeStoreKey;
import com.spark.psi.publish.order.key.ValidationRetailerIsValidTask;

public class RetailManageFunction extends PSIFunction {

	public String getName() {
		return "RetailManage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "��Ʒ����";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		ValidationRetailerIsValidTask task = new ValidationRetailerIsValidTask();
		context.handle(task);
		if(!task.isValid()){
			return new BaseFunction[]{
					new BaseFunction(new PageControllerInstance("NotFoundStoreAlterWindow",task.getMsg()),"��ʾ����")
			};
		}
//		if(login.hasAuth(Auth.tag_))
		StoreInfo store = context.find(StoreInfo.class,new GetSalesEmployeeStoreKey());
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"RetailCreatePage",store), "����", true),
//						"RetailDetailPage"), "����", true),
				new BaseFunction(new PageControllerInstance(
						"RecepitingRetailSheetListPage"), "�ͻ��տ�"),
				new BaseFunction(new PageControllerInstance(
						"ProcessedRetailSheetListPage"), "���ۼ�¼"),
				new BaseFunction(new PageControllerInstance(
						"RetailReturnCreatePage"), "�����˻�") };
	}

	@Override
	protected String getIconName() {
		return "ShangPinLinShou";
	}

	public String getCode(){
	    return Auth.MainFunction_RetailManage.getCode();
    }

}
