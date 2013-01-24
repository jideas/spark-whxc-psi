/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.allocate.service.common
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-24       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.util.allocateinventory;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.inventoryenum.AllocateTab;
import com.spark.psi.inventory.intf.key.allocateinventory.AllocateKey;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey;

/**
 * @author zhongxin
 * 
 */
public class SqlBuildHelper {
	private StringBuffer scriptBuffer = new StringBuffer();
	private StringBuffer paramterStr = new StringBuffer();// ���SQL�еĲ���
	private StringBuffer queryBody = new StringBuffer();
	private List<Object> paramterList = new ArrayList<Object>();// ����ֵ

	public static final StoreStatus queryStoreStatus[] = { StoreStatus.ENABLE,
			StoreStatus.ONCOUNTING };

	public SqlBuildHelper(GUID tenantsGuid) {
		paramterStr.append("@tenantsGuid  guid,");
		paramterList.add(tenantsGuid);
	}

	/**
	 * ȡ��DNA SQL
	 * 
	 * @return
	 */
	public String getQuerySql() {
		return this.scriptBuffer.toString();
	}

	/**
	 * ȡ��SQL�еĲ���ֵ
	 * 
	 * @return
	 */
	public List<Object> getParameterList() {
		return paramterList;
	}

	/**
	 * ȡ�ò�ѯ���
	 * 
	 * @return
	 */
	public String getQueryBody() {
		return this.queryBody.toString();
	}

	/**
	 * ȡ��SQL����������
	 * 
	 * @return
	 */
	public String getParamterStr() {
		return this.paramterStr.toString();
	}

	/**
	 * ���ɲ�ѯ������Ϊָ��Ա��������״̬Ϊָ��״̬��SQL
	 * 
	 * @param employeeGuid
	 * @param inventoryAllocatestatuss
	 */
	public void buildQueryAllocateInfoByCreatePersonSQL(GUID employeeGuid,
			InventoryAllocateStatus[] inventoryAllocatestatuss,
			String sortColumn, String sortType, String searchString,
			AllocateKey key) {
		paramterStr.append("@employeeGuid guid,");
		paramterList.add(employeeGuid);

		queryBody.append("	select \n");
		queryBody.append(getAllocateInfoColumns());
		queryBody.append("	from PSI_Inventory_Allocate as allocateInfo \n");

		queryBody
				.append(" where allocateInfo.creatorId = @employeeGuid");
		queryBody.append(" and "
				+ getQuerystatusCondition(inventoryAllocatestatuss) + "\n");
		if (key.getBeginTime() > 0 && key.getEndTime() > 0) {
			paramterStr.append("@beginTime date,");
			paramterList.add(key.getBeginTime());
			paramterStr.append("@endTime date,");
			paramterList.add(key.getEndTime());
			queryBody
					.append(" and allocateInfo.applyDate >= @beginTime and allocateInfo.applyDate <= @endTime");
		}
		if (null != searchString && !"".equals(searchString)) {
			queryBody.append(" and " + buildSearchConditionStr(searchString)
					+ "\n");
		}
		if (CheckIsNull.isEmpty(sortColumn)) {
			queryBody
					.append(" order by allocateInfo.createDate asc,allocateInfo.allocSheetNo asc\n");
		} else {
			queryBody.append(" order by allocateInfo." + sortColumn + " "
					+ sortType);
		}
		scriptBuffer.append("define query Qury_StoreStorage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0,
					paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(") \n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(queryBody);
		scriptBuffer.append(" end");
	}

	/**
	 * ��ѯָ��״̬�����е�������
	 * 
	 * @param querystatus
	 */
	public void buildQueryAllocateInfoBystatusSql(
			InventoryAllocateStatus[] querystatus, String sortColumn,
			String sortType, String searchString, AllocateKey key) {
		queryBody.append("	select \n");
		queryBody.append(getAllocateInfoColumns());
		queryBody.append("	from SA_STORE_ALLOCATE as allocateInfo \n");

		queryBody.append(" where allocateInfo.tenantsGuid = @tenantsGuid");
		queryBody.append(" and " + getQuerystatusCondition(querystatus) + "\n");
		if (null != searchString && !"".equals(searchString)) {
			queryBody.append(" and " + buildSearchConditionStr(searchString)
					+ "\n");
		}
		if (key.getBeginTime() > 0 && key.getEndTime() > 0) {
			paramterStr.append("@beginTime date,");
			paramterList.add(key.getBeginTime());
			paramterStr.append("@endTime date,");
			paramterList.add(key.getEndTime());
			queryBody
					.append(" and allocateInfo.applyDate >= @beginTime and allocateInfo.applyDate <= @endTime");
		}
		if (CheckIsNull.isEmpty(sortColumn)) {
			queryBody
					.append(" order by allocateInfo.createDate desc,allocateInfo.allocOrdNo desc\n");
		} else {
			queryBody.append(" order by allocateInfo." + sortColumn + " "
					+ sortType);
		}
		scriptBuffer.append("define query Qury_StoreStorage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0,
					paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(") \n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(queryBody);
		scriptBuffer.append(" end");
	}

	/**
	 * ��ѯ�������������ָ���ֿ��б��� �ҵ���״̬Ϊָ��״̬�ĵ�������
	 * 
	 * @param list
	 */
	public void buildQueryAllocateInfoByStore(List<StoreItem> list,
			InventoryAllocateStatus[] querystatus, boolean isShowExamine,
			String sortColumn, String sortType, String searchString,
			AllocateKey key) {
		StringBuffer conditionStr = new StringBuffer();
		conditionStr.append("(1=2 ");
		for (int storeIndex = 0; storeIndex < list.size(); storeIndex++) {
			String parameter = "@storeGuid" + storeIndex;
			paramterStr.append(parameter + " guid,");
			paramterList.add(list.get(storeIndex).getId());
//			if (isShowExamine) {// ��ʾ�����������������ľͲ�������ʾ
//				conditionStr.append(" or (allocateInfo.allocateOutStoreGuid="
//						+ parameter
//						+ " and allocateInfo.isOutExamine = @examineFalse)");
//				conditionStr.append(" or (allocateInfo.allocateInStoreGuid="
//						+ parameter
//						+ " and allocateInfo.isInExamine = @examineFalse)");
//			} else {
			conditionStr.append(" or allocateInfo.allocateOutStoreId="
					+ parameter);
			conditionStr.append(" or allocateInfo.allocateInStoreId="
					+ parameter);
//			}
		}
		conditionStr.append(")");

		queryBody.append("	select \n");
		queryBody.append(getAllocateInfoColumns());
		queryBody.append("	from PSI_Inventory_Allocate as allocateInfo \n");

		queryBody.append(" where 1=1 ");
		queryBody.append(" and " + getQuerystatusCondition(querystatus) + "\n");
		queryBody.append(" and " + conditionStr + "\n");
		if (key.getBeginTime() > 0 && key.getEndTime() > 0) {
			paramterStr.append("@beginTime date,");
			paramterList.add(key.getBeginTime());
			paramterStr.append("@endTime date,");
			paramterList.add(key.getEndTime());
			queryBody
					.append(" and allocateInfo.allocateInDate >= @beginTime and allocateInfo.allocateInDate <= @endTime");
		}
		if (null != searchString && !"".equals(searchString)) {
			queryBody.append(" and " + buildSearchConditionStr(searchString)
					+ "\n");
		}
		if (CheckIsNull.isEmpty(sortColumn)) {
			if (GetInventoryAllocateSheetListKey.Processedstatus.equals(key
					.getdealState())) { // ��ʷ��Ĭ�ϵ�һ���򣺵������ڵ��򣬵ڶ����򣺵������ŵ���
				queryBody
						.append(" order by allocateInfo.allocateInDate desc, allocateInfo.allocSheetNo desc \n");
			} else { // ���� Ĭ�ϵ�һ���������������򣬵ڶ����򣺵�����������
				queryBody
						.append(" order by allocateInfo.applyDate, allocateInfo.allocSheetNo \n");
			}
		} else {
			queryBody.append(" order by allocateInfo." + sortColumn + " "
					+ sortType);
		}
		scriptBuffer.append("define query Qury_StoreStorage(");
		if (paramterStr.length() > 0) {
			scriptBuffer.append(paramterStr.toString().substring(0,
					paramterStr.toString().length() - 1));
		}
		scriptBuffer.append(") \n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(queryBody);
		scriptBuffer.append(" end");
	}

	private String buildSearchConditionStr(final String searchString) {
		// �������š�����״̬������ֿ⡢�����ֿ�
		if (null == searchString || "".equals(searchString.trim()))
			return null;
		String searchWithTrim = searchString.trim();
		StringBuffer sql = new StringBuffer();
		sql.append("(allocateInfo.allocSheetNo like '%");
		sql.append(searchWithTrim);
		sql.append("%' ");
		sql.append("or allocateInfo.creator like '%");
		sql.append(searchWithTrim);
		sql.append("%' ");
		sql.append("or allocateInfo.creatorPY like '%");
		sql.append(searchWithTrim);
		sql.append("%' ");
		sql.append("or allocateInfo.allocateInStoreName like '%");
		sql.append(searchWithTrim);
		sql.append("%' ");
		sql.append("or allocateInfo.allocateOutStoreName like '%");
		sql.append(searchWithTrim);
		sql.append("%' ");

		List<String> statuss = getStatus(searchWithTrim);
		if (CheckIsNull.isNotEmpty(statuss)) {
			for (String status : statuss) {
				sql.append(" or allocateInfo.dealState='").append(status)
						.append("'");

			}
		}
		sql.append(") ");

		return sql.toString();
	}

	/**
	 * ģ��ƥ�䵥��״̬
	 * 
	 * @param searchWithTrim
	 * @return List<String>
	 */
	private List<String> getStatus(String searchWithTrim) {
		List<String> list = new ArrayList<String>();
		if (InventoryAllocateStatus.Submitting.getName().indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Submitting.getAllocateInstatusName()
						.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Submitting.getAllocateOutstatusName()
						.indexOf(searchWithTrim) != -1) {
			list.add(InventoryAllocateStatus.Submitting.getCode());
		}
		if (InventoryAllocateStatus.Submitted.getName().indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Submitted.getAllocateInstatusName()
						.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Submitted.getAllocateOutstatusName()
						.indexOf(searchWithTrim) != -1) {
			list.add(InventoryAllocateStatus.Submitted.getCode());
		}
		if (InventoryAllocateStatus.AllocateIn.getName().indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.AllocateIn.getAllocateInstatusName()
						.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.AllocateIn.getAllocateOutstatusName()
						.indexOf(searchWithTrim) != -1) {
			list.add(InventoryAllocateStatus.AllocateIn.getCode());
		}
		if (InventoryAllocateStatus.AllocateOut.getName()
				.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.AllocateOut.getAllocateInstatusName()
						.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.AllocateOut.getAllocateOutstatusName()
						.indexOf(searchWithTrim) != -1) {
			list.add(InventoryAllocateStatus.AllocateOut.getCode());
		}
		if (InventoryAllocateStatus.Rejected.getName().indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Rejected.getAllocateInstatusName()
						.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Rejected.getAllocateOutstatusName()
						.indexOf(searchWithTrim) != -1) {
			list.add(InventoryAllocateStatus.Rejected.getCode());
		}
		if (InventoryAllocateStatus.Allocating.getName().indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Allocating.getAllocateInstatusName()
						.indexOf(searchWithTrim) != -1
				|| InventoryAllocateStatus.Allocating.getAllocateOutstatusName()
						.indexOf(searchWithTrim) != -1) {
			list.add(InventoryAllocateStatus.Allocating.getCode());
		}
		return list;
	}

	/**
	 * ������ѯָ������������ϸ
	 * 
	 * @param allocateOrdGuid
	 */
	public void buildQueryAllocateDetailSQL(GUID allocateOrdGuid) {
		paramterStr.append("@allocateOrdGuid guid");
		paramterList.add(allocateOrdGuid);

		queryBody.append("	select \n");
		queryBody.append(getAllocateDetailColumns());
		queryBody.append("	from PSI_Inventory_Allocate_Det as detail \n");
		queryBody
				.append("where detail.allocateId = @allocateOrdGuid\n");

		scriptBuffer.append("define query Qury_allocateDetail(");
		scriptBuffer.append(paramterStr);
		// if (paramterStr.length() > 0) {
		// scriptBuffer.append(paramterStr.toString().substring(0,
		// paramterStr.toString().length() - 1));
		// }
		scriptBuffer.append(") \n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(queryBody);
		scriptBuffer.append(" end");
	}

	/**
	 * ��ѯָ����Ʒ�ڵ���״̬Ϊ�������⡱�е�������
	 * 
	 */
	public void buildQueryToAllocateOutCountSQL(GUID outStoreGuid,
			GUID goodsGuid) {
		paramterStr.append("@outStoreGuid guid, @goodsGuid guid");
		paramterList.add(outStoreGuid);
		paramterList.add(goodsGuid);

		queryBody
				.append("SELECT SUM(detail.allocateCount) as \"allocateCount\"  \n");
		queryBody.append("FROM sa_store_allocate_det as \"detail\" \n");
		queryBody
				.append("JOIN sa_store_allocate as \"allocate\" ON detail.allocateOrdGuid=allocate.recid \n");
		queryBody
				.append("WHERE allocate.tenantsGuid=@tenantsGuid AND allocate.dealState='"
						+ InventoryAllocateStatus.Allocating.getCode()
						+ "' "
						+ "AND detail.goodsGuid=@goodsGuid and allocate.allocateOutStoreGuid <> @outStoreGuid \n");

		scriptBuffer.append("define query Qury_allocateDetail(");
		scriptBuffer.append(paramterStr);
		scriptBuffer.append(") \n");
		scriptBuffer.append(" begin \n");
		scriptBuffer.append(queryBody);
		scriptBuffer.append(" end");
	}

	// /**
	// * ������ѯ����Ϊ����Ĳ�ѯ���
	// * @return
	// */
	// private String managerQueryBody() {
	//		
	// }
	// /**
	// * ������ѯ����Ϊ�ܾ���Ĳ�ѯ���
	// * @return
	// */
	// private String bossQueryBody() {
	//		
	// }
	// /**
	// * ָ��Ա�������Ĳֿ�
	// * @return
	// */
	// private String getEmployeeStoreStr() {
	// StringBuffer employeeStoreStr = new StringBuffer();
	// employeeStoreStr.append("select ");
	// // queryBody.append(getStoreEmployeeFullColumns());
	// employeeStoreStr.append("store.recid as storeGuid \n");
	// employeeStoreStr.append("from SA_STORE_EMPLOYEE as storeEmployee \n");
	// employeeStoreStr.append("join sa_em_employee as employee on storeEmployee.employeeGuid=employee.recid \n");
	// employeeStoreStr.append("join sa_store_info as store on storeEmployee.storeGuid=store.recid \n");
	// employeeStoreStr.append(" where storeEmployee.tenantsGuid = @tenantsGuid  and storeEmployee.employeeGuid = @employeeGuid and storeEmployee.employeeType='"
	// + StoreDetailItem.STOREMANAGER.getValue() + "'");
	// if(null != queryStoreStatus) {
	// List<StoreStatus> statussList = java.util.Arrays.asList(queryStoreStatus);
	// employeeStoreStr.append(" and ( 1=2");
	// for(StoreStatus status : statussList) {
	// employeeStoreStr.append(" or store.StoreStatus='" + status.getstatusValue()
	// + "'");
	// }
	// employeeStoreStr.append(")");
	// }
	// return employeeStoreStr.toString();
	// }
	// /**
	// * ָ�����Ź����Ĳֿ�
	// * @return
	// */
	// private String getDeptStoreStr(String deptParameterStr) {
	// StringBuffer deptStoreStr = new StringBuffer();
	// deptStoreStr.append("select ");
	// // queryBody.append(getStoreEmployeeFullColumns());
	// deptStoreStr.append("store.recid as storeGuid \n");
	// deptStoreStr.append("from SA_STORE_EMPLOYEE as storeEmployee \n");
	// deptStoreStr.append("join (" + getDeptEmployeeSql(deptParameterStr) +
	// ") as employee on storeEmployee.employeeGuid = employee.recid\n");
	// deptStoreStr.append("join sa_store_info as store on storeEmployee.storeGuid=store.recid \n");
	// deptStoreStr.append(" where storeEmployee.tenantsGuid = @tenantsGuid and storeEmployee.employeeType='"
	// + StoreDetailItem.STOREMANAGER.getValue() + "'");
	// if(null != queryStoreStatus) {
	// List<StoreStatus> statussList = java.util.Arrays.asList(queryStoreStatus);
	// queryBody.append(" and ( 1=2");
	// for(StoreStatus status : statussList) {
	// queryBody.append(" or store.StoreStatus='" + status.getstatusValue() + "'");
	// }
	// queryBody.append(")");
	// }
	// return deptStoreStr.toString();
	// }
	/**
	 * ��ѯ������״̬��sql����
	 * 
	 * @param inventoryAllocatestatuss
	 * @return
	 */
	private String getQuerystatusCondition(
			InventoryAllocateStatus[] inventoryAllocatestatuss) {
		StringBuffer conditionStr = new StringBuffer();
		if (null != inventoryAllocatestatuss) {
			conditionStr.append("( 1=2");
			for (InventoryAllocateStatus status : inventoryAllocatestatuss) {
				conditionStr.append(" or allocateInfo.\"status\"='"
						+ status.getCode() + "'");
			}
			conditionStr.append(")");
		}
		return conditionStr.toString();
	}

	// /**
	// * ���Ź���Ա��
	// * @param deptIdForParmater
	// * @return
	// */
	// private String getDeptEmployeeSql(String deptIdForParmater) {
	// StringBuffer columnsScript = new StringBuffer();
	// columnsScript.append("select employee.recid from sa_em_employee as employee \n");
	// columnsScript.append("join (select t.RECID as \"RECID\" from SA_CORE_TREE as s join SA_CORE_TREE as t on (t.PATH > s.PATH and t.PATH < s.PATH || bytes'ff') or t.RECID = "
	// + deptIdForParmater + " where s.RECID = " + deptIdForParmater +
	// "  ORDER BY s.PATH) as allDepartments \n");
	// columnsScript.append("on employee.departmentId = allDepartments.recid \n");
	// return columnsScript.toString();
	// }
	//	
	private String getAllocateInfoColumns() {
		StringBuffer colBuffer = new StringBuffer();
		colBuffer.append("allocateInfo.\"allocSheetNo\" as \"allocSheetNo\", \n");
		colBuffer.append("allocateInfo.\"allocateInDate\" as \"allocateInDate\", \n");
		colBuffer.append("allocateInfo.\"allocateInPerson\" as \"allocateInPerson\", \n");
		colBuffer.append("allocateInfo.\"allocateInPersonName\" as \"allocateInPersonName\", \n");
		colBuffer.append("allocateInfo.\"allocateInStoreId\" as \"allocateInStoreId\", \n");
		colBuffer.append("allocateInfo.\"allocateInStoreName\" as \"allocateInStoreName\", \n");
		colBuffer.append("allocateInfo.\"allocateOutDate\" as \"allocateOutDate\", \n");
		colBuffer.append("allocateInfo.\"allocateOutPerson\" as \"allocateOutPerson\", \n");
		colBuffer.append("allocateInfo.\"allocateOutPersonName\" as \"allocateOutPersonName\", \n");
		colBuffer.append("allocateInfo.\"allocateOutStoreId\" as \"allocateOutStoreId\", \n");
		colBuffer.append("allocateInfo.\"allocateOutStoreName\" as \"allocateOutStoreName\", \n");
		colBuffer.append("allocateInfo.\"allocateReason\" as \"allocateReason\", \n");
		colBuffer.append("allocateInfo.\"applyDate\" as \"applyDate\", \n");
		colBuffer.append("allocateInfo.\"approveDate\" as \"approveDate\", \n");
		colBuffer.append("allocateInfo.\"approvePerson\" as \"approvePerson\", \n");
		colBuffer.append("allocateInfo.\"approvePersonId\" as \"approvePersonId\", \n");
		colBuffer.append("allocateInfo.\"createDate\" as \"createDate\", \n");
		colBuffer.append("allocateInfo.\"creator\" as \"creator\", \n");
		colBuffer.append("allocateInfo.\"creatorId\" as \"creatorId\", \n");
		colBuffer.append("allocateInfo.\"creatorPY\" as \"creatorPY\", \n");
		colBuffer.append("allocateInfo.\"deptId\" as \"deptId\", \n");
		colBuffer.append("allocateInfo.\"rejectReason\" as \"rejectReason\", \n");
		colBuffer.append("allocateInfo.\"RECID\" as \"recid\", \n");
		colBuffer.append("allocateInfo.\"status\" as \"status\" \n");
		return colBuffer.toString();
	}

	private String getAllocateDetailColumns() {
		StringBuffer colBuffer = new StringBuffer();
		colBuffer.append("detail.\"ableCount\" as \"ableCount\", \n");
		colBuffer.append("detail.\"allocateCount\" as \"allocateCount\", \n");
		colBuffer.append("detail.\"allocateId\" as \"allocateId\", \n");
		colBuffer.append("detail.\"createDate\" as \"createDate\", \n");
		colBuffer.append("detail.\"creatorId\" as \"creatorId\", \n");
		colBuffer.append("detail.\"stockCode\" as \"stockCode\", \n");
		colBuffer.append("detail.\"stockId\" as \"stockId\", \n");
		colBuffer.append("detail.\"stockName\" as \"stockName\", \n");
		colBuffer.append("detail.\"stockNo\" as \"stockNo\", \n");
		colBuffer.append("detail.\"stockSpec\" as \"stockSpec\", \n");
		colBuffer.append("detail.\"stockScale\" as \"stockScale\", \n");
		colBuffer.append("detail.\"RECID\" as \"recid\", \n");
		colBuffer.append("detail.\"unit\" as \"unit\" \n");
		return colBuffer.toString();
	}
}
