/**
 * 
 */
package com.spark.psi.report.constant;

/**
 * 报表常量数据
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
	 * 过滤条件的类型
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
	 * 客户记录类型
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
