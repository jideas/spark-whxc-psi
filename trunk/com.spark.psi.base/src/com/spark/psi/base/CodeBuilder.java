package com.spark.psi.base;

/**
 * 
 * <p>���������</p>
 * 
 * ��ѯ���� context.get(String,CodeBuilder)


 *
 
 * @version 2012-3-12
 */
public enum CodeBuilder{

		/**
		 * 1.���۹�����
		 */
		// ���۶���ģ������
		SALES_BILLS("11","salesBills"),
		
		// �����˻���ģ������
		SALES_RETURN("12","salesReturn"),
		
		// ���ۿ�Ʊģ������
		SALES_BILLING("13","salesBilling"),
		
		/**
		 * 2.�ɹ�����
		 */
		//�ɹ�����ģ������
		BUY_ORDER("21","buyOrder"),
		
		// �ɹ��˻���
		BUY_RETURN("22","buyReturn"),
		
		// ����֪ͨ��ģ������
//		PAY_NOTICE("23","payNotice"),
		
		/**
		 * 3.������
		 */
		// ��ⵥģ������
		INSTORE("31","inStore"),
		
		// ���ⵥģ������
		OUTSTORE("32","outStore"),
		
		// �̵㵥ģ������
		CHECKSTORE("33","checkStore"),
		
		// ���䵥ģ������
		BLENDING("34","blending"),
		
		// ��װ��ģ������
		DISMOUNTING("35","dismounting"),
		
		/**
		 * 4.�������
		 */
		// �տģ������
		RECEIPT("41","receipt"),
		
		// ���ģ������
		PAYING("42","paying"),
		
		// ������ģ������
		APPLY("43","apply"),
		
		// ����������
		WANGLAI("44","wanglai"),
		
//		// ת�˵�ģ������
//		TRANSFER("44","transfer"),
		
		/**
		 * 5.ʵ�����
		 */
		// ��Ʒ��Ϣ����ģ������
		GOODS_INFO("51","goodsInfo"),
		
		// �ͻ���Ϣģ������
		CUSTOMER_INFO("52","customerInfo"),
		
		// ��Ӧ����Ϣģ������
		PROVIDER_INFO("53","providerInfo"),
		
		// ��ϵ����Ϣģ������
		CANTACT_INFO("54","cantactInfo"),
		// Ա����Ϣģ������
		EMPLOYEE_INFO("55","employeeInfo"),
		// �ֿ���Ϣ
		STORE_INFO("56","storeInfo");
		// �˻���Ϣ
		// ACCOUNT_INFO("57","accountInfo");
		// ģ��id
		private String tempId;
		
		// �����ļ�ǰ׺
		private String prifix;
		
		CodeBuilder(String tempId, String prifix) {
			this.tempId = tempId;
			this.prifix = prifix;
			
		}
		
		/**
		 * 
		 * @return ģ��id
		 */
		public String getTempId() {
			return tempId;
		}
		
		/**
		 * 
		 * @return �����ļ�ǰ׺
		 */
		public String getPrifix() {
			return this.prifix;
		}
	}

