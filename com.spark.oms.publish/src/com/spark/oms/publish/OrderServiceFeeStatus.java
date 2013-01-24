package com.spark.oms.publish;
/**
 * �������״̬: ���ѿ��ޡ��ȴ��������������������ٽ����ѡ�������ã��¶�����������
 * 
 * ˵���� �½����������ʹ�á��ٽ����ѡ����ѿ��ޡ���������ٽ����ѡ����ѿ��ޡ���������
 *       ��������ڵ��� �ȴ������������ٽ����ѡ����ѿ��ޡ���
 *       
 *       
 */
public enum OrderServiceFeeStatus {
	Free(3,"�������"),
	Wait(5,"�ȴ�����"),
	Pro(4,"�ٽ�����"),
	Extend(6,"���ѿ���"),
//	NoPay(7,"�޽ɿ�"),
	NoPay(7,""),
	Normal(2,"����"),
	Finish(1,"����"),//�Ѿ��������´ζ���
	
	//added all,use query where
	All(0,"ȫ��");
	
	
	/**
	 * ����
	 */
	private int code;

	/**
	 * ����
	 */
	private String name;
	
	private OrderServiceFeeStatus(int code,String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceFeeStatus getOrderServiceFeeStatus(int code){
		if(Free.code==code){
			return Free;
		}else if(Wait.code==code){
			return Wait;
		}else if(Pro.code==code){
			return Pro;
		}else if(Extend.code==code){
			return Extend;
		}else if(Normal.code==code){
			return Normal;
		}else if(Finish.code==code){
			return Finish;
		}else if(NoPay.code==code){
			return NoPay;
		}else{
			return null;
		}
	}
}
