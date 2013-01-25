package com.spark.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.OrderException;

/**
 * 客户行用额度工具
 * @author MoDI
 *
 */
public final class AlreadyUseCreditUtil {
	private AlreadyUseCreditUtil(){
	}
	
	/**
	 * 修改信用额度异常
	 * @author MoDI
	 *
	 */
	@SuppressWarnings("serial")
	public static final class AlredyUseCreditException extends OrderException{
	}
	
	/**
	 * 增加客户已用信用额度
	 * @param context
	 * @param customerId
	 * @param amount
	 * @return
	 * @throws AlredyUseCreditException
	 */
	public static final boolean add(Context context, GUID customerId, double amount) throws AlredyUseCreditException{
		AlreadyUseCreditTask task = new AlreadyUseCreditTask(customerId, amount);
		try{
			context.handle(task, AlreadyUseCreditTask.Method.ADD);
		}catch(Throwable e){
			throw new AlredyUseCreditException();
		}
		return true;
	}
	
	/**
	 * 减少客户已用信用额度
	 * @param context
	 * @param customerId
	 * @param amount
	 * @return
	 * @throws AlredyUseCreditException
	 */
	public static final boolean sub(Context context, GUID customerId, double amount) throws AlredyUseCreditException{
		AlreadyUseCreditTask task = new AlreadyUseCreditTask(customerId, amount);
		try{
			context.handle(task, AlreadyUseCreditTask.Method.SUB);
		}catch(Throwable e){
			throw new AlredyUseCreditException();
		}
		return true;
	}
	
	/**
	 * 获得客户已用信用额度
	 * @param context
	 * @param customerId
	 * @return
	 */
	public static final double getDouble(Context context, GUID customerId){
		AlreadyUseCredit auc = context.find(AlreadyUseCredit.class, customerId);
		if(null == auc){
			return 0;
		}
		else{
			return auc.getAleardyUseCredit();
		}
	}
}
