package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class InventoryQueryFunction extends PSIFunction {

	public String getName() {
		return "InventoryQuery";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "����ѯ";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo login = context.find(LoginInfo.class);
		if(login.hasAuth(Auth.SubFunction_InventoryQuery_Count)){
			list.add(new BaseFunction(new PageControllerInstance(
			"MaterialsInventoryQueryPage"), "���Ͽ��", true));
			list.add(new BaseFunction(new PageControllerInstance(
			"GoodsInventoryQueryPage"), "��Ʒ���"));
			list.add(new BaseFunction(new PageControllerInstance(
			"KitInventoryQueryPage"), "�������"));
		}
		list.add(new BaseFunction(new PageControllerInstance(
		"InventoryLogQueryPage"), "���Ͽ����ˮ"));
		list.add(new BaseFunction(new PageControllerInstance(
		"GoodsInventoryLog"), "��Ʒ�����ˮ"));
		list.add(new BaseFunction(new PageControllerInstance(
		"InventoryBookQueryPage"), "���Ͽ��̨��"));		
		list.add(new BaseFunction(new PageControllerInstance(
		"GoodsInventoryBookQuery"), "��Ʒ���̨��"));		
		return list.toArray(new BaseFunction[0]);
		
	}

	@Override
	protected String getIconName() {
		return "KuCunChaXun";
	}

	public String getCode() {
		return Auth.MainFunction_InventoryQuery.getCode();
	}

}
