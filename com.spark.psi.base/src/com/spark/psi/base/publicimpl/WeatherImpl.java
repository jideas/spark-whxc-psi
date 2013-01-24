package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.index.entity.Weather;

public class WeatherImpl implements Weather{
	private GUID recid;
	private String city_name;
	private String city_order;
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String cityName) {
		city_name = cityName;
	}
	public String getCity_order() {
		return city_order;
	}
	public void setCity_order(String cityOrder) {
		city_order = cityOrder;
	}
	

}
