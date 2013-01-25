package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �ı���Ʒ��Ϣ��״̬��һ�����߶�����
 * 
 */
public class ChangeMaterialsStatusTask extends SimpleTask {

	/**
	 * ��Ʒid����
	 */
	private GUID[] ids;  

	/**
	 * ͣ�ۻ��߿���
	 */
	private boolean turnOnOrOff;

	/**
	 * ���캯��
	 * 
	 * @param turnOnOrOff
	 *            ͣ�ۻ��߿���
	 * @param ids
	 *            ��Ʒid����
	 */
	public ChangeMaterialsStatusTask(boolean turnOnOrOff, GUID... ids) {
		this.turnOnOrOff = turnOnOrOff;
		this.ids = ids;
	}

	/**
	 * 
	 * @return
	 */
	public GUID[] getIds() {
		return ids;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTurnOnOrOff() {
		return this.turnOnOrOff;
	}

}
