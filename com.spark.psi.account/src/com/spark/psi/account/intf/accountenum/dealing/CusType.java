package com.spark.psi.account.intf.accountenum.dealing;
/**
	 * <p>�ͻ���Ӧ������</p>
	 *
	
	
	 *
	 * @author ��־��
	 * @version 2011-11-10
	 */
	public enum CusType{
		/**
		 * �ͻ�
		 */
		CUSTOMER,
		/**
		 * ��Ӧ��
		 */
		PROVIDER,
		/**
		 * ���ǲɹ�
		 */
		LXCG,
		/**
		 * ɢ��
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
					return "�ͻ�";
				case PROVIDER:
					return "��Ӧ��";
			}
			return "";
		}
	}