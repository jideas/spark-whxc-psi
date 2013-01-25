package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Store;

abstract class ServiceCheckImpl implements IServiceCheck{
	protected Boolean isError;//�Ƿ����
	protected GoodsItem goodsItem;//��Ʒ��Ŀ
	protected OrderInfo orderInfo;//������Ϣ
	protected Store store;//�ֿ�
	protected Promotion2 promotion;//����
	protected Inventory inventory;//���
	protected Context context;
	private CheckParam params;
	private final CheckEnum checkEnum;
	private int index = -1;
//	/**
//	 * @param context
//	 */
//	public ServiceCheckImpl(Context context, CheckEnum checkEnum) {
//		super();
//		this.context = context;
//		this.checkEnum = checkEnum;
//	}
	
	/**
	 * @param context
	 * @param param
	 */
	public ServiceCheckImpl(Context context, CheckParam param) {
		super();
		this.context = context;
		this.params = param;
		this.checkEnum = param.getCheckEnum();
	}

	public GoodsItem getGoodsItem() {
		return goodsItem;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public Promotion2 getPromotion() {
		return promotion;
	}

	public Store getStore() {
		return store;
	}
	
	public CheckEnum getCheckEnum() {
		return checkEnum;
	}
	
	public void setCheckParam(CheckParam param) {
		this.params = param;
	}
	
	public Inventory getGoodsInventory() {
		return inventory;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public boolean isError() throws UntreatedException {
		if(null == isError){
			if(null == params){
				throw new UntreatedException();
			}
			isError = doError();
		}
		return isError;
	}
	
	public boolean doError() {
		isError = doCheck(params);
		return isError;
	}
	
	/**
	 * ִ��У�飬�д��󷵻�true
	 * @param param
	 * @return boolean
	 */
	protected abstract boolean doCheck(CheckParam param);
	
	@SuppressWarnings("serial")
	protected class UntreatedException extends Throwable{
		public UntreatedException(){
			super("����У�����Ϊ�գ�����ִ�н������У����");
		}
	}
}
