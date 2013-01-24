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
 * 审批管理 外部接口服务
 * 
 
 *
 */
public class ApprovalPublishService extends Service {

	protected ApprovalPublishService() {
		super("com.spark.psi.base.internal.service.ApprovalPublishService");
	}

	/**
	 * 获得当前租户的审批配置信息
	 
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
	 * 保存当前租户的审核配置信息
	 
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
			context.handle(cfgExamTask); //修改 采购订单审批配置
			
			cfgExamTask = new CfgExamTask(Mode.BUY_RETURN);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getPurchaseReturnApprovalLimit());
			cfgExamTask.setOpenExam(task.isPurchaseReturnNeedApproval());
			context.handle(cfgExamTask); //修改采购退货审批配置
			
			cfgExamTask = new CfgExamTask(Mode.SALES_BILLS);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getSalesApprovalLimit());
			cfgExamTask.setOpenExam(task.isSalesOrderNeedApproval());
			context.handle(cfgExamTask); //修改销售订单审批配置
			
			cfgExamTask = new CfgExamTask(Mode.SALES_RETURN);
			cfgExamTask.setTenantsGuid(tr.getId());
			cfgExamTask.setMaxAmount(task.getSalesReturnApprovalLimit());
			cfgExamTask.setOpenExam(task.isSalesReturnNeedApproval());
			context.handle(cfgExamTask); //修改销售退货审批配置
			
			cfgExamTask = new CfgExamTask(Mode.BLENDING);
			cfgExamTask.setTenantsGuid(tr.getId());
//			cfgExamTask.setMaxAmount(task.getPurchaseReturnApprovalLimit());
			cfgExamTask.setOpenExam(task.isAllocateNeedApproval());
			context.handle(cfgExamTask); //修改调拨审批配置
						
		}
		
	}
		
	/**
	 * 保存当前租户的销售人员的信用度配置信息
	 
	 *
	 */
	@Publish
	protected class SaveSalesManCreditHandler extends SimpleTaskMethodHandler<SaveSalesmanCreditTask>{

		@Override
		protected void handle(Context arg0, SaveSalesmanCreditTask arg1)
				throws Throwable {
			//此处TASK需要确认，Task中是否需要储存整个配置列表
			
		}
		
		
	}
	
}
