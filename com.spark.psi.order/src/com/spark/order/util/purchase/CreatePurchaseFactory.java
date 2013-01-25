package com.spark.order.util.purchase;

import com.jiuqi.dna.core.Context;

/**
 * <p>�ɹ�������������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-23
 */
public final class CreatePurchaseFactory {
	private CreatePurchaseFactory(){
		
	}
	public enum CreateEnum{
		/**
		 * ��ͨ����(����ֱ��)
		 */
		Plan,
//		/**
//		 * ֱ������
//		 */
//		Direct,
		/**
		 * ���ǲɹ�
		 */
		Odd_Lot
	}
	/**
	 * ��òɹ�����ʵ��������
	 * @return ICreatePurchase
	 */
	public static ICreatePurchase getCreatePurchase(Context context, CreateEnum createEnum){
		switch (createEnum) {
		case Plan:
			return new PlanPurchaseImpl(context);
		case Odd_Lot:
			return new OddLotPurchaseImpl(context);
		default:
			return null;
		}
	}
}
