package com.spark.oms.publish;
/**
 * 订单服务运行状态：运行预警、保号运行、等待执行、等待启用、正常运行、中止(手动中止)、结束(服务结束)
 * 说明：新建订单》等待启用》-总经理登陆系统配置-》正常运行》-收款-》正常运行》-到期-》结束，结束原因：期满
 *                                                     -未收款-》运行状态：作废？        结束原因：未收款
 *                                                                       续单-》新订单等待生效》-收款-》等待执行》-时间到-》新订单正式运行，老订单结束,结束原因：续单》
 *                                                                                            -未收款-》作废,结束原因：未交款
 *                                                                       变更 -》新订单等待生效》收款.....                 》新订单正式运行,老订单结束，结束原因：变更
 *   
 *                                                                       
 *                                                                       中止-》订单运行状态：运行中止，结束原因：中止
 *                                                                       
 **/


public enum OrderServiceRunStatus {
	//新建订单才有此状态，等待业务系统配置完成后
	Enable(3,"等待启用"),
	//交款的变更服务，续期服务，恢复服务
	Wait(4,"等待执行"),
	Run(2,"正常运行"),
	//手动结束
	Suspend(5,"中止"),
//	//服务器结束
	Finish(1,"结束"),
//	//二期实现
	Warning(7,"运行预警"),
	Protected(6,"保号运行"),
	
	//added all,use query where
	All(0,"全部");
	
	/**
	 * 代码
	 */
	private int code;

	/**
	 * 名称
	 */
	private String name;
	
	private OrderServiceRunStatus(int code,String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceRunStatus getOrderServiceRunStatus(int code){
		if(Wait.code==code){
			return Wait;
		}else if(Enable.code==code){
			return Enable;
		}else if(Run.code==code){
			return Run;
			}
			
//		else if(Warning.code==code){
//			return Warning;
//		}else if(Protected.code==code){
//			return Protected;
//		}else if(Run.code==code){
//			return Run;
//		}else if(Suspend.code==code){
//			return Suspend;
//		}else if(Terminat.code==code){
//			return Terminat;
//		}
		else{
			return null;
		}
	}
}
