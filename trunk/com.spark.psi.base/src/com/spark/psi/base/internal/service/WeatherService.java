/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.mainpage.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-8-1       Administrator        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.mainpage.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-8-1       Administrator        
 * ============================================================*/

package com.spark.psi.base.internal.service;


import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.service.orm.ORM_weather;
import com.spark.psi.base.publicimpl.WeatherImpl;
import com.spark.psi.publish.base.index.entity.Weather;
import com.spark.psi.publish.base.index.task.WeatherTask;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Administrator
 * @version 2011-8-1
 */

public class WeatherService extends Service {

	protected final ORM_weather q_ORM_weather;

	protected WeatherService(ORM_weather qORMWeather){
	    super("WeatherService");
	    q_ORM_weather = qORMWeather;
    }

	@Publish
    protected class BaseWeatherCityProvider extends OneKeyResultListProvider<Weather, GUID> {
    	
    	protected void provide(Context context, GUID id,List<Weather> list) throws Throwable {
    		ORMAccessor<WeatherImpl> acc = null;
    		try{
    			acc = context.newORMAccessor(q_ORM_weather);
    			list.addAll(acc.fetch(id));
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}finally{
    			acc.unuse();
    		}
    	}
    }
	
	@Publish
	protected class WeatherCityAddHandler extends TaskMethodHandler<WeatherTask, WeatherTask.Method> {
		
		protected WeatherCityAddHandler(){
	        super(WeatherTask.Method.ADD);
	        
        }

		@Override
        protected void handle(Context context, WeatherTask task) throws Throwable{
			ORMAccessor<WeatherImpl> acc = null;
			try {
				acc = context.newORMAccessor(q_ORM_weather);
				WeatherImpl weather = new WeatherImpl();
				weather.setRecid(task.getRecid());
				weather.setCity_name(task.getCity_name());
				weather.setCity_order(task.getCity_order());
				acc.insert(weather);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally{
				acc.unuse();
			}
        }
		
	}
	
	@Publish
	protected class WeatjherCityDelhandler extends TaskMethodHandler<WeatherTask, WeatherTask.Method> {

		protected WeatjherCityDelhandler(){
	        super(WeatherTask.Method.DEL);
        }

		@Override
        protected void handle(Context context, WeatherTask task) throws Throwable{
			ORMAccessor<WeatherImpl> acc = null;
			try {
				acc = context.newORMAccessor(q_ORM_weather);
				acc.delete(task.getRecid());
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally{
				acc.unuse();
			}
        }
		
	}
}
