package com.spark.psi.publish.base.index.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.index.entity.Weather;

public class WeatherTask extends Task<WeatherTask.Method>{
	public enum Method{
		ADD,EDIT,DEL
	}
	
	public WeatherTask(GUID recid, String cityName, String cityOrder) {
		super();
		this.recid = recid;
		city_name = cityName;
		city_order = cityOrder;
	}
	
	private GUID recid;
	private String city_name;
	private String city_order;
	
	public GUID getRecid() {
		return recid;
	}
	public String getCity_name() {
		return city_name;
	}
	public String getCity_order() {
		return city_order;
	}
	
	
}
