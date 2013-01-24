/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.event.TimerEvent;

/**
 * ʱ���¼�������
 */
public class PlanTaskService extends Service{
	public PlanTaskService(){
		super("PlanTaskService");
	}

	public void init(final Context context){
		context.asyncHandle(new TTask());
	}

	private static boolean sendNow = false;

	@Publish
	protected class DoTimer extends SimpleTaskMethodHandler<TTask>{
		@Override
		protected void handle(Context context, TTask task) throws Throwable{
			Timer timer = new Timer();
			TimerTask t = new TimerTask(){
				@Override
				public void run(){
					sendNow = true; 
				}
			};
			long today = new Date().getTime();
			long endOfToday = DateUtil.truncDay(today) + 24 * 60 * 60000;
			long begin = endOfToday - today - 300000;
			if( begin<0){
				begin += 24 * 60 * 60000;
				sendNow = true;
			} 
			// �ӽ��콫����ʱ������5���ӣ���ʼִ�У�ÿ��һ��
			timer.schedule(t, begin, 24 * 60 * 60000); 
			while(true){ 
				if(sendNow){
					sendNow = false;
					context.occur(getTimerEvent()); 
				} 
				Thread.sleep(60000);
			}
		}
	}

	/**
	 * ����event
	 */
	private TimerEvent getTimerEvent(){
		TimerEvent event = new TimerEvent();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		long nextMonthBegin = DateUtil.truncDay(c.getTime().getTime());

		long today = new Date().getTime();

		long endOfToday = DateUtil.truncDay(today) + 24 * 60 * 60000;
		if(endOfToday >= today && endOfToday <= (300000 + today)){
			event.setEndOfToday(true);
		}
		if(nextMonthBegin >= today && nextMonthBegin <= (300000 + today)){
			event.setEndOfThisMonth(true);
		}
		return event;
	}

	protected class TTask extends SimpleTask{
	}
}
