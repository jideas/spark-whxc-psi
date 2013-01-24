/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.common.goods.intf.constant
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-8       ���ɹ�        
 * ============================================================*/

package com.spark.psi.base.utils;

import com.spark.common.utils.character.CheckIsNull;



/**
 * <p>��Ʒģ�鳣����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ���ɹ�
 * @version 2011-11-8
 */

public class GoodsConstant{

	/**
	 * 
	 * <p>��Ʒģ���������ʽ���֣������޶��ؼ����ݵ�������ʽ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-18
	 */
	public static class RegexExpression {
		public final static String GOODS_NO_REG = "^[A-Za-z0-9]{0,20}$"; // ��Ʒ��ţ�ֻ������20λ��ĸ������
		public final static String GOODS_NAME_REG = "^.{0,50}$"; //��Ʒ���ƣ������������ַ�50λ 
		public final static String GOODS_REMARK_REG = "^.{0,500}$"; //��Ʒ��ע�������������ַ�500λ 
		public final static String GOODS_PROPERTY_REG = "^.{0,35}$"; //��Ʒ�ֹ���������ԣ������������ַ�35λ 
	}
	
	/**
	 * 
	 * <p>��Ʒģ��Ȩ��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-23
	 */
	public static class GoodsQuanXian {
		// Ȩ��1���б���棨ͣ�ۡ�ɾ�����ָ����ۣ�����Ʒ���飨�������ۼ۸񣩣��ܾ����������۾���
		public final static String GOODS_QX_ONE = "GOODS_QX_ONE"; 
		
		// Ȩ��2���б���棨ҳǩ���ۡ�ͣ�ۣ�����Ʒ���飨ҳǩ��Ʒ��Ϣ�����ܾ����������۾�������Ա�����ɹ������ɹ�Ա������ܾ������Ա�� 
		public final static String GOODS_QX_TWO = "GOODS_QX_TWO"; 
		
		// Ȩ��3���б���棨ҳǩ��Ȩ��Ʒ������Ʒ���飨�鿴����ɹ��۸�ƽ���ɹ��۸񣩣��ܾ��������ɹ������ɹ�Ա��
		public final static String GOODS_QX_THREE = "GOODS_QX_THREE"; 
		
		// Ȩ��4���б���棨������ఴť�����ܾ����������۾����ɹ�������ܾ���
		public final static String GOODS_QX_FOUR = "GOODS_QX_FOUR"; 
		
		// Ȩ��5���б���棨�½���Ʒ��ť����Ʒ�������ӣ��༭��������Ʒ���飨�༭��Ʒ���飩���ܾ����������۾����ɹ������ɹ�Ա������ܾ������Ա��
		public final static String GOODS_QX_FIVE = "GOODS_QX_FIVE"; 
		
		// Ȩ��6���б���棨��Ʒ�������ӣ��鿴��������Ʒ���飨�鿴���ۼ۸񣩣�����Ա��
		public final static String GOODS_QX_SIX = "GOODS_QX_SIX"; 
		
		// Ȩ��7����Ʒ���飨ҳǩ�����Ϣ������Ʒ���飨���ÿ����ֵ�����ܾ���������ܾ���
		public final static String GOODS_QX_SEVEN = "GOODS_QX_SEVEN"; 
		
		// Ȩ��8����Ʒ���飨ҳǩ������������ܾ������۾���
		public final static String GOODS_QX_EIGHT = "GOODS_QX_EIGHT"; 

		// Ȩ��9����Ʒ���飨ҳǩ�ɹ���������ܾ����ɹ������ɹ�Ա��
		public final static String GOODS_QX_NINE = "GOODS_QX_NINE"; 
	}
	
	// ���ڵ�����
	public final static String ROOT_TYPE_NAME = "ȫ��";
	
	// ���ڵ�GUID
	public final static String ROOT_TYPE_RECID = "10000000000000000000000000000001";
	
	// ��λ
	public final static String GOODS_UNIT = "��λ";
	
	// �������ʱ��Ĭ����ʾ����
	public final static String GOODS_TYPE_DEFAULT = "�������������";
	
	// �������ʱ��Ĭ����ʾ����
	public final static String GOODS_PROPERTY_SP = "���ۼ۸�";
	
	// �༭��Ʒ���渡���ɹ��۸��ID
	public final static String TONG_JITU_FW_ID = "tongJiTuFloatWindow";
	
	// ��ѯ�����°����Լ�������������нڵ��SQL���
	public final static String QUERY_CHILIDREN_AND_SELF = " from sa_goods_type as t join sa_goods_type as s on" +
														  " s.PATH > t.PATH" +
														  " and s.PATH < t.PATH || bytes'ff' " +
//														  " and len(s.PATH) = len(t.PATH) + 34" +
//														  " and len(s.PATH) = len(t.PATH) " +
														  " or s.RECID=@goodsTypeGuid" +
														  " where t.RECID = @goodsTypeGuid" +
														  " and s.tenantsGuid=@tenantsGuid" +
														  " and s.yzFlag=@yzFlag";
	
	// ��ѯ�����²������Լ�������������нڵ��SQL���
	public final static String QUERY_CHILIDREN = " from sa_goods_type as t join sa_goods_type as s on" +
												 " s.PATH > t.PATH" +
												 " and s.PATH < t.PATH || bytes'ff' " +
//												 " and len(s.PATH) = len(t.PATH) + 34" +
//												 " and len(s.PATH) = len(t.PATH) " +
												 " where t.RECID = @goodsTypeGuid" + 
												 " and s.tenantsGuid=@tenantsGuid" +
												 " and s.yzFlag=@yzFlag";
	
	// ��ѯ�����°����Լ�������������нڵ��SQL���
	public final static String QUERY_CHILIDREN_AND_SELF_AND_YZ = " from sa_goods_type as t join sa_goods_type as s on" +
														  " s.PATH > t.PATH" +
														  " and s.PATH < t.PATH || bytes'ff' " +
//														  " and len(s.PATH) = len(t.PATH) + 34" +
//														  " and len(s.PATH) = len(t.PATH) " +
														  " or s.RECID=@goodsTypeGuid" +
														  " where t.RECID = @goodsTypeGuid" +
														  " and s.tenantsGuid=@tenantsGuid";
	
	// ��ѯ����ͨ��SQL���
	public final static String QUERY_TONGYONG = " select s.RECID as recid, s.yzFlag as yzFlag, s.goodsCountDigit as goodsCountDigit";
	
	/**
	 * 
	 * <p>��Ʒ�����������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-12-1
	 */
	public static class GoodsInfoOrder {
		// ����
		public final static String ORDER_DESC = " desc";
		
		// ����
		public final static String ORDER_ASC = " asc";
		
		// ��Ʒ���
		public final static String GOODS_NO = "goodsNo";
		
		// ��Ʒ����
		public final static String GOODS_NAME = "goodsName";
		
	}
	
	/**
	 * 
	 * <p>��Ȩ��Ʒ��������</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-12-1
	 */
	public static class AuthGoodsOrder {
		// ����
		public final static String ORDER_DESC = " desc";
		
		// ����
		public final static String ORDER_ASC = " asc";
		
		// ��Ȩ��Ӧ������
		public final static String AUTH_PRO_NAME = " b.cusproName";
		
		// ��Ȩ��Ʒ����
		public final static String AUTH_GOODS_NAME = " a.proGoodsName";
		
		// ��Ȩ��Ʒ����
		public final static String AUTH_GOODS_PROPERTY = " a.proGoodsProperty";
		
		// ��Ȩ��Ʒ�۸�
		public final static String AUTH_GOODS_PRICE = " a.proSalePrice";
		
		// ��Ȩ��Ʒ����
		public final static String GOODS_NO = " c.goodsNo";
		
		// ��Ȩ��Ʒ����
		public final static String GOODS_NAME = " c.goodsName";
		
		// ��Ȩ��Ʒ����
		public final static String GOODS_PROPERTY = " c.propertyValue";
		
		// ��Ȩ��Ʒ����
		public final static String GOODS_UNIT = " c.goodsUnit";
	}
	
	/**
	 * 
	 * <p>��Ʒ������������ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum PropertyInputType {
		SG_SHURU("�ֹ�����", "01"),
		XL_XUANZHE("����ѡ��", "02");
		private String value;
		private String key;
		PropertyInputType(String value, String key) {
			this.value = value;
			this.key = key;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
		/**
         * @return the key
         */
        public String getKey(){
        	return key;
        }
        
        /**
         * �����������Ƶõ�����
         * 
         * @param value ��������
         * @return PropertyInputType ����
         */
        public static PropertyInputType getTypeByValue(String value) {
        	if("�ֹ�����".equals(value)) {
        		return PropertyInputType.SG_SHURU;
        	} else if("����ѡ��".equals(value)) {
        		return PropertyInputType.XL_XUANZHE;
        	} else {
        		return null;
        	}
        }
        
        /**
         * �����������Ƶõ�����
         * 
         * @param CODE ��������
         * @return PropertyInputType ����
         */
        public static PropertyInputType getTypeByKey(String key) {
        	if("01".equals(key)) {
        		return PropertyInputType.SG_SHURU;
        	} else if("02".equals(key)) {
        		return PropertyInputType.XL_XUANZHE;
        	} else {
        		return null;
        	}
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ״̬����ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsStatus {
		ON_SALE("����", "01"),
		STOP_SALE("ͣ��", "02"),
		ALL_SALE("���Լ���ͣ����������", "03");
		private String value;
		private String key;
		GoodsStatus(String value, String key) {
			this.value = value;
			this.key = key;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
		/**
         * @return the key
         */
        public String getKey(){
        	return key;
        }
        
        public static GoodsStatus getGoodsStatusByKey(String key) {
        	if("01".equals(key)) {
        		return GoodsStatus.ON_SALE;
        	} else if("02".equals(key)) {
        		return GoodsStatus.STOP_SALE;
        	} else if("03".equals(key)) {
        		return GoodsStatus.ALL_SALE;
        	} else {
        		return null;
        	}
        }
	}
	
	/**
	 * 
	 * <p>�ͻ���Ӧ������ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum CusProType {
		PROVIDER("��Ӧ��", "01"),
		CUSTOMER("�ͻ�", "02");
		private String value;
		private String key;
		CusProType(String value, String key) {
			this.value = value;
			this.key = key;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
		/**
         * @return the key
         */
        public String getKey(){
        	return key;
        }
        public static CusProType getCusProTypeByKey(String key) {
        	if("01".equals(key)) {
        		return PROVIDER;
        	} else if("02".equals(key)) {
        		return CUSTOMER;
        	} else {
        		return null;
        	}
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ���б�����ҳǩ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum TabTitle {
		ON_SALE_GOODS("������Ʒ"),
		STOP_SALE_GOODS("ͣ����Ʒ"),
		PROVIDER_GRAND_GOODS("��Ȩ��Ʒ");
		private String value;
		TabTitle(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ��Ʒ������������ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum FenLeiExpandType {
		ZJ_SHEZHI("�ӽڵ��Ѿ�����"),
		FJ_SHEZHI("���ڵ��Ѿ�����"),
		ZFJ_WEISHEZHI("�Ӹ��ڵ㶼û������"),
		ROOT_JD("���ڵ�");
		private String value;
		FenLeiExpandType(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ�����������ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsDetailType {
		XIN_JIAN("�½�"),
		BIAN_JI("�༭"),
		CHA_KAN("�鿴");
		private String value;
		GoodsDetailType(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ����ҳǩö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsDetailTabTitle {
		GOODS_INFO("��Ʒ��Ϣ"),
		STORE_INFO("�����Ϣ"),
		BUY_INFO("�ɹ���¼"),
		SALE_INFO("���ۼ�¼");
		private String value;
		GoodsDetailTabTitle(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ����ҳ�������ֶβ��ֿ�������ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsDetailTableType {
		XJ_CUOHANG("�½�_����"),
		XJ_BUCUOHANG("�½�_������"),
		XJ_YINCANG("�½�_����"),
		BJCK_YINCANG("�༭�鿴_����"),
		BJCK_XSXSCH("�༭�鿴_��ʾ������Ϣ������"),
		BJCK_XSXSBCH("�༭�鿴_��ʾ������Ϣ��������"),
		BJCK_XSCGCH("�༭�鿴_��ʾ�ɹ���Ϣ������"),
		BJCK_XSCGBCH("�༭�鿴_��ʾ�ɹ���Ϣ��������"),
		BJCK_GDQXS("�༭�鿴_�̶��ֶ�ȫ��ʾ");
		private String value;
		GoodsDetailTableType(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>�����ֵ���Ʒ�Χö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum KuCunYuZhiFanWei {
		ZONG_KUCUN("�ܿ��"),
		FEN_CANGKU("�ֲֿ�");
		private String value;
		KuCunYuZhiFanWei(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>�����ֵ����ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum KuCunYuZhiLeiXing {
		SP_JINE("��Ʒ���"),
		SP_SHULIANG("��Ʒ����");
		private String value;
		KuCunYuZhiLeiXing(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ���൥λ����Ĭ����Ŀö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsUnitDefault {
		TAI("̨"),
		TAO("��"),
		ZHI("ֻ"),
		GE("��"),
		GONGJIN("����"),
		JIN("��"),
		XIANG("��"),
		MI("��"),
		PING("ƿ"),
		BAO("��"),
		DA("��"),
		ZHANG("��");
		private String value;
		GoodsUnitDefault(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ���������水ť���ö��</p>
	 * <p>���а�ť����������������ɾ���������ڵ�ΪҶ�ӽڵ�ʱ�����ѹ�����Ʒ��</p>
	 * <p>��������ɾ����ť���ûң�����������ɾ����ť�����</p>
	 * <p>���ڵ�Ϊ��Ҷ�ӽڵ�ʱ�����������Ҷ�ӽڵ��Ѿ�������Ʒ�����������ɾ���ûң�����������ɾ��������</p>
	 * <p>���ڵ�Ϊȫ���ڵ�ʱ���������ɾ���û�</p>
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsTypeBtnType {
		YZ_GLSP("Ҷ�ӽڵ������Ʒ"),
		YZ_BU_GLSP("Ҷ�ӽڵ�û������Ʒ"),
		FYZ_GLSP("��Ҷ�ӽڵ������Ʒ"),
		FYZ_BU_GLSP("��Ҷ�ӽڵ�û������Ʒ"),
		ROOT("���ڵ�");
		private String value;
		GoodsTypeBtnType(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ�༭������������ʽ����Ϊ��ʼ����Ӻ͵��������鰴ť����������</p>
	 * 
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum GoodsBianJiJialie {
		INIT_ADD("��ʼ�����"),
		BTN_ADD("�����ť���");
		private String value;
		GoodsBianJiJialie(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ�༭������������ʽ����Ϊ��ʼ����Ӻ͵��������鰴ť����������</p>
	 * 
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum TongJiType {
		HISTORY_BUYSALE("��ʷ�ɹ�/�������"),
		SALE_TARGET("����Ŀ����");
		private String value;
		TongJiType(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>��Ʒ�����б�ˢ������</p>
	 * 
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author ���ɹ�
	 * @version 2011-11-8
	 */
	public enum FlushTypeRowLabelType {
		TYPE_AREA("��Ʒ���ദ"),
		GOODS_AREA("��Ʒ�б�");
		private String value;
		FlushTypeRowLabelType(String value) {
			this.value = value;
		}
		/**
         * @return ����
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * �õ���Ʒ����
	 * 
	 * @param path
	 * @return int
	 */
	public static int getGoodsTypeJishu(String path) {
		int jishu = 0;
		if(CheckIsNull.isEmpty(path)) {
			return 0;
		}
		int lenth = path.length();
		jishu = lenth/34;
		return jishu;
	}
}
