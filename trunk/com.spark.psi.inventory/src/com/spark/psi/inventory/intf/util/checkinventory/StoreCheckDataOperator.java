package com.spark.psi.inventory.intf.util.checkinventory;

import com.jiuqi.dna.core.da.RecordSet;
import com.spark.common.components.db.ERPTableNames;
import com.spark.psi.inventory.internal.entity.CheckInventory;

public class StoreCheckDataOperator {
	
	/**
	 * 把查询出的结果全部导入到StoreCheck里面，然后返回
	 * @param rs
	 * @return
	 */
	public static CheckInventory getStoreCheck(RecordSet rs) {
		CheckInventory storeCheck = new CheckInventory();
		storeCheck.setCheckObj(rs.getFields().get(0).getString());
		storeCheck.setCheckOrdNo(rs.getFields().get(1).getString());
		storeCheck.setCheckOrdState(rs.getFields().get(2).getString());
		storeCheck.setCheckPerson(rs.getFields().get(3).getString());
		storeCheck.setCreateDate(rs.getFields().get(4).getDate());
		storeCheck.setCreatePerson(rs.getFields().get(5).getString());
		storeCheck.setDeptGuid(rs.getFields().get(6).getGUID());
		storeCheck.setEndDate(rs.getFields().get(7).getDate());
		storeCheck.setMarkName(rs.getFields().get(8).getString());
		storeCheck.setMarkPerson(rs.getFields().get(9).getGUID());
		storeCheck.setRecid(rs.getFields().get(10).getGUID());
		storeCheck.setRemark(rs.getFields().get(11).getString());
		storeCheck.setStartDate(rs.getFields().get(12).getDate());
		storeCheck.setStoreGuid(rs.getFields().get(13).getGUID());
		storeCheck.setStoreName(rs.getFields().get(14).getString());
		storeCheck.setStorePY(rs.getFields().get(15).getString());
		storeCheck.setTenantsGuid(rs.getFields().get(16).getGUID());
		storeCheck.setStoreStatus(rs.getFields().get(17).getString());
		storeCheck.setCheckProfit(rs.getFields().get(18).getInt());
		storeCheck.setCheckDeficient(rs.getFields().get(19).getInt());
		return storeCheck;
	}
	
	/**
	 * 盘点单的默认的字段
	 */
	public static StringBuilder defaultSql() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("begin ");
		buffer.append("select ");
		buffer.append("t.checkObj as checkObj," +
				"t.checkOrdNo as checkOrdNo," +
				"t.checkOrdState as checkOrdState," +
				"t.checkPerson as checkPerson," +
				"t.createDate as createDate," +
				"t.createPerson as createPerson," +
				"t.deptGuid as deptGuid," +
				"t.endDate as endDate," +
				"t.markName as markName," +
				"t.markPerson as markPerson," +
				"t.RECID as recid," +
				"t.remark as remark," +
				"t.startDate as startDate," +
				"t.storeGuid as storeGuid," +
				"t.storeName as storeName," +
				"t.storePY as storePY," +
				"t.tenantsGuid as tenantsGuid," +
				"t.StoreState as StoreStatus, " +
				"t.checkProfit as checkProfit,"+
				"t.checkDeficient as checkDeficient ");
		buffer.append("from SA_STORE_CHECK as t ");
		buffer.append("where 1=1  ");
		return buffer;
	}
	
	/**
	 * 查询指定类型，指定部门所有员工关联的仓库
	 * @param deptGuid
	 * @param storeEmployeeType
	 */
	public static void buildQueryEmployeeStoreByDept(StringBuffer buffer) {
		buffer.append("select ");
		buffer.append(" store.\"RECID\" as \"storeRecid\" ");
		buffer.append("from SA_STORE_EMPLOYEE as storeEmployee \n");
		buffer.append("join (" + getDeptEmployeeSql("@employeeGuid") + ") as employee on storeEmployee.employeeGuid = employee.recid\n");
		buffer.append("join \n");
		buffer.append(ERPTableNames.Base.Store.getTableName());
		buffer.append(" as store on storeEmployee.storeGuid=store.recid \n");
		buffer.append(" where storeEmployee.employeeType='0'");
		buffer.append(" and ( store.StoreStatus = '1' or store.StoreStatus = '2' or store.StoreStatus = '3' ) ");
		buffer.append("group by storeEmployee.storeGuid");
	}
	
	/**
	 * 查询指定类型，指定员工，关联的仓库
	 * @param storeGuid
	 * @param storeEmployeeType
	 */
	public static void buildQueryEmployeeStoreSql(StringBuffer buffer) {
		buffer.append("select ");
		buffer.append(" store.\"RECID\" as \"storeRecid\" ");
		buffer.append("from SA_STORE_EMPLOYEE as storeEmployee \n");
		buffer.append("join sa_em_employee as employee on storeEmployee.employeeGuid=employee.recid \n");
		buffer.append("join \n");
		buffer.append(ERPTableNames.Base.Store.getTableName());
		buffer.append(" as store on storeEmployee.storeGuid=store.recid \n");
		buffer.append(" where storeEmployee.employeeGuid = @employeeGuid and storeEmployee.employeeType='0'");
		buffer.append(" and( store.StoreStatus = '1' or store.StoreStatus = '2' or store.StoreStatus = '3') ");
		buffer.append("group by storeEmployee.storeGuid");
	}
	
	
	/**
	 * 部门关联员工
	 * @param deptIdForParmater
	 * @return
	 */
	private static String getDeptEmployeeSql(String deptIdForParmater) {
		StringBuffer columnsScript = new StringBuffer();
		columnsScript.append("select employee.recid from sa_em_employee as employee \n");
		columnsScript.append("join (select t.RECID as \"RECID\" from SA_CORE_TREE as s join SA_CORE_TREE as t on (t.PATH > s.PATH and t.PATH < s.PATH || bytes'ff') or t.RECID = " + deptIdForParmater + " where s.RECID = " + deptIdForParmater + "  ORDER BY s.PATH) as allDepartments \n");
		columnsScript.append("on employee.departmentId = allDepartments.recid \n");
		return columnsScript.toString();
	}
}
