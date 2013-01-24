package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Notice;
import com.spark.psi.base.internal.entity.NoticeImpl;
import com.spark.psi.base.internal.service.orm.Orm_Notice;

/**
 * <p>ģ��乫�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-26
 */

public class NoticeModuleService extends Service{

	final Orm_Notice ormNotice;

	/**
	 * 
	 *���췽��
	 */
	protected NoticeModuleService(Orm_Notice ormNotice){
		super("com.spark.psi.base.internal.service.NoticeModuleService");
		this.ormNotice = ormNotice;
	}

	/**
	 * ���ݹ���GUID��ѯ����
	 */
	@Publish
	protected class findByGUID extends OneKeyResultProvider<Notice, GUID>{

		@Override
		protected Notice provide(Context context, GUID key) throws Throwable{
			ORMAccessor<NoticeImpl> ormAccessor = context.newORMAccessor(ormNotice);
			NoticeImpl notice = null;
			try{
				notice = ormAccessor.findByRECID(key);
			}
			finally{
				ormAccessor.unuse();
			}
			return notice;
		}

	}
}
