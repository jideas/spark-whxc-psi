/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.finance.invoice.intf.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.util.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.spark.psi.account.intf.constant.invoice.InvoiceConstant;
import com.spark.psi.publish.InvoiceType;

/**
 * <p>字符串搜索翻译处理工具</p>
 *	主用于返回value中存在的key值集合。key值不重复
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2011-11-28
 */
public class SearchStringUtil {
	private final Map<String, String> map = new HashMap<String, String>();
	/**
	 * 存入key-value
	 * @param key
	 * @param value
	 * @return StringSearchUtil
	 */
	public SearchStringUtil put(String key, String value){
		map.put(key, value);
		return this;
	}
	
	public SearchStringUtil()
	{
		initMap();
	}
	private void initMap() {
		map.put(InvoiceConstant.ZZSFP, "增值税发票");
		map.put(InvoiceConstant.PTFP, "普通发票");
	}

	/**
	 * 获取value中存在指定值的key值集合
	 * @param search
	 * @return List<String>
	 */
	public List<String> getKeys(String searchText){
		List<String> list = new ArrayList<String>();
		if(null==searchText||"".equals(searchText))
		{
			return list;
		}
		if(InvoiceType.Common.getName().indexOf(searchText)!=-1)
		{
			list.add(InvoiceType.Common.getCode());
		}
		if(InvoiceType.ValueAddesTax.getName().indexOf(searchText)!=-1)
		{
			list.add(InvoiceType.ValueAddesTax.getCode());
		}
		return list;
	}
	
	/**
	 * 清空集合
	 *  void
	 */
	public void clear(){
		map.clear();
	}
}
