package com.spark.psi.publish;
/**
	 *收款类型枚举
	 */
	public enum ReceiptType{
		/**
		 * 销售货款
		 */
		RECEIPT_XSHK("01","销售货款"),
		/**
		 * 采购退款
		 */
		RECEIPT_CGTK("02","采购退款"),
		/**
		 * 零售收款
		 */
		RECEIPT_LSSK("03","零售收款");

		private String code,name;
		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
		private ReceiptType(String code,String name)
		{
			this.code = code;
			this.name = name;
		}
		public static ReceiptType getReceiptType(String code)
		{
			if(ReceiptType.RECEIPT_CGTK.getCode().equals(code))
			{
				return ReceiptType.RECEIPT_CGTK;
			}
			else if(ReceiptType.RECEIPT_LSSK.getCode().equals(code))
			{
				return ReceiptType.RECEIPT_LSSK;
			}
			else if(ReceiptType.RECEIPT_XSHK.getCode().equals(code))
			{
				return ReceiptType.RECEIPT_XSHK;
			}
			return null;
		}
	}