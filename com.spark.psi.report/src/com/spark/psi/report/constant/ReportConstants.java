/**
 * 
 */
package com.spark.psi.report.constant;

/**
 * ����������
 */
public abstract class ReportConstants{

	public enum SMessageSet{
		Bubbling("01"),
		Monitor("02");
		private String id;

		private SMessageSet(String id){
			this.id = id;
		}

		public String getCode(){
			return id;
		}
	}

	/**
	 * ��������������
	 */
	public enum ConditionType{
		Equals,
		In,
		Less_Than,
		Greater_Than,
		NotEquals,
		Between,
		NotIn,
		Less_ThanOrEquals,
		Greater_ThanOrEquals
	}

	/**
	 * �ͻ���¼����
	 */
	public enum CustomerLogType{
		Create("01"),
		Offical("02");
		private String code;

		private CustomerLogType(String code){
			this.code = code;
		}

		public String getCode(){
			return this.code;
		}

		public static CustomerLogType getInstance(String code){
			if("Create".equals(code)){
				return Create;
			}
			else if("Offical".equals(code)){
				return Offical;
			}
			return null;
		}
	}

}
