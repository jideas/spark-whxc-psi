package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;


public class UnDrawBillItem {
	/**
	 * δ��Ʊ���ϼ�
	 */
	private double totalUnDrawBillAmount;
	
	/**
	 * Ϊ��Ʊ��¼
	 */
	private List<DrawBillInfo> unDrawBillList = new ArrayList<DrawBillInfo>();
	
	public void add(DrawBillInfo drawBillInfo){
		unDrawBillList.add(drawBillInfo);
	}
	
	public class DrawBillInfo{
		/**
		 * �ͻ���ʶ
		 */
		private GUID tenantsId;
		
		/**
		 * �ͻ�����
		 */
		private String tenantsName;
		
		/**
		 * ���
		 */
		private double unDrawBillAmount;
		
		/**
		 * �Ѿ���Ʊ���
		 */
		private double drawBillAmount;

		public GUID getTenantsId() {
			return tenantsId;
		}

		public void setTenantsId(GUID tenantsId) {
			this.tenantsId = tenantsId;
		}

		public String getTenantsName() {
			return tenantsName;
		}

		public void setTenantsName(String tenantsName) {
			this.tenantsName = tenantsName;
		}

		public double getUnDrawBillAmount() {
			return unDrawBillAmount;
		}

		public void setUnDrawBillAmount(double unDrawBillAmount) {
			this.unDrawBillAmount = unDrawBillAmount;
		}

		public double getDrawBillAmount() {
			return drawBillAmount;
		}

		public void setDrawBillAmount(double drawBillAmount) {
			this.drawBillAmount = drawBillAmount;
		}
	}

	public double getTotalUnDrawBillAmount() {
		return totalUnDrawBillAmount;
	}

	public void setTotalUnDrawBillAmount(double totalUnDrawBillAmount) {
		this.totalUnDrawBillAmount = totalUnDrawBillAmount;
	}

	public List<DrawBillInfo> getUnDrawBillList() {
		return unDrawBillList;
	}

	public void setUnDrawBillList(List<DrawBillInfo> unDrawBillList) {
		this.unDrawBillList = unDrawBillList;
	}
}
