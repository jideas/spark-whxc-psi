package com.spark.psi.publish.base.station.key;

public class GetNewStationNoKey {
	
	public GetNewStationNoKey(String town){
		this.town = town;
	}

	private String town;
	
	public String getTown(){
		return town;
	}
}
