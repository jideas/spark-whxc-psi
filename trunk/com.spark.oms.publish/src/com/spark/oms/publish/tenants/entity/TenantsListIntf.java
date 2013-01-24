package com.spark.oms.publish.tenants.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 客户列表展现
 */
public interface TenantsListIntf {
	//标识
	public GUID getId() ;
	
	//简称
	public String getShortName();
	
	//名称
	public String getName();
	
	//总经理姓名
	public String getBossName() ;
	
	//总经理性别
	public String getBossSex() ;
	
	//总经理邮箱
	public String getBossEmail() ;
	
	//移动
	public String getBossMobile() ;
	
	//固话
	public String getBossTel();
	
	//销售经理
	public String getSaleManager() ;
	
	//租赁账户余额
	public double getLeaseAccountBalance();
	
	//计量账户余额
	public double getPieceAccountBalance() ;
	
	//收款金额
	public double getReceiptAmount();
	
	//开票金额
	public double getInvoiceAmount() ;
	
	//服务时常
	public int getServeMonth() ;
	
	//首次订单时间
	public long getFirstSignOrderDate();
	
	//获取租户类别：正式租户，流失、退款
	public String getStatus();
	//获取退款状态
	public String getRefundstatus();
}
