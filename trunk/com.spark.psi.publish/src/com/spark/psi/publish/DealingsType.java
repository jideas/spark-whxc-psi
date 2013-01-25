package com.spark.psi.publish;

/**
	 * �ͻ�/��Ӧ�����������Ͷ��� 
	 * @author yanglin
	 *
	 */
	public enum DealingsType{
		/**
		 * ����Ӧ��
		 */
		CUS_TZYS("01","����Ӧ��"),
		/**
		 * ���۳���
		 */
		CUS_XSCK("02","���۳���"),
		/**
		 * �˻����
		 */
		CUS_THRK("03","�˻����"),
		/**
		 * �����տ�
		 */
		CUS_XSSK("04","�����տ�"),
		/**
		 * �����˿�
		 */
		CUS_XSTK("05","�����˿�"),
		/**
		 * ���۳���
		 */
		CUS_LSCK("06","���۳���"),
		/**
		 * �����տ�
		 */
		CUS_LSSK("07","�����տ�"),
		/**
		 * �����˻�
		 */
		CUS_LSTH("08","�����˻�"),
		/**
		 * �����˿�
		 */
		CUS_LSTK("09","�����˿�"),
		/**
		 * ��ʼ��Ӧ��
		 */
		CUS_INIT("10","��ʼ��Ӧ��"),

		/**
		 * ����Ӧ��
		 */
		PRO_TZYF("11","����Ӧ��"),
		/**
		 * �ɹ����
		 */
		PRO_CGRK("12","�ɹ����"),
		/**
		 * �˻�����
		 */
		PRO_THCK("13","�˻�����"),
		/**
		 * �ɹ�����
		 */
		PRO_CGFK("14","�ɹ�����"),
		/**
		 * �ɹ��˿�
		 */
		PRO_CGTK("15","�ɹ��˿�"),
		/**
		 * ������
		 */
		PRO_LXCG("16","������"),
		/**
		 * ��ʼ��Ӧ��
		 */
		PRO_INIT("17","��ʼ��Ӧ��"),
		/**
		 * ��ɸ���
		 */
		PRO_LCFK("18","��ɸ���");
		
		private String code,name;
		
		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		private DealingsType(String code,String name)
		{
			this.code = code;
			this.name = name;
		}
		
		public static DealingsType getEnum(String code)
		{
			DealingsType type = null;
			if(null!=code&&!"".equals(code))
			{
				int c = Integer.valueOf(code);
				switch (c) {
				case 1:
					type = DealingsType.CUS_TZYS;
					break;
				case 2:
					type = DealingsType.CUS_XSCK;
					break;
				case 3:
					type = DealingsType.CUS_THRK;
					break;
				case 4:
					type = DealingsType.CUS_XSSK;
					break;
				case 5:
					type = DealingsType.CUS_XSTK;
					break;
				case 6:
					type = DealingsType.CUS_LSCK;
					break;
				
				case 7:
					type = DealingsType.CUS_LSSK;
					break;
				
				case 8:
					type = DealingsType.CUS_LSTH;
					break;
				
				case 9:
					type = DealingsType.CUS_LSTK;
					break;
				
				case 10:
					type = DealingsType.CUS_INIT;
					break;
				
				case 11:
					type = DealingsType.PRO_TZYF;
					break;
				
				case 12:
					type = DealingsType.PRO_CGRK;
					break;
				
				case 13:
					type = DealingsType.PRO_THCK;
					break;
				
				case 14:
					type = DealingsType.PRO_CGFK;
					break;
				
				case 15:
					type = DealingsType.PRO_CGTK;
					break;
				
				case 16:
					type = DealingsType.PRO_LXCG;
					break;
				
				case 17:
					type = DealingsType.PRO_INIT;
					break;
				
				case 18:
					type = DealingsType.PRO_LCFK;
					break;
				
				default:
					break;
				}
			}
			return type;
		}
		
	}