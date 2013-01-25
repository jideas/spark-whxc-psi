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
 * ������̬������
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-10-27
 */
public final class OrderUtil {
	private static SimpleDateFormat sf = BillsConstant.DATA_yyyy_MM_dd;// new

	// SimpleDateFormat("yyyy-MM-dd");

	private OrderUtil() {

	}
	
	/**
	 * ҵ��ع�
	 * @param context void
	 */
	public static void rollback(Context context){
		try {
			context.abort();
		} catch (AbortException e) {
		}
	}
	
	/**
	 * ��ȡyyyy-MM-dd��ʽ�����ַ���
	 * 
	 * @param l
	 * @return String
	 */
	public static String getDate(long l) {
		return sf.format(new Date(l));
	}
	
	/**
	 * ��õ��ݱ��
	 * @param context
	 * @param billsEnum
	 * @return String
	 */
	public static String getBillsNumber(Context context, BillsEnum billsEnum){
		return context.get(String.class, billsEnum.getSheetType());
//		String str = System.currentTimeMillis()+"";
//		return str.substring(str.length() - 12, str.length());
	}
	
	//=========================�������ݣ����ţ�========================
	public static Department getDepartment(Context context){
		return context.find(Department.class, BillsConstant.getUser(context).getDepartmentId());
	}
	/**
	 * ��õ�ǰ�˱����ֺ������ﲿ�ּ���(������ǰ����)
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
	 * ������۾������ֺ�ֱ���¼�����(������ǰ����)
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
	 * ���ָ�����ŵ�ֱ���Ӳ��ż���(������ǰ����)
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
	 * ���ָ�����ŵ����ﲿ�ּ���(������ǰ����)
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

	//==========================�ͻ��������ö��===========================
	/**
	 * AlreadyUseCreditUtil.add(context, customerId, changeAmount);
	 * ���ӿͻ��������ö�ȣ����ٿͻ��������ö�ȣ�
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
	 * ��ֱ��ʹ��AlreadyUseCreditUtil.sub(context, customerId, changeAmount);
	 * ���ٿͻ��������ö�ȣ����ӿͻ��������ö�ȣ�
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
	 * ��õ�ǰ���ݳ��⼰�տ����
	 * @param in
	 * @return StatusEnum
	 */
	public static RelationCheckOutSheet getOrderOutSheet(Context context, GUID relaOrderId){
		GetRelationCheckOutSheetKey key = new GetRelationCheckOutSheetKey();
		key.setRelationOrderId(relaOrderId);
		return context.find(RelationCheckOutSheet.class, key);
	}
	
	/**
	 * ��õ�ǰ������⼰����������������������Ѹ�����ȣ�
	 * @param in
	 * @return StatusEnum
	 */
	public static RelationCheckInSheet getOrderInSheet(Context context, GUID relaOrderId){
		GetRelationCheckInSheetKey key = new GetRelationCheckInSheetKey();
		key.setRelationOrdId(relaOrderId);
		return context.find(RelationCheckInSheet.class, key);
	}
	//------------------------------�ֿ�--------------------------
	/**
	 * ���òֿ⡾��ǰ�û���
	 * @param context
	 * @return Store
	 */
	public static Store getStoreBySales(Context context){
		return getStoreBySales(context, BillsConstant.getUserGuid(context));
	}
	
	/**
	 * ȡ�õ�ǰ�⻧�����ú��̵�ֿ⼯��
	 * @param context
	 * @return Store[]
	 */
	public static List<Store> getStore(Context context){
		GetAllStoreListKey key = new GetAllStoreListKey(StoreStatus.ENABLE, StoreStatus.ONCOUNTING);
		return context.getList(Store.class, key);
	}
	
	/**
	 * ���òֿ⡾ָ���û���
	 * @param context
	 * @return Store
	 */
	public static Store getStoreBySales(Context context, GUID userId){
		GetStoreBySaleManKey key = new GetStoreBySaleManKey(BillsConstant.getUserGuid(context), StoreStatus.ENABLE);
		return context.find(Store.class, key);
	}

	/**
	 * �ɹ����������ɹ��嵥+����˶�������
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
	 * ��ÿ����Ϣ
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
	 * �ɹ��������ɹ�����
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
	 * ��ѯ��Ʒ��ǰ�ܿ������ͽ��
	 * @param context
	 * @param goodsItemId
	 * @return GoodsInventorySum
	 */
	public static GoodsInventorySum getGoodsInventorySum(Context context, GUID goodsItemId){
		GoodsInventorySumKey key = new GoodsInventorySumKey();
		key.setGoodsItemId(goodsItemId);
		return context.find(GoodsInventorySum.class, key); 
	}
	
	// ---------------------------���ݿ����ݻ���ʾ���ݴ���---------------------

//	/**
//	 * ��ԭ��Ʒ�����ַ����е�[]�����(����1;����2)������
//	 * 
//	 * @param goodaAttr
//	 * @return String
//	 */
//	public static String getGoodsAttrToWarning(String goodsAttr) {
//		return BillsConstant.getGoodsAttrToWarning(goodsAttr);
//	}

	/**
	 * ��ÿ��������ƴ���ַ���
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
			up = "����";
		}
		if (null == floor || -1 == floor) {
			fl = "����";
		}
		return up + "/" + fl;
	}

	// /**
	// * �������ݿ�����
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
	// * �������ݿ�����
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
	 * ����ר�ã�doubleת�Ƴ�ǧλ���ַ���(2λС��)
	 * 
	 * @param d
	 * @return String
	 */
	public static String getDoubleStr_(Double d) {
		if (null == d || -1 == d) {
			return "����";
		}
		return getDoubleStr(d, 2);
	}

	/**
	 * ����ר�ã�doubleת�Ƴ�ǧλ���ַ���(2λС��)
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
	 * ����ר�ã�doubleת�Ƴ�ǧλ���ַ���(2λС��)
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
	 * ����ר�ã�doubleת�Ƴ�ǧλ���ַ���(2λС��)
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
	 * ����ר�ã�doubleת�Ƴ�ǧλ���ַ���
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
	 * ����ר�ã�doubleת�Ƴ�ǧλ���ַ���
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
	 * ����ר�ã�ǧλ���ַ���ת�Ƴ�double
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
	 * ���str==null���ء���
	 * 
	 * @param str
	 * @return String
	 */
	public static String getStr(String str) {
		return str == null ? "" : str;
	}
	
	/**
	 * ��÷�ǧ�ַ���ֵ�ַ���
	 * @param str
	 * @param scales
	 * @return String
	 */
	public static String getDoubleStrWithOutQfw(String str, int... scales){
		return getDoubleStrWithOutQfw(DoubleUtil.strToDouble(str), scales == null || scales.length == 0 ?2:scales[0]); 
	}
	
	/**
	 * ��÷�ǧ�ַ���ֵ�ַ���
	 * @param d
	 * @param scales
	 * @return String
	 */
	public static String getDoubleStrWithOutQfw(Double d, int... scales){
		return DoubleUtil.getRoundStrWithOutQfw(d, scales == null || scales.length == 0 ?2:scales[0]);
	}

	// /**
	// * ָ��С��λ void
	// */
	// public static String getDoubleStr(double d, int i) {
	// return DoubleUtil.getRoundStr(d, i);
	// }
	// --------------------����ƴ�������----------------
	private static final StringSearchUtil stringSearchUtil = new StringSearchUtil();

	/**
	 * �����ı���������ͼ���
	 * 
	 * @param search
	 * @return String
	 */
	@Deprecated
	public static List<String> getBillsType(BillsEnum billsEnum, String search) {
		stringSearchUtil.clear();
		stringSearchUtil.put("01", "��ͨ����");
		stringSearchUtil.put("02", "���϶���");
		stringSearchUtil.put("03", "��ͨ����(ֱ��)");
		stringSearchUtil.put("04", "���϶���(ֱ��)");
//		stringSearchUtil.put("05", "�ɹ��˻������˻�");
		if(BillsEnum.PURCHASE == billsEnum){
			stringSearchUtil.put("05", "�ɹ��˻�");
		}
		else{
			stringSearchUtil.put("05", "�����˻�");
		}
		// PLAIN("01", "��ͨ����"),
		// ON_LINE("02", "���϶���"),
		// PLAIN_DIRECT("03","��ͨ����(ֱ��)"),
		// ON_LINE_DIRECT("04", "���϶���(ֱ��)"),
		// CANCEL("05","�ɹ��˻�", "�����˻�"),
		return stringSearchUtil.getKeys(search);
	}
	
	/**
	 * �����ı���������ͼ���
	 * 
	 * @param search
	 * @return String
	 */
	public static List<String> getBillsType(OrderEnum billsEnum, String search) {
		stringSearchUtil.clear();
		stringSearchUtil.put("01", "��ͨ����");
		stringSearchUtil.put("02", "���϶���");
		stringSearchUtil.put("03", "��ͨ����(ֱ��)");
		stringSearchUtil.put("04", "���϶���(ֱ��)");
//		stringSearchUtil.put("05", "�ɹ��˻������˻�");
		if(OrderEnum.Purchase == billsEnum){
			stringSearchUtil.put("05", "�ɹ��˻�");
		}
		else{
			stringSearchUtil.put("05", "�����˻�");
		}
		// PLAIN("01", "��ͨ����"),
		// ON_LINE("02", "���϶���"),
		// PLAIN_DIRECT("03","��ͨ����(ֱ��)"),
		// ON_LINE_DIRECT("04", "���϶���(ֱ��)"),
		// CANCEL("05","�ɹ��˻�", "�����˻�"),
		return stringSearchUtil.getKeys(search);
	}
	
	/**
	 * �����ı�ת���ɵ��ݴ���״̬
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

	// ---------------------�绰�������--------------------
	/**
	 * ���ݿ�洢�绰���뷭��
	 * 
	 * @param tel
	 * @return PhoneAndTypeUtil
	 */
	public static PhoneAndTypeUtil getTel(String tel) {
		return PhoneAndTypeUtil.split(tel);
	}

	/**
	 * ������first���ȼ��Զ�ɸѡ����
	 * 
	 * @param mobile
	 *            �ֻ�
	 * @param tel
	 *            �̻�
	 * @param first
	 *            ��������
	 * @return String
	 */
	public static PhoneAndTypeUtil getTel(String mobile, String tel,
			PhoneType first) {
		return PhoneAndTypeUtil.selectTel(mobile, tel, first);
	}

	/**
	 * �ַ���ƴ��(������ʾ�÷���) ���磺ŷ������(2010-09-19)
	 * 
	 * @param str
	 * @param l
	 * @return String
	 */
	public static String spell(String str, long l) {
		String s = str == null ? "" : str;
		return s + "(" + OrderUtil.getDate(l) + ")";
	}

	// ----------------------------------���̿���-----------------------

	/**
	 * ��ȡ����״̬
	 * 
	 * @param key
	 * @return FSysFunction
	 */
	public static StatusEnum getStatusEnum(String key) {
		return StatusEnum.getstatus(key);
	}

	/**
	 * �������̵�ָ��״̬
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @return boolean true��ʾ���³ɹ�
	 */
	public static boolean modifystatus(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, GUID... examdept) {
		//TODO �˻�״̬����
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
	 * �������̵�ָ��״̬(ͬʱ�޸Ĳ���)
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @return boolean true��ʾ���³ɹ�
	 */
	public static boolean modifystatusDept(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, GUID deptGuid,
			GUID... examdept) {
		//TODO �˻�״̬����
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
	 * ��������Ϊ���˻�״̬
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @param cause
	 * @return boolean true��ʾ���³ɹ�
	 */
	public static boolean modifyRebut(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, String cause) {
		//TODO �˻�״̬����
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = RECID;
		task.newstatus = newstatus.getKey();
		task.oldstatus = oldstatus.getKey();
		task.cause = cause;
		context.handle(task);
		return task.isSucceed();
	}
	
	/**
	 * ��������Ϊ���˻�״̬
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @param cause
	 * @return boolean true��ʾ���³ɹ�
	 */
	public static boolean modifyRebut(BillsEnum billsEnum, Context context,
			GUID RECID, StatusEnum newstatus, StatusEnum oldstatus, String cause, OrderInfo info) {
		//TODO �˻�״̬����
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
	 * ��������Ϊ����ֹ״̬
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @param cause
	 * @return boolean true��ʾ���³ɹ�
	 */
	public static boolean modifyStop(BillsEnum billsEnum, Context context,
			GUID RECID, boolean isStoped, StatusEnum oldstatus, String cause) {
//		if(BillsEnum.SALE_CANCEL == billsEnum){
////			return modifyCancelstatus(context, info, nextstatus, cause, isStoped, reRun)
//		}
		//TODO �˻�״̬����
		StopTask task = new StopTask(billsEnum);
		task.billsRECID = RECID;
		task.oldstatus = oldstatus.getKey();
		task.isStoped = isStoped;
		task.cause = cause;
		context.handle(task);
		return task.isSucceed();
	}

//	/**
//	 * ���²ɹ��˻���״̬
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
//	 * ���������˻���״̬
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

	// ---------------------------------���۶�����������-----------------------------
	/**
	 * ������˲��ţ����۶������ר�ã�
	 * 
	 * @param context
	 * @param RECID
	 * @param newstatus
	 * @param oldstatus
	 * @return boolean true��ʾ���³ɹ�
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
