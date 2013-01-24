package com.spark.oms.publish;
/**
 *开票状态：开票，未开票
 */
public enum DrawBillStatus {
	Draw("01","为开票"),
	UnDraw("02","开票");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
