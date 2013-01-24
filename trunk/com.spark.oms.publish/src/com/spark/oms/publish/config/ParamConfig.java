package com.spark.oms.publish.config;

import java.util.ArrayList;

import com.jiuqi.dna.core.type.GUID;


public class ParamConfig {
	
	/**
	 * 直供标志
	 */
	private String directProviderFlag;
	
	private ArrayList<BillNoRule> BillNoRules = new ArrayList<BillNoRule>();
	
	private ArrayList<Orgnization> Orgnizations = new ArrayList<Orgnization>();
	
	public ArrayList<Orgnization> getOrgnizations() {
		return Orgnizations;
	}

	public void setOrgnizations(ArrayList<Orgnization> orgnizations) {
		Orgnizations = orgnizations;
	}



	public void addOrgnizations(Orgnization orgnization) {
		Orgnizations.add(orgnization);
	}



	public void addBillNoRule(BillNoRule billNoRule ){
		BillNoRules.add(billNoRule);
	}
	
	
	
	public ArrayList<BillNoRule> getBillNoRules() {
		return BillNoRules;
	}



	public void setBillNoRules(ArrayList<BillNoRule> billNoRules) {
		BillNoRules = billNoRules;
	}



	public String getDirectProviderFlag() {
		return directProviderFlag;
	}

	public void setDirectProviderFlag(String directProviderFlag) {
		this.directProviderFlag = directProviderFlag;
	}



	/**
	 * 组织机构
	 */
	public class Orgnization{
		/**
		 * 机构Id
		 */
		private GUID organizationId;
		/**
		 * 机构
		 */
		private String organizationName;
		/**
		 * 父机构
		 */
		private GUID organizationParent;
		
		/**
		 * 是否删除
		 */
		private boolean removeFlag;
		
		
		
		public boolean getRemoveFlag() {
			return removeFlag;
		}
		public void setRemoveFlag(boolean removeFlag) {
			this.removeFlag = removeFlag;
		}
		public GUID getOrganizationId() {
			return organizationId;
		}
		public void setOrganizationId(GUID organizationId) {
			this.organizationId = organizationId;
		}
		public String getOrganizationName() {
			return organizationName;
		}
		public void setOrganizationName(String organizationName) {
			this.organizationName = organizationName;
		}
		public GUID getOrganizationParent() {
			return organizationParent;
		}
		public void setOrganizationParent(GUID organizationParent) {
			this.organizationParent = organizationParent;
		}
	}
	/**
	 * 订单
	 */
	public class Sheet{
		
	}
	/**
	 * 字典
	 */
	public class Dictionary{
		
	}
	
	/**
	 * 订单编号
	 */
	public class BillNoRule{
		/**
		 * 订单代码
		 */
		private String billCode;
		
		/**
		 * 前缀
		 */
		private String prefix;
		
		/**
		 * 流水位数
		 */
		private int scale;
		
		/**
		 * 起始流水号
		 */
		private String firstSerial;
		
		/**
		 * 年度标志
		 */
		private String yearFlag;
		
		/**
		 * 月度标志
		 */
		private String monthFlag;
		
		/**
		 * 天数
		 */
		private String dayFlag;

		public String getBillCode() {
			return billCode;
		}

		public void setBillCode(String billCode) {
			this.billCode = billCode;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public int getScale() {
			return scale;
		}

		public void setScale(int scale) {
			this.scale = scale;
		}

		public String getFirstSerial() {
			return firstSerial;
		}

		public void setFirstSerial(String firstSerial) {
			this.firstSerial = firstSerial;
		}

		public String getYearFlag() {
			return yearFlag;
		}

		public void setYearFlag(String yearFlag) {
			this.yearFlag = yearFlag;
		}

		public String getMonthFlag() {
			return monthFlag;
		}

		public void setMonthFlag(String monthFlag) {
			this.monthFlag = monthFlag;
		}

		public String getDayFlag() {
			return dayFlag;
		}

		public void setDayFlag(String dayFlag) {
			this.dayFlag = dayFlag;
		}
		
		
	}

}
