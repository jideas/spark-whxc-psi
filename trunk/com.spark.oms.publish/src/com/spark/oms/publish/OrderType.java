package com.spark.oms.publish;
/**
 * ��������½�������������������ڵ����ָ���
 * @author mengyongfeng
 *
 */
public enum OrderType {
	NewOrder("01","�½�"),
	PreLongOrder("02","����"),
	ChangeOrder("03","���"),
	RestoreOrder("04","�ָ���"),
	EditOrder("05","�޸�"),
	ShowOrder("06","����"),
	//�½�������������еı�������������ڱ༭����
	PreChangeOrder("07","���");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private OrderType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderType getOrderType(String code){
		if(NewOrder.code.equals(code)){
			return NewOrder;
		}else if(PreLongOrder.code.equals(code)){
			return PreLongOrder;
		}else if(ChangeOrder.code.equals(code)){
			return ChangeOrder;
		}else if(RestoreOrder.code.equals(code)){
			return RestoreOrder;
		}else if(EditOrder.code.equals(code)){
			return EditOrder;
		}else if(ShowOrder.code.equals(code)){
			return ShowOrder;
		}else if(PreChangeOrder.code.equals(code)){
			return PreChangeOrder;
		}else{
			return null;
		}
	}
}
