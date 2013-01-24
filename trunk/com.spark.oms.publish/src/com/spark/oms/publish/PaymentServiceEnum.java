package com.spark.oms.publish;
/**
 * 收支记录项目：配套设备、产品服务、通讯服务、其他
 */
public enum PaymentServiceEnum {
	SupportServiceType("01","配套设备"),
	ProductServiceType("02","产品服务"),
	ServiceName("12","产品服务费"),
	MobileServiceType("03","通讯服务"),
	MobileServiceName("13","通讯服务费"),
	RefundService("04","服务退款"),
	ReturnAccountName("14","退还租金账户"),
	
	OtherServiceName("16","其他"),
	OtherServiceType("06","其他");
	
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private PaymentServiceEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static PaymentServiceEnum getPaymentItem(String code){
		if(SupportServiceType.code.equals(code)){
			return SupportServiceType;
		}else if(ProductServiceType.code.equals(code)){
			return ProductServiceType;
		}else if(MobileServiceType.code.equals(code)){
			return MobileServiceType;
		}else if(RefundService.code.equals(code)){
			return RefundService;
		}else if(OtherServiceType.code.equals(code)){
			return OtherServiceType;
		}else if(MobileServiceName.code.equals(code)){
			return MobileServiceName;
		}else if(ServiceName.code.equals(code)){
			return ServiceName;
		}else if(OtherServiceName.code.equals(code)){
			return OtherServiceName;
		}else if(ReturnAccountName.code.equals(code)){
			return ReturnAccountName;
		}else{
			return null;
		}
	}
	public static PaymentServiceEnum[] getAllServiceEnum(){
		PaymentServiceEnum ps[] = {
				SupportServiceType,ProductServiceType,MobileServiceType,RefundService,OtherServiceType
		};
		return ps;
	}
}
