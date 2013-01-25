/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.psi.base.Tenant;
import com.spark.psi.report.constant.TargetEnum.DateTimeEnum;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.ReportDateUtils;

/**
 *
 */
public abstract class DateTimeProvider{

	/**
	 * @param context
	 * @param key
	 * @param list
	 */
	@SuppressWarnings("deprecation")
	public static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		long begin = 0;
		int beginNo = 0;
		try{
			Tenant tenant = context.find(Tenant.class);
			begin = tenant.getStartDate();
		}
		catch(NullPointerException e){
		}

		List<ReportResult> list1 = new ArrayList<ReportResult>();
		if(key.getTargets().indexOf(DateTimeEnum.Season) >= 0 || key.getTargets().indexOf(DateTimeEnum.SeasonStr) >= 0)
		{
			beginNo = ReportDateUtils.getQuarter(new Date(begin));
			getNearlySeason(key.getMaxCount(), list1, beginNo);
		}
		else if(key.getTargets().indexOf(DateTimeEnum.Month) >= 0
		        || key.getTargets().indexOf(DateTimeEnum.MonthStr) >= 0)
		{
			beginNo = ReportDateUtils.getMonthNo(new Date(begin));
			getNearlyMonth(key.getMaxCount(), list1, beginNo);
		}
		else if(key.getTargets().indexOf(DateTimeEnum.Day) >= 0 || key.getTargets().indexOf(DateTimeEnum.DayStr) >= 0){
			beginNo = ReportDateUtils.getDateNo(new Date(begin));
			int date = new Date().getDate();
			for(int i = 1; i <= date; i++){
				ReportResult rr = new ReportResult();
				int value = ReportDateUtils.getDateNo(new Date()) - date + i;
				if(value < beginNo){
					continue;
				}
				rr.setTargetValue(DateTimeEnum.Day, value);
				rr.setTargetValue(DateTimeEnum.DayStr, i + "");
				list.add(rr);
			}
		}
		for(int i = list1.size() - 1; i >= 0; i--){
			list.add(list1.get(i));
		}
	}

	private static void getNearlySeason(int length, List<ReportResult> list, int beginNo){
		if(0 == length){
			return;
		}
		int thisSeason = ReportDateUtils.getQuarter(new Date());
		ReportResult rr1 = new ReportResult();
		rr1.setTargetValue(DateTimeEnum.Season, thisSeason);
		rr1.setTargetValue(DateTimeEnum.SeasonStr, ReportDateUtils.quaterToStrYear(thisSeason + ""));
		list.add(rr1);
		for(int i = 1; i < length; i++){
			ReportResult rr = new ReportResult();
			thisSeason = ReportDateUtils.getLastQuarter(thisSeason);
			if(thisSeason < beginNo){
				continue;
			}
			rr.setTargetValue(DateTimeEnum.Season, thisSeason);
			rr.setTargetValue(DateTimeEnum.SeasonStr, ReportDateUtils.quaterToStrYear(thisSeason + ""));
			list.add(rr);
		}

	}

	private static void getNearlyMonth(int length, List<ReportResult> list, int beginNo){
		if(0 == length){
			return;
		}
		int monthNo = ReportDateUtils.getMonthNo(new Date());
		ReportResult rr1 = new ReportResult();
		rr1.setTargetValue(DateTimeEnum.Month, monthNo);
		rr1.setTargetValue(DateTimeEnum.MonthStr, ReportDateUtils.monthToStrYear(monthNo + ""));
		list.add(rr1);
		for(int i = 1; i < length; i++){
			ReportResult rr = new ReportResult();
			monthNo = ReportDateUtils.getLastMonthNo(monthNo);
			if(monthNo < beginNo){
				continue;
			}
			rr.setTargetValue(DateTimeEnum.Month, monthNo);
			rr.setTargetValue(DateTimeEnum.MonthStr, ReportDateUtils.monthToStrYear(monthNo + ""));
			list.add(rr);
		}
	}

}
