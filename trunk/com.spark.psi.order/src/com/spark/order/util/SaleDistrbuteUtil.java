package com.spark.order.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * 销售配货工具类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-16
 */
public final class SaleDistrbuteUtil {
	private static Map<Long, Timers> map = new HashMap<Long, Timers>();
	
	private static final int DeadLine = 1000*20;
	
	private static Timer timer = new Timer();
	
	private static class Timers {
				
		private GUID orderId;
		
		private Long sessionId;
		
		private boolean isClose = false;
		
		private boolean check(){
			if(isClose){
				map.remove(sessionId);
				return false;
			}
			isClose = true;
			return true;
		}
		
		public void start(){
			timer.schedule(new TimerTask(){
				
				@Override
				public void run(){
					if(check()){
						start();
					}
				}
			}, DeadLine);
		}
		
		public Timers(final long sessionId,GUID orderId){
			this.sessionId = sessionId;
			this.orderId = orderId;
        }
		
		public void reset(){
			isClose = false;
		}
		
		@Override
		public boolean equals(Object obj){
			return ((Timers)obj).orderId.equals(orderId);
		}		
	}

	/**
	 * 开始分配
	 * 
	 * @param orderId
	 *            void
	 */
	public static synchronized boolean start(long sessionId, GUID orderId) {
		if(isDistrbuting(sessionId, orderId)){
			return false;
		}
		Timers t = new Timers(sessionId, orderId);
		map.put(sessionId, t);
		t.start();
		return true;
	}

	public static synchronized void reset(long sessionId){
		Timers t = map.get(sessionId);
		if(t!=null)t.reset();
	}
	
	/**
	 * 结束分配
	 * 
	 * @param orderId
	 *            void
	 */
	public static synchronized void cancel(long sessionId) {
		map.remove(sessionId);
	}

	// XXX：注意处理并发问题
	public static synchronized boolean isDistrbuting(long sessionId, GUID orderId) {
		Timers v = map.get(sessionId);
		if (v!=null&&orderId.equals(v.orderId)) {
			return false;
		}
		return map.containsValue(new Timers(sessionId, orderId));
	}
}
