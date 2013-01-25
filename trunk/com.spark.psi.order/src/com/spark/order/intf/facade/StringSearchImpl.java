package com.spark.order.intf.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.spark.common.utils.character.PinyinHelper;

class StringSearchImpl implements IStringSearch{
	final Map<String, String> map = new HashMap<String, String>();
	public IStringSearch put(String code, String value) {
		if(null == code || null == value){
			throw new NullPointerException("键值对不能为空！");
		}
		if(map.containsKey(code)){
			map.put(code, map.get(code)+value);
		}
		else{
			map.put(code, value);
		}
		return this;
	}

	public String[] searchValue(String searchText) {
		List<String> list = new ArrayList<String>();
		for(Entry<String, String> entry : map.entrySet()){
			if(entry.getValue().contains(searchText)){
				list.add(entry.getKey());
			}
		}
		return list.toArray(new String[list.size()]);
	}

	public String[] searchValue2(String searchText) {
		List<String> list = new ArrayList<String>();
		for(Entry<String, String> entry : map.entrySet()){
			if(entry.getValue().contains(searchText) || 
					PinyinHelper.getLetter(entry.getValue()).contains(searchText)){
				list.add(entry.getKey());
			}
		}
		return list.toArray(new String[list.size()]);
	}
}
