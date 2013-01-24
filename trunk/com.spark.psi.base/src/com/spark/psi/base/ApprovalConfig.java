package com.spark.psi.base;

import com.spark.psi.base.internal.entity.CfgExam;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;

/**
 * 审批配置<br>
 * 查询方法：直接查询ApprovalConfig对象
 */
public interface ApprovalConfig extends ApprovalConfigInfo {
	
	/**
	 * 默认审批配置
	 */
	public static final ApprovalConfig  DefualtApprovalConfig = new ApprovalConfig(){
		
		CfgExam exam = new CfgExam();
		
		public boolean isSalesReturnNeedApproval(){
			return exam.isOpenExam();
		}
		
		public boolean isSalesOrderNeedApproval(){
			return exam.isOpenExam();
		}
		
		public boolean isPurchaseReturnNeedApproval(){
			return exam.isOpenExam();
		}
		
		public boolean isPurchaseOrderNeedApproval(){
			return exam.isOpenExam();
		}
		
		public boolean isAllocateNeedApproval(){
			return exam.isOpenExam();
		}
		
		public double getSalesReturnApprovalLimit(){
			return exam.getMaxAmount();
		}
		
		public double getSalesApprovalLimit(){
			return exam.getMaxAmount();
		}
		
		public double getPurchaseReturnApprovalLimit(){
			return exam.getMaxAmount();
		}
		
		public double getPurchaseApprovalLimit(){
			return exam.getMaxAmount();
		}
	};
	
	
	/**
	 * 
	 * <p>审核模块枚举</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-20
	 */
	public enum Mode{
		/** 销售 */
		SALES_BILLS(CodeBuilder.SALES_BILLS.getTempId(), "销售订货",
		        "设置用户提交的销售订货单，是否需要具有销售经理权限的用户或总经理进行审批。"),
		/** 销售退货*/
		SALES_RETURN(CodeBuilder.SALES_RETURN.getTempId(), "销售退货",
		        "设置用户提交的销售退货单，是否需要具有销售经理权限的用户或总经理进行审批。"),
		/** 采购订货*/
		BUY_ORDER(CodeBuilder.BUY_ORDER.getTempId(), "采购订货",
		        "设置用户提交的采购订货单，是否需要具有采购经理权限的用户或总经理进行审批。"),
		/** 采购退货*/
		BUY_RETURN(CodeBuilder.BUY_RETURN.getTempId(), "采购退货",
		        "设置用户提交的采购退货单，是否需要具有采购经理权限的用户或总经理进行审批。"),
		/** 库存调拨*/
		BLENDING(CodeBuilder.BLENDING.getTempId(), "库存调拨",
		        "设置用户提交的库存调拨单，是否需要出库仓库的库管经理、入库仓库的库管理经或总经理进行审批。", false),
		 /** 促销 */
		PROMOTION("-1","促销",false);
		
		String id,name,remark;
		
		boolean isOpenMaxAmount = true,isCanConfig = true;
		
		
		
		public boolean isCanConfig(){
        	return isCanConfig;
        }

		public void setCanConfig(boolean isCanConfig){
        	this.isCanConfig = isCanConfig;
        }

		public boolean isOpenMaxAmount(){
			return isOpenMaxAmount;
		}
		
		public String getId(){
			return id;
		}
		
		public String getName(){
			return name;
		}
		
		public String getRemark(){
			return remark;
		}
		
		Mode(String id,String name,String remark,boolean isOpenMaxAmount){
			this.id = id;
			this.name = name;
			this.remark = remark;
			this.isOpenMaxAmount = isOpenMaxAmount;
		}
		
		Mode(String id,String name,String remark){
			this.id = id;
			this.name = name;
			this.remark = remark;			
		}
		
		Mode(String id,String name,boolean isCanConfig){
			this.id = id;
			this.name = name;
			this.remark = "";
			this.isCanConfig = isCanConfig;
		}
		
		public static Mode getModeForId(String id){
			for(Mode mode : Mode.values()){
	            if(mode.getId().equals(id)){
	            	return mode;
	            }
            }
			throw new IllegalArgumentException("没有id为"+id+"的"+Mode.class.getName()+"枚举值");
		}
		
	}

	/**
	 * 获取销售订单审核开启金额
	 * 
	 * @return the salesApprovalLimit
	 */
	public double getSalesApprovalLimit();

	/**
	 * 获取采购订单审核开启金额
	 * 
	 * @return the purchaseApprovalLimit
	 */
	public double getPurchaseApprovalLimit();

	/**
	 * 获取销售退货订单审核开启金额
	 * 
	 * @return the salesReturnApprovalLimit
	 */
	public double getSalesReturnApprovalLimit();
	

	/**
	 * 获取采购退货订单审核开启金额
	 * 
	 * @return the purchaseReturnApprovalLimit
	 */
	public double getPurchaseReturnApprovalLimit();

	/**
	 * 获取是否开启库存调拨审批
	 * 
	 * @return the allocateNeedApproval
	 */
	public boolean isAllocateNeedApproval();

	/**
	 * 获取是否开始销售订单审批流程
	 * 
	 * @return boolean
	 */
	public boolean isSalesOrderNeedApproval();
	/**
	 * 获取是否开启采购订单审批流程
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseOrderNeedApproval();

	/**
	 * 获取销售退货是否开启审批流程
	 * 
	 * @return boolean
	 */
	public boolean isSalesReturnNeedApproval();
	
	/**
	 * 获取采购退货是否开启审批流程
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseReturnNeedApproval();
	
	

}
