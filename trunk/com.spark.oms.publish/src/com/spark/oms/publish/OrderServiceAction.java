package com.spark.oms.publish;
/**
 * ����ִ��״�����½����޸ģ���������ѣ�ͣ�ã��ָ�
 */
public enum OrderServiceAction {
	New("01","�½�"),
	Modify("02","�޸�"),
	Run("03","����"),
	Change("04","���"),
	Extend("05","����"),
	Stop("06","ͣ��"),
	Restore("07","�ָ�"),
	Finish("08","����"),
	Terminate("09","��ֹ");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private OrderServiceAction(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceAction getOrderServiceAction(String code){
		if(New.code.equals(code)){
			return New;
		}else if(Modify.code.equals(code)){
			return Modify;
		}else if(Run.code.equals(code)){
			return Run;
		}else if(Change.code.equals(code)){
			return Change;
		}else if(Extend.code.equals(code)){
			return Extend;
		}else if(Stop.code.equals(code)){
			return Stop;
		}else if(Finish.code.equals(code)){
			return Finish;
		}else if(Terminate.code.equals(code)){
			return Terminate;
		}else{
			return null;
		}
	}
}
