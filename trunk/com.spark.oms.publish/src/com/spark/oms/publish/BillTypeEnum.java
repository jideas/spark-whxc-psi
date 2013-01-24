package com.spark.oms.publish;

import java.util.ArrayList;
import java.util.List;


/**
 * ���ݱ��Ĭ�����ɹ���ö��
 * 
 *
 */
public enum BillTypeEnum {
	
	saleManager("01","���۹���","","","",0),
	saleBillNum("0101","���۵���","01","SS","1,1,1",4),
	saleReturnBillNum("0102","�����˻���","01","ST","1,1,1",4),
	retailManager("02","���۹���","","","",0),
	retailBillNum("0201","���۵���","02","LS","1,1,1",4),
	retailReturnBillNum("0202","�����˻���","02","LT","1,1,1",4),
	purchaseManager("03","�ɹ�����","","","",0),
	purchaseBillNum("0301","�ɹ�����","03","CG","1,1,1",4),
	purchaseReturnBillNum("0302","�ɹ��˻���","03","CT","1,1,1",4),
	inventoryManager("04","������","","","",0),
	checkInBillNum("0401","��ⵥ��","04","RK","1,1,1",4),
	checkOutBillNum("0402","���ⵥ��","04","CK","1,1,1",4),
	checkBillNum("0403","�̵㵥��","04","PD","1,1,1",4),
	allocateBillNum("0404","��������","04","DB","1,1,1",4),
	dismountingBillNum("0405","��װ����","04","CZ","1,1,1",4),
	FinancialManager("05","�������","","","",0),
	paymentBillNum("0501","�����","05","FK","1,1,1",4),
	gatheringBillNum("0502","�տ��","05","SK","1,1,1",4),
	billingNum("0503","��Ʊ��¼���","05","KP","1,1,1",4),
	entityManager("06","ʵ�����","","","",0),
	goodsNum("0601","��Ʒ���","06","SP","0,0,0",6),
	employeeNum("0602","Ա�����","06","YG","0,0,0",6);
	
	
	private BillTypeEnum(String id, String name, String parent,String defaultPrefix, String defaultYMD, int defaultPlaces) {
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.defaultPrefix = defaultPrefix;
		this.defaultYMD = defaultYMD;
		this.defaultPlaces = defaultPlaces;
	}
	
	public static BillTypeEnum getBillTypeById(String id) {
		for (BillTypeEnum billType : BillTypeEnum.values()) {
			if (billType.getId().equals(id)) {
				return billType;
			}
		}
		throw new IllegalArgumentException(id + "����һ����ȷ�ĵ���ö��");
	}
	public static List<BillTypeEnum> getBillTypeEnum(){
		List<BillTypeEnum> list = new ArrayList<BillTypeEnum>();
		list.add(saleBillNum);
		list.add(saleReturnBillNum);
		
		list.add(retailBillNum);
		list.add(retailReturnBillNum);
		
		list.add(purchaseBillNum);
		list.add(purchaseReturnBillNum);
		
		list.add(checkInBillNum);
		list.add(checkOutBillNum);
		list.add(checkBillNum);
		list.add(allocateBillNum);
		list.add(dismountingBillNum);
		
		list.add(paymentBillNum);
		list.add(gatheringBillNum);
		list.add(billingNum);
		
		list.add(goodsNum);
		list.add(employeeNum);
		
		return list;
	}
	
	private String id;
	private String name;
	private String parent;
	private String defaultPrefix;
	private String defaultYMD;
	private int defaultPlaces;	
	
	public String getDefaultPrefix() {
		return defaultPrefix;
	}

	public String getDefaultYMD() {
		return defaultYMD;
	}

	public int getDefaultPlaces() {
		return defaultPlaces;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getParent() {
		return parent;
	}
}