package com.spark.order.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.spark.common.utils.character.PinyinHelper;

/**
 * <p>字符串搜索翻译处理工具</p>
 *	主用于返回value中存在的key值集合。key值不重复
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-16
 * 
 *2012-3-30扩展支持拼音助记码搜索。md。。。方法有待改进，当前只实现功能
 */
public class StringSearchUtil {
	private final Map<String, String> map = new HashMap<String, String>();
	private final Map<String, String> pyMap = new HashMap<String, String>();
	/**
	 * 存入key-value
	 * @param key
	 * @param value
	 * @return StringSearchUtil
	 */
	public StringSearchUtil put(String key, String value){
		map.put(key, value);
		pyMap.put(key, PinyinHelper.getLetter(value));
		return this;
	}
	
	/**
	 * 获取value中存在指定值的key值集合
	 * @param search
	 * @return List<String>
	 */
	public List<String> getKeys(String search){
		List<String> list = new ArrayList<String>();
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		for(;iterator.hasNext();){
			Entry<String, String> e = iterator.next();
			if(e.getValue().indexOf(search) > -1){
				list.add(e.getKey());
			}
		}
		return list;
	}
	
	/**
	 * 获取value中存在指定值的key值集合(支持拼音助记码搜索)
	 * @param search
	 * @return List<String>
	 */
	public List<String> getKeys2(String search){
		List<String> list = new ArrayList<String>();
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		for(;iterator.hasNext();){
			Entry<String, String> e = iterator.next();
			// 支持拼音助记码
			if(null != e.getValue() && ((e.getValue().indexOf(search) > -1) || PinyinHelper.getLetter(e.getValue()).indexOf(search) > -1)){
				list.add(e.getKey());
			}
		}
		return list;
	}
	
	/**
	 * 清空集合
	 *  void
	 */
	public void clear(){
		map.clear();
	}
}
