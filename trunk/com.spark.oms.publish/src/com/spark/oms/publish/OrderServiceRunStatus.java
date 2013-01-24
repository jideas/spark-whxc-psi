package com.spark.oms.publish;
/**
 * ������������״̬������Ԥ�����������С��ȴ�ִ�С��ȴ����á��������С���ֹ(�ֶ���ֹ)������(�������)
 * ˵�����½��������ȴ����á�-�ܾ����½ϵͳ����-���������С�-�տ�-���������С�-����-������������ԭ������
 *                                                     -δ�տ�-������״̬�����ϣ�        ����ԭ��δ�տ�
 *                                                                       ����-���¶����ȴ���Ч��-�տ�-���ȴ�ִ�С�-ʱ�䵽-���¶�����ʽ���У��϶�������,����ԭ��������
 *                                                                                            -δ�տ�-������,����ԭ��δ����
 *                                                                       ��� -���¶����ȴ���Ч���տ�.....                 ���¶�����ʽ����,�϶�������������ԭ�򣺱��
 *   
 *                                                                       
 *                                                                       ��ֹ-����������״̬��������ֹ������ԭ����ֹ
 *                                                                       
 **/


public enum OrderServiceRunStatus {
	//�½��������д�״̬���ȴ�ҵ��ϵͳ������ɺ�
	Enable(3,"�ȴ�����"),
	//����ı���������ڷ��񣬻ָ�����
	Wait(4,"�ȴ�ִ��"),
	Run(2,"��������"),
	//�ֶ�����
	Suspend(5,"��ֹ"),
//	//����������
	Finish(1,"����"),
//	//����ʵ��
	Warning(7,"����Ԥ��"),
	Protected(6,"��������"),
	
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
