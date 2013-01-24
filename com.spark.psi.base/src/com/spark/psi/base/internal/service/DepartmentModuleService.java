/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-8    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-8    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.Filter;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.helper.LevelTreeFilter;
import com.spark.psi.base.key.organization.GetAncestorsDepartmentListKey;
import com.spark.psi.base.key.organization.GetChildrenDepartmentListKey;
import com.spark.psi.base.key.organization.GetDepartmentFullPathNameKey;
import com.spark.psi.base.key.organization.GetDescendantDepartmentListKey;

/**
 * <p>
 * �ṩ�ڲ�ģ�鹫������
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-8
 */
public class DepartmentModuleService extends Service {

	protected DepartmentModuleService() {
		super(
				"com.spark.psi.base.internal.service.DepartmentModuleService");
	}

	/**
	 * 
	 * <p>
	 * ��ѯ��ǰ��¼Ա���Ĳ��Ŷ���
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
	 * Company: ����
	 * </p>
	 * 
	 
	 * @version 2012-3-15
	 */
	@Publish
	final class GetDepartmentByIdProvider1 extends ResultProvider<Department> {

		@Override
		protected Department provide(Context context) throws Throwable {
			Employee er = context.find(Employee.class);
			return context.find(Department.class, er.getDepartmentId());
		}

	}

	/**
	 * 
	 * <p>
	 * ���ָ������ӵ��ָ��ְ�ܵ����ﲿ���б�
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * Company: ����
	 * </p>
	 * 
	 * @author zhoulijun
	 * @version 2011-3-15
	 */
	@Publish
	protected class GetDescendantDepartmentListProvider
			extends
			OneKeyResultListProvider<Department, GetDescendantDepartmentListKey> {

		@Override
		protected void provide(Context context,
				final GetDescendantDepartmentListKey key,
				List<Department> resultList) throws Throwable {
			Department parent = context.find(Department.class, key.getDeptId());
			ResourceToken<Tenant> token = context.findResourceToken(
					Tenant.class, parent.getTenantId());
			List<Department> list = context.getResourceReferences(Department.class,
					token, new LevelTreeFilter<Department>(parent.getPath()) {
				@Override
				public boolean accept(Department item) {
//					if(true)return true;
					if (!super.accept(item)){
						return false;
					}
					if(key.getAuths().length==0)return true;
					if(item.hasAuth(key.getAuths()))return true;
					return false;
				}
			});
			Collections.sort(list,new Comparator<Department>(){

						public int compare(Department arg0, Department arg1){	                   
	                        return LevelTreeFilter.compare(arg1.getPath(),arg0.getPath()) ? 1 : -1;
                        }
				
			});
			resultList.addAll(list);
		}
	}
	
	/**
	 * 
	 * <p>��ѯָ�����ŵ�ֱ���¼�����</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-6
	 */
	@Publish
	protected final class GetChildrenDepartmentListProvider extends OneKeyResultListProvider<Department, GetChildrenDepartmentListKey>{

		@Override
        protected void provide(Context context,
                final GetChildrenDepartmentListKey key, List<Department> resultList)
                throws Throwable
        {
			ResourceToken<Tenant> token = context.findResourceToken(Tenant.class,context.find(Login.class).getTenantId());
			resultList.addAll(context.getResourceReferences(Department.class, token,new Filter<Department>(){

				public boolean accept(Department item){
					if(item.getParent() == null || !item.getParent().equals(key.getDeptId()))return false;
	                return item.hasAuth(key.getAuths())&&item.isValid();
                }
			}));
			Collections.sort(resultList, new Comparator<Department>(){

				public int compare(Department o1, Department o2){
	                return o1.getSortIndex() > o2.getSortIndex() ? 1 : -1;
                }
			});
        }		
	}
	
	/**
	 * 
	 * <p>���ָ�����ŵ��������Ȳ���</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-6
	 */
	@Publish
	protected final class GetAncestorsDepartmentListProvider extends OneKeyResultListProvider<Department, GetAncestorsDepartmentListKey>{

		@Override
        protected void provide(Context context,
                GetAncestorsDepartmentListKey key, List<Department> resultList)
                throws Throwable
        {
			Department dept = context.find(Department.class,key.getDeptId());			
			if(dept.getTenantId().equals(key.getDeptId()))return ;
			findParent(context, dept.getParent(), resultList);
        }
		
		private void findParent(Context context,GUID parentId,List<Department> list){
			Department parentDept = context.find(Department.class,parentId);
			list.add(parentDept);
			if(!parentDept.getTenantId().equals(parentDept.getId())){
				findParent(context, parentDept.getParent(), list);
			}
		}
		
	}
	
	/**
	 * 
	 * <p>��ô�·���Ĳ�������</p>
	 *  ������˾ > ��������  > ����һ��
	
	
	 *
	 
	 * @version 2012-5-11
	 */
	@Publish
	protected final class GetDepartmentFullPathNameProvider extends OneKeyResultProvider<String, GetDepartmentFullPathNameKey>{

		@Override
        protected String provide(Context context,
                GetDepartmentFullPathNameKey key) throws Throwable
        {
			Department dept = context.find(Department.class,key.getId());
			String result = dept.getName();
			if(dept.getId().equals(dept.getTenantId())){
				return "��˾ֱ��";
			}else{
				result = fillName(context,dept, result);
			}
			return result;
        }

		static final String space = " > ";
		
			
		private String fillName(Context context,Department dept,String name){
			if(!dept.getTenantId().equals(dept.getParent())){			
				Department parent = context.find(Department.class,dept.getParent());
				String result = parent.getName() + space + name;
				return fillName(context, parent, result);
			}else{
				return name;
			}
		}
	}

}
