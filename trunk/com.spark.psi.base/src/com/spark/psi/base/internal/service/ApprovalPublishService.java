/**
 * 
 */
/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.ApprovalConfig.Mode;
import com.spark.psi.base.internal.entity.CfgExamTask;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;
import com.spark.psi.publish.base.config.entity.SalesmanCreditItem;
import com.spark.psi.publish.base.config.task.SaveApprovalConfigTask;
import com.spark.psi.publish.base.config.task.SaveSalesmanCreditTask;

/**
 * �������� �ⲿ�ӿڷ���
 * 
 
 *
 */
public class ApprovalPublishService extends Service {

	protected ApprovalPublishService() {
		super("com.spark.psi.base.internal.service.ApprovalPublishService");
	}

	/**
	 * ��õ�ǰ�⻧������������Ϣ
	 
	 *
	 */
	@Publish
	protected class GetApprovalConfigInfoProvider extends ResultProvider<ApprovalConfigInfo>{

		@Override
		protected ApprovalConfigInfo provide(Context context) throws Throwable {
			return context.find(Tenant.class).getApprovalConfig();
		}
		
	}
	
	/**
	 * ���浱ǰ�⻧�����������Ϣ
	 
	 *
	 */
	@Publish
	protected class SaveApprovalConfigHandler extends SimpleTaskMethodHandler<SaveApprovalConfigTask>{

		@Override
		protected void handle(Context context, SaveApprovalConfigTask task)
				throws Throwable {
			Tenant tr = TenantHelper.getTenant(context);
			CfgExamTask cfgExamTask = new CfgExamTask(Mode.BUY_ORDER);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getPurchaseApprovalLimit());
			cfgExamTask.setOpenExam(task.isPurchaseOrderNeedApproval());
			context.handle(cfgExamTask); //�޸� �ɹ�������������
			
			cfgExamTask = new CfgExamTask(Mode.BUY_RETURN);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getPurchaseReturnApprovalLimit());
			cfgExamTask.setOpenExam(task.isPurchaseReturnNeedApproval());
			context.handle(cfgExamTask); //�޸Ĳɹ��˻���������
			
			cfgExamTask = new CfgExamTask(Mode.SALES_BILLS);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getSalesApprovalLimit());
			cfgExamTask.setOpenExam(task.isSalesOrderNeedApproval());
			context.handle(cfgExamTask); //�޸����۶�����������
			
			cfgExamTask = new CfgExamTask(Mode.SALES_RETURN);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getSalesReturnApprovalLimit());
			cfgExamTask.setOpenExam(task.isSalesReturnNeedApproval());
			context.handle(cfgExamTask); //�޸������˻���������
			
			cfgExamTask = new CfgExamTask(Mode.BLENDING);
			cfgExamTask.setTenantsGuid(tr.getId());
//			cfgExamTask.setMaxAmount(task.getPurchaseReturnApprovalLimit());
			cfgExamTask.setOpenExam(task.isAllocateNeedApproval());
			context.handle(cfgExamTask); //�޸ĵ�����������
						
		}
		
	}
		
	/**
	 * ���浱ǰ�⻧��������Ա�����ö�������Ϣ
	 
	 *
	 */
	@Publish
	protected class SaveSalesManCreditHandler extends SimpleTaskMethodHandler<SaveSalesmanCreditTask>{

		@Override
		protected void handle(Context arg0, SaveSalesmanCreditTask arg1)
				throws Throwable {
			//�˴�TASK��Ҫȷ�ϣ�Task���Ƿ���Ҫ�������������б�
			
		}
		
		
	}
	
}
