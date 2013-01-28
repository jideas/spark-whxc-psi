package com.spark.psi.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.util.StringUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.internal.entity.GoodsItemImpl;
import com.spark.psi.base.internal.entity.SubGoodsProperty;
import com.spark.psi.base.utils.GoodsConstant.PropertyInputType;


/**
 * <p>
 * ��Ʒ���Խ���������
 * </p>
 */
public class GoodsProperyUtil {
//	private final static String SET_METHOD = "set";
//	private final static String GET_METHOD = "get";
	private final static String INPUT_TYPE_NAME = "propertyInputType";
	private final static String INPUT_TYPE_GUID = "goodsTypePropertyGuid";
	private final static String INPUT_TYPE_SG = "SG_SHURU";
	private final static String INPUT_TYPE_XL = "XL_XUANZHE";
	private final static String PROPERTY_NAME = "subPropertyName";
	private final static String EMPTY = "";
	private final static String CLASS = "class";

	/**
	 * �������Կؼ������ƣ����ݣ�¼�뷽ʽ���б���װ��JSON����
	 * 
	 * @param subGoodsPropertyList
	 * @return
	 * @throws JSONException
	 *             JSONArray
	 */
	public static String subGoodsPropertyToJson(
			List<SubGoodsProperty> subGoodsPropertyList) throws JSONException {
		JSONArray jSONArray = new JSONArray();
		JSONObject jSONObject = null;
		for (int i = 0; i < subGoodsPropertyList.size(); i++) {
			SubGoodsProperty subProperty = subGoodsPropertyList.get(i);
			jSONObject = new JSONObject(subProperty);
			jSONObject.remove(CLASS);
			jSONArray.put(jSONObject);
		}
		String jsonStr = jSONArray.toString();
		return jsonStr;
	}
	
	public static String subGoodsPropertyToString(String[] propertys){
		return Arrays.toString(propertys).replaceAll("[\\[\\]\\s]", "");
	}
	
	public static String[] subGoodsPropertyToArray(String str){
		return str.split(",",-1);
	}

//	private static String removeClassByRegex(String str) {
//		
//	}
//	public static List<SubGoodsProperty> getSubGoodsProperty1(String property) {
//		List<SubGoodsProperty> list = new ArrayList<SubGoodsProperty>();
//		if (null == property) {
//			return list;
//		}
//		try {
//			JSONArray array = new JSONArray(property);
//			for (int i = 0; i < array.length(); i++) {
//				JSONObject j = array.getJSONObject(i);
//				String class_str = j.get("class").toString().substring(6);
//				Class<SubGoodsProperty> clazz = (Class<SubGoodsProperty>) Class
//						.forName(class_str);
//				Field fields[] = getClassFields(clazz);
//				SubGoodsProperty subGoodsProperty = new SubGoodsProperty();
//				for (Field field : fields) {
//					String value = j.get(field.getName()).toString();
//					Method m = getClassMethod(SET_METHOD, clazz, field);
//					if (field.getName().equals(INPUT_TYPE_NAME)) {
//						if (INPUT_TYPE_SG.equals(value)) {
//							m
//									.invoke(
//											subGoodsProperty,
//											new Object[] { PropertyInputType.SG_SHURU });
//						} else if (INPUT_TYPE_XL.equals(value)) {
//							m
//									.invoke(
//											subGoodsProperty,
//											new Object[] { PropertyInputType.XL_XUANZHE });
//						}
//					} else if (field.getName().equals(INPUT_TYPE_GUID)) {
//						m.invoke(subGoodsProperty, new Object[] { GUID
//								.valueOf(value) });
//					} else {
//						m.invoke(subGoodsProperty, new Object[] { value });
//					}
//				}
//				list.add(subGoodsProperty);
//			}
//
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

    @SuppressWarnings("unchecked")
    public static List<SubGoodsProperty> getSubGoodsProperty(String property) {
		List<SubGoodsProperty> list = new ArrayList<SubGoodsProperty>();
		if (null == property) {
			return list;
		}
		try {
			JSONArray array = new JSONArray(property);
			for (int i = 0; i < array.length(); i++) {
				JSONObject j = array.getJSONObject(i);
				Iterator<String> it = j.keys();
				SubGoodsProperty subGoodsProperty = new SubGoodsProperty();
				while (it.hasNext()) {
					String key = (String) it.next();
					if(!CLASS.equals(key) && !"".equals(key)) {
						String value = (String) j.get(key);
						if (key.equals(INPUT_TYPE_NAME)) {
							if (INPUT_TYPE_SG.equals(value)) {
								subGoodsProperty.setPropertyInputType(PropertyInputType.SG_SHURU);
							} else if (INPUT_TYPE_XL.equals(value)) {
								subGoodsProperty.setPropertyInputType(PropertyInputType.XL_XUANZHE);
							}
						} else 
							if (INPUT_TYPE_GUID.equals(key)) {
							subGoodsProperty.setGoodsTypePropertyGuid(GUID
									.valueOf(value));
						} else if(PROPERTY_NAME.equals(key)){
							subGoodsProperty.setSubPropertyName(value);
						} else {
							subGoodsProperty.setSubPropertyValue(value);
						}
					}
				}
				list.add(subGoodsProperty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * �õ�������λ�����������ַ�
	 * 
	 * @return String
	 */
	public static String getPropertyWithUnit(GoodsItemImpl property) {
		String str = "";
		String unitValue = "";
		if (null != property) {
			for (int i = 0; i < property.getGoodsProperties().length; i++) {
				if(StringUtils.isEmpty(property.getGoodsProperties()[i]))continue;
				if(i==0){
					property.setGoodsUnit(property.getGoodsProperties()[i]);
					unitValue = property.getGoodsUnit();
				}else{
					str += "[" + property.getGoodsProperties()[i] + "]";
				}
			}
		}
		if(CheckIsNull.isNotEmpty(str)) {
			str = str + "/[" + unitValue + "]";
		} else {
			str = "[" + unitValue + "]";
		}
		return str;
	}

	/**
	 * �õ�������λ�����������ַ�
	 * 
	 * @return String
	 */
//	public static String getPropertyWithOutUnit(GoodsItemImpl property) {
//		String str = "";
//		if (null != property) {
//			for (int i = 0; i < property.getSubGoodsPropertyList().size(); i++) {
//				SubGoodsProperty subGoodsProperty = property
//						.getSubGoodsPropertyList().get(i);
//				if (GoodsConstant.GOODS_UNIT.equals(subGoodsProperty
//						.getSubPropertyName())) {
//					continue;
//				} 
//				str = str + "[" + subGoodsProperty.getSubPropertyValue() + "]";
//			}
//		}
//		return str;
//	}
//
//	public static String getPropertyWithOutUnit(String propertyValue) {
//		List<SubGoodsProperty> list = getSubGoodsProperty(propertyValue);
//		GoodsItemImpl property = new GoodsItemImpl();
//		property.setSubGoodsPropertyList(list);
//		return getPropertyWithOutUnit(property);
//	}

	/**
	 * �õ���������λ�����������ַ�
	 * 
	 * @return String
	 */
	public static String getPropertyWithOutUnit(GoodsItemImpl property) {
		String str = "";
		if (null != property) {
			for (int i = 1; i < property.getGoodsProperties().length; i++) {
				if(StringUtils.isEmpty(property.getGoodsProperties()[i]))continue;
				str += "[" + property.getGoodsProperties()[i] + "]";
			}
		}
		return str;
	}

	/**
	 * ������Ʒ�����Ժ͵�λ
	 * 
	 * @param property
	 * @param unit
	 * @return
	 */
	public static String concatPropertyAndUnit(String property, String unit) {
		if (CheckIsNull.isNotEmpty(property) && CheckIsNull.isNotEmpty(unit)) {
			return property.concat("/[").concat(unit).concat("]");
		} else if (property != null && unit == null) {
			return property;
		} else {
			return unit;
		}
	}

	/**
	 * �Ѵ���λ�������ַ�������ֳɲ�����λ���ַ���
	 * 
	 * @param property
	 * @return
	 */
	public static String splitPropertyWithoutUnit(String property) {
		String str = EMPTY;
		if (property != null) {
			int index = property.indexOf("/[");
			if (index != -1) {
				str = property.substring(0, index);
			} else {
				str = property;
			}
		}
		return str;
	}

	/**
	 * �Ӵ���λ�������ַ������ȡ��λ
	 * 
	 * @param property
	 * @return
	 */
	public static String splitPropertyUnit(String property) {
		String str = EMPTY;
		if (property != null) {
			int index = property.indexOf("/[");
			if (index != -1 && property.length() > 2) {
				str = property.substring(index + 2, property.length() - 1);
			}
		}
		return str;
	}

	public static void main(String[] args) {
		String str = "[500ml/ƿ][450ml/ƿ]/[ƿ]";
		// String str = "/[";
		System.out.println(splitPropertyUnit(str));
		System.out.println(splitPropertyWithoutUnit(str));
		
		String property = "[{\"propertyInputType\":\"XL_XUANZHE\",\"subPropertyName\":\"��λ\",\"class\":\"class com.spark.bus.common.goods.intf.entity.SubGoodsProperty\",\"goodsTypePropertyGuid\":\"33AAF33FE0000021158131C80E33A2D0\",\"subPropertyValue\":\"33AAF33FE0000021158131C80E33A2D0\"},{\"propertyInputType\":\"SG_SHURU\",\"subPropertyName\":\"���\",\"class\":\"class com.spark.bus.common.goods.intf.entity.SubGoodsProperty\",\"goodsTypePropertyGuid\":\"10000000000000000000000000000001\",\"subPropertyValue\":\"21\"}]";
		List<SubGoodsProperty> list = getSubGoodsProperty(property);
		for (SubGoodsProperty subGoodsProperty : list) {
			System.out.println(subGoodsProperty.getSubPropertyName()+"  --  "+subGoodsProperty.getSubPropertyValue());
		}
		
		String[] strs = new String[]{"1","3","a"};
		System.out.println(Arrays.toString(strs).replaceAll("[\\[\\]\\s]", ""));
		
		str = "jaaa,";
		strs = str.split(",",-1);
		for(String string : strs){
	        System.out.println(string);
	        System.out.println("====");
        }
	}
	
}