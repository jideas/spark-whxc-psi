package com.spark.psi.base.internal.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.key.organization.GetAncestorsDepartmentListKey;
import com.spark.psi.base.key.organization.GetChildrenDepartmentListKey;
import com.spark.psi.base.key.organization.GetDescendantDepartmentListKey;
import com.spark.psi.publish.Auth;
/**
 * 组织结构资源实现类
 * 
 
 *
 */
@StructClass
public class DepartmentImpl implements Department {
	
	protected GUID id;
	protected String code;
	protected String name;
	//是否有效
	protected boolean valid;
	//拥有的职能列表	
	private List<Auth> auths = new ArrayList<Auth>();
	
	//类型  公司root or 部门
	private char dtype;
	//父id
	private GUID parentId;
	
	private GUID tenantId;
	//员工列表
	private List<GUID> employees = new ArrayList<GUID>();
	
	private String path;
	
	private int sortIndex;
	
	
	public DepartmentImpl(){
//		this.setEntity(this);
	}
	
//	/**
//	 * 存放直属下级列表
//	 */
//	protected List<DepartmentImpl> childrens = new ArrayList<DepartmentImpl>();
//	/**
//	 * 存放所有子孙节点
//	 */
//	protected List<DepartmentImpl> descendants = new ArrayList<DepartmentImpl>();
//	/**
//	 * 父节点
//	 */
//	protected DepartmentImpl parent;
//	/**
//	 * 祖先节点
//	 */
//	protected List<DepartmentImpl> ancestors = new ArrayList<DepartmentImpl>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获得子节点列表
	 * @return
	 */
	public Department[] getChildren(final Context context,Auth... auths){
	   List<Department> depts = context.getList(Department.class,new GetChildrenDepartmentListKey(id,auths));
	   return depts.toArray(new Department[depts.size()]);
	}
//	
//	public List<DepartmentImpl> getChildList(Auth... auths){
//		List<DepartmentImpl> temp = new ArrayList<DepartmentImpl>();
//		for(Node<DepartmentImpl> node : children){
//			DepartmentImpl dept = node.getEntity();
//			if(dept.isAuth(auths) && dept.isValid()) temp.add(dept);
//       }
//		return temp;
//	}
	
	/**
	 * 获得子孙节点列表
	 * @return
	 */
	public Department[] getDescendants(final Context context, Auth... auths){
		List<Department> depts =
		        context.getList(Department.class,
		                new GetDescendantDepartmentListKey(id, auths));
		return depts.toArray(new Department[depts.size()]);
	}

	//	
//	public List<DepartmentImpl> getDescendantList(Auth...auths){
//		List<DepartmentImpl> temp = new ArrayList<DepartmentImpl>();
//		for(Node<DepartmentImpl> node : descendants){
//			DepartmentImpl dept = node.getEntity();
//	        if(dept.isAuth(auths)&&dept.isValid())temp.add(dept);
//        }
//		return temp;
//	}
//	
	/**
	 * 获得祖先节点列表
	 * @return
	 */
	public Department[] getAncestors(final Context context){
		List<Department> depts =
	        context.getList(Department.class,
	                new GetAncestorsDepartmentListKey(id));
	return depts.toArray(new Department[depts.size()]);
	}
	
	/**
	 * 获得父节点
	 * @return
	 */
	public Department getParent(final Context context){
		return context.find(Department.class,getParent());
	}	
	
	/**
	 * 判断当前部门是否拥有指定职能
	 * 传入职能数组，其中一个满足则返回true
	 * @param targetAuths 目标职能数组  
	 * @return
	 */
	public boolean hasAuth(Auth... targetAuths){
		if(targetAuths.length==0)return true;
		for (Auth auth : targetAuths) {
			if(auths.contains(auth)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 增加职能
	 * @param targetAuths
	 */
	public void addAuth(Auth... targetAuths){
		for (Auth auth : targetAuths) {
			auths.add(auth);
		}
	}
	
	/**
	 * 移除职能
	 * @param targetAuths
	 */
	public void removeAuth(Auth... targetAuths){
		for (Auth auth : targetAuths) {
			auths.remove(auth);
		}
	}
	
	/**
	 * 移除所有职能
	 * @param targetAuths
	 */
	public void removeAllAuth(){
		auths.clear();
	}
	
	public boolean isRoot(){
		return dtype == '0';
	}

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public String getName(){
    	return name;
    }

	public void setName(String name){
    	this.name = name;
    }

	public boolean isValid(){
    	return valid;
    }

	public void setValid(boolean vaild){
    	this.valid = vaild;
    }

	public char getDtype(){
    	return dtype;
    }

	public void setDtype(char dtype){
    	this.dtype = dtype;
    }

	public GUID getParentId(){
    	return parentId;
    }

	public void setParentId(GUID parentId){
    	this.parentId = parentId;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	public Auth[] getAuths(){
    	return auths.toArray(new Auth[0]);
    }

	public void setAuths(List<Auth> auths){
    	this.auths = auths;
    }

	public List<GUID> getEmployees(){
    	return employees;
    }

	public void addEmployees(GUID employee){
    	this.employees.add(employee);
    }		

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSortIndex(){
    	return sortIndex;
    }

	public void setSortIndex(int sortIndex){
    	this.sortIndex = sortIndex;
    }

	public GUID getParent(){
	    return parentId;
    }
	
	public void setParent(GUID parentId){
		this.parentId = parentId;
	}
	
	
}
