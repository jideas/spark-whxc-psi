package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;

/**
 * <p>联系人列表</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-3
 */

public class ContactPersonItemImpl implements ContactPersonItem{

	/**GUID*/
	private GUID id;

	/**联系人姓名*/
	private String name;

	/**(单位)部门*/
	private String department;

	/**职位*/
	private String job;

	/**固话*/
	private String phone;

	/**手机*/
	private String mobile;

	/**邮箱*/
	private String email;

	/**类型*/
	private String type;

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

	public String getDepartment(){
		return department;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public String getJob(){
		return job;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

}
