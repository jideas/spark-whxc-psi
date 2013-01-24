package com.spark.oms.publish.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯ�û�(�б�)����
 */
public class UserItemKey extends LimitKey{

	public UserItemKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	
	/**
	 * �����ֶ�
	 */
	public static enum SortField {
		
		userCode("userCode"),
		userName("userName"),
		department("department"),
		duty("duty"),
		salesRank("salesRank"),
		mobilePhone("mobilePhone"),
		email("email"),
		entryDate("entryDate");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
	
	//��ѯ����
	public enum UserItemKeyType {
		
		QueryAllUsers("01", "ȫ���û�"), 
		QueryAllFunctionDepartmentUsers("02", "����ְ�ܲ����û�"),
		QueryAllFunctionDepartmentUsersByDepartment("03","ָ�������µ�����ְ�ܲ����û�"),
		QueryAllSalesTeamUsers("04","���������Ŷ��û�"),
		QueryAllSalesTeamUsersByDepartment("05","ָ�������µ����������Ŷ��û�"),
		QueryUserByUserCode("06","ָ����ŵ��û�"),
		QueryUserByUserGUID("07","ָ��GUID���û�");
		
		private String code;
		private String name;
		private UserItemKeyType(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
		public static UserItemKeyType getUserItemKeyType(String code) {
			for(UserItemKeyType type:UserItemKeyType.values()){
				if(type.getCode().equals(code)){
					return type;
				}
			}			
			return null;
		}
	}
	
	private String userType;
	private String userstatus;
	private String department;
	private String username;
	private String usercode;
	private GUID recid;
	private UserItemKeyType type;
	/**
	 * ָ���������ֶ�
	 */
	private SortField sortField;
	
	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
	
	public UserItemKeyType getType() {
		return type;
	}
	public void setType(UserItemKeyType type) {
		this.type = type;
	}
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserStatus() {
		return userstatus;
	}
	public void setUserStatus(String userstatus) {
		this.userstatus = userstatus;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
}