/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.GetCustomerListByEmployeeIdKey;
import com.spark.psi.report.constant.TargetEnum.AreaEnum;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.Condition;

/**
 *
 */
public abstract class AreaSubjectProvider{
	/**
	 * 地区报表数据查询
	 * 
	 * @param context
	 * @param key
	 * @param list
	 */
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		Login login = context.find(Login.class);
		List<Partner> plist = context.getList(Partner.class, new GetCustomerListByEmployeeIdKey(login.getEmployeeId()));
		Condition con = null;
		if(CheckIsNull.isNotEmpty(key.getConditions())){
			con = key.getConditions().get(0);
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		int totalCount = 0;
		for(Partner p : plist){
			if(null != con && CheckIsNull.isNotEmpty(con.getValue())){
				if(CheckIsNull.isNotEmpty(p.getProvince())&&con.getValue().equals(p.getProvince().getCode())){
					totalCount++;
					Integer count = map.get(p.getCity().getName());
					if(null == count){
						count = 1;
					}
					else{
						count++;
					}
					map.put(p.getCity().getName(), count);
				}
				continue;
			}
			totalCount++;
			Integer count = null;
			if(CheckIsNull.isNotEmpty(p.getProvince())&&CheckIsNull.isNotEmpty(p.getProvince().getName())){
				count = map.get(p.getProvince().getName());
			}
			if(null == count){
				count = 1;
			}
			else{
				count++;
			}
			if(CheckIsNull.isNotEmpty(p.getProvince())&&CheckIsNull.isNotEmpty(p.getProvince().getName())){
				map.put(p.getProvince().getName(), count);
			}
		}
		for(String s : map.keySet()){
			ReportResult rr = new ReportResult();
			rr.setTargetValue(AreaEnum.AreaName, s);
			rr.setTargetValue(AreaEnum.CustomersCountOfArea, map.get(s));
			rr.setTargetValue(AreaEnum.TotalCustomerCount, totalCount);
			rr.setOrderObj(AreaEnum.CustomersCountOfArea);
			list.add(rr);
		}
		ComparatorUtils.sort(list, "orderObj", true);
		if(list.size() > 5){
			list.subList(0, 5);
		}
	}
}
