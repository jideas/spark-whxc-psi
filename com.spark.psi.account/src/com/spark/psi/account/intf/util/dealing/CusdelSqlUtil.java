package com.spark.psi.account.intf.util.dealing;

import java.util.List;
import com.jiuqi.dna.core.da.RecordSetField;
import com.jiuqi.dna.core.da.RecordSetFieldContainer;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.account.intf.entity.dealing.Dealing;

public final class CusdelSqlUtil {

	/**
	 * 处理搜索关键字
	 * @param statusKey 
	 * @param key 
	 * @return
	 */
	public static String getSqlSearch(String key) {
		StringBuilder sb = new StringBuilder();
		//处理搜索关键字
		if(CheckIsNull.isNotEmpty(key)) {
			sb.append(" and ( t.cusproName like '%");
			sb.append(key);
			sb.append("%' or t.cusproNamePY like '%");
			sb.append(key);
			sb.append("%' or t.cusproShortName like '%");
			sb.append(key);
			sb.append("')");
		}
		return sb.toString();
	}
	
	/**
	 * 排序
	 * @param sortType 
	 * @param sortColumn
	 * @return
	 */
	public static String getSqlOrderBy(String sortType, String sortColumn) {
		StringBuilder sb = new StringBuilder();
		sb.append(" order by t.");
		if(CheckIsNull.isNotEmpty(sortType) && CheckIsNull.isNotEmpty(sortColumn)) {
			if("1".equals(sortColumn)) {
				sb.append("cusproName"); 
			} else if("2".equals(sortColumn)) {
				sb.append("takePayBalance");
			}
			sb.append(" ");
			sb.append(sortType);
		} else {
			sb.append("takePayBalance desc");
		}
		return sb.toString();
	}
	
	/**
	 * 部门sql
	 * @param context 
	 * @param deptGuid
	 * @param sb 
	 * @return
	 */
//	public static List<FSysDept> getSqlDept(Context context, GUID deptGuid, StringBuilder sb) {
//		
//		sb.append(" and ( t.deptNo = @deptGuid");
//		GetDeptAllChildrenListForParentKey deptKey = new GetDeptAllChildrenListForParentKey(deptGuid);
//		List<FSysDept> superDeptlist = context.getList(FSysDept.class, deptKey);
//		
//		for (int i = 0; i< superDeptlist.size() ; i++) {
//			sb.append(" or t.deptNo = @deptGuid");
//			sb.append(i);
//		}
//		sb.append(")");
//		return superDeptlist;
//	}

	/**
	 * 定义sql
	 * @param deptList 
	 * @return
	 */
//	public static String getSqlDefine(List<FSysDept> deptList) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(" define query QUERY_SA_CUSPROVIDER_INFO_LIST (@tenantsGuid guid,@cusproType string,@busPerson guid,@deptGuid guid");
//		if(deptList != null) {
//			for (int i = 0; i < deptList.size(); i++) {
//				sb.append(",@deptGuid");
//				sb.append(i);
//				sb.append(" guid");
//			}
//		}
//		sb.append(")");
//		sb.append(" begin ");
//		return sb.toString();
//	}
	
	public static String getSqlFoolter() {
		return " end";
	}
	
	/**
	 * 时间范围
	 * @param endDate 
	 * @param beginDate 
	 * @return
	 */
	public static String getSqlDateRange(Long beginDate, Long endDate) {
		return "";
	}
	
	/**
	 * 组合查询的表头
	 * @return
	 */
	public static String getSqlHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("  select ");
		sb.append(" t.RECID as recid,               ");
		sb.append(" t.busPerson as busPerson,      ");
		sb.append(" t.cusproName as cusproName,    ");
		sb.append(" t.cusproNo as cusproNo,        ");
		sb.append(" t.tenantsGuid as tenantsGuid,  ");
		sb.append(" t.cusproShortName as cusproShortName,  ");
		sb.append(" t.takePayBalance as takePayBalance,    ");
		sb.append(" t.initPayBalance as initPayBalance,    ");
		sb.append(" t.isReflag as isReflag,          ");
		sb.append(" t.cusproType as cusproType          ");
		sb.append("  from SA_CUSPROVIDER_INFO as t ");
		return sb.toString();
	}
	
	/**
	 * 组合查询总金额的表头
	 * @return
	 */
	public static String getSqlAmountHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" sum(t.takePayBalance) as c     ");
		sb.append("  from SA_CUSPROVIDER_INFO as t ");
		return sb.toString();
	}
	
	/**
	 * 设置结果
	 * @param fields
	 * @param list
	 */
	public static void fillData(
			RecordSetFieldContainer<? extends RecordSetField> fields,
			List<Dealing> list) {
		Dealing entity = new Dealing();
//		entity.setId(fields.get(0).getGUID());
//		entity.setCustomerOrSupplierId(customerOrSupplierId);
//		entity.setAmount(amount);
		list.add(entity);
	}
}
