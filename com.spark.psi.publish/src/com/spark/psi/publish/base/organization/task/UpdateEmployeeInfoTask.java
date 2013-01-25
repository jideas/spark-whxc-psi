package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����Ա������<br>
 * �������Ա����Ϣ�����ڵĸ��£������ڵ�����
 */
public class UpdateEmployeeInfoTask extends SimpleTask {

	/**
	 * Ա���б�
	 */
	private Item[] employeeItems;

	/**
	 * ���캯��
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
	 * �����û���Ϣ
	 */
	public static class Item {
		
		/**
		 * �Ƿ�������
		 */
		private boolean create;
		/**
		 * �Ƿ���Ч
		 */
		private boolean valid = true;
		
		/**
		 * �û�ID
		 */
		private GUID id;

		/**
		 * �û�����
		 */
		private String name;

		/**
		 * �ֻ�����
		 */
		private String mobileNumber;

		/**
		 * ���֤����
		 */
		private String idNumber;

		/**
		 * email��ַ
		 */
		private String email;

		/**
		 * ְλ
		 */
		private String position;

		/**
		 * ��ɫ�б�
		 */
		private int roles;

		/**
		 * ��������ID
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
