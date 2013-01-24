package com.spark.psi.publish;


/**
 * 
 * <p>����״̬</p> 
 */
public enum PromotionStatus{
	/**
	 * δȷ��
	 */
	Submit("01", "δȷ��"),
	/**
	 * ������
	 */
	Issue("02", "������"),
	/**
	 * ������
	 */
	Approve("03", "������"),
	/**
	 * ���˻�
	 */
	Return("04", "���˻�"),
	/**
	 * ������
	 */
	Promotioning("05", "������"),
	/**
	 * ����ֹ
	 */
	Suspended("06", "����ֹ"),
	/**
	 * �ѹ���
	 */
	Out_of_date("07", "�ѹ���"),
	/**
	 * ��ͣ��
	 */
	Stoped_sales("08", "��ͣ��"),
	/**
	 * ������
	 */
	Finished("09", "������");
	private String code;
	private String name;
	private PromotionStatus(String code, String name){
		this.code = code;
		this.name = name;
	}
	/**
	 * ״̬��ʶ
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * ״̬����
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * �жϵ�ǰö���Ƿ���ֱ��ö���е�һ�֣��Ƿ���true
	 * @param statuss
	 * @return boolean
	 */
	public boolean isIn(PromotionStatus...statuss ){
		for(PromotionStatus ps : statuss){
			if(this == ps){
				return true;
			}
		}
		return false;
	}
}
