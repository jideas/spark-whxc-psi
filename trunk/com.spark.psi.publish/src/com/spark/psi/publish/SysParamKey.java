package com.spark.psi.publish;

/**
 * <p>租户系统参数key，名称不能超过25</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-3
 */

public enum SysParamKey{
	/** 配置向导=》 已经初始化欢迎界面  */
	HAS_INIT_WELCOME,
	/**  配置向导=》 已经初始化企业信息配置 */
	HAS_INIT_COMPANY,
	/**  配置向导=》 已经初始化部门  */
	HAS_INIT_DEPT,
	/**  配置向导=》 已经初始化员工  */
	HAS_INIT_EMP,
	/**  配置向导=》 已经初始化客户  */
	HAS_INIT_CUSPRO,
	/**  配置向导=》 已经初始化供应商  */
	HAS_INIT_PROVIDER,
	/**  配置向导=》 已经初始化商品分类  */
	HAS_INIT_GOODSTYPE,
	/**  配置向导=》 已经初始化商品  */
	HAS_INIT_GOODS,
	/**  配置向导=》 已经初始化仓库  */
	HAS_INIT_STORE,
	/**  配置向导=》 已经启动系统  */
	HAS_INIT_START,
	/** 客户代表配置向导=》配置企业信息 */
	HAS_KHDBINIT_COMPANY,
	/** 客户代表配置向导=》配置部门信息 */	
	HAS_KHDBINIT_DEPT,
	/** 客户代表配置向导=》配置企业信息 */		
	HAS_KHDBINIT_FORM,
	/** 客户代表配置向导=》配置企业信息 */		
	HAS_KHDBINIT_BOSS,		
	/** 客户/供应商是否初始化 */
	CUSPRO_INIT,
	/** 是否开启供应商直送客户模式 */
	STORE_DIRECT,
	/** 商品库存预警提示类型 */
	GOODS_TIPS_TYPE;
}
