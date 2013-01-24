package com.spark.oms.publish.config;

import java.util.ArrayList;

import com.jiuqi.dna.core.type.GUID;


public class ParamConfig {
	
	/**
	 * ֱ����־
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
	 * ��֯����
	 */
	public class Orgnization{
		/**
		 * ����Id
		 */
		private GUID organizationId;
		/**
		 * ����
		 */
		private String organizationName;
		/**
		 * ������
		 */
		private GUID organizationParent;
		
		/**
		 * �Ƿ�ɾ��
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
	 * ����
	 */
	public class Sheet{
		
	}
	/**
	 * �ֵ�
	 */
	public class Dictionary{
		
	}
	
	/**
	 * �������
	 */
	public class BillNoRule{
		/**
		 * ��������
		 */
		private String billCode;
		
		/**
		 * ǰ׺
		 */
		private String prefix;
		
		/**
		 * ��ˮλ��
		 */
		private int scale;
		
		/**
		 * ��ʼ��ˮ��
		 */
		private String firstSerial;
		
		/**
		 * ��ȱ�־
		 */
		private String yearFlag;
		
		/**
		 * �¶ȱ�־
		 */
		private String monthFlag;
		
		/**
		 * ����
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
