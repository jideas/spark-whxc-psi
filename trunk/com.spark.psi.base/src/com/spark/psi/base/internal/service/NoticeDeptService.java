package com.spark.psi.base.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.internal.entity.NoticeDept;
import com.spark.psi.base.internal.service.orm.Orm_NoticeDept;
import com.spark.psi.publish.base.notice.key.FindDeptGuidListByNoticeKey;
import com.spark.psi.publish.base.notice.task.DeleteNoticeDeptByNoticeTask;
import com.spark.psi.publish.base.notice.task.SaveNoticeDeptTask;

/**
 * <p>���沿�Ź�ϵ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class NoticeDeptService extends Service{
	
	final Orm_NoticeDept ormNoticeDept;

	/** 
     *���췽��
     */
    protected NoticeDeptService(Orm_NoticeDept ormNoticeDept){
	    super("com.spark.psi.base.internal.service.NoticeDeptService");
	    this.ormNoticeDept = ormNoticeDept;
    }
    
    /**
     * �������沿�Ź�ϵ
     */
    @Publish
    protected class addNoticeDept extends SimpleTaskMethodHandler<SaveNoticeDeptTask>{

		@Override
        protected void handle(Context context, SaveNoticeDeptTask task) throws Throwable{
			//��ѯ����
			Department dept = context.find(Department.class, task.getDeptGUID());
			ORMAccessor<NoticeDept> ormAccessor = context.newORMAccessor(ormNoticeDept);
	        try{
	        	if(null != dept){
	        		NoticeDept noticeDept = new NoticeDept();
	        		//GUID
	        		noticeDept.setRECID(context.newRECID());
	        		//�⻧GUID
	        		noticeDept.setTenantsGuid(task.getTenantsGUID());
	        		//����ID
	        		noticeDept.setNoticeGuid(task.getNoticeGUID());
	        		//����GUID
	        		noticeDept.setDeptGuid(dept.getId());
	        		//��������
	        		noticeDept.setDeptName(dept.getName());
	        		//�������沿�Ź�ϵ
	        		ormAccessor.insert(noticeDept);
	        	}
	        }finally{
	        	ormAccessor.unuse();
	        }
        }
    	
    }
    
    /**
     * ���ݹ���ɾ�������빫���ϵ
     */
    @Publish
    protected class deleteNoticeDeptByNotice extends SimpleTaskMethodHandler<DeleteNoticeDeptByNoticeTask>{

    	/**
    	 * ���SQL
    	 */
    	public String getSql(){
    		StringBuffer buffer = new StringBuffer();
    		buffer.append("define delete del_noticeDept(@noticeGuid guid not null) ");
    		buffer.append(" begin ");
    		buffer.append(" delete from SA_NOTICE_DEPT as t where t.noticeGuid=@noticeGuid ");
    		buffer.append(" end ");
    		return buffer.toString();
    	}
    	
		@Override
        protected void handle(Context context, DeleteNoticeDeptByNoticeTask task) throws Throwable{
			//���SQL
			String sql = getSql();
			DBCommand dbCommand = context.prepareStatement(sql);
			dbCommand.setArgumentValue(0, task.getNoticeGUID());
	        try{
	        	dbCommand.executeUpdate();
	        }finally{
	        	dbCommand.unuse();
	        }
        }
    }
    
    /**
	 *  ���ݹ���ID��ѯ����ID�б�
	 */
	@Publish
	protected class findDeptGuidList extends OneKeyResultListProvider<GUID, FindDeptGuidListByNoticeKey>{

		/**
    	 * ���SQL
    	 */
    	public String getSql(){
    		
    		StringBuffer buffer = new StringBuffer();
    		buffer.append("define query Qu_DeptGuidList(@noticeGuid guid) ");
    		buffer.append(" begin ");
    		buffer.append(" select t.deptGuid as deptGuid from SA_NOTICE_DEPT as t ");
    		buffer.append(" where t.noticeGuid=@noticeGuid ");
    		buffer.append(" end ");
    		return buffer.toString();
    	}
		
		@Override
        protected void provide(Context context, FindDeptGuidListByNoticeKey key, List<GUID> resultList) throws Throwable{
			//��ȡ��¼Ա��
			DBCommand dbCommand = context.prepareStatement(getSql());
			dbCommand.setArgumentValue(0, key.getNoticeGuid());
			try{
				RecordSet recordSet = dbCommand.executeQuery();
				while(recordSet.next()){
					resultList.add(recordSet.getFields().get(0).getGUID());
				}
			}
			finally{
				dbCommand.unuse();
			}
        }
	}

}
