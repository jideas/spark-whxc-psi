/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.leveltree.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-5-17       Zhoulj        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.leveltree.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-5-17       Zhoulj        
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.LevelTree;
import com.spark.psi.base.internal.service.orm.Orm_LevelTree;
import com.spark.psi.base.internal.service.query.QD_GetChildOf;
import com.spark.psi.base.task.pri.DeleteLevelTreeTask;
import com.spark.psi.base.task.pri.NewLevelTreeTask;
import com.spark.psi.base.task.pri.UpdateLevelTreePathTask;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-17
 */

public class LevelTreeService extends Service{

	protected final Orm_LevelTree q_Orm_LevelTree;

	private QD_GetChildOf qD_GetChildOf;

	protected LevelTreeService(final Orm_LevelTree qOrmLevelTree,
	        QD_GetChildOf qD_GetChildOf)
	{
		super("LevelTreeService");
		q_Orm_LevelTree = qOrmLevelTree;
		this.qD_GetChildOf = qD_GetChildOf;
	}

	@Publish
	protected class BaseLevelTreeProvider extends
	        OneKeyResultProvider<LevelTree, GUID>
	{
		@Override
		protected LevelTree provide(Context context, GUID id) throws Throwable{
			ORMAccessor<LevelTree> acc =
			        context.newORMAccessor(q_Orm_LevelTree);
			return acc.findByRECID(id);
		}
	}

	@Publish
	protected class AllLevelTreeProvider extends ResultListProvider<LevelTree>{
		@Override
		protected void provide(Context context, List<LevelTree> resultList)
		        throws Throwable
		{
			// TODO Add arguments for Orm_LevelTree if necessary.
			ORMAccessor<LevelTree> acc =
			        context.newORMAccessor(q_Orm_LevelTree);
			resultList.addAll(acc.fetch());
		}
	}

	/**
	 * 
	 * <p>新增级次树管理</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author Zhoulj
	 * @version 2011-5-17
	 */
	@Publish
	protected class AddLevelTreeHandler extends
	        SimpleTaskMethodHandler<NewLevelTreeTask>
	{

		@Override
		protected void handle(Context context, NewLevelTreeTask task)
		        throws Throwable
		{
			LevelTree levelTree = task.getLevelTree();
			LevelTree parentLevel = null;
			ORMAccessor<LevelTree> acc =
			        context.newORMAccessor(q_Orm_LevelTree);
			if(null != task.getParentRecid())
			    parentLevel =
			            (LevelTree)context.find(LevelTree.class, task
			                    .getParentRecid());
			if(parentLevel == null){
				levelTree.setPath(("00" + levelTree.getRECID()).getBytes());
			}
			else{
				levelTree
				        .setPath((new String(parentLevel.getPath()) + "00" + levelTree
				                .getRECID().toString()).getBytes());
				parentLevel.setStauts(LevelTree.STAUTS_PARENT);
				acc.update(parentLevel);
			}
			try{
//				System.out.println(new String(levelTree.getPath()));
				acc.insert(levelTree);
				task.setPath(new String(levelTree.getPath()));
			}
			catch(Exception e){
				System.out
				        .println(String.valueOf(levelTree.getPath()).length());
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * <p>修改父级节点</p>
	 * 更新所有子孙节点的级次关联
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author Zhoulj
	 * @version 2011-5-17
	 */
	@SuppressWarnings("unused")
	@Publish
	private class UpdateLevelTreePathHandler extends
	        SimpleTaskMethodHandler<UpdateLevelTreePathTask>
	{

		Context context;

		@Override
		protected void handle(final Context context,
		        final UpdateLevelTreePathTask task) throws Throwable
		{
			this.context = context;
			LevelTree tree =
			        (LevelTree)context.find(LevelTree.class, task.RECID);
			if(null == tree){
				throw new NullPointerException(
				        "更新级次表映射关系时发生错误：传入的RECID在级次树中未找到[" + task.RECID + "]");
			}
			//List<LevelTreeMapping> list = new ArrayList();
			LevelTreeMapping mapping = new LevelTreeMapping(tree.getRECID());
			//获得当前父节点的所有子孙节点的映射关系
			if(LevelTree.STAUTS_PARENT.equals(tree.getStauts()))
			    getLevelTreeMapping(mapping);

			//以下代码块更新映射关系
			LevelTree parent = context.find(LevelTree.class, task.parentRecid);
			if(parent == null){ //如果父节点为空，path就等于“00”加自己的RECID
				mapping.setPath(("00" + mapping.getRECID()).getBytes());
			}
			else{
				mapping.setPath((new String(parent.getPath()) + "00" + mapping
				        .getRECID().toString()).getBytes());
			}
			ORMAccessor<LevelTree> acc =
			        context.newORMAccessor(q_Orm_LevelTree);
			acc.update(mapping.getSuper());
			if(LevelTree.STAUTS_PARENT.equals(tree.getStauts()))
			    UpdateMapping(mapping, acc);
		}

		void UpdateMapping(final LevelTreeMapping mapping,
		        ORMAccessor<LevelTree> acc)
		{
			for(LevelTreeMapping child : mapping.children){
				child.setPath((new String(mapping.getPath()) + "00" + child
				        .getRECID().toString()).getBytes());
				acc.update(child.getSuper());
				if(LevelTree.STAUTS_PARENT.equals(child.getStauts()))
				    UpdateMapping(child, acc);
			}
		}

		void getLevelTreeMapping(final LevelTreeMapping parent){
			RecordSet rs = context.openQuery(qD_GetChildOf, parent.getRECID());
			LevelTreeMapping mapping;
			while(rs.next()){
				mapping = new LevelTreeMapping(rs.getFields().get(0).getGUID());
				mapping.setStauts(rs.getFields().get(1).getString());
				parent.addChildren(mapping);
				if(LevelTree.STAUTS_PARENT.equals(mapping.getStauts()))
				    getLevelTreeMapping(mapping);
			}
		}

		final class LevelTreeMapping extends LevelTree{
			List<LevelTreeMapping> children = new ArrayList<LevelTreeMapping>();

			public void addChildren(LevelTreeMapping mapping){
				this.children.add(mapping);
			}

			public LevelTreeMapping(GUID RECID){
				this.setRECID(RECID);
			}

			public LevelTree getSuper(){
				LevelTree lt = new LevelTree();
				lt.setRECID(this.getRECID());
				lt.setPath(getPath());
				lt.setStauts(getStauts());
				return lt;
			}
		}

	}

	/**
	 * 
	 * <p>清空级次表</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-5
	 */
	@Publish
	protected final class DeleteLevelTreeHandler extends
	        SimpleTaskMethodHandler<DeleteLevelTreeTask>
	{

		@Override
		protected void handle(Context context, DeleteLevelTreeTask task)
		        throws Throwable
		{
			StringBuffer sql = new StringBuffer("define delete deleteAll(@tenantId guid)");
			sql.append(" begin");
			sql.append(" delete from ");
			sql.append(" sa_core_tree ");
			sql.append(" as a ");
			if(task.getTenantId()!=null){
				sql.append(" where a.tenantId = @tenantId");
			}
			sql.append("  ");
			sql.append(" end");
			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValues(task.getTenantId());
			dbc.executeUpdate(); // 删除商品条目
		}

	}

}
