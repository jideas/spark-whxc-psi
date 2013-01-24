package com.spark.psi.base.internal.entity.helper;

import com.jiuqi.dna.core.Filter;
import com.spark.psi.base.internal.entity.ILevelTree;

/**
 * 级次树过滤器<Br>
 * 通过此过滤器可以查询出指定节点的所有子孙节点
 
 *
 * @param <T>
 */
public class LevelTreeFilter<T extends ILevelTree> implements Filter<T> {
	
	static byte[] bb = new byte[1];
	
	private String path;

	public LevelTreeFilter(String path){
		this.path = path;
	}
	
	public boolean accept(T item) {		
		if(!item.isValid())return false;
		return compare(item.getPath(),path) && compare( path+"FF",item.getPath());
	}

	/**
	 * b1 是否 大于  b2
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean compare(String b1,String b2){
		b1 = b1.toUpperCase();
		b2 = b2.toUpperCase();
		int len = b1.length() > b2.length() ? b2.length() : b1.length();
		for (int i = 0; i < len; i++) {
			int i1 = Integer.parseInt(Integer.toHexString(b1.charAt(i)));
			int i2 = Integer.parseInt(Integer.toHexString(b2.charAt(i)));
			if(i1>i2){
				return true;
			}else if(i1<i2){
				return false;
			}
			i++;
		}
		return b1.length() >= b2.length();
	}
	
		
	public static void main(String[] args) {
		System.out.println(compare("00D7E940597BB001693ACAD7A626E20C3600A37F068E971853B19B564FFAF89215C3", "00D7E940597BB001693ACAD7A626E20C36FF"));
	}
	
}
