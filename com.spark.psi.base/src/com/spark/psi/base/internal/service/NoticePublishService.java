package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.event.NoticeReadEvent;
import com.spark.psi.base.event.NoticeStatusChangeEvent;
import com.spark.psi.base.event.TimerEvent;
import com.spark.psi.base.event.NoticeStatusChangeEvent.NoticeAction;
import com.spark.psi.base.internal.entity.NoticeImpl;
import com.spark.psi.base.internal.service.orm.Orm_Notice;
import com.spark.psi.base.publicimpl.NoticeInfoImpl;
import com.spark.psi.base.publicimpl.NoticeItemImpl;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.NoticeStatus;
import com.spark.psi.publish.base.notice.entity.NoticeInfo;
import com.spark.psi.publish.base.notice.entity.NoticeItem;
import com.spark.psi.publish.base.notice.key.FindNoticeInfoKey;
import com.spark.psi.publish.base.notice.key.FindNoticeItemListKey;
import com.spark.psi.publish.base.notice.key.FindPublishedNoticeInfoKey;
import com.spark.psi.publish.base.notice.task.CancelNoticeTask;
import com.spark.psi.publish.base.notice.task.DeleteNoticeDeptByNoticeTask;
import com.spark.psi.publish.base.notice.task.DeleteNoticeTask;
import com.spark.psi.publish.base.notice.task.PublishNoticeTask;
import com.spark.psi.publish.base.notice.task.SaveNoticeDeptTask;
import com.spark.psi.publish.base.notice.task.SaveOrUpdateNoticeTask;
import com.spark.psi.publish.base.notice.task.TimerNoticeTask;

/**
 * <p>
 * 公告服务
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 20012 - 20018<br>
 * 
 * 
 * @author 刘青
 * @version 2012-4-19
 */

public class NoticePublishService extends Service {
	final Orm_Notice ormNotice;

	/**
	 * 
	 *构造方法
	 */
	protected NoticePublishService(Orm_Notice ormNotice) {
		super("com.spark.psi.base.internal.service.NoticePublishService");
		this.ormNotice = ormNotice;
	}

	/**
	 * 新增公告
	 */
	@Publish
	protected class addNotice extends TaskMethodHandler<SaveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method> {

		/**
		 *构造方法
		 */
		protected addNotice() {
			super(SaveOrUpdateNoticeTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, SaveOrUpdateNoticeTask task) throws Throwable {
			// 获得登录用户
			Employee employee = context.find(Employee.class);
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				NoticeImpl notice = new NoticeImpl();
				// 公告GUID
				notice.setRECID(task.getRECID());
				// 租户GUID
				notice.setTenantsGuid(employee.getTenantId());
				// 部门GUID
				notice.setDeptGuid(employee.getDepartmentId());
				// 发部范围
				notice.setDeptNameStr(task.getDeptNameStr());
				// 创建人GUID
				notice.setCreateGuid(employee.getId());
				// 创建人
				notice.setCreatePerson(employee.getName());
				// 公告标题
				notice.setNoticeTitle(task.getNoticeTitle());
				if (StringHelper.isNotEmpty(task.getNoticeTitle())) {
					// 公告标题拼音
					notice.setNoticeTitlePY(PinyinHelper.getLetter(task.getNoticeTitle().trim()));
				}
				// 创建时间
				notice.setCancelTime(new Date().getTime());
				// 发布时间
				notice.setPublishTime(task.getPublishTime());
				// 撤销时间
				notice.setCancelTime(task.getCancelTime());
				// 是否置顶
				notice.setIsTop(task.getIsTop());
				// 公告状态
				notice.setNoticeStatus(NoticeStatus.NotRelease.getKey());
				// 公告内容
				notice.setNoticeContent(task.getNoticeContent());
				// 创建时间
				notice.setCreateTime(task.getCreateDate());
				// 新增公告
				ormAccessor.insert(notice);
				// 新增部门与公告关系
				addNoticeDept(context, employee.getTenantId(), notice.getRECID(), task.getDeptGuidList());
				// 当天则立即发布
				if (DateUtil.isCurrentDay(task.getPublishTime())) {
					context.handle(new PublishNoticeTask(notice.getRECID()));
				}

			} finally {
				ormAccessor.unuse();
			}

		}

	}

	/**
	 * 更新公告
	 */
	@Publish
	protected class updateNotice extends TaskMethodHandler<SaveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method> {

		/**
		 *构造方法
		 */
		protected updateNotice() {
			super(SaveOrUpdateNoticeTask.Method.UPDATE);
		}

		@Override
		protected void handle(Context context, SaveOrUpdateNoticeTask task) throws Throwable {
			// 取登录用户
			Login login = context.find(Login.class);
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// 根据GUID查询公告
				NoticeImpl notice = ormAccessor.findByRECID(task.getRECID());
				if (null != notice) {
					// GUID
					notice.setRECID(task.getRECID());
					// 发部范围
					notice.setDeptNameStr(task.getDeptNameStr());
					// 公告标题
					notice.setNoticeTitle(task.getNoticeTitle());
					if (StringHelper.isNotEmpty(task.getNoticeTitle())) {
						// 公告标题拼音
						notice.setNoticeTitlePY(PinyinHelper.getLetter(task.getNoticeTitle().trim()));
					}
					// 发布时间
					notice.setPublishTime(task.getPublishTime());
					// 撤销时间
					notice.setCancelTime(task.getCancelTime());
					// 是否置顶
					notice.setIsTop(task.getIsTop());
					// 公告内容
					notice.setNoticeContent(task.getNoticeContent());
					// 创建时间
					notice.setCreateTime(task.getCreateDate());
					// 更新公告
					ormAccessor.update(notice);
					// 根据公告GUID删除部门与公告关系
					DeleteNoticeDeptByNoticeTask deleteNoticeDeptByNoticeTask = new DeleteNoticeDeptByNoticeTask();
					deleteNoticeDeptByNoticeTask.setNoticeGUID(task.getRECID());
					context.handle(deleteNoticeDeptByNoticeTask);
					// 新增部门与公告中间表
					addNoticeDept(context, login.getTenantId(), task.getRECID(), task.getDeptGuidList());
					// 当天则立即发布
					if (DateUtil.isCurrentDay(task.getPublishTime())) {
						context.handle(new PublishNoticeTask(notice.getRECID()));
					}
				}
			} finally {
				ormAccessor.unuse();
			}
		}
	}

	/**
	 * 删除公告
	 */
	@Publish
	protected class deleteNotice extends SimpleTaskMethodHandler<DeleteNoticeTask> {

		@Override
		protected void handle(Context context, DeleteNoticeTask task) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// 删除公告
				ormAccessor.delete(task.getRECID());
				// 根据公告GUID删除部门与公告关系
				DeleteNoticeDeptByNoticeTask deleteNoticeDeptByNoticeTask = new DeleteNoticeDeptByNoticeTask();
				deleteNoticeDeptByNoticeTask.setNoticeGUID(task.getRECID());
				context.handle(deleteNoticeDeptByNoticeTask);
			} finally {
				ormAccessor.unuse();
			}

		}

	}

	/**
	 * 发布公告
	 */
	@Publish
	protected class publishNotice extends SimpleTaskMethodHandler<PublishNoticeTask> {

		@Override
		protected void handle(Context context, PublishNoticeTask task) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// 根据GUID查询公告
				NoticeImpl notice = ormAccessor.findByRECID(task.getRECID());
				if (null != notice) {
					// 公告状态
					notice.setNoticeStatus(NoticeStatus.Released.getKey());
					// 发布时间
					notice.setPublishTime(new Date().getTime());
					ormAccessor.update(notice);
					// 发送消息
					context.dispatch(new NoticeStatusChangeEvent(task.getRECID(), notice.getTenantsGuid(), NoticeAction.Effectual));
				}
			} finally {
				ormAccessor.unuse();
			}
		}

	}

	/**
	 * 撤消公告
	 */
	@Publish
	protected class cancelNotice extends SimpleTaskMethodHandler<CancelNoticeTask> {

		@Override
		protected void handle(Context context, CancelNoticeTask task) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// 根据GUID查询公告
				NoticeImpl notice = ormAccessor.findByRECID(task.getRECID());
				if (null != notice) {
					// 公告状态
					notice.setNoticeStatus(task.getNoticeStatus());
					// 撤消时间
					notice.setCancelTime(new Date().getTime());
					ormAccessor.update(notice);
					// 发送消息
					context.dispatch(new NoticeStatusChangeEvent(task.getRECID(), notice.getTenantsGuid(), NoticeAction.Expired));
				}
			} finally {
				ormAccessor.unuse();
			}
		}

	}

	/**
	 * 查询公告列表
	 */
	@Publish
	protected class findNoticeItemList extends OneKeyResultListProvider<NoticeItem, FindNoticeItemListKey> {

		/**
		 * 获得查询SQL
		 * 
		 *@param employee
		 *            员工
		 *@param key
		 *            查询公告列表key
		 *@param paramName
		 *            DNA SQL查询参数串
		 *@param paramValue
		 *            参数值
		 *@return
		 */
		private String getSql(Employee employee, FindNoticeItemListKey key, StringBuffer paramName, List<Object> paramValue) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select t.RECID as RECID ");
			buffer.append(", t.createGuid as createGuid ");
			buffer.append(", t.createPerson as createPerson ");
			buffer.append(", t.noticeTitle as noticeTitle ");
			buffer.append(", t.publishTime as publishTime ");
			buffer.append(", t.cancelTime as cancelTime ");
			buffer.append(", t.deptNameStr as deptNameStr ");
			buffer.append(", t.isTop as isTop ");
			buffer.append(" from SA_NOTICE as t ");
			// 租户
			buffer.append(" where t.tenantsGuid=@tenantsGuid");
			paramName.append("@tenantsGuid guid");
			paramValue.add(employee.getTenantId());
			// 类型
			if (key.getType().equals(FindNoticeItemListKey.NOT_RELEASE)) { // 未发布
				buffer.append(" and t.noticeState=@notReleasestatus");
				paramName.append(",@notReleasestatus string");
				paramValue.add(NoticeStatus.NotRelease.getKey());
			} else if (key.getType().equals(FindNoticeItemListKey.RELEASED)) { // 已发布
				buffer.append(" and t.noticeState=@releasedstatus");
				paramName.append(",@releasedstatus string");
				paramValue.add(NoticeStatus.Released.getKey());
			} else { // 撤消或过期
				buffer.append(" and t.noticeState in (@cancelstatus, @overduestatus)");
				paramName.append(",@cancelstatus string");
				paramName.append(",@overduestatus string");
				paramValue.add(NoticeStatus.Cancel.getKey());
				paramValue.add(NoticeStatus.Overdue.getKey());
			}
			// 搜索字符串 公告标题模糊匹配
			String searchText = key.getSearchText();
			if (null != searchText && !searchText.trim().equals("")) {
				buffer.append(" and ( ");
				buffer.append(" t.noticeTitle like '%'+@searchText+'%' ");
				buffer.append(" or t.noticeTitlePY like '%'+@searchText+'%' ");
				buffer.append(" or t.createPerson like '%'+@searchText+'%' ");
				buffer.append(" or t.deptNameStr like '%'+@searchText+'%' ");
				buffer.append(" ) ");
				paramName.append(",@searchText string");
				paramValue.add(key.getSearchText());
			}
			// 非Boss并且查询未发布公告时，只查询自已创建的公告
			if (!employee.hasAuth(Auth.Boss) && key.getType().equals(FindNoticeItemListKey.NOT_RELEASE)) {
				buffer.append(" and t.createGuid=@createGuid ");
				paramName.append(",@createGuid guid");
				paramValue.add(key.getCreateGuid());
			}
			// 非Boss并且查询已发布或历史公告时，只查询属于自已部门公告或自已创建的公告
			if (!employee.hasAuth(Auth.Boss)
					&& (key.getType().equals(FindNoticeItemListKey.RELEASED) || key.getType().equals(FindNoticeItemListKey.HISTORY))) {
				buffer.append(" and (");
				buffer.append(" exists (select 1 from SA_NOTICE_DEPT as dt where dt.noticeGuid=t.RECID and dt.deptGuid=@deptGuid) ");
				buffer.append(" or t.createGuid=@createGuid ");
				buffer.append(" ) ");
				paramName.append(",@deptGuid guid");
				paramValue.add(key.getDeptGuid());
				paramName.append(",@createGuid guid");
				paramValue.add(key.getCreateGuid());
			}
			// 如果历史公告且发布公告起始时间不为空
			if (key.getType().equals(FindNoticeItemListKey.HISTORY) && null != key.getQueryTerm()) {
				buffer.append(" and t.publishTime between @beginTime and @endTime ");
				paramName.append(",@beginTime date");
				paramName.append(",@endTime date");
				paramValue.add(key.getQueryTerm().getStartTime());
				paramValue.add(key.getQueryTerm().getEndTime());
			}
			// 排序
			if (StringHelper.isNotEmpty(key.getSortCloumName())) {
				buffer.append(" order by t.").append(key.getSortCloumName()).append(" ").append(key.getSortType());
			} else {
				buffer.append(" order by t.isTop desc, t.publishTime desc ");
			}
			return buffer.toString();
		}

		/**
		 * 获得查询DNASQL
		 */
		private String getDnaSql(Employee employee, FindNoticeItemListKey key, List<Object> param) {

			StringBuffer paramName = new StringBuffer();
			// 获得SQL
			String sql = getSql(employee, key, paramName, param);
			// 组装DNA SQL
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_NoticeItemList");
			buffer.append(" ( ");
			buffer.append(paramName.toString());
			buffer.append(" ) ");
			buffer.append(" begin ");
			buffer.append(sql);
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void provide(Context context, FindNoticeItemListKey key, List<NoticeItem> resultList) throws Throwable {
			// 获取登录员工
			Employee employee = context.find(Employee.class);
			List<Object> param = new ArrayList<Object>();
			// 获得SQL
			String sql = getDnaSql(employee, key, param);
			DBCommand dbCommand = context.prepareStatement(sql);
			NoticeItemImpl noticeItem = null;
			try {
				// 设置参数
				for (int i = 0, size = param.size(); i < size; i++) {
					dbCommand.setArgumentValue(i, param.get(i));
				}
				RecordSet recordSet = dbCommand.executeQueryLimit(key.getOffset(), key.getCount());
				while (recordSet.next()) {
					noticeItem = new NoticeItemImpl();
					noticeItem.setRECID(recordSet.getFields().get(0).getGUID());
					noticeItem.setCreateGuid(recordSet.getFields().get(1).getGUID());
					noticeItem.setCreatePerson(recordSet.getFields().get(2).getString());
					noticeItem.setNoticeTitle(recordSet.getFields().get(3).getString());
					noticeItem.setPublishTime(recordSet.getFields().get(4).getLong());
					noticeItem.setCancelTime(recordSet.getFields().get(5).getLong());
					noticeItem.setDeptNameStr(recordSet.getFields().get(6).getString());
					noticeItem.setIsTop(recordSet.getFields().get(7).getBoolean());
					resultList.add(noticeItem);
				}
			} finally {
				dbCommand.unuse();
			}

		}
	}

	/**
	 * 查询公告详情并发布阅读事件
	 */
	@Publish
	protected class findPublishedNoticeInfo extends OneKeyResultProvider<NoticeInfo, FindPublishedNoticeInfoKey> {

		@Override
		protected NoticeInfo provide(Context context, FindPublishedNoticeInfoKey key) throws Throwable {
			// 调用通用查询公告详情
			FindNoticeInfoKey findNoticeInfoKey = new FindNoticeInfoKey();
			findNoticeInfoKey.setRECID(key.getRECID());
			NoticeInfo noticeInfo = context.get(NoticeInfo.class, findNoticeInfoKey);
			context.dispatch(new NoticeReadEvent(key.getRECID()));
			return noticeInfo;
		}

	}

	/**
	 * 通用查询公告详情
	 */
	@Publish
	protected class findNoticeInfo extends OneKeyResultProvider<NoticeInfo, FindNoticeInfoKey> {

		/**
		 * 获得查询SQL
		 */
		private String getSql() {

			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_DeptGuidList(@noticeGuid guid not null, @tenantsGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select t.deptGuid as deptGuid ");
			buffer.append(" from SA_NOTICE_DEPT as t ");
			buffer.append(" where t.noticeGuid=@noticeGuid and t.tenantsGuid=@tenantsGuid");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected NoticeInfo provide(Context context, FindNoticeInfoKey key) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			NoticeInfoImpl noticeInfo = new NoticeInfoImpl();
			try {
				NoticeImpl notice = ormAccessor.findByRECID(key.getRECID());
				if (null != notice) {
					noticeInfo.setRECID(notice.getRECID());
					noticeInfo.setTenantsGuid(notice.getTenantsGuid());
					noticeInfo.setDeptGuid(notice.getDeptGuid());
					noticeInfo.setDeptNameStr(notice.getDeptNameStr());
					noticeInfo.setCreateGuid(notice.getCreateGuid());
					noticeInfo.setCreatePerson(notice.getCreatePerson());
					noticeInfo.setNoticeTitle(notice.getNoticeTitle());
					noticeInfo.setNoticeTitlePY(notice.getNoticeTitlePY());
					noticeInfo.setCreateTime(notice.getCreateTime());
					noticeInfo.setPublishTime(notice.getPublishTime());
					noticeInfo.setCancelTime(notice.getCancelTime());
					noticeInfo.setIsTop(notice.getIsTop());
					noticeInfo.setNoticeStatus(notice.getNoticeStatus());
					noticeInfo.setNoticeContent(notice.getNoticeContent());
					// 查询公告发布范围GUID列表
					String sql = getSql();
					DBCommand dbCommand = context.prepareStatement(sql);
					dbCommand.setArgumentValue(0, notice.getRECID());
					dbCommand.setArgumentValue(1, context.get(Login.class).getTenantId());
					RecordSet recordSet = dbCommand.executeQuery();
					List<GUID> deptGuidList = new ArrayList<GUID>();
					while (recordSet.next()) {
						deptGuidList.add(recordSet.getFields().get(0).getGUID());
					}
					noticeInfo.setDeptGuidList(deptGuidList);
				}
			} finally {
				ormAccessor.unuse();
			}
			return noticeInfo;
		}

	}

	/**
	 * 保存公告发布范围
	 */
	private void addNoticeDept(Context context, GUID tenantId, GUID noticeGUID, List<GUID> guidList) {
		if (null != guidList && guidList.size() > 0) {
			for (GUID deptGUID : guidList) {
				SaveNoticeDeptTask task = new SaveNoticeDeptTask();
				task.setTenantsGUID(tenantId);
				task.setNoticeGUID(noticeGUID);
				task.setDeptGUID(deptGUID);
				context.handle(task);
			}
		}
	}

	/**
	 * 自动发布公告
	 */
	@Publish
	protected class autoPublishingNotice extends TaskMethodHandler<TimerNoticeTask, TimerNoticeTask.Method> {

		/**
		 *构造方法
		 */
		protected autoPublishingNotice() {
			super(TimerNoticeTask.Method.AUTOPUBLISH);
		}

		/**
		 * 获得自动发布公告的SQL
		 */
		private String getAutoPublishSql() {

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("define update autoCancelNotice(@releasedstatus string, @curDate date, @publishTime date, @notReleasestatus string) ");
			buffer.append(" begin ");
			buffer.append(" update SA_NOTICE as t ");
			buffer.append(" set noticeState=@releasedstatus, publishTime=@curDate ");
			buffer.append(" where t.publishTime=@publishTime and t.noticeState=@notReleasestatus");
			buffer.append(" end ");
			return buffer.toString();
		}

		/**
		 * 获得满足发布公告条件的公告GUID的SQL
		 */
		private String getAutoPublishGuidSql() {

			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_AutoCancelNoticeGuid(@publishTime date, @notReleasestatus string) ");
			buffer.append(" begin ");
			buffer.append(" select t.RECID as RECID, t.tenantsGuid as tenantsGuid ");
			buffer.append(" from SA_NOTICE as t ");
			buffer.append(" where t.publishTime=@publishTime and t.noticeState=@notReleasestatus");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void handle(Context context, TimerNoticeTask task) throws Throwable {
			long publishTime = DateUtil.getDayStartTime(new Date().getTime());
			// 自动发布公告，发送消息
			String autoPublishGuidSql = getAutoPublishGuidSql();
			DBCommand dbCommandPublishGuid = context.prepareStatement(autoPublishGuidSql);
			try {
				dbCommandPublishGuid.setArgumentValue(0, publishTime);
				dbCommandPublishGuid.setArgumentValue(1, NoticeStatus.NotRelease.getKey());
				RecordSet recordSet = dbCommandPublishGuid.executeQuery();
				while (recordSet.next()) {
					context.dispatch(new NoticeStatusChangeEvent(recordSet.getFields().get(0).getGUID(), recordSet.getFields().get(1)
							.getGUID(), NoticeAction.Effectual));
				}
			} finally {
				dbCommandPublishGuid.unuse();
			}
			// 自动发布公告
			String autoPublishSql = getAutoPublishSql();
			DBCommand dbCommandPublish = context.prepareStatement(autoPublishSql);
			try {
				dbCommandPublish.setArgumentValue(0, NoticeStatus.Released.getKey());
				dbCommandPublish.setArgumentValue(1, new Date());
				dbCommandPublish.setArgumentValue(2, publishTime);
				dbCommandPublish.setArgumentValue(3, NoticeStatus.NotRelease.getKey());
				dbCommandPublish.executeUpdate();
			} finally {
				dbCommandPublish.unuse();
			}
		}

	}

	/**
	 * 自动过期公告
	 */
	@Publish
	protected class autoOverdueNotice extends TaskMethodHandler<TimerNoticeTask, TimerNoticeTask.Method> {

		/**
		 *构造方法
		 */
		protected autoOverdueNotice() {
			super(TimerNoticeTask.Method.AUTOOVERDUE);
		}

		/**
		 * 获得自动过期公告SQL
		 */
		private String getAutoOverdueSql() {
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("define update autoOverdueNotice(@overduestatus string, @curDate date, @cancelTime date, @releasedstatus string) ");
			buffer.append(" begin ");
			buffer.append(" update SA_NOTICE as t ");
			buffer.append(" set noticeState=@overduestatus, cancelTime=@curDate ");
			buffer.append(" where t.cancelTime<=@cancelTime and t.noticeState=@releasedstatus and t.cancelTime is not null ");
			buffer.append(" end ");
			return buffer.toString();
		}

		/**
		 * 获得满足过期公告条件的公告GUID的SQL
		 */
		private String getAutoOverdueGuidSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_AutoOverdueNoticeGuid(@cancelTime date, @releasedstatus string) ");
			buffer.append(" begin ");
			buffer.append(" select t.RECID as RECID, t.tenantsGuid as tenantsGuid ");
			buffer.append(" from SA_NOTICE as t ");
			buffer.append(" where t.cancelTime<=@cancelTime and t.noticeState=@releasedstatus and t.cancelTime is not null ");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void handle(Context context, TimerNoticeTask task) throws Throwable {
			// 自动过期公告，发送消息
			String autoOverdueGuidSql = getAutoOverdueGuidSql();
			DBCommand dbCommandOverdueGuid = context.prepareStatement(autoOverdueGuidSql);
			try {
				dbCommandOverdueGuid.setArgumentValue(0, new Date().getTime());
				dbCommandOverdueGuid.setArgumentValue(1, NoticeStatus.Released.getKey());
				RecordSet recordSet = dbCommandOverdueGuid.executeQuery();
				while (recordSet.next()) {
					context.dispatch(new NoticeStatusChangeEvent(recordSet.getFields().get(0).getGUID(), recordSet.getFields().get(1)
							.getGUID(), NoticeAction.Expired));
				}
			} finally {
				dbCommandOverdueGuid.unuse();
			}
			// 自动过期公告
			String autoOverdueSql = getAutoOverdueSql();
			DBCommand dbCommandOverdue = context.prepareStatement(autoOverdueSql);
			try {
				dbCommandOverdue.setArgumentValue(0, NoticeStatus.Overdue.getKey());
				dbCommandOverdue.setArgumentValue(1, new Date());
				dbCommandOverdue.setArgumentValue(2, new Date().getTime());
				dbCommandOverdue.setArgumentValue(3, NoticeStatus.Released.getKey());
				dbCommandOverdue.executeUpdate();
			} finally {
				dbCommandOverdue.unuse();
			}
		}

	}

	/**
	 * 监听事件自动发布公告和自动过期公告
	 */
	@Publish
	protected class autoCancelNotice extends EventListener<TimerEvent> {

		@Override
		protected void occur(Context context, TimerEvent event) throws Throwable {
			// 五分钟后执行
			Thread.sleep(5 * 60 * 1000 + 1);
			// 自动发布公告
			context.asyncHandle(new TimerNoticeTask(), TimerNoticeTask.Method.AUTOPUBLISH);
			// 自动过期公告
			context.asyncHandle(new TimerNoticeTask(), TimerNoticeTask.Method.AUTOOVERDUE);

		}
	}

}
