/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-27    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalLog;
import com.spark.psi.base.Login;
import com.spark.psi.base.ApprovalConfig.Mode;
import com.spark.psi.base.internal.entity.ormentity.ApprovalRecordOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_ApprovalRecord;
import com.spark.psi.base.task.CreateApprovalRecordTask;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.approval.ApprovalRecordItem;
import com.spark.psi.publish.base.approval.GetApprovalRecordKey;

/**
 * <p>审核记录服务</p>
 *


 *
 
 * @version 2012-4-27
 */

public class ApprovalLogPublishService extends Service{

	final Orm_ApprovalRecord orm_ApprovalRecord;
	
	protected ApprovalLogPublishService(final Orm_ApprovalRecord orm_ApprovalRecord){
	    super("审核记录服务");
	    this.orm_ApprovalRecord = orm_ApprovalRecord;
    }
	
	/**
	 * 
	 * <p>写入审核记录</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-27
	 */
	@Publish
	protected final class CreateApprovalRecordHandler extends SimpleTaskMethodHandler<CreateApprovalRecordTask>{

		@Override
        protected void handle(Context context, CreateApprovalRecordTask task)
                throws Throwable
        {
//			ORMAccessor<ApprovalRecordOrmEntity> acc = context.newORMAccessor(orm_ApprovalRecord);
//			ApprovalRecordOrmEntity entity = new ApprovalRecordOrmEntity();
//			entity.setId(context.newRECID());
//			entity.setAmount(task.getAmount());
//			entity.setBillsNumber(task.getBillNumber());
//			entity.setBusType(task.getBusMode().getId());
//			entity.setBusTypeName(task.getBusMode().getName());
//			entity.setExamDate(System.currentTimeMillis());
//			entity.setStatus(task.getBillstatus());
//			entity.setUserId(context.find(Login.class).getEmployeeId());
//			entity.setTenantId(context.find(Login.class).getTenantId());
//			entity.setCreateDate(task.getCreateDate());
//			entity.setCreatePerson(task.getCreatePerson());
//			acc.insert(entity);
//			ApprovalRecordItemImpl impl = new ApprovalRecordItemImpl();
//			impl.setId(entity.getId());
//			impl.setAmount(entity.getAmount());
//			impl.setApprovalDate(entity.getExamDate());
//			impl.setBillsNumber(entity.getBillsNumber());
//			impl.setBusTypeName(entity.getBusTypeName());
//			impl.setCreateDate(entity.getCreateDate());
//			impl.setCreatePerson(entity.getCreatePerson());
//			impl.setMode(task.getBusMode());
//			context.dispatch(new ApprovalLogEvent(impl));
        }		
	}
	
	@Publish
	protected final class GetApprovalRecordProvider extends OneKeyResultProvider<ListEntity<ApprovalRecordItem>, GetApprovalRecordKey>{

		@Override
        protected ListEntity<ApprovalRecordItem> provide(Context context,
                GetApprovalRecordKey key) throws Throwable
        {
			ORMAccessor<ApprovalRecordOrmEntity> acc = context.newORMAccessor(orm_ApprovalRecord);
			GUID tenantId = context.find(Login.class).getTenantId();
			List<ApprovalRecordOrmEntity> list = acc.fetchLimit(key.getOffset(), key.getCount(), tenantId);
			List<ApprovalRecordItem> resultList = new ArrayList<ApprovalRecordItem>();
			for(ApprovalRecordOrmEntity entity : list){
				ApprovalRecordItemImpl impl = new ApprovalRecordItemImpl();
				impl.setId(entity.getId());
				impl.setAmount(entity.getAmount());
				impl.setApprovalDate(entity.getExamDate());
				impl.setBillsNumber(entity.getBillsNumber());
				impl.setBusTypeName(entity.getBusTypeName());
				impl.setCreateDate(entity.getCreateDate());
				impl.setCreatePerson(entity.getCreatePerson());
				resultList.add(impl);
            }
	        return new ListEntity<ApprovalRecordItem>(resultList,acc.rowCountOf(tenantId));
        }		
	}
	
	public class ApprovalRecordItemImpl implements ApprovalRecordItem , ApprovalLog{
		
		
		private GUID id;
		
		private long approvalDate;
		private String billsNumber;
		private String busTypeName;
		private double amount;
		private String createPerson;
		private long createDate;
		
		private Mode mode;
		
		public GUID getId(){
        	return id;
        }
		public void setId(GUID id){
        	this.id = id;
        }
		public long getApprovalDate(){
        	return approvalDate;
        }
		public void setApprovalDate(long approvalDate){
        	this.approvalDate = approvalDate;
        }
		public String getBillsNumber(){
        	return billsNumber;
        }
		public void setBillsNumber(String billsNumber){
        	this.billsNumber = billsNumber;
        }
		public String getBusTypeName(){
        	return busTypeName;
        }
		public void setBusTypeName(String busTypeName){
        	this.busTypeName = busTypeName;
        }
		public double getAmount(){
        	return amount;
        }
		public void setAmount(double amount){
        	this.amount = amount;
        }
		public String getCreatePerson(){
        	return createPerson;
        }
		public void setCreatePerson(String createPerson){
        	this.createPerson = createPerson;
        }
		public long getCreateDate(){
        	return createDate;
        }
		public void setCreateDate(long createDate){
        	this.createDate = createDate;
        }
		public Mode getMode(){
        	return mode;
        }
		public void setMode(Mode mode){
        	this.mode = mode;
        }

		
		
		
	}


}
