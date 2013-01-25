package com.spark.psi.publish.base.contact.key;

import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.LimitKey;

/**
 * 查询通讯录条目
 * 
 */
public class GetContactListKey extends LimitKey {

	/**
	 * 要查询的类型列表
	 */
	private ContactType[] types;

	/**
	 * 查询的首字母范围
	 */
	private char[] scope;

	/**
	 * 构造函数
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetContactListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * 
	 * @param types
	 */
	public void setTypes(ContactType... types) {
		this.types = types;
	}

	/**
	 * 
	 * @return
	 */
	public ContactType[] getTypes() {
		return this.types;
	}

	/**
	 * @return the scope
	 */
	public char[] getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(char[] scope) {
		this.scope = scope;
	}

}
