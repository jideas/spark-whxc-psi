package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʊ����
 */
public class InvalidateInvoiceTask extends SimpleTask {

	/**
	 * ��ƱID
	 */
	private GUID id;

	/**
	 * ����ԭ��
	 */
	private String reason;

	/**
	 * ���캯��
	 * 
	 * @param id
	 *            ��ƱID
	 */
	public InvalidateInvoiceTask(GUID id, String reason) {
		this.id = id;
		this.reason = reason;
	}

	/**
	 * ��ȡ��ƱID
	 * 
	 * @return
	 */
	public GUID getId() {
		return this.id;
	}

	/**
	 * ����ԭ��
	 * 
	 * @return
	 */
	public String getReason() {
		return this.reason;
	}
}
