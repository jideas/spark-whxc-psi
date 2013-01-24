package com.spark.psi.base;

/**
 * 
 * <p>编号生成器</p>
 * 
 * 查询方法 context.get(String,CodeBuilder)


 *
 
 * @version 2012-3-12
 */
public enum CodeBuilder{

		/**
		 * 1.销售管理部分
		 */
		// 销售订单模版设置
		SALES_BILLS("11","salesBills"),
		
		// 销售退货单模版设置
		SALES_RETURN("12","salesReturn"),
		
		// 销售开票模版设置
		SALES_BILLING("13","salesBilling"),
		
		/**
		 * 2.采购管理
		 */
		//采购订单模版设置
		BUY_ORDER("21","buyOrder"),
		
		// 采购退货单
		BUY_RETURN("22","buyReturn"),
		
		// 付款通知单模版设置
//		PAY_NOTICE("23","payNotice"),
		
		/**
		 * 3.库存管理
		 */
		// 入库单模版设置
		INSTORE("31","inStore"),
		
		// 出库单模版设置
		OUTSTORE("32","outStore"),
		
		// 盘点单模版设置
		CHECKSTORE("33","checkStore"),
		
		// 调配单模版设置
		BLENDING("34","blending"),
		
		// 拆装单模版设置
		DISMOUNTING("35","dismounting"),
		
		/**
		 * 4.财务管理
		 */
		// 收款单模版设置
		RECEIPT("41","receipt"),
		
		// 付款单模版设置
		PAYING("42","paying"),
		
		// 报销单模版设置
		APPLY("43","apply"),
		
		// 往来调整单
		WANGLAI("44","wanglai"),
		
//		// 转账单模版设置
//		TRANSFER("44","transfer"),
		
		/**
		 * 5.实体管理
		 */
		// 商品信息管理模版设置
		GOODS_INFO("51","goodsInfo"),
		
		// 客户信息模版设置
		CUSTOMER_INFO("52","customerInfo"),
		
		// 供应商信息模版设置
		PROVIDER_INFO("53","providerInfo"),
		
		// 联系人信息模版设置
		CANTACT_INFO("54","cantactInfo"),
		// 员工信息模板设置
		EMPLOYEE_INFO("55","employeeInfo"),
		// 仓库信息
		STORE_INFO("56","storeInfo");
		// 账户信息
		// ACCOUNT_INFO("57","accountInfo");
		// 模版id
		private String tempId;
		
		// 配置文件前缀
		private String prifix;
		
		CodeBuilder(String tempId, String prifix) {
			this.tempId = tempId;
			this.prifix = prifix;
			
		}
		
		/**
		 * 
		 * @return 模版id
		 */
		public String getTempId() {
			return tempId;
		}
		
		/**
		 * 
		 * @return 配置文件前缀
		 */
		public String getPrifix() {
			return this.prifix;
		}
	}

