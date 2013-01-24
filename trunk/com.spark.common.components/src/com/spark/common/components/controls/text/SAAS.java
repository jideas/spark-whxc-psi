package com.spark.common.components.controls.text;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;

public class SAAS {
	public static String SHOW_NULL_STR = "--";

	/**
	 * ��Ʒ���Ԥ������
	 */
	public static enum Goods_Tips_Type {
		/**
		 * ���ܿ�棬��Ʒ���
		 */
		ALL_AMOUNT("01"),
		/**
		 * ���ܿ�棬��Ʒ����
		 */
		ALL_COUTN("02"),
		/**
		 * ���ֿ�棬��Ʒ���
		 */
		STORAGE_AMOUNT("03"),
		/**
		 * ���ֿ�棬��Ʒ����
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
	 * �����ֵ�ö��
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author ������
	 * @version 2011-11-28
	 */
	public enum DictionaryType {
		SEX("sex"),

		/**
		 * �������
		 */
		INSTOTYPE("instotypes"),
		/**
		 * ���״̬
		 */
		instoState("instoStates"),
		/**
		 * ����״����
		 */
		OUTSTOTYPE("outstotypes"),
		/**
		 * ����״̬
		 */
		outstoState("outstoStates"),
		/**
		 * ��������
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
	 * sessionʧЧʱ�䣬Ĭ��5����
	 */
	public final static int SESSIONTIME = 15 * 60 * 1000;
	/**
	 * Ԥ������
	 */
	public final static int waringDay = 3;
	/**
	 * ��Ӧ��ֱ��������ֿ�id������
	 */
	public final static GUID PROVIDERSOTRE = GUID
			.valueOf("00000000000000000000000000000000");
	public final static String PROVIDERSOTRENAME = "��Ӧ��ֱ��";

	/**
	 * <p>
	 * ����ģ��ƴ����ʶ
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-16
	 */
	public interface FunStr {

		/**
		 * ���ģ����
		 */
		String INSTO = "ruku";
		/**
		 * ����ģ����
		 */
		String OUTSTO = "chuku";
		/**
		 * �������ģ����
		 */
		String DEPLOY = "xsph";
	}

	/**
	 * 
	 * <p>
	 * ��������
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author ������
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
	 * ��Ӫ���� --����(���ꡢ����)
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author ������
	 * @version 2011-12-16
	 */
	public enum REPORT_YEARORMONTH {
		YEAR, MONTH;
	}

	/**
	 * <p>
	 * ���ݿ����ö��
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	public enum TaskMethod {
		INSERT, MODFIY, DELETE,
		/**
		 * ��������״̬
		 */
		CHANGEPROCESS,
		/**
		 * ��̬
		 */
		EXECUTE;

	}

	/**
	 * <p>
	 * �ͻ���Ӧ������
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	public enum CusType {
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
				return "�ͻ�";
			case PROVIDER:
				return "��Ӧ��";
			}
			return "";
		}
	}

	/**
	 * <p>
	 * ��Ȩ��Ʒ״̬
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ������
	 * @version 2011-11-17
	 */
	public enum GoodsStatus {
		/**
		 * �������µ�
		 */
		GOOD_ZXXD,
		/**
		 * ���������µ�
		 */
		GOOD_NOZXXD,
		/**
		 *ͣ����Ʒ
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
				return "�������µ�";
			case GOOD_NOZXXD:
				return "���������µ�";
			case GOOD_TSSP:
				return "ͣ����Ʒ";
			}
			return "";
		}
	}

	/**
	 * �����ؼ��ĳ���
	 * 
	 * @author ��־��
	 * @version 2011-10-13
	 */
	public interface CONTROLS {

		String AREA_LABEL = "�ء�����";

		/**
		 * <p>
		 * �����ؼ�������
		 * </p>
		 * 
		 * @author ��־��
		 * @version 2011-10-18
		 */
		public enum SearchType {
			/**
			 * һ���ı���������ť
			 */
			TEXT_BUTTON_BUTTON_FORGOOD,
			/**
			 * ���зŴ󾵵��ı����һ���߼������İ�ť
			 */
			TEXT_IMG_BUTTON,
			/**
			 * һ���ı���һ����ť
			 */
			TEXT_BUTTON
		}
	}

	/**
	 * ������������ɫֵ
	 */
	// public static final int NO_LINK_FONTCOLOR = 0xCCCCCC;
	public static final int NO_LINK_FONTCOLOR = 0x000000;
	/**
	 * ���ñ����������������
	 */
	public static final int TxtKindCN = 0;// ����
	public static final int TxtKindNM = 1;// ���֣���ĸ�������ֺ���ĸ�����
	/**
	 * �м��
	 */
	public static final int horizontalSpacing = 15;

	/**
	 * �м��
	 */
	public static final int verticalSpacing = 1;

	/**
	 * ������ʽ
	 */
	public static final int SEARCH_STYLE = 1;
	/**
	 * �߼�����
	 */
	public static final int ADVANCE_STYLE = 2;

	/**
	 * ҳǩ�������ҵĿո�
	 */
	private static final String space = "   ";

	public static class Layout {
		/**
		 * ȫ�־�̬FillLayout����<br>
		 * <strong>ע�����ܸ�fillLayoutIns�ĳ�Ա��ֵ�������Ӱ��ȫ�ֲ���</strong>
		 */
		public static final FillLayout fillLayoutIns = new FillLayout();

		/**
		 * ȫ�־�̬����griddata���ֶ��� �����˺�����䣬�߶�20����
		 * <strong>ע�����ܸ�fillLayoutIns�ĳ�Ա��ֵ�������Ӱ��ȫ�ֲ���</strong>
		 */
		public static final GridData gridDataLineIns = new GridData(
				GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);

		static {
			gridDataLineIns.heightHint = 20;
		}
	}

	/**
	 * ���������
	 * 
	 * @author zhangyu
	 * 
	 */
	public static class Grid {

		public final static int operWidth = 41;
		/**
		 * �����п��
		 */
		public final static int HUANBI_WIDTH = 60;

		private static Font font = new Font(9, "����", JWT.FONT_STYLE_UNDERLINE);
		private static Font commonFont = new Font();
		private static Font GridCNFont = new Font(9, "����", JWT.FONT_STYLE_PLAIN);

		public static Font getGridCNFont() {
			return GridCNFont;
		}

		private static Font GridNumFont = new Font(9, "Arial",
				JWT.FONT_STYLE_PLAIN);

		/**
		 * ����ʼ����
		 */
		public static final int pageSize = 20;
		/**
		 * ����ʼ����
		 */
		public static final int detailPageSize = 1;

		public static Font getGridNumFont() {
			return GridNumFont;
		}

		/**
		 * ȡ�ñ�������������
		 * 
		 * @return
		 */
		public static final Font getLinkFont() {
			return font;
		}

		/**
		 * ȡ�ñ�������������
		 * 
		 * @return
		 */
		public static final Font getCommonFont() {
			return commonFont;
		}

		/**
		 * ȡ�ñ������������ɫֵ
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
		 * ��༸λС����������ʽ
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
		 * ����������ʽ
		 * 
		 * @param integer
		 *            ��������λ��
		 * @param decimal
		 *            С������λ����С������λ����
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
		 * ȫ�־�ֻ̬��������λС����������������<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_DOUBLE = "^\\d*(\\.)?(\\d)?(\\d)?$";

		/**
		 * ����������ĸ�»���
		 */
		public static final String TEXT = "^[a-zA-Z0-9_/u4e00-/u9fa5]+$";
		public static final String REGEXP_POSITIVE_DOUBLE_FUSHU_LIMIT = "^([+-]?\\d{0,15}(\\.\\d{0,2})?)$";

		/**
		 * ȫ�־�̬�̻��ʹ�����֤ ���ܸ�ʽ XXXX-XXXX-XXX ����XXXX�������֣������޳���
		 */
		public static final String REGEXP_PHONE = "(^\\d*)([+-]?)(\\d*)([+-]?)(\\d*$)";

		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ8λ��С�������Ϊ2λ<br>
		 */
		public static final String NUM_TEN_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * �ۿ۶��5��2��0%-100%
		 */
		public static final String NUM_FIVE_TWO = "(^(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?$)|(^[1][0][0]$)";
		/**
		 * ȫ�־�̬�ʱ�<br>
		 * 
		 */
		public static final String REGEXP_POSTCODE = "^[0-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * ȫ�־�̬�ֻ�<br>
		 * 
		 */
		public static final String REGEXP_mob = "^[1-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

		/**
		 * ȫ�־�ֻ̬��������λ������λС����������������<br>
		 * 
		 */
		public static final String REGEXP_POSITIVE_FIVE_DOUBLE = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ8λ��С�������Ϊ2λ<br>
		 */
		public static final String NUM_EIGHT_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * ȫ�־�ֻ̬����������
		 */
		public static final String REGEXP_NUM = "^[0-9]+$";

		/**
		 * ȫ�־�ֻ̬���������ֺ���ĸ
		 */
		public static final String ENGLISH_AND_NUM = "^[A-Za-z0-9]+$";
		/**
		 * ȫ�־�ֻ̬������ʱ���ʽ
		 */
		public static final String TIEM_FORAMT = "^([0-9]?[0-9]?(\\:)?[0-5]?[0-9]$)?";
		/**
		 * ȫ�־�̬����У��
		 */
		public static final String Mail = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)*";
		// public static final String Mail="^[A-Za-z0-9_]@[A-Za-z].[A-Za-z]+$";

		/**
		 * ȫ�־�ֻ̬������һλС����������������,���������Ϊ15λ��û��С������<br>
		 */
		public static final String REGEXP_POSITIVE_ZERO_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * ȫ�־�ֻ̬������һλС����������������,���������Ϊ15λ��С�������Ϊ1λ<br>
		 */
		public static final String REGEXP_POSITIVE_ONE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ2λ<br>
		 */
		public static final String REGEXP_POSITIVE_DOUBLE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ3λ<br>
		 */
		public static final String REGEXP_POSITIVE_THREE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
		/**
		 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ4λ<br>
		 */
		public static final String REGEXP_POSITIVE_FOUR_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

		/**
		 * Сʱ������ʽ
		 */
		public static final String REGEXP_HOUR = "(^[0-9]$)|(^[0-1](\\d)?$)|(^[2][0-3]?$)";
		/**
		 * ��������ʽ
		 */
		public static final String REGEXP_MIN = "(^[0-9]$)|(^[0-5](\\d)?$)";

	}

	/**
	 * ��ҳǩ�ı�������ӿո�
	 * 
	 * @param str
	 *            Ҫ��ո���ı�
	 * @return String ������Ӻ���ַ���
	 */
	public static String addSpace(String str) {
		return space + str + space;
	}

	/**
	 * <p>
	 * �������
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	public interface InstoType {
		/**
		 * �ɹ����
		 */
		String TYPE_BUY = "01";
		/**
		 * �����˻�
		 */
		String TYPE_SALE_CANCEL = "02";
		/**
		 * �������
		 */
		String TYPE_OTHER = "03";
		/**
		 * ���ǲɹ����
		 */
		String TYPE_SPORADIC_BUY = "04";
		/**
		 * �����˻�
		 */
		String TYPE_RETAIL = "05";
		/**
		 * ��������
		 */
		String TYPE_HIDDEN = "06";
	}

	/**
	 * <p>
	 * ���״̬
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-16
	 */
	public interface instoState {
		/**
		 * δ���
		 */
		String status_NONE = "01";
		/**
		 * �������
		 */
		String status_LITTLE = "02";
		/**
		 * ȫ�����
		 */
		String status_ALL = "03";

	}

	/**
	 * <p>
	 * ��������
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	public interface OutstoType {
		/**
		 * ���۳���
		 */
		String TYPE_SALE = "01";
		/**
		 * �ɹ��˻�
		 */
		String TYPE_BUY_CANCEL = "02";
		/**
		 * ��������
		 */
		String TYPE_OTHER = "03";
		/**
		 * �����˻�
		 */
		String TYPE_RETAIL = "04";
		/**
		 * ��������
		 */
		String TYPE_HIDDEN = "05";
	}

	/**
	 * <p>
	 * ����״̬
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-16
	 */
	public interface outstoState {
		/**
		 * δ����
		 */
		String status_NONE = "01";
		/**
		 * ���ֳ���
		 */
		String status_LITTLE = "02";
		/**
		 * ȫ������
		 */
		String status_ALL = "03";

	}

	/**
	 * ����������װ���
	 */
	public final static int GRID_DATA = 1;
	public final static int ROW_DATA = 2;
	public final static String SEARCH_DEFAULT_KEY = "����ؼ���";
	public final static String EMPTY_KEY = "";

	/**
	 * �ɹ��������ⲿ����
	 * 
	 * @author modi
	 * 
	 */
	public interface BillsWithout {
		/**
		 * δ������ݣ��ͻ�/��Ӧ��ģ��ҳǩ��
		 */
		String FINISH_NO = "01";
		/**
		 * ��������ݣ��ͻ�/��Ӧ��ģ��ҳǩ��
		 */
		String FINISH_YES = "02";
	}
}
