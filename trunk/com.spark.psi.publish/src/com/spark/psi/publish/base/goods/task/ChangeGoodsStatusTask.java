package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �ı���Ʒ��Ϣ��״̬��һ�����߶�����
 * 
 */
public class ChangeGoodsStatusTask extends SimpleTask {

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
	public ChangeGoodsStatusTask(boolean turnOnOrOff, GUID... ids) {
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
