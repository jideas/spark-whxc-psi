/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.report.intf.constant
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-12       zhongxin        
 * ============================================================*/

package com.spark.psi.report.constant;

/**
 * @author zhongxin
 *
 */
public class OldReportEnums{
	public final static String AMOUNT_UNIT = "元";
	public final static String HUAN_BI = "环比";

	/**
	 * 采购、销售枚举
	 * @author zhongxin
	 *
	 */
	public enum BuyOrSale{
		/** 采购  */
		BUY("01"),
		/** 销售  */
		SALE("02");

		String code;

		private BuyOrSale(String code){
			this.code = code;
		}

		public String getCode(){
			return this.code;
		}
	}

	/**
	 * 客户、供应商枚举
	 * @author zhongxin
	 *
	 */
	public enum CustomOrProvider{
		/** 采购  */
		CUSTOM("01"),
		/** 销售  */
		PROVIDER("02");

		String code;

		private CustomOrProvider(String code){
			this.code = code;
		}

		public String getCode(){
			return this.code;
		}
	}

	/**
	 * 滚动屏显示金额类型：销售金额、销售收款枚举
	 * @author zhongxin
	 *
	 */
	public enum ScreenType{
		/** 销售金额  */
		SALE_AMOUNT("01", "销售金额"),
		/** 销售收款  */
		SALE_RECEIPT("02", "销售收款");
		private String code;
		private String title;
		private ScreenType(String code, String title){
			this.code = code;
			this.title = title;
		}
		public String getCode(){
			return this.code;
		}
        public String getTitle(){
        	return title;
        }

		public static ScreenType getScreenTypeByCode(String code){
			if("01".equals(code)){
				return SALE_AMOUNT;
			}
			else if("02".equals(code)){
				return SALE_RECEIPT;
			}
			else{
				return null;
			}
		}
		
		public String getUrl(){
			switch (this) {
			case SALE_AMOUNT:
				return "sales";
			case SALE_RECEIPT:
				return "receipt";
			}
			return null;
		}
	}

	/**
	 * <p>报表图形类型</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author tangchengguo
	 * @version 2012-1-7
	 */

	public enum ReportTypeEnum{
		LINE("折线"),
		BAR("柱形"),
		LINE_BAR_EQUAL("直线和柱形一起，数据一致"),
		LINE_BAR_NEQUAL("直线和柱形一起，数据不一致"),
		PIE("饼图");
		private String title;

		ReportTypeEnum(String title){
			this.title = title;
		}

		public String getTitle(){
			return title;
		}
	}

	/**
	 * 
	 * <p>页签枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 向中秋
	 * @version 2012-12-12
	 */
	public enum PageType{
		/**
		 * 季度
		 */
		QUARTER,
		/**
		 * 月
		 */
		MONTH,
	}

	/**
	 * 数据类型
	 * @author zhongxin
	 *
	 */
	public enum ReportDataType{
		/** 销售分析 */
		SALE_ANALYSIS,
		/** 采购分析 */
		BUY_ANALYSIS,
		/** 库存分析 */
		STORAGE_ANALYSIS,
		/** 收支情况 */
		FINANCE_ANALYSIS,
		/** 销售退货分析 */
		SALE_RTN_ANALYSIS,
		/** 客户分析 */
		CUSTOM_ANALYSIS,
	}

	/**
	 * 销售分析类型
	 * @author zhongxin
	 *
	 */
	public enum FenXiType{
		GONG_SI, // 公司
		BU_MEN, // 部门
		YUAN_GONG, // 员工
		
		/** 部门经理、部门员工时，销售管控界面处的业绩类型 *******/
		XS_DUIBI, // 销售对比
		KH_PAIMING // 客户排名
	}

	/**
	 * 查询本月、按月、按季
	 * @author 向中秋
	 */
	public enum DayMonQuarter{
		/**
		 * 本月
		 */
		DAY,
		/**
		 * 按月
		 */
		MONTH,
		/**
		 * 本季
		 */
		QUARTER
	}
	
	/**
	 * 
	 * <p>经营分析坐标轴以及提示字符数字显示位数</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-12-20
	 */
	public enum LabelFormatType {
		DECEIMAL_ZERO("{%Value}{numDecimals:0}", "{numDecimals:0}"), // 0位
		DECEIMAL_TWO("{%Value}{numDecimals:2}", "{numDecimals:2}"); // 2位
		private String labelFormat;
		private String hintFormat;
		LabelFormatType(String labelFormat, String hintFormat) {
			this.labelFormat = labelFormat;
			this.hintFormat = hintFormat;
		}
        public String getLabelFormat(){
        	return labelFormat;
        }
        public String getHintFormat(){
        	return hintFormat;
        }
	}
	
	/**
	 * 浮出框类型
	 * @author 汤成国
	 */
	public enum FloatWindowType{
		ONLY_SALE("仅销售"),
		ONLY_RTN("仅退货"),
		SALE_RTN("销售退货"),
		BUY_RTN("采购退货"),
		STORE_SALE_BUY("库存销售采购");
		FloatWindowType(String title) {
		}
	}
	public class RreportConstant{
		
		/**
		 * 环比为负时设置的字体颜色
		 * item.getCell(index).setFontColor();
		 */
		public static final int huanbiColor = 0xFF3200;
		
		
		// 查询部门不包含自己的一级部门的节点
		public static final String DEPT_YIJI_JIEDAIN = " JOIN ( SELECT h1.RECID AS recid,s.RECID AS srecid  FROM SA_CORE_TREE AS s  JOIN" +
											" SA_CORE_TREE AS h1 ON h1.PATH > s.PATH AND (h1.PATH < s.PATH || bytes'ff')" +
											" and len(h1.PATH) = len(s.PATH) + 34 " +
											" WHERE s.RECID =  @deptGuid) AS Y ON y.recid=a.deptGuid ";
		// 查询部门包含自己的一级部门的节点
		public static final String DEPT_YIJI_JIEDAIN_AND_SELF = " JOIN ( SELECT h1.RECID AS recid,s.RECID AS srecid  FROM SA_CORE_TREE AS s  JOIN" +
											" SA_CORE_TREE AS h1 ON h1.PATH > s.PATH AND (h1.PATH < s.PATH || bytes'ff')" +
											" and len(h1.PATH) = len(s.PATH) + 34 OR h1.RECID=@deptGuid " +
											" WHERE s.RECID =  @deptGuid) AS Y ON y.recid=a.deptGuid ";
		
		// 查询部门不包含自己的子孙部门的节点
		public static final String DEPT_ZISUN_JIEDAIN = " JOIN ( SELECT h1.RECID AS recid,s.RECID AS srecid  FROM SA_CORE_TREE AS s  JOIN" +
											" SA_CORE_TREE AS h1 ON h1.PATH > s.PATH AND (h1.PATH < s.PATH || bytes'ff')" +
											" and len(h1.PATH) = len(s.PATH) + 34 " +
											" WHERE s.RECID =  @deptGuid) AS Y ON y.recid=a.deptGuid ";
		
		// 查询部门包含自己的子孙部门的节点
		public static final String DEPT_ZISUN_JIEDAIN_AND_SELF = " JOIN ( SELECT h1.RECID AS recid,s.RECID AS srecid  FROM SA_CORE_TREE AS s  JOIN" +
											" SA_CORE_TREE AS h1 ON h1.PATH > s.PATH AND (h1.PATH < s.PATH || bytes'ff')" +
											" and len(h1.PATH) = len(s.PATH) + 34 OR h1.RECID=@deptGuid " +
											" WHERE s.RECID =  @deptGuid) AS Y ON y.recid=a.deptGuid ";
		// 查询部门包含自己的子孙部门的节点
		public static final String DEPT_ZISUN_JIEDAIN_AND_SELF_NOJOIN = " SELECT h1.RECID AS recid,s.RECID AS srecid  FROM SA_CORE_TREE AS s  JOIN" +
											" SA_CORE_TREE AS h1 ON h1.PATH > s.PATH AND (h1.PATH < s.PATH || bytes'ff')" +
											" and len(h1.PATH) = len(s.PATH) + 34 OR h1.RECID=@deptGuid " +
											" WHERE s.RECID =  @deptGuid ";
		/**
		 * 
		 * <p>销售分析常量</p>
		 *
		 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
		
		 *
		 * @author 汤成国
		 * @version 2011-12-14
		 */
		public  class SaleReportConstant {
			public final static String SALE_AMOUNT = "销售金额";
			public final static String SALE_RECEIT = "销售收款";
			public final static String RECEIT_AMOUNT = "回款收款";
			public final static String SALE_TARGET = "销售目标";
			public final static String RECEIT_TARGET = "回款目标";
		}
		
		/**
		 * 
		 * <p>采购分析常量</p>
		 *
		 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
		
		 *
		 * @author 汤成国
		 * @version 2011-12-14
		 */
		public  class BuyReportConstant {
			public final static String BUY_AMOUNT = "采购金额";
			public final static String BUY_RECEIT = "采购付款";
		}
		 
	}
}
