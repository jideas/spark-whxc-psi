package com.spark.order.util.purchase;

import java.util.List;

import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;

/**
 * <p>���ɲɹ������ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-23
 */
public interface ICreatePurchase {
	/**
	 * ���ɲɹ��������ɹ�����true��
	 * @param info
	 * @param dets
	 * @return boolean
	 */
	boolean create(PurchaseOrderInfo info, List<PurchaseOrderItem> dets) throws Throwable;
	/**
	 * ���ֱ��ʱ�׳��ı���δ���ɵ�ֱ����Ʒ(��ֱ��������ʱ����null)
	 * @return List<PurchaseOrderItem>
	 */
	List<PurchaseOrderItem> getDirected();
	/**
	 * ����Ѿ�ֱ����Ʒ����
	 * @return List<Integer>
	 */
	List<Integer> getDirectedIndexs();
}
