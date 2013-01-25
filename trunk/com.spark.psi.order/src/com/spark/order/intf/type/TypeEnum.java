package com.spark.order.intf.type;

import java.util.List;

import com.spark.order.intf.OrderEnum;

/**
 * <p>
 * ��������
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-10-26
 */
public enum TypeEnum {
	/**
	 * ��ͨ����
	 */
	PLAIN("01", "��ͨ����"),
	/**
	 * ���϶���
	 */
	@Deprecated
	ON_LINE("02", "���϶���"), 
	/**
	 * ���϶���(ֱ��)
	 */
	@Deprecated
	ON_LINE_DIRECT("04", "���϶���(ֱ��)"),
	/**
	 * "�ɹ��˻�", "�����˻�"
	 */
	CANCEL("05", "�ɹ��˻�", "�����˻�"),
	/**
	 * ���ǲɹ�
	 */
	BUY_SPORADIC("06", "���ǲɹ�"),
	/**
	 * ��Ʒ����
	 */
	Retail("07", "��Ʒ����"),
	/**
	 * �����˻�
	 */
	Retail_Return("08", "�����˻�");
	private String key;
	private String[] name;

	private TypeEnum(String key, String... names) {
		this.key = key;
		this.name = names;
		EnumUtil.tyepEnummap.put(key, this);
		for(OrderEnum orderEnum : OrderEnum.values()){
			EnumUtil.addOrderTypeSearch(orderEnum, key, getName(orderEnum));
		}
	}
	/**
	 * �����ã�����code����
	 * @param searchText
	 * @return String[]
	 */
	public static List<String> searchType(OrderEnum orderEnum, String searchText){
		return EnumUtil.getOrderTypeSearch(orderEnum, searchText);
	}

	/**
	 * ��ȡ��ʶ���ƣ��˻����������
	 * 
	 * @return String[]
	 */
	public String getName() {
		return name[0];
	}

	/**
	 * ��ȡ����key
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * ��ȡ��ʶ����
	 * 
	 * @return String[]
	 */
	@Deprecated
	public String getName(BillsEnum ord) {
		if (BillsEnum.SALE.equals(ord) && name.length > 1) {
			return name[1];
		}
		return name[0];
	}
	
	/**
	 * ��ȡ��ʶ����
	 * 
	 * @return String[]
	 */
	public String getName(OrderEnum ord) {
		if ((OrderEnum.Sales == ord || OrderEnum.Sales_Return == ord) && name.length > 1) {
			return name[1];
		}
		return name[0];
	}

	/**
	 * ����key���ö��
	 * 
	 * @param key
	 * @return TypeEnum
	 */
	public static TypeEnum getType(String key) {
		return EnumUtil.tyepEnummap.get(key);
	}

	/**
	 * �жϵ�ǰö���Ƿ���ָ��ö����һ�����ǵĻ�����true
	 * 
	 * @param enums
	 * @return boolean
	 */
	public boolean isInType(TypeEnum... enums) {
		for (TypeEnum type : enums) {
			if (this == type) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �жϵ�ǰö���Ƿ���ָ��ö����һ�����ǵĻ�����true
	 * 
	 * @param enums
	 * @return boolean
	 */
	public boolean isInType(String... keys) {
		for (String type : keys) {
			if (isThis(type)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �ж�ָ��key�Ƿ�ǰö��
	 * @param key
	 * @return boolean
	 */
	public boolean isThis(String key){
		return this.key.equals(key);
	}
}
