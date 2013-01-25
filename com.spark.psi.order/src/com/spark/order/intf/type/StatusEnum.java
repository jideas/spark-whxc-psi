package com.spark.order.intf.type;

import java.util.ArrayList;
import java.util.List;

import com.spark.order.intf.OrderConstant2;
import com.spark.order.intf.OrderEnum;
import com.spark.psi.publish.OrderStatus;

/**
 * <p>
 * �������̿���
 * </p>
 */
// ��������δ��⡢������⡢ȫ����⡢δ�������ѷ��������˻ء�����ֹ�������
public enum StatusEnum {
	/**
	 * "01", "���ύ", "���ύ", "δȷ��"
	 */
	Submit("01", "���ύ"),
	/**
	 * ���������ݣ�ֻҪ�õ�ǰ�������ġ�
	 */
	Approveing("02", "������"),
	/**
	 * "02", "������", "������", "������"
	 */
	Approve("02", "������"),
	/**
	 * δ��⣨���⣩
	 */
	Store_N0("03", "δ���", "δ����"),
	/**
	 * ������⣨���⣩
	 */
	Store_Part("04", "�������", "���ֳ���"),
	/**
	 * ȫ����⣨���⣩
	 */
	Store_All("05", "ȫ�����", "ȫ������"),
	/**
	 * �Ѳ���
	 */
	Return("06", "���˻�"),
	/**
	 * "07", "�����", "�����", "������"
	 */
	Finished("07", "�����"),
	/**
	 * δ����
	 */
	Consignment_No("08", "δ����"),
	/**
	 * �ѷ���
	 */
	Consignment_Yes("09", "�ѷ���"),
//	 ---------����-------
	/**
	 * �ҵ�
	 */
	Working("21"),
	/**
	 * ���տ�
	 */
	Receipt("22");

	/**
	 * �ж�ָ��key�Ƿ��ǵ�ǰ״̬
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean isThis(String key) {
		return this.getKey().equals(key);
	}
	
	/**
	 * ��ǰ״̬�Ƿ���ָ��״̬�е�һ��
	 * @param enums
	 * @return boolean
	 */
	public boolean isIn(StatusEnum... enums){
		for(StatusEnum s : enums){
			if(this == s){
				return true;
			}
		}
		return false; 
	}

	private final String id;
	private final String[] names;

	private StatusEnum(String key, String... names) {
		this.id = key;
		this.names = names;
		EnumUtil.StatusEnumMap.put(key, this);
		for(OrderEnum orderEnum : OrderEnum.values()){
			EnumUtil.addOrderStatusSearch(orderEnum, key, getName(orderEnum));
			EnumUtil.addOrderStatusSearch(orderEnum, OrderConstant2.stopCode, OrderConstant2.stopName);
		}
	}
	
	/**
	 * �����ã�����code����
	 * @param searchText
	 * @return String[]
	 */
	public static List<String> searchstatus(OrderEnum orderEnum, String searchText){
		return EnumUtil.getOrderStatusSearch(orderEnum, searchText);
	}
	
	/**
	 * ���ⲿ״̬ת�����ڲ�״̬����
	 * @param pubstatuss
	 * @return PromotionStatus2[]
	 * @throws Throwable 
	 */
	public static StatusEnum[] getPubToMe(OrderStatus... pubstatuss) throws Throwable{
		List<StatusEnum> list = new ArrayList<StatusEnum>();
		for(OrderStatus status : pubstatuss){
			StatusEnum se = getPubToMe(status);
			if(null != se){
				list.add(se);
			}
		}
		return list.toArray(new StatusEnum[list.size()]);
	}
	
	/**
	 * �����ⲿ״̬��ȡ�ڲ�״̬
	 * @param pubstatus
	 * @return PromotionStatus2
	 * @throws Throwable 
	 */
	public static StatusEnum getPubToMe(OrderStatus pubstatus) throws Throwable{
		StatusEnum status = null;
		switch (pubstatus) {
		case Submit:
			status = StatusEnum.Submit;
			break;
		case Approval_No:
			status = StatusEnum.Approveing;
			break;
		case Approval_Yes:
			status = StatusEnum.Approve;
			break;
		case CheckedIn:
			status = StatusEnum.Store_All;
			break;
		case CheckedOut:
			status = StatusEnum.Store_All;
			break;
		case CheckingIn_NO:
			status = StatusEnum.Store_N0;
			break;
		case CheckingIn_Part:
			status = StatusEnum.Store_Part;
			break;
		case CheckingOut_No:
			status = StatusEnum.Store_N0;
			break;
		case CheckingOut_Part:
			status = StatusEnum.Store_Part;
			break;
		case CONSIGNMENT_NO:
			status = StatusEnum.Consignment_No;
			break;
		case CONSIGNMENT_YES:
			status = StatusEnum.Consignment_Yes;
			break;
		case Denied:
			status = StatusEnum.Return;
			break;
		case finish:
			status = StatusEnum.Finished;
			break;
		case Stop:
			status = null;
			break;
		default:
			throw new Throwable("��ǰ�ⲿö��ת��δ���ã��뵽StatusEnum�����ö�Ӧ��ϵ");
		}
		return status;
	}

	/**
	 * ���״̬��ʶ
	 * 
	 * @return String
	 */
	public String getKey() {
		return id;
	}
	/**
	 * ��ȡ��ǰ�ڵ�����
	 * 
	 * @param e
	 * @return String
	 */
	@Deprecated
	public String getName(BillsEnum e) {
		if ((BillsEnum.SALE == e || BillsEnum.PURCHASE_CANCEL == e)
				&& names.length > 1) {
			return names[1];
		} else if (BillsEnum.SALE_PROMOTION == e && names.length > 2) {
			return names[2];
		} else if (names.length > 0) {
			return names[0];
		}
		return null;
	}
	
	/**
	 * ��õ�ǰö����ָ�����Ͷ����е�����
	 * @param e
	 * @return String
	 */
	public String getName(OrderEnum e){
		if(e.isIn(OrderEnum.Sales, OrderEnum.Purchase_Return) && names.length > 1){
			return names[1];
		}
		else if(names.length > 0){
			return names[0];
		}
		return null;
	}

	/**
	 * ���۶���
	 * 
	 * @return String
	 */
	public String getSalesOrderStatusName() {
		return getName(OrderEnum.Sales);
	}

	/**
	 * �����˻�
	 * 
	 * @return String
	 */
	public String getSalesReturnstatusName() {
		return getName(OrderEnum.Sales_Return);
	}

	/**
	 * �ɹ�����
	 * 
	 * @return String
	 */
	public String getPurchaseOrderStatusName() {
		return getName(OrderEnum.Purchase);
	}

	/**
	 * �ɹ��˻�
	 * 
	 * @return String
	 */
	public String getPurchaseReturnstatusName() {
		return getName(OrderEnum.Purchase_Return);
	}

	/**
	 * ��ȡָ����ʶ�ڵ�Ľڵ�����
	 * 
	 * @param key
	 * @return String
	 */
	public static StatusEnum getstatus(String key) {
		return EnumUtil.StatusEnumMap.get(key);
	}
}
