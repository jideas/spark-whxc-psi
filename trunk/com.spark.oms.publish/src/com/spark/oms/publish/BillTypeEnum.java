package com.spark.oms.publish;

import java.util.ArrayList;
import java.util.List;


/**
 * 单据编号默认生成规则枚举
 * 
 *
 */
public enum BillTypeEnum {
	
	saleManager("01","销售管理","","","",0),
	saleBillNum("0101","销售单号","01","SS","1,1,1",4),
	saleReturnBillNum("0102","销售退货号","01","ST","1,1,1",4),
	retailManager("02","零售管理","","","",0),
	retailBillNum("0201","零售单号","02","LS","1,1,1",4),
	retailReturnBillNum("0202","零售退货号","02","LT","1,1,1",4),
	purchaseManager("03","采购管理","","","",0),
	purchaseBillNum("0301","采购单号","03","CG","1,1,1",4),
	purchaseReturnBillNum("0302","采购退货号","03","CT","1,1,1",4),
	inventoryManager("04","库存管理","","","",0),
	checkInBillNum("0401","入库单号","04","RK","1,1,1",4),
	checkOutBillNum("0402","出库单号","04","CK","1,1,1",4),
	checkBillNum("0403","盘点单号","04","PD","1,1,1",4),
	allocateBillNum("0404","调拨单号","04","DB","1,1,1",4),
	dismountingBillNum("0405","拆装单号","04","CZ","1,1,1",4),
	FinancialManager("05","财务管理","","","",0),
	paymentBillNum("0501","付款单号","05","FK","1,1,1",4),
	gatheringBillNum("0502","收款单号","05","SK","1,1,1",4),
	billingNum("0503","开票记录编号","05","KP","1,1,1",4),
	entityManager("06","实体管理","","","",0),
	goodsNum("0601","商品编号","06","SP","0,0,0",6),
	employeeNum("0602","员工编号","06","YG","0,0,0",6);
	
	
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
		throw new IllegalArgumentException(id + "不是一个正确的单据枚举");
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