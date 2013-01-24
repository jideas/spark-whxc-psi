package com.spark.oms.publish;
/**
 * ��֧��¼��Ŀ�������豸����Ʒ����ͨѶ��������
 */
public enum PaymentServiceEnum {
	SupportServiceType("01","�����豸"),
	ProductServiceType("02","��Ʒ����"),
	ServiceName("12","��Ʒ�����"),
	MobileServiceType("03","ͨѶ����"),
	MobileServiceName("13","ͨѶ�����"),
	RefundService("04","�����˿�"),
	ReturnAccountName("14","�˻�����˻�"),
	
	OtherServiceName("16","����"),
	OtherServiceType("06","����");
	
	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
