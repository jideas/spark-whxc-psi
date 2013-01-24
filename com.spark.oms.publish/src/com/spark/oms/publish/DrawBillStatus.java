package com.spark.oms.publish;
/**
 *��Ʊ״̬����Ʊ��δ��Ʊ
 */
public enum DrawBillStatus {
	Draw("01","Ϊ��Ʊ"),
	UnDraw("02","��Ʊ");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private DrawBillStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static DrawBillStatus getDrawBillStatus(String code){
		if(Draw.code.equals(code)){
			return Draw;
		}else if(UnDraw.code.equals(code)){
			return UnDraw;
		}else{
			return null;
		}
	}
}
