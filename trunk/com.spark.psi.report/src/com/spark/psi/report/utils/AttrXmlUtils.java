/**
 * 
 */
package com.spark.psi.report.utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.xml.sax.SAXException;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.event.ChangedType;
import com.spark.psi.base.event.NoticeStatusChangeEvent.NoticeAction;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.CheckingOutType;

/**
 * 队列中参数存储为xml格式，该类为转换工具
 */
public abstract class AttrXmlUtils{

	/**
	 * 生成xml
	 * 
	 * @param event
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public static String createXml(Event event){
		StringBuilder ss = new StringBuilder("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		ss.append("<saas>");
		Set<Field> set = new HashSet<Field>();
		Field[] fs = event.getClass().getDeclaredFields();
		for(Field f : fs){
			set.add(f);
		}
		Class classa = event.getClass();
		while(classa.getSuperclass() != null){
			classa = classa.getSuperclass();
			Field[] fs2 = classa.getDeclaredFields();
			for(Field f : fs2){
				set.add(f);
			}
		}

		if(0 == set.size()){
			ss.append("</saas>");
			return ss.toString();
		}

		for(Field f : set){
			String name = "";
			String value = "";
			f.setAccessible(true);
			name = f.getName();
			Object obj = null;
			try{
				obj = f.get(event);
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			catch(IllegalAccessException e){
				e.printStackTrace();
			}
			if(null != obj){
				value = obj.toString();
			}
			f.setAccessible(false);
			ss.append("<field name=\"" + name + "\">" + value + "</field>");
		}
		ss.append("</saas>");
		return ss.toString();
	}

	/**
	 * 解析xml
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseXml(String xml, Class<T> clazz){
		SXElementBuilder seb = null;
		try{
			seb = new SXElementBuilder();
		}
		catch(SAXException e){
			e.printStackTrace();
		}
		SXElement root = null;
		try{
			root = seb.build(xml.getBytes()).firstChild();
		}
		catch(SAXException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		Iterator<SXElement> elist = root.getChildren("field").iterator();
		T event = null;
		try{
			event = (T)clazz.newInstance();
		}
		catch(Exception e){
			Constructor con = clazz.getConstructors()[0];
			Class[] cs = con.getParameterTypes();
			Object[] objs = new Object[cs.length];
			try{
				event = (T)con.newInstance(objs);
			}
			catch(IllegalArgumentException e1){
				e1.printStackTrace();
			}
			catch(InstantiationException e1){
				e1.printStackTrace();
			}
			catch(IllegalAccessException e1){
				e1.printStackTrace();
			}
			catch(InvocationTargetException e1){
				e1.printStackTrace();
			}
		}

		if(null == event){
			return null;
		}
		while(elist.hasNext()){
			SXElement el = elist.next();
			String name = el.getAttribute("name");
			String value = el.getText();
			Class ccc = clazz;
			Field f = null;
			try{
				f = clazz.getDeclaredField(name);
			}
			catch(SecurityException e){
				e.printStackTrace();
			}
			catch(NoSuchFieldException e){
				while(null != ccc.getSuperclass() && null == f){
					ccc = ccc.getSuperclass();
					try{
						f = ccc.getDeclaredField(name);
					}
					catch(SecurityException e1){
						e1.printStackTrace();
					}
					catch(NoSuchFieldException e1){
						continue;
					}
				}

			}
			if(null == f){
				continue;
			}
			f.setAccessible(true);
			Class fclass = f.getType();
			try{
				Object obj = getRealValue(fclass, value);
				if(null != obj){
					f.set(event, obj);
				}
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			catch(IllegalAccessException e){

				e.printStackTrace();
			}
			f.setAccessible(false);
		}

		return event;
	}

	/**
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Object getRealValue(Class fclass, String value){
		if(CheckIsNull.isEmpty(value)){
			return null;
		}
		if(String.class.equals(fclass)){
			return value;
		}
		else if(GUID.class.equals(fclass)){
			return GUID.valueOf(value);
		}
		else if(Double.class.equals(fclass)){
			return Double.parseDouble(value);
		}
		else if(Long.class.equals(fclass)){
			return Long.parseLong(value);
		}
		else if(Integer.class.equals(fclass)){
			return Integer.parseInt(value);
		}
		else if(Boolean.class.equals(fclass)){
			return Boolean.parseBoolean(value);
		}
		else if(ChangedType.class.equals(fclass)){
			return ChangedType.getChangedType(value);
		}
		else if(CheckingInType.class.equals(fclass)){
			return CheckingInType.valueOf(value);
		}
		else if(CheckingOutType.class.equals(fclass)){
			return CheckingOutType.valueOf(value);
		}
		else if(NoticeAction.class.equals(fclass)){
			return NoticeAction.valueOf(value);
		}
		else if(com.spark.psi.base.event.EmployeeStatusChangeEvent.Action.class.equals(fclass)){
			return com.spark.psi.base.event.EmployeeStatusChangeEvent.Action.valueOf(value);
		}
		else if(com.spark.psi.base.ApprovalConfig.Mode.class.equals(fclass)){
			return com.spark.psi.base.ApprovalConfig.Mode.valueOf(value);
		}
		return null;
	}
}
