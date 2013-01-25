package com.spark.order.service.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.exception.AbortException;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.PhoneAndTypeUtil;
import com.spark.common.utils.PhoneAndTypeUtil.PhoneType;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.customer.AlreadyUseCreditUtil;
import com.spark.customer.AlreadyUseCreditUtil.AlredyUseCreditException;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.entity.SearchStatusEntity;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.task.StopTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.sales.intf.task.SaleExamDeptTask;
import com.spark.order.util.StringSearchUtil;
import com.spark.psi.base.Department;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetAllStoreListKey;
import com.spark.psi.base.key.GetPurchaseOrderGoodsCountByGoodsIdKey;
import com.spark.psi.base.key.GetStoreBySaleManKey;
import com.spark.psi.inventory.intf.entity.instorage.mod.RelationCheckInSheet;
import com.spark.psi.inventory.intf.entity.inventory.GoodsInventorySum;
import com.spark.psi.inventory.intf.entity.outstorage.mod.RelationCheckOutSheet;
import com.spark.psi.inventory.intf.key.instorage.mod.GetRelationCheckInSheetKey;
import com.spark.psi.inventory.intf.key.inventory.GoodsInventorySumKey;
import com.spark.psi.inventory.intf.key.outstorage.mod.GetRelationCheckOutSheetKey;
import com.spark.psi.inventory.intf.key.pub.GetGoodsStoreInventoryKey;
import com.spark.psi.publish.StoreStatus;

/**
 * <p>
 * 订单静态工具类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-10-27
 */
public final class OrderUtil {
	private static SimpleDateFormat sf = BillsConstant.DATA_yyyy_MM_dd;// new

	// SimpleDateFormat("yyyy-MM-dd");

	private OrderUtil() {

	}
	
	/**
	 * 业务回滚
	 * @param context void
	 */
	public static void rollback(Context context){
		try {
			context.abort();
		} catch (AbortException e) {
		}
	}
	
	/**
	 * 获取yyyy-MM-dd样式日期字符串
	 * 
	 * @param l
	 * @return String
	 */
	public static String getDate(long l) {
		return sf.format(new Date(l));
	}
	
	/**
	 * 获得单据编号
	 * @param context
	 * @param billsEnum
	 * @return String
	 */
	public static String getBillsNumber(Context context, BillsEnum billsEnum){
		return context.get(String.class, billsEnum.getSheetType());
//		String str = System.currentTimeMillis()+"";
//		return str.substring(str.length() - 12, str.length());
	}
	
	//=========================基础数据（部门）========================
	public static Department getDepartment(Context context){
		return context.find(Department.class, BillsConstant.getUser(context).getDepartmentId());
	}
	/**
	 * 获得当前人本部分和其子孙部分集合(包含当前部门)
	 * 
	 * @return List<Department>
	 */
	public static List<Department> getDescendantDepts(Context context) {
		List<Department> list = new ArrayList<Department>();
		Department dept = getDepartment(context);
		list.add(dept);
		list.addAll(getDescendantDepts(context, dept.getId()));
		return list;
	}

	/**
	 * 获得销售经理本部分和直属下级部分(包含当前部门)
	 * 
	 * @return List<Department>
	 */
	public static List<Department> getChildrenDepts(Context context) {
		List<Department> list = new ArrayList<Department>();
		Department dept = getDepartment(context);
		list.add(dept);
		list.addAll(getChildrenDepts(context, dept.getId()));
		return list;
	}
	
	/**
	 * 获得指定部门的直属子部门集合(包含当前部门)
	 * @param context
	 * @param deptId
	 * @return List<Department>
	 */
	public static List<Department> getChildrenDepts(Context context, GUID deptId){
		List<Department> list = new ArrayList<Department>();
		Department dept = context.find(Department.class, deptId);
		list.add(dept);
		list.addAll(Arrays.asList(dept.getChildren(context)));
		return list;
	}
	
	/**
	 * 获得指定部门的子孙部分集合(包含当前部门)
	 * 
	 * @return List<Department>
	 */
	public static List<Department> getDescendantDepts(Context context, GUID deptId) {
		List<Department> list = new ArrayList<Department>();
		Department dept = context.find(Department.class, deptId);
		list.add(dept);
		list.addAll(Arrays.asList(dept.getDescendants(context)));
		return list;
	}

	//==========================客户已用信用额度===========================
	/**
	 * AlreadyUseCreditUtil.add(context, customerId, changeAmount);
	 * 增加客户已用信用额度（减少客户可用信用额度）
	 * @param customerId
	 * @param changeAmount
	 * @return boolean
	 */
	@Deprecated
	public static boolean addAlreadyUseCredit(Context context, GUID customerId, double changeAmount){
		if(0 == changeAmount){
			return true;
		}
//		AlreadyUseCreditTask task = new AlreadyUseCreditTask(customerId, changeAmount);
//		context.handle(task, AlreadyUseCreditTask.Method.ADD);
		try {
			return AlreadyUseCreditUtil.add(context, customerId, changeAmount);
		} catch (AlredyUseCreditException e) {
			return false;
		}
//		return true;
	}
	/**
	 * 请直接使用AlreadyUseCreditUtil.sub(context, customerId, changeAmount);
	 * 减少客户已用信用额度（增加客户可用信用额度）
	 * @param customerId
	 * @param changeAmount
	 * @return boolean
	 */
	@Deprecated
	public static boolean subAlreadyUseCredit(Context context, GUID customerId, double changeAmount){
		if(0 == changeAmount){
			return true;
		}
//		AlreadyUseCreditTask task = new AlreadyUseCreditTask(customerId, changeAmount);
//		context.handle(task, AlreadyUseCreditTask.Method.SUB);
		try {
			return AlreadyUseCreditUtil.sub(context, customerId, changeAmount);
		} catch (AlredyUseCreditException e) {
			return false;
		}
//		return true;
	}
	
	/**
	 * 获得当前单据出库及收款情况
	 * @param in
	 * @return StatusEnum
	 */
	public static RelationCheckOutSheet getOrderOutSheet(Context context, GUID relaOrderId){
		GetRelationCheckOutSheetKey key = new GetRelationCheckOutSheetKey();
		key.setRelationOrderId(relaOrderId);
		return context.find(RelationCheckOutSheet.class, key);
	}
	
	/**
	 * 获得当前单据入库及付款情况情况（入库数量，已付款金额等）
	 * @param in
	 * @return StatusEnum
	 */
	public static RelationCheckInSheet getOrderInSheet(Context context, GUID relaOrderId){
		GetRelationCheckInSheetKey key = new GetRelationCheckInSheetKey();
		key.setRelationOrdId(relaOrderId);
		return context.find(RelationCheckInSheet.class, key);
	}
	//------------------------------仓库--------------------------
	/**
	 * 启用仓库【当前用户】
	 * @param context
	 * @return Store
	 */
	public static Store getStoreBySales(Context context){
		return getStoreBySales(context, BillsConstant.getUserGuid(context));
	}
	
	/**
	 * 取得当前租户下启用和盘点仓库集合
	 * @param context
	 * @return Store[]
	 */
	public static List<Store> getStore(Context context){
		GetAllStoreListKey key = new GetAllStoreListKey(StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		return context.getList(Store.class, key);
	}
	
	/**
	 * 启用仓库【指定用户】
	 * @param context
	 * @return Store
	 */
	public static Store getStoreBySales(Context context, GUID userId){
		GetStoreBySaleManKey key = new GetStoreBySaleManKey(BillsConstant.getUserGuid(context), StoreStatus.ENABLE);
		return context.find(Store.class, key);
	}

	/**
	 * 采购中数量，采购清单+待审核订单数量
	 * 
	 * @param goodsGuid
	 * @return double
	 */
	@Deprecated
	public static double getGoodsBuyingCount(Context context, GUID goodsGuid,
			GUID storeGuid) {
//		PurchaseingCountTask task = new PurchaseingCountTask();
//		task.setGoodsGuid(goodsGuid);
//		task.setStoreGuid(storeGuid);
//		context.handle(task);
//		return task.getBuyingCount();
		return getPurchaseingCount(context, storeGuid, goodsGuid);
	}
	
	/**
	 * 获得库存信息
	 * @param context
	 * @param storeId
	 * @param goodsItemId
	 * @return GoodsInventory
	 */
	public static Inventory getInventory(Context context, GUID storeId, GUID goodsItemId){
		GetGoodsStoreInventoryKey key = new GetGoodsStoreInventoryKey(goodsItemId, storeId);
		return context.find(Inventory.class, key);
	}

	/**
	 * 采购中数量采购部分
	 * @param context
	 * @param storeId
	 * @param goodsItemId
	 * @return Double
	 */
	public static Double getPurchaseingCount(Context context, GUID storeId, GUID goodsItemId){
		GetPurchaseOrderGoodsCountByGoodsIdKey key = new GetPurchaseOrderGoodsCountByGoodsIdKey(goodsItemId, storeId);
		return context.find(Double.class, key);
	}
	
	/**
	 * 查询商品当前总库数量和金额
	 * @param context
	 * @param goodsItemId
	 * @return GoodsInventorySum
	 */
	public static GoodsInventorySum getGoodsInventorySum(Context context, GUID goodsItemId){
		GoodsInventorySumKey key = new GoodsInventorySumKey();
		key.setGoodsItemId(goodsItemId);
		return context.find(GoodsInventorySum.class, key); 
	}
	
	// ---------------------------数据库数据或显示数据处理---------------------

//	/**
//	 * 将原商品属性字符串中的[]翻译成(属性1;属性2)的样子
//	 * 
//	 * @param goodaAttr
//	 * @return String
//	 */
//	public static String getGoodsAttrToWarning(String goodsAttr) {
//		return BillsConstant.getGoodsAttrToWarning(goodsAttr);
//	}

	/**
	 * 获得库存上下限拼接字符串
	 * 
	 * @param upper
	 * @param floor
	 * @return String
	 */
	public static String getStoreUpperFloor(Double upper, Double floor,
			int goodsCountDigit) {
		String up = OrderUtil.getDoubleStr(upper, goodsCountDigit), fl = OrderUtil
				.getDoubleStr(floor, goodsCountDigit);
		if (null == upper || -1 == upper) {
			up = "——";
		}
		if (null == floor || -1 == floor) {
			fl = "——";
		}
		return up + "/" + fl;
	}

	// /**
	// * 翻译数据库数据
	// * @param d
	// * @return Double
	// */
	// public static Double getDoubleNull(double d){
	// if(-1 == d){
	// return null;
	// }
	// return d;
	// }
	//	
	// /**
	// * 翻译数据库数据
	// * @param d
	// * @return Double
	// */
	// public static String getDoubleStr(double d){
	// if(-1 == d){
	// return "-";
	// }
	// return DoubleUtil.getRoundStr(d);
	// }

	/**
	 * 订单专用，double转移成千位符字符串(2位小数)
	 * 
	 * @param d
	 * @return String
	 */
	public static String getDoubleStr_(Double d) {
		if (null == d || -1 == d) {
			return "——";
		}
		return getDoubleStr(d, 2);
	}

	/**
	 * 订单专用，double转移成千位符字符串(2位小数)
	 * 
	 * @param d
	 * @return String
	 */
	public static String getDoubleStr(Double d) {
		if (null == d || -1 == d) {
			return "";
		}
		return getDoubleStr(d, 2);
	}
	
	/**
	 * 订单专用，double转移成千位符字符串(2位小数)
	 * 
	 * @param d
	 * @return String
	 */
	public static String getDoubleStr(double d) {
		if (-1 == d) {
			return "";
		}
		return getDoubleStr(d, 2);
	}

	/**
	 * 订单专用，double转移成千位符字符串(2位小数)
	 * 
	 * @param d
	 * @return String
	 */
	public static String getDoubleStr_0(Double d) {
		if (null == d || -1 == d) {
			return "0.00";
		}
		return getDoubleStr(d, 2);
	}

	/**
	 * 订单专用，double转移成千位符字符串
	 * 
	 * @param d
	 * @param i
	 * @return String
	 */
	public static String getDoubleStr(Double d, int i) {
		if (null == d || -1 == d) {
			return "";
		}
		return DoubleUtil.getRoundStr(d, i);
	}
	
	/**
	 * 订单专用，double转移成千位符字符串
	 * 
	 * @param d
	 * @param i
	 * @return String
	 */
	public static String getDoubleStr(double d, int i) {
		if (-1 == d) {
			return "";
		}
		return DoubleUtil.getRoundStr(d, i);
	}
	
	/**
	 * 订单专用，千位符字符串转移成double
	 * 
	 * @param d
	 * @param i
	 * @return String
	 */
	public static double getDouble(String str) {
		Double d = DoubleUtil.strToDouble(str);
		return d == null ? 0 : d;
	}

	/**
	 * 如果str==null返回“”
	 * 
	 * @param str
	 * @return String
	 */
	public static String getStr(String str) {
		return str == null ? "" : str;
	}
	
	/**
	 * 获得非千分符数值字符串
	 * @param str
	 * @param scales
	 * @return String
	 */
	public static String getDoubleStrWithOutQfw(String str, int... scales){
		return getDoubleStrWithOutQfw(DoubleUtil.strToDouble(str), scales == null || scales.length == 0 ?2:scales[0]); 
	}
	
	/**
	 * 获得非千分符数值字符串
	 * @param d
	 * @param scales
	 * @return String
	 */
	public static String getDoubleStrWithOutQfw(Double d, int... scales){
		return DoubleUtil.getRoundStrWithOutQfw(d, scales == null || scales.length == 0 ?2:scales[0]);
	}

	// /**
	// * 指定小数位 void
	// */
	// public static String getDoubleStr(double d, int i) {
	// return DoubleUtil.getRoundStr(d, i);
	// }
	// --------------------数据拼接与解析----------------
	private static final StringSearchUtil stringSearchUtil = new StringSearchUtil();

	/**
	 * 搜索文本翻译成类型集合
	 * 
	 * @param search
	 * @return String
	 */
	@Deprecated
	public static List<String> getBillsType(BillsEnum billsEnum, String search) {
		stringSearchUtil.clear();
		stringSearchUtil.put("01", "普通订单");
		stringSearchUtil.put("02", "网上订单");
		stringSearchUtil.put("03", "普通订单(直供)");
		stringSearchUtil.put("04", "网上订单(直供)");
//		stringSearchUtil.put("05", "采购退货销售退货");
		if(BillsEnum.PURCHASE == billsEnum){
			stringSearchUtil.put("05", "采购退货");
		}
		else{
			stringSearchUtil.put("05", "销售退货");
		}
		// PLAIN("01", "普通订单"),
		// ON_LINE("02", "网上订单"),
		// PLAIN_DIRECT("03","普通订单(直供)"),
		// ON_LINE_DIRECT("04", "网上订单(直供)"),
		// CANCEL("05","采购退货", "销售退货"),
		return stringSearchUtil.getKeys(search);
	}
	
	/**
	 * 搜索文本翻译成类型集合
	 * 
	 * @param search
	 * @return String
	 */
	public static List<String> getBillsType(OrderEnum billsEnum, String search) {
		stringSearchUtil.clear();
		stringSearchUtil.put("01", "普通订单");
		stringSearchUtil.put("02", "网上订单");
		stringSearchUtil.put("03", "普通订单(直供)");
		stringSearchUtil.put("04", "网上订单(直供)");
//		stringSearchUtil.put("05", "采购退货销售退货");
		if(OrderEnum.Purchase == billsEnum){
			stringSearchUtil.put("05", "采购退货");
		}
		else{
			stringSearchUtil.put("05", "销售退货");
		}
		// PLAIN("01", "普通订单"),
		// ON_LINE("02", "网上订单"),
		// PLAIN_DIRECT("03","普通订单(直供)"),
		// ON_LINE_DIRECT("04", "网上订单(直供)"),
		// CANCEL("05","采购退货", "销售退货"),
		return stringSearchUtil.getKeys(search);
	}
	
	/**
	 * 搜索文本转换成单据处理状态
	 * @param billsEnum
	 * @param search
	 * @return List<SearchStatusEntity>
	 */
	public static List<SearchStatusEntity> getBillsstatus(BillsEnum billsEnum, String search){
		if(null == search || "".equals(search)){
			return new ArrayList<SearchStatusEntity>();
		}
		StatusSearchUtil util = new StatusSearchUtil();
		return util.getListByBus(billsEnum, search);
	}

	// ---------------------电话号码相关--------------------
	/**
	 * 数据库存储电话号码翻译
	 * 
	 * @param tel
	 * @return PhoneAndTypeUtil
	 */
	public static PhoneAndTypeUtil getTel(String tel) {
		return PhoneAndTypeUtil.split(tel);
	}

	/**
	 * 按出入first优先级自动筛选号码
	 * 
	 * @param mobile
	 *            手机
	 * @param tel
	 *            固话
	 * @param first
	 *            优先类型
	 * @return String
	 */
	public static PhoneAndTypeUtil getTel(String mobile, String tel,
			PhoneType first) {
		return PhoneAndTypeUtil.selectTel(mobile, tel, first);
	}

	/**
	 * 字符串拼接(界面显示用放用) 例如：欧阳久其(2010-09-19)
	 * 
	 * @param str
	 * @param l
	 * @return String
	 */
	public static String spell(String str, long l) {
		String s = str == null ? "" : str;
		return s + "(" + OrderUtil.getDate(l) + ")";
	}

	// ----------------------------------流程控制-----------------------

	/**
	 * 获取单据状态
	 * 
	 * @param key
	 * @return FSysFunction
	 */
	public static StatusEnum getStatusEnum(String key) {
		return StatusEnum.getstatus(key);
	}

	/**
	 * 更新流程到指定状态
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @return boolean true表示更新成功
	 */
	public static boolean modifystatus(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, GUID... examdept) {
		//TODO 退货状态更新
		FlowTask task = new FlowTask(billsEnum);
		task.billsRECID = RECID;
		task.newstatus = newstatus.getKey();
		task.oldstatus = oldstatus.getKey();
		if (BillsEnum.SALE == billsEnum && examdept.length > 0) {
			task.setExamDept(examdept[0]);
		}
		context.handle(task);
		return task.isSucceed();
	}

	/**
	 * 更新流程到指定状态(同时修改部门)
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @return boolean true表示更新成功
	 */
	public static boolean modifystatusDept(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, GUID deptGuid,
			GUID... examdept) {
		//TODO 退货状态更新
		FlowTask task = new FlowTask(billsEnum);
		task.billsRECID = RECID;
		task.newstatus = newstatus.getKey();
		task.oldstatus = oldstatus.getKey();
		task.setDeptGuid(deptGuid);
		if (BillsEnum.SALE == billsEnum && examdept.length > 0) {
			task.setExamDept(examdept[0]);
		}
		context.handle(task);
		return task.isSucceed();
	}

	/**
	 * 订单更新为已退回状态
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @param cause
	 * @return boolean true表示更新成功
	 */
	public static boolean modifyRebut(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, String cause) {
		//TODO 退货状态更新
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = RECID;
		task.newstatus = newstatus.getKey();
		task.oldstatus = oldstatus.getKey();
		task.cause = cause;
		context.handle(task);
		return task.isSucceed();
	}
	
	/**
	 * 订单更新为已退回状态
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @param cause
	 * @return boolean true表示更新成功
	 */
	public static boolean modifyRebut(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, String cause, OrderInfo info) {
		//TODO 退货状态更新
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = RECID;
		task.newstatus = newstatus.getKey();
		task.oldstatus = oldstatus.getKey();
		task.cause = cause;
		task.info = info;
		context.handle(task);
		return task.isSucceed();
	}

	/**
	 * 订单更新为已中止状态
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @param cause
	 * @return boolean true表示更新成功
	 */
	public static boolean modifyStop(BillsEnum billsEnum, Context context,
			GUID RECID, boolean isStoped, StatusEnum oldstatus, String cause) {
//		if(BillsEnum.SALE_CANCEL == billsEnum){
////			return modifyCancelstatus(context, info, nextstatus, cause, isStoped, reRun)
//		}
		//TODO 退货状态更新
		StopTask task = new StopTask(billsEnum);
		task.billsRECID = RECID;
		task.oldstatus = oldstatus.getKey();
		task.isStoped = isStoped;
		task.cause = cause;
		context.handle(task);
		return task.isSucceed();
	}

//	/**
//	 * 更新采购退货单状态
//	 * 
//	 * @param context
//	 * @param recid
//	 * @param nextstatus
//	 * @param cause
//	 * @param isStoped
//	 * @param status
//	 * @return boolean
//	 */
//	private static boolean modifyCancelstatus(Context context, OrderInfo info,
//			StatusEnum nextstatus, String cause, boolean isStoped, boolean reRun) {
//		PurchaseCancel cancel = new PurchaseCancel();
//		cancel.setTenantsGuid(info.getTenantsGuid());
//		cancel.setRecid(info.getRecid());
//		cancel.setOldstatus(info.getStatus());
//		cancel.setNewstatus(nextstatus.getKey());
//		cancel.setStoped(isStoped);
//		cancel.setReRun(reRun);
//		if (isStoped) {
//			cancel.setStopCause(cause);
//		}
//		if (!info.getStatus().equals(nextstatus)) {
//			if (StatusEnum.REBUT == nextstatus) {
//				cancel.setRebutCause(cause);
//			}
//		}
//
//		ModifyCancelStatusTask task = new ModifyCancelStatusTask();
//		task.setEntity(cancel);
//		context.handle(task, TaskEnum.UPDATE);
//		return task.isDone();
//	}
//
//	/**
//	 * 更新销售退货单状态
//	 * 
//	 * @param context
//	 * @param recid
//	 * @param nextstatus
//	 * @param cause
//	 * @param isStoped
//	 * @param status
//	 * @return boolean
//	 */
//	private static boolean modifySaleCancelstatus(Context context,
//			OrderInfo info, StatusEnum nextstatus, String cause, boolean isStoped,
//			boolean reRun) {
//		SaleCancel cancel = new SaleCancel();
//		cancel.setTenantsGuid(info.getTenantsGuid());
//		cancel.setRecid(info.getRecid());
//		cancel.setOldstatus(info.getStatus());
//		cancel.setNewstatus(nextstatus.getKey());
//		cancel.setStoped(isStoped);
//		cancel.setReRun(reRun);
//		if (isStoped) {
//			cancel.setStopCause(cause);
//		}
//		if (!info.getStatus().equals(nextstatus)) {
//			if (StatusEnum.REBUT == nextstatus) {
//				cancel.setRebutCause(cause);
//			}
//		}
//
//		ModifySaleCancelStatusTask task = new ModifySaleCancelStatusTask();
//		task.setEntity(cancel);
//		context.handle(task, TaskEnum.UPDATE);
//		return task.isDone();
//	}

	// ---------------------------------销售订单流程区域-----------------------------
	/**
	 * 更新审核部门（销售订单审核专用）
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @return boolean true表示更新成功
	 */
	public static boolean modifyExamDept(Context context, GUID RECID,
			GUID newExamDept, GUID oldExamDept, String examin) {
		SaleExamDeptTask task = new SaleExamDeptTask();
		task.recid = RECID;
		task.oldExamDetp = oldExamDept;
		task.examDeptGuid = newExamDept;
		task.examinStr = examin;
		context.handle(task);
		return task.isSucceed();
	}
}
