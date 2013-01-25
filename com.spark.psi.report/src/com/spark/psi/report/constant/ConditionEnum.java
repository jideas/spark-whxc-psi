/**
 * 
 */
package com.spark.psi.report.constant;

import java.util.Date;

import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.TimeTest;

/**
 *���˿ھ�ö��
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
	 * ��Ʒ��Ŀ���˿ھ�
	 */
	public enum GoodsItemConditionEnum{

	}

	/**
	 * �ͻ����˿ھ�
	 */
	public enum CustomerConditionEnum{
		CustomerId,
		OrderPerson,
		Auth
	}

	/**
	 * �ͻ���������Ա���˿ھ�
	 */
	public enum Customer_EmployeeConditionEnum{
		CustomerId,
		EmployeeId,

	}

	/**
	 * ��Ӧ�̹��˿ھ�
	 */
	public enum SupplierConditionEnum{
		SupplierId,
	}

	/**
	 * Ա�����˿ھ�
	 */
	public enum EmployeeConditionEnum{
		DepartmentId,
		EmployeeId
	}

	/**
	 * ���Ź��˿ھ�
	 */
	public enum DepartmentConditionEnum{
		HasRole,
		DepartmentId,
		ParentDepartmentId,
	}

	/**
	 * �������˿ھ�
	 */
	public enum AreaConditionEnum{
		Province,
	}

	/**
	 * ���̨�˹��˿ھ�
	 */
	public enum InventoryBookConditionEnum{
		StoreId,
		GoodsSearch
	}

	/**
	 * ��˾���˿ھ�
	 */
	public enum CompanyConditionEnum{
	}

}
