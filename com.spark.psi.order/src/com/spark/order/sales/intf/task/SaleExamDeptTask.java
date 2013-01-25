package com.spark.order.sales.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>���۶������Ĳ���Sql</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public class SaleExamDeptTask extends SimpleTask implements ITaskResult{
	/**
	 * ���۶���GUID
	 */
	public GUID recid;
	/**
	 * �²���GUID
	 */
	public GUID examDeptGuid;
	/**
	 * �ϲ���GUID
	 */
	public GUID oldExamDetp;
	/**
	 * �����Ϣ
	 */
	public String examinStr;
	private int lenght;
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public boolean isSucceed() {
		return 1 == lenght;
	}
	public int lenght() {
		return lenght;
	}
}
