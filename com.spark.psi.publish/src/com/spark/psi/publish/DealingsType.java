package com.spark.psi.publish;

/**
	 * 客户/供应商往来款类型定义 
	 * @author yanglin
	 *
	 */
	public enum DealingsType{
		/**
		 * 调整应收
		 */
		CUS_TZYS("01","调整应收"),
		/**
		 * 销售出库
		 */
		CUS_XSCK("02","销售出库"),
		/**
		 * 退货入库
		 */
		CUS_THRK("03","退货入库"),
		/**
		 * 销售收款
		 */
		CUS_XSSK("04","销售收款"),
		/**
		 * 销售退款
		 */
		CUS_XSTK("05","销售退款"),
		/**
		 * 零售出库
		 */
		CUS_LSCK("06","零售出库"),
		/**
		 * 零售收款
		 */
		CUS_LSSK("07","零售收款"),
		/**
		 * 零售退货
		 */
		CUS_LSTH("08","零售退货"),
		/**
		 * 零售退款
		 */
		CUS_LSTK("09","零售退款"),
		/**
		 * 初始化应收
		 */
		CUS_INIT("10","初始化应收"),

		/**
		 * 调整应付
		 */
		PRO_TZYF("11","调整应付"),
		/**
		 * 采购入库
		 */
		PRO_CGRK("12","采购入库"),
		/**
		 * 退货出库
		 */
		PRO_THCK("13","退货出库"),
		/**
		 * 采购付款
		 */
		PRO_CGFK("14","采购付款"),
		/**
		 * 采购退款
		 */
		PRO_CGTK("15","采购退款"),
		/**
		 * 零采入库
		 */
		PRO_LXCG("16","零采入库"),
		/**
		 * 初始化应付
		 */
		PRO_INIT("17","初始化应付"),
		/**
		 * 零采付款
		 */
		PRO_LCFK("18","零采付款");
		
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