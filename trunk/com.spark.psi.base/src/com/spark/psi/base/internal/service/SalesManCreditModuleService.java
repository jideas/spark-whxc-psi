/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-17    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.internal.entity.ormentity.SalesManCreditOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_SalesManCredit;
import com.spark.psi.base.task.AddSalesManCreditUsedTask;
import com.spark.psi.base.task.config.RemoveSalesManCreidtUesdTask;
import com.spark.psi.base.task.resource.UpdateSalesCreditResourceTask;
import com.spark.psi.publish.Auth;

/**
 * <p>���۾���ҵ����Ȩ����</p>
 *


 *
 
 * @version 2012-5-17
 */

public class SalesManCreditModuleService extends Service{
	
	final Orm_SalesManCredit orm_SalesManCredit;

	protected SalesManCreditModuleService(final Orm_SalesManCredit orm_SalesManCredit){
	    super("���۾���ҵ����Ȩ����");
	    this.orm_SalesManCredit = orm_SalesManCredit;	    
    }
	
	/**
	 * 
	 * <p>�������۾��������õ����ö�ȼ��ͻ�����</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-17
	 */
	@Publish
	protected final class AddSalesManCreidtUsedHandler extends SimpleTaskMethodHandler<AddSalesManCreditUsedTask>{

		@Override
        protected void handle(Context context, AddSalesManCreditUsedTask task)
                throws Throwable
        {
			Employee emp = context.find(Employee.class);
			if(!emp.hasAuth(Auth.SalesManager)||emp.hasAuth(Auth.Boss))return ;//���û�����۾���ְ�ܣ�������¼			
			ORMAccessor<SalesManCreditOrmEntity> acc = context.newORMAccessor(orm_SalesManCredit);
			SalesManCreditOrmEntity entity = acc.findByRECID(context.find(Login.class).getEmployeeId());
			if(entity==null){
				return ;
//				throw new IllegalArgumentException("��ǰ�û�û������ҵ����Ȩ");
//				entity = 
			}
			entity.setCustomerCountUsed(task.getCount()+entity.getCustomerCountUsed());
			entity.setCustomerCreditUsed(task.getAmount()+entity.getCustomerCreditUsed());
			acc.update(entity);
			context.handle(new UpdateSalesCreditResourceTask(entity.getId()),UpdateSalesCreditResourceTask.Method.Modify);
        }
		
	}

	/**
	 * 
	 * <p>�������۾��������õ����ö��</p>
	 *
	
	
	 *
	 
	 * @version 2012-5-17
	 */
	@Publish
	protected final class RemoveSalesManCreditUeseHandler extends SimpleTaskMethodHandler<RemoveSalesManCreidtUesdTask>{

		@Override
        protected void handle(Context context, RemoveSalesManCreidtUesdTask task)
                throws Throwable
        {
			if(task.getId()==null)return ;
			Employee emp = context.find(Employee.class,task.getId());
			if(emp==null)return ;
			if(!emp.hasAuth(Auth.SalesManager)||emp.hasAuth(Auth.Boss))return ;//���û�����۾���ְ�ܣ�������¼			
			ORMAccessor<SalesManCreditOrmEntity> acc = context.newORMAccessor(orm_SalesManCredit);
			SalesManCreditOrmEntity entity = acc.findByRECID(task.getId());
			if(entity==null){
				return ;
//				throw new IllegalArgumentException("ָ���û�û������ҵ����Ȩ");
			}
			entity.setCustomerCountUsed(entity.getCustomerCountUsed()-1);
			entity.setCustomerCreditUsed(entity.getCustomerCreditUsed()-task.getAmount());
			acc.update(entity);
			context.handle(new UpdateSalesCreditResourceTask(entity.getId()),UpdateSalesCreditResourceTask.Method.Modify);
        }
		
	}
	
}
