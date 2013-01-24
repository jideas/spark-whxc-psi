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
 * �������
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 20012 - 20018<br>
 * 
 * 
 * @author ����
 * @version 2012-4-19
 */

public class NoticePublishService extends Service {
	final Orm_Notice ormNotice;

	/**
	 * 
	 *���췽��
	 */
	protected NoticePublishService(Orm_Notice ormNotice) {
		super("com.spark.psi.base.internal.service.NoticePublishService");
		this.ormNotice = ormNotice;
	}

	/**
	 * ��������
	 */
	@Publish
	protected class addNotice extends TaskMethodHandler<SaveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method> {

		/**
		 *���췽��
		 */
		protected addNotice() {
			super(SaveOrUpdateNoticeTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, SaveOrUpdateNoticeTask task) throws Throwable {
			// ��õ�¼�û�
			Employee employee = context.find(Employee.class);
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				NoticeImpl notice = new NoticeImpl();
				// ����GUID
				notice.setRECID(task.getRECID());
				// �⻧GUID
				notice.setTenantsGuid(employee.getTenantId());
				// ����GUID
				notice.setDeptGuid(employee.getDepartmentId());
				// ������Χ
				notice.setDeptNameStr(task.getDeptNameStr());
				// ������GUID
				notice.setCreateGuid(employee.getId());
				// ������
				notice.setCreatePerson(employee.getName());
				// �������
				notice.setNoticeTitle(task.getNoticeTitle());
				if (StringHelper.isNotEmpty(task.getNoticeTitle())) {
					// �������ƴ��
					notice.setNoticeTitlePY(PinyinHelper.getLetter(task.getNoticeTitle().trim()));
				}
				// ����ʱ��
				notice.setCancelTime(new Date().getTime());
				// ����ʱ��
				notice.setPublishTime(task.getPublishTime());
				// ����ʱ��
				notice.setCancelTime(task.getCancelTime());
				// �Ƿ��ö�
				notice.setIsTop(task.getIsTop());
				// ����״̬
				notice.setNoticeStatus(NoticeStatus.NotRelease.getKey());
				// ��������
				notice.setNoticeContent(task.getNoticeContent());
				// ����ʱ��
				notice.setCreateTime(task.getCreateDate());
				// ��������
				ormAccessor.insert(notice);
				// ���������빫���ϵ
				addNoticeDept(context, employee.getTenantId(), notice.getRECID(), task.getDeptGuidList());
				// ��������������
				if (DateUtil.isCurrentDay(task.getPublishTime())) {
					context.handle(new PublishNoticeTask(notice.getRECID()));
				}

			} finally {
				ormAccessor.unuse();
			}

		}

	}

	/**
	 * ���¹���
	 */
	@Publish
	protected class updateNotice extends TaskMethodHandler<SaveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method> {

		/**
		 *���췽��
		 */
		protected updateNotice() {
			super(SaveOrUpdateNoticeTask.Method.UPDATE);
		}

		@Override
		protected void handle(Context context, SaveOrUpdateNoticeTask task) throws Throwable {
			// ȡ��¼�û�
			Login login = context.find(Login.class);
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// ����GUID��ѯ����
				NoticeImpl notice = ormAccessor.findByRECID(task.getRECID());
				if (null != notice) {
					// GUID
					notice.setRECID(task.getRECID());
					// ������Χ
					notice.setDeptNameStr(task.getDeptNameStr());
					// �������
					notice.setNoticeTitle(task.getNoticeTitle());
					if (StringHelper.isNotEmpty(task.getNoticeTitle())) {
						// �������ƴ��
						notice.setNoticeTitlePY(PinyinHelper.getLetter(task.getNoticeTitle().trim()));
					}
					// ����ʱ��
					notice.setPublishTime(task.getPublishTime());
					// ����ʱ��
					notice.setCancelTime(task.getCancelTime());
					// �Ƿ��ö�
					notice.setIsTop(task.getIsTop());
					// ��������
					notice.setNoticeContent(task.getNoticeContent());
					// ����ʱ��
					notice.setCreateTime(task.getCreateDate());
					// ���¹���
					ormAccessor.update(notice);
					// ���ݹ���GUIDɾ�������빫���ϵ
					DeleteNoticeDeptByNoticeTask deleteNoticeDeptByNoticeTask = new DeleteNoticeDeptByNoticeTask();
					deleteNoticeDeptByNoticeTask.setNoticeGUID(task.getRECID());
					context.handle(deleteNoticeDeptByNoticeTask);
					// ���������빫���м��
					addNoticeDept(context, login.getTenantId(), task.getRECID(), task.getDeptGuidList());
					// ��������������
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
	 * ɾ������
	 */
	@Publish
	protected class deleteNotice extends SimpleTaskMethodHandler<DeleteNoticeTask> {

		@Override
		protected void handle(Context context, DeleteNoticeTask task) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// ɾ������
				ormAccessor.delete(task.getRECID());
				// ���ݹ���GUIDɾ�������빫���ϵ
				DeleteNoticeDeptByNoticeTask deleteNoticeDeptByNoticeTask = new DeleteNoticeDeptByNoticeTask();
				deleteNoticeDeptByNoticeTask.setNoticeGUID(task.getRECID());
				context.handle(deleteNoticeDeptByNoticeTask);
			} finally {
				ormAccessor.unuse();
			}

		}

	}

	/**
	 * ��������
	 */
	@Publish
	protected class publishNotice extends SimpleTaskMethodHandler<PublishNoticeTask> {

		@Override
		protected void handle(Context context, PublishNoticeTask task) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// ����GUID��ѯ����
				NoticeImpl notice = ormAccessor.findByRECID(task.getRECID());
				if (null != notice) {
					// ����״̬
					notice.setNoticeStatus(NoticeStatus.Released.getKey());
					// ����ʱ��
					notice.setPublishTime(new Date().getTime());
					ormAccessor.update(notice);
					// ������Ϣ
					context.dispatch(new NoticeStatusChangeEvent(task.getRECID(), notice.getTenantsGuid(), NoticeAction.Effectual));
				}
			} finally {
				ormAccessor.unuse();
			}
		}

	}

	/**
	 * ��������
	 */
	@Publish
	protected class cancelNotice extends SimpleTaskMethodHandler<CancelNoticeTask> {

		@Override
		protected void handle(Context context, CancelNoticeTask task) throws Throwable {
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			try {
				// ����GUID��ѯ����
				NoticeImpl notice = ormAccessor.findByRECID(task.getRECID());
				if (null != notice) {
					// ����״̬
					notice.setNoticeStatus(task.getNoticeStatus());
					// ����ʱ��
					notice.setCancelTime(new Date().getTime());
					ormAccessor.update(notice);
					// ������Ϣ
					context.dispatch(new NoticeStatusChangeEvent(task.getRECID(), notice.getTenantsGuid(), NoticeAction.Expired));
				}
			} finally {
				ormAccessor.unuse();
			}
		}

	}

	/**
	 * ��ѯ�����б�
	 */
	@Publish
	protected class findNoticeItemList extends OneKeyResultListProvider<NoticeItem, FindNoticeItemListKey> {

		/**
		 * ��ò�ѯSQL
		 * 
		 *@param employee
		 *            Ա��
		 *@param key
		 *            ��ѯ�����б�key
		 *@param paramName
		 *            DNA SQL��ѯ������
		 *@param paramValue
		 *            ����ֵ
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
			// �⻧
			buffer.append(" where t.tenantsGuid=@tenantsGuid");
			paramName.append("@tenantsGuid guid");
			paramValue.add(employee.getTenantId());
			// ����
			if (key.getType().equals(FindNoticeItemListKey.NOT_RELEASE)) { // δ����
				buffer.append(" and t.noticeState=@notReleasestatus");
				paramName.append(",@notReleasestatus string");
				paramValue.add(NoticeStatus.NotRelease.getKey());
			} else if (key.getType().equals(FindNoticeItemListKey.RELEASED)) { // �ѷ���
				buffer.append(" and t.noticeState=@releasedstatus");
				paramName.append(",@releasedstatus string");
				paramValue.add(NoticeStatus.Released.getKey());
			} else { // ���������
				buffer.append(" and t.noticeState in (@cancelstatus, @overduestatus)");
				paramName.append(",@cancelstatus string");
				paramName.append(",@overduestatus string");
				paramValue.add(NoticeStatus.Cancel.getKey());
				paramValue.add(NoticeStatus.Overdue.getKey());
			}
			// �����ַ��� �������ģ��ƥ��
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
			// ��Boss���Ҳ�ѯδ��������ʱ��ֻ��ѯ���Ѵ����Ĺ���
			if (!employee.hasAuth(Auth.Boss) && key.getType().equals(FindNoticeItemListKey.NOT_RELEASE)) {
				buffer.append(" and t.createGuid=@createGuid ");
				paramName.append(",@createGuid guid");
				paramValue.add(key.getCreateGuid());
			}
			// ��Boss���Ҳ�ѯ�ѷ�������ʷ����ʱ��ֻ��ѯ�������Ѳ��Ź�������Ѵ����Ĺ���
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
			// �����ʷ�����ҷ���������ʼʱ�䲻Ϊ��
			if (key.getType().equals(FindNoticeItemListKey.HISTORY) && null != key.getQueryTerm()) {
				buffer.append(" and t.publishTime between @beginTime and @endTime ");
				paramName.append(",@beginTime date");
				paramName.append(",@endTime date");
				paramValue.add(key.getQueryTerm().getStartTime());
				paramValue.add(key.getQueryTerm().getEndTime());
			}
			// ����
			if (StringHelper.isNotEmpty(key.getSortCloumName())) {
				buffer.append(" order by t.").append(key.getSortCloumName()).append(" ").append(key.getSortType());
			} else {
				buffer.append(" order by t.isTop desc, t.publishTime desc ");
			}
			return buffer.toString();
		}

		/**
		 * ��ò�ѯDNASQL
		 */
		private String getDnaSql(Employee employee, FindNoticeItemListKey key, List<Object> param) {

			StringBuffer paramName = new StringBuffer();
			// ���SQL
			String sql = getSql(employee, key, paramName, param);
			// ��װDNA SQL
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
			// ��ȡ��¼Ա��
			Employee employee = context.find(Employee.class);
			List<Object> param = new ArrayList<Object>();
			// ���SQL
			String sql = getDnaSql(employee, key, param);
			DBCommand dbCommand = context.prepareStatement(sql);
			NoticeItemImpl noticeItem = null;
			try {
				// ���ò���
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
	 * ��ѯ�������鲢�����Ķ��¼�
	 */
	@Publish
	protected class findPublishedNoticeInfo extends OneKeyResultProvider<NoticeInfo, FindPublishedNoticeInfoKey> {

		@Override
		protected NoticeInfo provide(Context context, FindPublishedNoticeInfoKey key) throws Throwable {
			// ����ͨ�ò�ѯ��������
			FindNoticeInfoKey findNoticeInfoKey = new FindNoticeInfoKey();
			findNoticeInfoKey.setRECID(key.getRECID());
			NoticeInfo noticeInfo = context.get(NoticeInfo.class, findNoticeInfoKey);
			context.dispatch(new NoticeReadEvent(key.getRECID()));
			return noticeInfo;
		}

	}

	/**
	 * ͨ�ò�ѯ��������
	 */
	@Publish
	protected class findNoticeInfo extends OneKeyResultProvider<NoticeInfo, FindNoticeInfoKey> {

		/**
		 * ��ò�ѯSQL
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
					// ��ѯ���淢����ΧGUID�б�
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
	 * ���湫�淢����Χ
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
	 * �Զ���������
	 */
	@Publish
	protected class autoPublishingNotice extends TaskMethodHandler<TimerNoticeTask, TimerNoticeTask.Method> {

		/**
		 *���췽��
		 */
		protected autoPublishingNotice() {
			super(TimerNoticeTask.Method.AUTOPUBLISH);
		}

		/**
		 * ����Զ����������SQL
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
		 * ������㷢�����������Ĺ���GUID��SQL
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
			// �Զ��������棬������Ϣ
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
			// �Զ���������
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
	 * �Զ����ڹ���
	 */
	@Publish
	protected class autoOverdueNotice extends TaskMethodHandler<TimerNoticeTask, TimerNoticeTask.Method> {

		/**
		 *���췽��
		 */
		protected autoOverdueNotice() {
			super(TimerNoticeTask.Method.AUTOOVERDUE);
		}

		/**
		 * ����Զ����ڹ���SQL
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
		 * ���������ڹ��������Ĺ���GUID��SQL
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
			// �Զ����ڹ��棬������Ϣ
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
			// �Զ����ڹ���
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
	 * �����¼��Զ�����������Զ����ڹ���
	 */
	@Publish
	protected class autoCancelNotice extends EventListener<TimerEvent> {

		@Override
		protected void occur(Context context, TimerEvent event) throws Throwable {
			// ����Ӻ�ִ��
			Thread.sleep(5 * 60 * 1000 + 1);
			// �Զ���������
			context.asyncHandle(new TimerNoticeTask(), TimerNoticeTask.Method.AUTOPUBLISH);
			// �Զ����ڹ���
			context.asyncHandle(new TimerNoticeTask(), TimerNoticeTask.Method.AUTOOVERDUE);

		}
	}

}
