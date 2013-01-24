package com.spark.oms.publish;
/**
 * 服务执行状况：新建；修改；变更；续费；停用；恢复
 */
public enum OrderServiceAction {
	New("01","新建"),
	Modify("02","修改"),
	Run("03","运行"),
	Change("04","变更"),
	Extend("05","续费"),
	Stop("06","停用"),
	Restore("07","恢复"),
	Finish("08","结束"),
	Terminate("09","终止");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
