package com.spark.common.utils.character;

import java.util.Comparator;

import com.jiuqi.dna.core.type.GUID;

public abstract class SortComparator<T> implements Comparator<T>{
	
	protected int desc(int i,String sortType){
		if(sortType.equals("Desc")){
			i = i > 0 ? -1 : 1;
		}
		return i;
	}
	
	/**
	 * b1 ÊÇ·ñ ´óÓÚ  b2
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static int compare(String b1,String b2){
		if(StringHelper.isEmpty(b1))return -1;
		if(StringHelper.isEmpty(b2))return 1;
		b1 = b1.toUpperCase();
		b2 = b2.toUpperCase();
		int len = b1.length() > b2.length() ? b2.length() : b1.length();
		for (int i = 0; i < len; i++) {
			int i1 = b1.charAt(i);
			int i2 = b2.charAt(i);
			if(i1>i2){
				return 1;
			}else if(i1<i2){
				return -1;
			}
			i++;
		}
		return b1.length() > b2.length() ? 1 : -1;
	}

	public static int compare(int i1,int i2){
		return i1-i2;
	}

	public static int compare(double d1,double d2){
		return d1-d2 > 0 ? 1 : -1;
	}
	
	public static int compare(long l1,long l2){
		return l1-l2 > 0 ? 1 : -1;
	}
	
	public static int compare(GUID l1,GUID l2){
		if(l1==null)return -1;
		if(l2==null)return 1;
		return compare(l1.toString(), l2.toString());
	}
	
}
