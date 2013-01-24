/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.util.StringUtils;
import com.spark.psi.base.Employee;
import com.spark.psi.base.internal.entity.FunctionPositionImpl;
import com.spark.psi.base.internal.service.orm.Orm_FunctionPosition;
import com.spark.psi.base.internal.service.orm.Orm_FunctionPositionByFunidAndUserId;
import com.spark.psi.base.publicimpl.FunctionPositionInfoImpl;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.function.FunctionPositionInfo;
import com.spark.psi.publish.base.function.UpdateFunctionPositionTask;

/**
 * <p>�û�����ģ�鰴ť��λ����</p>
 *


 *
 
 * @version 2012-4-18
 */

public class FunctionPositionService extends Service{

	
	final static Map<String,Integer> nullRolePriority = new HashMap<String, Integer>();
	
	final Orm_FunctionPosition orm_FunctionPosition;
	
	final Orm_FunctionPositionByFunidAndUserId orm_FunctionPositionByFunidAndUserId;
	
	final static Map<String,Map<String,Integer>> rolePrioritys = new HashMap<String, Map<String,Integer>>();
	
	protected FunctionPositionService(final Orm_FunctionPosition orm_FunctionPosition,
			final Orm_FunctionPositionByFunidAndUserId orm_FunctionPositionByFunidAndUserId){
	    super("�û�����ģ�鰴ť��λ����");
	    this.orm_FunctionPosition = orm_FunctionPosition;
	    this.orm_FunctionPositionByFunidAndUserId = orm_FunctionPositionByFunidAndUserId;
    }
	
	
	/**
	 * ��ʼ����ɫģ�����ȼ�
	 * 
	 */
	@Override
	protected void init(Context context){
		InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("rolePriority"));
		try{
	        BufferedReader bf = new BufferedReader(reader);
	        String[] roles = bf.readLine().split("\\|");
	        while(bf.ready()){
	        	String str = bf.readLine();
	        	if(StringUtils.isEmpty(str))break;
	        	String[] rolePriority = str.split("\\|");
	        	if(rolePriority.length!=roles.length){
	        		throw new ArrayIndexOutOfBoundsException("��ɫ���ȼ������ļ���ʽ�д���");
	        	}
	        	Map<String,Integer> rolePriorityMap = new HashMap<String, Integer>();
	        	for(int r = 2;r<rolePriority.length;r++){
	        		String roleP = rolePriority[r].trim();
	        		rolePriorityMap.put(roles[r].trim(), roleP.length()==0 ? 0 : Integer.parseInt(roleP));
	            }
	        	rolePrioritys.put(rolePriority[1], rolePriorityMap);
	        }
	        reader.close();
        }
        catch(IOException e){
	        e.printStackTrace();
        }finally{
        	try{
	            reader.close();
            }
            catch(IOException e){
	            e.printStackTrace();
            }
        }
	}


	/**
	 * ͨ���û�id��ù���ģ�鶨λ�б�
	 * 
	 * 
	 */
	@Publish
	protected final class GetFunctionPositionListByUserIdProvider extends ResultProvider<FunctionPositionInfo>{

		@Override
        protected FunctionPositionInfo provide(Context context) throws Throwable
        {
			Employee emp = context.find(Employee.class);
			List<FunctionPositionImpl> list = context.newORMAccessor(orm_FunctionPosition).fetch(emp.getId());
			List<FunctionPositionInfoImpl.FunctionPositionImpl> result = 
				new ArrayList<FunctionPositionInfoImpl.FunctionPositionImpl>();
			for(Auth auth : emp.getAcls()){
//				System.out.println(auth.getName());
				boolean isInit = false; //�Ƿ��Ѵ����ģ��Ķ�λ��Ϣ��ģ�鶨λ��ϢĬ��û�У�ֻ���û��ı���ģ�鰴ť�Ķ�λ��Ϣ�Ż�����Ӧ�ļ�¼
				//���û�г�ʼ��������Ҫ�ύһ��Ĭ�ϵĶ�λ��Ϣ��
				FunctionPositionInfoImpl.FunctionPositionImpl entity =	
					new FunctionPositionInfoImpl.FunctionPositionImpl();
	            for(FunctionPositionImpl fun : list){
	                if(fun.getFunctionId().equals(auth.getCode())){
	                	entity.setFunctionId(fun.getFunctionId());
	                	entity.setPutMain(fun.isPutMain());
	                	entity.setXindex(fun.getXindex());
	                	entity.setYindex(fun.getYindex());
	                	entity.setRolePriority(rolePrioritys.get(fun.getFunctionId()));
	                	if(entity.getRolePriority()==null){
	                		entity.setRolePriority(nullRolePriority);
	                	}
	                	entity.setInited(true);
	                	isInit = true;
	                }
                }
	            if(!isInit){ //���û�г�ʼ����λ��Ϣ����ʵ����һ��Ĭ�ϵĶ�λ��Ϣ
	            	entity.setFunctionId(auth.getCode());
                	entity.setRolePriority(rolePrioritys.get(auth.getCode()));
                	if(entity.getRolePriority()==null){
                		entity.setRolePriority(nullRolePriority);
                	}
	            }
	            result.add(entity);
            }
	        return new FunctionPositionInfoImpl(result);
        }
		
	}
	
	/**
	 * 
	 * <p>�޸��û�����ģ�鰴ť��λ</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-18
	 */
	@Publish
	protected final class UpdateFunctionPositionHandler extends SimpleTaskMethodHandler<UpdateFunctionPositionTask>{

		@Override
        protected void handle(Context context, UpdateFunctionPositionTask task)
                throws Throwable
        {
			ORMAccessor<FunctionPositionImpl> acc = context.newORMAccessor(orm_FunctionPositionByFunidAndUserId);
			FunctionPositionImpl entity = acc.findByPKey(task.getFunctionId(),task.getUserId());
			boolean isCreate = false;
			if(entity==null){
				entity = new FunctionPositionImpl();
				entity.setId(context.newRECID());
				entity.setFunctionId(task.getFunctionId());
				entity.setUserId(task.getUserId());
				isCreate = true;
			}
			entity.setButMain(task.isPutMain());
			entity.setXindex(task.getXindex());
			entity.setYindex(task.getYindex());
			if(isCreate){
				acc.insert(entity);
			}else{
				acc.update(entity);
			}
        }
		
	}
	
}
