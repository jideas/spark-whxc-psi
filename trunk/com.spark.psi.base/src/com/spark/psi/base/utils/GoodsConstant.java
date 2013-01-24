/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.common.goods.intf.constant
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-8       汤成国        
 * ============================================================*/

package com.spark.psi.base.utils;

import com.spark.common.utils.character.CheckIsNull;



/**
 * <p>商品模块常量类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 汤成国
 * @version 2011-11-8
 */

public class GoodsConstant{

	/**
	 * 
	 * <p>商品模块的正则表达式部分，用于限定控件内容的输入形式</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-18
	 */
	public static class RegexExpression {
		public final static String GOODS_NO_REG = "^[A-Za-z0-9]{0,20}$"; // 商品编号，只能输入20位字母和数字
		public final static String GOODS_NAME_REG = "^.{0,50}$"; //商品名称，能输入任意字符50位 
		public final static String GOODS_REMARK_REG = "^.{0,500}$"; //商品备注，能输入任意字符500位 
		public final static String GOODS_PROPERTY_REG = "^.{0,35}$"; //商品手工输入的属性，能输入任意字符35位 
	}
	
	/**
	 * 
	 * <p>商品模块权限</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-23
	 */
	public static class GoodsQuanXian {
		// 权限1（列表界面（停售、删除、恢复销售）、商品详情（设置销售价格））总经理、助理、销售经理、
		public final static String GOODS_QX_ONE = "GOODS_QX_ONE"; 
		
		// 权限2（列表界面（页签在售、停售）、商品详情（页签商品信息））总经理、助理、销售经理、销售员工、采购经理、采购员工、库管经理、库管员工 
		public final static String GOODS_QX_TWO = "GOODS_QX_TWO"; 
		
		// 权限3（列表界面（页签授权商品）、商品详情（查看最近采购价格、平均采购价格））总经理、助理、采购经理、采购员工
		public final static String GOODS_QX_THREE = "GOODS_QX_THREE"; 
		
		// 权限4（列表界面（管理分类按钮））总经理、助理、销售经理、采购经理、库管经理
		public final static String GOODS_QX_FOUR = "GOODS_QX_FOUR"; 
		
		// 权限5（列表界面（新建商品按钮、商品名称链接（编辑））、商品详情（编辑商品详情））总经理、助理、销售经理、采购经理、采购员工、库管经理、库管员工
		public final static String GOODS_QX_FIVE = "GOODS_QX_FIVE"; 
		
		// 权限6（列表界面（商品名称链接（查看））、商品详情（查看销售价格））销售员工
		public final static String GOODS_QX_SIX = "GOODS_QX_SIX"; 
		
		// 权限7（商品详情（页签库存信息）、商品详情（设置库存阈值））总经理、助理、库管经理
		public final static String GOODS_QX_SEVEN = "GOODS_QX_SEVEN"; 
		
		// 权限8（商品详情（页签销售情况））总经理、销售经理
		public final static String GOODS_QX_EIGHT = "GOODS_QX_EIGHT"; 

		// 权限9（商品详情（页签采购情况））总经理、采购经理、采购员工
		public final static String GOODS_QX_NINE = "GOODS_QX_NINE"; 
	}
	
	// 跟节点名称
	public final static String ROOT_TYPE_NAME = "全部";
	
	// 跟节点GUID
	public final static String ROOT_TYPE_RECID = "10000000000000000000000000000001";
	
	// 单位
	public final static String GOODS_UNIT = "单位";
	
	// 分类管理时的默认显示内容
	public final static String GOODS_TYPE_DEFAULT = "请输入分类名称";
	
	// 分类管理时的默认显示内容
	public final static String GOODS_PROPERTY_SP = "销售价格";
	
	// 编辑商品界面浮出采购价格框ID
	public final static String TONG_JITU_FW_ID = "tongJiTuFloatWindow";
	
	// 查询分类下包含自己及其子孙的所有节点的SQL语句
	public final static String QUERY_CHILIDREN_AND_SELF = " from sa_goods_type as t join sa_goods_type as s on" +
														  " s.PATH > t.PATH" +
														  " and s.PATH < t.PATH || bytes'ff' " +
//														  " and len(s.PATH) = len(t.PATH) + 34" +
//														  " and len(s.PATH) = len(t.PATH) " +
														  " or s.RECID=@goodsTypeGuid" +
														  " where t.RECID = @goodsTypeGuid" +
														  " and s.tenantsGuid=@tenantsGuid" +
														  " and s.yzFlag=@yzFlag";
	
	// 查询分类下不包含自己包含子孙的所有节点的SQL语句
	public final static String QUERY_CHILIDREN = " from sa_goods_type as t join sa_goods_type as s on" +
												 " s.PATH > t.PATH" +
												 " and s.PATH < t.PATH || bytes'ff' " +
//												 " and len(s.PATH) = len(t.PATH) + 34" +
//												 " and len(s.PATH) = len(t.PATH) " +
												 " where t.RECID = @goodsTypeGuid" + 
												 " and s.tenantsGuid=@tenantsGuid" +
												 " and s.yzFlag=@yzFlag";
	
	// 查询分类下包含自己及其子孙的所有节点的SQL语句
	public final static String QUERY_CHILIDREN_AND_SELF_AND_YZ = " from sa_goods_type as t join sa_goods_type as s on" +
														  " s.PATH > t.PATH" +
														  " and s.PATH < t.PATH || bytes'ff' " +
//														  " and len(s.PATH) = len(t.PATH) + 34" +
//														  " and len(s.PATH) = len(t.PATH) " +
														  " or s.RECID=@goodsTypeGuid" +
														  " where t.RECID = @goodsTypeGuid" +
														  " and s.tenantsGuid=@tenantsGuid";
	
	// 查询分类通用SQL语句
	public final static String QUERY_TONGYONG = " select s.RECID as recid, s.yzFlag as yzFlag, s.goodsCountDigit as goodsCountDigit";
	
	/**
	 * 
	 * <p>商品详情界面排序</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-12-1
	 */
	public static class GoodsInfoOrder {
		// 降序
		public final static String ORDER_DESC = " desc";
		
		// 升序
		public final static String ORDER_ASC = " asc";
		
		// 商品编号
		public final static String GOODS_NO = "goodsNo";
		
		// 商品名称
		public final static String GOODS_NAME = "goodsName";
		
	}
	
	/**
	 * 
	 * <p>授权商品界面排序</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-12-1
	 */
	public static class AuthGoodsOrder {
		// 降序
		public final static String ORDER_DESC = " desc";
		
		// 升序
		public final static String ORDER_ASC = " asc";
		
		// 授权供应商名称
		public final static String AUTH_PRO_NAME = " b.cusproName";
		
		// 授权商品名称
		public final static String AUTH_GOODS_NAME = " a.proGoodsName";
		
		// 授权商品属性
		public final static String AUTH_GOODS_PROPERTY = " a.proGoodsProperty";
		
		// 授权商品价格
		public final static String AUTH_GOODS_PRICE = " a.proSalePrice";
		
		// 授权商品名称
		public final static String GOODS_NO = " c.goodsNo";
		
		// 授权商品名称
		public final static String GOODS_NAME = " c.goodsName";
		
		// 授权商品名称
		public final static String GOODS_PROPERTY = " c.propertyValue";
		
		// 授权商品名称
		public final static String GOODS_UNIT = " c.goodsUnit";
	}
	
	/**
	 * 
	 * <p>商品属性输入类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum PropertyInputType {
		SG_SHURU("手工输入", "01"),
		XL_XUANZHE("下拉选择", "02");
		private String value;
		private String key;
		PropertyInputType(String value, String key) {
			this.value = value;
			this.key = key;
		}
		/**
         * @return 名称
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
         * 根据类型名称得到类型
         * 
         * @param value 类型名称
         * @return PropertyInputType 类型
         */
        public static PropertyInputType getTypeByValue(String value) {
        	if("手工输入".equals(value)) {
        		return PropertyInputType.SG_SHURU;
        	} else if("下拉选择".equals(value)) {
        		return PropertyInputType.XL_XUANZHE;
        	} else {
        		return null;
        	}
        }
        
        /**
         * 根据类型名称得到类型
         * 
         * @param CODE 类型名称
         * @return PropertyInputType 类型
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
	 * <p>商品状态类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsStatus {
		ON_SALE("在售", "01"),
		STOP_SALE("停售", "02"),
		ALL_SALE("属性既有停售又有在售", "03");
		private String value;
		private String key;
		GoodsStatus(String value, String key) {
			this.value = value;
			this.key = key;
		}
		/**
         * @return 名称
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
	 * <p>客户供应商类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum CusProType {
		PROVIDER("供应商", "01"),
		CUSTOMER("客户", "02");
		private String value;
		private String key;
		CusProType(String value, String key) {
			this.value = value;
			this.key = key;
		}
		/**
         * @return 名称
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
	 * <p>商品主列表界面的页签</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum TabTitle {
		ON_SALE_GOODS("在售商品"),
		STOP_SALE_GOODS("停售商品"),
		PROVIDER_GRAND_GOODS("授权商品");
		private String value;
		TabTitle(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品商品分类设置类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum FenLeiExpandType {
		ZJ_SHEZHI("子节点已经设置"),
		FJ_SHEZHI("父节点已经设置"),
		ZFJ_WEISHEZHI("子父节点都没有设置"),
		ROOT_JD("跟节点");
		private String value;
		FenLeiExpandType(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品详情界面类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsDetailType {
		XIN_JIAN("新建"),
		BIAN_JI("编辑"),
		CHA_KAN("查看");
		private String value;
		GoodsDetailType(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品详情页签枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsDetailTabTitle {
		GOODS_INFO("商品信息"),
		STORE_INFO("库存信息"),
		BUY_INFO("采购记录"),
		SALE_INFO("销售记录");
		private String value;
		GoodsDetailTabTitle(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品详情页面属性字段布局控制类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsDetailTableType {
		XJ_CUOHANG("新建_错行"),
		XJ_BUCUOHANG("新建_不错行"),
		XJ_YINCANG("新建_隐藏"),
		BJCK_YINCANG("编辑查看_隐藏"),
		BJCK_XSXSCH("编辑查看_显示销售信息，错行"),
		BJCK_XSXSBCH("编辑查看_显示销售信息，不错行"),
		BJCK_XSCGCH("编辑查看_显示采购信息，错行"),
		BJCK_XSCGBCH("编辑查看_显示采购信息，不错行"),
		BJCK_GDQXS("编辑查看_固定字段全显示");
		private String value;
		GoodsDetailTableType(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>库存阈值控制范围枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum KuCunYuZhiFanWei {
		ZONG_KUCUN("总库存"),
		FEN_CANGKU("分仓库");
		private String value;
		KuCunYuZhiFanWei(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>库存阈值类型枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum KuCunYuZhiLeiXing {
		SP_JINE("商品金额"),
		SP_SHULIANG("商品数量");
		private String value;
		KuCunYuZhiLeiXing(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品分类单位属性默认项目枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsUnitDefault {
		TAI("台"),
		TAO("套"),
		ZHI("只"),
		GE("个"),
		GONGJIN("公斤"),
		JIN("斤"),
		XIANG("箱"),
		MI("米"),
		PING("瓶"),
		BAO("包"),
		DA("打"),
		ZHANG("张");
		private String value;
		GoodsUnitDefault(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品分类管理界面按钮情况枚举</p>
	 * <p>其中按钮包括“新增”、“删除”，当节点为叶子节点时，若已关联商品，</p>
	 * <p>则新增、删除按钮均置灰，否则新增、删除按钮均激活；</p>
	 * <p>若节点为非叶子节点时，如果其下属叶子节点已经关联商品，则新增激活，删除置灰，否则新增、删除均激活</p>
	 * <p>若节点为全部节点时，新增激活，删除置灰</p>
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsTypeBtnType {
		YZ_GLSP("叶子节点关联商品"),
		YZ_BU_GLSP("叶子节点没关联商品"),
		FYZ_GLSP("非叶子节点关联商品"),
		FYZ_BU_GLSP("非叶子节点没关联商品"),
		ROOT("根节点");
		private String value;
		GoodsTypeBtnType(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品编辑界面填充表格的形式，分为初始化添加和电机添加详情按钮添加两种情况</p>
	 * 
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum GoodsBianJiJialie {
		INIT_ADD("初始化添加"),
		BTN_ADD("点击按钮添加");
		private String value;
		GoodsBianJiJialie(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品编辑界面填充表格的形式，分为初始化添加和电机添加详情按钮添加两种情况</p>
	 * 
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum TongJiType {
		HISTORY_BUYSALE("历史采购/销售情况"),
		SALE_TARGET("销售目标监控");
		private String value;
		TongJiType(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 
	 * <p>商品分类列表刷新类型</p>
	 * 
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 汤成国
	 * @version 2011-11-8
	 */
	public enum FlushTypeRowLabelType {
		TYPE_AREA("商品分类处"),
		GOODS_AREA("商品列表处");
		private String value;
		FlushTypeRowLabelType(String value) {
			this.value = value;
		}
		/**
         * @return 名称
         */
        public String getValue(){
        	return value;
        }
	}
	
	/**
	 * 得到商品级数
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
