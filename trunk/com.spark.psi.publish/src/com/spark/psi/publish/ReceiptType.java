package com.spark.psi.publish;
/**
	 *�տ�����ö��
	 */
	public enum ReceiptType{
		/**
		 * ���ۻ���
		 */
		RECEIPT_XSHK("01","���ۻ���"),
		/**
		 * �ɹ��˿�
		 */
		RECEIPT_CGTK("02","�ɹ��˿�"),
		/**
		 * �����տ�
		 */
		RECEIPT_LSSK("03","�����տ�");

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