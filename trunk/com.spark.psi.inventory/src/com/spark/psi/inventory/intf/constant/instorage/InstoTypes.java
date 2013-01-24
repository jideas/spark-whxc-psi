/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.constant.instorage;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
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
	 * @return 类型标题
	 */
	public String getViewName(String code){
		return this.types.get(code);
	}

	/**
	 * 查询并填充数据
	 * @param context 
	 */
//	private void fillDataTostatuss(Context context){
//		List<ListTask> lts = Dictionary.getList(context, SAAS.DictionaryType.INSTOTYPE.toString(), null);
//		for(ListTask lt : lts){
//			this.types.put(lt.code, lt.codeName);
//		}
//	}

	/**
	 * 建立存放容器
	 */
	private void initstatussMap(){
		this.types = new HashMap<String, String>();
	}

	private Map<String, String> types = null;
}
