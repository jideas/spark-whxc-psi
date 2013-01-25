package com.spark.order.promotion.intf;

import java.util.ArrayList;
import java.util.List;

import com.spark.psi.publish.PromotionStatus;

/**
 * <p>����״̬</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public enum PromotionStatus2 {
	/**
	 * δȷ��
	 */
	Submit(PromotionStatus.Submit, "01", "δȷ��"),
	/**
	 * ������
	 */
	Issue(PromotionStatus.Issue, "02", "������"),
	/**
	 * ������
	 */
	Approve(PromotionStatus.Approve, "03", "������"),
	/**
	 * ���˻�
	 */
	Return(PromotionStatus.Return, "04", "���˻�"),
	/**
	 * ������
	 */
	Promotioning(PromotionStatus.Promotioning, "05", "������"),
	/**
	 * ����ֹ
	 */
	Suspended(PromotionStatus.Suspended, "06", "����ֹ"),
	/**
	 * �ѹ���
	 */
	Out_of_date(PromotionStatus.Out_of_date, "07", "�ѹ���"),
	/**
	 * ��ͣ��
	 */
	Stoped_sales(PromotionStatus.Stoped_sales, "08", "��ͣ��"),
	/**
	 * ������
	 */
	Finished(PromotionStatus.Finished, "09", "������");
	private PromotionStatus status;
	private String code;
	private String name;
	private PromotionStatus2(PromotionStatus status, String code, String name){
		PromotionConstant2.statusPubToMe.put(status, this);
		PromotionConstant2.statusMap.put(code, this);
		PromotionConstant2.StatusSearchUtil.put(code, name);
		this.status = status;
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
	 * ��õ�ǰ״̬��Ӧ���ⲿ״̬���޷���null
	 * @return PromotionStatus
	 */
	public PromotionStatus getPubstatus() {
		return status;
	}
	
	/**
	 * �жϵ�ǰö���Ƿ���ֱ��ö���е�һ�֣��Ƿ���true
	 * @param statuss
	 * @return boolean
	 */
	public boolean isInEnum(PromotionStatus2...statuss ){
		for(PromotionStatus2 ps : statuss){
			if(this == ps){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ���ⲿ״̬ת�����ڲ�״̬����
	 * @param pubstatuss
	 * @return PromotionStatus2[]
	 */
	public static PromotionStatus2[] getPubToMe(PromotionStatus... pubstatuss){
		List<PromotionStatus2> list = new ArrayList<PromotionStatus2>();
		for(PromotionStatus status : pubstatuss){
			list.add(getPubToMe(status));
		}
		return list.toArray(new PromotionStatus2[list.size()]);
	}
	
	/**
	 * ���ⲿ״̬ת�����ڲ�״̬����
	 * @param pubstatuss
	 * @return PromotionStatus2[]
	 */
	public static List<PromotionStatus2> getPubToMeList(PromotionStatus... pubstatuss){
		List<PromotionStatus2> list = new ArrayList<PromotionStatus2>();
		for(PromotionStatus status : pubstatuss){
			list.add(getPubToMe(status));
		}
		return list;
	}
	
	/**
	 * �����ⲿ״̬��ȡ�ڲ�״̬
	 * @param pubstatus
	 * @return PromotionStatus2
	 */
	public static PromotionStatus2 getPubToMe(PromotionStatus pubstatus){
		return PromotionConstant2.statusPubToMe.get(pubstatus);
	}
	
	/**
	 * �����ã�����code����
	 * @param searchText
	 * @return String[]
	 */
	public static List<String> searchstatus(String searchText){
		return PromotionConstant2.StatusSearchUtil.getKeys(searchText);
	}
	
	/**
	 * ����key���ö����
	 * @param code
	 * @return PromotionStatus
	 */
	public static PromotionStatus2 getPromotionStatus2(String code){
		return PromotionConstant2.statusMap.get(code);
	}
}
