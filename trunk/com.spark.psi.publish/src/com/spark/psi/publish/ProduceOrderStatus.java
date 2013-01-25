package com.spark.psi.publish;


/**
 * 
 * <p>��������״̬</p>
 *
 */
public enum ProduceOrderStatus{
	
	/**
	 * ���ύ
	 */
	Submiting("01","���ύ"),
	/**
	 * ������
	 */
	Submited("02","������"),
	/**
	 * �˻�
	 */
	Reject("03","���˻�"),
	/**
	 * �ӹ���
	 */
	Producing("04","�ӹ���"),
	/**
	 * �����
	 */
	Finished("05","�����");
	
	
	
	final String code,name;

	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }
	
	private ProduceOrderStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static ProduceOrderStatus getStatus(String code)
	{
		if(code.equals(ProduceOrderStatus.Producing.getCode()))
		{
			return ProduceOrderStatus.Producing;
		}
		else if(code.equals(ProduceOrderStatus.Reject.getCode()))
		{
			return ProduceOrderStatus.Reject;
		}
		else if(code.equals(ProduceOrderStatus.Submited.getCode()))
		{
			return ProduceOrderStatus.Submited;
		}
		else if(code.equals(ProduceOrderStatus.Submiting.getCode()))
		{
			return ProduceOrderStatus.Submiting;
		}
		else if(code.equals(ProduceOrderStatus.Finished.getCode()))
		{
			return ProduceOrderStatus.Finished;
		}
		else
		{
			return null;
		}
	}
		
}	
