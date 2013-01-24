package com.spark.common.components.controls.text;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;

public class SAAS {
	public static String SHOW_NULL_STR = "--";

	/**
	 * 商品库存预计类型
	 */
	public static enum Goods_Tips_Type {
		/**
		 * 按总库存，商品金额
		 */
		ALL_AMOUNT("01"),
		/**
		 * 按总库存，商品数量
		 */
		ALL_COUTN("02"),
		/**
		 * 按分库存，商品金额
		 */
		STORAGE_AMOUNT("03"),
		/**
		 * 按分库存，商品数量
		 */
		STORAGE_COUNT("04");

		private String value;

		Goods_Tips_Type(String value) {
			this.value = value;
		}

		public String toCode() {
			return value;
		}
	}

	/**
	 * 
	 * <p>
	 * 数据字典枚举
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author 周利均
	 * @version 2011-11-28
	 */
	public enum DictionaryType {
		SEX("sex"),

		/**
		 * 入库类型
		 */
		INSTOTYPE("instotypes"),
		/**
		 * 入库状态
		 */
		instoState("instoStates"),
		/**
		 * 出库状类型
		 */
		OUTSTOTYPE("outstotypes"),
		/**
		 * 出库状态
		 */
		outstoState("outstoStates"),
		/**
		 * 提醒周期
		 */
		IMPORTANTCYCLE("importantCycle");

		private String typeCode;

		@Override
		public String toString() {
			return typeCode;
		}

		private DictionaryType(String typeCode) {
			this.typeCode = typeCode;
		}
	}

	/**
	 * session失效时间，默认5分钟
	 */
	public final static int SESSIONTIME = 15 * 60 * 1000;
	/**
	 * 预警天数
	 */
	public final static int waringDay = 3;
	/**
	 * 供应商直供的虚拟仓库id和名称
	 */
	public final static GUID PROVIDERSOTRE = GUID
			.valueOf("00000000000000000000000000000000");
	public final static String PROVIDERSOTRENAME = "供应商直供";

	/**
	 * <p>
	 * 功能模块拼音标识
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-16
	 */
	public interface FunStr {

		/**
		 * 入库模块标记
		 */
		String INSTO = "ruku";
		/**
		 * 出库模块标记
		 */
		String OUTSTO = "chuku";
		/**
		 * 销售配货模块标记
		 */
		String DEPLOY = "xsph";
	}

	/**
	 * 
	 * <p>
	 * 排序类型
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author 向中秋
	 * @version 2011-11-15
	 */
	public enum SortType {
		DESC, ASC;

		public String toString() {
			switch (this) {
			case DESC:
				return "desc";
			case ASC:
				return "asc";
			}
			return "";
		}
	}

	/**
	 * 
	 * <p>
	 * 经营分析 --财务(按年、按月)
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author 向中秋
	 * @version 2011-12-16
	 */
	public enum REPORT_YEARORMONTH {
		YEAR, MONTH;
	}

	/**
	 * <p>
	 * 数据库操作枚举
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	public enum TaskMethod {
		INSERT, MODFIY, DELETE,
		/**
		 * 更改流程状态
		 */
		CHANGEPROCESS,
		/**
		 * 动态
		 */
		EXECUTE;

	}

	/**
	 * <p>
	 * 客户供应商类型
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	public enum CusType {
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

		public String toCode() {
			switch (this) {
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

		public String toString() {
			switch (this) {
			case CUSTOMER:
				return "客户";
			case PROVIDER:
				return "供应商";
			}
			return "";
		}
	}

	/**
	 * <p>
	 * 授权商品状态
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 闫永红
	 * @version 2011-11-17
	 */
	public enum GoodsStatus {
		/**
		 * 可在线下单
		 */
		GOOD_ZXXD,
		/**
		 * 不可在线下单
		 */
		GOOD_NOZXXD,
		/**
		 *停售商品
		 */
		GOOD_TSSP;

		public String toCode() {
			switch (this) {
			case GOOD_ZXXD:
				return "01";
			case GOOD_NOZXXD:
				return "02";
			case GOOD_TSSP:
				return "03";
			}
			return "";
		}

		public String toString() {
			switch (this) {
			case GOOD_ZXXD:
				return "可在线下单";
			case GOOD_NOZXXD:
				return "不可在线下单";
			case GOOD_TSSP:
				return "停售商品";
			}
			return "";
		}
	}

	/**
	 * 公共控件的常量
	 * 
	 * @author 王志坚
	 * @version 2011-10-13
	 */
	public interface CONTROLS {

		String AREA_LABEL = "地　　区";

		/**
		 * <p>
		 * 搜索控件的类型
		 * </p>
		 * 
		 * @author 王志坚
		 * @version 2011-10-18
		 */
		public enum SearchType {
			/**
			 * 一个文本框两个按钮
			 */
			TEXT_BUTTON_BUTTON_FORGOOD,
			/**
			 * 带有放大镜的文本框和一个高级搜索的按钮
			 */
			TEXT_IMG_BUTTON,
			/**
			 * 一个文本框一个按钮
			 */
			TEXT_BUTTON
		}
	}

	/**
	 * 无连接字体颜色值
	 */
	// public static final int NO_LINK_FONTCOLOR = 0xCCCCCC;
	public static final int NO_LINK_FONTCOLOR = 0x000000;
	/**
	 * 设置表格列字体内容属性
	 */
	public static final int TxtKindCN = 0;// 汉字
	public static final int TxtKindNM = 1;// 数字，字母，或数字和字母的组合
	/**
	 * 列间距
	 */
	public static final int horizontalSpacing = 15;

	/**
	 * 行间距
	 */
	public static final int verticalSpacing = 1;

	/**
	 * 搜索样式
	 */
	public static final int SEARCH_STYLE = 1;
	/**
	 * 高级搜索
	 */
	public static final int ADVANCE_STYLE = 2;

	/**
	 * 页签文字左右的空格
	 */
	private static final String space = "   ";

	public static class Layout {
		/**
		 * 全局静态FillLayout对象<br>
		 * <strong>注：不能给fillLayoutIns的成员赋值，否则会影响全局布局</strong>
		 */
		public static final FillLayout fillLayoutIns = new FillLayout();

		/**
		 * 全局静态表单行griddata布局对象 设置了横向填充，高度20像素
		 * <strong>注：不能给fillLayoutIns的成员赋值，否则会影响全局布局</strong>
		 */
		public static final GridData gridDataLineIns = new GridData(
				GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);

		static {
			gridDataLineIns.heightHint = 20;
		}
	}

	/**
	 * 表格常量定义
	 * 
	 * @author zhangyu
	 * 
	 */
	public static class Grid {

		public final static int operWidth = 41;
		/**
		 * 环比列宽度
		 */
		public final static int HUANBI_WIDTH = 60;

		private static Font font = new Font(9, "宋体", JWT.FONT_STYLE_UNDERLINE);
		private static Font commonFont = new Font();
		private static Font GridCNFont = new Font(9, "宋体", JWT.FONT_STYLE_PLAIN);

		public static Font getGridCNFont() {
			return GridCNFont;
		}

		private static Font GridNumFont = new Font(9, "Arial",
				JWT.FONT_STYLE_PLAIN);

		/**
		 * 表格初始行数
		 */
		public static final int pageSize = 20;
		/**
		 * 表格初始行数
		 */
		public static final int detailPageSize = 1;

		public static Font getGridNumFont() {
			return GridNumFont;
		}

		/**
		 * 取得表格链接字体对象
		 * 
		 * @return
		 */
		public static final Font getLinkFont() {
			return font;
		}

		/**
		 * 取得表格链接字体对象
		 * 
		 * @return
		 */
		public static final Font getCommonFont() {
			return commonFont;
		}

		/**
		 * 取得表格链接字体颜色值
		 * 
		 * @return
		 */
		public static final int getLinkForeground() {
			// return 0x9dd06f;
			return 0x0059af;
		}
	}

	public static class Reg {

		/**
		 * 最多几位小数的正则表达式
		 * 
		 * @param scale
		 * @return String
		 */
		public static String getReg(final int scale) {
			if (5 < scale) {
				return null;
			} else if (0 == scale) {
				return "^\\d{1,15}$";
			}
			int length = 16 - scale;
			return "^\\d{1," + length + "}(\\.\\d{0," + scale + "})?$";
		}

		/**
		 * 数字正则表达式
		 * 
		 * @param integer
		 *            整数部分位数
		 * @param decimal
		 *            小数部分位数（小数部分位数）
		 * @return String
		 */
		public static String getReg(final int integer, final int decimal) {
			if (0 >= integer) {
				return null;
			}
			if (0 >= decimal) {
				return "^\\d{1," + integer + "}$";
			}
			return "^\\d{1," + integer + "}(\\.\\d{0," + decimal + "})?$";
		}

		/**
		 * 全局静态只能输入两位小数的正浮点数对象<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_DOUBLE = "^\\d*(\\.)?(\\d)?(\\d)?$";

		/**
		 * 汉字数字字母下划线
		 */
		public static final String TEXT = "^[a-zA-Z0-9_/u4e00-/u9fa5]+$";
		public static final String REGEXP_POSITIVE_DOUBLE_FUSHU_LIMIT = "^([+-]?\\d{0,15}(\\.\\d{0,2})?)$";

		/**
		 * 全局静态固话和传真验证 接受格式 XXXX-XXXX-XXX 其中XXXX代表数字，但不限长度
		 */
		public static final String REGEXP_PHONE = "(^\\d*)([+-]?)(\\d*)([+-]?)(\\d*$)";

		/**
		 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为8位，小数部分最长为2位<br>
		 */
		public static final String NUM_TEN_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 折扣额等5（2）0%-100%
		 */
		public static final String NUM_FIVE_TWO = "(^(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?$)|(^[1][0][0]$)";
		/**
		 * 全局静态邮编<br>
		 * 
		 */
		public static final String REGEXP_POSTCODE = "^[0-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * 全局静态手机<br>
		 * 
		 */
		public static final String REGEXP_mob = "^[1-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * 全局静态只能输入五位整数两位小数的正浮点数对象<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_FIVE_DOUBLE = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为8位，小数部分最长为2位<br>
		 */
		public static final String NUM_EIGHT_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 全局静态只能输入数字
		 */
		public static final String REGEXP_NUM = "^[0-9]+$";

		/**
		 * 全局静态只能输入数字和字母
		 */
		public static final String ENGLISH_AND_NUM = "^[A-Za-z0-9]+$";
		/**
		 * 全局静态只能输入时间格式
		 */
		public static final String TIEM_FORAMT = "^([0-9]?[0-9]?(\\:)?[0-5]?[0-9]$)?";
		/**
		 * 全局静态邮箱校验
		 */
		public static final String Mail = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)*";
		// public static final String Mail="^[A-Za-z0-9_]@[A-Za-z].[A-Za-z]+$";

		/**
		 * 全局静态只能输入一位小数的正浮点数对象,整数部分最长为15位，没有小数部分<br>
		 */
		public static final String REGEXP_POSITIVE_ZERO_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 全局静态只能输入一位小数的正浮点数对象,整数部分最长为15位，小数部分最长为1位<br>
		 */
		public static final String REGEXP_POSITIVE_ONE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为15位，小数部分最长为2位<br>
		 */
		public static final String REGEXP_POSITIVE_DOUBLE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 全局静态只能输入三位小数的正浮点数对象,整数部分最长为15位，小数部分最长为3位<br>
		 */
		public static final String REGEXP_POSITIVE_THREE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * 全局静态只能输入四位小数的正浮点数对象,整数部分最长为15位，小数部分最长为4位<br>
		 */
		public static final String REGEXP_POSITIVE_FOUR_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * 小时正则表达式
		 */
		public static final String REGEXP_HOUR = "(^[0-9]$)|(^[0-1](\\d)?$)|(^[2][0-3]?$)";
		/**
		 * 分正则表达式
		 */
		public static final String REGEXP_MIN = "(^[0-9]$)|(^[0-5](\\d)?$)";

	}

	/**
	 * 在页签文本左右添加空格
	 * 
	 * @param str
	 *            要填空格的文本
	 * @return String 返回添加后的字符串
	 */
	public static String addSpace(String str) {
		return space + str + space;
	}

	/**
	 * <p>
	 * 入库类型
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	public interface InstoType {
		/**
		 * 采购入库
		 */
		String TYPE_BUY = "01";
		/**
		 * 销售退货
		 */
		String TYPE_SALE_CANCEL = "02";
		/**
		 * 其它入库
		 */
		String TYPE_OTHER = "03";
		/**
		 * 零星采购入库
		 */
		String TYPE_SPORADIC_BUY = "04";
		/**
		 * 零售退货
		 */
		String TYPE_RETAIL = "05";
		/**
		 * 隐藏类型
		 */
		String TYPE_HIDDEN = "06";
	}

	/**
	 * <p>
	 * 入库状态
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-16
	 */
	public interface instoState {
		/**
		 * 未入库
		 */
		String status_NONE = "01";
		/**
		 * 部分入库
		 */
		String status_LITTLE = "02";
		/**
		 * 全部入库
		 */
		String status_ALL = "03";

	}

	/**
	 * <p>
	 * 出库类型
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	public interface OutstoType {
		/**
		 * 销售出库
		 */
		String TYPE_SALE = "01";
		/**
		 * 采购退货
		 */
		String TYPE_BUY_CANCEL = "02";
		/**
		 * 其它出库
		 */
		String TYPE_OTHER = "03";
		/**
		 * 零售退货
		 */
		String TYPE_RETAIL = "04";
		/**
		 * 隐藏类型
		 */
		String TYPE_HIDDEN = "05";
	}

	/**
	 * <p>
	 * 出库状态
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-16
	 */
	public interface outstoState {
		/**
		 * 未出库
		 */
		String status_NONE = "01";
		/**
		 * 部分出库
		 */
		String status_LITTLE = "02";
		/**
		 * 全部出库
		 */
		String status_ALL = "03";

	}

	/**
	 * 部门下拉封装风格
	 */
	public final static int GRID_DATA = 1;
	public final static int ROW_DATA = 2;
	public final static String SEARCH_DEFAULT_KEY = "输入关键字";
	public final static String EMPTY_KEY = "";

	/**
	 * 采购、销售外部常量
	 * 
	 * @author modi
	 * 
	 */
	public interface BillsWithout {
		/**
		 * 未完成数据（客户/供应商模块页签）
		 */
		String FINISH_NO = "01";
		/**
		 * 已完成数据（客户/供应商模块页签）
		 */
		String FINISH_YES = "02";
	}
}
