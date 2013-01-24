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
		return "库存查询";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo login = context.find(LoginInfo.class);
		if(login.hasAuth(Auth.SubFunction_InventoryQuery_Count)){
			list.add(new BaseFunction(new PageControllerInstance(
			"MaterialsInventoryQueryPage"), "材料库存", true));
			list.add(new BaseFunction(new PageControllerInstance(
			"GoodsInventoryQueryPage"), "商品库存"));
			list.add(new BaseFunction(new PageControllerInstance(
			"KitInventoryQueryPage"), "其他库存"));
		}
		list.add(new BaseFunction(new PageControllerInstance(
		"InventoryLogQueryPage"), "材料库存流水"));
		list.add(new BaseFunction(new PageControllerInstance(
		"GoodsInventoryLog"), "商品库存流水"));
		list.add(new BaseFunction(new PageControllerInstance(
		"InventoryBookQueryPage"), "材料库存台账"));		
		list.add(new BaseFunction(new PageControllerInstance(
		"GoodsInventoryBookQuery"), "商品库存台账"));		
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
