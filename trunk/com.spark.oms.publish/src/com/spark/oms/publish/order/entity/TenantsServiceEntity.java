package com.spark.oms.publish.order.entity;

import java.util.ArrayList;
import java.util.List;

public class TenantsServiceEntity {
	
	private List<ProductCategory>  productCategoriesList = new ArrayList<ProductCategory>();
	
	
	public List<ProductCategory> getProductCategoriesList() {
		return productCategoriesList;
	}
	
	public class ProductCategory{
		private String name;
		private String code;
		private List<ProductSerial> productSerialList = new ArrayList<ProductSerial>();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public List<ProductSerial> getProductSerialList() {
			return productSerialList;
		}
		public void add(ProductSerial productSerial) {
			productSerialList.add(productSerial);
		}
	
	}
	public class ProductSerial{
		private String name;
		private String code;
		private double AccountBalance;
		private List<OrderServiceInfo> orderServiceList = new ArrayList<OrderServiceInfo>();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public double getAccountBalance() {
			return AccountBalance;
		}
		public void setAccountBalance(double accountBalance) {
			AccountBalance = accountBalance;
		}
		public void add(OrderServiceInfo orderServiceInfo){
			orderServiceList.add(orderServiceInfo);
		}
		public List<OrderServiceInfo> getOrderServiceList() {
			return orderServiceList;
		}
	}

}
