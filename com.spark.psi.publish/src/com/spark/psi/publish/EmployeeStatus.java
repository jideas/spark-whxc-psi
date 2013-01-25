package com.spark.psi.publish;

/**
 * Ա��״̬
 */
public enum EmployeeStatus {
	/**����*/
	Normal("01", "����"), //
	/**��ְ*/
	Departure("02", "��ְ"),
	/**�����ܾ���*/
	Supper("00","�����ܾ���"); //

	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
	 * ���ݴ����ȡö�ٶ���
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