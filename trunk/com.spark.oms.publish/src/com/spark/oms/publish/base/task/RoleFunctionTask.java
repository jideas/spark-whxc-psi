package com.spark.oms.publish.base.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.FunctionType;

/**
 * 设置角色功能关联
 */
public class RoleFunctionTask extends Task<RoleFunctionTask.METHOD>{
	
	public enum METHOD {
		ADD,DELETE
	}

	/**
	 * 角色GUID
	 * @return
	 */
	private GUID RECID;
	/**
	 * 角色ID
	 * @return
	 */
	private String roleId;
	/**
	 * 角色Code
	 * @return
	 */
	private String roleCode;
	/**
	 * 角色名称
	 * @return
	 */
	private String roleName;
	/**
	 * 创建人GUID
	 * @return
	 */
	private GUID createPerson;
	/**
	 * 创建人
	 * @return
	 */
	private String createPersonName;
	/**
	 * 创建时间
	 * @return
	 */
	private long createDate;

	/**
	 * 更新人GUID
	 * @return
	 */
	private GUID updatePerson;
	/**
	 * 更新人
	 * @return
	 */
	private String updatePersonName;
	/**
	 * 更新时间
	 * @return
	 */
	private long updateDate;
	
	/**
	 * 获取角色对应的功能模块
	 * @return
	 */
	private Function[] functions;
	
	public GUID getRECID() {
		return RECID;
	}


	public void setRECID(GUID rECID) {
		RECID = rECID;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getRoleCode() {
		return roleCode;
	}


	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public GUID getCreatePerson() {
		return createPerson;
	}


	public void setCreatePerson(GUID createPerson) {
		this.createPerson = createPerson;
	}


	public String getCreatePersonName() {
		return createPersonName;
	}


	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}


	public long getCreateDate() {
		return createDate;
	}


	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}


	public GUID getUpdatePerson() {
		return updatePerson;
	}


	public void setUpdatePerson(GUID updatePerson) {
		this.updatePerson = updatePerson;
	}


	public String getUpdatePersonName() {
		return updatePersonName;
	}


	public void setUpdatePersonName(String updatePersonName) {
		this.updatePersonName = updatePersonName;
	}


	public long getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}


	public Function[] getFunctions() {
		return functions;
	}


	public void setFunctions(Function[] functions) {
		this.functions = functions;
	}


	public static final class Function{
		
		private GUID RECID;
		private String mfcode;
		private String mfname;
		private String mfparent;
		private FunctionType mftype;
		public GUID getRECID() {
			return RECID;
		}
		public void setRECID(GUID rECID) {
			RECID = rECID;
		}
		public String getMfcode() {
			return mfcode;
		}
		public void setMfcode(String mfcode) {
			this.mfcode = mfcode;
		}
		public String getMfname() {
			return mfname;
		}
		public void setMfname(String mfname) {
			this.mfname = mfname;
		}
		public String getMfparent() {
			return mfparent;
		}
		public void setMfparent(String mfparent) {
			this.mfparent = mfparent;
		}
		public FunctionType getMftype() {
			return mftype;
		}
		public void setMftype(FunctionType mftype) {
			this.mftype = mftype;
		}
	}
}