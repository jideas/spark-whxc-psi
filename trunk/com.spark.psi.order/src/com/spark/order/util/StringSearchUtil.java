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
 * <p>�ַ����������봦����</p>
 *	�����ڷ���value�д��ڵ�keyֵ���ϡ�keyֵ���ظ�
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-16
 * 
 *2012-3-30��չ֧��ƴ��������������md�����������д��Ľ�����ǰֻʵ�ֹ���
 */
public class StringSearchUtil {
	private final Map<String, String> map = new HashMap<String, String>();
	private final Map<String, String> pyMap = new HashMap<String, String>();
	/**
	 * ����key-value
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
	 * ��ȡvalue�д���ָ��ֵ��keyֵ����
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
	 * ��ȡvalue�д���ָ��ֵ��keyֵ����(֧��ƴ������������)
	 * @param search
	 * @return List<String>
	 */
	public List<String> getKeys2(String search){
		List<String> list = new ArrayList<String>();
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		for(;iterator.hasNext();){
			Entry<String, String> e = iterator.next();
			// ֧��ƴ��������
			if(null != e.getValue() && ((e.getValue().indexOf(search) > -1) || PinyinHelper.getLetter(e.getValue()).indexOf(search) > -1)){
				list.add(e.getKey());
			}
		}
		return list;
	}
	
	/**
	 * ��ռ���
	 *  void
	 */
	public void clear(){
		map.clear();
	}
}
