package com.spark.order.promotion.intf;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>������Ʒ�����������仯Task</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
public class PromotionSaledCountTask extends SimpleTask implements ITaskResult{
	private GUID id;
	private double changeCount;//�仯����
	/**
	 * @param id
	 * @param changeCount
	 */
	public PromotionSaledCountTask(GUID id, double changeCount) {
		super();
		this.id = id;
		this.changeCount = changeCount;
	}
	public GUID getId() {
		return id;
	}
	public double getChangeCount() {
		return changeCount;
	}
	public boolean isSucceed() {
		return 1 == lenght;
	}
	public int lenght() {
		return lenght;
	}
	public int lenght;
}
