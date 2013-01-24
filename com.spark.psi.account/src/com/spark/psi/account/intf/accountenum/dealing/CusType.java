package com.spark.psi.account.intf.accountenum.dealing;
/**
	 * <p>客户供应商类型</p>
	 *
	
	
	 *
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	public enum CusType{
		/**
		 * 客户
		 */
		CUSTOMER,
		/**
		 * 供应商
		 */
		PROVIDER,
		/**
		 * 零星采购
		 */
		LXCG,
		/**
		 * 散客
		 */
		SK;

		public String toCode(){
			switch(this){
				case CUSTOMER:
					return "01";
				case PROVIDER:
					return "02";
				case LXCG:
					return "03";
				case SK:
					return "04";
			}
			return "";
		}

		public String toString(){
			switch(this){
				case CUSTOMER:
					return "客户";
				case PROVIDER:
					return "供应商";
			}
			return "";
		}
	}