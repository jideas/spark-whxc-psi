package com.spark.psi.mainpage.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.FunctionGroup;
import com.spark.psi.mainpage.UserFunction;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.entity.RoleInfo;

public abstract class FunctionPriorityComparator implements
        Comparator<UserFunction>
{

	static final Map<String, Integer> offsetMap =
	        new HashMap<String, Integer>();
	static{

		offsetMap.put(FunctionGroup.Sales.getCode(), 4);
		offsetMap.put(FunctionGroup.Purchase.getCode(), 3);
		offsetMap.put(FunctionGroup.Inventory.getCode(), 2);
		offsetMap.put(FunctionGroup.Account.getCode(), 1);
		offsetMap.put(FunctionGroup.Hide.getCode(), 0);
		offsetMap.put(FunctionGroup.Other.getCode(), 0);

	}

	LoginInfo user =
	        getContext().find(LoginInfo.class);

	public int compare(UserFunction fun1, UserFunction fun2){
		if(fun1.getGroup().equals(FunctionGroup.Hide)){ //隐藏类型的功能模块排到最后面
			return 1;
		}
		if(offsetMap.get(fun1.getGroup()) == null){
			return 1;
		}
		if(offsetMap.get(fun2.getGroup()) == null){
			return -1;
		}
		int int1 = getMaxPriority(fun1.getRolePriority());
		if(int1 > 0){
			int1 += offsetMap.get(fun1.getGroup());
		}
		int int2 = getMaxPriority(fun2.getRolePriority());
		if(int2 > 0){
			int2 += offsetMap.get(fun2.getGroup());
		}
		return int2 - int1;
	}

	private Integer getMaxPriority(Map<String, Integer> map){
		int max = 0;
		List<RoleInfo> roles = getContext().getList(RoleInfo.class);
		for (RoleInfo role : roles) {
			if (((1 << role.getCode()) & user.getEmployeeInfo().getRoles()) != 0) {
				if(map.get(role.getCode()+"") == null){
					continue;
				}
				if(map.get(role.getCode()+"") > max){
					max = map.get(role.getCode()+"");
				}				
			}
		}
		return max;
	}

	public abstract Context getContext();

	public static void getNavigation(List<UserFunction> list,
	        final Context context)
	{
		java.util.Collections.sort(list, new FunctionPriorityComparator(){

			@Override
			public Context getContext(){
				return context;
			}
		});
	}

}