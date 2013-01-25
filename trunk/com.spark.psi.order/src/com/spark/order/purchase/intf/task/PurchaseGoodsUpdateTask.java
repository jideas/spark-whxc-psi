package com.spark.order.purchase.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>���²ɹ��嵥��Ʒ�۸�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-18
 */
public class PurchaseGoodsUpdateTask extends SimpleTask implements ITaskResult{
	private final GUID reicd;
	private final double price;
	private final String buyCause;
	private final boolean isDirect; 
	
	public PurchaseGoodsUpdateTask(GUID reicd, double price, String buyCause, boolean isDirect) {
		this.reicd = reicd;
		this.price = price;
		this.buyCause = buyCause;
		this.isDirect = isDirect;
	}
	
	public boolean isDirect() {
		return isDirect;
	}
	
	public String getBuyCause() {
		return buyCause;
	}
	
	/**
	 * @return the reicd
	 */
	public GUID getReicd() {
		return reicd;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	public int lenght;
	public boolean isSucceed() {
		return 1 == lenght;
	}

	public int lenght() {
		return lenght;
	}
}
