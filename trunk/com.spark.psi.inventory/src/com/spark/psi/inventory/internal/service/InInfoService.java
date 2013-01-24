/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.outstorage.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-17       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.intf.key.instorage.InstorageKey;
import com.spark.psi.inventory.intf.util.instorage.dbox.GoodsInstoInfo;

/**
 * <p>�������������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-17
 */

public class InInfoService extends Service{

	public InInfoService(){
		super("OutInfoService");
	}

	@Publish
	protected class InInfoProvider extends OneKeyResultListProvider<GoodsInstoInfo, GUID>{

		@Override
		protected void provide(Context context, GUID recid, List<GoodsInstoInfo> list) throws Throwable{
			InstorageKey key = new InstorageKey();
			key.setRelaOrdGuid(recid);
			list.addAll(context.getList(GoodsInstoInfo.class, key));
		}

	}
}
