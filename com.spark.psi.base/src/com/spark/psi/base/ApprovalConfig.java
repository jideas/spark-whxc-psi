package com.spark.psi.base;

import com.spark.psi.base.internal.entity.CfgExam;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;

/**
 * ��������<br>
 * ��ѯ������ֱ�Ӳ�ѯApprovalConfig����
 */
public interface ApprovalConfig extends ApprovalConfigInfo {
	
	/**
	 * Ĭ����������
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
	 * <p>���ģ��ö��</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-20
	 */
	public enum Mode{
		/** ���� */
		SALES_BILLS(CodeBuilder.SALES_BILLS.getTempId(), "���۶���",
		        "�����û��ύ�����۶��������Ƿ���Ҫ�������۾���Ȩ�޵��û����ܾ������������"),
		/** �����˻�*/
		SALES_RETURN(CodeBuilder.SALES_RETURN.getTempId(), "�����˻�",
		        "�����û��ύ�������˻������Ƿ���Ҫ�������۾���Ȩ�޵��û����ܾ������������"),
		/** �ɹ�����*/
		BUY_ORDER(CodeBuilder.BUY_ORDER.getTempId(), "�ɹ�����",
		        "�����û��ύ�Ĳɹ����������Ƿ���Ҫ���вɹ�����Ȩ�޵��û����ܾ������������"),
		/** �ɹ��˻�*/
		BUY_RETURN(CodeBuilder.BUY_RETURN.getTempId(), "�ɹ��˻�",
		        "�����û��ύ�Ĳɹ��˻������Ƿ���Ҫ���вɹ�����Ȩ�޵��û����ܾ������������"),
		/** ������*/
		BLENDING(CodeBuilder.BLENDING.getTempId(), "������",
		        "�����û��ύ�Ŀ����������Ƿ���Ҫ����ֿ�Ŀ�ܾ������ֿ�Ŀ�������ܾ������������", false),
		 /** ���� */
		PROMOTION("-1","����",false);
		
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
			throw new IllegalArgumentException("û��idΪ"+id+"��"+Mode.class.getName()+"ö��ֵ");
		}
		
	}

	/**
	 * ��ȡ���۶�����˿������
	 * 
	 * @return the salesApprovalLimit
	 */
	public double getSalesApprovalLimit();

	/**
	 * ��ȡ�ɹ�������˿������
	 * 
	 * @return the purchaseApprovalLimit
	 */
	public double getPurchaseApprovalLimit();

	/**
	 * ��ȡ�����˻�������˿������
	 * 
	 * @return the salesReturnApprovalLimit
	 */
	public double getSalesReturnApprovalLimit();
	

	/**
	 * ��ȡ�ɹ��˻�������˿������
	 * 
	 * @return the purchaseReturnApprovalLimit
	 */
	public double getPurchaseReturnApprovalLimit();

	/**
	 * ��ȡ�Ƿ�������������
	 * 
	 * @return the allocateNeedApproval
	 */
	public boolean isAllocateNeedApproval();

	/**
	 * ��ȡ�Ƿ�ʼ���۶�����������
	 * 
	 * @return boolean
	 */
	public boolean isSalesOrderNeedApproval();
	/**
	 * ��ȡ�Ƿ����ɹ�������������
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseOrderNeedApproval();

	/**
	 * ��ȡ�����˻��Ƿ�����������
	 * 
	 * @return boolean
	 */
	public boolean isSalesReturnNeedApproval();
	
	/**
	 * ��ȡ�ɹ��˻��Ƿ�����������
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseReturnNeedApproval();
	
	

}
