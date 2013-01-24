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
 * <p>公告部门关系服务</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class NoticeDeptService extends Service{
	
	final Orm_NoticeDept ormNoticeDept;

	/** 
     *构造方法
     */
    protected NoticeDeptService(Orm_NoticeDept ormNoticeDept){
	    super("com.spark.psi.base.internal.service.NoticeDeptService");
	    this.ormNoticeDept = ormNoticeDept;
    }
    
    /**
     * 新增公告部门关系
     */
    @Publish
    protected class addNoticeDept extends SimpleTaskMethodHandler<SaveNoticeDeptTask>{

		@Override
        protected void handle(Context context, SaveNoticeDeptTask task) throws Throwable{
			//查询部门
			Department dept = context.find(Department.class, task.getDeptGUID());
			ORMAccessor<NoticeDept> ormAccessor = context.newORMAccessor(ormNoticeDept);
	        try{
	        	if(null != dept){
	        		NoticeDept noticeDept = new NoticeDept();
	        		//GUID
	        		noticeDept.setRECID(context.newRECID());
	        		//租户GUID
	        		noticeDept.setTenantsGuid(task.getTenantsGUID());
	        		//公告ID
	        		noticeDept.setNoticeGuid(task.getNoticeGUID());
	        		//部门GUID
	        		noticeDept.setDeptGuid(dept.getId());
	        		//部门名称
	        		noticeDept.setDeptName(dept.getName());
	        		//新增公告部门关系
	        		ormAccessor.insert(noticeDept);
	        	}
	        }finally{
	        	ormAccessor.unuse();
	        }
        }
    	
    }
    
    /**
     * 根据公告删除部门与公告关系
     */
    @Publish
    protected class deleteNoticeDeptByNotice extends SimpleTaskMethodHandler<DeleteNoticeDeptByNoticeTask>{

    	/**
    	 * 获得SQL
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
			//获得SQL
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
	 *  根据公告ID查询部门ID列表
	 */
	@Publish
	protected class findDeptGuidList extends OneKeyResultListProvider<GUID, FindDeptGuidListByNoticeKey>{

		/**
    	 * 获得SQL
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
			//获取登录员工
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
