package com.spark.psi.mainpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.FunctionGroup;
import com.spark.common.components.pages.MainFunction;
import com.spark.common.components.pages.MainFunctionGather;
import com.spark.psi.mainpage.utils.FunctionPriorityComparator;
import com.spark.psi.publish.base.function.FunctionPositionInfo;
import com.spark.psi.publish.base.function.FunctionPositionInfo.FunctionPosition;

/**
 * 
 * <p>用户功能模块列表提供器</p>
 *


 *
 
 * @version 2012-4-18
 */
public class UserFunctionProvider{
	
	public static UserFunction[] getUserFunctions(final Context context){
		FunctionPositionInfo position = context.find(FunctionPositionInfo.class);
		List<UserFunction> result = new ArrayList<UserFunction>(); 
		for(final FunctionPosition f : position.getUserFunctionPositions()){
	        final MainFunction function = MainFunctionGather.getFunction(f.getFunctionId());
	        if(function==null)continue;
	        UserFunction uf = new UserFunction(){
				
				public ImageDescriptor getTitleIcon(){ 
					return function.getTitleIcon();
				}
				
				public String getTitle(){
					return function.getTitle();
				}
				
				public ImageDescriptor getSmallNormalIcon(){
					return function.getSmallNormalIcon();
				}
				
				public ImageDescriptor getSmallHoverIcon(){
					return function.getSmallHoverIcon();
				}
				
				public Map<String, Integer> getRolePriority(){
					return f.getRolePriority();
				}
				
				public String getName(){
					return function.getName();
				}
				
				public ImageDescriptor getMiddleNormalIcon(){
					return function.getMiddleNormalIcon();
				}
				
				public ImageDescriptor getMiddleHoverIcon(){
					return function.getMiddleHoverIcon();
				}
				
				public ImageDescriptor getLargeNormalIcon(){
					return function.getLargeNormalIcon();
				}
				
				public ImageDescriptor getLargeHoverIcon(){
					return function.getLargeHoverIcon();
				}
				
				public String getGroup(){
					return function.getGroup();
				}
				
				public String getCode(){
					return function.getCode();
				}
				
				public BaseFunction[] getBaseFunctions(Context context){
					return function.getBaseFunctions(context);
				}
				
				public boolean isPutMain(){  //如果用户没有初始化模块定位，则取默认的模块定位
					return f.isInited() ? f.isPutMain() : function.isPutMain();
				}
				
				public int getYindex(){
					return f.getYindex();
				}
				
				public int getXindex(){
					return f.getXindex();
				}

                public FunctionGroup getFunctionGroup(){
	                return FunctionGroup.getFunctionGroupByCode(function.getGroup());
                }
                
                @Override
                public boolean equals(Object arg0){
                	if(arg0==null)return false;
                	if(!(arg0 instanceof UserFunction))return false;
                    return this.getCode().equals(((UserFunction)arg0).getCode());
                }
			};
			if(!result.contains(uf))
				result.add(uf);
        }
		
		Collections.sort(result,new FunctionPriorityComparator(){
			
			@Override
			public Context getContext(){
				return context;
			}
		});
		return result.toArray(new UserFunction[result.size()]);
	}
	
}
