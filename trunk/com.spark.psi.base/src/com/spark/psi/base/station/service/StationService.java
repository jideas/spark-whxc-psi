package com.spark.psi.base.station.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.internal.entity.EnumEntityImpl;
import com.spark.psi.base.key.GetEmployeeListByAuthKey;
import com.spark.psi.base.publicimpl.StationItemImpl;
import com.spark.psi.base.publicimpl.StationManagerItemImpl;
import com.spark.psi.base.utils.QueryTableColumnUtil;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.station.entity.StationInfo;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.entity.StationManagerItem;
import com.spark.psi.publish.base.station.key.GetAllStationEnumEntityKey;
import com.spark.psi.publish.base.station.key.GetAllStationManagerKey;
import com.spark.psi.publish.base.station.key.GetNewStationNoKey;
import com.spark.psi.publish.base.station.key.GetStationListKey;
import com.spark.psi.publish.base.station.task.StationInfoTask;
import com.spark.psi.publish.base.station.task.StopStationTask;
import com.spark.psi.publish.base.station.task.ValidateStationNameTask;

public class StationService extends Service {

	protected StationService() {
		super("com.spark.psi.base.station.service.StationService");
	}

	private static String SaveLock = "locked";

	@Publish
	protected class StationListByUserProvider extends OneKeyResultListProvider<StationItem, GetStationListKey> {

		@Override
		protected void provide(Context context, GetStationListKey key, List<StationItem> list) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.stationNo", "stationNo");
			qb.addColumn("t.stationName", "stationName");
			qb.addColumn("t.shortName", "shortName");
			qb.addColumn("t.manager", "manager");
			qb.addColumn("t.managerPhone", "managerPhone");
			qb.addColumn("t.province", "province");
			qb.addColumn("t.city", "city");
			qb.addColumn("t.town", "town");
			qb.addColumn("t.address", "address");
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			if (login.hasAuth(Auth.Boss) || key.isQueryAll()) {

			} else if (login.hasAuth(Auth.StationManager)) {
				Department dep = context.find(Department.class, user.getDepartmentId());
				List<String> args = new ArrayList<String>();
				int index = 0;
				for (Department d : dep.getDescendants(context, Auth.Station)) {
					args.add("@depId" + index);
					qb.addArgs("depId" + index++, qb.guid, d.getId());
				}
				qb.addIn("t.deptId", args);
			} else if (login.hasAuth(Auth.Station)) {
				qb.addArgs("userId", qb.guid, user.getId());
				qb.addEquals("t.managerId", "@userId");
			} else {
				return;
			}
			if (key.getSortField() != null) {
				if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " asc ");
				}
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			while (rs.next()) {
				StationItemImpl impl = new StationItemImpl();
				int i = 0;
				impl.setId(rs.getFields().get(i++).getGUID());
				String stationNo = rs.getFields().get(i++).getString();
				String str1 = stationNo.substring(0, 4);
				String str2 = stationNo.substring(4, 6);
				String str3 = stationNo.substring(6);
				if (str2.indexOf("4") >= 0) {
					str2 = str2.replace("4", "A");
					stationNo = str1 + str2 + str3;
				}
				impl.setStationNo(stationNo);
				impl.setName(rs.getFields().get(i++).getString());
				impl.setShortName(rs.getFields().get(i++).getString());
				impl.setManager(rs.getFields().get(i++).getString());
				impl.setMobileNo(rs.getFields().get(i++).getString());
				String province = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				String city = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				String town = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				impl.setAddress(province + " " + city + " " + town + " " + rs.getFields().get(i++).getString());
				list.add(impl);
			}
		}
	}
	
	@Publish
	protected class GetStationItemById extends OneKeyResultProvider<StationItem, GUID>
	{
		@Override
		protected StationItem provide(Context context, GUID id) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.stationNo", "stationNo");
			qb.addColumn("t.stationName", "stationName");
			qb.addColumn("t.shortName", "shortName");
			qb.addColumn("t.manager", "manager");
			qb.addColumn("t.managerPhone", "managerPhone");
			qb.addColumn("t.province", "province");
			qb.addColumn("t.city", "city");
			qb.addColumn("t.town", "town");
			qb.addColumn("t.address", "address");
			qb.addColumn("t.isStop", "isStop");
			
			qb.addArgs("recid", qb.guid, id);
			qb.addEquals("t.RECID", "@recid");
			
			RecordSet rs = qb.getRecord();
			while(rs.next())
			{
				StationItemImpl impl = new StationItemImpl();
				int i = 0;
				impl.setId(rs.getFields().get(i++).getGUID());
				String stationNo = rs.getFields().get(i++).getString();
				String str1 = stationNo.substring(0, 4);
				String str2 = stationNo.substring(4, 6);
				String str3 = stationNo.substring(6);
				if (str2.indexOf("4") >= 0) {
					str2 = str2.replace("4", "A");
					stationNo = str1 + str2 + str3;
				}
				impl.setStationNo(stationNo);
				impl.setName(rs.getFields().get(i++).getString());
				impl.setShortName(rs.getFields().get(i++).getString());
				impl.setManager(rs.getFields().get(i++).getString());
				impl.setMobileNo(rs.getFields().get(i++).getString());
				String province = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				String city = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				String town = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				impl.setAddress(province + " " + city + " " + town + " " + rs.getFields().get(i++).getString());
				impl.setStop(rs.getFields().get(i++).getBoolean());
				return impl;
			}
			return null;
		}
		
	}

	@Publish
	protected class StationListProvider extends OneKeyResultProvider<ListEntity<StationItem>, GetStationListKey> {

		@Override
		protected ListEntity<StationItem> provide(Context context, GetStationListKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.stationNo", "stationNo");
			qb.addColumn("t.stationName", "stationName");
			qb.addColumn("t.shortName", "shortName");
			qb.addColumn("t.manager", "manager");
			qb.addColumn("t.managerPhone", "managerPhone");
			qb.addColumn("t.province", "province");
			qb.addColumn("t.city", "city");
			qb.addColumn("t.town", "town");
			qb.addColumn("t.address", "address");
			qb.addColumn("t.isStop", "isStop");

			if (CheckIsNull.isNotEmpty(key.getSearchText())) {
				qb.addArgs("text", qb.STRING, key.getSearchText());
				StringBuilder ss = new StringBuilder("( ");
				ss.append(" t.stationNo like '%'+@text+'%' ");
				ss.append(" or t.stationName like '%'+@text+'%' ");
				ss.append(" or t.shortName like '%'+@text+'%' ");
				ss.append(" or t.manager like '%'+@text+'%' ");
				ss.append(" or t.address like '%'+@text+'%' ");
				ss.append(" )");
				qb.addCondition(ss.toString());
			}
			Login login = context.find(Login.class);
			Employee user = context.find(Employee.class, login.getEmployeeId());
			if (login.hasAuth(Auth.Boss) || key.isQueryAll()) {

			} else if (login.hasAuth(Auth.StationManager)) {
				Department dep = context.find(Department.class, user.getDepartmentId());
				List<String> args = new ArrayList<String>();
				int index = 0;
				for (Department d : dep.getDescendants(context, Auth.Station)) {
					args.add("@depId" + index);
					qb.addArgs("depId" + index++, qb.guid, d.getId());
				}
				qb.addIn("t.deptId", args);
			} else if (login.hasAuth(Auth.Station)) {
				qb.addArgs("userId", qb.guid, user.getId());
				qb.addEquals("t.managerId", "@userId");
			}
			if (key.getSortField() != null) {
				if (key.getSortType() == SortType.Desc) {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " desc ");
				} else {
					qb.addOrderBy("t." + key.getSortField().getFieldName() + " asc ");
				}
			}
			RecordSet rs = null;
			if (key.getCount() > 0) {
				rs = qb.getRecordLimit(key.getOffset(), key.getCount());
			} else {
				rs = qb.getRecord();
			}
			List<StationItem> resultList = new ArrayList<StationItem>();
			while (rs.next()) {
				StationItemImpl impl = new StationItemImpl();
				int i = 0;
				impl.setId(rs.getFields().get(i++).getGUID());
				String stationNo = rs.getFields().get(i++).getString();
				String str1 = stationNo.substring(0, 4);
				String str2 = stationNo.substring(4, 6);
				String str3 = stationNo.substring(6);
				if (str2.indexOf("4") >= 0) {
					str2 = str2.replace("4", "A");
					stationNo = str1 + str2 + str3;
				}
				impl.setStationNo(stationNo);
				impl.setName(rs.getFields().get(i++).getString());
				impl.setShortName(rs.getFields().get(i++).getString());
				impl.setManager(rs.getFields().get(i++).getString());
				impl.setMobileNo(rs.getFields().get(i++).getString());
				String province = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				String city = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				String town = context.find(EnumEntity.class, EnumType.Area, rs.getFields().get(i++).getString())
						.getName();
				impl.setAddress(province + " " + city + " " + town + " " + rs.getFields().get(i++).getString());
				impl.setStop(rs.getFields().get(i++).getBoolean());
				resultList.add(impl);
			}
			if (resultList.size() == 0) {
				return null;
			}
			return new ListEntity<StationItem>(resultList, resultList.size());
		}
	}

	@Publish
	protected class StationProvider extends OneKeyResultProvider<StationInfo, GUID> {

		@Override
		protected StationInfo provide(Context context, GUID key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			QueryTableColumnUtil.setStationInfoColumn(qb);
			qb.addArgs("id", qb.guid, key);
			qb.addEquals("t.RECID", "@id");
			RecordSet rs = qb.getRecord();
			if (rs.next()) {
				return QueryTableColumnUtil.getStationInfo(context, rs);
			}
			return null;
		}
	}

	@Publish
	protected class SaveOrUpdateStationHandle extends SimpleTaskMethodHandler<StationInfoTask> {

		@Override
		protected void handle(Context context, StationInfoTask task) throws Throwable {
			if (null == task.getId()) {
				doAdd(context, task);
			} else {
				doUpdate(context, task);
			}
		}

		private void doUpdate(Context context, StationInfoTask task) {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.Station.getTableName());
			ub.addColumn("address", ub.STRING, task.getAddress());
			ub.addColumn("city", ub.STRING, task.getCity());
			ub.addColumn("province", ub.STRING, task.getProvince());
			ub.addColumn("town", ub.STRING, task.getTown());
			ub.addColumn("fax", ub.STRING, task.getFax());
			ub.addColumn("manager", ub.STRING, task.getManager());
			ub.addColumn("managerEmail", ub.STRING, task.getManagerEmail());
			ub.addColumn("managerId", ub.guid, task.getManagerId());
			Employee user = context.find(Employee.class, task.getManagerId());
			ub.addColumn("deptId", ub.guid, user.getDepartmentId());
			ub.addColumn("managerPersonId", ub.STRING, task.getManagerPersonId());
			ub.addColumn("managerPhone", ub.STRING, task.getManagerPhone());
			ub.addColumn("remark", ub.STRING, task.getRemark());
			ub.addColumn("shortName", ub.STRING, task.getShortName());
			ub.addColumn("namePY", ub.STRING, PinyinHelper.getLetter(task.getStationName()));
			ub.addColumn("stationName", ub.STRING, task.getStationName());
			ub.addColumn("telephone", ub.STRING, task.getTelephone());
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID=@id");
			ub.execute();
		}

		private void doAdd(Context context, StationInfoTask task) {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.Station.getTableName());
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("address", ib.STRING, task.getAddress());
			ib.addColumn("city", ib.STRING, task.getCity());
			ib.addColumn("province", ib.STRING, task.getProvince());
			ib.addColumn("town", ib.STRING, task.getTown());
			ib.addColumn("fax", ib.STRING, task.getFax());
			ib.addColumn("manager", ib.STRING, task.getManager());
			ib.addColumn("managerEmail", ib.STRING, task.getManagerEmail());
			ib.addColumn("managerId", ib.guid, task.getManagerId());
			Employee user = context.find(Employee.class, task.getManagerId());
			ib.addColumn("deptId", ib.guid, user.getDepartmentId());
			ib.addColumn("managerPersonId", ib.STRING, task.getManagerPersonId());
			ib.addColumn("managerPhone", ib.STRING, task.getManagerPhone());
			ib.addColumn("remark", ib.STRING, task.getRemark());
			ib.addColumn("shortName", ib.STRING, task.getShortName());
			ib.addColumn("namePY", ib.STRING, PinyinHelper.getLetter(task.getStationName()));
			ib.addColumn("stationName", ib.STRING, task.getStationName());
			ib.addColumn("telephone", ib.STRING, task.getTelephone());

			synchronized (SaveLock) {
				String number = context.find(String.class, new GetNewStationNoKey(task.getTown()));
				task.setStationNo(number);
				ib.addColumn("stationNo", ib.STRING, task.getStationNo());
				ib.execute();
			}
		}
	}

	@Publish
	protected class StopStationHandle extends SimpleTaskMethodHandler<StopStationTask> {

		@Override
		protected void handle(Context context, StopStationTask task) throws Throwable {
			UpdateSqlBuilder db = new UpdateSqlBuilder(context);
			db.setTable(ERPTableNames.Base.Station.getTableName());
			db.addColumn("isStop", db.BOOLEAN, task.isStop());
			db.addCondition("id", db.guid, task.getId(), "t.RECID = @id");
			db.execute();
		}
	}

	@Publish
	protected class AllManagerItemListProvider extends
			OneKeyResultListProvider<StationManagerItem, GetAllStationManagerKey> {

		@Override
		protected void provide(Context context, GetAllStationManagerKey key, List<StationManagerItem> resultList)
				throws Throwable {
			List<Employee> emps = context.getList(Employee.class, new GetEmployeeListByAuthKey(Auth.Station,
					Auth.StationManager));
			for (Employee emp : emps) {
				Department dep = context.find(Department.class, emp.getDepartmentId());
				resultList.add(new StationManagerItemImpl(emp.getId(), emp.getName(), emp.getDepartmentId(), dep
						.getName(), emp.getMobileNo(), emp.getIdNumber()));
			}
		}

	}

	/**
	 * 得到最新站点编号
	 */
	@Publish
	protected class GetNewStationNoProvider extends OneKeyResultProvider<String, GetNewStationNoKey> {

		@Override
		protected String provide(Context context, GetNewStationNoKey key) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			String number = null;
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addArgs("stype", qb.STRING, key.getTown().substring(0, 6));
			qb.addColumn("t.stationNo", "ccc");
			qb.addCondition("t.stationNo like @stype+'%'");
			qb.addOrderBy("t.stationNo desc ");
			RecordSet rs = qb.getRecordLimit(0, 1);
			if (rs.next()) {
				String count = rs.getFields().get(0).getString();
				if (CheckIsNull.isNotEmpty(count)) {
					number = com.spark.common.utils.character.StringHelper.addOneInt(count);
					while (number.substring(6).indexOf("4") >= 0) {
						number = com.spark.common.utils.character.StringHelper.addOneInt(number + "");
					}
					return number;
				}
			}
			return key.getTown().substring(0, 6) + "001";
		}
	}

	/**
	 * 校验客户全称是否是唯一的
	 */
	@Publish
	protected class ValidateCustomerNameIsOnlyHandler extends
			TaskMethodHandler<ValidateStationNameTask, ValidateStationNameTask.Method> {

		protected ValidateCustomerNameIsOnlyHandler() {
			super(ValidateStationNameTask.Method.FullName);
		}

		@Override
		protected void handle(Context context, ValidateStationNameTask task) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addColumn("count(1)", "ccc");
			qb.addArgs("id", "guid", task.getId());
			qb.addArgs("name", qb.STRING, task.getName());
			qb.addNotEquals("t.RECID", "@id");
			qb.addEquals("t.stationName", "@name");
			RecordSet rs = qb.getRecord();
			rs.next();
			if (rs.getFields().get(0).getInt() > 0) {
				task.setExist(true);
			} else {
				task.setExist(false);
			}
		}
	}

	/**
	 * 校验客户简称是否是唯一的
	 */
	@Publish
	protected class ValidateCustomerShortNameIsOnlyHandler extends
			TaskMethodHandler<ValidateStationNameTask, ValidateStationNameTask.Method> {

		protected ValidateCustomerShortNameIsOnlyHandler() {
			super(ValidateStationNameTask.Method.ShortName);
		}

		@Override
		protected void handle(Context context, ValidateStationNameTask task) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addColumn("count(1)", "ccc");
			qb.addArgs("id", "guid", task.getId());
			qb.addArgs("name", qb.STRING, task.getName());
			qb.addNotEquals("t.RECID", "@id");
			qb.addEquals("t.shortName", "@name");
			RecordSet rs = qb.getRecord();
			rs.next();
			if (rs.getFields().get(0).getInt() > 0) {
				task.setExist(true);
			} else {
				task.setExist(false);
			}
		}
	}

	@Publish
	protected class GetAllStationEnumEntityProvider extends
			OneKeyResultListProvider<EnumEntity, GetAllStationEnumEntityKey> {

		@Override
		protected void provide(Context context, GetAllStationEnumEntityKey key, List<EnumEntity> resultList)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.Station.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.shortName", "shortName");
			qb.addOrderBy("t.namePY");
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				resultList.add(new EnumEntityImpl(rs.getFields().get(0).getGUID().toString(), rs.getFields().get(1)
						.getString()));
			}
		}

	}

}
