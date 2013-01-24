/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-1    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-1    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ContactPerson;
import com.spark.psi.base.internal.service.query.QD_GetAllContactResourceList;
import com.spark.psi.base.internal.service.query.QD_GetContactPersonResourceById;
import com.spark.psi.base.task.resource.UpdateContactPersonResourceTask;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.base.internal.service.ContactInternalService.ContactPersonImpl;
/**
 * <p>��ϵ����Դ����</p>
 *


 *
 
 * @version 2012-4-1
 */

public class ContactInternalService extends
        ResourceService<ContactPerson, ContactPersonImpl, ContactPersonImpl>
{

	final QD_GetAllContactResourceList qD_GetAllContactResourceList;
	
	final QD_GetContactPersonResourceById qD_GetContactPersonResourceById;

	protected ContactInternalService(
			final QD_GetAllContactResourceList qD_GetAllContactResourceList,
			final QD_GetContactPersonResourceById qD_GetContactPersonResourceById){
	    super("��ϵ����Դ����",ResourceKind.SINGLETON_IN_CLUSTER);
	    this.qD_GetAllContactResourceList = qD_GetAllContactResourceList;	
	    this.qD_GetContactPersonResourceById = qD_GetContactPersonResourceById;
    }
	
	@Override
	protected void initResources(Context context,
			ResourceInserter<ContactPerson, ContactPersonImpl, ContactPersonImpl> initializer){
		List<ContactPersonImpl> list = getContactPersons(context);
		for(ContactPersonImpl contactPerson : list){
	        initializer.putResource(contactPerson);
        }
	}
	
	final class GetContactBookById extends OneKeyResourceProvider<GUID>{

		@Override
        protected GUID getKey1(ContactPersonImpl keysHolder){
	        return keysHolder.getId();
        }
		
	}
	
	
	private List<ContactPersonImpl> getContactPersons(Context context){
		List<ContactPersonImpl> result = new ArrayList<ContactPersonImpl>();
		RecordSet rs = context.openQuery(qD_GetAllContactResourceList);
		while(rs.next()){
			result.add(fullContactData(rs, context));
		}
		return result;
	}
	
	private ContactPersonImpl getContactPersonById(Context context,GUID id)
    {
       	RecordSet rs = context.openQuery(qD_GetContactPersonResourceById, id);
       	if(rs.next()){
       		return fullContactData(rs, context);
       	}
        return null;
    }
		
	
	private ContactPersonImpl fullContactData(RecordSet rs,Context context){
		ContactPersonImpl entity = new ContactPersonImpl();
		int i=0;	
		entity.setId(rs.getFields().get(i++).getGUID());
		entity.setName(rs.getFields().get(i++).getString());
		entity.setMobileNo(rs.getFields().get(i++).getString());
		entity.setLandLineNumber(rs.getFields().get(i++).getString());
		entity.setPartnerId(rs.getFields().get(i++).getGUID());
		String province = rs.getFields().get(i++).getString();
		if(province!=null){
			entity.setProvince(context.find(EnumEntity.class,province));
		}
		entity.setDepartment(rs.getFields().get(i++).getString());
		String city = rs.getFields().get(i++).getString();
		if(city!=null){
			entity.setProvince(context.find(EnumEntity.class,city));
		}
		String district = rs.getFields().get(i++).getString();
		if(district!=null){
			entity.setProvince(context.find(EnumEntity.class,district));
		}
		entity.setAddress(rs.getFields().get(i++).getString());
		entity.setPostCode(rs.getFields().get(i++).getString());
		i++;
		entity.setEmail(rs.getFields().get(i++).getString());
		entity.setLastDate(rs.getFields().get(i++).getDate());
		return entity;		
	}
	
	
	@Publish
	protected final class PutContactPersonResourceHandler extends TaskMethodHandler<UpdateContactPersonResourceTask, UpdateContactPersonResourceTask.Method>{

		protected PutContactPersonResourceHandler(){
	        super(UpdateContactPersonResourceTask.Method.Put);
        }

		@Override
        protected void handle(
                ResourceContext<ContactPerson, ContactPersonImpl, ContactPersonImpl> context,
                UpdateContactPersonResourceTask task) throws Throwable
        {
			ContactPersonImpl ContactPerson = getContactPersonById(context, task.id);
	        context.putResource(ContactPerson);
        }

	}
	
	@Publish
	protected final class ModifyContactPersonResourceHandler extends TaskMethodHandler<UpdateContactPersonResourceTask, UpdateContactPersonResourceTask.Method>{

		protected ModifyContactPersonResourceHandler(){
	        super(UpdateContactPersonResourceTask.Method.Modify);
        }

		@Override
        protected void handle(
                ResourceContext<ContactPerson, ContactPersonImpl, ContactPersonImpl> context,
                UpdateContactPersonResourceTask task) throws Throwable
        {
			context.removeResource(task.id);
			context.putResource(getContactPersonById(context, task.id));
        }		
	}
	
	
	@Publish
	protected final class RemoveContactPersonResourceHandler extends TaskMethodHandler<UpdateContactPersonResourceTask, UpdateContactPersonResourceTask.Method>{

		protected RemoveContactPersonResourceHandler(){
	        super(UpdateContactPersonResourceTask.Method.Remove);
        }

		@Override
        protected void handle(
                ResourceContext<ContactPerson, ContactPersonImpl, ContactPersonImpl> context,
                UpdateContactPersonResourceTask task) throws Throwable
        {
	        context.removeResource(task.id);
        }		
	}
	@StructClass
	public static final class ContactPersonImpl implements ContactPerson{

		/**
		 * ��ĿID
		 */
		private GUID id;

		/**
		 * ��ϵ������
		 */
		private String name;

		/**
		 * ����
		 */
		private String department;

		/**
		 * �̶��绰
		 */
		private String landLineNumber;

		/**
		 * �ֻ�
		 */
		private String mobileNumber;
		
		/**
		 * �����ʼ�
		 */
		private String email;

		/**
		 * ʡ��
		 */
		private EnumEntity province;

		/**
		 * ����
		 */
		private EnumEntity city;

		/**
		 * ����
		 */
		private EnumEntity district;

		/**
		 * ��ϸ��ַ
		 */
		private String address;

		/**
		 * �ʱ�
		 */
		private String postCode;

		/**
		 * �ͻ���Ӧ��Id
		 */
		private GUID partnerId;
		
		private long lastDate;
		
		

		public long getLastDate(){
        	return lastDate;
        }

		public void setLastDate(long lastDate){
        	this.lastDate = lastDate;
        }

		public GUID getId(){
			return id;
		}

		public void setId(GUID id){
			this.id = id;
		}

		public String getName(){
			return name;
		}

		public void setName(String name){
			this.name = name;
		}

		public String getDepartment(){
			return department;
		}

		public void setDepartment(String department){
			this.department = department;
		}

		public String getLandLineNumber(){
			return landLineNumber;
		}

		public void setLandLineNumber(String landLineNumber){
			this.landLineNumber = landLineNumber;
		}

		public String getMobileNo(){
			return mobileNumber;
		}

		public void setMobileNo(String mobileNumber){
			this.mobileNumber = mobileNumber;
		}

		public EnumEntity getProvince(){
			return province;
		}

		public void setProvince(EnumEntity province){
			this.province = province;
		}

		public EnumEntity getCity(){
			return city;
		}

		public void setCity(EnumEntity city){
			this.city = city;
		}

		public EnumEntity getDistrict(){
			return district;
		}

		public void setDistrict(EnumEntity district){
			this.district = district;
		}

		public String getAddress(){
			return address;
		}

		public void setAddress(String address){
			this.address = address;
		}

		public String getPostCode(){
			return postCode;
		}

		public void setPostCode(String postCode){
			this.postCode = postCode;
		}

		public GUID getPartnerId(){
			return partnerId;
		}

		public void setPartnerId(GUID partnerId){
			this.partnerId = partnerId;
		}

		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
	}

}
