package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.util.checking.IServiceCheck.CheckParam;
import com.spark.order.util.checking.IServiceCheck.ErrorEnumException;

/**
 * <p>业务校验实例化工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-22
 */
public final class ServiceCheckFactory {
	private ServiceCheckFactory(){
	}
	/**
	 * <p>需要校验的页面</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	public enum CheckEnum{
		/**
		 * 促销(数量不大于可促销数量，单价等于促销单价)
		 */
		promotion,
		/**
		 * 判断直供商品已采购
		 */
		direct_goods,
		/**
		 * 仓库停用 
		 */
		store_stop,
		/**
		 * 是否只有一个仓库（是否直接生成出库单校验，填入预警出库日期）
		 */
		store_one,
		/**
		 * 商品停售
		 */
		goods_stop,
		/**
		 * 商品数量大于0检查
		 */
		goods_count_zero,
		/**
		 * 库存超数量上限（采购）
		 */
		inventory_count_upper,
		/**
		 * 库存超金额上限（采购）
		 */
		inventory_amount_upper,
		/**
		 * 采购预警商品不足（不能满足交货需求）
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
