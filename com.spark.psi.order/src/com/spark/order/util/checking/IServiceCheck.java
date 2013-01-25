package com.spark.order.util.checking;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;
import com.spark.order.util.checking.ServiceCheckImpl.UntreatedException;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Store;

/**
 * <p>业务校验</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-22
 */
public interface IServiceCheck{
	/**
	 * <p>类型错误异常</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	@SuppressWarnings("serial")
	public class ErrorEnumException extends Throwable{
		public ErrorEnumException(){
			super("业务校验对象实例化时，传入异常枚举错误");
		}
	}
	/**
	 * <p>不存在当前页面校验数据项实例，请扩展...</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	@SuppressWarnings("serial")
	public class ServiceCheckInitException extends Throwable{
		public ServiceCheckInitException(){
			super("不存在当前页面校验数据项实例，请扩展...");
		}
	}
	/**
	 * <p>校验参数控制</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-22
	 */
	public class CheckParam{
		private GUID id;
		private GUID id2;
		private double count;//数量
		private double money;//金额
		private final CheckEnum checkEnum;//审核类型
		
		/**
		 * 设置预计发货日期(只有一个可用仓库校验)、
		 * @param checkEnum
		 */
		public CheckParam(CheckEnum checkEnum){
			this.checkEnum = checkEnum;
		}
		/**
		 * 库存金额上限、库存数量上限、是否满足预警库存校验
		 * @param goodsItemId
		 * @param storeId
		 * @param d
		 * @param checkEnum
		 * @throws ServiceCheckInitException
		 */
		public CheckParam(GUID goodsItemId, GUID storeId, double d, CheckEnum checkEnum) throws ServiceCheckInitException {
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case inventory_amount_upper:
				this.money = d;
				break;
			case inventory_count_upper:
				this.count = d;
				break;
			case purchase_warning_lack:
				this.count = d;
				break;
			default:
				throw new ServiceCheckInitException();
			}
			this.id = goodsItemId;
			this.id2 = storeId;
		}
		
		/**
		 * 商品采购销售数量(0)
		 * @param d
		 * @param checkEnum
		 * @throws ServiceCheckInitException 
		 */
		public CheckParam(double d, CheckEnum checkEnum) throws ServiceCheckInitException{
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case goods_count_zero:
				this.count = d;
				break;
			default:
				throw new ServiceCheckInitException();
			}
		}
		
		/**
		 * 促销校验
		 * @param id
		 * @param count
		 * @param price
		 * @param checkEnum
		 * @throws ServiceCheckInitException 
		 */
		public CheckParam(GUID id, double count, double price, CheckEnum checkEnum) throws ServiceCheckInitException{
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case promotion:
				this.money = price;
				this.count = count;
				break;
			default:
				throw new ServiceCheckInitException();
			}
			this.id = id;
		}
		
		/**
		 * 商品停售、采购直供商品已采购、仓库停用
		 * @param storeId
		 * @throws ServiceCheckInitException 
		 */
		public CheckParam(GUID id, CheckEnum checkEnum) throws ServiceCheckInitException {
			this.checkEnum = checkEnum;
			switch (checkEnum) {
			case goods_stop:
				this.id = id;
				break;
			case direct_goods:
				this.id = id;
				break;
			case store_stop:
				this.id = id;
				break;
			default:
				throw new ServiceCheckInitException();
			}
		}

		
		//===========get方法=============
		/**
		 * @return the id
		 */
		public GUID getId() {
			return id;
		}
		
		/**
		 * 
		 * @return GUID
		 */
		public GUID getId2() {
			return id2;
		}
		
		/**
		 * @return the count
		 */
		public double getCount() {
			return count;
		}
		
		/**
		 * @return the money
		 */
		public double getMoney() {
			return money;
		}
		
		/**
		 * @return the checkEnum
		 */
		public CheckEnum getCheckEnum() {
			return checkEnum;
		}
		
	}
	/**
	 * 获得校验类型
	 * @return CheckType
	 */
	CheckEnum getCheckEnum();
	/**
	 * 是否错误，有错返回true
	 * @return boolean
	 * @throws UntreatedException 
	 */
	boolean isError() throws UntreatedException;
	/**
	 * 执行验证，返回是否错误，有错返回true
	 * @param param
	 * @return boolean
	 */
	boolean doError();
	/**
	 * 获得促销对象，无返回null
	 * @return Promotion
	 */
	Promotion2 getPromotion();
	/**
	 * 获得订单对象（销售、销售退货，采购、采购退货），无返回null
	 * @return OrderInfo
	 */
	OrderInfo getOrderInfo();
	/**
	 * 获得商品条目对象，无返回null
	 * @return GoodsItem
	 */
	GoodsItem getGoodsItem();
	/**
	 * 获得仓库对象，无返回null
	 * @return Store
	 */
	Store getStore();
	/**
	 * 获得商品库存信息，无返回null
	 * @return GoodsInventory
	 */
	Inventory getGoodsInventory();
	/**
	 * 放入参数对象
	 *  void
	 */
	void setCheckParam(CheckParam param);
	/**
	 * 获得当前序列
	 * @return int
	 */
	int getIndex();
	/**
	 * 放入当前序列
	 *  void
	 */
	void setIndex(int index);
}
