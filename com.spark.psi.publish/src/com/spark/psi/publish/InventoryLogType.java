package com.spark.psi.publish;

public enum InventoryLogType {


	/** �ɹ���� */
	INSTORAGE("1","�ɹ����"),
	/** �ɹ��˻� */
	BUYCANCEL("2","�ɹ��˻�"),
	/** ���۳���*/
	OUTSTORAGE("3","���۳���"),
	/** �����˻�*/
	SALECANCEL("4","�����˻�"),
	/** ������*/
	STOREALLCOATE("5","������"),
	/** ����װ*/
	STOREDISMOUNT("6","����װ"),
	/** ����̵�*/
	STORECHECK("7","����̵�"),
	/** �ֿ����� */
	INITVALUE("8","�ֿ�����"),
	/**��Ʒ��ӯ**/
	CheckProfit("9","��Ʒ��ӯ"),
	/**��Ʒ�̿�**/
	CheckDeficient("10","��Ʒ�̿�"),
	/**���ǲɹ�**/
	HandtomouthBuying("11","���ǲɹ�"),
	/**���۳�����Ʒ**/
	HandtomouthOUTSTORAGE("12","���۳�����Ʒ"),
	/**�����˻����**/
	HandtomouthCANCELSTORAGE("13","�����˻����"),
	/**��������**/
	OTHEROUTSTORAGE("14","��������"),
	/**������Ʒ**/
	GOODSOUTSTORAGE("15","������Ʒ"),
	/**������Ʒ**/
	GOODSINSTORAGE("16","������Ʒ"),
	/**�����ɱ�**/
	AdjustInventoryCost("17","�����ɱ�"),
	/**��汨��**/
	ReportLoss("18","��汨��"),
	/**���ϳ���**/
	MaterialsCheckout("19","���ϳ���"),
	/**���ϳ���**/
	MaterialsCheckin("20","���ϳ���"),
	/**��Ʒ����**/
	GoodsCheckout("21","��Ʒ����"),
	/**��Ʒ���**/
	GoodsCheckin("22","��Ʒ���"),
	GoodsSplitCheckout("23","��Ʒ��ֳ���"),
	GoodsSplitCheckin("24","��Ʒ������"),
	
	
	
	
	
	
	
	
	;

	
	private String code,name; 
	
	InventoryLogType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode(){
		return code;
	}
	public String getName() {
		return name;
	}
	public static InventoryLogType getEnum(String code){
		InventoryLogType typeEnum = null;
		int codeI = Integer.parseInt(code);
		switch(codeI){
		case 1:
			typeEnum = InventoryLogType.INSTORAGE;
			break;
		case 2:
			typeEnum = InventoryLogType.BUYCANCEL;
			break;
		case 3:
			typeEnum = InventoryLogType.OUTSTORAGE;
			break;
		case 4:
			typeEnum = InventoryLogType.SALECANCEL;
			break;
		case 5:
			typeEnum = InventoryLogType.STOREALLCOATE;
			break;
		case 6:
			typeEnum = InventoryLogType.STOREDISMOUNT;
			break;
		case 7:
			typeEnum = InventoryLogType.STORECHECK;
			break;
		case 8:
			typeEnum = InventoryLogType.INITVALUE;
			break;
		case 9:
			typeEnum = InventoryLogType.CheckProfit;
			break;
		case 10:
			typeEnum = InventoryLogType.CheckDeficient;
			break;
		case 11:
			typeEnum = InventoryLogType.HandtomouthBuying;
			break;
		case 12:
			typeEnum = InventoryLogType.HandtomouthOUTSTORAGE;
			break;
		case 13:
			typeEnum = InventoryLogType.HandtomouthCANCELSTORAGE;
			break;
		case 14:
			typeEnum = InventoryLogType.OTHEROUTSTORAGE;
			break;
		case 15:
			typeEnum = InventoryLogType.GOODSOUTSTORAGE;
			break;
		case 16:
			typeEnum = InventoryLogType.GOODSINSTORAGE;
			break;
		case 17:
			typeEnum = InventoryLogType.AdjustInventoryCost;
			break;
		case 18:
			typeEnum = InventoryLogType.ReportLoss;
			break;
		case 19:
			typeEnum = InventoryLogType.MaterialsCheckout;
			break;
		case 20:
			typeEnum = InventoryLogType.MaterialsCheckin;
			break;
		case 21:
			typeEnum = InventoryLogType.GoodsCheckout;
			break;
		case 22:
			typeEnum = InventoryLogType.GoodsCheckin;
			break;
		case 23:
			typeEnum = InventoryLogType.GoodsSplitCheckout;
			break;
		case 24:
			typeEnum = InventoryLogType.GoodsSplitCheckin;
			break;
		}
		
		return typeEnum;
	}
	

	
}
