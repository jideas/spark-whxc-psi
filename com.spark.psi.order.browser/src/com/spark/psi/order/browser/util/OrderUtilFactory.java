package com.spark.psi.order.browser.util;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SEditTable;

/**
 * <p>�ɹ�����ʵ��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-10
 */
public final class OrderUtilFactory {
	/**
	 * �ɹ���Ʒ���飨���ɲɹ������ã�
	 * @return IPurchaseGoodsGroup
	 */
	public static IPurchaseGoodsGroup getPurchaseGoodsGroup(Context context, SEditTable table){
		return new PurchaseGoodsGroupImpl(context, table);
	}
	
	/**
	 * ����������
	 *  void
	 */
	public static void addAllColumnSort(STableColumn[] columns){
		for(int i = 0; i<columns.length && null != columns[i]; i++){
			columns[i].setSortable(true);
		}
	}
	
	/**
	 * �����Զ���ʽ��Booolean���飬��һ��ʶ�Զ�����ȣ��ڶ���ʾ����
	 * @param columns
	 * @param booleans void
	 */
	public static void addAllColumnParam(STableColumn[] columns, Boolean...booleans){
		if(null == booleans || 0 == booleans.length){
			return;
		}
		for(int i = 0; i<columns.length && null != columns[i]; i++){
			for(int j = 0; j<booleans.length; j++){
				switch (j) {
				case 0:
					columns[i].setGrab(booleans[j]);
					break;
				case 1:
					columns[i].setSortable(booleans[j]);
					break;

				default:
					break;
				}
			}
		}
	}
}
