package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 更新员工数据<br>
 * 包括多个员工信息，存在的更新，不存在的新增
 */
public class UpdateEmployeeInfoTask extends SimpleTask {

	/**
	 * 员工列表
	 */
	private Item[] employeeItems;

	/**
	 * 构造函数
	 * 
	 * @param employees
	 */
	public UpdateEmployeeInfoTask(Item[] employeeItems) {
		this.employeeItems = employeeItems;
	}

	/**
	 * 
	 * @return
	 */
	public Item[] getEmployeeItems() {
		return this.employeeItems;
	}

	public static Item getItemInstance() {
		return new Item();
	}

	/**
	 * 单个用户信息
	 */
	public static class Item {
		
		/**
		 * 是否是新增
		 */
		private boolean create;
		/**
		 * 是否有效
		 */
		private boolean valid = true;
		
		/**
		 * 用户ID
		 */
		private GUID id;

		/**
		 * 用户姓名
		 */
		private String name;

		/**
		 * 手机号码
		 */
		private String mobileNumber;

		/**
		 * 身份证号码
		 */
		private String idNumber;

		/**
		 * email地址
		 */
		private String email;

		/**
		 * 职位
		 */
		private String position;

		/**
		 * 角色列表
		 */
		private int roles;

		/**
		 * 所属部门ID
		 */
		private GUID departmentId;
		
		private GUID logo;
		
		private String landLineNumber;
		
		

		public GUID getLogo(){
        	return logo;
        }



		public void setLogo(GUID logo){
        	this.logo = logo;
        }



		public String getLandLineNumber(){
        	return landLineNumber;
        }



		public void setLandLineNumber(String landLineNumber){
        	this.landLineNumber = landLineNumber;
        }



		/**
		 * @return the id
		 */
		public GUID getId() {
			return id;
		}

		
		
		public boolean isValid() {
			return valid;
		}



		public void setValid(boolean valid) {
			this.valid = valid;
		}



		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(GUID id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the mobileNumber
		 */
		public String getMobileNo() {
			return mobileNumber;
		}

		/**
		 * @param mobileNumber
		 *            the mobileNumber to set
		 */
		public void setMobileNo(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		/**
		 * @return the idNumber
		 */
		public String getIdNumber() {
			return idNumber;
		}

		/**
		 * @param idNumber
		 *            the idNumber to set
		 */
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email
		 *            the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the position
		 */
		public String getPosition() {
			return position;
		}

		/**
		 * @param position
		 *            the position to set
		 */
		public void setPosition(String position) {
			this.position = position;
		}

		/**
		 * @return the roles
		 */
		public int getRoles() {
			return roles;
		}

		/**
		 * @param roles
		 *            the roles to set
		 */
		public void setRoles(int roles) {
			this.roles = roles;
		}

		/**
		 * @return the departmentId
		 */
		public GUID getDepartmentId() {
			return departmentId;
		}

		/**
		 * @param departmentId
		 *            the departmentId to set
		 */
		public void setDepartmentId(GUID departmentId) {
			this.departmentId = departmentId;
		}

		public boolean isCreate(){
        	return create;
        }

		public void setCreate(boolean create){
        	this.create = create;
        }

		
	}
}
