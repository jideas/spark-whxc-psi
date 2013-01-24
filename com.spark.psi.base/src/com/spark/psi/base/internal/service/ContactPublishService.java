package com.spark.psi.base.internal.service;

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
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.ormentity.ContactOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Contack;
import com.spark.psi.base.internal.service.query.QD_GetContactBookInfoById;
import com.spark.psi.base.publicimpl.ContactBookInfoImpl;
import com.spark.psi.base.publicimpl.ContactPersonItemImpl;
import com.spark.psi.base.task.resource.UpdateContactPersonResourceTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;
import com.spark.psi.publish.base.contact.key.FindAllContactListKey;
import com.spark.psi.publish.base.contact.key.FindColleagueContactListKey;
import com.spark.psi.publish.base.contact.key.FindCustomerSupplierContactListKey;
import com.spark.psi.publish.base.contact.key.FindMainPersonCountKey;
import com.spark.psi.publish.base.contact.key.FindPersonalContactListKey;
import com.spark.psi.publish.base.contact.task.DeleteContactByParterTask;
import com.spark.psi.publish.base.contact.task.DeleteContactInfoTask;
import com.spark.psi.publish.base.contact.task.UpdateContactInfoTask;

/**
 * 
 * <p>ͨѶ¼�ⲿ�ӿڷ���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-3
 */
public class ContactPublishService extends Service{

	final QD_GetContactBookInfoById qD_GetContactBookInfoById;

	final Orm_Contack orm_Contack;

	/**
	 * 
	 *���췽��
	 *@param qD_GetContactBookInfoById
	 *@param orm_Contack
	 */
	protected ContactPublishService(final QD_GetContactBookInfoById qD_GetContactBookInfoById,
	        final Orm_Contack orm_Contack)
	{
		super("com.spark.psi.base.internal.service.ContactPublishService");
		this.qD_GetContactBookInfoById = qD_GetContactBookInfoById;
		this.orm_Contack = orm_Contack;
	}

	/**
	 * �����ϵ����ϸ��Ϣ
	 * 
	 
	 * 
	 */
	@Publish
	protected class GetContactBookInfoByIdProvider extends OneKeyResultProvider<ContactBookInfo, GUID>{

		@Override
		protected ContactBookInfo provide(Context context, GUID key) throws Throwable{
			RecordSet rs = context.openQuery(qD_GetContactBookInfoById, key);

			if(rs.next()){
				return fullData(context, rs);
			}
			return null;
		}

	}

	/**
	 * ɾ����ϵ��
	 * 
	 
	 * 
	 */
	@Publish
	protected class DeleteContactHandler extends SimpleTaskMethodHandler<DeleteContactInfoTask>{

		@Override
		protected void handle(Context context, DeleteContactInfoTask task) throws Throwable{
			ORMAccessor<ContactOrmEntity> acc = context.newORMAccessor(orm_Contack);
			acc.delete(task.getId());
			context.handle(new UpdateContactPersonResourceTask(task.getId()),
			        UpdateContactPersonResourceTask.Method.Remove);
		}

	}

	/**
	 * ������ϵ����Ϣ
	 * 
	 
	 * 
	 */
	@Publish
	protected class CreateContactHandler extends TaskMethodHandler<UpdateContactInfoTask, UpdateContactInfoTask.Method>
	{

		protected CreateContactHandler(){
			super(UpdateContactInfoTask.Method.Create);
		}

		@Override
		protected void handle(Context context, UpdateContactInfoTask task) throws Throwable{
			ContactOrmEntity entity = new ContactOrmEntity();
			task.setId(GUID.randomID());
			fillOrmEntity(entity, task);
			entity.setType(task.getType().getCode()); // ������ʱ��task�л�ȡ����
			entity.setTenantId(context.find(Tenant.class).getId());
			Employee employee = context.find(Employee.class);
			entity.setCreaetPerson(employee.getName());
			entity.setCreatePersonId(employee.getId());
			entity.setCreateDate(System.currentTimeMillis());
			ORMAccessor<ContactOrmEntity> acc = context.newORMAccessor(orm_Contack);
			acc.insert(entity);
			context.handle(new UpdateContactPersonResourceTask(entity.getId()),
			        UpdateContactPersonResourceTask.Method.Put);
		}
	}

	/**
	 * �޸���ϵ����Ϣ
	 * 
	 
	 * 
	 */
	@Publish
	protected class UpdateContactHandler extends TaskMethodHandler<UpdateContactInfoTask, UpdateContactInfoTask.Method>
	{

		protected UpdateContactHandler(){
			super(UpdateContactInfoTask.Method.Update);
		}

		@Override
		protected void handle(Context context, UpdateContactInfoTask task) throws Throwable{
			ORMAccessor<ContactOrmEntity> acc = context.newORMAccessor(orm_Contack);
			ContactOrmEntity entity = acc.findByRECID(task.getId());
			fillOrmEntity(entity, task);
			acc.update(entity);
			context.handle(new UpdateContactPersonResourceTask(entity.getId()),
			        UpdateContactPersonResourceTask.Method.Modify);
		}
	}

	/**
	 * ������ϵ��Ormʵ��
	 */
	private void fillOrmEntity(ContactOrmEntity entity, UpdateContactInfoTask task){
		entity.setId(task.getId());
		entity.setName(task.getName());
		entity.setNamePy(PinyinHelper.getLetter(task.getName()));
		entity.setMain(task.isMain());
		entity.setSexCode(task.getSexCode());
		entity.setNickName(task.getNickName());
		entity.setMobileNo(task.getMobileNo());
		entity.setLandLineNumber(task.getLandLineNumber());
		entity.setEmail(task.getEmail());
		entity.setCompanyName(task.getCompanyName());
		entity.setCompanyPy(PinyinHelper.getLetter(task.getCompanyName()));
		entity.setDepartment(task.getDepartment());
		entity.setPosition(task.getPosition());
		entity.setPostionPy(PinyinHelper.getLetter(task.getPosition()));
		entity.setQqNumber(task.getQqNumber());
		entity.setMsnNumber(task.getMsnNumber());
		entity.setWwNumber(task.getWwNumber());
		entity.setBirth(task.getBirth());
		entity.setHobbies(task.getHobbies());
		entity.setMemo(task.getRemark());
		entity.setAddress(task.getAddress());
		entity.setPostCode(task.getPostCode());
		entity.setProvince(task.getProvince());
		entity.setCity(task.getCity());
		entity.setDistrict(task.getDistrict());
		entity.setPartnerId(task.getPartnerId());
	}

	public ContactBookInfo fullData(Context context, RecordSet rs){
		int i = 0;
		ContactBookInfoImpl entity = new ContactBookInfoImpl();

		entity.setName(rs.getFields().get(i++).getString());
		String sexCode = rs.getFields().get(i++).getString();
		if(sexCode != null) entity.setSex(context.find(EnumEntity.class, EnumType.Sex, sexCode));
		entity.setNickName(rs.getFields().get(i++).getString());
		entity.setMobileNo(rs.getFields().get(i++).getString());
		entity.setLandLineNumber(rs.getFields().get(i++).getString());
		entity.setEmail(rs.getFields().get(i++).getString());
		entity.setCompany(rs.getFields().get(i++).getString());
		entity.setDepartment(rs.getFields().get(i++).getString());
		entity.setPosition(rs.getFields().get(i++).getString());
		entity.setQqNumber(rs.getFields().get(i++).getString());
		entity.setMsnNumber(rs.getFields().get(i++).getString());
		entity.setWwNumber(rs.getFields().get(i++).getString());
		entity.setBirth(rs.getFields().get(i++).getLong());
		entity.setHobbies(rs.getFields().get(i++).getString());
		entity.setMemo(rs.getFields().get(i++).getString());
		entity.setPartnerId(rs.getFields().get(i++).getGUID());
		String provinceCode = rs.getFields().get(i++).getString();
		if(provinceCode != null) entity.setProvince(context.find(EnumEntity.class, EnumType.Area, provinceCode));
		String cityCode = rs.getFields().get(i++).getString();
		if(cityCode != null) entity.setCity(context.find(EnumEntity.class, EnumType.Area, cityCode));
		String districtCode = rs.getFields().get(i++).getString();
		if(districtCode != null) entity.setDistrict(context.find(EnumEntity.class, EnumType.Area, districtCode));
		entity.setAddress(rs.getFields().get(i++).getString());
		entity.setPostCode(rs.getFields().get(i++).getString());
		entity.setId(rs.getFields().get(i++).getGUID());
		entity.setMain(rs.getFields().get(i++).getBoolean());
		entity.setType(ContactType.getContactTypeByCode(rs.getFields().get(i++).getString()));
		return entity;
	}

	/**
	 * ��ѯ��ǰ������ָ���ͻ�����ָ����ϵ�˵���������ϵ�˵�����
	 */
	@Publish
	protected class GetMainPersonCount extends OneKeyResultProvider<Long, FindMainPersonCountKey>{

		/**
		 * ���SQL
		 */
		private String getSql(){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" define query Qu_MainPersonCount(");
			buffer.append("@tenantsGUID guid, @createPersonGUID guid, @type string, @isMain boolean, @id guid, @cusproGuid guid");
			buffer.append(" ) ");
			buffer.append(" begin ");
			buffer.append(" select count(t.RECID) ");
			buffer.append(" from sa_contackbook as t ");
			buffer.append(" where t.tenantsGUID=@tenantsGUID and t.createPersonGUID=@createPersonGUID ");
			buffer.append(" and t.type = @type and t.main=@isMain and t.cusproGuid=@cusproGuid and t.RECID<>@id ");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Long provide(Context context, FindMainPersonCountKey key) throws Throwable{
			//��ȡ��¼Ա��
			Employee employee = context.find(Employee.class);
			DBCommand dbCommand = context.prepareStatement(getSql());
			//�����ֵ
			dbCommand.setArgumentValue(0, employee.getTenantId());
			dbCommand.setArgumentValue(1, employee.getId());
			dbCommand.setArgumentValue(2, ContactType.Partner.getCode());
			dbCommand.setArgumentValue(3, Boolean.TRUE);
			dbCommand.setArgumentValue(4, key.getCusproGuid());
			dbCommand.setArgumentValue(5, key.getContactPersonGuid());
			Long count = 0L;
			try{
				RecordSet recordSet = dbCommand.executeQuery();
				while(recordSet.next()){
					count = recordSet.getFields().get(0).getLong();
				}
			}
			finally{
				dbCommand.unuse();
			}
			return count;
		}

	}


	/**
	 * ����ͨѶ¼
	 * 
	 */
	@Publish
	protected class UpdateContactBook extends TaskMethodHandler<UpdateContactInfoTask, UpdateContactInfoTask.Method>{

		protected UpdateContactBook(){
			super(UpdateContactInfoTask.Method.UpdateBook);
		}
		
		/**
		 * ����޸�SQL
		 */
		private String getUpdateSql(){
			StringBuffer buffer = new StringBuffer();
			buffer.append("define update updateMainPersonstatus(");
			buffer.append("@tenantsGUID guid, @createPersonGUID guid, @type string, @isMain boolean");
			buffer.append(", @notMain boolean, @cusproGuid guid,  @id guid");
			buffer.append(" ) ");
			buffer.append(" begin ");
			buffer.append(" update sa_contackbook as t ");
			buffer.append(" set main=@notMain");
			buffer.append(" where t.tenantsGUID=@tenantsGUID and t.createPersonGUID=@createPersonGUID ");
			buffer.append(" and t.type = @type and t.main=@isMain and t.cusproGuid=@cusproGuid and t.RECID<>@id ");
			buffer.append(" end ");
			return buffer.toString();
		}
		
		/**
		 * �޸ĵ�ǰ�����ߵ���������ϵ��Ϊ������ϵ��
		 */
		private void updateMainPersonstatus(Context context, Employee employee, ContactOrmEntity entity){
			DBCommand dbCommand = context.prepareStatement(getUpdateSql());
			//�����ֵ
			dbCommand.setArgumentValue(0, employee.getTenantId());
			dbCommand.setArgumentValue(1, employee.getId());
			dbCommand.setArgumentValue(2, ContactType.Partner.getCode());
			dbCommand.setArgumentValue(3, Boolean.TRUE);
			dbCommand.setArgumentValue(4, Boolean.FALSE);
			dbCommand.setArgumentValue(5, entity.getPartnerId());
			dbCommand.setArgumentValue(6, entity.getId());
			try{
				dbCommand.executeUpdate();
			}
			finally{
				dbCommand.unuse();
			}
		}
		
//		/**
//		 * ������Դ�Ƿ�����ϵ��״̬
//		 */
//		private void updateResourceMainPersonstatus(Context context, ContactOrmEntity entity){
//			Partner partner = context.find(Partner.class,entity.getPartnerId());
//			if(null != partner && null != partner.getContactPersons() && partner.getContactPersons().length>0){
//				for(GUID id : partner.getContactPersons()){
//					context.handle(new UpdateContactPersonResourceTask(id),
//							UpdateContactPersonResourceTask.Method.Modify);
//				}
//				
//			}
//		}
		

		@Override
		protected void handle(Context context, UpdateContactInfoTask task) throws Throwable{
			ORMAccessor<ContactOrmEntity> acc = context.newORMAccessor(orm_Contack);
			ContactOrmEntity entity = acc.findByRECID(task.getId());
			if(null == entity){
				return;
			}
			entity.setName(task.getName());
			entity.setNamePy(PinyinHelper.getLetter(task.getName()));
			entity.setMain(task.isMain());
			entity.setSexCode(task.getSexCode());
			entity.setNickName(task.getNickName());
			entity.setMobileNo(task.getMobileNo());
			entity.setLandLineNumber(task.getLandLineNumber());
			entity.setEmail(task.getEmail());
			entity.setDepartment(task.getDepartment());
			entity.setPosition(task.getPosition());
			entity.setPostionPy(PinyinHelper.getLetter(task.getPosition()));
			entity.setQqNumber(task.getQqNumber());
			entity.setMsnNumber(task.getMsnNumber());
			entity.setWwNumber(task.getWwNumber());
			entity.setBirth(task.getBirth());
			entity.setHobbies(task.getHobbies());
			entity.setMemo(task.getRemark());
			acc.update(entity);
			//��Ϊ����ϵ��
			if(task.isMain()){
				//����ȡ����ǰ�����ߵĵ�ǰ�ͻ�����������ϵ��
				updateMainPersonstatus(context, context.find(Employee.class), entity);
				//���µ�ǰ�ͻ�������ϵ����Դ
//				updateResourceMainPersonstatus(context, entity);
			}else{
				//���µ�ǰ��ϵ����Դ
				context.handle(new UpdateContactPersonResourceTask(entity.getId()),
						UpdateContactPersonResourceTask.Method.Modify);
			}
		}
	}

	/**
	 * ��ѯͨѶ¼(����)�б�
	 */
	@Publish
	protected class GetPersonalContactList extends
	        OneKeyResultListProvider<ContactPersonItem, FindPersonalContactListKey>
	{

		/**
		 * ��ò�ѯSQL
		 */
		private String getDnaSql(FindPersonalContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer
			        .append(" define query Qu_PersonalContactList(@tenantsGUID guid, @createPersonGUID guid, @type string, @serarchText string) ");
			buffer.append(" begin ");
			buffer.append(" select ");
			buffer.append(" t.RECID as id, ");
			buffer.append(" t.name as name, ");
			buffer.append(" t.company + case when t.department is not null then '(' + t.department + ')' end as department, ");
			buffer.append(" t.job as job, ");
			buffer.append(" t.phone as phone, ");
			buffer.append(" t.mobile as mobile, ");
			buffer.append(" t.email as email ");
			buffer.append(" from sa_contackbook as t ");
			buffer.append(" where t.tenantsGUID=@tenantsGUID and t.createPersonGUID=@createPersonGUID ");
			buffer.append(" and t.type = @type ");
			//ƴ������
			if(StringHelper.isNotEmpty(key.getPhonetics())){
				buffer.append(" and	(");
				for (int i = 0, size=key.getPhonetics().length(); i < size; i++) {
					if (i > 0) {
						buffer.append(" or ");
					}
					buffer.append("t.namePY like '").append(
							key.getPhonetics().charAt(i)).append("%' ");
				}
				buffer.append(") ");
			}
			//��������Ϊ�գ�ģ��ƥ����������λ�������绰������
			if(StringHelper.isNotEmpty(key.getSearchText())){
				buffer.append("and	(");
				buffer.append(" t.name like '%'+@serarchText+'%' ");
				buffer.append(" or t.company like '%'+@serarchText+'%' ");
				buffer.append(" or t.phone like '%'+@serarchText+'%' ");
				buffer.append(" or t.mobile like '%'+@serarchText+'%' ");
				buffer.append(" or t.email like '%'+@serarchText+'%' ");
				buffer.append(") ");
			}
			//����
			if(StringHelper.isNotEmpty(key.getSortCloumName())){
				buffer.append(" order by t.").append(key.getSortCloumName()).append(" ").append(key.getSortType());
			}
			else{
				buffer.append(" order by t.namePY asc ");
			}
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void provide(Context context, FindPersonalContactListKey key, List<ContactPersonItem> resultList)
		        throws Throwable
		{
			//��ȡ��¼Ա��
			Employee employee = context.find(Employee.class);
			//���SQL
			String sql = getDnaSql(key);
			DBCommand dbCommand = context.prepareStatement(sql);
			//�����ֵ
			dbCommand.setArgumentValue(0, employee.getTenantId());
			dbCommand.setArgumentValue(1, employee.getId());
			dbCommand.setArgumentValue(2, ContactType.Personal.getCode());
			dbCommand.setArgumentValue(3, key.getSearchText());
			ContactPersonItemImpl contactPersonItemImpl = null;
			try{
				RecordSet recordSet = dbCommand.executeQueryLimit(key.getOffset(), key.getCount());
				while(recordSet.next()){
					contactPersonItemImpl = new ContactPersonItemImpl();
					contactPersonItemImpl.setId(recordSet.getFields().get(0).getGUID());
					contactPersonItemImpl.setName(recordSet.getFields().get(1).getString());
					contactPersonItemImpl.setDepartment(recordSet.getFields().get(2).getString());
					contactPersonItemImpl.setJob(recordSet.getFields().get(3).getString());
					contactPersonItemImpl.setPhone(recordSet.getFields().get(4).getString());
					contactPersonItemImpl.setMobile(recordSet.getFields().get(5).getString());
					contactPersonItemImpl.setEmail(recordSet.getFields().get(6).getString());
					resultList.add(contactPersonItemImpl);
				}
			}
			finally{
				dbCommand.unuse();
			}
		}

	}

	/**
	 * ��ѯͨѶ¼(�ͻ���Ӧ��)�б�
	 */
	@Publish
	protected class getCustomerContactList extends
	        OneKeyResultListProvider<ContactPersonItem, FindCustomerSupplierContactListKey>
	{

		/**
		 * ��ò�ѯSQL
		 */
		private String getDnaSql(Employee employee, FindCustomerSupplierContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" define query Qu_CustomerSupplierContactList ");
			buffer.append(" ( ");
			buffer.append("  @tenantsGuid guid");
			buffer.append(", @deptGuid guid");
			buffer.append(", @createPersonGuid guid");
			buffer.append(", @type string");
			buffer.append(", @cusproType string");
			buffer.append(", @serarchText string");
			buffer.append(" ) ");
			buffer.append(" begin ");
			buffer.append(" select ");
			buffer.append(" 	t.RECID as id, ");
			buffer.append(" 	t.name as name, ");
			buffer.append(" 	t.company + case when t.department is not null then '(' + t.department + ')' end as department, ");
			buffer.append(" 	t.job as job, ");
			buffer.append(" 	t.phone as phone, ");
			buffer.append(" 	t.mobile as mobile, ");
			buffer.append(" 	t.email as email ");
			buffer.append(" from sa_contackbook as t ");
			buffer.append(" join sa_cusprovider_info as c on t.cusproGuid = c.RECID ");
			buffer.append(" join sa_em_employee as e on c.busPerson = e.RECID ");
			buffer.append(" where t.tenantsGUID = @tenantsGuid and t.type = @type ");
			buffer.append(" and c.tenantsGUID = @tenantsGuid and c.cusproType = @cusproType");
			//�ͻ�
			if(key.getPartnerType().getCode().equals(PartnerType.Customer.getCode())){
				if(!employee.hasAuth(Auth.Boss)){ //��boss
					if(employee.hasAuth(Auth.SalesManager))
					{ //������Բ鿴�������������Լ����ѵĿͻ�
						buffer.append(" and ( ");
						buffer.append(" c.busPerson is null "); //����ͻ�(������Ϊ�ձ�ʾΪ����ͻ�)
						buffer.append(" or c.busPerson = @createPersonGuid "); //�������ѵĿͻ�
						buffer.append(" or e.departmentid in( "); //���������Ŀͻ�
						buffer.append(" 	select k.RECID as RECID from SA_CORE_TREE as s join SA_CORE_TREE as k ");
						buffer.append(" 	on (k.PATH > s.PATH and k.PATH < s.PATH || bytes'ff' ) ");
						buffer.append(" 	where s.RECID = @deptGuid ");
						buffer.append(" )) ");
					}
					else if(employee.hasAuth(Auth.Sales)){ //Ա��ֻ�鿴���ѵĿͻ�
						buffer.append(" and c.busperson = @createPersonGuid");
					}
				}
			}
			//ƴ������
			if(StringHelper.isNotEmpty(key.getPhonetics())){
				buffer.append(" and	(");
				for (int i = 0, size=key.getPhonetics().length(); i < size; i++) {
					if (i > 0) {
						buffer.append(" or ");
					}
					buffer.append("t.namePY like '").append(
							key.getPhonetics().charAt(i)).append("%' ");
				}
				buffer.append(") ");
			}
			//��������Ϊ�գ�ģ��ƥ����������λ�������绰������
			if(StringHelper.isNotEmpty(key.getSearchText())){
				buffer.append(" and (");
				buffer.append(" t.name like '%'+@serarchText+'%' ");
				buffer.append(" or t.company like '%'+@serarchText+'%' ");
				buffer.append(" or t.phone like '%'+@serarchText+'%' ");
				buffer.append(" or t.mobile like '%'+@serarchText+'%' ");
				buffer.append(" or t.email like '%'+@serarchText+'%' ");
				buffer.append(") ");
			}
			//����
			if(StringHelper.isNotEmpty(key.getSortCloumName())){
				buffer.append(" order by t.").append(key.getSortCloumName()).append(" ").append(key.getSortType());
			}
			else{
				buffer.append(" order by t.namePY asc ");
			}
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void provide(Context context, FindCustomerSupplierContactListKey key,
		        List<ContactPersonItem> resultList) throws Throwable
		{
			//��ȡ��¼Ա��
			Employee employee = context.find(Employee.class);
			//���SQL
			String sql = getDnaSql(employee, key);
			DBCommand dbCommand = context.prepareStatement(sql);
			//�����ֵ
			dbCommand.setArgumentValue(0, employee.getTenantId());
			dbCommand.setArgumentValue(1, employee.getDepartmentId());
			dbCommand.setArgumentValue(2, employee.getId());
			dbCommand.setArgumentValue(3, ContactType.Partner.getCode());
			dbCommand.setArgumentValue(4, key.getPartnerType().getCode());//
			dbCommand.setArgumentValue(5, key.getSearchText());
			ContactPersonItemImpl contactPersonItemImpl = null;
			try{
				RecordSet recordSet = dbCommand.executeQueryLimit(key.getOffset(), key.getCount());
				while(recordSet.next()){
					contactPersonItemImpl = new ContactPersonItemImpl();
					contactPersonItemImpl.setId(recordSet.getFields().get(0).getGUID());
					contactPersonItemImpl.setName(recordSet.getFields().get(1).getString());
					contactPersonItemImpl.setDepartment(recordSet.getFields().get(2).getString());
					contactPersonItemImpl.setJob(recordSet.getFields().get(3).getString());
					contactPersonItemImpl.setPhone(recordSet.getFields().get(4).getString());
					contactPersonItemImpl.setMobile(recordSet.getFields().get(5).getString());
					contactPersonItemImpl.setEmail(recordSet.getFields().get(6).getString());
					resultList.add(contactPersonItemImpl);
				}
			}
			finally{
				dbCommand.unuse();
			}
		}

	}

	/**
	 * ��ѯͨѶ¼(ͬ��)�б�
	 */
	@Publish
	protected class GetCollContactList extends OneKeyResultListProvider<ContactPersonItem, FindColleagueContactListKey>
	{

		/**
		 * ��ѯͬ��SQL
		 */
		private String getCollSql(FindColleagueContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select ");
			buffer.append(" t.RECID as id, ");
			buffer.append(" t.empname as name, ");
			buffer.append(" d.name as department, ");
			buffer.append(" t.duty as job, ");
			buffer.append(" t.phone as phone, ");
			buffer.append(" t.mobilephone as mobile, ");
			buffer.append(" t.email as email, ");
			buffer.append(" t.pyempname as namePY, ");
			buffer.append(" t.createdate as createdate ");
			buffer.append(" from sa_em_employee as t ");
			buffer.append(" left join sa_personnel_dept as d on d.RECID=t.departmentid ");
			buffer.append(" where t.tenantsGUID=@tenantsGuid and t.RECID <> @employeeGuid");
			//ƴ������
			if(StringHelper.isNotEmpty(key.getPhonetics())){
				buffer.append(" and (");
				for (int i = 0, size=key.getPhonetics().length(); i < size; i++) {
					if (i > 0) {
						buffer.append(" or ");
					}
					buffer.append("t.pyempname like '").append(
							key.getPhonetics().charAt(i)).append("%' ");
				}
				buffer.append(") ");
			}
			//��������Ϊ�գ�ģ��ƥ����������λ�������绰������
			if(StringHelper.isNotEmpty(key.getSearchText())){
				buffer.append(" and ( ");
				buffer.append(" t.empname like '%'+@serarchText+'%' ");
				buffer.append(" or d.name like '%'+@serarchText+'%' ");
				buffer.append(" or t.phone like '%'+@serarchText+'%' ");
				buffer.append(" or t.mobilephone like '%'+@serarchText+'%' ");
				buffer.append(" or t.email like '%'+@serarchText+'%' ");
				buffer.append(") ");
			}
			return buffer.toString();
		}

		/**
		 * ��ò�ѯSQL
		 */
		private String getDnaSql(FindColleagueContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer
			        .append(" define query Qu_PersonalContactList(@tenantsGuid guid, @employeeGuid guid, @serarchText string) ");
			buffer.append(" begin ");
			buffer.append(" select ");
			buffer.append(" coll.id as id, ");
			buffer.append(" coll.name as name, ");
			buffer.append(" coll.department as department, ");
			buffer.append(" coll.job as job, ");
			buffer.append(" coll.phone as phone, ");
			buffer.append(" coll.mobile as mobile, ");
			buffer.append(" coll.email as email ");
			buffer.append(" from (");
			buffer.append(getCollSql(key));
			buffer.append(") as coll ");
			//����
			if(StringHelper.isNotEmpty(key.getSortCloumName())){
				buffer.append(" order by coll.").append(key.getSortCloumName()).append(" ").append(key.getSortType());
			}
			else{
				buffer.append(" order by coll.namePY asc ");
			}
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void provide(Context context, FindColleagueContactListKey key, List<ContactPersonItem> resultList)
		        throws Throwable
		{
			//��ȡ��¼Ա��
			Employee employee = context.find(Employee.class);
			//���SQL
			String sql = getDnaSql(key);
			DBCommand dbCommand = context.prepareStatement(sql);
			//�����ֵ
			dbCommand.setArgumentValue(0, employee.getTenantId());
			dbCommand.setArgumentValue(1, employee.getId());
			dbCommand.setArgumentValue(2, key.getSearchText());
			ContactPersonItemImpl contactPersonItemImpl = null;
			try{
				RecordSet recordSet = dbCommand.executeQueryLimit(key.getOffset(), key.getCount());
				while(recordSet.next()){
					contactPersonItemImpl = new ContactPersonItemImpl();
					contactPersonItemImpl.setId(recordSet.getFields().get(0).getGUID());
					contactPersonItemImpl.setName(recordSet.getFields().get(1).getString());
					contactPersonItemImpl.setDepartment(recordSet.getFields().get(2).getString());
					contactPersonItemImpl.setJob(recordSet.getFields().get(3).getString());
					contactPersonItemImpl.setPhone(recordSet.getFields().get(4).getString());
					contactPersonItemImpl.setMobile(recordSet.getFields().get(5).getString());
					contactPersonItemImpl.setEmail(recordSet.getFields().get(6).getString());
					resultList.add(contactPersonItemImpl);
				}
			}
			finally{
				dbCommand.unuse();
			}
		}

	}

	/**
	 * ��ѯͨѶ¼(ȫ��)�б�
	 */
	@Publish
	protected class GetAllContactList extends OneKeyResultListProvider<ContactPersonItem, FindAllContactListKey>{

		/**
		 * �����ϵ��Sql
		 */
		private String getContactPersonSql(Employee employee, FindAllContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select ");
			buffer.append(" 	t.RECID as id, ");
			buffer.append(" 	t.name as name, ");
			buffer.append(" 	t.company + case when t.department is not null then '(' + t.department + ')' end as department, ");
			buffer.append(" 	t.job as job, ");
			buffer.append(" 	t.phone as phone, ");
			buffer.append(" 	t.mobile as mobile, ");
			buffer.append(" 	t.email as email, ");
			buffer.append(" 	t.namePY as namePY, ");
			buffer.append(" 	t.createDate as createDate, ");
			buffer.append(ContactPersonItem.CONTACT_PERSON + " as type ");
			buffer.append(" from sa_contackbook as t ");
			buffer.append(" left join sa_cusprovider_info as c on t.cusproGuid = c.RECID ");
			buffer.append(" left join sa_em_employee as e on c.busPerson = e.RECID ");
			buffer.append(" where t.tenantsGUID = @tenantsGuid ");
			buffer.append(" and (");
			//����
			buffer.append(" (t.createPersonGUID = @createPersonGuid and t.type = @personalType) ");
			//�ͻ�
			if(employee.hasAuth(Auth.Tag_ContactMange_Customer)){
				buffer.append(" or (t.type = @partnerType and c.tenantsGUID = @tenantsGuid and c.cusproType = @customerType ");
				if(!employee.hasAuth(Auth.Boss)){ //��boss
					if(employee.hasAuth(Auth.SalesManager))
					{ //������Բ鿴�������������Լ����ѵĿͻ�
						buffer.append(" and ( ");
						buffer.append(" c.busPerson is null "); //����ͻ�(������Ϊ�ձ�ʾΪ����ͻ�)
						buffer.append(" or c.busPerson = @createPersonGuid "); //�������ѵĿͻ�
						buffer.append(" or e.departmentid in( "); //���������Ŀͻ�
						buffer.append(" 	select k.RECID as RECID from SA_CORE_TREE as s join SA_CORE_TREE as k ");
						buffer.append(" 	on (k.PATH > s.PATH and k.PATH < s.PATH || bytes'ff' ) ");
						buffer.append(" 	where s.RECID = @deptGuid ");
						buffer.append(" )) ");
					}
					else if(employee.hasAuth(Auth.Sales)){ //Ա��ֻ�鿴���ѵĿͻ�
						buffer.append(" and c.busperson = @createPersonGuid");
					}
				}
				buffer.append(" ) ");
			}
			//��Ӧ��
			if(employee.hasAuth(Auth.Tab_ContactMange_Purchase)){
				buffer.append(" or (t.type = @partnerType and c.tenantsGUID = @tenantsGuid and c.cusproType = @supplierType) ");
			}
			buffer.append(" ) ");
			//ƴ������
			if(StringHelper.isNotEmpty(key.getPhonetics())){
				buffer.append(" and	(");
				for (int i = 0, size=key.getPhonetics().length(); i < size; i++) {
					if (i > 0) {
						buffer.append(" or ");
					}
					buffer.append("t.namePY like '").append(
							key.getPhonetics().charAt(i)).append("%' ");
				}
				buffer.append(") ");
			}
			//��������Ϊ�գ�ģ��ƥ����������λ�������绰������
			if(StringHelper.isNotEmpty(key.getSearchText())){
				buffer.append(" and (");
				buffer.append(" t.name like '%'+@serarchText+'%' ");
				buffer.append(" or t.company like '%'+@serarchText+'%' ");
				buffer.append(" or t.phone like '%'+@serarchText+'%' ");
				buffer.append(" or t.mobile like '%'+@serarchText+'%' ");
				buffer.append(" or t.email like '%'+@serarchText+'%' ");
				buffer.append(") ");
			}
			return buffer.toString();
		}

		/**
		 * ���ͬ��SQL
		 */
		private String getCollSql(FindAllContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select ");
			buffer.append(" t.RECID as id, ");
			buffer.append(" t.empname as name, ");
			buffer.append(" d.name as department, ");
			buffer.append(" t.duty as job, ");
			buffer.append(" t.phone as phone, ");
			buffer.append(" t.mobilephone as mobile, ");
			buffer.append(" t.email as email, ");
			buffer.append(" t.pyempname as namePY, ");
			buffer.append(" t.createdate as createDate, ");
			buffer.append(ContactPersonItem.COLLEAGUE + " as type ");
			buffer.append(" from sa_em_employee as t ");
			buffer.append(" left join sa_personnel_dept as d on d.RECID=t.departmentid ");
			buffer.append(" where t.tenantsGUID = @tenantsGuid and t.RECID <> @createPersonGuid ");
			//ƴ������
			if(StringHelper.isNotEmpty(key.getPhonetics())){
				buffer.append(" and	(");
				for (int i = 0, size=key.getPhonetics().length(); i < size; i++) {
					if (i > 0) {
						buffer.append(" or ");
					}
					buffer.append("t.pyempname like '").append(
							key.getPhonetics().charAt(i)).append("%' ");
				}
				buffer.append(") ");
			}
			//��������Ϊ�գ�ģ��ƥ����������λ�������绰������
			if(StringHelper.isNotEmpty(key.getSearchText())){
				buffer.append(" and (");
				buffer.append(" t.empname like '%'+@serarchText+'%' ");
				buffer.append(" or d.name like '%'+@serarchText+'%' ");
				buffer.append(" or t.phone like '%'+@serarchText+'%' ");
				buffer.append(" or t.mobilephone like '%'+@serarchText+'%' ");
				buffer.append(" or t.email like '%'+@serarchText+'%' ");
				buffer.append(") ");
			}
			return buffer.toString();
		}

		/**
		 * ��ò�ѯSQL
		 */
		private String getDnaSql(Employee employee, FindAllContactListKey key){
			StringBuffer buffer = new StringBuffer();
			buffer.append(" define query Qu_PersonalContactList ");
			buffer.append(" ( ");
			buffer.append("  @tenantsGuid guid");
			buffer.append(", @deptGuid guid");
			buffer.append(", @createPersonGuid guid");
			buffer.append(", @personalType string");
			buffer.append(", @partnerType string");
			buffer.append(", @serarchText string");
			buffer.append(", @customerType string");
			buffer.append(", @supplierType string");
			buffer.append(" ) ");
			buffer.append(" begin ");
			buffer.append(" select contact.id as id ");
			buffer.append(", contact.name as name ");
			buffer.append(", contact.department as department ");
			buffer.append(", contact.job as job ");
			buffer.append(", contact.phone as phone ");
			buffer.append(", contact.mobile as mobile ");
			buffer.append(", contact.email as email ");
			buffer.append(", contact.type as type ");
			buffer.append(" from (");
			buffer.append(getCollSql(key));//ͬ��
			buffer.append(" union all ");
			buffer.append(getContactPersonSql(employee, key));//��ϵ��
			buffer.append(" ) as contact ");
			//����
			if(StringHelper.isNotEmpty(key.getSortCloumName())){
				buffer.append(" order by contact.").append(key.getSortCloumName()).append(" ")
				        .append(key.getSortType());
			}
			else{
				buffer.append(" order by contact.namePY asc ");
			}
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected void provide(Context context, FindAllContactListKey key, List<ContactPersonItem> resultList)
		        throws Throwable
		{
			//��ȡ��¼Ա��
			Employee employee = context.find(Employee.class);
			//���SQL
			String sql = getDnaSql(employee, key);
			DBCommand dbCommand = context.prepareStatement(sql);
			//�����ֵ
			dbCommand.setArgumentValue(0, employee.getTenantId());
			dbCommand.setArgumentValue(1, employee.getDepartmentId());
			dbCommand.setArgumentValue(2, employee.getId());
			dbCommand.setArgumentValue(3, ContactType.Personal.getCode());
			dbCommand.setArgumentValue(4, ContactType.Partner.getCode());//
			dbCommand.setArgumentValue(5, key.getSearchText());
			dbCommand.setArgumentValue(6, PartnerType.Customer.getCode());
			dbCommand.setArgumentValue(7, PartnerType.Supplier.getCode());
			ContactPersonItemImpl contactPersonItemImpl = null;
			try{
				RecordSet recordSet = dbCommand.executeQueryLimit(key.getOffset(), key.getCount());
				while(recordSet.next()){
					contactPersonItemImpl = new ContactPersonItemImpl();
					contactPersonItemImpl.setId(recordSet.getFields().get(0).getGUID());
					contactPersonItemImpl.setName(recordSet.getFields().get(1).getString());
					contactPersonItemImpl.setDepartment(recordSet.getFields().get(2).getString());
					contactPersonItemImpl.setJob(recordSet.getFields().get(3).getString());
					contactPersonItemImpl.setPhone(recordSet.getFields().get(4).getString());
					contactPersonItemImpl.setMobile(recordSet.getFields().get(5).getString());
					contactPersonItemImpl.setEmail(recordSet.getFields().get(6).getString());
					contactPersonItemImpl.setType(recordSet.getFields().get(7).getString());
					resultList.add(contactPersonItemImpl);
				}
			}
			finally{
				dbCommand.unuse();
			}
		}

	}
	
	/**
	 * ɾ�����пͻ���Ӧ�̶�Ӧ����ϵ��
	 */
	@Publish
	protected final class DeleteContactByParterHandler extends SimpleTaskMethodHandler<DeleteContactByParterTask>{

		/**
    	 * ���SQL
    	 */
    	public String getSql(){
    		StringBuffer buffer = new StringBuffer();
    		buffer.append("define delete del_contact(@tenantsGuid guid, @cusproType string, @partner string, @delivery string) ");
    		buffer.append(" begin ");
    		buffer.append(" delete from sa_contackbook as t where t.tenantsGUID=@tenantsGuid and t.type in (@partner, @delivery)");
    		buffer.append(" and t.cusproGuid in (select s.recid as recid from sa_cusprovider_info as s where s.tenantsGuid=@tenantsGuid and s.cusproType=@cusproType)");
    		buffer.append(" end ");
    		return buffer.toString();
    	}
		
		@Override
        protected void handle(Context context, DeleteContactByParterTask task) throws Throwable{
			DBCommand dbCommand = context.prepareStatement(getSql());
			dbCommand.setArgumentValue(0, context.get(Login.class).getTenantId());
			dbCommand.setArgumentValue(1, task.getPartnerType().getCode());
			dbCommand.setArgumentValue(2, ContactType.Partner.getCode());
			dbCommand.setArgumentValue(3, ContactType.Delivery.getCode());
	        try{
	        	dbCommand.executeUpdate();
	        }finally{
	        	dbCommand.unuse();
	        }
        }
		
	}

}
