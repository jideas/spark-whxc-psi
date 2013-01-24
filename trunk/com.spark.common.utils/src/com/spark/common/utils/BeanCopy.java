package com.spark.common.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class BeanCopy {

	@SuppressWarnings("unchecked")
	public static <T> T copy(Object o,T t){
		
		Class clazz = o.getClass();
		Field far[] = t.getClass().getDeclaredFields();
		for(Field f : far){
			try {
				String getName = "get"+getMethodName(f.getName());
				if(f.getType().equals(boolean.class)){
					getName = "is"+getMethodName(f.getName());
				}
				Method om = clazz.getDeclaredMethod(getName);
				Method tm = t.getClass().getDeclaredMethod("set"+getMethodName(f.getName()), new Class[]{f.getType()});
				tm.invoke(t, om.invoke(o));
			} catch (Exception e) {
				System.out.println("com.spark.common.utils.BeanCopy:"+clazz.getName()+"."+f.getName()+"ณ๖ดํมห");
			}
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> copys(List o,List<T> tlist){
		List list = new ArrayList();
		for(int i=o.size()-1;i>=0;i--){
			list.add(copy(o.get(i),tlist.get(i)));
		}
		return list;
	}

	private static String getMethodName(String s){
		String str = null;
		str = s.substring(0,1).toUpperCase()+s.substring(1);		
		return str;
	}
}
