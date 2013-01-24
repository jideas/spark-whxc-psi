package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 开票：已开票
 * @author mengyongfeng
 *
 */
public class DrawBillItem {
	/**
	 * 开票金额合计
	 */
	private double totalDrawBillAmount;
	
	/**
	 * 开票列表
	 */
	private List<DrawBillInfo> DrawBillList = new ArrayList<DrawBillInfo>();
	
	public class DrawBillInfo{
		GUID drawRECID;
		String tenantsName;
		GUID   tenantsRECID;
		long drawDate;
		String drawerName;
		double drawAmount;
		double undrawAmount;
		
		public double getUndrawAmount() {
			return undrawAmount;
		}
		public void setUndrawAmount(double undrawAmount) {
			this.undrawAmount = undrawAmount;
		}
		public String getTenantsName() {
			return tenantsName;
		}
		public void setTenantsName(String tenantsName) {
			this.tenantsName = tenantsName;
		}
		public long getDrawDate() {
			return drawDate;
		}
		public void setDrawDate(long drawDate) {
			this.drawDate = drawDate;
		}
		public String getDrawerName() {
			return drawerName;
		}
		public void setDrawerName(String drawerName) {
			this.drawerName = drawerName;
		}
		public double getDrawAmount() {
			return drawAmount;
		}
		public void setDrawAmount(double drawAmount) {
			this.drawAmount = drawAmount;
		}
		public GUID getDrawRECID() {
			return drawRECID;
		}
		public void setDrawRECID(GUID drawRECID) {
			this.drawRECID = drawRECID;
		}
		public GUID getTenantsRECID() {
			return tenantsRECID;
		}
		public void setTenantsRECID(GUID tenantsRECID) {
			this.tenantsRECID = tenantsRECID;
		}
	
	}

	public double getTotalDrawBillAmount() {
		return totalDrawBillAmount;
	}
	public void add(DrawBillInfo info){
		this.DrawBillList.add(info);
	}
	public void setTotalDrawBillAmount(double totalDrawBillAmount) {
		this.totalDrawBillAmount = totalDrawBillAmount;
	}

	public List<DrawBillInfo> getDrawBillList() {
		return DrawBillList;
	}

	public void setDrawBillList(List<DrawBillInfo> drawBillList) {
		DrawBillList = drawBillList;
	}
}
