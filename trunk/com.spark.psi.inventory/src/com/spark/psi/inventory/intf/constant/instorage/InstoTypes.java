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

public class InstoTypes{

	/**
	 * 
	 */
	public InstoTypes(Context context){
		initstatussMap();
//		fillDataTostatuss(context);
	}
	
	/**
	 * @param code
	 * @return ���ͱ���
	 */
	public String getViewName(String code){
		return this.types.get(code);
	}

	/**
	 * ��ѯ���������
	 * @param context 
	 */
//	private void fillDataTostatuss(Context context){
//		List<ListTask> lts = Dictionary.getList(context, SAAS.DictionaryType.INSTOTYPE.toString(), null);
//		for(ListTask lt : lts){
//			this.types.put(lt.code, lt.codeName);
//		}
//	}

	/**
	 * �����������
	 */
	private void initstatussMap(){
		this.types = new HashMap<String, String>();
	}

	private Map<String, String> types = null;
}
