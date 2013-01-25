/**
 * 
 */
package com.spark.psi.report.constant;

import java.util.Date;

import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.TimeTest;

/**
 *过滤口径枚举
 */
public class ConditionEnum{

	public enum DateTimeIntervalEnum{
		Day("Day"),Month("Month"),Season("Season"),
		ThisMonth("ThisMonth"),
		ThisQuarter("ThisQuarter"),
		ThisYear("ThisYear"),
		DateTimeInterval("DateTimeInterval");
		
		private long beginTime;
		private long endTime;

		private DateTimeIntervalEnum(String code){
		}

		public static DateTimeIntervalEnum getInstance(String code){
			if(CheckIsNull.isEmpty(code)){
				return null;
			}
			code = code.toUpperCase();
			if("ThisMonth".toUpperCase().equals(code)){
				return ThisMonth;
			}
			else if("ThisQuarter".toUpperCase().equals(code)){
				return ThisQuarter;
			}
			else if("ThisYear".toUpperCase().equals(code)){
				return ThisYear;
			}
			else if("DateTimeInterval".toUpperCase().equals(code)){
				return DateTimeInterval;
			}
			return null;
		}

		public long getBeginTime(){
			switch(this){
				case ThisMonth:
					return new TimeTest().getFirstDayOfMonth();
				case ThisQuarter:
					return new TimeTest().getThisSeasonTime();
				case ThisYear:
					return new TimeTest().getCurrentYearFirst();
				case DateTimeInterval:
					return this.beginTime;
				default:
					return 0l;
			} 
		}

		public void setInterval(long beginTime, long endTime){
			this.beginTime = beginTime;
			this.endTime = endTime;
		}

		public long getEndTime(){
			if(DateTimeInterval == this){
				return this.endTime;
			}
			return new Date().getTime();

		}
	}

	/**
	 * 商品条目过滤口径
	 */
	public enum GoodsItemConditionEnum{

	}

	/**
	 * 客户过滤口径
	 */
	public enum CustomerConditionEnum{
		CustomerId,
		OrderPerson,
		Auth
	}

	/**
	 * 客户并销售人员过滤口径
	 */
	public enum Customer_EmployeeConditionEnum{
		CustomerId,
		EmployeeId,

	}

	/**
	 * 供应商过滤口径
	 */
	public enum SupplierConditionEnum{
		SupplierId,
	}

	/**
	 * 员工过滤口径
	 */
	public enum EmployeeConditionEnum{
		DepartmentId,
		EmployeeId
	}

	/**
	 * 部门过滤口径
	 */
	public enum DepartmentConditionEnum{
		HasRole,
		DepartmentId,
		ParentDepartmentId,
	}

	/**
	 * 地区过滤口径
	 */
	public enum AreaConditionEnum{
		Province,
	}

	/**
	 * 库存台账过滤口径
	 */
	public enum InventoryBookConditionEnum{
		StoreId,
		GoodsSearch
	}

	/**
	 * 公司过滤口径
	 */
	public enum CompanyConditionEnum{
	}

}
