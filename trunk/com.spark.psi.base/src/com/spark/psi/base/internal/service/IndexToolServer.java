package com.spark.psi.base.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.service.orm.Orm_IndexTool;
import com.spark.psi.base.publicimpl.IndexToolImpl;
import com.spark.psi.publish.base.index.entity.IndexTool;
import com.spark.psi.publish.base.index.task.IndexToolTask;
import com.spark.psi.publish.base.index.task.IndexToolTask.Method;

public class IndexToolServer extends Service {

	private Orm_IndexTool indexToolorm;
	
	protected IndexToolServer(Orm_IndexTool indexTool) {
		super("com.spark.psi.base.internal.service.IndexToolServer");
		this.indexToolorm = indexTool;
	}
	
	/**
	 * 根据用户GUID查询
	 * @author durendong
	 *
	 */
	@Publish
	protected class IndexToolProvider extends OneKeyResultListProvider<IndexTool, GUID> {

		@Override
		protected void provide(Context context, GUID userid,
				List<IndexTool> resultList) throws Throwable {
			ORMAccessor<IndexToolImpl> accessor = context.newORMAccessor(indexToolorm);
			List<IndexToolImpl> impls = accessor.fetch(userid,"");
			resultList.addAll(impls);
		}
		
	}
	
	/**
	 * 新增
	 * @author durendong
	 *
	 */
	@Publish
	protected class IndexToolAddHandler extends TaskMethodHandler<IndexToolTask, IndexToolTask.Method> {

		protected IndexToolAddHandler() {
			super(IndexToolTask.Method.ADD);
			
		}

		@Override
		protected void handle(Context context, IndexToolTask task)
				throws Throwable {
			ORMAccessor<IndexToolImpl> accessor = context.newORMAccessor(indexToolorm);
			//先删除
			List<IndexToolImpl> impls = accessor.fetch(task.getUserid(),task.getName());
			if(impls != null && impls.size() > 0) {
				accessor.delete(impls.get(0));
			}
			//再新增
			IndexToolImpl indexToolImpl = new IndexToolImpl();
			indexToolImpl.setRecid(task.getRecid());
			indexToolImpl.setName(task.getName());
			indexToolImpl.setUserid(task.getUserid());
			indexToolImpl.setX(task.getX());
			indexToolImpl.setY(task.getY());
			
			accessor.insert(indexToolImpl);
		}
	}
	
	/**
	 * 删除
	 * @author durendong
	 *
	 */
	@Publish
	protected class IndexToolDelHandler extends TaskMethodHandler<IndexToolTask, IndexToolTask.Method> {

		protected IndexToolDelHandler() {
			super(IndexToolTask.Method.DEL);
		}

		@Override
		protected void handle(Context context, IndexToolTask task)
				throws Throwable {
			ORMAccessor<IndexToolImpl> accessor = context.newORMAccessor(indexToolorm);
			List<IndexToolImpl> impls = accessor.fetch(task.getUserid(),task.getName());
			if(impls != null && impls.size() > 0) {
				accessor.delete(impls.get(0));
			}
		}
		
	}
}
