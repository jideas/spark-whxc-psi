/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.util
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.constant.instorage;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;

/**
 * <p>TODO ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-10
 */

public class InstoStatus{

	/**
	 * 
	 */
	public InstoStatus(Context context){
		initstatussMap();
//		fillDataTostatuss(context);
	}
	
	/**
	 * @param code
	 * @return ״̬����
	 */
	public String getViewName(String code){
		return this.statuss.get(code);
	}

	/**
	 * ��ѯ���������
	 * @param context 
	 */
//	private void fillDataTostatuss(Context context){
//		List<ListTask> lts = Dictionary.getList(context, SAAS.DictionaryType.instoState.toString(), null);
//		for(ListTask lt : lts){
//			this.statuss.put(lt.code, lt.codeName);
//		}
//	}

	/**
	 * �����������
	 */
	private void initstatussMap(){
		this.statuss = new HashMap<String, String>();
	}

	private Map<String, String> statuss = null;
}
