package com.spark.psi.publish.base.contact.key;

import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.LimitKey;

/**
 * ��ѯͨѶ¼��Ŀ
 * 
 */
public class GetContactListKey extends LimitKey {

	/**
	 * Ҫ��ѯ�������б�
	 */
	private ContactType[] types;

	/**
	 * ��ѯ������ĸ��Χ
	 */
	private char[] scope;

	/**
	 * ���캯��
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
