package com.spark.psi.publish;

/**
 * 员工状态
 */
public enum EmployeeStatus {
	/**正常*/
	Normal("01", "正常"), //
	/**离职*/
	Departure("02", "离职"),
	/**超级总经理*/
	Supper("00","超级总经理"); //

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private EmployeeStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 根据代码获取枚举对象
	 * 
	 * @param code
	 * @return
	 */
	public final static EmployeeStatus getEmployeeStatus(String code) {
		for(EmployeeStatus status : EmployeeStatus.values()){
	        if(status.getCode().equals(code)){
	        	return status;
	        }
        }
		return null;
//		if (Normal.code.equals(code)) {
//			return Normal;
//		} else if (Departure.code.equals(code)) {
//			return Departure;
//		}
//		return null;
	}

}