package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.util.checking.IServiceCheck.CheckParam;
import com.spark.order.util.checking.IServiceCheck.ErrorEnumException;

/**
 * <p>ҵ��У��ʵ��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-22
 */
public final class ServiceCheckFactory {
	private ServiceCheckFactory(){
	}
	/**
	 * <p>��ҪУ���ҳ��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	public enum CheckEnum{
		/**
		 * ����(���������ڿɴ������������۵��ڴ�������)
		 */
		promotion,
		/**
		 * �ж�ֱ����Ʒ�Ѳɹ�
		 */
		direct_goods,
		/**
		 * �ֿ�ͣ�� 
		 */
		store_stop,
		/**
		 * �Ƿ�ֻ��һ���ֿ⣨�Ƿ�ֱ�����ɳ��ⵥУ�飬����Ԥ���������ڣ�
		 */
		store_one,
		/**
		 * ��Ʒͣ��
		 */
		goods_stop,
		/**
		 * ��Ʒ��������0���
		 */
		goods_count_zero,
		/**
		 * ��泬�������ޣ��ɹ���
		 */
		inventory_count_upper,
		/**
		 * ��泬������ޣ��ɹ���
		 */
		inventory_amount_upper,
		/**
		 * �ɹ�Ԥ����Ʒ���㣨�������㽻������
		 */
		purchase_warning_lack,
	}
//	public static IServiceCheck getServiceCheck(Context context, CheckEnum checkEnum) throws ErrorEnumException{
//		switch (checkEnum) {
//		case promotion:
//			return new GoodsPromotionImpl(context, checkEnum);
//		case goods_stop:
//			return new GoodsStopImpl(context, checkEnum);
//		case store_stop:
//			return new StoreStopImpl(context, checkEnum);
//		case store_one:
//			return new StoreOneImpl(context, checkEnum);
//		case inventory_count_upper:
//			return new InventoryCountImpl(context, checkEnum);
//		case inventory_amount_upper:
//			return new InventoryAmountImpl(context, checkEnum);
//		case purchase_warning_lack:
//			return new PurchaseWarningImpl(context, checkEnum);
//		case goods_count_zero:
//			return new GoodsCountZeroImpl(context, checkEnum);
//		case direct_goods:
//			return new DirectGoodsImpl(context, checkEnum);
//		default:
//			throw new ErrorEnumException();
//		}
//	}
	
	public static IServiceCheck getServiceCheck(Context context, CheckParam param) throws ErrorEnumException{
		switch (param.getCheckEnum()) {
		case promotion:
			return new GoodsPromotionImpl(context, param);
		case goods_stop:
			return new GoodsStopImpl(context, param);
		case store_stop:
			return new StoreStopImpl(context, param);
		case store_one:
			return new StoreOneImpl(context, param);
		case inventory_count_upper:
			return new InventoryCountImpl(context, param);
		case inventory_amount_upper:
			return new InventoryAmountImpl(context, param);
		case purchase_warning_lack:
			return new PurchaseWarningImpl(context, param);
		case goods_count_zero:
			return new GoodsCountZeroImpl(context, param);
		case direct_goods:
			return new DirectGoodsImpl(context, param);
		default:
			throw new ErrorEnumException();
		}
	}
}
