package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;

/**
 * <p>��������SimpleTask</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public class FlowTask extends SimpleTask implements ITaskResult{
	/**
	 * ��������
	 */
	public final BillsEnum billsEnum;
	public FlowTask(BillsEnum b){
		this.billsEnum = b;
	}
	/**
	 * ����GUID
	 */
	public GUID billsRECID;
	/**
	 * ����ԭstatus
	 */
	public String oldstatus;
	/**
	 * ������status
	 */
	public String newstatus;
	
	private GUID deptGuid = null;//�޸Ĳ���GUID,�ǿ�ʱ�޸�
	private GUID examDept = null;//�޸������������ţ��ǿ���Ϊ���۶���ʱ�޸�
	public GUID getExamDept() {
		return examDept;
	}
	public void setExamDept(GUID examDept) {
		this.examDept = examDept;
	}
	public GUID getDeptGuid() {
		return deptGuid;
	}
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}
	
	private int lenght;
	
	/**
	 * ���÷�������
	 * @param lenght void
	 */
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	
	public boolean isSucceed() {
		if(1 == lenght){
			return true;
		}
		return false;
	}
	public int lenght() {
		return lenght;
	}
}
