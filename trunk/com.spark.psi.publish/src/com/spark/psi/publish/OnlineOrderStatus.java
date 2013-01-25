package com.spark.psi.publish;


/**
 * 
 * <p>���϶���״̬</p>
 *
 */
public enum OnlineOrderStatus{
	
	/**
	 * ������
	 */
	Paying("01","������"),
	/**
	 * ����Ч
	 */
	Effected("02","����Ч"),
	/**
	 * �����
	 */
	Picking("03","�����"),
	/**
	 * ������
	 */
	Delivering("04","������"),
	/**
	 * �ѵ���
	 */
	Arrivaled("05","�ѵ���"),
	/**
	 * ���
	 */
	Finished("06","�����");
	
	
	
	final String code,name;

	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }
	
	private OnlineOrderStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static OnlineOrderStatus getStatus(String code)
	{
		if(code.equals(OnlineOrderStatus.Paying.getCode()))
		{
			return OnlineOrderStatus.Paying;
		}
		else if(code.equals(OnlineOrderStatus.Arrivaled.getCode()))
		{
			return OnlineOrderStatus.Arrivaled;
		}
		else if(code.equals(OnlineOrderStatus.Delivering.getCode()))
		{
			return OnlineOrderStatus.Delivering;
		}
		else if(code.equals(OnlineOrderStatus.Effected.getCode()))
		{
			return OnlineOrderStatus.Effected;
		}
		else if(code.equals(OnlineOrderStatus.Finished.getCode()))
		{
			return OnlineOrderStatus.Finished;
		}
		else if(code.equals(OnlineOrderStatus.Picking.getCode()))
		{
			return OnlineOrderStatus.Picking;
		}
		else
		{
			return null;
		}
	}
		
}	
