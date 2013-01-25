package com.spark.order.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spark.order.intf.entity.SearchStatusEntity;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;

/**
 * <p>
 * 单据状态搜索Util
 * </p> 
 */
public class StatusSearchUtil {
	// private final static List<SearchStatusEntity> statusTypes = new
	// ArrayList<SearchStatusEntity>();
	public StatusSearchUtil(){
		init();
	}
	private void init() {
		statusTypes.clear();
		put(statusTypes, BillsEnum.PURCHASE);
		put(statusTypes, BillsEnum.PURCHASE_CANCEL);
		put(statusTypes, BillsEnum.SALE);
		put(statusTypes, BillsEnum.SALE_CANCEL);
	}
	private Map<BillsEnum, List<SearchStatusEntity>> statusTypes = new HashMap<BillsEnum, List<SearchStatusEntity>>();

	private static void put(Map<BillsEnum, List<SearchStatusEntity>> statusTypes, BillsEnum billsEnum) {
		statusTypes.put(billsEnum, new ArrayList<SearchStatusEntity>());
		switch (billsEnum) {
		case PURCHASE:
			addType(statusTypes, billsEnum, TypeEnum.ON_LINE);
			addType(statusTypes, billsEnum, TypeEnum.ON_LINE_DIRECT);
			addType(statusTypes, billsEnum, TypeEnum.PLAIN); 
			break;
		case PURCHASE_CANCEL:
			addType(statusTypes, billsEnum, TypeEnum.CANCEL);
			break;
		case SALE:
			addType(statusTypes, billsEnum, TypeEnum.ON_LINE);
			addType(statusTypes, billsEnum, TypeEnum.PLAIN);
			break;
		case SALE_CANCEL:
			addType(statusTypes, billsEnum, TypeEnum.CANCEL);
			break;
		default:
			break;
		}
//		addType(billsEnum, TypeEnum.CANCEL);
//		addType(billsEnum, TypeEnum.ON_LINE);
//		addType(billsEnum, TypeEnum.ON_LINE_DIRECT);
//		addType(billsEnum, TypeEnum.PLAIN);
//		addType(billsEnum, TypeEnum.PLAIN_DIRECT);
	}

	private static void addType(Map<BillsEnum, List<SearchStatusEntity>> statusTypes, BillsEnum billsEnum, TypeEnum type) {
		addEntity(statusTypes, billsEnum, type, StatusEnum.Finished);
		addEntity(statusTypes, billsEnum, type, StatusEnum.Store_All);
		addEntity(statusTypes, billsEnum, type, StatusEnum.Store_N0);
		addEntity(statusTypes, billsEnum, type, StatusEnum.Store_Part);
		addEntity(statusTypes, billsEnum, type, StatusEnum.Approve);
		statusTypes.get(billsEnum)
				.add(
						new SearchStatusEntity(type.getKey(),
								BillsConstant.STOPED, true));
	}

	private static void addEntity(Map<BillsEnum, List<SearchStatusEntity>> statusTypes, BillsEnum billsEnum, TypeEnum type,
			StatusEnum status) {
		statusTypes.get(billsEnum).add(
				new SearchStatusEntity(type.getKey(), status, status.getName(billsEnum)));
		// statusTypes.add(billsEnum, new SearchStatusEntity(type.getKey(),
		// OrderUtil.getSysFuntionGuid(status), status.getName(e)));
	}

	/**
	 * 根据单据类型获得结果集（
	 * BillsEnum.BUY,BillsEnum.BUY_CANCEL,BillsEnum.SALE,BillsEnum.SALE_CANCEL ）
	 * 
	 * @param billsEnum
	 * @param search
	 * @return List<SearchStatusEntity>
	 */
	public List<SearchStatusEntity> getListByBills(BillsEnum billsEnum,
			String search) {
		List<SearchStatusEntity> list = new ArrayList<SearchStatusEntity>();
		for (SearchStatusEntity e : statusTypes.get(billsEnum)) {
			if (e.getName().indexOf(search) > -1) {
				list.add(e);
			}
		}
		return list;
	}

	/**
	 * 根据模块类型获得结果集(BillsEnum.BUY,BillsEnum.SALE)
	 * 
	 * @param billsEnum
	 * @param search
	 * @return List<SearchStatusEntity>
	 */
	public List<SearchStatusEntity> getListByBus(BillsEnum billsEnum,
			String search) {
		List<SearchStatusEntity> list = new ArrayList<SearchStatusEntity>();
		switch (billsEnum) {
		case PURCHASE:
			list.addAll(getListByBills(billsEnum, search));
			list.addAll(getListByBills(BillsEnum.PURCHASE_CANCEL, search));
			break;
		case SALE:
			list.addAll(getListByBills(billsEnum, search));
			list.addAll(getListByBills(BillsEnum.SALE_CANCEL, search));
			break;
		default:
			break;
		}
		return list;
	}
}
