package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.key.SelectMainKey;

/**
 * <p>
 * ҳǩ���޿���ʾ����У��
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-11-21
 */
public class MainTabDataValiTask extends SimpleTask implements ITaskResult {
	private int lenght;
	private boolean haveData = false;
	private SelectMainKey key;

	public MainTabDataValiTask(SelectMainKey key) {
		this.key = key;
	}
	/**
	 * @return the haveData
	 */
	public boolean isHaveData() {
		return haveData;
	}
	/**
	 * @param haveData the haveData to set
	 */
	public void setHaveData(boolean haveData) {
		this.haveData = haveData;
	}


	/**
	 * @return the key
	 */
	public SelectMainKey getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(SelectMainKey key) {
		this.key = key;
	}

	public boolean isSucceed() {
		return lenght>0;
	}

	public int lenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
}
